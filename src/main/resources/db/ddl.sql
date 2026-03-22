CREATE TABLE users
(
    id       integer primary key,
    name     varchar(15)       not null,
    password varchar(255)      not null,
    stage    integer default 1 not null
);

CREATE TABLE heroes
(
    id             integer primary key,
    name           varchar(100)          not null,
    details        varchar(255)          not null,
    dps_mantissa   float   DEFAULT 1.000 not null,
    dps_exponent   integer DEFAULT 1     not null,
    price_mantissa float   DEFAULT 1.000 not null,
    price_exponent integer DEFAULT 1     not null,
    level          integer default 0     not null
);

CREATE TABLE monsters
(
    id          integer primary key,
    user_id     integer               NOT NULL,
    hp_mantissa float   DEFAULT 1.000 not null,
    hp_exponent integer DEFAULT 1     not null,
    is_boss     boolean DEFAULT false not null,
    name        varchar(100)          not null
);

CREATE TABLE gold
(
    ID            integer primary key,
    user_id       integer               NOT NULL,
    gold_mantissa float   DEFAULT 1.000 not null,
    gold_exponent integer DEFAULT 1     not null
);

CREATE TABLE hero_skills
(
    id        integer primary key,
    heroes_id integer      NOT NULL,
    name      varchar(100) not null,
    details   varchar(255) not null,
    type      varchar(10)  not null check ( type in ('dps_active', 'dps_passive', 'gold_active', 'gold_passive') )
);

CREATE TABLE users_heroes
(
    user_id integer NOT NULL,
    hero_id integer NOT NULL
);

alter table users
    add constraint uk_users_name unique (name),
    add constraint uk_users_password unique (password);

alter table heroes
    add constraint uk_heroes_name unique (name);

alter table monsters
    add constraint fk_monsters_user_id
        foreign key (user_id) references users (id),
    add constraint uk_monsters_user_id unique (user_id);
alter table monsters
    add constraint uk_monsters_name unique (name);

alter table gold
    add constraint fk_gold_user_id
        foreign key (user_id) references users (id),
    add constraint uk_gold_user_id unique (user_id);

alter table hero_skills
    add constraint fk_hero_skills_heroes_id
        foreign key (heroes_id) references heroes (id);
alter table hero_skills
    add constraint uk_hero_skills_name unique (name);

alter table users_heroes
    add constraint fk_users_heroes_user_id
        foreign key (user_id) references users (id),
    add constraint fk_users_heroes_hero_id
        foreign key (hero_id) references heroes (id);
