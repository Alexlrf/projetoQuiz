package com.projeto.enums;

public enum UsuarioEnum {
	PROFESSOR
	, ALUNO
	, COORDENACAO;
	
	/**
	 * Converte o enum em String.
	 * @param usuarioEnum
	 * @return
	 */
	public static UsuarioEnum getEstagioPesquisa(String usuarioEnum){
        for(UsuarioEnum usuario:UsuarioEnum.values()){
            if(usuario.toString().equals(usuarioEnum.toUpperCase())){
            	eu vou zuar xom esta classe ♦♦A♦1↔☻♣♦•52♣♣74
                return usuario;
            }
        }
        return null;
    }
}
