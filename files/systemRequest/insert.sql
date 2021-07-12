insert into roles(role) values('administrator');
insert into roles(role) values ('programmer');
insert into roles(role) values ('manager');
insert into roles(role) values ('booker');

insert into rules(rule) values('admin');
insert into rules(rule) values('1C');
insert into rules(rule) values('user');

insert into roles_rules(roles_id, rules_id) values (1, 1);
insert into roles_rules(roles_id, rules_id) values (2, 1);
insert into roles_rules(roles_id, rules_id) values (3, 3);
insert into roles_rules(roles_id, rules_id) values (4, 2);

insert into states(state) values('in processing');
insert into states(state) values('close');
insert into states(state) values('sent');

insert into categoryes(category) values('tehnical');
insert into categoryes(category) values('documentation');
insert into categoryes(category) values('programmatic');

insert into users(name, roles_id) values ('Ruslan Shuyski', 1);
insert into users(name, roles_id) values ('Petr Ivanov', 2);
insert into users(name, roles_id) values ('Ivan Petrov', 3);
insert into users(name, roles_id) values ('Andrei Sidorov', 4);

insert into items(item, users_id, categorye_id, state_id) values('network configuration', 1, 1, 1);
insert into items(item, users_id, categorye_id, state_id) values('printer connection', 1, 1, 2);
insert into items(item, users_id, categorye_id, state_id) values('development software', 2, 3, 3);
insert into items(item, users_id, categorye_id, state_id) values('issue a document', 4, 2, 1);

insert into comments(comment, item_id) values('comment one', 3);
insert into comments(comment, item_id) values('comment two', 3);
insert into comments(comment, item_id) values('comment three', 4);
insert into comments(comment, item_id) values('comment four', 4);

insert into attachs(attach, item_id) values('{ "Data": "10.07.2020", "Scan file": {"Name": "Ruslan", "role": "admin"}}', 4);