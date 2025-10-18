package com.example.Inventario_Farmacia.model;

public class Product {

    //Atributos
    private int ID;
    private String code;
    private String name;
    private int id_categoria;
    private int stock;
    private boolean active;

    //Constructor
    public Product(int ID, String code, String name, int idCategoria, int stock, boolean active) {
        this.ID = ID;
        this.code = code;
        this.name = name;
        this.id_categoria = idCategoria;
        this.stock = stock;
        this.active = active;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdCategoria() {
        return id_categoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.id_categoria = idCategoria;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
