package com.company.studio.database;

public class Catalog {

    public Catalog(int idcatalog, int cost_price, int sale_value, int products_idproduct) {
        this.idcatalog = idcatalog;
        this.products_idproduct = products_idproduct;
        this.sale_value = sale_value;
        this.cost_price = cost_price;
    }

    public Catalog(int sale_value, int products_idproduct) {
        this.products_idproduct = products_idproduct;
        this.sale_value = sale_value;
        this.cost_price = cost_price;
    }

    public Catalog (){}

    /*public Catalog(int idcatalog, int sale_value) {
        this.idcatalog = idcatalog;
        this.sale_value = sale_value;
    }*/

    public int getIdcatalog() {
        return idcatalog;
    }

    public void setIdcatalog(int idcatalog) {
        this.idcatalog = idcatalog;
    }

    public int getProducts_idproduct() {
        return products_idproduct;
    }

    public void setProducts_idproduct(int products_idproduct) {
        this.products_idproduct = products_idproduct;
    }

    public int getSale_value() {
        return sale_value;
    }

    public void setSale_value(int sale_value) {
        this.sale_value = sale_value;
    }

    public int getCost_price() {
        return cost_price;
    }

    public void setCost_price(int cost_price) {
        this.cost_price = cost_price;
    }

    private int idcatalog;
    private int products_idproduct;
    private int sale_value;
    private int cost_price;
}
