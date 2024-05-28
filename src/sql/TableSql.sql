
DROP DATABASE IF EXISTS restoration_management;
CREATE DATABASE restoration_management;


\c restoration_management;

-- Table pour stocker les informations des restaurants
CREATE TABLE restaurant (
    id SERIAL PRIMARY KEY,
    location VARCHAR(255) NOT NULL
);

-- Table pour stocker les informations des menus
CREATE TABLE menu (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    currentPrice DECIMAL(10, 2) NOT NULL
);

-- Table pour stocker les informations des ingrédients
CREATE TABLE unit (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);
CREATE TABLE ingredient (
    id SERIAL PRIMARY KEY,
    unit_id INt,
    name VARCHAR(255) NOT NULL,
    costPrice DECIMAL(10, 2) NOT NULL,
    quantity DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (unit_id) REFERENCES unit(id)
);
-- Table pour définir la composition de chaque menu en termes d'ingrédients
CREATE TABLE menuIngredient (
    menu_id INT,
    ingredient_id INT,
    quantity DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (menu_id, ingredient_id),
    FOREIGN KEY (menu_id) REFERENCES menu(id),
    FOREIGN KEY (ingredient_id) REFERENCES ingredient(id)
);

-- Table pour gérer les stocks d'ingrédients dans chaque restaurant
CREATE TABLE stock (
    id SERIAL PRIMARY KEY,
    restaurant_id INT,
    ingredient_id INT,
    movement_type VARCHAR(255) NOT NULL,
    quantity DECIMAL(10, 2) NOT NULL,
    movement_date DATE NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant(id),
    FOREIGN KEY (ingredient_id) REFERENCES ingredient(id)
);


-- Table pour enregistrer les ventes réalisées
CREATE TABLE sale (
    id SERIAL PRIMARY KEY,
    restaurant_id INT,
    menu_id INT,
    saleDate DATE NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant(id),
    FOREIGN KEY (menu_id) REFERENCES menu(id)
);




INSERT INTO restaurant (location) VALUES 
('TWF'),
('Akoor');

------------------------------------------

INSERT INTO unit (name) VALUES 
('Pièce'),
('KG'),
('Litre');

--------------------------------------
INSERT INTO ingredient (unit_id, name, costPrice, quantity) VALUES
(1, 'Pain', 500, 1000),
(2, 'Saucisse', 20000, 100),
(3, 'Mayonnaise', 10000, 50),
(3, 'Ketchup', 5000, 50);


---------------------------------
INSERT INTO menu (name, currentPrice) VALUES 
('Hot dog', 5000),
('Burger', 7000);

--------------------------------------------------
INSERT INTO menuIngredient (menu_id, ingredient_id, quantity) VALUES 
(1, 1, 1),     -- Hot dog needs 1 piece of bread
(1, 2, 0.125), -- Hot dog needs 0.125 KG of sausage
(1, 3, 0.05),  -- Hot dog needs 0.05 L of mayonnaise
(1, 4, 0.05);  -- Hot dog needs 0.05 L of ketchup

---------------------------------
INSERT INTO stock (restaurant_id, ingredient_id, movement_type, quantity, movement_date) VALUES 
(1, 1, 'Entrée', 500, '2024-05-01'),
(1, 3, 'Entrée', 10, '2024-05-01'),
(1, 2, 'Entrée', 50, '2024-05-02'),
(1, 2, 'Sortie', 10, '2024-05-02');

---------------------------------------------------------
INSERT INTO sale (restaurant_id, menu_id, saleDate, quantity, price) VALUES
 (1, 1, '2024-05-02', 10, 50000), -- 10 Hot dogs sold at TWF
 (2, 1, '2024-05-02', 20, 100000); -- 20 Hot dogs sold at Akoor


SELECT
    s.movement_date,
    i.name AS ingredient,
    s.movement_type,
    s.quantity,
    u.name AS unit
FROM
    stock AS s
        INNER JOIN ingredient AS i ON s.ingredient_id = i.id
        INNER JOIN unit AS u ON i.unit_id = u.id
WHERE
    s.movement_date >= '2024-05-01 08:00:00' AND s.movement_date <= '2024-05-02 10:10:00'
ORDER BY
    s.movement_date;




SELECT
    i.id AS ingredient_id,
    i.name AS ingredient_name,
    m.name AS menu_name,
    SUM(mi.quantity * s.quantity) AS total_quantity_used,
    u.name AS unit_name
FROM
    sale AS s
        INNER JOIN menu AS m ON s.menu_id = m.id
        INNER JOIN menuIngredient AS mi ON m.id = mi.menu_id
        INNER JOIN ingredient AS i ON mi.ingredient_id = i.id
        INNER JOIN unit AS u ON i.unit_id = u.id
WHERE
    s.saleDate >= '2024-05-01 08:00' AND s.saleDate <= '2024-05-01 10:10'
  AND s.restaurant_id = 1
GROUP BY
    i.id, i.name, m.name, u.name
ORDER BY
    total_quantity_used DESC
    LIMIT 3;



SELECT
    r.id AS restaurant_id,
    r.location AS restaurant_location,
    COALESCE(SUM(CASE WHEN m.id = 1 THEN s.quantity ELSE 0 END), 0) AS menu_1_quantity,
    COALESCE(SUM(CASE WHEN m.id = 2 THEN s.quantity ELSE 0 END), 0) AS menu_2_quantity,
    COALESCE(SUM(CASE WHEN m.id = 3 THEN s.quantity ELSE 0 END), 0) AS menu_3_quantity,
    COALESCE(SUM(CASE WHEN m.id = 1 THEN s.price * s.quantity ELSE 0 END), 0) AS menu_1_revenue,
    COALESCE(SUM(CASE WHEN m.id = 2 THEN s.price * s.quantity ELSE 0 END), 0) AS menu_2_revenue,
    COALESCE(SUM(CASE WHEN m.id = 3 THEN s.price * s.quantity ELSE 0 END), 0) AS menu_3_revenue
FROM
    restaurant AS r
        LEFT JOIN sale AS s ON r.id = s.restaurant_id
        LEFT JOIN menu AS m ON s.menu_id = m.id
WHERE
    s.saleDate >= '2024-05-01 08:00' AND s.saleDate <= '2024-05-01 10:10'
GROUP BY
    r.id, r.location
ORDER BY
    r.id;
