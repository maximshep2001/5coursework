package com.company.studio.behavior;

import java.sql.Date;

public class CatalogInformation {

    private Integer idcatalog;
    private String productname;
    private String material;
    private Integer cost_price;
    private Integer sale_value;
    private Date sale_date;

    public CatalogInformation(Integer idcatalog, String productname, String material, Integer cost_price, Integer sale_value) {
        this.idcatalog = idcatalog;
        this.productname = productname;
        this.material = material;
        this.cost_price = cost_price;
        this.sale_value = sale_value;
    }

    public CatalogInformation(String productname, String material, Integer sale_value, Date sale_date) {
        this.productname = productname;
        this.material = material;
        this.sale_value = sale_value;
        this.sale_date = sale_date;
    }

    public Integer getIdcatalog() {
        return idcatalog;
    }

    public void setIdcatalog(Integer idcatalog) {
        this.idcatalog = idcatalog;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Integer getCost_price() {
        return cost_price;
    }

    public void setCost_price(Integer cost_price) {
        this.cost_price = cost_price;
    }

    public Integer getSale_value() {
        return sale_value;
    }

    public void setSale_value(Integer sale_value) {
        this.sale_value = sale_value;
    }

    public Date getSale_date() {
        return sale_date;
    }

    public void setSale_date(Date sale_date) {
        this.sale_date = sale_date;
    }
 }
