create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values('iphone', 4100), ('ipods', 1800), ('note lenovo', 6390), 
('huawei nova ', 9999), ('Nokia 3310', 2999), ('headphones JBL C100SI', 799);
insert into people(name) values('Alex'), ('Ruslan'), ('Gordon'), ('Max'), ('Mary'), ('Bobi');
insert into devices_people(people_id, device_id) values(1, 1), (1, 4), (1, 6);
insert into devices_people(people_id, device_id) values(2, 2), (2, 3), (2, 5);
insert into devices_people(people_id, device_id) values(3, 1), (3, 2), (3, 6);
insert into devices_people(people_id, device_id) values(4, 4), (4, 2);
insert into devices_people(people_id, device_id) values(5, 1);
insert into devices_people(people_id, device_id) values(6, 2), (6, 3), (6, 4), (6,5), (6, 6);

select avg(price) from devices;

select p.name, avg(d.price) 
from people as p 
join devices_people dp on dp.people_id = p.id  
join devices as d on dp.device_id = d.id 
group by p.name;

select p.name, avg(d.price) 
from people as p 
join devices_people as dp on dp.people_id = p.id  
join devices as d on dp.device_id = d.id 
group by p.name
having avg(d.price) > 5000;
