use bd_mncompany;

INSERT INTO usuario(nome, email, senha)
VALUES("Neudo Paulino", "neudo@gmail.com", "12345"), ("Maria Girlene", "girlene@gmail.com", "12345"),
("Felipe Gabriel", "felipe@gmail.com", "12345"), ("Leocassio Silva", "leocassio@gmail.com", "12345"),
("Jakeline Freitas", "jakeline@gmail.com", "12345"), ("Natan Almeida", "natan@gmail.com", "12345");

INSERT INTO voo(origem, destino, data_saida, hora_saida, data_chegada, hora_chegada, companhia)
VALUES ("Fortaleza", "São Paulo", "2020-11-20", "13:00:00", "2020-11-20", "16:30:00", "Latam"),
("São Paulo", "Fortaleza", "2020-11-25", "17:00:00", "2020-11-25", "20:30:00", "Latam"),
("Natal", "Rio de Janeiro", "2020-12-01", "09:00:00", "2020-12-01", "13:00:00", "Azul"),
("Rio de Janeiro", "Natal", "2020-12-05", "13:00:00", "2020-12-05", "16:30:00", "Azul"),
("Bahia", "Coritiba", "2020-12-10", "12:00:00", "2020-12-10", "15:30:00", "GOL");

INSERT INTO situacao_pagamento(status)
VALUES ("concluida"), ("andamento"), ("cancelado");

#FAZER PARA OS OUTROS IDS
INSERT INTO assento(nome, disponibilidade, classe, preco, id_voo)
VALUES ("A1", true, "primeira", 2000, 1), ("B1", true, "primeira", 2000, 1), ("C1", true, "primeira", 2000, 1), ("D1", true, "primeira", 2000, 1), ("E1", true, "primeira", 2000, 1), ("F1", true, "primeira", 2000, 1),
("A2", true, "primeira", 2000, 1), ("B2", true, "primeira", 2000, 1), ("C2", true, "primeira", 2000, 1), ("D2", true, "primeira", 2000, 1), ("E2", true, "primeira", 2000, 1), ("F2", true, "primeira", 2000, 1),
("A3", true, "primeira", 2000, 1), ("B3", true, "primeira", 2000, 1), ("C3", true, "primeira", 2000, 1), ("D3", true, "primeira", 2000, 1), ("E3", true, "primeira", 2000, 1), ("F3", true, "primeira", 2000, 1),
("A4", true, "primeira", 2000, 1), ("B4", true, "primeira", 2000, 1), ("C4", true, "primeira", 2000, 1), ("D4", true, "primeira", 2000, 1), ("E4", true, "primeira", 2000, 1), ("F4", true, "primeira", 2000, 1),
("A5", true, "primeira", 2000, 1), ("B5", true, "primeira", 2000, 1), ("C5", true, "primeira", 2000, 1), ("D5", true, "primeira", 2000, 1), ("E5", true, "primeira", 2000, 1), ("F5", true, "primeira", 2000, 1),
("A6", true, "primeira", 2000, 1), ("B6", true, "primeira", 2000, 1), ("C6", true, "primeira", 2000, 1), ("D6", true, "primeira", 2000, 1), ("E6", true, "primeira", 2000, 1), ("F6", true, "primeira", 2000, 1),
("A7", true, "primeira", 2000, 1), ("B7", true, "primeira", 2000, 1), ("C7", true, "primeira", 2000, 1), ("D7", true, "primeira", 2000, 1), ("E7", true, "primeira", 2000, 1), ("F7", true, "primeira", 2000, 1),
("A8", true, "primeira", 2000, 1), ("B8", true, "primeira", 2000, 1), ("C8", true, "primeira", 2000, 1), ("D8", true, "primeira", 2000, 1), ("E8", true, "primeira", 2000, 1), ("F8", true, "primeira", 2000, 1),
("A9", true, "primeira", 2000, 1), ("B9", true, "primeira", 2000, 1), ("C9", true, "primeira", 2000, 1), ("D9", true, "primeira", 2000, 1), ("E9", true, "primeira", 2000, 1), ("F9", true, "primeira", 2000, 1),
("A10", true, "primeira", 2000, 1), ("B10", true, "primeira", 2000, 1), ("C10", true, "primeira", 2000, 1), ("D10", true, "primeira", 2000, 1), ("E10", true, "primeira", 2000, 1), ("F10", true, "primeira", 2000, 1),
("A11", true, "economica", 1000, 1), ("B11", true, "economica", 1000, 1), ("C11", true, "economica", 1000, 1), ("D11", true, "economica", 1000, 1), ("E11", true, "economica", 1000, 1), ("F11", true, "economica", 1000, 1),
("A12", true, "economica", 1000, 1), ("B12", true, "economica", 1000, 1), ("C12", true, "economica", 1000, 1), ("D12", true, "economica", 1000, 1), ("E12", true, "economica", 1000, 1), ("F12", true, "economica", 1000, 1),
("A13", true, "economica", 1000, 1), ("B13", true, "economica", 1000, 1), ("C13", true, "economica", 1000, 1), ("D13", true, "economica", 1000, 1), ("E13", true, "economica", 1000, 1), ("F13", true, "economica", 1000, 1),
("A14", true, "economica", 1000, 1), ("B14", true, "economica", 1000, 1), ("C14", true, "economica", 1000, 1), ("D14", true, "economica", 1000, 1), ("E14", true, "economica", 1000, 1), ("F14", true, "economica", 1000, 1),
("A15", true, "economica", 1000, 1), ("B15", true, "economica", 1000, 1), ("C15", true, "economica", 1000, 1), ("D15", true, "economica", 1000, 1), ("E15", true, "economica", 1000, 1), ("F15", true, "economica", 1000, 1),
("A16", true, "economica", 1000, 1), ("B16", true, "economica", 1000, 1), ("C16", true, "economica", 1000, 1), ("D16", true, "economica", 1000, 1), ("E16", true, "economica", 1000, 1), ("F16", true, "economica", 1000, 1),
("A17", true, "economica", 1000, 1), ("B17", true, "economica", 1000, 1), ("C17", true, "economica", 1000, 1), ("D17", true, "economica", 1000, 1), ("E17", true, "economica", 1000, 1), ("F17", true, "economica", 1000, 1),
("A18", true, "economica", 1000, 1), ("B18", true, "economica", 1000, 1), ("C18", true, "economica", 1000, 1), ("D18", true, "economica", 1000, 1), ("E18", true, "economica", 1000, 1), ("F18", true, "economica", 1000, 1),
("A19", true, "economica", 1000, 1), ("B19", true, "economica", 1000, 1), ("C19", true, "economica", 1000, 1), ("D19", true, "economica", 1000, 1), ("E19", true, "economica", 1000, 1), ("F19", true, "economica", 1000, 1),
("A20", true, "economica", 1000, 1), ("B20", true, "economica", 1000, 1), ("C20", true, "economica", 1000, 1), ("D20", true, "economica", 1000, 1), ("E20", true, "economica", 1000, 1), ("F20", true, "economica", 1000, 1),
("A21", true, "economica", 1000, 1), ("B21", true, "economica", 1000, 1), ("C21", true, "economica", 1000, 1), ("D21", true, "economica", 1000, 1), ("E21", true, "economica", 1000, 1), ("F21", true, "economica", 1000, 1),
("A22", true, "economica", 1000, 1), ("B22", true, "economica", 1000, 1), ("C22", true, "economica", 1000, 1), ("D22", true, "economica", 1000, 1), ("E22", true, "economica", 1000, 1), ("F22", true, "economica", 1000, 1),
("A23", true, "economica", 1000, 1), ("B23", true, "economica", 1000, 1), ("C23", true, "economica", 1000, 1), ("D23", true, "economica", 1000, 1), ("E23", true, "economica", 1000, 1), ("F23", true, "economica", 1000, 1),
("A24", true, "economica", 1000, 1), ("B24", true, "economica", 1000, 1), ("C24", true, "economica", 1000, 1), ("D24", true, "economica", 1000, 1), ("E24", true, "economica", 1000, 1), ("F24", true, "economica", 1000, 1);

INSERT INTO passagem(id_usuario, id_pagamento, id_assento)
VALUES(1, 1, 5), (2, 2, 12), (3, 3, 16), (1, 1, 10), (3,1,130);

select * from passagem as p
inner join usuario as u on (p.id_usuario = u.id)
inner join situacao_pagamento as s on (p.id_pagamento = s.id)
inner join assento as a on (p.id_assento = a.id)
inner join voo as v on (a.id_voo = v.id)
where p.id_usuario = 2;

select * from passagem as p inner join usuario as u on p.id_usuario = u.id inner join situacao_pagamento as s on p.id_pagamento = s.id inner join assento as a on p.id_assento = a.id inner join voo as v on a.id_voo = v.id where u.id = 1;

update assento set disponibilidade = false
where id = 135;

update voo set destino = "Curitiba"
where id = 5;