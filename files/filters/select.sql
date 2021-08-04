select * from product as p join type t on p.type_id = t.id where t.name = 'СЫР';
select * from product where name like '%мороженное%';
select * from product where expired_data < current_date;
select * from product where price = (select max(price) from product);
select t.name, count(p.type_id) from type as t join product p on p.type_id = t.id group by t.name;
select * from product as p join type t on p.type_id = t.id where t.name = 'СЫР' or t.name = 'МОЛОКО';

select t.name, count(p.type_id) from type as t 
join product p on p.type_id = t.id 
group by t.name 
having count(p.type_id) < 10;

select * from product as p join type t on p.type_id = t.id;
