-- Table creation for 'users' table
create table users (
    email varchar(255) not null,
    first_name varchar(255),
    last_name varchar(255),
    avatar varchar(255),
    primary key (email)
);

-- Table creation for 'user_friends' join table
create table user_friends (
    user_email varchar(255) not null,
    friend_email varchar(255) not null,
    primary key (user_email, friend_email)
);

-- Foreign key constraints for 'user_friends' table
alter table user_friends
    add constraint FK_user_email
    foreign key (user_email)
    references users (email);

alter table user_friends
    add constraint FK_friend_email
    foreign key (friend_email)
    references users (email);