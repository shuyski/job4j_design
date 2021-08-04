create table departaments(
	 id serial primary key,
	name varchar(255)
);

create table emploees(
	 id serial primary key,
	name varchar(255),
	dep_id int references departaments(id)
);

insert into departaments(name) values('departament 1'), ('departament 2'), ('departament 3'), ('departament 4'), ('departament 5');
insert into emploees(name, dep_id) values('name 1', 1), ('name 2', 3), ('name 3', 1), ('name 4', 2), ('name 5', 1), ('name 9', 3),
('name 10', 2), ('name 11', 1), ('name 12', 3), ('name 13', 2);
insert into emploees(name) values('name 6'), ('name 7'), ('name 8');

/*2. Выполнить запросы с left, rigth, full, cross соединениями*/
select * from emploees e left join departaments d on e.dep_id = d.id;
select * from emploees e right join departaments d on e.dep_id = d.id;
select * from emploees e full join departaments d on e.dep_id = d.id;
select * from emploees e cross join departaments d;

/*3. Используя left join найти департаменты, у которых нет работников*/
select * from departaments d left join emploees e on e.dep_id = d.id where e is null;


/*4. Используя left и right join написать запросы, которые давали бы одинаковый результат.*/
select * from emploees e left join departaments d on e.dep_id = d.id;
select * from departaments d right join emploees e on e.dep_id = d.id;

select * from departaments d left join emploees e on e.dep_id = d.id;
select * from emploees e right join departaments d on e.dep_id = d.id;


/*5. Создать таблицу teens с атрибутами name, gender и заполнить ее. Используя cross join составить все возможные разнополые пары*/
create table gender(
	id serial primary key,
	gender varchar(1)
);

create table teens(
	id serial primary key,
	name varchar(255),
	gender_id int references teens(id)
);

insert into gender(gender) values('M'), ('W');
insert into teens(name, gender_id) values('Alex', 2), ('Max', 1), ('Ted', 1);

select * from teens cross join gender;
