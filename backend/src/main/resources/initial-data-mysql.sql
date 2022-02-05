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
INSERT INTO user_role(name) VALUES('guide');
INSERT INTO user_role(name) VALUES('client');

insert into user(email, password, role_id) values ('pera@mail.com', '$2a$12$vzvp8zB6eTuLxPJMfgTcQOOUDRIaMd6KNoqeOIzLAAPszaQqQnMBS', 1);
insert into agent(email, birthdate, gender, name, surname, phone_number, bonus_per_tour, bonus_per_travelogue, fixed_salary) values ('pera@mail.com', '2000-02-04', 'MALE', 'Pera', 'Peric', '0655827415', 50, 20, 800);

insert into user(email, password, role_id) values ('zika@mail.com', '$2a$12$o6qmDahaUlnUmJBWc6ja1OETELTx.r.lMXsy2RgsbhoOqbbaVLxc6', 2);
insert into guide(email, birthdate, gender, name, surname, phone_number, fixed_salary, percentage_per_tour) values ('zika@mail.com', '1995-06-07', 'MALE', 'Zika', 'Zikic', '0655827415', 600, 100);


insert into file (alt_text, file, description)
VALUES ('paris1',
        LOAD_FILE('/home/tijanajanjic/Documents/PMF/RIS projekat/backend/src/main/resources/images/paris1.png'), 'Paris 1');
insert into file (alt_text, file, description)
VALUES ('paris2',
        LOAD_FILE('/home/tijanajanjic/Documents/PMF/RIS projekat/backend/src/main/resources/images/paris2.png'), 'Paris 2');
insert into file (alt_text, file, description)
VALUES ('amalfy coast',
        LOAD_FILE('/home/tijanajanjic/Documents/PMF/RIS projekat/backend/src/main/resources/images/amalfi-coast2.png'), 'Italy 2');

insert into city_tour (id, city_id, hotel_id, file_id, name)
values (1, 1, 1, 1, 'City tour 1');
insert into city_tour (id, city_id, hotel_id, file_id, name)
values (2, 2, 3, 2, 'City tour 1');

insert into tour (id, name, description, max_passengers, deadline, start_date, end_date, tour_type, transportation_type, price, file_id)
values (1, 'December in France',
        'Thanks to its expertise in the field, France Tourisme offers you a selection of lunch and dinner cruises to discover Paris and its prestigious monuments according to your desires and your budget. You could board for a romantic dinner cruise with musical background, or enjoy an early dinner-cruise to be completed with a cabaret show or a Paris night tour, or you could appreciate a "Prestige" dinner cruise with a gourmet menu, or organize your cruise to celebrate special events among friends or family! It''s up to you!',
        50, '2022-01-11 00:00:00', '2022-01-10 00:00:00','2022-01-15 00:00:00', 'WEEKEND', 'BUS', 200, 1);

insert into tour (id, name, description, max_passengers, deadline, start_date, end_date, tour_type, transportation_type, price, file_id)
   values (2, 'France summer tour',
           'Thanks to its expertise in the field, France Tourisme offers you a selection of lunch and dinner cruises to discover Paris and its prestigious monuments according to your desires and your budget. You could board for a romantic dinner cruise with musical background, or enjoy an early dinner-cruise to be completed with a cabaret show or a Paris night tour, or you could appreciate a "Prestige" dinner cruise with a gourmet menu, or organize your cruise to celebrate special events among friends or family! It''s up to you!',
           50, '2022-01-11 00:00:00', '2022-01-10 00:00:00','2022-01-15 00:00:00', 'WEEKEND', 'BUS', 200,2 );

insert into tour (id, name, description, max_passengers, deadline, start_date, end_date, tour_type, transportation_type, price, file_id)
values (4, 'Italian summer',
        'Thanks to its expertise in the field, France Tourisme offers you a selection of lunch and dinner cruises to discover Paris and its prestigious monuments according to your desires and your budget. You could board for a romantic dinner cruise with musical background, or enjoy an early dinner-cruise to be completed with a cabaret show or a Paris night tour, or you could appreciate a "Prestige" dinner cruise with a gourmet menu, or organize your cruise to celebrate special events among friends or family! It''s up to you!',
        50, '2022-01-11 00:00:00', '2022-01-10 00:00:00','2022-01-15 00:00:00', 'WEEKEND', 'BUS', 200,3 );

insert into tours_city_tours(tour_id, city_tour_id) values (1,1);
insert into tours_city_tours(tour_id, city_tour_id) values (1,2);
insert into tours_city_tours(tour_id, city_tour_id) values (2,2);

insert into city_landmarks (city_id, landmarks_id) VALUES (1,1);
insert into city_landmarks (city_id, landmarks_id) VALUES (1,2);

insert into city_tour_landmarks(city_tour_id, landmarks_id) VALUES (1,1);
insert into city_tour_landmarks(city_tour_id, landmarks_id) VALUES (1,2);

insert into travelogue(text, title, agent_email, city_id, cover_image_id)
value ('wunderbar!', 'Why to visit Paris', 'pera@mail.com', 1,1);
insert into travelogue(text, title, agent_email, city_id, cover_image_id)
value ('bblablabablablblabla!', 'City of light', 'pera@mail.com', 1,2);
insert into travelogue(text, title, agent_email, city_id, cover_image_id)
value ('Paris does not need an introduction. It is one of the most beautiful cities on Earth and occupies the top spot in many a traveller''s lists. Brimming with history, beauty and romance; Paris is home to over 200 museums, 2100 listed historic monuments, 1000 art galleries and 3 opera houses. A simple stroll through its charming streets will help you realise the beauty of Paris - whether its the emphatic Eiffel Tower or the beautiful Jardin des Tuileries gardens, there is something to see in every street in the city. To help you experience the best of Paris. In this guide, we will take a look at the 10 best things do in Paris - whether you''re looking at art museums, historic architecture or royal palaces, you will find them in this list. However, if you want to take a deeper look into the different activities based on any given genre, then click on the markers above to go to that list.', 'City of light', 'pera@mail.com', 1,2);



# drop schema travel_agency;
# create schema travel_agency;
