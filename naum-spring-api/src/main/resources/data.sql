insert into Usuario
    (nome, email, senha, tipo)
values
    ('Guilherme', 'guilherme@gmail.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 0);

INSERT INTO Endereco (cidade, cep, numero, bairro, uf, rua) VALUES
('Cidade 1', '12345678', '123', 'Bairro 1', 'UF1', 'Rua 1');

INSERT INTO Barbearia (nome, link_barbearia, ativa, foto_barbearia, endereco_id) VALUES
('Barbearia 1', 'link1', true, 'foto1.jpg', 1);

INSERT INTO Barbeiro (nome, email, senha, telefone, descricao, barbeiro_ativo, foto, fk_permissao, barbearia_id, usuario_id) VALUES
('Guilherme', 'guilherme@gmail.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', '123456789', 'Descrição do Barbeiro 1', true, 'foto1.jpg', 1, 1, 1);


insert into Usuario
    (nome, email, senha, tipo)
values
    (null, 'adm@gmail.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 1);

INSERT INTO Usuario
    (nome, email, senha, tipo) VALUES
        (3, 'cliente@gmail.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 2);

INSERT INTO Cliente (id, usuario_id, email, nome, senha, telefone) VALUES
    (1, 3, 'cliente@gmail.com', 'cliente 01', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', '977845786');

INSERT INTO Login_Adm (email, senha, usuario_id) VALUES
('adm@gmail.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 2);

INSERT INTO Servico (id, nome_servico, preco, tempo_servico, barbearia_id) VALUES
    (1, 'corte simples', 20.00, 30, 1);
-- --
-- -- Inserir dados na tabela agendamento
INSERT INTO Agendamento (id, barbeiro_id, cliente_id, inicio, fim, valor_total)
VALUES (1, 1, 1, '2024-05-25 14:30:00', '2024-05-25 15:00:00', 50.00);
