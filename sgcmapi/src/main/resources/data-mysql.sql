SET SESSION FOREIGN_KEY_CHECKS=0;

INSERT INTO `atendimento` (`id`, `data`, `hora`, `status`, `convenio_id`, `paciente_id`, `profissional_id`) VALUES
    (1,'2024-11-19','14:00:00','AGENDADO',1,1,1),
    (2,'2024-11-18','14:30:00','AGENDADO',2,2,2),
    (3,'2024-11-18','14:00:00','AGENDADO',3,3,3),
    (4,'2024-11-19','15:00:00','AGENDADO',1,4,4),
    (5,'2024-11-22','15:00:00','AGENDADO',2,1,1),
    (6,'2024-11-22','16:00:00','AGENDADO',3,2,1),
    (7,'2024-11-21','17:30:00','AGENDADO',1,3,2),
    (8,'2024-11-20','16:30:00','AGENDADO',2,4,2),
    (9,'2024-11-20','15:00:00','AGENDADO',3,5,5),
    (10,'2024-11-21','17:30:00','AGENDADO',1,2,3),
    (11,'2024-11-21','18:00:00','AGENDADO',3,3,1),
    (12,'2024-11-25','17:00:00','AGENDADO',1,3,5),
    (13,'2024-11-25','17:30:00','AGENDADO',1,4,5),
    (14,'2024-11-26','18:00:00','AGENDADO',1,5,5),
    (15,'2024-11-26','18:30:00','AGENDADO',1,1,5),
    (16,'2024-11-28','15:00:00','AGENDADO',1,4,4),
    (17,'2024-11-29','15:00:00','AGENDADO',2,1,1),
    (18,'2024-11-28','16:00:00','AGENDADO',3,2,1),
    (19,'2024-11-29','15:30:00','AGENDADO',1,3,2),
    (20,'2024-11-28','16:30:00','AGENDADO',2,4,2);

INSERT INTO `convenio` (`id`, `ativo`, `cnpj`, `email`, `nome`, `razao_social`, `representante`, `telefone`) VALUES
    (1,b'1','46.560.030/0001-53','contato@unimedriobranco.com.br','Unimed','Unimed Rio Branco Cooperativa de Trabalho Médico Ltda.','Thayline Figueredo Vaz','(68) 3668-1546'),
    (2,b'1','29.309.127/0001-79','contato@amil.com.br','Amil','AMIL ASSISTENCIA MEDICA INTERNACIONAL S.A.','Davi Faria da Conceição','(11) 3279-3035'),
    (3,b'1','92.693.118/0001-60','contato@bradescosaude.com.br','Bradesco','BRADESCO SAUDE S.A.','Leandra Paes dos Anjos','(21) 2503-0787');

INSERT INTO `especialidade` (`id`, `nome`) VALUES
    (1,'Cardiologia'),
    (2,'Dermatologia'),
    (3,'Geriatria'),
    (4,'Infectologia'),
    (5,'Pediatria'),
    (6,'Psiquiatria'),
    (7,'Urologia');

INSERT INTO `paciente` (`id`, `cep`, `cidade`, `data_nascimento`, `email`, `endereco`, `estado`, `grupo_sanguineo`, `nome`, `sexo`, `telefone`) VALUES
    (1,'69903-134','Rio Branco','1945-09-28','giulia.bencatel@gmail.com','Rua Cedro, 100 - Parque dos Sabiás','AC','AB_POSITIVO','Giulia Farias Bencatel','F','(68) 98105-2583'),
    (2,'69901-884','Rio Branco','1941-04-22','wallace.soriano@yahoo.com','Travessa Mossoró, 90 - Vitória','AC','B_POSITIVO','Wallace Macedo Inácio Soriano','M','(68) 97431-7722'),
    (3,'69909-060','Rio Branco','1997-06-05','helen.vilar@gmail.com','Rua Adelaide, 50 - Rosa Linda','AC','AB_NEGATIVO','Helen Dutra Vilar','F','(68) 99752-4954'),
    (4,'69911-262','Rio Branco','2009-03-10','mario.branco@uol.com.br','Rua Luiz Galvez, 200 - Conjunto Castelo Branco','AC','O_POSITIVO','Jean Schuenck Amorin','M','(68) 97120-8079'),
    (5,'69980-970','Cruzeiro do Sul','1974-11-14','lucilene.lucas@gmail.com','Rua Rego Barros, 427 - Centro','AC','A_POSITIVO','Lucilene Santos Lucas','F','(68) 99384-3354');

INSERT INTO `profissional` (`id`, `email`, `nome`, `registro_conselho`, `telefone`, `especialidade_id`, `unidade_id`) VALUES
    (1,'monique.nespoli@uol.com.br','Maria Adelia Serravalle Bezerra','CRM/AC 377','(68) 98205-4704',1,1),
    (2,'elielson.andrade@gmail.com','Elielson Silveira Andrade','CRM/AC 455','(68) 98085-4910',1,1),
    (3,'davi.mendes@yahoo.com','Davi Jesus Mendes','CRM/AC 123','(68) 98408-5352',4,3),
    (4,'carla.valle@gmail.com','Carla da Paixão Valle','CRM/AC 234','(68) 98395-5604',3,1),
    (5,'neuza.nobrega@uol.com.br','Neuza Biango Nobrega','CRM/AC 232','(68) 98561-6622',2,2);

INSERT INTO `unidade` (`id`, `endereco`, `nome`) VALUES
    (1,'Rua Independência, 56 - Conjunto Bela Vista','Bela Vista'),
    (2,'Rua Paulo VI, 100 - Bosque','Bosque'),
    (3,'Rua Rui Barbosa, 252 - Centro','Cruzeiro do Sul');

INSERT INTO `usuario` (`id`, `ativo`, `nome_completo`, `nome_usuario`, `papel`, `senha`) VALUES
    (1,b'1','Administrador','admin','ROLE_ADMIN','$2a$10$h.s3BmgI2/N0MuDYEiawsOYh1P8l7gUcKRY6nhlDqnfcgHTv9Eb7S'),
    (2,b'1','Daniel','daniel','ROLE_ADMIN','$2a$10$8b4pxEEFWXleegyJRhopLO748ONErS6gNpw8MkzldN7KU0jM6hjK.'),
    (3,b'1','Paulo','paulo','ROLE_ATENDENTE','$2a$10$eDxP.Kb7uOQ9.oX1Vjq25.JoInkqmgA2oP6J0oMTWa5pCzLRZOTw2'),
    (4,b'1','Limeira','limeira','ROLE_ATENDENTE','$2a$10$x3e7yFAQ5cN8bD2bZkZ3t.X6IMjHA9o34JmIfuiVVBQfUiGmWnXtG');

SET SESSION FOREIGN_KEY_CHECKS=1;
