package org.web.repository;

public interface PasswordEncodeRepository {

    public String encriptar(String password) ;

    public boolean comparar(String passwordPlano, String passwordEncriptado) ;
}
