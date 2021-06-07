
DROP DATABASE IF EXISTS DBTESTE;

CREATE DATABASE DBTESTE;

USE DBTESTE;

CREATE TABLE disciplina (
  id_disciplina INT NOT NULL AUTO_INCREMENT
  , nome_disciplina varchar(50) not null 
  , constraint PK_DISCIPLINA primary key (id_disciplina)
  );

CREATE TABLE USUARIO (
  ID_USUARIO INT NOT NULL AUTO_INCREMENT
  , ID_DISCIPLINA int 
  , NOME VARCHAR(255) NOT NULL
  , RG VARCHAR(7) UNIQUE NOT NULL
  , CPF VARCHAR(11) UNIQUE NOT NULL
  , DT_NASCIMENTO DATE NOT NULL
  , SEXO CHAR(1) NOT NULL
  , POSSUI_DEFICIENCIA BOOLEAN NOT NULL
  , CELULAR VARCHAR(11)
  , NACIONALIDADE VARCHAR(50) NOT NULL
  , TURNO ENUM('MATUTINO', 'VESPERTINO', 'NOTURNO', 'MATUTINO_E_VERSPERTINO', 'MATUTINO_E_NOTURNO', 'VESPERTINO_E_NOTURNO') NOT NULL  
  , SENHA VARCHAR(50) NOT NULL
  , TIPO ENUM('PROFESSOR', 'ALUNO', 'COORDENADOR') NOT NULL
  , ATIVO BOOLEAN NOT NULL
  , CONSTRAINT PK_USUARIO PRIMARY KEY (ID_USUARIO)
 );
 
 CREATE TABLE categoria (
  id_categoria INT NOT NULL AUTO_INCREMENT
  , id_disciplina int not null
  , ID_USUARIO INT NOT NULL
  , descricao_categoria VARCHAR(50) NOT NULL
  , constraint PK_CATEGORIA primary key (id_categoria)
  );
  
  CREATE TABLE pergunta (
  id_pergunta INT NOT NULL AUTO_INCREMENT
  , id_usuario INT NOT NULL
  , id_categoria INT NOT NULL
  , id_disciplina int not null
  , texto_pergunta TEXT NOT NULL  
  , constraint PK_PERGUNTA primary key (id_pergunta)
  , constraint FK_PERGUNTA_USUARIO foreign key (id_usuario) references usuario(id_usuario)
   , constraint FK_PERGUNTA_CATEGORIA foreign key (id_categoria) references categoria(id_categoria)
  );
  
  CREATE TABLE alternativa (
  id_alternativa INT NOT NULL AUTO_INCREMENT
  , id_pergunta INT NOT NULL
  , texto_alternativa TEXT NOT NULL 
  , alternativa_correta ENUM('CORRETA', '- - -') NOT NULL
  , constraint PK_ALTERNATIVA primary key (id_alternativa)
  , constraint FK_ALTERNATIVA_PERGUNTA foreign key (id_pergunta) references pergunta(id_pergunta)
  );

insert into disciplina (nome_disciplina) values ('Java');
insert into disciplina (nome_disciplina) values ('Software');

INSERT INTO USUARIO (NOME, RG,  CPF, DT_NASCIMENTO, SEXO, POSSUI_DEFICIENCIA, CELULAR, NACIONALIDADE, TURNO, id_DISCIPLINA, SENHA, TIPO, ATIVO) 
	VALUES ('VILMAR', '2222222', '22222222222', '2020-05-05', 'M', false, '45987456321', 'BRASILEIRO', 'MATUTINO', 1, MD5('vilmar'), 'PROFESSOR', true);
INSERT INTO USUARIO (NOME, RG,  CPF, DT_NASCIMENTO, SEXO, POSSUI_DEFICIENCIA, CELULAR, NACIONALIDADE, TURNO, id_DISCIPLINA, SENHA, TIPO, ATIVO) 
	VALUES ('ADRIANO', '4444444', '44444444444', '2020-05-05', 'M', false, '45987456321', 'BRASILEIRO', 'MATUTINO', 1, MD5('adriano'), 'PROFESSOR', true); 
INSERT INTO USUARIO (NOME, RG,  CPF, DT_NASCIMENTO, SEXO, POSSUI_DEFICIENCIA, CELULAR, NACIONALIDADE, TURNO, id_DISCIPLINA, SENHA, TIPO, ATIVO) 
	VALUES ('PASQUINI', '5555555', '55555555555', '2020-05-05', 'M', false, '45987456321', 'BRASILEIRO', 'MATUTINO', 2, MD5('pasquini'), 'PROFESSOR', true);

INSERT INTO USUARIO (NOME, RG,  CPF, DT_NASCIMENTO, SEXO, POSSUI_DEFICIENCIA, CELULAR, NACIONALIDADE, TURNO, SENHA, TIPO, ATIVO) 
	VALUES ('KOGUT', '1111111', '11111111111', '2020-05-05', 'M', false, '45987456311', 'BRASILEIRO', 'MATUTINO', MD5('kogut'), 'COORDENADOR', true);
INSERT INTO USUARIO (NOME, RG,  CPF, DT_NASCIMENTO, SEXO, POSSUI_DEFICIENCIA, CELULAR, NACIONALIDADE, TURNO, SENHA, TIPO, ATIVO) 
	VALUES ('JOÃO', '5656565', '56565656565', '2020-05-05', 'M', false, '45987456351', 'BRASILEIRO', 'NOTURNO', MD5('joao'), 'COORDENADOR', true);

INSERT INTO USUARIO (NOME, RG,  CPF, DT_NASCIMENTO, SEXO, POSSUI_DEFICIENCIA, CELULAR, NACIONALIDADE, TURNO, SENHA, TIPO, ATIVO) 
	VALUES ('ALISON', '3333333', '33333333333', '2020-05-05', 'M', false, '45987456321', 'BRASILEIRO', 'MATUTINO', MD5('alison'), 'ALUNO', true);
INSERT INTO USUARIO (NOME, RG,  CPF, DT_NASCIMENTO, SEXO, POSSUI_DEFICIENCIA, CELULAR, NACIONALIDADE, TURNO, SENHA, TIPO, ATIVO) 
	VALUES ('RODRIGO', '6666666', '66666666666', '2020-04-05', 'M', false, '45987456321', 'BRASILEIRO', 'NOTURNO', MD5('rodrigo'), 'ALUNO', true);
INSERT INTO USUARIO (NOME, RG,  CPF, DT_NASCIMENTO, SEXO, POSSUI_DEFICIENCIA, CELULAR, NACIONALIDADE, TURNO, SENHA, TIPO, ATIVO) 
	VALUES ('ALEXANDRO', '7777777', '77777777777', '2020-03-05', 'M', false, '45987456551', 'BRASILEIRO', 'MATUTINO', MD5('alexandro'), 'ALUNO', true);
INSERT INTO USUARIO (NOME, RG,  CPF, DT_NASCIMENTO, SEXO, POSSUI_DEFICIENCIA, CELULAR, NACIONALIDADE, TURNO, SENHA, TIPO, ATIVO) 
	VALUES ('ANA CAROLINA', '8888888', '88888888888', '2020-02-05', 'F', false, '45987456521', 'BRASILEIRO', 'NOTURNO', MD5('carolina'), 'ALUNO', true);
INSERT INTO USUARIO (NOME, RG,  CPF, DT_NASCIMENTO, SEXO, POSSUI_DEFICIENCIA, CELULAR, NACIONALIDADE, TURNO, SENHA, TIPO, ATIVO) 
	VALUES ('PELÉ', '9999999', '99999999999', '2020-01-05', 'M', false, '45987956321', 'BRASILEIRO', 'MATUTINO', MD5('pele'), 'ALUNO', true);
INSERT INTO USUARIO (NOME, RG,  CPF, DT_NASCIMENTO, SEXO, POSSUI_DEFICIENCIA, CELULAR, NACIONALIDADE, TURNO, SENHA, TIPO, ATIVO) 
	VALUES ('WAGNER', '1010101', '10101010101', '2020-10-05', 'M', false, '44983456321', 'BRASILEIRO', 'NOTURNO', MD5('wagner'), 'ALUNO', true);
INSERT INTO USUARIO (NOME, RG,  CPF, DT_NASCIMENTO, SEXO, POSSUI_DEFICIENCIA, CELULAR, NACIONALIDADE, TURNO, SENHA, TIPO, ATIVO)
	VALUES ('PATETA', '1313131', '13131313131', '2020-05-05', 'M', false, '48987456321', 'BRASILEIRO', 'MATUTINO', MD5('pateta'), 'ALUNO', true);
INSERT INTO USUARIO (NOME, RG,  CPF, DT_NASCIMENTO, SEXO, POSSUI_DEFICIENCIA, CELULAR, NACIONALIDADE, TURNO, SENHA, TIPO, ATIVO)
	VALUES ('TOM', '3131313', '31313131313', '2020-05-05', 'M', false, '47987456321', 'BRASILEIRO', 'MATUTINO', MD5('tom'), 'ALUNO', true);
INSERT INTO USUARIO (NOME, RG,  CPF, DT_NASCIMENTO, SEXO, POSSUI_DEFICIENCIA, CELULAR, NACIONALIDADE, TURNO, SENHA, TIPO, ATIVO)
	VALUES ('WILL', '5151515', '51515151515', '2020-05-05', 'M', false, '46987456321', 'BRASILEIRO', 'MATUTINO', MD5('will'), 'ALUNO', true);
INSERT INTO USUARIO (NOME, RG,  CPF, DT_NASCIMENTO, SEXO, POSSUI_DEFICIENCIA, CELULAR, NACIONALIDADE, TURNO, SENHA, TIPO, ATIVO)
	VALUES ('JASON', '1919191', '19191919191', '2020-05-05', 'M', false, '42987456321', 'BRASILEIRO', 'MATUTINO', MD5('jason'), 'ALUNO', true);
INSERT INTO USUARIO (NOME, RG,  CPF, DT_NASCIMENTO, SEXO, POSSUI_DEFICIENCIA, CELULAR, NACIONALIDADE, TURNO, SENHA, TIPO, ATIVO)
	VALUES ('HEITOR', '9191919', '91919191919', '2020-05-05', 'M', false, '45777456321', 'BRASILEIRO', 'MATUTINO', MD5('heitor'), 'ALUNO', true);
INSERT INTO USUARIO (NOME, RG,  CPF, DT_NASCIMENTO, SEXO, POSSUI_DEFICIENCIA, CELULAR, NACIONALIDADE, TURNO, SENHA, TIPO, ATIVO)
	VALUES ('CELSO', '1717171', '17171717171', '2020-05-05', 'M', false, '45988456321', 'BRASILEIRO', 'MATUTINO', MD5('celso'), 'ALUNO', true);



INSERT INTO categoria (id_disciplina, id_usuario, descricao_categoria) values(1, 1, 'GIT / GITHUB');
INSERT INTO categoria (id_disciplina, id_usuario, descricao_categoria) values(1, 1, 'SELETORES');
INSERT INTO categoria (id_disciplina, id_usuario, descricao_categoria) values(1, 1, 'INTERFACES');
INSERT INTO categoria (id_disciplina, id_usuario, descricao_categoria) values(1, 1, 'EXCEÇÕES');

INSERT INTO categoria (id_disciplina, id_usuario, descricao_categoria) values(1, 2, 'ORIENTAÇÃO A OBJETOS');
INSERT INTO categoria (id_disciplina, id_usuario, descricao_categoria) values(1, 2, 'POLIMORFISMO');


INSERT INTO categoria (id_disciplina, id_usuario, descricao_categoria) values(2, 3, 'TIPOS DE SISTEMAS ');
INSERT INTO categoria (id_disciplina, id_usuario, descricao_categoria) values(2, 3, 'AMBIENTES OPERACIONAIS');


INSERT INTO pergunta (id_usuario, id_categoria, id_disciplina, texto_pergunta) values(2, 5, 1, 'O QUE É ABSTRAÇÃO?');
INSERT INTO pergunta (id_usuario, id_categoria, id_disciplina, texto_pergunta) values(1, 1, 1, 'QUAL A DIFERENÇA ENTRE GIT E GITHUB?');
INSERT INTO pergunta (id_usuario, id_categoria, id_disciplina, texto_pergunta) values(1, 1, 1, 'O QUE É UM PUSH?');
INSERT INTO pergunta (id_usuario, id_categoria, id_disciplina, texto_pergunta) values(1, 2, 1, 'QUAL A FUNÇÃO DOS SELETORES?');

INSERT INTO pergunta (id_usuario, id_categoria, id_disciplina, texto_pergunta) values(2, 6, 1, 'O QUE É POLIMORFISMO?');
INSERT INTO pergunta (id_usuario, id_categoria, id_disciplina, texto_pergunta) values(3, 8, 2, 'O QUE É AMBIENTE OPERACIONAL?');



INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('1', 'É UMA CATEGORIA DE CLASSE JAVA', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('1', 'É UM TIPO DE ATRIBUTO JAVA', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('1', 'É UMA RELAÇÃO DE "MUITOS" PARA "MUITOS"', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('1', 'É QUANDO SE "ABORDA O QUE É RELEVANTE PARA O PROJETO"', 'CORRETA');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('1', 'NENHUMA DAS ALTERNATIVAS ANTERIORES', '- - -');

INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('2', 'NENHUMA, OS DOIS SÃO A MESMA FERRAMENTA COM NOMES DIFERENTES', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('2', 'GIT É UM SOFTWARE DE VERSIONAMENTO E GITHUB É UM SITE QUE HOSPEDA REPOSITÓRIOS GIT', 'CORRETA');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('2', 'GITHUB É UM SOFTWARE DE VERSIONAMENTO E GIT É UM SITE QUE HOSPEDA REPOSITÓRIOS GIT', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('2', 'GIT É UM FRAMEWORK JAVA ENQUANTO GITHUB É UM FRAMEWORK JAVASCRIPT', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('2', 'GIT É A LINGUAGEM DE PROGRAMAÇÃO ENQUANTO GITHUB É A IDE QUE SE USA PARA CODIFICAR EM GIT', '- - -');

INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('3', 'PUSH É QUANDO ENVIAMOS PARA UM REPOSITÓRIO REMOTO AS ALTERAÇÕES FEITAS NO REPOSITÓRIO LOCAL', 'CORRETA');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('3', 'PUSH É O ERRO QUE DÁ AO FAZERMOS MERGES ENTRE DUAS BRANCHS DIFERENTES', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('3', 'CHAMAMOS DE PUSH O ATO DE TRAZER AS ALTERAÇÕS DO REPOSITÓRIO REMOTO PARA DENTRO DO REPOSITÓRIO LOCAL', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('3', 'CHAMAMOS DE PUSH O ATO DE TRAZER UM PROJETO DO REPOSITÓRIO REMOTO PARA DENTRO DO REPOSITÓRIO LOCAL', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('3', 'NENHUMA DAS ALTERNATIVAS ANTERIORES', '- - -');

INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('4', 'APRESENTAR OS DADOS DA CONSULTA EM UMA TABELA', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('4', 'AJUDAR NA IMPLEMENTAÇÃO DE INTERFACES JAVA', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('4', 'AUXILIAR NA APRESENTAÇÃO DO LAYOUT DE UMA JFRAME', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('4', 'POSSIBILITA CONSULTAS SQL MAIS COMPLEXAS DE FORMA MAIS DINÂMICA', 'CORRETA');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('4', 'INSTANCIAR OBJETOS DE CLASSES ABSTRATAS', '- - -');

INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('5', 'É UMA CATEGORIA DE CLASSE JAVA', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('5', 'É UM TIPO DE ATRIBUTO JAVA', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('5', 'É UMA RELAÇÃO DE "MUITOS" PARA "MUITOS"', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('5', 'É QUANDO TIPOS DE CLASSES MAIS ABSTRATAS REPRESENTAM O COMPORTAMENTO DAS CLASSES CONCRETAS QUE REFERENCIAM', 'CORRETA');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('5', 'NENHUMA DAS ALTERNATIVAS ANTERIORES', '- - -');

INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('6', 'É UMA CATEGORIA DE SISTEMA ORGANIZACIONAL', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('6', 'É UM TIPO DE PROCESSO INTERNO DE RH', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('6', 'É UMA ÁREA INSERIDA DENTRO DA CORPORAÇÃO', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('6', 'SÃO FATORES E CONDIÇÕES EXTERNAS QUE TÊM RELEVÂNCIA IMEDIATA PARA A ORGANIZAÇÃO', 'CORRETA');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('6', 'NENHUMA DAS ALTERNATIVAS ANTERIORES', '- - -');