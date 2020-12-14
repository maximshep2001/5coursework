package com.company.studio.command;

import com.company.studio.connection.MonoThreadClientHandler;
import com.company.studio.database.Catalog;
import com.company.studio.database.DataBaseHandler;
import com.company.studio.database.Solvency;
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

public abstract class UserCommand extends MonoThreadClientHandler {

    public UserCommand(Socket client) throws IOException {
        super(client);
    }

    public static void buyGoods(String iduser) throws IOException, SQLException, ClassNotFoundException {
        Integer ostatok = Integer.parseInt(get());

        String str = get();
        System.out.println(str);
        String date1 = get();
        Date date = Date.valueOf(date1);


        JSONObject data = new JSONObject();
        int summ = 0;
        int ost = 0;
        String paid = "yes";
        String bron = "no";

        assert str != null;
        JSONObject json = new JSONObject(str);
        JSONArray array = json.getJSONArray("goods");
        for (int i = 0; i < array.length(); i++) {
            int idcatalog = array.getJSONObject(i).getInt("idcatalog");
            String material = array.getJSONObject(i).getString("material");
            int sale_value = array.getJSONObject(i).getInt("sale_value");

        }
        int j = 0;
        int sale_value[] = new int[array.length()];
        int idcatalog[] = new int[array.length()];
        String material[] = new String[array.length()];
        for (int i = 0; i < array.length(); i++) {
            idcatalog[i] = array.getJSONObject(i).getInt("idcatalog");
            sale_value[i] = array.getJSONObject(i).getInt("sale_value");
            material[i] = array.getJSONObject(i).getString("material");
        }
        for (int i = 0; i < array.length(); i++) {
            System.out.println(idcatalog[i] + "   " + sale_value[i] + "   " + material[i]);
            summ+=sale_value[i];
        }
        //System.out.println("Summa is "+summ);
        //System.out.println("Ostatok is "+ostatok);
        //System.out.println("Result is "+(ostatok-summ));
        if (summ > ostatok)
            send("limit");
        else {
            send("ok");
            String getCash = updateCash(ostatok, summ).toString();
            send(getCash);
            for (int i = 0; i < array.length(); i++) {
                ToGarantTable(idcatalog[i],  date);
                ToOrderTable(idcatalog[i], date, Integer.parseInt(iduser), paid, bron);
            }
        }
    }

    public static void ToOrderTable(Integer idcatalog, Date date, Integer iduser, String paid, String bron){
        DataBaseHandler handler = new DataBaseHandler();
        handler.addNewOrder(idcatalog, date, iduser, paid, bron);

    }

    public static void ToGarantTable(Integer idcatalog, Date date) {
        DataBaseHandler handler = new DataBaseHandler();
        handler.addNewCatalogToGarant(idcatalog, date);
    }

    public static Integer updateCash(Integer ostatok, Integer summ) throws SQLException, IOException, ClassNotFoundException {

        DataBaseHandler handler = new DataBaseHandler();
        ResultSet result = handler.getUserCashByOstatok(ostatok);
        int cash = result.getInt(2);
        int userid = result.getInt(3);
        //int newCash = cash;

        Solvency solvency = new Solvency(userid, ostatok-summ);

        handler.updateBalance(solvency);
        int upCash = ostatok-summ;
        //int upCash = valueOf(cash);
        //send(upCash);
        //System.out.println(ostatok+"   "+upCash);
    return upCash;
    }

    public static void getUserId() throws IOException {
        String userPhone;
        String userId = null;
        userPhone = get();

        DataBaseHandler handler = new DataBaseHandler();

        ResultSet result = handler.getUser(userPhone);

        int counter = 0;
        try {
            while (result.next()) {
                counter++;
                userId = result.getString(1);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        if(counter>=1){
            send(userId);
        }
    }

    public static void getCompanyInformation() throws SQLException, IOException {
        DataBaseHandler handler = new DataBaseHandler();
        ResultSet result = handler.getCompanyInformation();

        String paragraph;

        paragraph = result.getString(2);

        send(paragraph);
    }

    public static void updateUserCash() throws SQLException, ClassNotFoundException, IOException {
        Integer cash;

        String idUser = get();
        int userid = Integer.parseInt(idUser);

        DataBaseHandler handler = new DataBaseHandler();
        ResultSet result = handler.getUserCash(userid);

        cash = result.getInt(2);

        String newCash = get();
        Integer newcash = Integer.parseInt(newCash);

        if(cash+10000>=newcash) {

            cash += newcash;

            Solvency solvency = new Solvency(userid, cash);

            handler.updateBalance(solvency);

            String upCash = String.valueOf(cash);
            send(upCash);
        }
        else {
            send("wrong");
        }
    }

    public static void addToOpros() {
        String option1;
        String option2;
        String option3;

        JSONObject opros = new JSONObject(get());
        option1 = opros.getString("option1");
        option2 = opros.getString("option2");
        option3 = opros.getString("option3");

        System.out.println("я получил строку: " + opros);
            DataBaseHandler handler = new DataBaseHandler();
            handler.addToOpros( option1, option2, option3 );
    }

}
