package com.flat.flatmaintenance.exceptions;

public class PurchaseNotFoundException extends RuntimeException {
    public PurchaseNotFoundException(Long id){
        super("Could not find purchase" + id);
    }
}
