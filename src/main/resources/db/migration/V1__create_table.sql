-- Create addresses table
CREATE TABLE IF NOT EXISTS addresses (
                                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                         street VARCHAR(255),
                                         city VARCHAR(100),
                                         state VARCHAR(100),
                                         country VARCHAR(100),
                                         postal_code VARCHAR(20),
                                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create institutions table
CREATE TABLE IF NOT EXISTS institutions (
                                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                            name VARCHAR(255) NOT NULL,
                                            address_id BIGINT,
                                            type VARCHAR(100),
                                            contact VARCHAR(255),
                                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                            FOREIGN KEY (address_id) REFERENCES addresses(id)
);

-- Create users table
CREATE TABLE IF NOT EXISTS users (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     name VARCHAR(255) NOT NULL,
                                     email VARCHAR(255) NOT NULL,
                                     password VARCHAR(255) NOT NULL,
                                     institution_id BIGINT,
                                     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                     updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                     FOREIGN KEY (institution_id) REFERENCES institutions(id) ON DELETE CASCADE
);

-- Create roles table
CREATE TABLE IF NOT EXISTS roles (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     name VARCHAR(100) NOT NULL
);

-- Create users_roles table
CREATE TABLE IF NOT EXISTS users_roles (
                                           user_id BIGINT,
                                           role_id BIGINT,
                                           PRIMARY KEY (user_id, role_id),
                                           FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                                           FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

-- Create servers table
CREATE TABLE IF NOT EXISTS servers (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       name VARCHAR(255) NOT NULL,
                                       description TEXT,
                                       institution_id BIGINT,
                                       responsible_id BIGINT,
                                       health VARCHAR(50),
                                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                       FOREIGN KEY (institution_id) REFERENCES institutions(id) ON DELETE CASCADE,
                                       FOREIGN KEY (responsible_id) REFERENCES users(id) ON DELETE SET NULL
);

-- Create protocols table
CREATE TABLE IF NOT EXISTS protocols (
                                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                         name VARCHAR(50) NOT NULL UNIQUE
);

-- Create services table
CREATE TABLE IF NOT EXISTS services (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        name VARCHAR(255) NOT NULL,
                                        ip VARCHAR(45) NOT NULL,
                                        port INT NOT NULL,
                                        status VARCHAR(50) DEFAULT 'UNKNOWN',
                                        server_id BIGINT,
                                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                        FOREIGN KEY (server_id) REFERENCES servers(id) ON DELETE CASCADE
);

-- Create service_protocols table
CREATE TABLE IF NOT EXISTS service_protocols (
                                                 service_id BIGINT,
                                                 protocol_id BIGINT,
                                                 PRIMARY KEY (service_id, protocol_id),
                                                 FOREIGN KEY (service_id) REFERENCES services(id) ON DELETE CASCADE,
                                                 FOREIGN KEY (protocol_id) REFERENCES protocols(id) ON DELETE CASCADE
);

-- Create vulnerability_types table
CREATE TABLE IF NOT EXISTS vulnerability_types (
                                                   id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                                   name VARCHAR(255) NOT NULL,
                                                   description TEXT
);

-- Create vulnerabilities table
CREATE TABLE IF NOT EXISTS vulnerabilities (
                                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                               title VARCHAR(255) NOT NULL,
                                               severity VARCHAR(50),
                                               status VARCHAR(50),
                                               server_id BIGINT,
                                               service_id BIGINT NOT NULL,
                                               type_id BIGINT NOT NULL,
                                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                               resolved_at TIMESTAMP,
                                               FOREIGN KEY (server_id) REFERENCES servers(id) ON DELETE CASCADE,
                                               FOREIGN KEY (type_id) REFERENCES vulnerability_types(id) ON DELETE CASCADE,
                                               FOREIGN KEY (service_id) REFERENCES services(id) ON DELETE CASCADE
);

-- Create action_log table
CREATE TABLE IF NOT EXISTS action_log (
                                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                          vulnerability_id BIGINT,
                                          action TEXT,
                                          performed_by BIGINT,
                                          timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                          FOREIGN KEY (vulnerability_id) REFERENCES vulnerabilities(id) ON DELETE CASCADE,
                                          FOREIGN KEY (performed_by) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS vulnerability_history (
                                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                                     vulnerability_id BIGINT NOT NULL,
                                                     title VARCHAR(255),
                                                     severity VARCHAR(50),
                                                     status VARCHAR(50),
                                                     description TEXT,
                                                     action_taken TEXT,
                                                     changed_by BIGINT,
                                                     changed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                                     FOREIGN KEY (vulnerability_id) REFERENCES vulnerabilities(id) ON DELETE CASCADE,
                                                     FOREIGN KEY (changed_by) REFERENCES users(id) ON DELETE SET NULL
);
