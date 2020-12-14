package com.company.studio.database;

public class Solvency {

    private int idsolvency;
    private int cash;
    private int user_id;

    public Solvency(int idsolvency, int cash, int user_id) {
        this.idsolvency = idsolvency;
        this.cash = cash;
        this.user_id = user_id;
    }

    public Solvency(int user_id, int cash) {
        this.user_id = user_id;
        this.cash = cash;
    }

    public Solvency(){}

    public int getIdsolvency() {
        return idsolvency;
    }

    public void setIdsolvency(int idsolvency) {
        this.idsolvency = idsolvency;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUsers_id(int user_id) {
        this.user_id = user_id;
    }

}