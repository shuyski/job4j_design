create table body(
	id serial primary key,
	name varchar(255)
);

create table engine(
	id serial primary key,
	name varchar(255)
);

create table box(
	id serial primary key,
	name varchar(255)
);

create table car(
	id serial primary key,
	name varchar(255),
	body_id int references body(id) unique,
	engine_id int references engine(id),
	box_id int references box(id) 
);


insert into body(name) values('X5'), ('X6'), ('X7'), ('Benz'), ('Pilot'), ('Cr-v'), ('Rio'), ('Spotage');
insert into engine(name) values('B47B20'), ('B57D30'), ('M102'), ('M266'), ('B3256nmK'), ('J35A4'), ('R20A9'), ('K24W'), ('G4FA');
insert into box(name) values('MKPP 4'), ('MKPP 5'), ('MKPP 6'), ('AKPP 4'), ('AKPP 5'), ('AKPP 6');
insert into car(name, body_id, engine_id, box_id) values('BMW', 1, 1, 2), ('BMW', 2, 2, 3), ('Mercedes', 4, 3, 4),
('Honda', 5, 5, 6), ('Honda', 6, 6, 4), ('Kia', 7, 8, 1);

select c.name, bd.name, e.name, b.name from car as c 
join body as bd on c.body_id = bd.id 
join engine as e on c.engine_id = e.id
join box as b on c.box_id = b.id
group by c.name, bd.name, e.name, b.name;


select bd.name from body as bd
except select bd.name from car as c join body as bd on c.body_id = bd.id;


select e.name from engine e
except select e.name from car c join engine e on c.engine_id = e.id;

select b.name from box b
except select b.name from car c join box b on c.box_id = b.id;