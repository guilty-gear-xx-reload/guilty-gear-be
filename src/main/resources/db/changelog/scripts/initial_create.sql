CREATE TABLE users (
	id int8 NOT NULL,
	password varchar(255) NULL,
	username varchar(255) NULL,
	CONSTRAINT users_pkey PRIMARY KEY (id)
);

CREATE TABLE player (
	id int8 NOT NULL,
	nickname varchar(255) NULL,
	user_id int8 NULL,
	CONSTRAINT player_pkey PRIMARY KEY (id)
);

CREATE TABLE sprite (
	id int8 NOT NULL,
	color_indexes oid NULL,
	file_size_in_bytes int8 NOT NULL,
	height int4 NOT NULL,
	posture_id int4 NOT NULL,
	width int4 NOT NULL,
	character_id int8 NULL,
	CONSTRAINT sprite_pkey PRIMARY KEY (id)
);

CREATE TABLE palette (
	id int8 NOT NULL,
	file_size_in_bytes int8 NOT NULL,
	"header" oid NULL,
	palette_type varchar(255) NULL,
	character_id int8 NULL,
	CONSTRAINT palette_pkey PRIMARY KEY (id)
);

CREATE TABLE palette_colors (
	palette_id int8 NOT NULL,
	b int2 NOT NULL,
	g int2 NOT NULL,
	r int2 NOT NULL,
	a int2 NOT NULL
);

CREATE TABLE character (
	id int8 NOT NULL,
	"name" varchar(255) NULL,
	CONSTRAINT character_pkey PRIMARY KEY (id)
);

CREATE TABLE player_stats (
	id varchar(255) NOT NULL,
	"rank" int4 NOT NULL,
	score int4 NOT NULL,
	total_battle int4 NOT NULL,
	total_draw int4 NOT NULL,
	total_error int4 NOT NULL,
	total_lose int4 NOT NULL,
	total_win int4 NOT NULL,
	wins int4 NOT NULL,
	CONSTRAINT player_stats_pkey PRIMARY KEY (id)
);

CREATE TABLE player_config (
	id varchar(255) NOT NULL,
	active bool NOT NULL,
	broadcast_enable bool NOT NULL,
	delay int4 NULL,
	disp_inv_combo int4 NULL,
	enable_net bool NOT NULL,
	fps_enable bool NOT NULL,
	ignore_miss_node bool NOT NULL,
	ignore_slow bool NOT NULL,
	intrusion_enable bool NOT NULL,
	last_activity int8 NOT NULL,
	message varchar(255) NULL,
	port int4 NULL,
	rounds int4 NULL,
	script_address varchar(255) NULL,
	slow_rate int4 NULL,
	trip varchar(255) NULL,
	use_ex bool NOT NULL,
	user_name varchar(255) NULL,
	"version" int4 NOT NULL,
	wait int4 NULL,
	watch_max_nodes_enable bool NOT NULL,
	watch_replay_enable bool NOT NULL,
	player_stats_id varchar(255) NULL,
	CONSTRAINT player_config_pkey PRIMARY KEY (id)
);

CREATE TABLE player_palette (
	id int8 NOT NULL,
	palette_id int8 NULL,
	player_id int8 NULL,
	CONSTRAINT player_palette_pkey PRIMARY KEY (id)
);

-- player_config foreign keys

ALTER TABLE player_config ADD CONSTRAINT fk1qg871j1mwohos3e700kl5v0r FOREIGN KEY (player_stats_id) REFERENCES player_stats(id);
ALTER TABLE palette ADD CONSTRAINT fklj9f1menrgsqj74apm81lnaqo FOREIGN KEY (character_id) REFERENCES character(id);
ALTER TABLE palette_colors ADD CONSTRAINT fkscdxgurju99vtm2pkofyo3xmf FOREIGN KEY (palette_id) REFERENCES palette(id);
ALTER TABLE sprite ADD CONSTRAINT fkkt69h3w74ipobujs4p17t9qo1 FOREIGN KEY (character_id) REFERENCES character(id);
ALTER TABLE player ADD CONSTRAINT fkoycxb69gpaapuv23fn52y0g50 FOREIGN KEY (user_id) REFERENCES users(id);
ALTER TABLE player_palette ADD CONSTRAINT fk7y022l0c67vtd3ny0bhxiggfb FOREIGN KEY (player_id) REFERENCES player(id);
ALTER TABLE player_palette ADD CONSTRAINT fknvwso9davqwy1gvo3mb0t798d FOREIGN KEY (palette_id) REFERENCES palette(id);


create sequence if not exists sprite_seq;
create sequence if not exists palette_seq;
create sequence if not exists character_seq;
create sequence if not exists users_seq;
create sequence if not exists player_seq;
create sequence if not exists player_palette_seq;
