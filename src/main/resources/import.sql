INSERT INTO tb_professional (id, name, specialty, email, created_at) VALUES (RANDOM_UUID(), 'LeandroBueno', 'Corte de Cabelo', 'leandro@gmail.com', CURRENT_TIMESTAMP );


INSERT INTO tb_customer (id, name, phone, email, created_at) VALUES (RANDOM_UUID(), 'Lucilene Massoco Oliveira', '(11) 99999-9999','lucilene@gmail.com',CURRENT_TIMESTAMP);
INSERT INTO tb_customer (id, name, phone, email, created_at) VALUES (RANDOM_UUID(), 'Lucia Oliveira', '(11) 99999-9999','lucia@gmail.com',CURRENT_TIMESTAMP);
INSERT INTO tb_customer (id, name, phone, email, created_at) VALUES (RANDOM_UUID(), 'Carla Brown', '(11) 99999-9999','carla@gmail.com',CURRENT_TIMESTAMP);
INSERT INTO tb_customer (id, name, phone, email, created_at) VALUES (RANDOM_UUID(), 'Ana Maria', '(11) 99999-9999','ana@gmail.com',CURRENT_TIMESTAMP);
INSERT INTO tb_customer (id, name, phone, email, created_at) VALUES (RANDOM_UUID(), 'Herta B. Massoco', '(11) 99999-9999','herta@gmail.com',CURRENT_TIMESTAMP);
INSERT INTO tb_customer (id, name, phone, email, created_at) VALUES (RANDOM_UUID(), 'Camila Brown', '(11) 99999-9999','camila@gmail.com',CURRENT_TIMESTAMP);


INSERT INTO tb_product (id, name, duration_in_seconds, price, created_at ) VALUES ('5f41d15f-e3e8-4669-a28e-ae21b313e6af', 'Corte de Cabelo Básico',  1800000000000,  39.00,  CURRENT_TIMESTAMP);
INSERT INTO tb_product (id, name, duration_in_seconds, price, created_at ) VALUES ('c0f8c7e2-7b2d-4a1c-8f3e-5c0b8f1e2a3b', 'Corte de Cabelo com Barba',  2700000000000,  59.00,  CURRENT_TIMESTAMP);
INSERT INTO tb_product (id, name, duration_in_seconds, price, created_at ) VALUES ('a1b2c3d4-e5f6-7890-a1b2-c3d4e5f67890', 'Corte de Cabelo Premium',  2400000000000,  79.00,  CURRENT_TIMESTAMP);
INSERT INTO tb_product (id, name, duration_in_seconds, price, created_at ) VALUES ('b2c3d4e5-f678-9012-a3b4-c5d6e7f89012', 'Corte de Cabelo Premium com Barba',  3600000000000,  99.00,  CURRENT_TIMESTAMP);