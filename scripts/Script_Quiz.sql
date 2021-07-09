set sql_safe_updates = 0;

DROP DATABASE IF EXISTS DB_QUIZ;

CREATE DATABASE DB_QUIZ;

USE DB_QUIZ;

CREATE TABLE disciplina (
  id_disciplina INT NOT NULL AUTO_INCREMENT
  , nome_disciplina varchar(50) not null 
  , constraint PK_DISCIPLINA primary key (id_disciplina)
  );

CREATE TABLE USUARIO (
  ID_USUARIO INT NOT NULL AUTO_INCREMENT
  , ID_DISCIPLINA int 
  , NOME VARCHAR(255) NOT NULL
  , RG VARCHAR(20) NOT NULL
  , CPF VARCHAR(11) UNIQUE NOT NULL
  , DT_NASCIMENTO DATE NOT NULL
  , SEXO CHAR(1) NOT NULL
  , POSSUI_DEFICIENCIA BOOLEAN NOT NULL
  , CELULAR VARCHAR(11) NOT NULL
  , TURNO ENUM('MATUTINO', 'VESPERTINO', 'NOTURNO', 'MATUTINO_E_VERSPERTINO', 'MATUTINO_E_NOTURNO', 'VESPERTINO_E_NOTURNO') NOT NULL  
  , SENHA VARCHAR(50) NOT NULL
  , TIPO ENUM('PROFESSOR', 'ALUNO', 'COORDENADOR') NOT NULL
  , ATIVO BOOLEAN NOT NULL
  , CONSTRAINT PK_USUARIO PRIMARY KEY (ID_USUARIO)
 );
 
 CREATE TABLE categoria (
  id_categoria INT NOT NULL AUTO_INCREMENT
  , id_disciplina int not null
  , id_usuario INT NOT NULL
  , ativada boolean not null
  , descricao_categoria VARCHAR(50) NOT NULL
  , constraint PK_CATEGORIA primary key (id_categoria)
  );
  
  CREATE TABLE pergunta (
  id_pergunta INT NOT NULL AUTO_INCREMENT
  , id_usuario INT NOT NULL
  , id_categoria INT NOT NULL
  , id_disciplina int not null
  , texto_pergunta TEXT NOT NULL
  , pergunta_ativada boolean not null
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
  
  create table quiz (
  id_quiz int not null auto_increment
  , id_usuario int not null 
  , constraint PK_QUIZ primary key (id_quiz)
  , constraint FK_quiz_usuario foreign key (id_usuario) references usuario(id_usuario)
  );
  
  create table prova_quiz (
  id_quiz int not null
  , id_pergunta int not null
  , id_usuario int not null  
  , constraint PK_prova_quiz primary key (id_quiz, id_pergunta)
  , constraint FK_prova_quiz_quiz foreign key (id_quiz) references quiz(id_quiz)
  , constraint FK_prova_quiz_pergunta foreign key (id_pergunta) references pergunta (id_pergunta)
  );
  
   create table resultado_prova ( 
  id_quiz int not null 
  , id_usuario int not null    
  , num_acertos int not null 
  , constraint PK_resultado_prova primary key (id_usuario, id_quiz)
  , constraint FK_resultado_prova_id_usuario foreign key (id_usuario) references usuario(id_usuario)
  , constraint FK_resultado_prova_id_quiz foreign key (id_quiz) references quiz(id_quiz)
  );
  

insert into disciplina (nome_disciplina) values ('Java');
insert into disciplina (nome_disciplina) values ('Software');

INSERT INTO USUARIO (NOME, RG,  CPF, DT_NASCIMENTO, SEXO, POSSUI_DEFICIENCIA, CELULAR, TURNO, id_DISCIPLINA, SENHA, TIPO, ATIVO) 
	VALUES ('VILMAR', '2222222', '22222222222', '2020-05-05', 'M', false, '45987456321', 'MATUTINO', 1, 'vilmar12', 'PROFESSOR', true);
INSERT INTO USUARIO (NOME, RG,  CPF, DT_NASCIMENTO, SEXO, POSSUI_DEFICIENCIA, CELULAR, TURNO, id_DISCIPLINA, SENHA, TIPO, ATIVO) 
	VALUES ('ADRIANO', '4444444', '44444444444', '2020-05-05', 'M', false, '45987456321', 'MATUTINO', 1, 'adriano1', 'PROFESSOR', true); 
INSERT INTO USUARIO (NOME, RG,  CPF, DT_NASCIMENTO, SEXO, POSSUI_DEFICIENCIA, CELULAR, TURNO, id_DISCIPLINA, SENHA, TIPO, ATIVO) 
	VALUES ('PASQUINI', '5555555', '55555555555', '2020-05-05', 'M', false, '45987456321', 'MATUTINO', 2, 'pasquini', 'PROFESSOR', true);

INSERT INTO USUARIO (NOME, RG,  CPF, DT_NASCIMENTO, SEXO, POSSUI_DEFICIENCIA, CELULAR, TURNO, SENHA, TIPO, ATIVO) 
	VALUES ('KOGUT', '1111111', '11111111111', '2020-05-05', 'M', false, '45987456311', 'MATUTINO', 'kogut123', 'COORDENADOR', true);
INSERT INTO USUARIO (NOME, RG,  CPF, DT_NASCIMENTO, SEXO, POSSUI_DEFICIENCIA, CELULAR, TURNO, SENHA, TIPO, ATIVO) 
	VALUES ('JOÃO', '5656565', '56565656565', '2020-05-05', 'M', false, '45987456351', 'NOTURNO', 'joao1234', 'COORDENADOR', true);

INSERT INTO USUARIO (NOME, RG,  CPF, DT_NASCIMENTO, SEXO, POSSUI_DEFICIENCIA, CELULAR, TURNO, SENHA, TIPO, ATIVO) 
	VALUES ('ALISON', '3333333', '33333333333', '2020-05-05', 'M', false, '45987456321', 'MATUTINO', 'alison12', 'ALUNO', true);
INSERT INTO USUARIO (NOME, RG,  CPF, DT_NASCIMENTO, SEXO, POSSUI_DEFICIENCIA, CELULAR, TURNO, SENHA, TIPO, ATIVO) 
	VALUES ('RODRIGO', '6666666', '66666666666', '2020-04-05', 'M', false, '45987456321', 'NOTURNO', 'rodrigo1', 'ALUNO', true);
INSERT INTO USUARIO (NOME, RG,  CPF, DT_NASCIMENTO, SEXO, POSSUI_DEFICIENCIA, CELULAR, TURNO, SENHA, TIPO, ATIVO) 
	VALUES ('ALEXSANDRO', '7777777', '77777777777', '2020-03-05', 'M', false, '45987456551', 'MATUTINO', 'alexsandro', 'ALUNO', true);
INSERT INTO USUARIO (NOME, RG,  CPF, DT_NASCIMENTO, SEXO, POSSUI_DEFICIENCIA, CELULAR, TURNO, SENHA, TIPO, ATIVO) 
	VALUES ('ANA CAROLINA', '8888888', '88888888888', '2020-02-05', 'F', false, '45987456521', 'NOTURNO', 'carolina', 'ALUNO', true);
INSERT INTO USUARIO (NOME, RG,  CPF, DT_NASCIMENTO, SEXO, POSSUI_DEFICIENCIA, CELULAR, TURNO, SENHA, TIPO, ATIVO) 
	VALUES ('PELÉ', '9999999', '99999999999', '2020-01-05', 'M', false, '45987956321', 'MATUTINO', 'pele1234', 'ALUNO', true);
INSERT INTO USUARIO (NOME, RG,  CPF, DT_NASCIMENTO, SEXO, POSSUI_DEFICIENCIA, CELULAR, TURNO, SENHA, TIPO, ATIVO) 
	VALUES ('WAGNER', '1010101', '10101010101', '2020-10-05', 'M', false, '44983456321', 'NOTURNO', 'wagner12', 'ALUNO', true);
INSERT INTO USUARIO (NOME, RG,  CPF, DT_NASCIMENTO, SEXO, POSSUI_DEFICIENCIA, CELULAR, TURNO, SENHA, TIPO, ATIVO)
	VALUES ('PATETA', '1313131', '13131313131', '2020-05-05', 'M', false, '48987456321', 'MATUTINO', 'pateta12', 'ALUNO', true);
INSERT INTO USUARIO (NOME, RG,  CPF, DT_NASCIMENTO, SEXO, POSSUI_DEFICIENCIA, CELULAR, TURNO, SENHA, TIPO, ATIVO)
	VALUES ('TOM', '3131313', '31313131313', '2020-05-05', 'M', false, '47987456321', 'MATUTINO', 'tom12345', 'ALUNO', true);
INSERT INTO USUARIO (NOME, RG,  CPF, DT_NASCIMENTO, SEXO, POSSUI_DEFICIENCIA, CELULAR, TURNO, SENHA, TIPO, ATIVO)
	VALUES ('WILL', '5151515', '51515151515', '2020-05-05', 'M', false, '46987456321', 'MATUTINO', 'will1234', 'ALUNO', true);
INSERT INTO USUARIO (NOME, RG,  CPF, DT_NASCIMENTO, SEXO, POSSUI_DEFICIENCIA, CELULAR, TURNO, SENHA, TIPO, ATIVO)
	VALUES ('JASON', '1919191', '19191919191', '2020-05-05', 'M', false, '42987456321', 'MATUTINO', 'jason123', 'ALUNO', true);
INSERT INTO USUARIO (NOME, RG,  CPF, DT_NASCIMENTO, SEXO, POSSUI_DEFICIENCIA, CELULAR, TURNO, SENHA, TIPO, ATIVO)
	VALUES ('HEITOR', '9191919', '91919191919', '2020-05-05', 'M', false, '45777456321', 'MATUTINO', 'heitor12', 'ALUNO', true);
INSERT INTO USUARIO (NOME, RG,  CPF, DT_NASCIMENTO, SEXO, POSSUI_DEFICIENCIA, CELULAR, TURNO, SENHA, TIPO, ATIVO)
	VALUES ('CELSO', '1717171', '17171717171', '2020-05-05', 'M', false, '45988456321', 'MATUTINO', 'celso123', 'ALUNO', true);



INSERT INTO categoria (id_disciplina, id_usuario, descricao_categoria, ativada) values(1, 1, 'GIT / GITHUB', true);
INSERT INTO categoria (id_disciplina, id_usuario, descricao_categoria, ativada) values(1, 1, 'SELETORES', true);
INSERT INTO categoria (id_disciplina, id_usuario, descricao_categoria, ativada) values(1, 1, 'INTERFACES', true);
INSERT INTO categoria (id_disciplina, id_usuario, descricao_categoria, ativada) values(1, 1, 'EXCEÇÕES', true);

INSERT INTO categoria (id_disciplina, id_usuario, descricao_categoria, ativada) values(1, 2, 'ORIENTAÇÃO A OBJETOS', true);
INSERT INTO categoria (id_disciplina, id_usuario, descricao_categoria, ativada) values(1, 2, 'POLIMORFISMO', true);


INSERT INTO categoria (id_disciplina, id_usuario, descricao_categoria, ativada) values(2, 3, 'TIPOS DE SISTEMAS ', true);
INSERT INTO categoria (id_disciplina, id_usuario, descricao_categoria, ativada) values(2, 3, 'AMBIENTES OPERACIONAIS', true);


-- Perguntas Vilmar

INSERT INTO pergunta (id_usuario, id_categoria, id_disciplina, texto_pergunta, pergunta_ativada) values(1, 1, 1, 'QUAL A DIFERENÇA ENTRE GIT E GITHUB?', true);
INSERT INTO pergunta (id_usuario, id_categoria, id_disciplina, texto_pergunta, pergunta_ativada) values(1, 1, 1, 'O QUE É UM PUSH?', true);
INSERT INTO pergunta (id_usuario, id_categoria, id_disciplina, texto_pergunta, pergunta_ativada) values(1, 2, 1, 'QUAL A FUNÇÃO DOS SELETORES?', true);

INSERT INTO pergunta (id_usuario, id_categoria, id_disciplina, texto_pergunta, pergunta_ativada) values(1, 5, 1, 'O QUE É UMA VARIÁVEL?', true);
INSERT INTO pergunta (id_usuario, id_categoria, id_disciplina, texto_pergunta, pergunta_ativada) values(1, 5, 1, 'CAMEL CASE É?', true);
INSERT INTO pergunta (id_usuario, id_categoria, id_disciplina, texto_pergunta, pergunta_ativada) values(1, 5, 1, 'EXISTE HERANÇA MULTIPLA EM JAVA?', true);
INSERT INTO pergunta (id_usuario, id_categoria, id_disciplina, texto_pergunta, pergunta_ativada) values(1, 1, 1, 'O QUE FAZ UM MERGE?', true);
INSERT INTO pergunta (id_usuario, id_categoria, id_disciplina, texto_pergunta, pergunta_ativada) values(1, 4, 1, 'TRY, CATCH, THROWS SÃO COMANDOS DE?', true);
INSERT INTO pergunta (id_usuario, id_categoria, id_disciplina, texto_pergunta, pergunta_ativada) values(1, 2, 1, 'SELETORES SÃO INTERFACES JAVA?', true);
INSERT INTO pergunta (id_usuario, id_categoria, id_disciplina, texto_pergunta, pergunta_ativada) values(1, 1, 1, 'GITHUB É UMA VARIAÇÃO DE CÓDIGO PARA USAR EM MOBILE?', true);
INSERT INTO pergunta (id_usuario, id_categoria, id_disciplina, texto_pergunta, pergunta_ativada) values(1, 5, 1, 'QUAL CLASSE É USADA PARA TRABALHAR COM TEXTOS EM JAVA?', true);
INSERT INTO pergunta (id_usuario, id_categoria, id_disciplina, texto_pergunta, pergunta_ativada) values(1, 4, 1, 'O QUE É UM BLOCO TRY/CATCH?', true);
INSERT INTO pergunta (id_usuario, id_categoria, id_disciplina, texto_pergunta, pergunta_ativada) values(1, 1, 1, 'NÃO É POSSÍVEL USAR O GIT COM JAVA', true);
INSERT INTO pergunta (id_usuario, id_categoria, id_disciplina, texto_pergunta, pergunta_ativada) values(1, 1, 1, 'NÃO É POSSÍVEL USAR O GIT COM JAVASCRIPT', true);
INSERT INTO pergunta (id_usuario, id_categoria, id_disciplina, texto_pergunta, pergunta_ativada) values(1, 5, 1, 'JAVA É UM FRAMEWORK PHP', true);
INSERT INTO pergunta (id_usuario, id_categoria, id_disciplina, texto_pergunta, pergunta_ativada) values(1, 5, 1, 'QUAL AFIRMATIVA ESTÁ CORRETA?', true);
INSERT INTO pergunta (id_usuario, id_categoria, id_disciplina, texto_pergunta, pergunta_ativada) values(1, 1, 1, 'O QUE É UM PULL ?', true);
INSERT INTO pergunta (id_usuario, id_categoria, id_disciplina, texto_pergunta, pergunta_ativada) values(1, 2, 1, 'NÃO É POSSÍVEL USAR JAVA COM BANCO DE DADOS', true);

-- Perguntas Adriano

INSERT INTO pergunta (id_usuario, id_categoria, id_disciplina, texto_pergunta, pergunta_ativada) values(2, 5, 1, 'O QUE É ABSTRAÇÃO?', true);
INSERT INTO pergunta (id_usuario, id_categoria, id_disciplina, texto_pergunta, pergunta_ativada) values(2, 6, 1, 'O QUE É POLIMORFISMO?', true);

INSERT INTO pergunta (id_usuario, id_categoria, id_disciplina, texto_pergunta, pergunta_ativada) values(2, 4, 1, 'PARA TRATAR UMA EXCEÇÃO BASTA UTILIZAR UM BLOCO DE VARIÁVEIS GLOBAIS', true);
INSERT INTO pergunta (id_usuario, id_categoria, id_disciplina, texto_pergunta, pergunta_ativada) values(2, 5, 1, 'VARIÁVEL LOCAL É AQUELA QUE TEM ESCOPO GLOBAL', true);
INSERT INTO pergunta (id_usuario, id_categoria, id_disciplina, texto_pergunta, pergunta_ativada) values(2, 5, 1, 'O QUE É UMA VARIÁVEL GLOBAL ?', true);
INSERT INTO pergunta (id_usuario, id_categoria, id_disciplina, texto_pergunta, pergunta_ativada) values(2, 5, 1, 'QUAL ALTERNATIVA ESTÁ CORRETA?', true);
INSERT INTO pergunta (id_usuario, id_categoria, id_disciplina, texto_pergunta, pergunta_ativada) values(2, 4, 1, 'O QUE É FINALLY?', true);


-- Pasquini

INSERT INTO pergunta (id_usuario, id_categoria, id_disciplina, texto_pergunta, pergunta_ativada) values(3, 8, 2, 'O QUE É AMBIENTE OPERACIONAL?', true);



INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('1', 'NENHUMA, OS DOIS SÃO A MESMA FERRAMENTA COM NOMES DIFERENTES', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('1', 'GIT É UM SOFTWARE DE VERSIONAMENTO E GITHUB É UM SITE QUE HOSPEDA REPOSITÓRIOS GIT', 'CORRETA');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('1', 'GITHUB É UM SOFTWARE DE VERSIONAMENTO E GIT É UM SITE QUE HOSPEDA REPOSITÓRIOS GIT', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('1', 'GIT É UM FRAMEWORK JAVA ENQUANTO GITHUB É UM FRAMEWORK JAVASCRIPT', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('1', 'GIT É A LINGUAGEM DE PROGRAMAÇÃO ENQUANTO GITHUB É A IDE QUE SE USA PARA CODIFICAR EM GIT', '- - -');

INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('2', 'PUSH É QUANDO ENVIAMOS PARA UM REPOSITÓRIO REMOTO AS ALTERAÇÕES FEITAS NO REPOSITÓRIO LOCAL', 'CORRETA');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('2', 'PUSH É O ERRO QUE DÁ AO FAZERMOS MERGES ENTRE DUAS BRANCHS DIFERENTES', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('2', 'CHAMAMOS DE PUSH O ATO DE TRAZER AS ALTERAÇÕS DO REPOSITÓRIO REMOTO PARA DENTRO DO REPOSITÓRIO LOCAL', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('2', 'CHAMAMOS DE PUSH O ATO DE TRAZER UM PROJETO DO REPOSITÓRIO REMOTO PARA DENTRO DO REPOSITÓRIO LOCAL', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('2', 'NENHUMA DAS ALTERNATIVAS ANTERIORES', '- - -');

INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('3', 'APRESENTAR OS DADOS DA CONSULTA EM UMA TABELA', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('3', 'AJUDAR NA IMPLEMENTAÇÃO DE INTERFACES JAVA', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('3', 'AUXILIAR NA APRESENTAÇÃO DO LAYOUT DE UMA JFRAME', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('3', 'POSSIBILITA CONSULTAS SQL MAIS COMPLEXAS DE FORMA MAIS DINÂMICA', 'CORRETA');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('3', 'INSTANCIAR OBJETOS DE CLASSES ABSTRATAS', '- - -');

INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('4', 'ISSO VARIA MUITO', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('4', 'É UM TIPO DE ATRIBUTO JAVA', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('4', 'É UMA RELAÇÃO DE "MUITOS" PARA "MUITOS"', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('4', 'É QUANDO SE "ABORDA O QUE É RELEVANTE PARA O PROJETO"', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('4', 'É UM ESPAÇO DE MEMÓRIA QUE ARMAZENARÁ UM DADO', 'CORRETA');

INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('5', 'É UMA CONVENÇÃO DE ESCRITA DE CÓDIGO USADA EM JAVA', 'CORRETA');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('5', 'É UM CASO DO CAMELO', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('5', 'É UM TIPO DE CLASSE', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('5', 'UMA COISA INVENTADA PARA COMPLICAR A VIDA DO PROGRAMADOR', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('5', 'NENHUMA DAS ALTERNATIVAS', '- - -');

INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('6', 'SOMENTE A PARTIR DA VERSÃO 8 ', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('6', 'COM CERTEZA EXISTE', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('6', 'EXISTIA ATÉ A VERSÃO 11', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('6', 'NÃO EXISTE', 'CORRETA');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('6', 'NENHUMA DAS ALTERNATIVAS ANTERIORES', '- - -');

INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('7', 'PROPORCIONA UM AUMENTO DE MEMÓRIA RAM', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('7', 'AJUDA NA IMPLEMENTAÇÃO DE INTERFACES JAVA', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('7', 'AUXILIA NA APRESENTAÇÃO DO LAYOUT DE UMA JFRAME', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('7', 'POSSIBILITA CONSULTAS SQL MAIS COMPLEXAS DE FORMA MAIS DINÂMICA', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('7', 'NENHUMA DAS ALTERNATIVAS ANTERIORES', 'CORRETA');

INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('8', 'COMANDOS EM AÇÃO', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('8', 'ATRIBUTOS JAVA', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('8', 'PERSISTÊNCIA DE DADOS MYSQL', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('8', 'TRATAMENTO DE EXCEÇÃO JAVA', 'CORRETA');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('8', 'NENHUMA DAS ALTERNATIVAS ANTERIORES', '- - -');

INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('9', 'SIM', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('9', 'CERTEZA QUE SIM', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('9', 'DEPENDE MUITO', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('9', 'NÃO', 'CORRETA');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('9', 'TODAS AFIRMATIVAS ESTÃO CORRETAS', '- - -');

INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('10', 'SOMENTE COM MAC-OS', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('10', 'SOMENTE COM LINUX', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('10', 'SOMENTE COM WINDOWS', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('10', 'AFIRMATIVA TOTALMENTE ERRADA', 'CORRETA');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('10', 'NENHUMA DAS ALTERNATIVAS ANTERIORES', '- - -');

INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('11', 'INTEGER', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('11', 'STRING', 'CORRETA');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('11', 'DOUBLE', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('11', 'UBUNTU', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('11', 'DOCKER', '- - -');

INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('12', 'É UM MODO DE TRATAMENTO DE EXCEÇÕES EM CÓDIGO JAVA', 'CORRETA');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('12', 'FAMOSO TRIO ELÉTRICO DO CARNAVAL DA BAHIA', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('12', 'CAIXA USADA EM CONSTRUÇÕES DE MUROS', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('12', 'NÃO FAÇO IDEIA', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('12', 'NENHUMA DAS ALTERNATIVAS ANTERIORES', '- - -');

INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('13', 'ERRADO, DESENVOLVEDORES PLENOS CONSEGUEM', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('13', 'NUNCA SE DEVE TENTAR ESSA UNIÃO', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('13', 'SOMENTE ESTAGIÁRIOS NÃO DEVEM USAR', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('13', 'CLARO QUE É POSSÍVEL !!!', 'CORRETA');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('13', 'FICA POR CONTA E RISCO DE QUEM SE ARRISCAR', '- - -');

INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('14', 'ERRADO, DESENVOLVEDORES PLENOS CONSEGUEM', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('14', 'NUNCA SE DEVE TENTAR ESSA UNIÃO', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('14', 'SOMENTE ESTAGIÁRIOS NÃO DEVEM USAR', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('14', 'CLARO QUE É POSSÍVEL !!!', 'CORRETA');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('14', 'FICA POR CONTA E RISCO DE QUEM SE ARRISCAR', '- - -');

INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('15', 'NÃO É ISSO, É AO CONTRÁRIO', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('15', 'SOMENTE NA VERSÃO DESKTOP', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('15', 'CERTO', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('15', 'ERRADO', 'CORRETA');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('15', 'NENHUMA DAS ALTERNATIVAS ANTERIORES', '- - -');

INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('16', 'ENCAPSULAMENTO É UMA RELAÇÃO DE "MUITOS" PARA "MUITOS"', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('16', 'CLASSE ABSTRATA É UM TIPO DE VARIÁVEL', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('16', 'MÉTODO É UM ATRIBUTO INSERIDA DENTRO DA JRE', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('16', 'STRING É UMA CLASSE JAVA', 'CORRETA');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('16', 'NENHUMA DAS ALTERNATIVAS ANTERIORES', '- - -');

INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('17', 'É QUANDO SE "ABORDA O QUE É RELEVANTE PARA O PROJETO"', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('17', 'É UM TIPO DE VARIÁVEL', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('17', 'É UM ATRIBUTO INSERIDA DENTRO DA JRE', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('17', 'TODAS AS ALTERNATIVAS ANTERIORES', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('17', 'NENHUMA DAS ALTERNATIVAS ANTERIORES', 'CORRETA');

INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('18', 'SOMENTE SE PODE COM NO RELACIONAIS', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('18', 'SOMENTE SE PODE COM RELACIONAIS', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('18', 'SOMENTE COM ORACLE DATABASE', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('18', 'SOMENTE COM SQL SERVER', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('18', 'NENHUMA DAS ALTERNATIVAS ANTERIORES', 'CORRETA');

INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('19', 'É UMA CATEGORIA DE CLASSE JAVA', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('19', 'É UM TIPO DE ATRIBUTO JAVA', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('19', 'É UMA RELAÇÃO DE "MUITOS" PARA "MUITOS"', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('19', 'É QUANDO SE "ABORDA O QUE É RELEVANTE PARA O PROJETO"', 'CORRETA');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('19', 'NENHUMA DAS ALTERNATIVAS ANTERIORES', '- - -');

INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('20', 'É UMA CATEGORIA DE CLASSE JAVA', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('20', 'É UM TIPO DE ATRIBUTO JAVA', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('20', 'É UMA RELAÇÃO DE "MUITOS" PARA "MUITOS"', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('20', 'É QUANDO TIPOS DE CLASSES MAIS ABSTRATAS REPRESENTAM O COMPORTAMENTO DAS CLASSES CONCRETAS QUE REFERENCIAM', 'CORRETA');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('20', 'NENHUMA DAS ALTERNATIVAS ANTERIORES', '- - -');

INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('21', 'MEIO CERTO', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('21', 'QUASE CERTO', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('21', 'BEM CERTO', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('21', 'MUITO ERRADO', 'CORRETA');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('21', 'TANTO FAZ', '- - -');

INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('22', 'É VERDADEIRA A AFIRMAÇÃO', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('22', 'É VERDADEIRA A AFIRMAÇÃO QUANDO SE ESTÁ NO PARADIGMA FUNCIONAL', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('22', 'SOMENTE EM UMA RELAÇÃO DE "MUITOS" PARA "MUITOS"', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('22', 'NÃO ESTÁ CORRETA A AFIRMAÇÃO', 'CORRETA');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('22', 'TODAS AS ALTERNATIVAS ANTERIORES ESTÃO CORRETAS', '- - -');

INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('23', 'É UMA VARIÁVEL QUE ESTÁ DENTRO DE UM MÉTODO', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('23', 'É UMA VARIÁVEL JAVA', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('23', 'É UMA VARIÁVEL QUE VIAJA PELO MUNDO', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('23', 'É UMA VARIÁVEL DECLARADA DE FORMA QUE POSSA SER USADA EM TODA A CLASSE', 'CORRETA');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('23', 'NENHUMA DAS ALTERNATIVAS ANTERIORES', '- - -');

INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('24', 'HERANÇA PERMITE QUE UM SISTEMA PASSE SUAS FUNCIONALIDADES PARA OUTRO SISTEMA AO SER ENCERRADO', 'CORRETA');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('24', 'UMA CLASSE JAVA PODE IMPLEMENTAR DIVERSAS INTERFACES', 'CORRETA');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('24', 'O VALOR DEFAULT DE UM INT É NULL', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('24', 'CHAMAMOS DE PUSH O ATO DE TRAZER UM PROJETO DO REPOSITÓRIO REMOTO PARA DENTRO DO REPOSITÓRIO LOCAL', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('24', 'O VALOR DEFAULT DE UM DOUBLE É NULL', '- - -');

INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('25', 'É UMA INSTÂNCIA DE UMA CLASSE ABSTRATA JAVA', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('25', 'É QUANDO UM MÉTODO BOOLEANO RETORNA UM DOUBLE', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('25', 'É UM BLOCO QUE SEMPRE SERÁ EXECUTADO NO TRATAMENTO DE EXCEÇÕES COM TRY/CATCH', 'CORRETA');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('25', 'É UMA CONSULTA EM MAIS DE UMA TABELA DO BANCO DE DADOS', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('25', 'É O NOME QUE SE DÁ AO ARQUIVO GERADO APÓS O BUILD DO PROJETO JAVA', '- - -');

INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('26', 'É UMA CATEGORIA DE SISTEMA ORGANIZACIONAL', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('26', 'É UM TIPO DE PROCESSO INTERNO DE RH', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('26', 'É UMA ÁREA INSERIDA DENTRO DA CORPORAÇÃO', '- - -');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('26', 'SÃO FATORES E CONDIÇÕES EXTERNAS QUE TÊM RELEVÂNCIA IMEDIATA PARA A ORGANIZAÇÃO', 'CORRETA');
INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) values('26', 'NENHUMA DAS ALTERNATIVAS ANTERIORES', '- - -');

INSERT INTO quiz (id_usuario) values (1);

insert into prova_quiz (id_quiz, id_usuario, id_pergunta) values (1, 2, 1);
insert into prova_quiz (id_quiz, id_usuario, id_pergunta) values (1, 2, 2);
insert into prova_quiz (id_quiz, id_usuario, id_pergunta) values (1, 2, 3);

insert into resultado_prova (id_quiz, id_usuario, num_acertos) values (1, 9, 2);
insert into resultado_prova (id_quiz, id_usuario, num_acertos) values (1, 13, 3);

