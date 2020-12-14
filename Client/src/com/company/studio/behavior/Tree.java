package com.company.studio.behavior;

import com.company.studio.connection.Connect;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Tree {
    private Float prognozfirmyblago;
    private Float neprognozfirmyblago;
    private Float neprognozfirmyneblago;
    private Float prognozfirmyneblago;
    private Integer blago1;
    private Integer neblago1;
    private Integer blago2;
    private Integer neblago2;
    private Integer blago3;
    private Integer neblago3;
    private Float a4;
    private Float a5;
    private Float a6;
    private Float a7;
    private Float a8;
    private Float a9;
    private Float max2;
    private Float max3;
    private Float prognozblago;
    private Float neprognozblago;
    private Float max;


    private static Tree instance;

    public Tree(Float prognozfirmyblago, Float neprognozfirmyblago, Float neprognozfirmyneblago,
                Float prognozfirmyneblago, Integer blago1, Integer neblago1, Integer blago2,
                Integer neblago2, Integer blago3, Integer neblago3, Float a4, Float a5,
                Float a6, Float a7, Float a8, Float a9, Float max2, Float max3,
                Float prognozblago, Float neprognozblago, Float max) {

        this.prognozfirmyblago = prognozfirmyblago;
        this.neprognozfirmyblago = neprognozfirmyblago;
        this.neprognozfirmyneblago = neprognozfirmyneblago;
        this.prognozfirmyneblago = prognozfirmyneblago;
        this.blago1 = blago1;
        this.blago2 = blago2;
        this.blago3 = blago3;
        this.neblago1 = neblago1;
        this.neblago2 = neblago2;
        this.neblago3 = neblago3;
        this.a4 = a4;
        this.a5 = a5;
        this.a6 = a6;
        this.a7= a7;
        this.a8 = a8;
        this.a9 = a9;
        this.max2 = max2;
        this.max3 = max3;
        this.prognozblago = prognozblago;
        this.neprognozblago= neprognozblago;
        this.max = max;
    }

    public static synchronized Tree getInstance(){
        if(instance == null){
            instance = new Tree();
        }
        return instance;
    }

    public Tree() {
        try {

            String str = Connect.get();

            JSONObject json = new JSONObject(str);

            prognozfirmyblago = json.getFloat("prognozfirmyblago");
            neprognozfirmyblago = json.getFloat("neprognozfirmyblago");
            neprognozfirmyneblago = json.getFloat("neprognozfirmyneblago");
            prognozfirmyneblago = json.getFloat("prognozfirmyneblago");
            blago1 = json.getInt("blago1");
            blago2 = json.getInt("blago2");
            blago3 = json.getInt("blago3");
            neblago1 = json.getInt("neblago1");
            neblago2 = json.getInt("neblago2");
            neblago3 = json.getInt("neblago3");
            a4 = json.getFloat("a4");
            a5 = json.getFloat("a5");
            a6 = json.getFloat("a6");
            a7 = json.getFloat("a7");
            a8 = json.getFloat("a8");
            a9 = json.getFloat("a9");
            max2 = json.getFloat("max2");
            max3 = json.getFloat("max3");
            prognozblago = json.getFloat("prognozblago");
            neprognozblago = json.getFloat("neprognozblago");
            max = json.getFloat("max");

        } catch(JSONException e){
            System.err.println(e);
        }
    }


    public Float getPrognozfirmyblago() { return prognozfirmyblago; }

    public void setPrognozfirmyblago(Float prognozfirmyblago) { this.prognozfirmyblago = prognozfirmyblago;  }

    public Float getNeprognozfirmyblago() { return neprognozfirmyblago; }

    public void setNeprognozfirmyblago(Float neprognozfirmyblago) { this.neprognozfirmyblago = neprognozfirmyblago; }

    public Float getNeprognozfirmyneblago() { return neprognozfirmyneblago; }

    public void setNeprognozfirmyneblago(Float neprognozfirmyneblago) { this.neprognozfirmyneblago = neprognozfirmyneblago;  }

    public Float getPrognozfirmyneblago() { return prognozfirmyneblago; }

    public void setPrognozfirmyneblago(Float prognozfirmyneblago) { this.prognozfirmyneblago = prognozfirmyneblago; }

    public Integer getBlago1() { return blago1; }

    public void setBlago1(Integer blago1) { this.blago1 = blago1;  }

    public Integer getBlago2() { return blago2; }

    public void setBlago2(Integer blago2) { this.blago2 = blago2;  }

    public Integer getBlago3() { return blago3; }

    public void setBlago3(Integer blago3) { this.blago3 = blago3;  }

    public Integer getNeblago2() { return neblago2; }

    public void setNeblago2(Integer neblago2) { this.neblago2 = neblago2; }

    public Integer getNeblago3() { return neblago3; }

    public void setNeblago3(Integer neblago3) { this.neblago3 = neblago3; }

    public Integer getNeblago1() { return neblago1; }

    public void setNeblago1(Integer neblago1) { this.neblago1 = neblago1; }

    public Float getA4() { return a4; }

    public void setA4(Float a4) { this.a4 = a4;  }

    public Float getA5() { return a5; }

    public void setA5(Float a5) { this.a5 = a5; }

    public Float getA6() { return a6; }

    public void setA6(Float a6) { this.a6 = a6; }

    public Float getA7() { return a7; }

    public void setA7(Float a7) { this.a7 = a7; }

    public Float getA8() { return a8; }

    public void setA8(Float a8) { this.a8 = a8; }

    public Float getA9() { return a9; }

    public void setA9(Float a9) { this.a9 = a9; }

    public Float getMax2() { return max2; }

    public void setMax2(Float max2) { this.max2 = max2;  }

    public Float getMax3() { return max3; }

    public void setMax3(Float max3) { this.max3 = max3; }

    public void setPrognozblago(Float prognozblago) { this.prognozblago = prognozblago;  }

    public Float getPrognozblago() { return prognozblago; }

    public void setNeprognozblago(Float neprognozblago) { this.neprognozblago = neprognozblago; }

    public Float getNeprognozblago() { return neprognozblago; }

    public void setMax(Float max) { this.max = max; }

    public Float getMax() { return max; }
}