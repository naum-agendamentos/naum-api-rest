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

INSERT INTO Login_Adm (email, senha, usuario_id) VALUES
('adm@gmail.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 2);
