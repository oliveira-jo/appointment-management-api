INSERT INTO tb_professional (id, name, specialty, created_at) VALUES (RANDOM_UUID(), 'LeandroBueno', 'Corte de Cabelo', CURRENT_TIMESTAMP );


INSERT INTO tb_customer (id, name, phone, email, created_at) VALUES (RANDOM_UUID(), 'Lucilene Massoco Oliveira', '(11) 99999-9999','lucilene@gmail.com',CURRENT_TIMESTAMP);
INSERT INTO tb_customer (id, name, phone, email, created_at) VALUES (RANDOM_UUID(), 'Lucia Oliveira', '(11) 99999-9999','lucia@gmail.com',CURRENT_TIMESTAMP);
INSERT INTO tb_customer (id, name, phone, email, created_at) VALUES (RANDOM_UUID(), 'Carla Brown', '(11) 99999-9999','carla@gmail.com',CURRENT_TIMESTAMP);
INSERT INTO tb_customer (id, name, phone, email, created_at) VALUES (RANDOM_UUID(), 'Ana Maria', '(11) 99999-9999','ana@gmail.com',CURRENT_TIMESTAMP);
INSERT INTO tb_customer (id, name, phone, email, created_at) VALUES (RANDOM_UUID(), 'Herta B. Massoco', '(11) 99999-9999','herta@gmail.com',CURRENT_TIMESTAMP);
INSERT INTO tb_customer (id, name, phone, email, created_at) VALUES (RANDOM_UUID(), 'Camila Brown', '(11) 99999-9999','camila@gmail.com',CURRENT_TIMESTAMP);


INSERT INTO tb_product (id, name, duration, price, created_at ) VALUES (RANDOM_UUID(), 'Corte de Cabelo Básico',  30,  39.00,  CURRENT_TIMESTAMP);
INSERT INTO tb_product (id, name, duration, price, created_at ) VALUES (RANDOM_UUID(), 'Corte de Cabelo com Barba',  45,  59.00,  CURRENT_TIMESTAMP);
INSERT INTO tb_product (id, name, duration, price, created_at ) VALUES (RANDOM_UUID(), 'Corte de Cabelo Premium',  40,  79.00,  CURRENT_TIMESTAMP);
INSERT INTO tb_product (id, name, duration, price, created_at ) VALUES (RANDOM_UUID(), 'Corte de Cabelo Premium com Barba',  60,  99.00,  CURRENT_TIMESTAMP);