package com.company.studio.behavior;

import com.company.studio.connection.Connect;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public final class Product {
    public Product(String productname, String material, String cost_price) {
        this.productname = productname;
        this.material = material;
        this.cost_price = cost_price;
    }

    @Override
    public String toString() {
        return productname + ' ' + material + ' ' + cost_price;
    }

    // private int idproduct;
    private String productname;
    private String material;
    private String cost_price;

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getProductname() { return productname; }

    public void setProductname(String productname) { this.productname = productname; }

    public Product(){
        try {

            //String str = Client.getInstance().get();
            String str = Connect.get();

            JSONObject json = new JSONObject(str);

            // idproduct = json.getInt("idproduct");
            productname = json.getString("productname");
            material = json.getString("material");
            cost_price = json.getString("cost_price");

        } catch(JSONException e){
            System.err.println(e);
        }
    }

    private static Product instance;

    public static synchronized Product getInstance(){
        if(instance == null){
            instance = new Product();
        }
        return instance;
    }

  /*  public int getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(int idproduct) {
        this.idproduct = idproduct;
    }*/

    public String getCost_price() {
        return cost_price;
    }

    public void setCost_price(String cost_price) {
        this.cost_price = cost_price;
    }
}
