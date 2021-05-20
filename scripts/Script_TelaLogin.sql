SET GLOBAL log_bin_trust_function_creators = 1;

DROP DATABASE IF EXISTS DBTESTE;

CREATE DATABASE DBTESTE;

USE DBTESTE;

CREATE TABLE usuario (   
  id_usuario INT NOT NULL AUTO_INCREMENT  
  , login VARCHAR(20) NOT NULL  
  , senha VARCHAR(50) NOT NULL 
  , tipo enum('PROFESSOR', 'ALUNO', 'COORDENACAO') NOT NULL
  , constraint PK_USUARIO primary key (id_usuario)
 ); 
 
 CREATE TABLE categoria (   
  id_categoria INT NOT NULL AUTO_INCREMENT
  , descricao_categoria VARCHAR(50) NOT NULL 
  , constraint PK_CATEGORIA primary key (id_categoria)
  );
  
  CREATE TABLE pergunta (   
  id_pergunta INT NOT NULL AUTO_INCREMENT
  , id_usuario INT NOT NULL
  , id_categoria INT NOT NULL
  , texto_pergunta VARCHAR(40) NOT NULL  
  , constraint PK_PERGUNTA primary key (id_pergunta)
  , constraint FK_PERGUNTA_USUARIO foreign key (id_usuario) references usuario(id_usuario)
   , constraint FK_PERGUNTA_CATEGORIA foreign key (id_categoria) references categoria(id_categoria)
  );
  
  CREATE TABLE alternativa (   
  id_alternativa INT NOT NULL AUTO_INCREMENT
  , id_pergunta INT NOT NULL
  , texto_alternativa VARCHAR(255) NOT NULL 
  , alternativa_correta ENUM('CORRETA', 'ERRADA') NOT NULL
  , constraint PK_ALTERNATIVA primary key (id_alternativa)
  , constraint FK_ALTERNATIVA_PERGUNTA foreign key (id_pergunta) references pergunta(id_pergunta)
  );
  
  
INSERT INTO usuario (login, senha, tipo)  VALUES ('admin', MD5('admin'), 'COORDENACAO');
INSERT INTO usuario (login, senha, tipo)  VALUES ('Alison', MD5('Alison@01'), 'ALUNO');
INSERT INTO usuario (login, senha, tipo)  VALUES ('Alexandro', MD5('Alexandro&02'), 'ALUNO');
INSERT INTO usuario (login, senha, tipo)  VALUES ('Rodrigo', MD5('Rodrigo?03'), 'ALUNO');
INSERT INTO usuario (login, senha, tipo)  VALUES ('Vilmar', MD5('Vilmar*04'), 'PROFESSOR');

INSERT INTO categoria (descricao_categoria) values('ORIENTAÇÃO A OBJETOS');
INSERT INTO categoria (descricao_categoria) values('GIT / GITHUB');
INSERT INTO categoria (descricao_categoria) values('SELETORES');
INSERT INTO categoria (descricao_categoria) values('INTERFACES');
INSERT INTO categoria (descricao_categoria) values('EXCEÇÕES');

INSERT INTO pergunta (id_usuario, id_categoria, texto_pergunta) values(5, 1, 'O QUE É ABSTRAÇÃO?');
INSERT INTO pergunta (id_usuario, id_categoria, texto_pergunta) values(5, 2, 'QUAL A DIFERENÇA ENTRE GIT E GITHUB?');
INSERT INTO pergunta (id_usuario, id_categoria, texto_pergunta) values(5, 2, 'O QUE É UM PUSH?');
INSERT INTO pergunta (id_usuario, id_categoria, texto_pergunta) values(5, 3, 'QUAL A FUNÇÃO DOS SELETORES?');

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




DELIMITER $$  
 DROP FUNCTION IF EXISTS fun_valida_usuario $$

-- A função verifica se existe na base de dados alguma informação 
-- que coincida com os valores passados aos parâmetros p_login e p_senha, 
-- se existir, a função retornará o idUsuario, caso não, 0.
CREATE FUNCTION fun_valida_usuario( p_login VARCHAR(20), p_senha VARCHAR(50))  RETURNS NUMERIC(1)
	BEGIN  
		DECLARE l_ret NUMERIC(1) DEFAULT 0;
		SET l_ret = IFNULL((SELECT DISTINCT id_usuario FROM usuario  
							WHERE login = p_login  
							AND senha = MD5(p_senha)),0);    
        
		RETURN l_ret;  
	END $$
DELIMITER ;

SELECT fun_valida_usuario('admin','admin') as validacao;
SELECT fun_valida_usuario('Alison','Alison@01') as validacao;
SELECT fun_valida_usuario('Alexandro','Alexandro&02') as validacao;
SELECT fun_valida_usuario('login','senha');

