INSERT INTO author (id, firstname, lastname, version) VALUES (1, 'Joshua', 'Bloch', 0);
INSERT INTO author (id, firstname, lastname, version) VALUES (2, 'Gavin', 'King', 0);
INSERT INTO author (id, firstname, lastname, version) VALUES (3, 'Christian', 'Bauer', 0);
INSERT INTO author (id, firstname, lastname, version) VALUES (4, 'Gary', 'Gregory', 0);
INSERT INTO author (id, firstname, lastname, version) VALUES (5, 'Raoul-Gabriel', 'Urma', 0);
INSERT INTO author (id, firstname, lastname, version) VALUES (6, 'Mario', 'Fusco', 0);
INSERT INTO author (id, firstname, lastname, version) VALUES (7, 'Alan', 'Mycroft', 0);
INSERT INTO author (id, firstname, lastname, version) VALUES (8, 'Andrew Lee', 'Rubinger', 0);
INSERT INTO author (id, firstname, lastname, version) VALUES (9, 'Aslak', 'Knutsen', 0);
INSERT INTO author (id, firstname, lastname, version) VALUES (10, 'Bill', 'Burke', 0);
INSERT INTO author (id, firstname, lastname, version) VALUES (11, 'Scott', 'Oaks', 0);

INSERT INTO book (id, publishingdate, title, version,fk_author) VALUES (1, '2008-05-08', 'Effective Java', 0,1);
INSERT INTO book (id, publishingdate, title, version,fk_author) VALUES (2, '2015-10-01', 'Java Persistence with Hibernate', 0,1);
INSERT INTO book (id, publishingdate, title, version) VALUES (3, '2014-08-28', 'Java 8 in Action', 0);
INSERT INTO book (id, publishingdate, title, version) VALUES (4, '2014-03-12', 'Continuous Enterprise Development in Java', 0);
INSERT INTO book (id, publishingdate, title, version) VALUES (5, '2010-09-08', 'Enterprise JavaBeans 3.1', 0);
INSERT INTO book (id, publishingdate, title, version) VALUES (6, '2014-04-29', 'Java Performance The Definitive Guide', 0);