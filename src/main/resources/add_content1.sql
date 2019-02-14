insert into role (name) values ('admin');
insert into role (name) values ('moderator');
insert into role (name) values ('user');

insert into city (name) values ('Kazan');
insert into city (name) values ('Moscow');
insert into city (name) values ('Samara');

insert into "user" (login, password, name, email, phone_number, role_id, city_id, is_actual)
values ('max', '111', 'Максим А.', 'max@ya.ru', '+7 999 999 9999', 3, 1, true);
insert into "user" (login, password, name, email, phone_number, role_id, city_id, is_actual)
values ('u1', '1', 'Иванов Иван', 'ivanov@gmail.com', '+7 960 000 0000', 3, 2, true);
insert into "user" (login, password, name, email, phone_number, role_id, city_id, is_actual)
values ('admin', 'admin', 'admin', 'admin@gmail.com', '+7 960 777 7777', 1, 2, true);
insert into "user" (login, password, name, email, phone_number, role_id, city_id, is_actual)
values ('mod', 'mod', 'moderator', 'moderator@ya.ru', '+7 960 777 5555', 2, 2, true);

