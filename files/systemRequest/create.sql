create table roles(
	id serial primary key,
	role varchar(255)
);

create table rules(
	id serial primary key,
	rule varchar(255)
);

create table roles_rules(
	id serial primary key,
	roles_id int references roles(id),
	rules_id int references rules(id)
);

create table states(
	id serial primary key,
	state varchar(255)
);

create table categoryes(
	id serial primary key,
	category varchar(255)
);

create table users(
	id serial primary key,
	name varchar(255),
	roles_id int references roles(id)
);

create table items(
	id serial primary key,
	item text,
	users_id int references users(id),
	categorye_id int references categoryes(id),
	state_id int references states(id)
);

create table comments(
	id serial primary key,
	comment text,
	item_id int references items(id)
);

create table attachs(
	id serial primary key,
	attach json,
	item_id int references items(id)
);