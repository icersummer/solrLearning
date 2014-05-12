--create table user(_id int auto_increment primary key not null,username text,userpwd text);

-- table Author
create table author
(
id integer primary key autoincrement ,
name text,
gengre text,
spytime date
);

-- table Book
create table book(
id integer primary key autoincrement,
title text,
writer text,
description text,
isbn text,
url text,
createDate date
)

-- table City
