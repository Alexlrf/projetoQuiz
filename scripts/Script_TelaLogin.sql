SET GLOBAL log_bin_trust_function_creators = 1;

DROP DATABASE IF EXISTS DBTESTE;

CREATE DATABASE DBTESTE;

USE DBTESTE;

CREATE TABLE usuario (   
  idUsuario INT NOT NULL AUTO_INCREMENT,  
  login VARCHAR(20) NOT NULL,  
  senha VARCHAR(50) NOT NULL, 
  tipo enum('PROFESSOR', 'ALUNO', 'COORDENACAO') NOT NULL,
  PRIMARY KEY (idUsuario)  
 ); 
 
INSERT INTO usuario (login, senha, tipo)  VALUES ('admin', MD5('admin'), 'COORDENACAO');
INSERT INTO usuario (login, senha, tipo)  VALUES ('Alison', MD5('Alison@01'), 'ALUNO');
INSERT INTO usuario (login, senha, tipo)  VALUES ('Alexandro', MD5('Alexandro&02'), 'ALUNO');
INSERT INTO usuario (login, senha, tipo)  VALUES ('Rodrigo', MD5('Rodrigo?03'), 'ALUNO');
INSERT INTO usuario (login, senha, tipo)  VALUES ('Vilmar', MD5('Vilmar*04'), 'PROFESSOR');

DELIMITER $$  
 DROP FUNCTION IF EXISTS fun_valida_usuario $$

-- A função verifica se existe na base de dados alguma informação 
-- que coincida com os valores passados aos parâmetros p_login e p_senha, 
-- se existir, a função retornará o idUsuario, caso não, 0.
CREATE FUNCTION fun_valida_usuario( p_login VARCHAR(20), p_senha VARCHAR(50))  RETURNS NUMERIC(1)
	BEGIN  
		DECLARE l_ret NUMERIC(1) DEFAULT 0;
		SET l_ret = IFNULL((SELECT DISTINCT idUsuario FROM usuario  
							WHERE login = p_login  
							AND senha = MD5(p_senha)),0);    
        
		RETURN l_ret;  
	END $$
DELIMITER ;

SELECT fun_valida_usuario('admin','admin') as validacao;
SELECT fun_valida_usuario('Alison','Alison@01') as validacao;
SELECT fun_valida_usuario('Alexandro','Alexandro&02') as validacao;
SELECT fun_valida_usuario('login','senha');

