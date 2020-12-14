package com.company.studio.command;

import com.company.studio.connection.MonoThreadClientHandler;
import com.company.studio.database.Catalog;
import com.company.studio.database.DataBaseHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.Socket;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import static java.lang.Integer.valueOf;

public abstract class AdminCommand extends MonoThreadClientHandler {

    public AdminCommand(Socket client) throws IOException {
        super(client);
    }

    public static void sendCompanyInfo() throws SQLException, IOException {
        DataBaseHandler handler = new DataBaseHandler();
        ResultSet result = handler.getCompanyInfo();

        if (result!=null){
        String paragraph;
        paragraph = result.getString(2);
        send(paragraph);
        }
        else send("Пустое поле, его следует заполнить бы!");
    }

    public static void updateCompanyInfo() throws SQLException, ClassNotFoundException {
        String paragraph = get();
        System.out.println(paragraph);
        DataBaseHandler handler = new DataBaseHandler();
        handler.updateCompanyInfo(paragraph);
    }

    public static void sendUsersData() {
        DataBaseHandler handler = new DataBaseHandler();
        String users = handler.getUsers();
        try {
            send(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteUser() throws SQLException, ClassNotFoundException {
        int id = Integer.parseInt(Objects.requireNonNull(get()));

        DataBaseHandler handler = new DataBaseHandler();

        handler.deleteUser(id);
    }

    public static void addProduct() {
        String prod;
        prod = get();
        if (prod != null) {
            System.out.println("я получил продукт: " + prod);

            String[] s = prod.split(" ");
            String productname = s[0];
            String material = s[1];
            String cost_price = s[2];

            DataBaseHandler handler = new DataBaseHandler();
            handler.addProduct(productname, material, cost_price);
        }
    }

    public static void getNewProduct() throws SQLException, IOException {
        DataBaseHandler handler = new DataBaseHandler();
        ResultSet result = handler.getNewProduct();

        int id;
        String productname;
        String material;
        String cost_price;

        id = result.getInt(1);
        String idproduct = String.valueOf(id);
        productname = result.getString(2);
        material = result.getString(3);
        cost_price = result.getString(4);

        send(idproduct);
        send(productname);
        send(material);
        send(cost_price);
    }

    public static void getOpros(){
        DataBaseHandler handler = new DataBaseHandler();
        String opros = handler.getOpros();
        try {
            send(opros);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendProductsData(){
        DataBaseHandler handler = new DataBaseHandler();
        String prod = handler.getProduct();
        try {
            send(prod);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteProduct() throws SQLException, ClassNotFoundException {
        int id = Integer.parseInt(Objects.requireNonNull(get()));

        DataBaseHandler handler = new DataBaseHandler();

        handler.deleteProduct(id);
    }

    public static void deleteCatalog() throws SQLException, ClassNotFoundException {
        int id = Integer.parseInt(Objects.requireNonNull(get()));

        DataBaseHandler handler = new DataBaseHandler();

        handler.deleteCatalog(id);
    }

    public static void addProductToCatalog(){
        String sale_value;
        String idproduct;
        sale_value = get();
        idproduct = get();

        DataBaseHandler handler = new DataBaseHandler();
        handler.addNewProductToCatalog(sale_value, idproduct);
    }

    public static void getNewCatalog() throws SQLException, IOException {
        DataBaseHandler handler = new DataBaseHandler();
        ResultSet result = handler.getNewCatalog();

        int id;
        String nameproduct;
        String material;
        int intsale_value;
        int intcost_price;

        id = result.getInt(1);
        String idcatalog = String.valueOf(id);
        nameproduct = result.getString(2);
        material = result.getString(3);
        intcost_price = result.getInt(4);
        String cost_price = String.valueOf(intcost_price);
        intsale_value = result.getInt(5);
        String sale_value = String.valueOf(intsale_value);

        send(idcatalog);
        send(nameproduct);
        send(material);
        send(cost_price);
        send(sale_value);
    }

    public static void sendCatalogData(){
        DataBaseHandler handler = new DataBaseHandler();
        String catalog = handler.getCatalog();
        try {
            send(catalog);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendHistoryData(String id){
        DataBaseHandler handler = new DataBaseHandler();
        String history = handler.getHistory(id);
        try {
            send(history);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getStatistics() throws IOException {
        String stat;

        stat = get();

        if (stat != null) {
            System.out.println("я получил статистику: " + stat);

            JSONObject ob = new JSONObject(stat);
            String year = ob.getString("year");
            String revenue = ob.getString("revenue");
            String expenses = ob.getString("expenses");
            String profit = ob.getString("profit");
            System.out.println(revenue);

            DataBaseHandler handler = new DataBaseHandler();
            handler.statisticInput(year,revenue, expenses, profit);

            send(year);
            send(revenue);
            send(expenses);
            send(profit);
        }
    }

    public static void sendStatData(){
        DataBaseHandler handler = new DataBaseHandler();
        String stat = handler.getStatistic();
        try {
            send(stat);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendUserInformation(String uid) throws SQLException, IOException {
        Integer uId = Integer.parseInt(uid);
        DataBaseHandler handler = new DataBaseHandler();
        String userInformation = handler.getUserInformation(uId);
    }

    public static void getSingleSolution(int number) {
        try {
            String task;
            task = get();
            String str = get();

            JSONObject data = new JSONObject();

            assert str != null;
            JSONObject json = new JSONObject(str);
            JSONArray array = json.getJSONArray("TaskData");
            for (int i = 0; i<array.length(); i++) {
                int  idcatalog = array.getJSONObject(i).getInt("idcatalog");
                String productname = array.getJSONObject(i).getString("productname");
                String  material = array.getJSONObject(i).getString("material");
                int cost_price = array.getJSONObject(i).getInt("cost_price");
                int sale_value = array.getJSONObject(i).getInt("sale_value");
            }

            assert task != null;
            if (!task.equals("back")) {
                JSONObject taskJson = new JSONObject(task);

                int amount1 = taskJson.getInt("AmountPr1");
                int amount2 = taskJson.getInt("AmountPr2");
                int amount3 = taskJson.getInt("AmountPr3");
                float prognozfirmyblago = taskJson.getFloat("PrognozFirmyBlago")/100;
                float prognozfirmyneblago = taskJson.getFloat("PrognozFirmyNeblago")/100;
                float prognozblago = taskJson.getFloat("PrognozBlago")/100;
                float neprognozblago = 1-prognozblago;
                float neprognozfirmyblago = 1-prognozfirmyblago;
                float neprognozfirmyneblago = 1- prognozfirmyneblago;
                data.put("prognozblago",prognozblago);
                data.put("neprognozblago",neprognozblago);
                data.put("prognozfirmyblago",prognozfirmyblago);
                data.put("neprognozfirmyblago",neprognozfirmyblago);
                data.put("neprognozfirmyneblago",neprognozfirmyneblago);
                data.put("prognozfirmyneblago",prognozfirmyneblago);


                int idcatalog1 = array.getJSONObject(0).getInt("idcatalog");
                int idcatalog2 = array.getJSONObject(1).getInt("idcatalog");
                int idcatalog3 = array.getJSONObject(2).getInt("idcatalog");

                int cost_price1 = array.getJSONObject(0).getInt("cost_price");
                int cost_price2 = array.getJSONObject(1).getInt("cost_price");
                int cost_price3 = array.getJSONObject(2).getInt("cost_price");

                int sale_value1 = array.getJSONObject(0).getInt("sale_value");
                int sale_value2 = array.getJSONObject(1).getInt("sale_value");
                int sale_value3 = array.getJSONObject(2).getInt("sale_value");

                int blago1 = (sale_value1-cost_price1)*amount1;
                int blago2 = (sale_value2-cost_price2)*amount2;
                int blago3 = (sale_value3-cost_price3)*amount3;

                int neblago1 = cost_price1*amount1;
                int neblago2 = cost_price2*amount2;
                int neblago3 = cost_price3*amount3;

                float a1 = (float)(blago1-neblago1)/2;
                float a2 = (float)(blago2-neblago2)/2;
                float a3 = (float)(blago3-neblago3)/2;

                float a4 = (float)blago1*prognozfirmyblago-(float)neblago1*(1-prognozfirmyblago);
                float a5 = (float)blago2*prognozfirmyblago-(float)neblago2*(1-prognozfirmyblago);
                float a6 = (float)blago3*prognozfirmyblago-(float)neblago3*(1-prognozfirmyblago);

                float a7 = (float)blago1*(1-prognozfirmyneblago)-(float)neblago1*prognozfirmyneblago;
                float a8 = (float)blago2*(1-prognozfirmyneblago)-(float)neblago2*prognozfirmyneblago;
                float a9 = (float)blago3*(1-prognozfirmyneblago)-(float)neblago3*prognozfirmyneblago;

                //System.out.println("a1 = "+a1+ "\na2 = "+a2 + "\na3 = "+a3+"\na4 = "+a4+"\na5 = "+a5+
                //       "\na6 = "+a6+"\na7 = "+a7+"\na8 = "+a8+"\na9 = "+a9);
                data.put("blago1",blago1);
                data.put("blago2",blago2);
                data.put("blago3",blago3);
                data.put("neblago1",neblago1);
                data.put("neblago2",neblago2);
                data.put("neblago3",neblago3);
                data.put("a4",a4);
                data.put("a5",a5);
                data.put("a6",a6);
                data.put("a7",a7);
                data.put("a8",a8);
                data.put("a9",a9);

                float max1 = a1;
                if(a2>max1)
                    max1 = a2;
                if (a3>max1)
                    max1 = a3;
                //System.out.println("max1 = "+max1);

                float max2 = a4;
                if(a5>max2)
                    max2 = a5;
                if (a6>max2)
                    max2 = a6;
                //System.out.println("max2 = "+max2);

                float max3 = a7;
                if(a8>max3)
                    max3 = a8;
                if (a9>max3)
                    max3 = a9;

                data.put("max3",max3);
                data.put("max2",max2);

                float maxbl=1;
                String input1;
                if (max2 == max2) {
                    if (max2 == a4) {
                        maxbl=a4;
                        input1 = array.getJSONObject(0).toString();
                        send( input1/* + '\n'*/ );
                        //System.out.println("I send "+ input1);
                    }
                    if (max2 == a5) {
                        maxbl=a5;
                        input1 = array.getJSONObject(1).toString();
                        send(input1 /*+ '\n' */);

                        //System.out.println("I send "+ input1);
                    }
                    if (max2 == a6) {
                        maxbl=a6;
                        input1 = array.getJSONObject(2).toString();
                        send( input1/* + '\n' */);;

                        //System.out.println("I send "+ input1);
                    }
                }
                float maxnebl=1;
                String input2;
                if (max3 == max3) {
                    if (max3 == a7) {
                        maxnebl = a7;
                        input2 = array.getJSONObject(0).toString();
                        send(input2 /*+ '\n'*/);

                        //System.out.println("I send " + input2);
                    }
                    if (max3 == a8) {
                        maxnebl=a8;
                        input2 = array.getJSONObject(1).toString();
                        send(input2 /*+ '\n'*/);

                        //System.out.println("I send " + input2);
                    }
                    if (max3 == a9) {
                        maxnebl=a9;
                        input2 = array.getJSONObject(2).toString();
                        send(input2 /*+ '\n'*/);

                        //System.out.println("I send " + input2);
                    }
                }
                float max = maxbl*prognozblago+maxnebl*neprognozblago;
                data.put("max", max);
                if (number == 2) {
                    send(data.toString()/* + '\n'*/);

                    //System.out.println("I send " + data);
                }
            }
        }catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
}

