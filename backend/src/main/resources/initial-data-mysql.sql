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

INSERT INTO user_role(name) VALUES('agent');
INSERT INTO user_role(name) VALUES('client');

select * from user_role;

insert into user(email, password, role_id) values ('pera@mail.com', '$2a$12$vzvp8zB6eTuLxPJMfgTcQOOUDRIaMd6KNoqeOIzLAAPszaQqQnMBS', 1);

insert into file (alt_text, file, description)
VALUES ('paris1',
        LOAD_FILE('/home/tijanajanjic/Documents/PMF/RIS projekat/backend/src/main/resources/images/paris1.png'), 'Paris 1');
insert into file (alt_text, file, description)
VALUES ('paris2',
        LOAD_FILE('/home/tijanajanjic/Documents/PMF/RIS projekat/backend/src/main/resources/images/paris2.png'), 'Paris 2');

insert into city_tour (id, city_id, hotel_id, file_id)
values (1, 1, 1, 1);
insert into city_tour (id,  city_id, hotel_id, file_id)
values (2, 2, 3, 2);

insert into tour (id, name, description, max_passengers, deadline, start_date, end_date, tour_type, transportation_type, price, file_id)
values (1, 'France winder tour',
        'Thanks to its expertise in the field, France Tourisme offers you a selection of lunch and dinner cruises to discover Paris and its prestigious monuments according to your desires and your budget. You could board for a romantic dinner cruise with musical background, or enjoy an early dinner-cruise to be completed with a cabaret show or a Paris night tour, or you could appreciate a "Prestige" dinner cruise with a gourmet menu, or organize your cruise to celebrate special events among friends or family! It''s up to you!',
        50, '2022-01-11 00:00:00', '2022-01-10 00:00:00','2022-01-15 00:00:00', 'WEEKEND', 'BUS', 200, 1);

insert into tour (id, name, description, max_passengers, deadline, start_date, end_date, tour_type, transportation_type, price, file_id)
   values (2, 'France summer tour',
           'Thanks to its expertise in the field, France Tourisme offers you a selection of lunch and dinner cruises to discover Paris and its prestigious monuments according to your desires and your budget. You could board for a romantic dinner cruise with musical background, or enjoy an early dinner-cruise to be completed with a cabaret show or a Paris night tour, or you could appreciate a "Prestige" dinner cruise with a gourmet menu, or organize your cruise to celebrate special events among friends or family! It''s up to you!',
           50, '2022-01-11 00:00:00', '2022-01-10 00:00:00','2022-01-15 00:00:00', 'WEEKEND', 'BUS', 200,2 );

insert into tour (id, name, description, max_passengers, deadline, start_date, end_date, tour_type, transportation_type, price, file_id)
values (4, 'Italian summer',
        'Thanks to its expertise in the field, France Tourisme offers you a selection of lunch and dinner cruises to discover Paris and its prestigious monuments according to your desires and your budget. You could board for a romantic dinner cruise with musical background, or enjoy an early dinner-cruise to be completed with a cabaret show or a Paris night tour, or you could appreciate a "Prestige" dinner cruise with a gourmet menu, or organize your cruise to celebrate special events among friends or family! It''s up to you!',
        50, '2022-01-11 00:00:00', '2022-01-10 00:00:00','2022-01-15 00:00:00', 'WEEKEND', 'BUS', 200,2 );

insert into tours_city_tours(tour_id, city_tour_id) values (1,1);
insert into tours_city_tours(tour_id, city_tour_id) values (1,2);
insert into tours_city_tours(tour_id, city_tour_id) values (2,2);

insert into city_gallery(city_id, gallery_id) VALUES (1,1);


insert into city_landmarks (city_id, landmarks_id) VALUES (1,1);
insert into city_landmarks (city_id, landmarks_id) VALUES (1,2);

insert into city_tour_landmarks(city_tour_id, landmarks_id) VALUES (1,1);
insert into city_tour_landmarks(city_tour_id, landmarks_id) VALUES (1,2);

update tour set name = 'December in France' where tour.id = 1 ;