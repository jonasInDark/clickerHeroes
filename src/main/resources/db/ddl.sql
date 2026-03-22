drop table if exists users cascade;
drop table if exists heroes cascade;
drop table if exists hero_skills cascade;
drop table if exists monsters cascade;
drop table if exists gold cascade;
drop table if exists hired_heroes cascade;
drop table if exists activated_hero_skills cascade;

CREATE TABLE users
(
    id         serial primary key,
    name       varchar(15)       not null,
    password   varchar(255)      not null,
    stage      integer default 1 not null,
    created_at timestamptz       not null,
    updated_at timestamptz       not null
);

CREATE TABLE heroes
(
    id                  serial primary key,
    name                varchar(100)          not null,
    details             varchar(255)          not null,
    base_dps_mantissa   float   DEFAULT 1.000 not null,
    base_dps_exponent   integer DEFAULT 1     not null,
    base_price_mantissa float   DEFAULT 1.000 not null,
    base_price_exponent integer DEFAULT 1     not null,
    created_at          timestamptz           not null
);

CREATE TABLE monsters
(
    id          serial primary key,
    user_id     integer               NOT NULL,
    hp_mantissa float   DEFAULT 1.000 not null,
    hp_exponent integer DEFAULT 1     not null,
    is_boss     boolean DEFAULT false not null,
    created_at  timestamptz           not null
);

CREATE TABLE gold
(
    ID            serial primary key,
    user_id       integer               NOT NULL,
    gold_mantissa float   DEFAULT 1.000 not null,
    gold_exponent integer DEFAULT 1     not null,
    created_at    timestamptz           not null,
    updated_at    timestamptz           not null
);

CREATE TABLE hero_skills
(
    id                     serial primary key,
    heroes_id              integer      NOT NULL,
    name                   varchar(100) not null,
    details                varchar(255) not null,
    required_level         integer      not null,
    required_gold_mantissa float        not null,
    required_gold_exponent integer      not null,
    skill_type             varchar(10)  not null check ( skill_type in
                                                         ('DPS_ACTIVE', 'DPS_PASSIVE', 'GOLD_ACTIVE', 'GOLD_PASSIVE') ),
    created_at             timestamptz  not null
);

CREATE TABLE hired_heroes
(
    id             serial primary key,
    user_id        integer               NOT NULL,
    hero_id        integer               NOT NULL,
    dps_mantissa   float   DEFAULT 1.000 not null,
    dps_exponent   integer DEFAULT 1     not null,
    price_mantissa float   DEFAULT 1.000 not null,
    price_exponent integer DEFAULT 1     not null,
    level          integer               not null,
    created_at     timestamptz           not null,
    updated_at     timestamptz           not null
);

create table activated_hero_skills
(
    id            serial primary key,
    hired_hero_id integer     not null,
    hero_skill_id integer     not null,
    status        varchar(10) not null check ( status in ('LEARNED', 'NOT_LEARNED') ),
    created_at    timestamptz not null,
    updated_at    timestamptz not null
);

alter table activated_hero_skills
    add constraint fk_activated_hero_skills_hired_hero_id
        foreign key (hired_hero_id) references hired_heroes (id),
    add constraint fk_activated_hero_skills_hero_skill_id
        foreign key (hero_skill_id) references hero_skills (id),
    add constraint uk_activated_hero_skills_hired_hero_skill unique (hired_hero_id, hero_skill_id);

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

alter table hired_heroes
    add constraint fk_hired_heroes_user_id
        foreign key (user_id) references users (id),
    add constraint fk_hired_heroes_hero_id
        foreign key (hero_id) references heroes (id);
