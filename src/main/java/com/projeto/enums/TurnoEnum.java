package com.projeto.enums;

public enum TurnoEnum {
	
	MATUTINO
	, VESPERTINO
	, NOTURNO;
	
	/**
	 * Converte o enum em String.
	 * @param turnoEnum
	 * @return
	 */
	public static TurnoEnum getTurnoEnum(String turnoEnum){
        for(TurnoEnum turno:TurnoEnum.values()){
            if(turno.toString().equals(turnoEnum.toUpperCase())){
                return turno;
            }
        }
        return null;
    }
}
