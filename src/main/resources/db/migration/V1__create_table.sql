-- Create Address table
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

-- Create Institution table
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

-- (Outras tabelas)

-- Create User table
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

-- Create Role table
CREATE TABLE IF NOT EXISTS roles (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     name VARCHAR(100) NOT NULL
);

-- Create User_Roles table (Many-to-Many relationship between Users and Roles)
CREATE TABLE IF NOT EXISTS users_roles (
                                           user_id BIGINT,
                                           role_id BIGINT,
                                           PRIMARY KEY (user_id, role_id),
                                           FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                                           FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

-- Create System table
CREATE TABLE IF NOT EXISTS systems (
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

-- Create Protocol table
CREATE TABLE IF NOT EXISTS protocols (
                                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                         name VARCHAR(50) NOT NULL UNIQUE
);

-- Create Service table
CREATE TABLE IF NOT EXISTS services (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        name VARCHAR(255) NOT NULL,
                                        ip VARCHAR(45) NOT NULL,
                                        port INT NOT NULL,
                                        status VARCHAR(50) DEFAULT 'UNKNOWN',
                                        system_id BIGINT,
                                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                        FOREIGN KEY (system_id) REFERENCES systems(id) ON DELETE CASCADE
);

-- Create Service_Protocols table
CREATE TABLE IF NOT EXISTS service_protocols (
                                                 service_id BIGINT,
                                                 protocol_id BIGINT,
                                                 PRIMARY KEY (service_id, protocol_id),
                                                 FOREIGN KEY (service_id) REFERENCES services(id) ON DELETE CASCADE,
                                                 FOREIGN KEY (protocol_id) REFERENCES protocols(id) ON DELETE CASCADE
);


-- Create Vulnerability table
CREATE TABLE IF NOT EXISTS vulnerabilities (
                                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                               title VARCHAR(255) NOT NULL,
                                               description TEXT,
                                               severity VARCHAR(50),
                                               status VARCHAR(50),
                                               system_id BIGINT,
                                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                               resolved_at TIMESTAMP,
                                               FOREIGN KEY (system_id) REFERENCES systems(id) ON DELETE CASCADE
);

-- Create ActionLog table
CREATE TABLE IF NOT EXISTS action_log (
                                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                          vulnerability_id BIGINT,
                                          action TEXT,
                                          performed_by BIGINT,
                                          timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                          FOREIGN KEY (vulnerability_id) REFERENCES vulnerabilities(id) ON DELETE CASCADE,
                                          FOREIGN KEY (performed_by) REFERENCES users(id) ON DELETE CASCADE
);

-- Create Report table
CREATE TABLE IF NOT EXISTS reports (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       generated_by BIGINT,
                                       content TEXT,
                                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                       FOREIGN KEY (generated_by) REFERENCES users(id) ON DELETE CASCADE
);

-- Create Tool table
CREATE TABLE IF NOT EXISTS tools (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     name VARCHAR(255) NOT NULL,
                                     description TEXT,
                                     integration_details TEXT
);

-- Create Notification table
CREATE TABLE IF NOT EXISTS notifications (
                                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                             recipient_id BIGINT,
                                             message TEXT,
                                             is_read BOOLEAN DEFAULT FALSE,
                                             timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                             FOREIGN KEY (recipient_id) REFERENCES users(id) ON DELETE CASCADE
);
