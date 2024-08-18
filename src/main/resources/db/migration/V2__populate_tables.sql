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
INSERT INTO systems (name, description, institution_id, responsible_id)
VALUES
    ('Sistema de Gestão Acadêmica', 'Sistema que gerencia as atividades acadêmicas da UFRN', 1, 1), -- Sistema UFRN
    ('Sistema de Biblioteca', 'Sistema de gerenciamento das bibliotecas da UFC', 2, 2);             -- Sistema UFC

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
