insert into country values (1, 'France');
insert into country values (2, 'Greece');
insert into country values (3, 'Spain');
insert into country values (4, 'Italy');
insert into country values (5, 'Germany');

insert into city values (1, 'Paris', 1);
insert into city values (2, 'Lyon', 1);
insert into city values (3, 'Nice', 1);
insert into city values (4, 'Marseille', 1);
insert into city values (5, 'Toulouse', 1);

insert into hotel values (1, 'B&B Hôtel Paris Porte des Lilas', 4, 1);
insert into hotel values (2, 'Campanile Hotel', 3, 1);
insert into hotel values (3, 'Radisson Blu Hotel', 4, 2);

insert into landmark (id, name, city_id, enterance_price) values (1, 'Arc de Triomphe', 1, 12.0);
insert into landmark (id, name, city_id, enterance_price) values (2, 'Eiffel Tower', 1, 11.0);

insert into city_tour (id, included, city_id, hotel_id)
values (1, false, 1, 1);
insert into city_tour (id, included, city_id, hotel_id)
values (2, true, 2, 3);

insert into tour (id, name, max_passengers, deadline, start_date, end_date, tour_type, transportation_type, price)
   values (1, 'France summer tour', 50, '2022-01-11 00:00:00', '2022-01-10 00:00:00','2022-01-15 00:00:00', 'WEEKEND', 'BUS', 200);

insert into user (email, password) values ('pera@mail.com', 'pera')