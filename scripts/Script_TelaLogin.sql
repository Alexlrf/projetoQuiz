SET GLOBAL log_bin_trust_function_creators = 1;

DROP DATABASE IF EXISTS DBTESTE;

CREATE DATABASE DBTESTE;

USE DBTESTE;

CREATE TABLE usuario(   
  id BIGINT NOT NULL AUTO_INCREMENT,  
  login VARCHAR(20) NOT NULL,  
  senha VARCHAR(50) NOT NULL,  
  PRIMARY KEY (`id`)  
 ); 
 
 SELECT * FROM USUARIO;
 
INSERT INTO usuario (id, login, senha)  VALUES (NULL, 'admin', MD5('admin'));
INSERT INTO usuario (id, login, senha)  VALUES (NULL, 'Alison', MD5('Alison@01'));
INSERT INTO usuario (id, login, senha)  VALUES (NULL, 'Alexandro', MD5('Alexandro&02'));
INSERT INTO usuario (id, login, senha)  VALUES (NULL, 'Rodrigo', MD5('Rodrigo?03'));
INSERT INTO usuario (id, login, senha)  VALUES (NULL, 'Vilmar', MD5('Vilmar*04'));

DELIMITER $$  
-- DROP FUNCTION IF EXISTS fun_valida_usuario $$

-- A função verifica se existe na base dados alguma informação 
-- que coincida com os valores passados aos parâmetros p_login e p_senha, 
-- se existir, a função retornará o valor 1 (Um), caso não, 0 (Zero).
CREATE FUNCTION fun_valida_usuario( p_login VARCHAR(20), p_senha VARCHAR(50))  RETURNS NUMERIC(1)
	BEGIN  
		DECLARE l_ret NUMERIC(1) DEFAULT 0;  
		SET l_ret = IFNULL((SELECT DISTINCT ID FROM usuario  
							WHERE login = p_login  
							AND senha = MD5(p_senha)),0);                           
		RETURN l_ret;  
	END $$
DELIMITER ;  

SELECT fun_valida_usuario('admin','admin') as validacao;
