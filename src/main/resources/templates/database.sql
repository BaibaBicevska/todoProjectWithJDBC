SET GLOBAL time_zone = '+2:00';
-- DROP DATABASE IF EXISTS sys;
CREATE DATABASE IF NOT EXISTS sys ;

USE sys;
CREATE TABLE todos(
     id int not null auto_increment,
    description varchar(30) not null,
    isComplete boolean default false,
    createdAt timestamp default current_timestamp,
    updatedAt timestamp default  current_timestamp on UPDATE current_timestamp,
    primary key (id)
                )