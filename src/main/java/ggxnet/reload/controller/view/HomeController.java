package ggxnet.reload.controller.view;

import org.springframework.core.NestedExceptionUtils;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;

import static ggxnet.reload.configuration.WebSocketConfiguration.PING_PROVIDER_ENDPOINT;

@Controller
public class HomeController {
  @GetMapping
  public String getStartPage(Model model) {
    model.addAttribute("title", PageTitle.DEFAULT);
    return "index";
  }

  @MessageExceptionHandler
  @SendTo("/topic/errors")
  public String handleException(IllegalArgumentException e) {
    var message = ("an exception occurred! " + NestedExceptionUtils.getMostSpecificCause(e));
    System.out.println(message);
    return message;
  }

  @MessageMapping(PING_PROVIDER_ENDPOINT)
  @SendTo("/topic/pings")
  GreetingResponse greet() throws Exception {
    Thread.sleep(1_000);
    return new GreetingResponse("Hello, I am channel which you subscribed and I am gonna send you pings every 1 second");
  }

  record GreetingResponse(String message){}
}
