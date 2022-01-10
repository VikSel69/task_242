
INSERT INTO roles (role) VALUES ('ROLE_ADMIN'), ('ROLE_USER');
INSERT INTO users (email, enabled, first_name, last_name, password) VALUES ('viktor@mail.ru', true, 'Виктор', 'Селезнев', '$2a$12$P4XBNgCiYzT/OavtHlQ8X.2Bd/XpoA4C1ZTzdA3vLu6j6LDT0zCJC');
INSERT INTO users_roles VALUES (1, 1), (1, 2);
