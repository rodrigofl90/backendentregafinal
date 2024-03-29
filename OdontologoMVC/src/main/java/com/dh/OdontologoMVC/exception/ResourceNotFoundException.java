package com.dh.OdontologoMVC.exception;

public class ResourceNotFoundException extends RuntimeException{
    //creamos nuestra propia excepción y la extendemos
    public ResourceNotFoundException(String message) {
            super(message);
        }
    }

