package com.company.studio.database;

import java.util.Date;

public class Garant {
    private int idgarant;
    private int catalog_idcatalog;
    private Date sale_date;

    public Garant(int idgarant, int catalog_idcatalog, Date sale_date) {
        this.idgarant = idgarant;
        this.catalog_idcatalog = catalog_idcatalog;
        this.sale_date = sale_date;
    }

    public Garant(){

    }

    public int getIdgarant() {
        return idgarant;
    }

    public void setIdgarant(int idgarant) {
        this.idgarant = idgarant;
    }

    public int getCatalog_idcatalog() {
        return catalog_idcatalog;
    }

    public void setCatalog_idcatalog(int catalog_idcatalog) {
        this.catalog_idcatalog = catalog_idcatalog;
    }

    public Date getSale_date() {
        return sale_date;
    }

    public void setSale_date(Date sale_date) {
        this.sale_date = sale_date;
    }
}
