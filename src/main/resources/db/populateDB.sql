DELETE FROM votes;
DELETE FROM acts;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;


INSERT INTO USERS(nick,login,password)
VALUES ('user1','ivan','0ef78b48343037bbdcf70abee62238b4'),
       ('user2','petr','cae49840b6785f4074cb41dd21c47e22'),
       ('user3','sidor','fa6c34cebbd140b3cb53acb85856c7eb'),
       ('user4','fedor','2fa3c839cdfd57deaadc4f0f904813ba'),
       ('user5','evgen','997b9c7fbfbd6f7bf6899cfa75f5ff2e');


INSERT INTO ACTS(sin,acted,description,user_id)
VALUES (true,'2021-09-11','Смотрел порно',100000),
       (false,'2021-09-12','Сходил в храм на литургию',100001),
       (true,'2021-09-13','Впал в блуд',100002),
       (false,'2021-09-14','Подал милостыню нищему',100003),
       (true,'2021-09-15','Ругался матом',100004);
