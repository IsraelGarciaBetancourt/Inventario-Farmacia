------------------------------
-- 1. USUARIOS
------------------------------
CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(200) NOT NULL,
    nombre_completo VARCHAR(200) NOT NULL,
    rol VARCHAR(50) NOT NULL,         -- ADMIN / USER
    activo BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

------------------------------
-- 2. CATEGORIAS
------------------------------
CREATE TABLE categorias (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL UNIQUE,
    activo BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

------------------------------
-- 3. PRODUCTO_CATALOGO
------------------------------
CREATE TABLE producto_catalogo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(100) NOT NULL UNIQUE,
    nombre VARCHAR(200) NOT NULL,
    id_categoria INT NOT NULL,
    activo BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_producto_categoria
        FOREIGN KEY (id_categoria)
        REFERENCES categorias(id)
);

------------------------------
-- 4. PRODUCTO_PARQUE (Stock)
------------------------------
CREATE TABLE producto_parque (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_producto_catalogo INT NOT NULL,
    existencias INT NOT NULL DEFAULT 0,
    activo BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_parque_producto_catalogo
        FOREIGN KEY (id_producto_catalogo)
        REFERENCES producto_catalogo(id)
);

------------------------------
-- 5. DOCUMENTOS (Ingresos / Salidas)
------------------------------
CREATE TABLE documentos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipo_movimiento VARCHAR(20) NOT NULL,   -- "INGRESO" o "SALIDA"
    numero_documento VARCHAR(100) NOT NULL,
    fecha DATE NOT NULL,
    id_usuario INT NOT NULL,
    observacion VARCHAR(400),
    activo BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_documento_usuario
        FOREIGN KEY (id_usuario)
        REFERENCES usuarios(id)
);

------------------------------
-- 6. DETALLES DE DOCUMENTO
------------------------------
CREATE TABLE documento_detalles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_documento INT NOT NULL,
    id_producto_catalogo INT NOT NULL,
    cantidad INT NOT NULL,
    activo BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_detalle_documento
        FOREIGN KEY (id_documento)
        REFERENCES documentos(id),

    CONSTRAINT fk_detalle_producto_catalogo
        FOREIGN KEY (id_producto_catalogo)
        REFERENCES producto_catalogo(id)
);