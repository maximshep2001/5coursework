package com.company.studio.database;

public class Order {
    private int idorder;
    private int garant_idgarant;
    private int users_id;
    private String paid;
    private String bron;

    public Order(int idorder, int garant_idgarant, int users_id, String paid, String bron) {
        this.idorder = idorder;
        this.garant_idgarant = garant_idgarant;
        this.users_id = users_id;
        this.paid = paid;
        this.bron = bron;
    }

    public Order(){
    }



    public int getIdorder() {
        return idorder;
    }

    public void setIdorder(int idorder) {
        this.idorder = idorder;
    }

    public int getGarant_idgarant() {
        return garant_idgarant;
    }

    public void setGarant_idgarant(int garant_idgarant) {
        this.garant_idgarant = garant_idgarant;
    }

    public int getUsers_id() {
        return users_id;
    }

    public void setUsers_id(int users_id) {
        this.users_id = users_id;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getBron() {
        return bron;
    }

    public void setBron(String bron) {
        this.bron = bron;
    }
}
