
insert into category (name, parent_id) VALUES ('бытовая электроника', null);
insert into category (name, parent_id) VALUES ('телефоны', 1);
insert into category (name, parent_id) VALUES ('ноутбуки', 1);
insert into category (name, parent_id) VALUES ('оргтехника', 1);
insert into category (name, parent_id) VALUES ('принтеры', 4);
insert into category (name, parent_id) VALUES ('сканеры', 4);

insert into ad ( title, text, user_id, category_id, price, price_min, date, day_count, confirm, is_actual)
values ('телефон Xiaomi','продам телефон',2,2,3000,2500,'13.02.2019',20,true, true);
insert into ad ( title, text, user_id, category_id, price, price_min, date, day_count, confirm, is_actual)
values ('телефон Nokia','продам телефон',2,2,900,400,'13.02.2019',10,true, true);
insert into ad ( title, text, user_id, category_id, price, price_min, date, day_count, confirm, is_actual)
values ('ноутбук Lenovo','продам ноутбук',1,3,2000,17500,'13.02.2019',30,true, true);
insert into ad ( title, text, user_id, category_id, price, price_min, date, day_count, confirm, is_actual)
values ('принтер Epson','продам принтер',1,5,10000,8500,'13.02.2019',25,true, true);
insert into ad ( title, text, user_id, category_id, price, price_min, date, day_count, confirm, is_actual)
values ('сканер','продам сканер',1,6,6000,5500,'13.02.2019',23,true, true);
insert into ad ( title, text, user_id, category_id, price, price_min, date, day_count, confirm, is_actual)
values ('принтер HP','продам принтер',1,5,7000,6700,'13.02.2019',15,true, true);
