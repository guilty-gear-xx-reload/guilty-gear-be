INSERT INTO users
(id, password, username)
VALUES(1, '$2a$12$2Kb5Po8vJ2JeKyNBFLQrt.iD8yElh/nUz9.Qj19JWrK.s4pl3bM7i', 'alfu');

INSERT INTO users
(id, password, username)
VALUES(2, '$2a$12$2Kb5Po8vJ2JeKyNBFLQrt.iD8yElh/nUz9.Qj19JWrK.s4pl3bM7i', 'arek');

INSERT INTO player_stats
(id, "rank", score, total_battle, total_draw, total_error, total_lose, total_win, wins)
VALUES('26b6092e-yyyy-40b4-98d2-198c511dd22c', 0, 0, 0, 0, 0, 0, 0, 0);

INSERT INTO player_stats
(id, "rank", score, total_battle, total_draw, total_error, total_lose, total_win, wins)
VALUES('26b609de-xxxx-40b4-98d2-198c511dd8ac', 0, 0, 0, 0, 0, 0, 0, 0);

INSERT INTO player_config
(id, active, broadcast_enable, delay, disp_inv_combo, enable_net, fps_enable, ignore_miss_node, ignore_slow, intrusion_enable, last_activity, message, port, rounds, script_address, slow_rate, trip, use_ex, user_name, "version", wait, watch_max_nodes_enable, watch_replay_enable)
VALUES('b6b609de-xxxx-40b4-98d2-198c511dd8ac', false, true, 6, 1, true, true, false, false, false, 0, '', 10550, 3, '26.39.40.108', 5, null, true, 'Alfu', 120, 3, true, false);

INSERT INTO player_config
(id, active, broadcast_enable, delay, disp_inv_combo, enable_net, fps_enable, ignore_miss_node, ignore_slow, intrusion_enable, last_activity, message, port, rounds, script_address, slow_rate, trip, use_ex, user_name, "version", wait, watch_max_nodes_enable, watch_replay_enable)
VALUES('xxxxxx-yyyy-40b4-98d2-198c511dd8ac', false, true, 6, 1, true, true, false, false, false, 0, '', 10551, 3, '26.68.204.99', 5, null, true, 'Arek', 120, 3, true, false);

INSERT INTO player(id, nickname, player_config_id, player_stats_id, user_id)
VALUES('b6b609de-8ac8-40b4-98d2-198c511dd8ac', 'Alfu', 'b6b609de-xxxx-40b4-98d2-198c511dd8ac', '26b609de-xxxx-40b4-98d2-198c511dd8ac', 1);

INSERT INTO player(id, nickname, player_config_id, player_stats_id, user_id)
VALUES('xxxxxx-8ac8-40b4-98d2-198c511dd8ac', 'Arek', 'xxxxxx-yyyy-40b4-98d2-198c511dd8ac', '26b6092e-yyyy-40b4-98d2-198c511dd22c', 2);