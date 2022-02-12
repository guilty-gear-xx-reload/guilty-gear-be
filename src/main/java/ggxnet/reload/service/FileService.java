package ggxnet.reload.service;


import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public class FileService {

    public List<String> readNodeList(String configFile) {
        File file = new File(configFile);
        if (!file.exists()) {
            return new LinkedList<>();
        }
        List<String> fileData = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                int dollarIndex = line.indexOf("$");
                int percentIndex = line.indexOf("%");
                if (dollarIndex != -1 && percentIndex != -1) {
                    long time = Long.parseLong(line.substring(0, dollarIndex));
                    int busy = Integer.parseInt(line.substring(percentIndex + 1, percentIndex + 2));
                    int removeTime = 0;
                    if (busy == 0) {
                        removeTime = 70;//const
                    } else {
                        removeTime = 600;//const
                    }
                    if (time + removeTime > Instant.now().getEpochSecond()) {
                        fileData.add(line);
                    }
                }

            }
        } catch (FileNotFoundException e) {
            log.error("", e);
        } catch (IOException e) {
            log.error("", e);
        }
        return fileData;
    }

    public int findNode(String node, List<String> dataFile) {
        for (int i = 0; i < dataFile.size(); i++) {
            boolean contains = dataFile.get(i).contains(node);
            if (contains) {
                return i;
            }
        }

        return -1;
    }

    public void saveAll(List<String> dataFile, String configFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(configFile))) {
            for (String line : dataFile) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
