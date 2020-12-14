package com.company.studio.database;

public class Products {
    private int idproduct;
    private String productname;
    private String material;
    private int cost_price;


    public Products(int idproduct, String productname, String material, int cost_price) {
        this.idproduct = idproduct;
        this.productname = productname;
        this.material = material;
        this.cost_price = cost_price;
    }

    public Products(){}

    public int getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(int idproduct) {this.idproduct = idproduct;}

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getMaterial() { return material;}

    public void setMaterial(String material) { this.material = material; }

    public int getCost_price() { return cost_price; }

    public void setCost_price(int cost_price) { this.cost_price = cost_price; }

}
