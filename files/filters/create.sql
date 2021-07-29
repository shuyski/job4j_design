create table type(
	id serial primary key,
	name varchar(255)
);

create table product(
	id serial primary key,
	name varchar(255),
	type_id int references type(id),
	expired_data date,
	price float
);

insert into type(name) values('СЫР'), ('МОЛОКО'), ('Овощи'), ('Фрукты'), ('Хлеб'), ('Молочные изделия');

insert into product(name, type_id, expired_data, price) values('СЫР 1', 1, '10/08/2021', 98.1), ('Кефир 1', 6, '5/08/2021', 55), 
('Молоко 2', 2, '3/08/2021', 61), ('Огурцы', 3, '20/07/2021', 30.5), ('Яблоки', 4, '25/07/2021', 25),
('Батон', 5, '30/07/2021', 21.3), ('Сыр 2', 1, '28.07.2021', 156.8), ('Молоко 4', 2, '26/07/2021', 46.90),
('мороженное 1', 6, '31/07/2021', 40), ('мороженное 2', 6, '30/06/2021', 23);
