
create database db_quiz;

use db_quiz;

create table usuario (
id_usuario int not null auto_increment 
, nome_usuario varchar(50) not null
, categoria varchar(15) not null
, constraint pk_usuario primary key(id_usuario)
);

create table quiz (
id_quiz int not null auto_increment
, id_usuario int not null
, nome_quiz varchar(20) not null
, constraint pk_quiz primary key(id_quiz)
, constraint fk_quiz_usuario foreign key(id_usuario) references usuario(id_usuario)
);

create table questao (
id_questao int not null auto_increment
, id_quiz int not null
, questao int not null
, pergunta varchar(200) not null
, constraint pk_questao primary key(id_questao)
, constraint fk_questao_quiz foreign key(id_quiz) references quiz(id_quiz)
);

create table resposta (
id_resposta int not null auto_increment
, id_questao int not null
, pergunta int not null
, resposta varchar(200) not null
, constraint pk_resposta primary key(id_resposta)
, constraint fk_resposta_questao foreign key(id_questao) references questao(id_questao)
);