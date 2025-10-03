DROP TABLE IF EXISTS usuarios;
DROP TABLE IF EXISTS auth_users;

-- Tabla de autenticación
CREATE TABLE auth_users (
                            id SERIAL PRIMARY KEY,
                            username VARCHAR(50) NOT NULL UNIQUE,
                            contrasena VARCHAR(255) NOT NULL,
                            rol VARCHAR(20) NOT NULL DEFAULT 'admin'
);

INSERT INTO auth_users (username, contrasena, rol)
VALUES ('admin', 'utec123', 'admin');

-- Tabla de usuarios
CREATE TABLE usuarios (
                          id SERIAL PRIMARY KEY,
                          nombre VARCHAR(100) NOT NULL,
                          email VARCHAR(120) NOT NULL UNIQUE,
                          rol VARCHAR(20) NOT NULL CHECK (rol IN ('admin','usuario'))
);

INSERT INTO usuarios (nombre, email, rol) VALUES
                                              ('wilson deng', 'wilson@utec.com.pe', 'usuario'),
                                              ('jaime farfan', 'jaime@utec.com.pe', 'admin');
