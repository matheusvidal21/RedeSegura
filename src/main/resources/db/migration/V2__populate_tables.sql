-- Disable foreign key checks to avoid issues during data insertion
SET FOREIGN_KEY_CHECKS = 0;

-- Insert addresses
INSERT INTO addresses (street, city, state, country, postal_code)
VALUES
    ('Av. Senador Salgado Filho, 3000', 'Natal', 'RN', 'Brasil', '59078-970'), -- UFRN
    ('Av. da Universidade, 2853', 'Fortaleza', 'CE', 'Brasil', '60020-181');   -- UFC

-- Insert institutions
INSERT INTO institutions (name, address_id, contact)
VALUES
    ('Universidade Federal do Rio Grande do Norte', 1, 'contato@ufrn.br'), -- UFRN
    ('Universidade Federal do Ceará', 2, 'contato@ufc.br');                -- UFC

-- Insert servers for UFRN
INSERT INTO servers (name, description, institution_id, health)
VALUES
    ('Servidor Vulnerável', 'Servidor com vulnerabilidades conhecidas para testes', 1, 'DEGRADED'),
    ('Servidor A', 'Servidor de aplicação crítica', 1,  'PARTIALLY_OPERATIONAL'),
    ('Servidor B', 'Servidor de backup', 1,  'OPERATIONAL'),
    ('Servidor C', 'Servidor de desenvolvimento', 1, 'DEGRADED'),
    ('Servidor D', 'Servidor de BI', 1, 'OPERATIONAL'),
    ('Servidor E', 'Servidor de armazenamento', 1,  'PARTIALLY_OPERATIONAL');

-- Insert servers for UFC
INSERT INTO servers (name, description, institution_id, health)
VALUES
    ('Servidor Estável', 'Servidor estável e seguro', 2,  'OPERATIONAL'),
    ('Servidor F', 'Servidor de aplicação web', 2, 'PARTIALLY_OPERATIONAL'),
    ('Servidor G', 'Servidor de gerenciamento de rede', 2, 'DEGRADED'),
    ('Servidor H', 'Servidor de testes', 2, 'OPERATIONAL'),
    ('Servidor I', 'Servidor de arquivos', 2, 'PARTIALLY_OPERATIONAL'),
    ('Servidor J', 'Servidor de banco de dados', 2, 'DEGRADED');

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

-- Servidor Vulnerável
INSERT INTO services (name, ip, port, server_id)
VALUES
    ('DNS Service', '200.130.38.131', 53, 1),    -- DNS
    ('NTP Service', '200.130.38.131', 123, 1),   -- NTP
    ('NetBIOS Service', '200.130.38.131', 137, 1), -- NetBIOS
    ('SNMP Service', '200.130.38.131', 161, 1),  -- SNMP
    ('SMB Service', '200.130.38.131', 445, 1),   -- SMB
    ('MySQL Service', '200.130.38.131', 3306, 1), -- MySQL
    ('Redis Service', '200.130.38.131', 6379, 1), -- Redis
    ('SSDP Service', '200.130.38.131', 1900, 1), -- SSDP
    ('Memcached Service', '200.130.38.131', 11211, 1), -- Memcached
    ('SLP Service', '200.130.38.131', 427, 1); -- SLP

-- Servidor Estável
INSERT INTO services (name, ip, port, server_id)
VALUES
    ('DNS Service', '200.130.38.130', 53, 7),    -- DNS
    ('NTP Service', '200.130.38.130', 123, 7),   -- NTP
    ('NetBIOS Service', '200.130.38.130', 137, 7), -- NetBIOS
    ('SNMP Service', '200.130.38.130', 161, 7),  -- SNMP
    ('SMB Service', '200.130.38.130', 445, 7),   -- SMB
    ('MySQL Service', '200.130.38.130', 3306, 7), -- MySQL
    ('Redis Service', '200.130.38.130', 6379, 7), -- Redis
    ('SSDP Service', '200.130.38.130', 1900, 7), -- SSDP
    ('Memcached Service', '200.130.38.130', 11211, 7), -- Memcached
    ('SLP Service', '200.130.38.130', 427, 7); -- SLP

-- Associando Protocolos aos Serviços no Servidor Vulnerável
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

-- Associando Protocolos aos Serviços no Servidor Estável
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

-- Insert vulnerability types
INSERT INTO vulnerability_types (name, description)
VALUES
    ('DNS Recursion', 'Vulnerabilidade que permite ataques de DDoS através de recursão DNS.'),
    ('NTP DDOS Amplification', 'Vulnerabilidade de amplificação DDOS em servidores NTP expostos.'),
    ('NetBIOS Exposure', 'Exposição de informações sensíveis devido a configurações inadequadas do NetBIOS.'),
    ('SNMP Public Community', 'Exposição de comunidades SNMP públicas, permitindo potencial manipulação e ataques de DDoS.'),
    ('Samba Outdated Version', 'Vulnerabilidade em versões desatualizadas do Samba, suscetíveis a escaneamento de portas e acessos não autorizados.'),
    ('Exposed MySQL', 'Exposição do serviço MySQL, permitindo conexões não autenticadas que podem ser exploradas.'),
    ('Redis No Authentication', 'Exposição do Redis sem autenticação, permitindo acesso não autorizado ao serviço.'),
    ('Exposed SSDP', 'Exposição indevida do serviço SSDP, que deve operar apenas em ambientes locais.'),
    ('Exposed Memcached', 'Exposição do serviço Memcached, projetado para operação local, à internet, criando uma vulnerabilidade.'),
    ('Exposed SLP', 'Exposição do serviço SLP à internet, quando deveria ser utilizado apenas localmente.');

-- Vulnerabilidades no Servidor Vulnerável
INSERT INTO vulnerabilities (title, severity, status, server_id, service_id, type_id)
VALUES
    ('Recursive DNS Query Exploitation - AMBIENTE VULNERÁVEL', 'HIGH', 'OPEN', 3, (SELECT id FROM services WHERE name = 'DNS Service' AND ip = '200.130.38.131' LIMIT 1), (SELECT id FROM vulnerability_types WHERE name = 'DNS Recursion' LIMIT 1)),
    ('NTP Reflection Attack - AMBIENTE VULNERÁVEL', 'HIGH', 'OPEN', 3, (SELECT id FROM services WHERE name = 'NTP Service' AND ip = '200.130.38.131' LIMIT 1), (SELECT id FROM vulnerability_types WHERE name = 'NTP DDOS Amplification' LIMIT 1)),
    ('NetBIOS Information Disclosure - AMBIENTE VULNERÁVEL', 'MEDIUM', 'OPEN', 3, (SELECT id FROM services WHERE name = 'NetBIOS Service' AND ip = '200.130.38.131' LIMIT 1), (SELECT id FROM vulnerability_types WHERE name = 'NetBIOS Exposure' LIMIT 1)),
    ('Public SNMP Community String Exposure - AMBIENTE VULNERÁVEL', 'MEDIUM', 'OPEN', 3, (SELECT id FROM services WHERE name = 'SNMP Service' AND ip = '200.130.38.131' LIMIT 1), (SELECT id FROM vulnerability_types WHERE name = 'SNMP Public Community' LIMIT 1)),
    ('Outdated Samba Null Session Exploit - AMBIENTE VULNERÁVEL', 'HIGH', 'OPEN', 3, (SELECT id FROM services WHERE name = 'SMB Service' AND ip = '200.130.38.131' LIMIT 1), (SELECT id FROM vulnerability_types WHERE name = 'Samba Outdated Version' LIMIT 1)),
    ('Exposed MySQL Authentication Bypass - AMBIENTE VULNERÁVEL', 'HIGH', 'OPEN', 3, (SELECT id FROM services WHERE name = 'MySQL Service' AND ip = '200.130.38.131' LIMIT 1), (SELECT id FROM vulnerability_types WHERE name = 'Exposed MySQL' LIMIT 1)),
    ('Unauthenticated Redis Access - AMBIENTE VULNERÁVEL', 'HIGH', 'OPEN', 3, (SELECT id FROM services WHERE name = 'Redis Service' AND ip = '200.130.38.131' LIMIT 1), (SELECT id FROM vulnerability_types WHERE name = 'Redis No Authentication' LIMIT 1)),
    ('SSDP Service Exposure - AMBIENTE VULNERÁVEL', 'MEDIUM', 'OPEN', 3, (SELECT id FROM services WHERE name = 'SSDP Service' AND ip = '200.130.38.131' LIMIT 1), (SELECT id FROM vulnerability_types WHERE name = 'Exposed SSDP' LIMIT 1)),
    ('Memcached Internet Exposure - AMBIENTE VULNERÁVEL', 'HIGH', 'OPEN', 3, (SELECT id FROM services WHERE name = 'Memcached Service' AND ip = '200.130.38.131' LIMIT 1), (SELECT id FROM vulnerability_types WHERE name = 'Exposed Memcached' LIMIT 1)),
    ('SLP Service Exposure - AMBIENTE VULNERÁVEL', 'MEDIUM', 'OPEN', 3, (SELECT id FROM services WHERE name = 'SLP Service' AND ip = '200.130.38.131' LIMIT 1), (SELECT id FROM vulnerability_types WHERE name = 'Exposed SLP' LIMIT 1));

-- Vulnerabilidades no Servidor Estável
INSERT INTO vulnerabilities (title, severity, status, server_id, service_id, type_id)
VALUES
    ('Stable Recursive DNS Query - AMBIENTE ESTÁVEL', 'LOW', 'OPEN', 4, (SELECT id FROM services WHERE name = 'DNS Service' AND ip = '200.130.38.130' LIMIT 1), (SELECT id FROM vulnerability_types WHERE name = 'DNS Recursion' LIMIT 1)),
    ('Stable NTP Reflection Test - AMBIENTE ESTÁVEL', 'LOW', 'RESOLVED', 4, (SELECT id FROM services WHERE name = 'NTP Service' AND ip = '200.130.38.130' LIMIT 1), (SELECT id FROM vulnerability_types WHERE name = 'NTP DDOS Amplification' LIMIT 1)),
    ('Stable NetBIOS Information Disclosure - AMBIENTE ESTÁVEL', 'MEDIUM', 'OPEN', 4, (SELECT id FROM services WHERE name = 'NetBIOS Service' AND ip = '200.130.38.130' LIMIT 1), (SELECT id FROM vulnerability_types WHERE name = 'NetBIOS Exposure' LIMIT 1)),
    ('Stable SNMP Community String Exposure - AMBIENTE ESTÁVEL', 'MEDIUM', 'RESOLVED', 4, (SELECT id FROM services WHERE name = 'SNMP Service' AND ip = '200.130.38.130' LIMIT 1), (SELECT id FROM vulnerability_types WHERE name = 'SNMP Public Community' LIMIT 1)),
    ('Stable Samba Null Session Exploit - AMBIENTE ESTÁVEL', 'HIGH', 'OPEN', 4, (SELECT id FROM services WHERE name = 'SMB Service' AND ip = '200.130.38.130' LIMIT 1), (SELECT id FROM vulnerability_types WHERE name = 'Samba Outdated Version' LIMIT 1)),
    ('Stable MySQL Authentication Bypass - AMBIENTE ESTÁVEL', 'HIGH', 'RESOLVED', 4, (SELECT id FROM services WHERE name = 'MySQL Service' AND ip = '200.130.38.130' LIMIT 1), (SELECT id FROM vulnerability_types WHERE name = 'Exposed MySQL' LIMIT 1)),
    ('Stable Redis Unauthenticated Access - AMBIENTE ESTÁVEL', 'HIGH', 'OPEN', 4, (SELECT id FROM services WHERE name = 'Redis Service' AND ip = '200.130.38.130' LIMIT 1), (SELECT id FROM vulnerability_types WHERE name = 'Redis No Authentication' LIMIT 1)),
    ('Stable SSDP Service Exposure - AMBIENTE ESTÁVEL', 'MEDIUM', 'RESOLVED', 4, (SELECT id FROM services WHERE name = 'SSDP Service' AND ip = '200.130.38.130' LIMIT 1), (SELECT id FROM vulnerability_types WHERE name = 'Exposed SSDP' LIMIT 1)),
    ('Stable Memcached Internet Exposure - AMBIENTE ESTÁVEL', 'HIGH', 'OPEN', 4, (SELECT id FROM services WHERE name = 'Memcached Service' AND ip = '200.130.38.130' LIMIT 1), (SELECT id FROM vulnerability_types WHERE name = 'Exposed Memcached' LIMIT 1)),
    ('Stable SLP Service Exposure - AMBIENTE ESTÁVEL', 'MEDIUM', 'RESOLVED', 4, (SELECT id FROM services WHERE name = 'SLP Service' AND ip = '200.130.38.130' LIMIT 1), (SELECT id FROM vulnerability_types WHERE name = 'Exposed SLP' LIMIT 1));


-- Insert additional services for other servers (excluding Servidor Vulnerável and Servidor Estável)
INSERT INTO services (name, ip, port, server_id)
VALUES
    ('Apache HTTP Service', '200.130.38.140', 80, 2),   -- Servidor A
    ('PostgreSQL Service', '200.130.38.141', 5432, 3),   -- Servidor B
    ('MongoDB Service', '200.130.38.142', 27017, 4),   -- Servidor C
    ('Nginx Service', '200.130.38.143', 443, 5),   -- Servidor D
    ('ElasticSearch Service', '200.130.38.144', 9200, 6),   -- Servidor E
    ('SMTP Service', '200.130.38.150', 25, 8),   -- Servidor F
    ('Redis Service', '200.130.38.151', 6379, 9),   -- Servidor G
    ('MySQL Service', '200.130.38.152', 3306, 10),   -- Servidor H
    ('FTP Service', '200.130.38.153', 21, 11),   -- Servidor I
    ('MariaDB Service', '200.130.38.154', 3306, 12);   -- Servidor J

-- Associando Protocolos aos Serviços nos Servidores (excluindo Servidor Vulnerável e Servidor Estável)
INSERT INTO service_protocols (service_id, protocol_id)
VALUES
    (21, (SELECT id FROM protocols WHERE name = 'TCP')),    -- Apache HTTP usa TCP
    (22, (SELECT id FROM protocols WHERE name = 'TCP')),    -- PostgreSQL usa TCP
    (23, (SELECT id FROM protocols WHERE name = 'TCP')),    -- MongoDB usa TCP
    (24, (SELECT id FROM protocols WHERE name = 'TCP')),    -- Nginx usa TCP
    (25, (SELECT id FROM protocols WHERE name = 'TCP')),    -- ElasticSearch usa TCP
    (26, (SELECT id FROM protocols WHERE name = 'TCP')),    -- SMTP usa TCP
    (27, (SELECT id FROM protocols WHERE name = 'TCP')),    -- Redis usa TCP
    (28, (SELECT id FROM protocols WHERE name = 'TCP')),    -- MySQL usa TCP
    (29, (SELECT id FROM protocols WHERE name = 'TCP')),    -- FTP usa TCP
    (30, (SELECT id FROM protocols WHERE name = 'TCP'));    -- MariaDB usa TCP

-- Insert vulnerabilities into other servers (excluding Servidor Vulnerável and Servidor Estável)
INSERT INTO vulnerabilities (title, severity, status, server_id, service_id, type_id)
VALUES
    ('Apache HTTP Server Information Disclosure', 'MEDIUM', 'OPEN', 2, 21, (SELECT id FROM vulnerability_types WHERE name = 'Exposed SSDP' LIMIT 1)),
    ('PostgreSQL Unauthorized Access', 'HIGH', 'OPEN', 3, 22, (SELECT id FROM vulnerability_types WHERE name = 'Exposed MySQL' LIMIT 1)),
    ('MongoDB Open Access', 'CRITICAL', 'OPEN', 4, 23, (SELECT id FROM vulnerability_types WHERE name = 'Redis No Authentication' LIMIT 1)),
    ('Nginx Insecure Configuration', 'MEDIUM', 'RESOLVED', 5, 24, (SELECT id FROM vulnerability_types WHERE name = 'Samba Outdated Version' LIMIT 1)),
    ('ElasticSearch Publicly Accessible', 'HIGH', 'OPEN', 6, 25, (SELECT id FROM vulnerability_types WHERE name = 'Exposed Memcached' LIMIT 1)),
    ('SMTP Open Relay', 'HIGH', 'OPEN', 8, 26, (SELECT id FROM vulnerability_types WHERE name = 'SNMP Public Community' LIMIT 1)),
    ('Redis Insecure Configuration', 'CRITICAL', 'OPEN', 9, 27, (SELECT id FROM vulnerability_types WHERE name = 'Redis No Authentication' LIMIT 1)),
    ('MySQL Weak Passwords', 'LOW', 'OPEN', 10, 28, (SELECT id FROM vulnerability_types WHERE name = 'Exposed MySQL' LIMIT 1)),
    ('FTP Anonymous Login Allowed', 'MEDIUM', 'OPEN', 11, 29, (SELECT id FROM vulnerability_types WHERE name = 'NetBIOS Exposure' LIMIT 1)),
    ('MariaDB SQL Injection Vulnerability', 'HIGH', 'OPEN', 12, 30, (SELECT id FROM vulnerability_types WHERE name = 'DNS Recursion' LIMIT 1));

-- Insert records into the vulnerability_history table for tracking changes (excluding Servidor Vulnerável and Servidor Estável)
INSERT INTO vulnerability_history (vulnerability_id, title, severity, status, description, action_taken)
VALUES
    ((SELECT id FROM vulnerabilities WHERE title = 'Apache HTTP Server Information Disclosure' LIMIT 1), 'Apache HTTP Server Information Disclosure', 'MEDIUM', 'OPEN', 'Sensitive information exposed by Apache server.', 'Initial discovery of vulnerability'),
    ((SELECT id FROM vulnerabilities WHERE title = 'PostgreSQL Unauthorized Access' LIMIT 1), 'PostgreSQL Unauthorized Access', 'HIGH', 'OPEN', 'Unauthorized access allowed to PostgreSQL database.', 'Initial discovery of vulnerability'),
    ((SELECT id FROM vulnerabilities WHERE title = 'MongoDB Open Access' LIMIT 1), 'MongoDB Open Access', 'CRITICAL', 'OPEN', 'MongoDB database is publicly accessible without authentication.', 'Initial discovery of vulnerability'),
    ((SELECT id FROM vulnerabilities WHERE title = 'Nginx Insecure Configuration' LIMIT 1), 'Nginx Insecure Configuration', 'MEDIUM', 'RESOLVED', 'Nginx server was configured with weak security settings.', 'Vulnerability resolved after security patches applied'),
    ((SELECT id FROM vulnerabilities WHERE title = 'ElasticSearch Publicly Accessible' LIMIT 1), 'ElasticSearch Publicly Accessible', 'HIGH', 'OPEN', 'ElasticSearch service is accessible over the internet.', 'Initial discovery of vulnerability'),
    ((SELECT id FROM vulnerabilities WHERE title = 'SMTP Open Relay' LIMIT 1), 'SMTP Open Relay', 'HIGH', 'OPEN', 'SMTP server is misconfigured as an open relay.', 'Initial discovery of vulnerability', 2),
    ((SELECT id FROM vulnerabilities WHERE title = 'Redis Insecure Configuration' LIMIT 1), 'Redis Insecure Configuration', 'CRITICAL', 'OPEN', 'Redis server is configured without authentication, allowing unauthorized access.', 'Initial discovery of vulnerability'),
    ((SELECT id FROM vulnerabilities WHERE title = 'MySQL Weak Passwords' LIMIT 1), 'MySQL Weak Passwords', 'LOW', 'OPEN', 'MySQL service allows weak password authentication.', 'Initial discovery of vulnerability'),
    ((SELECT id FROM vulnerabilities WHERE title = 'FTP Anonymous Login Allowed' LIMIT 1), 'FTP Anonymous Login Allowed', 'MEDIUM', 'OPEN', 'FTP service allows anonymous login without restrictions.', 'Initial discovery of vulnerability'),
    ((SELECT id FROM vulnerabilities WHERE title = 'MariaDB SQL Injection Vulnerability' LIMIT 1), 'MariaDB SQL Injection Vulnerability', 'HIGH', 'OPEN', 'SQL Injection vulnerability found in MariaDB service.', 'Initial discovery of vulnerability');

-- Re-enable foreign key checks after data insertion
SET FOREIGN_KEY_CHECKS = 1;

