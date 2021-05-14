
insert into usuario(nome_usuario, categoria)
		values("Jorge", "Professor"),
			  ("Jorgina", "Professora"),
              ("Jorgeval", "Professor"),
              ("Jorgevalda", "Coordenadora");
              
insert into quiz(id_usuario, nome_quiz)
		values(1, "Capitais Brasileiras"),
			  (2, "Praias de Floripa"),
              (3, "Biologia");              
              
insert into questao(id_quiz, questao, pergunta)
		values(1, 1, "Qual a capital do Paraná?"),
			  (1, 2, "Qual a capital do Tocantins?"),
              (1, 3, "Qual a capital do Amazonas?"),
              (2, 4, "Qual a praia mais ao sul da ilha?"),
              (2, 5, "Qual a praia mais ao norte da ilha?"),
			  (2, 6, "Qual a praia mais extensa da ilha?"),
              (3, 7, "Qual amaior mamífero terrestre?"),
              (3, 8, "Qual a maior floresta do Brasil?"),
              (3, 9, "Qual o maior rio do Brasil?");
              
insert into resposta(id_questao, pergunta,  resposta)
		values(1, 1, "Curitiba"),
			  (1, 2, "Palmas"),
              (1, 3, "Manaus"),
              (2, 4, "Naufragados"),
              (2, 5, "Ponta das Canas"),
			  (2, 6, "Moçambique"),
              (3, 7, "Elefante"),
              (3, 8, "Amazônica"),
              (3, 9, "Amazonas");