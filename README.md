# Технологии
- Java 17
- Spring Boot
- PostgreSQL
- Docker

# Запуск приложения через Docker
1. Установите Docker и Docker Compose на своем компьютере.
2. Склонируйте репозиторий с приложением.
3. Перейдите в корневую директорию репозитория.
4. Выполните команду `docker-compose up`.
5. После успешного запуска, приложение будет доступно по адресу http://localhost:8081.


Регистрируемся

![image](https://github.com/Dzmitry-yarik/Goods_storeJWT/assets/107866389/c91239f7-3560-490d-b14b-74dabcb04575)

Авторизируемся

![image](https://github.com/Dzmitry-yarik/Goods_storeJWT/assets/107866389/9c21405d-518f-4052-9caf-59be9d107e13)

и можем пользоваться.
Если ваша роль USER, то к запросам начинаюшимся с /admin/** доступа не будет

![image](https://github.com/Dzmitry-yarik/Goods_storeJWT/assets/107866389/2404245e-79a6-46ab-9483-6c380eaf08b0)

Однако, если роль ADMIN, то вы можете выполнять все запросы и удалять пользователей

![image](https://github.com/Dzmitry-yarik/Goods_storeJWT/assets/107866389/e7c0f9e2-3806-41bb-a23e-86190ccac776)

![image](https://github.com/Dzmitry-yarik/Goods_storeJWT/assets/107866389/38087b78-2607-4fd5-bd3b-432573076918)

![image](https://github.com/Dzmitry-yarik/Goods_storeJWT/assets/107866389/c18a4e95-2fca-4e68-86d2-9568233a5901)

![image](https://github.com/Dzmitry-yarik/Goods_storeJWT/assets/107866389/65b2c828-5ba1-49f4-a2c1-0f910b2a9403)


Если понадобится заполнять таблицы:
CREATE TABLE Users (
    userId SERIAL PRIMARY KEY,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    password VARCHAR(150) DEFAULT '{noop}1111',
    email VARCHAR(50) NOT NULL,
    role VARCHAR(20) 
);

CREATE TABLE unitid (
    unit_id SERIAL PRIMARY KEY,
    unit_name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE pack (
    pack_id SERIAL PRIMARY KEY,
    unit_id INTEGER NOT NULL,
    pack_name VARCHAR(50) NOT NULL,
    pack_quant numeric(10, 3) NOT NULL,
    pack_type numeric(10, 2) NOT NULL,
    UNIQUE (pack_name),
    FOREIGN KEY (unit_id) REFERENCES unitid(unit_id)
);

CREATE TABLE packprc (
    packprc_id SERIAL PRIMARY KEY,
    pack_price numeric(10, 2) NOT NULL,
    pack_bonus numeric(10, 2) NOT NULL,
    FOREIGN KEY (packprc_id) REFERENCES pack(pack_id)
);

CREATE TABLE exbarc (
    exbarc_id SERIAL PRIMARY KEY,
    pack_id INTEGER NOT NULL,
    exbar_body VARCHAR(30) NOT NULL,
    UNIQUE (exbar_body),
    FOREIGN KEY (pack_id) REFERENCES pack(pack_id)
);

CREATE TABLE sales (
    sales_id SERIAL PRIMARY KEY,
    sales_time TIMESTAMP NOT NULL,
    sales_tag smallint NOT NULL,
    exbar_body VARCHAR(30),
    pack_name VARCHAR(50),
    unit_name VARCHAR(50),
    price numeric(10, 2) NOT NULL,
    bonus_price numeric(10, 2),
    quantity numeric(10, 3),
    FOREIGN KEY (exbar_body) REFERENCES exbarc(exbar_body),
    FOREIGN KEY (pack_name) REFERENCES pack(pack_name),
    FOREIGN KEY (unit_name) REFERENCES unitid(unit_name)
);


CREATE TABLE assortment (
    assort_id SERIAL PRIMARY KEY,
    exbar_body VARCHAR(25) NOT NULL,
    pack_name VARCHAR(55) NOT NULL,
    pack_quant numeric(10, 2)  NOT NULL,
    pack_type BOOLEAN NOT NULL,
    pack_price numeric(10, 2) NOT NULL,
    pack_bonus numeric(10, 2) NOT NULL,
    unit_name VARCHAR(25) NOT NULL,
    FOREIGN KEY (exbar_body) REFERENCES exbarc(exbar_body),
    FOREIGN KEY (pack_name) REFERENCES pack(pack_name),
    FOREIGN KEY (unit_name) REFERENCES unitid(unit_name)
);


INSERT INTO users (firstname, lastname, email, role)
VALUES
('jon', 'jon', 'jon@mail.ru', 'ADMIN'),
('alex', 'alex', 'alex@mail.ru', 'USER');

-- Заполнение таблицы unit_id
INSERT INTO unitid (unit_id, unit_name) VALUES
(1,'шт'),
(2,'кг'),
(3,'бут');

-- Заполнение таблицы pack
INSERT INTO pack (pack_id, unit_id, pack_name, pack_quant, pack_type) VALUES
(1, 1,'Мороженное каштан', 1, 0),
(2, 2,'Картофель', 2546, 0),
(3, 3,'Bonaqua', 2, 0);

-- Заполнение таблицы pack_prc
INSERT INTO packprc (packprc_id, pack_price, pack_bonus) VALUES
(1, 254, 0),
(2, 548, 135),
(3, 358, 33);

-- Заполнение таблицы exbarc
INSERT INTO exbarc (exbarc_id, pack_id, exbar_body) VALUES
(1, 1, '62134'),
(2, 2, '312114'),
(3, 3, '23467');

-- Заполнение таблицы sales
INSERT INTO sales (sales_time, sales_tag, exbar_body, pack_name, unit_name, price, bonus_price, quantity) VALUES
('2024-02-26 20:03:32', 3, NULL, NULL, NULL, 1160, 168, NULL),
('2024-02-26 20:03:32', 2, NULL, NULL, NULL, 1000, NULL, NULL),
('2024-02-26 20:03:32', 1, NULL, NULL, NULL, 500, NULL, NULL),
('2024-02-26 20:03:32', 0, '62134', 'Мороженное каштан', 'шт', 254, 0, 1),
('2024-02-26 20:03:32', 0, '312114', 'Картофель', 'кг', 548, 135, 2546),
('2024-02-26 20:03:32', 0, '23467', 'Bonaqua', 'бут', 358, 33, 2);

-- Заполнение таблицы assortment
INSERT INTO assortment (exbar_body, pack_name, pack_quant, pack_type, pack_price, pack_bonus, unit_name) VALUES
('62134', 'Мороженное каштан', 1, FALSE, 254, 0, 'шт'),
('312114', 'Картофель', 2546, FALSE, 548, 135, 'кг'),
('23467', 'Bonaqua', 2, FALSE, 358, 33, 'бут');
