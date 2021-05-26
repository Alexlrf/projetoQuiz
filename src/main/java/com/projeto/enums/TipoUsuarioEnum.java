package com.projeto.enums;

public enum TipoUsuarioEnum {
	PROFESSOR
	, ALUNO
	, COORDENACAO;
	
	/**
	 * Converte o enum em String.
	 * @param usuarioEnum
	 * @return
	 */
	public static TipoUsuarioEnum getTipoUsuarioEnum(String usuarioEnum){
        for(TipoUsuarioEnum usuario:TipoUsuarioEnum.values()){
            if(usuario.toString().equals(usuarioEnum.toUpperCase())){
                return usuario;
            }
        }
        return null;
    }
}
