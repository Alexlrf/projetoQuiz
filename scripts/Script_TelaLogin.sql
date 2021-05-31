DROP DATABASE IF EXISTS DBTESTE;

CREATE DATABASE DBTESTE;

USE DBTESTE;

CREATE TABLE USUARIO (
  ID_USUARIO INT NOT NULL AUTO_INCREMENT
  , NOME VARCHAR(255) NOT NULL
  , RG VARCHAR(8) UNIQUE NOT NULL
  , CPF VARCHAR(11) UNIQUE NOT NULL
  , DT_NASCIMENTO DATE NOT NULL
  , SEXO CHAR(1) NOT NULL
  , POSSUI_DEFICIENCIA BOOLEAN NOT NULL
  , CELULAR VARCHAR(11)
  , NACIONALIDADE VARCHAR(50) NOT NULL
  , TURNO ENUM('MATUTINO', 'VESPERTINO', 'NOTURNO', 'MATUTINO_E_VERSPERTINO', 'MATUTINO_E_NOTURNO', 'VESPERTINO_E_NOTURNO') NOT NULL
  , DISCIPLINA VARCHAR(50)
  , SENHA VARCHAR(50) NOT NULL
  , TIPO ENUM('PROFESSOR', 'ALUNO', 'COORDENADOR') NOT NULL
  , CONSTRAINT PK_USUARIO PRIMARY KEY (ID_USUARIO)
 );
 
 CREATE TABLE categoria (
  id_categoria INT NOT NULL AUTO_INCREMENT
  , id_usuario int not null
  , descricao_categoria VARCHAR(50) NOT NULL
  , constraint PK_CATEGORIA primary key (id_categoria)
  );
  
  CREATE TABLE pergunta (
  id_pergunta INT NOT NULL AUTO_INCREMENT
  , id_usuario INT NOT NULL
  , id_categoria INT NOT NULL
  , texto_pergunta TEXT NOT NULL  
  , constraint PK_PERGUNTA primary key (id_pergunta)
  , constraint FK_PERGUNTA_USUARIO foreign key (id_usuario) references usuario(id_usuario)
   , constraint FK_PERGUNTA_CATEGORIA foreign key (id_categoria) references categoria(id_categoria)
  );
  
  CREATE TABLE alternativa (
  id_alternativa INT NOT NULL AUTO_INCREMENT
  , id_pergunta INT NOT NULL
  , texto_alternativa TEXT NOT NULL 
  , alternativa_correta ENUM('CORRETA', 'ERRADA') NOT NULL
  , constraint PK_ALTERNATIVA primary key (id_alternativa)
  , constraint FK_ALTERNATIVA_PERGUNTA foreign key (id_pergunta) references pergunta(id_pergunta)
  );

INSERT INTO USUARIO (NOME, RG,  CPF, DT_NASCIMENTO, SEXO, POSSUI_DEFICIENCIA, CELULAR, NACIONALIDADE, TURNO, DISCIPLINA, SENHA, TIPO) 
	VALUES ('VILMAR', '1234567', '12345678987', '2020-05-05', 'M', false, '45987456321', 'BRASILEIRO', 'MATUTINO', 'DESKTOP', MD5('vilmar'), 'PROFESSOR');

INSERT INTO USUARIO (NOME, RG,  CPF, DT_NASCIMENTO, SEXO, POSSUI_DEFICIENCIA, CELULAR, NACIONALIDADE, TURNO, SENHA, TIPO) 
	VALUES ('kOGUT', '3216547', '98765432123', '2020-05-05', 'M', false, '45987456321', 'BRASILEIRO', 'MATUTINO', MD5('kogut'), 'COORDENADOR');

INSERT INTO USUARIO (NOME, RG,  CPF, DT_NASCIMENTO, SEXO, POSSUI_DEFICIENCIA, CELULAR, NACIONALIDADE, TURNO, SENHA, TIPO) 
	VALUES ('ALISON', '4567891', '12345678978', '2020-05-05', 'M', false, '45987456321', 'BRASILEIRO', 'MATUTINO', MD5('alison'), 'ALUNO');

INSERT INTO categoria (id_usuario, descricao_categoria) values(1, 'ORIENTAÇÃO A OBJETOS');
INSERT INTO categoria (id_usuario, descricao_categoria) values(1, 'GIT / GITHUB');
INSERT INTO categoria (id_usuario, descricao_categoria) values(1, 'SELETORES');
INSERT INTO categoria (id_usuario, descricao_categoria) values(1, 'INTERFACES');
INSERT INTO categoria (id_usuario, descricao_categoria) values(1, 'EXCEÇÕES');

INSERT INTO pergunta (id_usuario, id_categoria, texto_pergunta) values(1, 1, 'O QUE É ABSTRAÇÃO?');
INSERT INTO pergunta (id_usuario, id_categoria, texto_pergunta) values(1, 2, 'QUAL A DIFERENÇA ENTRE GIT E GITHUB?');
INSERT INTO pergunta (id_usuario, id_categoria, texto_pergunta) values(1, 2, 'O QUE É UM PUSH?');
INSERT INTO pergunta (id_usuario, id_categoria, texto_pergunta) values(1, 3, 'QUAL A FUNÇÃO DOS SELETORES?');

INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('1', 'É UMA CATEGORIA DE CLASSE JAVA', 'ERRADA');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('1', 'É UM TIPO DE ATRIBUTO JAVA', 'ERRADA');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('1', 'É UMA RELAÇÃO DE "MUITOS" PARA "MUITOS"', 'ERRADA');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('1', 'É QUANDO SE "ABORDA O QUE É RELEVANTE PARA O PROJETO"', 'CORRETA');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('1', 'NENHUMA DAS ALTERNATIVAS ANTERIORES', 'ERRADA');

INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('2', 'NENHUMA, OS DOIS SÃO A MESMA FERRAMENTA COM NOMES DIFERENTES', 'ERRADA');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('2', 'GIT É UM SOFTWARE DE VERSIONAMENTO E GITHUB É UM SITE QUE HOSPEDA REPOSITÓRIOS GIT', 'CORRETA');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('2', 'GITHUB É UM SOFTWARE DE VERSIONAMENTO E GIT É UM SITE QUE HOSPEDA REPOSITÓRIOS GIT', 'ERRADA');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('2', 'GIT É UM FRAMEWORK JAVA ENQUANTO GITHUB É UM FRAMEWORK JAVASCRIPT', 'ERRADA');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('2', 'GIT É A LINGUAGEM DE PROGRAMAÇÃO ENQUANTO GITHUB É A IDE QUE SE USA PARA CODIFICAR EM GIT', 'ERRADA');

INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('3', 'PUSH É QUANDO ENVIAMOS PARA UM REPOSITÓRIO REMOTO AS ALTERAÇÕES FEITAS NO REPOSITÓRIO LOCAL', 'CORRETA');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('3', 'PUSH É O ERRO QUE DÁ AO FAZERMOS MERGES ENTRE DUAS BRANCHS DIFERENTES', 'ERRADA');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('3', 'CHAMAMOS DE PUSH O ATO DE TRAZER AS ALTERAÇÕS DO REPOSITÓRIO REMOTO PARA DENTRO DO REPOSITÓRIO LOCAL', 'ERRADA');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('3', 'CHAMAMOS DE PUSH O ATO DE TRAZER UM PROJETO DO REPOSITÓRIO REMOTO PARA DENTRO DO REPOSITÓRIO LOCAL', 'ERRADA');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('3', 'NENHUMA DAS ALTERNATIVAS ANTERIORES', 'ERRADA');

INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('4', 'APRESENTAR OS DADOS DA CONSULTA EM UMA TABELA', 'ERRADA');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('4', 'AJUDAR NA IMPLEMENTAÇÃO DE INTERFACES JAVA', 'ERRADA');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('4', 'AUXILIAR NA APRESENTAÇÃO DO LAYOUT DE UMA JFRAME', 'ERRADA');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('4', 'POSSIBILITA CONSULTAS SQL MAIS COMPLEXAS DE FORMA MAIS DINÂMICA', 'CORRETA');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('4', 'INSTANCIAR OBJETOS DE CLASSES ABSTRATAS', 'ERRADA');

select 
	distinct(usuario.id_usuario), categoria.descricao_categoria, pergunta.texto_pergunta
    , alternativa.texto_alternativa, alternativa.alternativa_correta
from 
	usuario
 inner join
	pergunta on usuario.id_usuario = pergunta.id_usuario
 inner join
    categoria on categoria.id_categoria = pergunta.id_categoria
 inner join
    alternativa on alternativa.id_pergunta = pergunta.id_pergunta;
