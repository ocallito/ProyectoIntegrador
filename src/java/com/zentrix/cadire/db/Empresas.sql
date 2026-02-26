DROP DATABASE IF EXISTS reciclaje_leon;
CREATE DATABASE reciclaje_leon;
USE reciclaje_leon;


CREATE TABLE empresa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    direccion VARCHAR(200) NOT NULL,
    telefono VARCHAR(20),
    material VARCHAR(100) NOT NULL,
    precio_kg DECIMAL(10,2),
    ciudad VARCHAR(100) DEFAULT 'León'
);
ALTER TABLE empresa 
ADD lat DECIMAL(10,6),
ADD lng DECIMAL(10,6);

CREATE TABLE usuario(
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    contrasenia VARCHAR(100) NOT NULL,
    rol VARCHAR(30) NOT NULL
);

INSERT INTO usuario(nombre, contrasenia, rol)
VALUES('oscar', '1234', 'ADMIN'),
	  ('abril', '1234', 'ADMIN'),
      ('alex', '1234', 'ADMIN');
INSERT INTO empresa (nombre, direccion, telefono, material, precio_kg) VALUES
('Recicladora León', 
 'Av. Ciudad Asís 1319, Fracciones de Sangre de Cristo, León, Gto', 
 '4778743730', 
 'Plástico', 
 4.50),

('Recicladora León', 
 'Av. Ciudad Asís 1319, Fracciones de Sangre de Cristo, León, Gto', 
 '4778743730', 
 'Cartón', 
 2.80),

('Recicladora Briquel', 
 'Blvd. Miguel Hidalgo 7005, Los Castillos, León, Gto', 
 '4777908603', 
 'Metales', 
 6.00),

('Recicladora Briquel', 
 'Blvd. Miguel Hidalgo 7005, Los Castillos, León, Gto', 
 '4777908603', 
 'Plástico', 
 4.20),

('Recicladora El Carmen', 
 'Blvd. Vicente Valtierra 1118, El Carmen, León, Gto', 
 '4771234567', 
 'Plástico', 
 4.20),

('Recicladora El Carmen', 
 'Blvd. Vicente Valtierra 1118, El Carmen, León, Gto', 
 '4771234567', 
 'Cartón', 
 2.50),

('Eco ABC Recycling', 
 'Antiguo Camino León–San Juan #330, Col. El Laurel, León, Gto', 
 '4771450765', 
 'Vidrio', 
 1.50),

('Eco ABC Recycling', 
 'Antiguo Camino León–San Juan #330, Col. El Laurel, León, Gto', 
 '4771450765', 
 'Metales', 
 7.50),

('Eco ABC Recycling', 
 'Antiguo Camino León–San Juan #330, Col. El Laurel, León, Gto', 
 '4771450765', 
 'Plástico', 
 4.30),

('PAB Recicladora', 
 'Sarmiento, San José el Alto, León, Gto', 
 '4772927000', 
 'Plástico', 
 4.80),

('PAB Recicladora', 
 'Sarmiento, San José el Alto, León, Gto', 
 '4772927000', 
 'Cartón', 
 2.70),

('REINBA', 
 'Blvd. Jose María Morelos 4412-A, Industrial Julián de Obregón, León, Gto', 
 '4777135153', 
 'Cartón', 
 3.00),

('REINBA', 
 'Blvd. Jose María Morelos 4412-A, Industrial Julián de Obregón, León, Gto', 
 '4777135153', 
 'Plástico', 
 4.70),

('Vidrio Recuperable', 
 'Av. Transportistas 420-C, El Palmar, León, Gto', 
 '4776362020', 
 'Vidrio', 
 1.80),

('Ecofibras Ponderosa', 
 'Blvd. Timoteo Lozano 628, San Miguel, León, Gto', 
 '4772225555', 
 'Cartón', 
 2.90),

('Ecofibras Ponderosa', 
 'Blvd. Timoteo Lozano 628, San Miguel, León, Gto', 
 '4772225555', 
 'Fibra/Textiles', 
 4.10),

('Centro de Acopio Los Cárcamos', 
 'Blvd. Adolfo López Mateos #3402, Hacienda del Campestre, León, Gto', 
 '4777172790', 
 'Plástico', 
 4.40),

('Centro de Acopio Los Cárcamos', 
 'Blvd. Adolfo López Mateos #3402, Hacienda del Campestre, León, Gto', 
 '4777172790', 
 'Cartón', 
 2.80),

('Neoplasticos León', 
 'Blvd. Fidel Velázquez 1234, León, Gto', 
 '4771980018', 
 'Plástico', 
 4.60),

('Neoplasticos León', 
 'Blvd. Fidel Velázquez 1234, León, Gto', 
 '4771980018', 
 'Cartón', 
 3.20);
SELECT nombre, direccion, telefono, material, precio_kg
FROM empresa
WHERE material = 'Plástico'
ORDER BY precio_kg DESC;
UPDATE empresa SET lat=21.124530, lng=-101.686920 
WHERE nombre='Recicladora León';

UPDATE empresa SET lat=21.150210, lng=-101.655820 
WHERE nombre='Recicladora Briquel';

UPDATE empresa SET lat=21.112980, lng=-101.667540 
WHERE nombre='Recicladora El Carmen';

UPDATE empresa SET lat=21.135840, lng=-101.703410 
WHERE nombre='Eco ABC Recycling';

UPDATE empresa SET lat=21.170140, lng=-101.651990 
WHERE nombre='PAB Recicladora';

UPDATE empresa SET lat=21.160520, lng=-101.675310 
WHERE nombre='REINBA';

UPDATE empresa SET lat=21.145920, lng=-101.694210 
WHERE nombre='Vidrio Recuperable';

UPDATE empresa SET lat=21.119820, lng=-101.690420 
WHERE nombre='Ecofibras Ponderosa';

UPDATE empresa SET lat=21.152640, lng=-101.699830 
WHERE nombre='Centro de Acopio Los Cárcamos';

UPDATE empresa SET lat=21.148410, lng=-101.670120 
WHERE nombre='Neoplasticos León';
DESCRIBE empresa;

SHOW TABLES;
SELECT * FROM empresa;