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

insert into city values (6, 'Athens', 2);
insert into city values (7, 'Santorini', 2);
insert into city values (8, 'Mikonos', 2);

insert into city values (9, 'Florence', 4);
insert into city values (10, 'Rome', 4);
insert into city values (11, 'Milano', 4);
insert into city values (12, 'Venice', 4);

insert into city values (13, 'Madrid', 3);
insert into city values (14, 'Barcelona', 3);
insert into city values (15, 'Valencia', 3);


insert into hotel values (1, 'B&B Hôtel Paris Porte des Lilas', 4, 1);
insert into hotel values (2, 'Campanile Hotel', 3, 1);
insert into hotel values (3, 'Radisson Blu Hotel', 4, 2);

insert into hotel values (4, 'Hotel Trevi', 4, 10);
insert into hotel values (5, 'Eurostars', 4, 10);
insert into hotel values (6, 'Pearl of Caldera - Oia', 3, 11);

insert into hotel values (7, 'Riu Plaza España', 5, 13);
insert into hotel values (8, 'Hotel Barcelona Universal', 4, 14);
insert into hotel values (9, 'Bob W Chueca', 4, 13);

insert into hotel values (10, 'Loft', 5, 6);
insert into hotel values (11, 'The Pinnacle Athens', 3, 7);
insert into hotel values (12, 'Radisson Blu Hotel', 4, 6);

insert into landmark (name, city_id, enterance_price) values ('Arc de Triomphe', 1, 12.0);
insert into landmark (name, city_id, enterance_price) values ('Eiffel Tower', 1, 11.0);
insert into landmark (name, city_id, enterance_price) values ('La Basilique Notre Dame de Fourvière', 2, 11.0);
insert into landmark (name, city_id, enterance_price) values ('Musée des Confluences', 2, 23.0);
insert into landmark (name, city_id, enterance_price) values ('Cours Saleya', 3, 0.0);
insert into landmark (name, city_id, enterance_price) values ('Acropolis', 2, 11.0);
insert into landmark (name, city_id, enterance_price) values ('Olympieion: Temple of Olympian Zeus', 2, 11.0);
insert into landmark (name, city_id, enterance_price) values ('Colosseum', 4, 33.0);
insert into landmark (name, city_id, enterance_price) values ('Roman Forum', 4, 21.0);
insert into landmark (name, city_id, enterance_price) values ('Pantheon', 4, 14.0);
insert into landmark (name, city_id, enterance_price) values ('Ponte Vecchio', 9, 11.0);
insert into landmark (name, city_id, enterance_price) values ('Cathedral of Santa Maria del Fiore and Piazza Duomo', 9, 50.0);
insert into landmark (name, city_id, enterance_price) values ('La Sagrada Família ', 14, 40.0);
insert into landmark (name, city_id, enterance_price) values ('Park Güell', 14, 40.0);
insert into landmark (name, city_id, enterance_price) values ('Mercado de La Boqueria', 14, 40.0);

INSERT INTO role(name) VALUES('agent');
INSERT INTO role(name) VALUES('guide');
INSERT INTO role(name) VALUES('client');

insert into user(email, password, role_id) values ('pera@mail.com', '$2a$12$vzvp8zB6eTuLxPJMfgTcQOOUDRIaMd6KNoqeOIzLAAPszaQqQnMBS', 1);
insert into agent(email, birthdate, gender, name, surname, phone_number, bonus_per_tour, bonus_per_article, fixed_salary) values ('pera@mail.com', '2000-02-04', 'MALE', 'Pera', 'Peric', '0655827415', 50, 20, 800);

insert into user(email, password, role_id) values ('zika@mail.com', '$2a$12$o6qmDahaUlnUmJBWc6ja1OETELTx.r.lMXsy2RgsbhoOqbbaVLxc6', 2);
insert into guide(email, birthdate, gender, name, surname, phone_number, fixed_salary, percentage_per_tour) values ('zika@mail.com', '1995-06-07', 'MALE', 'Zika', 'Zikic', '0655827415', 600, 0.05);

insert into user(email, password, role_id) values ('mika@mail.com', '$2a$12$o6qmDahaUlnUmJBWc6ja1OETELTx.r.lMXsy2RgsbhoOqbbaVLxc6', 3);
insert into client(email, birthdate, gender, name, surname, phone_number) values ('mika@mail.com', '1999-06-07', 'MALE', 'Mika', 'Mikic', '0655827444');

insert into file (alt_text, file, description)
VALUES ('paris1',
        LOAD_FILE('/home/tijanajanjic/Documents/PMF/RIS projekat/backend/src/main/resources/images/paris1.png'), 'Paris 1');
insert into file (alt_text, file, description)
VALUES ('paris2',
        LOAD_FILE('/home/tijanajanjic/Documents/PMF/RIS projekat/backend/src/main/resources/images/paris2.png'), 'Paris 2');
insert into file (alt_text, file, description)
VALUES ('amalfy coast',
        LOAD_FILE('/home/tijanajanjic/Documents/PMF/RIS projekat/backend/src/main/resources/images/amalfi-coast2.png'), 'Italy 2');
insert into file (alt_text, file, description)
VALUES ('rome',
        LOAD_FILE('/home/tijanajanjic/Documents/PMF/RIS projekat/backend/src/main/resources/images/rome.png'), 'Italy 3');

insert into city_tour (id, city_id, hotel_id, file_id, name)
values (1, 1, 1, 1, 'Paris daily route');
insert into city_tour (id, city_id, hotel_id, file_id, name)
values (2, 2, 3, 2, 'Paris economy tour');
insert into city_tour (id, city_id, hotel_id, file_id, name)
values (3, 10, 4, 4, 'Rome daily');

insert into tour (id, name, description, max_passengers, deadline, start_date, end_date, tour_type, transportation_type, price, file_id, agent_email, guide_email)
values (1, 'December in France',
        'Thanks to its expertise in the field, France Tourisme offers you a selection of lunch and dinner cruises to discover Paris and its prestigious monuments according to your desires and your budget. You could board for a romantic dinner cruise with musical background, or enjoy an early dinner-cruise to be completed with a cabaret show or a Paris night tour, or you could appreciate a "Prestige" dinner cruise with a gourmet menu, or organize your cruise to celebrate special events among friends or family! It''s up to you!',
        50, '2022-01-11 00:00:00', '2022-01-10 00:00:00','2022-01-15 00:00:00', 'WEEKEND', 'BUS', 200, 1, 'pera@mail.com', 'zika@mail.com');

insert into tour (id, name, description, max_passengers, deadline, start_date, end_date, tour_type, transportation_type, price, file_id, agent_email, guide_email)
   values (2, 'France summer tour',
           'France, the largest country in Western Europe, has long been a gateway between the continent''s northern and southern regions. Its lengthy borders touch Germany and Belgium in the north; the Atlantic Ocean in the west; the Pyrenees Mountains and Spain in the south.',
           50, '2022-04-11 00:00:00', '2022-05-10 00:00:00','2022-04-15 00:00:00', 'WEEKEND', 'BUS', 200,2 , 'pera@mail.com', 'zika@mail.com');

insert into tour (id, name, description, max_passengers, deadline, start_date, end_date, tour_type, transportation_type, price, file_id, agent_email, guide_email)
values ( 3,'Italian summer',
         'Italy, country of south-central Europe, occupying a peninsula that juts deep into the Mediterranean Sea. Italy comprises some of the most varied and scenic landscapes on Earth and is often described as a country shaped like a boot. At its broad top stand the Alps, which are among the world''s most rugged mountains.',
         50, '2022-11-01 00:00:00', '2022-11-10 00:00:00','2022-11-15 00:00:00', 'WEEKEND', 'BUS', 200,3 , 'pera@mail.com', 'zika@mail.com');



insert into tours_city_tours(tour_id, city_tour_id) values (1,1);
insert into tours_city_tours(tour_id, city_tour_id) values (1,2);
insert into tours_city_tours(tour_id, city_tour_id) values (2,2);
insert into tours_city_tours(tour_id, city_tour_id) values (3,3);

insert into landmark_city_tour(city_tour_id, landmark_id) VALUES (1,1);
insert into landmark_city_tour(city_tour_id, landmark_id) VALUES (1,2);

insert into landmark_city_tour(city_tour_id, landmark_id) VALUES (2,3);
insert into landmark_city_tour(city_tour_id, landmark_id) VALUES (2,4);

insert into landmark_city_tour(city_tour_id, landmark_id) VALUES (3,8);
insert into landmark_city_tour(city_tour_id, landmark_id) VALUES (3,9);
insert into landmark_city_tour(city_tour_id, landmark_id) VALUES (3,10);



insert into article(text, title, agent_email, city_id, cover_image_id)
value (' 1. It''s the world''s most romantic city

Stanley Stewart explains: "Chaotic passions flutter beneath its fine sensible civilised face. Its reputation as the city of love is so pervasive that I once met nomads in Outer Mongolia discussing fantasy dates in Paris. Despite their geography being a bit hazy - they seemed to feel Paris was a day’s ride from Moscow on a decent horse - they talked of the river, the bridges, the spires of Notre Dame."
Why everyone should visit Paris in autumnEvery street in Paris seems to echo with the ghosts of love stories  Photo: AP/FOTOLIA
May we suggest a stroll through the gardens of the Palace of Fontainebleau, just outside the city, a sundowner (and the views) in Belleville, the hilltop district where Edith Piaf was born, a show at the Palais Garnier, and dinner at La Fermette Marboeu, where the Art Nouveau decor is as exquisite as the Grand Marnier soufflé? Then recreate Robert Doisneau''s Le Baiser de l''Hôtel de Ville (Kiss by the Town Hall).
Paris by Robert Doisneau: photographs of the city in the 1940s and 50s Robert Doisneau''s famous photograph  Photo: GETTY

2. Getting there is a doddle
Just 2 hours 15 mins on the Eurostar, with return fares from £64.

3. It''s got 70 Michelin-starred restaurants
Including 10 with three stars.

4. And you''ll learn about the true hierarchy in the relationship between waiter and diner
"It is an ignoble lie that Parisian waiters are rude," explains Anthony Peregrine. "They aren’t. They are simply professional men (generally men) in a hurry. Consider your average London waiter or barman who, unless he actually is French, certainly doesn’t speak French and will give short shrift to anyone who cannot speak English.
"Anyway, he doesn’t consider waiting his real job. It’s just something he does until he goes back to college or gets a role in a reality TV show. He is, therefore, clueless. French waiters are, by contrast, men doing a serious job. It’s a career.
"The fellow may take an order for 14 different drinks in English, dodge between traffic, give directions to a Japanese passer-by on how to get to the Eiffel Tower – and still be a damned sight more agreeable than any waiter I have recently encountered in any British airport (they are legion) in recent times."
• Why France is better than Britain
33 reasons you must keep visiting ParisFrench waiters: "professional men in a hurry"  Photo: AP/FOTOLIA

5. It''s got the world''s greatest museum
Paris: How to visit the LouvreThe Louvre  Photo: GETTY
But please, no #monalisaselfies

6. And dozens of brilliant ones you''ve never heard of
"Alongside the world-famous museums and monuments, there are also some gloriously eccentric ones," says Natasha Edwards, our Paris expert. "My favourite is the Musée Gustave Moreau, his former home and studio piled high with his works and an insight into a city that has long been an inspiration for artists."
• Secret Paris: lesser-known attractions, bars and restaurants

7. There''s the most beautiful stained glass you''ll probably ever see
Inside the Sainte Chapelle, on the Ile de la Cité, you''ll find the most awe-inspiring 13th-century stained glass in the world. The place exults with the sort of light which heralds angels. Fifteen huge and exquisite windows constitute the side and end of the chapel, held in place – only God knows how – by the frailest possible stone-work.
Whoever designed it had mastered the sublime. The scriptural stories in the 1,113 panels – recently restored to glory – are impossible to follow. It doesn’t matter. When the sunlight pours through, the effect is of being bathed in grace and serenity. !',
       'Why to visit Paris', 'pera@mail.com', 1,1);

insert into article(text, title, agent_email, city_id, cover_image_id)
value ('Paris does not need an introduction. It is one of the most beautiful cities on Earth and occupies the top spot in many a traveller''s lists. Brimming with history, beauty and romance; Paris is home to over 200 museums, 2100 listed historic monuments, 1000 art galleries and 3 opera houses. A simple stroll through its charming streets will help you realise the beauty of Paris - whether its the emphatic Eiffel Tower or the beautiful Jardin des Tuileries gardens, there is something to see in every street in the city. To help you experience the best of Paris. In this guide, we will take a look at the 10 best things do in Paris - whether you''re looking at art museums, historic architecture or royal palaces, you will find them in this list. However, if you want to take a deeper look into the different activities based on any given genre, then click on the markers above to go to that list.',
       'City of light', 'pera@mail.com', 1,2);

# permission  agent guide client 1 2 3

insert into permission (id, name) values (1, 'GET/travel');
insert into permission (id, name) values (2, 'PUT/auth');
insert into permission (id, name) values (3, 'PUT/travel');
insert into permission (id, name) values (4, 'POST/auth');
insert into permission (id, name) values (5, 'POST/travel');
insert into permission (id, name) values (6, 'DELETE/travel');

insert into role_and_permission (role_id, permission_id) VALUES (1, 1);
insert into role_and_permission (role_id, permission_id) VALUES (1, 2);
insert into role_and_permission (role_id, permission_id) VALUES (1, 3);
insert into role_and_permission (role_id, permission_id) VALUES (1, 4);
insert into role_and_permission (role_id, permission_id) VALUES (1, 5);
insert into role_and_permission (role_id, permission_id) VALUES (1, 6);
insert into role_and_permission (role_id, permission_id) VALUES (2, 1);
insert into role_and_permission (role_id, permission_id) VALUES (3, 1);

#
# drop schema travel_agency;
# create schema travel_agency;
# use travel_agency
