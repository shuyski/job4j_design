create table posts(
	id serial primary key,
	post varchar(255),
	permitting varchar(255)
);

create table users(
	id serial primary key,
	name varchar(255),
	surname varchar(255),
	age int,
	post_id int references posts(id)
);

insert into posts(post, permitting) values('administrator', 'admin');
insert into posts(post, permitting) values ('programmer', 'admin');
insert into posts(post, permitting) values ('manager', 'user');
insert into posts(post, permitting) values ('booker', '1C');

insert into users(name, surname, age, post_id) values ('Ruslan', 'Shuyski', 28, 1);
insert into users(name, surname, age, post_id) values ('Jack', 'Jonson', 30, 1);
insert into users(name, surname, age, post_id) values ('Petr', 'Ivanov', 35, 2);
insert into users(name, surname, age, post_id) values ('Albert', 'Nosov', 20, 2);
insert into users(name, surname, age, post_id) values ('Ivan', 'Petrov', 23, 3);
insert into users(name, surname, age, post_id) values ('Vasily', 'Smirnov', 34, 3);
insert into users(name, surname, age, post_id) values ('Nikolay', 'Hotov', 25, 3);
insert into users(name, surname, age, post_id) values ('Svetlana', 'Akimova', 24, 3);
insert into users(name, surname, age, post_id) values ('Andrei', 'Sidorov', 43, 4);
insert into users(name, surname, age, post_id) values ('Alina', 'Byrago', 56, 4);
insert into users(name, surname, age) values ('Boris', 'Jonson', 21);
insert into users(name, surname, age) values ('Rypert', 'Dino', 22);
insert into users(name, surname, age) values ('Evgeny', 'Sherbak', 18);
insert into users(name, surname, age) values ('Alena', 'Rybak', 29);

select p.post, p.permitting, u.name, u.surname, u.age from posts as p join users as u on u.post_id = p.id;
select p.permitting, u.name, u.surname, u.age from posts as p join users as u on u.post_id = p.id;
select p.post, u.surname from posts as p join users as u on u.post_id = p.id;