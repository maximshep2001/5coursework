package com.company.studio.database;

public class Opros {
    private int idopros;
    private String option1;
    private String option2;
    private String option3;

    public Opros(int idopros, String option1, String option2, String  option3) {
        this.idopros = idopros;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
    }

    public Opros(){}

    public int getIdopros() {
        return idopros;
    }

    public void setIdopros(int idopros) {this.idopros = idopros;}

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

}
