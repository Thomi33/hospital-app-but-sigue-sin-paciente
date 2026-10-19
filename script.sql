DROP DATABASE IF EXISTS hospital;

CREATE DATABASE hospital
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE hospital;

CREATE TABLE perfil(

id_perfil INT AUTO_INCREMENT PRIMARY KEY,

nombre VARCHAR(50) NOT NULL UNIQUE,

descripcion VARCHAR(150),

activo BOOLEAN DEFAULT TRUE

);

CREATE TABLE usuario(

id_usuario INT AUTO_INCREMENT PRIMARY KEY,

nombre VARCHAR(80) NOT NULL,

apellido VARCHAR(80) NOT NULL,

cedula VARCHAR(20) UNIQUE,

telefono VARCHAR(30),

email VARCHAR(120),

usuario VARCHAR(40) UNIQUE NOT NULL,

password VARCHAR(255) NOT NULL,

id_perfil INT NOT NULL,

activo BOOLEAN DEFAULT TRUE,

CONSTRAINT fk_usuario_perfil
FOREIGN KEY(id_perfil)
REFERENCES perfil(id_perfil)

);

CREATE TABLE funcionalidad(

id_funcionalidad INT AUTO_INCREMENT PRIMARY KEY,

nombre VARCHAR(80) NOT NULL,

descripcion VARCHAR(200)

);

CREATE TABLE perfil_funcionalidad(

id_perfil INT,

id_funcionalidad INT,

PRIMARY KEY(id_perfil,id_funcionalidad),

FOREIGN KEY(id_perfil)
REFERENCES perfil(id_perfil),

FOREIGN KEY(id_funcionalidad)
REFERENCES funcionalidad(id_funcionalidad)

);

CREATE TABLE pais(

id_pais INT AUTO_INCREMENT PRIMARY KEY,

nombre VARCHAR(80) UNIQUE NOT NULL

);

CREATE TABLE institucion(

id_institucion INT AUTO_INCREMENT PRIMARY KEY,

nombre VARCHAR(150),

direccion VARCHAR(200),

telefono VARCHAR(30),

id_pais INT NOT NULL,

activo BOOLEAN DEFAULT TRUE,

FOREIGN KEY(id_pais)
REFERENCES pais(id_pais)

);

CREATE TABLE ubicacion(

id_ubicacion INT AUTO_INCREMENT PRIMARY KEY,

nombre VARCHAR(100),

descripcion VARCHAR(200),

id_institucion INT,

FOREIGN KEY(id_institucion)
REFERENCES institucion(id_institucion)

);

CREATE TABLE marca(

id_marca INT AUTO_INCREMENT PRIMARY KEY,

nombre VARCHAR(80) UNIQUE NOT NULL,

activo BOOLEAN DEFAULT TRUE

);

CREATE TABLE modelo(

id_modelo INT AUTO_INCREMENT PRIMARY KEY,

nombre VARCHAR(100),

id_marca INT,

activo BOOLEAN DEFAULT TRUE,

FOREIGN KEY(id_marca)
REFERENCES marca(id_marca)

);

CREATE TABLE tipo_equipo(

id_tipo INT AUTO_INCREMENT PRIMARY KEY,

nombre VARCHAR(80),

descripcion VARCHAR(150),

activo BOOLEAN DEFAULT TRUE

);
CREATE TABLE proveedor(

id_proveedor INT AUTO_INCREMENT PRIMARY KEY,

nombre VARCHAR(150),

direccion VARCHAR(200),

telefono VARCHAR(30),

email VARCHAR(100),

activo BOOLEAN DEFAULT TRUE

);

CREATE TABLE equipo(

id_equipo INT AUTO_INCREMENT PRIMARY KEY,

codigo VARCHAR(30) UNIQUE,

nombre VARCHAR(120),

serie VARCHAR(120),

fecha_compra DATE,

fecha_garantia DATE,

estado ENUM('ACTIVO','INACTIVO','MANTENIMIENTO'),

observaciones TEXT,

id_modelo INT,

id_tipo INT,

id_proveedor INT,

id_ubicacion INT,

FOREIGN KEY(id_modelo)
REFERENCES modelo(id_modelo),

FOREIGN KEY(id_tipo)
REFERENCES tipo_equipo(id_tipo),

FOREIGN KEY(id_proveedor)
REFERENCES proveedor(id_proveedor),

FOREIGN KEY(id_ubicacion)
REFERENCES ubicacion(id_ubicacion)

);

CREATE TABLE tipo_intervencion(

id_tipo_intervencion INT AUTO_INCREMENT PRIMARY KEY,

nombre VARCHAR(80),

descripcion VARCHAR(200)

);

CREATE TABLE intervencion(

id_intervencion INT AUTO_INCREMENT PRIMARY KEY,

fecha DATE,

descripcion TEXT,

costo DECIMAL(10,2),

id_equipo INT,

id_tipo_intervencion INT,

id_usuario INT,

FOREIGN KEY(id_equipo)
REFERENCES equipo(id_equipo),

FOREIGN KEY(id_tipo_intervencion)
REFERENCES tipo_intervencion(id_tipo_intervencion),

FOREIGN KEY(id_usuario)
REFERENCES usuario(id_usuario)

);

CREATE TABLE auditoria(

id_auditoria INT AUTO_INCREMENT PRIMARY KEY,

fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

tabla_afectada VARCHAR(80),

operacion VARCHAR(30),

descripcion TEXT,

id_usuario INT,

FOREIGN KEY(id_usuario)
REFERENCES usuario(id_usuario)

);