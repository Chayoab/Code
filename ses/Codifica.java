package com.infotec.ses;

import java.util.Base64;

public class Codifica {
	
	public String codifica(String entradaOriginal) {
        String cadenaCodificada = Base64.getEncoder().encodeToString(entradaOriginal.getBytes());

        return cadenaCodificada;
     }    

public String decodifica(String cadenaCodificada) {
    byte[] bytesDecodificados = Base64.getDecoder().decode(cadenaCodificada);
    String cadenaDecodificada = new String(bytesDecodificados);
    
    return cadenaDecodificada;
} 
}
