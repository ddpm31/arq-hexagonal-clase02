package com.example.application;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductService {

    public String getProduct(){

        //Implementación de la lógica para obtener productos
        return "Listado de productos";

    }

}
