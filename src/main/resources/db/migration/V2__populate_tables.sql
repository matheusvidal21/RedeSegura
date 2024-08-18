-- Disable foreign key checks to avoid issues during data insertion
SET FOREIGN_KEY_CHECKS = 0;

-- Insert addresses
INSERT INTO addresses (street, city, state, country, postal_code)
VALUES
    ('Av. Senador Salgado Filho, 3000', 'Natal', 'RN', 'Brasil', '59078-970'), -- UFRN
    ('Av. da Universidade, 2853', 'Fortaleza', 'CE', 'Brasil', '60020-181');   -- UFC

-- Insert institutions
INSERT INTO institutions (name, address_id, type, contact)
VALUES
    ('Universidade Federal do Rio Grande do Norte', 1, 'Universidade', 'contato@ufrn.br'), -- UFRN
    ('Universidade Federal do Ceará', 2, 'Universidade', 'contato@ufc.br');                -- UFC

-- Insert roles
INSERT INTO roles (name)
VALUES
    ('ADMIN'),
    ('USER'),
    ('SUPPORT');

-- Insert users
INSERT INTO users (name, email, password, institution_id)
VALUES
    ('João da Silva', 'joao.silva@ufrn.br', 'senhaSegura123', 1), -- Usuário UFRN
    ('Maria de Souza', 'maria.souza@ufc.br', 'senhaForte456', 2); -- Usuário UFC

-- Assign roles to users
INSERT INTO users_roles (user_id, role_id)
VALUES
    (1, 1), -- João da Silva é ADMIN
    (1, 2), -- João da Silva é USER
    (2, 2), -- Maria de Souza é USER
    (2, 3); -- Maria de Souza é SUPPORT

-- Insert systems
INSERT INTO systems (name, description, institution_id, responsible_id, health)
VALUES
    ('Sistema de Gestão Acadêmica', 'Sistema que gerencia as atividades acadêmicas da UFRN', 1, 1, 'OPERATIONAL'), -- Sistema UFRN
    ('Sistema de Biblioteca', 'Sistema de gerenciamento das bibliotecas da UFC', 2, 2, 'PARTIALLY_OPERATIONAL'),   -- Sistema UFC
    ('Sistema Vulnerável', 'Sistema com vulnerabilidades conhecidas para testes', 1, 1, 'DEGRADED'),               -- Sistema Vulnerável
    ('Sistema Estável', 'Sistema estável e seguro', 2, 2, 'OPERATIONAL');                                          -- Sistema Estável

-- Insert protocols
INSERT INTO protocols (name) VALUES
                                 ('HTTP'),           -- HyperText Transfer Protocol
                                 ('HTTPS'),          -- HTTP Secure
                                 ('FTP'),            -- File Transfer Protocol
                                 ('SFTP'),           -- Secure File Transfer Protocol
                                 ('SSH'),            -- Secure Shell
                                 ('SMTP'),           -- Simple Mail Transfer Protocol
                                 ('IMAP'),           -- Internet Message Access Protocol
                                 ('POP3'),           -- Post Office Protocol 3
                                 ('DNS'),            -- Domain Name System
                                 ('TCP'),            -- Transmission Control Protocol
                                 ('UDP'),            -- User Datagram Protocol
                                 ('RDP'),            -- Remote Desktop Protocol
                                 ('SMB'),            -- Server Message Block
                                 ('NFS'),            -- Network File System
                                 ('SNMP'),           -- Simple Network Management Protocol
                                 ('LDAP'),           -- Lightweight Directory Access Protocol
                                 ('MQTT'),           -- Message Queuing Telemetry Transport
                                 ('AMQP'),           -- Advanced Message Queuing Protocol
                                 ('COAP'),           -- Constrained Application Protocol
                                 ('ICMP'),           -- Internet Control Message Protocol
                                 ('TELNET'),         -- Teletype Network Protocol
                                 ('TFTP'),           -- Trivial File Transfer Protocol
                                 ('RTSP'),           -- Real-Time Streaming Protocol
                                 ('SCTP');           -- Stream Control Transmission Protocol


-- Sistema Vulnerável
INSERT INTO services (name, ip, port, system_id)
VALUES
    ('DNS Service', '200.130.38.131', 53, 3),    -- DNS
    ('NTP Service', '200.130.38.131', 123, 3),   -- NTP
    ('NetBIOS Service', '200.130.38.131', 137, 3), -- NetBIOS
    ('SNMP Service', '200.130.38.131', 161, 3),  -- SNMP
    ('SMB Service', '200.130.38.131', 445, 3),   -- SMB
    ('MySQL Service', '200.130.38.131', 3306, 3), -- MySQL
    ('Redis Service', '200.130.38.131', 6379, 3), -- Redis
    ('SSDP Service', '200.130.38.131', 1900, 3), -- SSDP
    ('Memcached Service', '200.130.38.131', 11211, 3), -- Memcached
    ('SLP Service', '200.130.38.131', 427, 3); -- SLP

-- Sistema Estável
INSERT INTO services (name, ip, port, system_id)
VALUES
    ('DNS Service', '200.130.38.130', 53, 4),    -- DNS
    ('NTP Service', '200.130.38.130', 123, 4),   -- NTP
    ('NetBIOS Service', '200.130.38.130', 137, 4), -- NetBIOS
    ('SNMP Service', '200.130.38.130', 161, 4),  -- SNMP
    ('SMB Service', '200.130.38.130', 445, 4),   -- SMB
    ('MySQL Service', '200.130.38.130', 3306, 4), -- MySQL
    ('Redis Service', '200.130.38.130', 6379, 4), -- Redis
    ('SSDP Service', '200.130.38.130', 1900, 4), -- SSDP
    ('Memcached Service', '200.130.38.130', 11211, 4), -- Memcached
    ('SLP Service', '200.130.38.130', 427, 4); -- SLP

-- Associando Protocolos aos Serviços no Sistema Vulnerável
INSERT INTO service_protocols (service_id, protocol_id)
VALUES
    (1, (SELECT id FROM protocols WHERE name = 'UDP')),    -- DNS usa UDP
    (2, (SELECT id FROM protocols WHERE name = 'UDP')),    -- NTP usa UDP
    (3, (SELECT id FROM protocols WHERE name = 'UDP')),    -- NetBIOS usa UDP
    (4, (SELECT id FROM protocols WHERE name = 'UDP')),    -- SNMP usa UDP
    (5, (SELECT id FROM protocols WHERE name = 'TCP')),    -- SMB usa TCP
    (6, (SELECT id FROM protocols WHERE name = 'TCP')),    -- MySQL usa TCP
    (7, (SELECT id FROM protocols WHERE name = 'TCP')),    -- Redis usa TCP
    (8, (SELECT id FROM protocols WHERE name = 'UDP')),    -- SSDP usa UDP
    (9, (SELECT id FROM protocols WHERE name = 'TCP')),    -- Memcached usa TCP
    (9, (SELECT id FROM protocols WHERE name = 'UDP')),    -- Memcached usa UDP
    (10, (SELECT id FROM protocols WHERE name = 'TCP')),   -- SLP usa TCP
    (10, (SELECT id FROM protocols WHERE name = 'UDP'));   -- SLP usa UDP

-- Associando Protocolos aos Serviços no Sistema Estável
INSERT INTO service_protocols (service_id, protocol_id)
VALUES
    (11, (SELECT id FROM protocols WHERE name = 'UDP')),    -- DNS usa UDP
    (12, (SELECT id FROM protocols WHERE name = 'UDP')),    -- NTP usa UDP
    (13, (SELECT id FROM protocols WHERE name = 'UDP')),    -- NetBIOS usa UDP
    (14, (SELECT id FROM protocols WHERE name = 'UDP')),    -- SNMP usa UDP
    (15, (SELECT id FROM protocols WHERE name = 'TCP')),    -- SMB usa TCP
    (16, (SELECT id FROM protocols WHERE name = 'TCP')),    -- MySQL usa TCP
    (17, (SELECT id FROM protocols WHERE name = 'TCP')),    -- Redis usa TCP
    (18, (SELECT id FROM protocols WHERE name = 'UDP')),    -- SSDP usa UDP
    (19, (SELECT id FROM protocols WHERE name = 'TCP')),    -- Memcached usa TCP
    (19, (SELECT id FROM protocols WHERE name = 'UDP')),    -- Memcached usa UDP
    (20, (SELECT id FROM protocols WHERE name = 'TCP')),    -- SLP usa TCP
    (20, (SELECT id FROM protocols WHERE name = 'UDP'));    -- SLP usa UDP


-- Vulnerabilidades no Sistema Vulnerável
INSERT INTO vulnerabilities (title, description, severity, status, system_id)
VALUES
    ('DNS Recursion', 'Vulnerabilidade de DNS recursion podendo causar DDoS.', 'HIGH', 'OPEN', 3),
    ('NTP v4', 'Vulnerabilidade de amplificação de DDoS em NTP versão 4.', 'HIGH', 'OPEN', 3),
    ('NetBIOS Exposure', 'NetBIOS exposto permitindo consultas de informações sensíveis.', 'MEDIUM', 'OPEN', 3),
    ('SNMP Public Community', 'Comunity "public" exposta, vulnerável a manipulação.', 'MEDIUM', 'OPEN', 3),
    ('Samba Vulnerability', 'Versão defasada do Samba vulnerável a scanning e null session.', 'HIGH', 'OPEN', 3),
    ('Exposed MySQL', 'MySQL exposto à internet, possibilitando conexões não autenticadas.', 'HIGH', 'OPEN', 3),
    ('Redis Exposure', 'Redis exposto sem autenticação, vulnerável a conexões externas.', 'HIGH', 'OPEN', 3),
    ('SSDP Exposure', 'SSDP exposto na internet, serviço deveria rodar localmente.', 'MEDIUM', 'OPEN', 3),
    ('Exposed Memcached', 'Memcached exposto à internet, deveria rodar localmente.', 'HIGH', 'OPEN', 3),
    ('SLP Exposure', 'SLP exposto à internet, deveria rodar localmente.', 'MEDIUM', 'OPEN', 3);

-- Insert vulnerabilities
INSERT INTO vulnerabilities (title, description, severity, status, system_id)
VALUES
    ('SQL Injection', 'Vulnerabilidade de injeção de SQL no módulo de autenticação.', 'HIGH', 'OPEN', 1), -- Vulnerabilidade no sistema da UFRN
    ('Cross-Site Scripting', 'Vulnerabilidade de XSS no módulo de comentários.', 'MEDIUM', 'RESOLVED', 2); -- Vulnerabilidade no sistema da UFC

-- Insert action logs
INSERT INTO action_log (vulnerability_id, action, performed_by)
VALUES
    (1, 'Análise inicial da vulnerabilidade', 1), -- Ação no sistema da UFRN
    (2, 'Correção aplicada e testada', 2);        -- Ação no sistema da UFC

-- Insert reports
INSERT INTO reports (generated_by, content)
VALUES
    (1, 'Relatório de análise da vulnerabilidade SQL Injection no sistema de gestão acadêmica.'), -- Relatório do João da Silva (UFRN)
    (2, 'Relatório de resolução da vulnerabilidade XSS no sistema de biblioteca.');              -- Relatório da Maria de Souza (UFC)

-- Insert tools
INSERT INTO tools (name, description, integration_details)
VALUES
    ('Nmap', 'Ferramenta de varredura de redes e descoberta de hosts.', 'Integração via linha de comando.'),
    ('Burp Suite', 'Ferramenta de teste de segurança de aplicações web.', 'Integração via proxy.');

-- Insert notifications
INSERT INTO notifications (recipient_id, message, is_read)
VALUES
    (1, 'Nova vulnerabilidade identificada no sistema de gestão acadêmica.', FALSE), -- Notificação para João da Silva (UFRN)
    (2, 'Correção aplicada na vulnerabilidade XSS do sistema de biblioteca.', TRUE); -- Notificação para Maria de Souza (UFC)

-- Enable foreign key checks after data insertion
SET FOREIGN_KEY_CHECKS = 1;