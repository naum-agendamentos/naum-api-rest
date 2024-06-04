-- Insert into Usuario table
INSERT INTO Usuario (nome, email, senha, tipo) VALUES
                                                   ('Guilherme', 'guilherme@gmail.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 0),
                                                   (null, 'adm@gmail.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 1);

-- Insert into Endereco table
INSERT INTO Endereco (cidade, cep, numero, bairro, uf, rua) VALUES
    ('Cidade 1', '12345678', '123', 'Bairro 1', 'UF1', 'Rua 1');

-- Insert into Barbearia table
INSERT INTO Barbearia (nome, link_barbearia, ativa, foto_barbearia, endereco_id) VALUES
    ('Barbearia 1', 'link1', true, 'foto1.jpg', 1);

-- Insert into Barbeiro table
INSERT INTO Barbeiro (nome, email, senha, telefone, descricao, barbeiro_ativo, foto, fk_permissao, barbearia_id, usuario_id) VALUES
    ('Guilherme', 'guilherme@gmail.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', '123456789', 'Descrição do Barbeiro 1', true, 'https://img.freepik.com/fotos-gratis/movel_677411-2579.jpg?t=st=1717098698~exp=1717102298~hmac=2006915ae0788b8c4b3e22dd440e94683533da98f2fcb06891bef4c500b56c79&w=740', 1, 1, 1);

-- Insert into Cliente table (assuming this table was missing in your script)
INSERT INTO Cliente (nome, email, senha) VALUES
                                             ('Cliente 1', 'cliente1@gmail.com', 'senha1'),
                                             ('Cliente 2', 'cliente2@gmail.com', 'senha2'); -- Make sure id=2 exists

-- Insert into Login_Adm table
INSERT INTO Login_Adm (email, senha, usuario_id) VALUES
    ('adm@gmail.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 2);

-- Insert into Servico table
INSERT INTO Servico (nome_servico, preco, tempo_servico) VALUES
                                                             ('Corte Simples', 50.0, 30),
                                                             ('Corte Médio', 50.0, 60),
                                                             ('Corte Difícil', 50.0, 30);

-- Insert into Agendamento table
INSERT INTO Agendamento (barbeiro_id, cliente_id, inicio, fim, valor_total)
VALUES (1, 1, '2024-06-01 10:00:00', '2024-06-01 11:00:00', 150.00);

INSERT INTO AGENDAMENTO_SERVICOS_IDS (AGENDAMENTO_ID,SERVICOS_IDS) VALUES (1,1),(1,3);