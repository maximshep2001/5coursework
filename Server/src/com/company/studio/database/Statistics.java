package com.company.studio.database;


public class Statistics {
    private int year;
    private int revenue;
    private int expenses;
    private int profit;

    public Statistics(){}

    public Statistics(int year, int revenue, int expenses, int profit) {
        this.year = year;
        this.revenue = revenue;
        this.expenses = expenses;
        this.profit = profit;
    }


    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public int getExpenses() {
        return expenses;
    }

    public void setExpenses(int expenses) {
        this.expenses = expenses;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

}