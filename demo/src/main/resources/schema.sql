CREATE TABLE IF NOT EXISTS estudiante (
    id INT PRIMARY KEY,
    nombre VARCHAR(200) NOT NULL,
    apellido VARCHAR(200) NOT NULL,
    codigo VARCHAR(10) UNIQUE,
    fecha_nacimiento DATE NOT NULL
);

create table if not exists tipo_producto(
    id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre varchar(200) not null,
    fechaCreacion date not null
);

CREATE TABLE IF NOT EXISTS producto (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre VARCHAR(200) NOT NULL,
    fecha_creacion DATE NOT NULL,
    id_tipo_producto INT NOT NULL,
    CONSTRAINT fk_tipo_producto FOREIGN KEY (id_tipo_producto) 
    REFERENCES tipo_producto(id) ON DELETE SET NULL
);


-- ================================================
-- 🧩 TABLA: USUARIOS
-- ================================================
CREATE TABLE IF NOT EXISTS usuarios (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    nombre_completo VARCHAR(200) NOT NULL,
    rol VARCHAR(50) NOT NULL,
    activo BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ================================================
-- 🧩 TABLA: CATEGORIAS
-- ================================================
CREATE TABLE IF NOT EXISTS categorias (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre VARCHAR(200) NOT NULL,
    activo BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ================================================
-- 🧩 TABLA: PRODUCTO_CATALOGO
-- ================================================
CREATE TABLE IF NOT EXISTS producto_catalogo (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    codigo VARCHAR(50) NOT NULL UNIQUE,
    nombre VARCHAR(200) NOT NULL,
    id_categoria INT NOT NULL,
    activo BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_categoria FOREIGN KEY (id_categoria)
        REFERENCES categorias(id) ON DELETE CASCADE
);

-- ================================================
-- 🧩 TABLA: PRODUCTO_PARQUE
-- ================================================
CREATE TABLE IF NOT EXISTS producto_parque (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_producto_catalogo INT NOT NULL,
    existencias INT DEFAULT 0,
    activo BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_producto_catalogo FOREIGN KEY (id_producto_catalogo)
        REFERENCES producto_catalogo(id) ON DELETE CASCADE
);

-- ================================================
-- 🧩 TABLA: DOCUMENTOS
-- ================================================
CREATE TABLE IF NOT EXISTS documentos (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    tipo_movimiento VARCHAR(50) NOT NULL,
    numero_documento VARCHAR(100) NOT NULL UNIQUE,
    fecha DATE NOT NULL,
    id_usuario INT NOT NULL,
    observacion TEXT,
    activo BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_usuario FOREIGN KEY (id_usuario)
        REFERENCES usuarios(id) ON DELETE CASCADE
);

-- ================================================
-- 🧩 TABLA: DOCUMENTO_DETALLES
-- ================================================
CREATE TABLE IF NOT EXISTS documento_detalles (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_documento INT NOT NULL,
    id_producto_catalogo INT NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10,2) NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL,
    activo BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_documento FOREIGN KEY (id_documento)
        REFERENCES documentos(id) ON DELETE CASCADE,
    CONSTRAINT fk_detalle_producto FOREIGN KEY (id_producto_catalogo)
        REFERENCES producto_catalogo(id) ON DELETE CASCADE
);
