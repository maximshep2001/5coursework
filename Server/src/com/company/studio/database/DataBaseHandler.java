package com.company.studio.database;

import com.company.studio.command.AdminCommand;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.*;
import java.util.Calendar;

public class DataBaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        String connectionSting="jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName  + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection= DriverManager.getConnection(connectionSting, dbUser, dbPass);

        return dbConnection;
    }

    public void signUpUser(String surname, String name, String phone, String email,
                           String password, String role) {
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USER_NAME + "," + Const.USER_SURNAME + ","
                + Const.USER_PHONE + "," + Const.USER_EMAIL + "," + Const.USER_PASSWORD + "," +
                Const.USER_ROLE + ")" + "VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, name);
            prSt.setString(2, surname);
            prSt.setString(3, phone);
            prSt.setString(4, email);
            prSt.setString(5, password);
            prSt.setString(6, role);
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet phoneExist(User user){
        ResultSet resSet = null;
        String select = "SELECT * FROM "+ Const.USER_TABLE + " WHERE "+ Const.USER_PHONE + "= ?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getPhone());

            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        }
        return resSet;
    }

    public ResultSet signInUser(User user){
        ResultSet resSet = null;
        String select = "SELECT * FROM "+ Const.USER_TABLE + " WHERE "+ Const.USER_PHONE + "" +
                "= ? AND " + Const.USER_PASSWORD + "= ?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getPhone());
            prSt.setString(2, user.getPassword());

            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        }
        return resSet;
    }

    public String getUsers() {
        User user;
        JSONObject userJson;
        JSONArray users = new JSONArray();
        try {

            String select = "SELECT * FROM "+Const.USER_TABLE;
            PreparedStatement prep1 = getDbConnection().prepareStatement(select);
            ResultSet rs = prep1.executeQuery();
            while (rs.next()){

                user = new User();
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setSurname(rs.getString(3));
                user.setPhone(rs.getString(4));
                user.setEmail(rs.getString(5));
                user.setPassword(rs.getString(6));
                user.setRole(rs.getString(7));


                userJson = new JSONObject();
                userJson.put("id", user.getId());
                userJson.put("name", user.getName());
                userJson.put("surname", user.getSurname());
                userJson.put("phone", user.getPhone());
                userJson.put("email", user.getEmail());
                userJson.put("password", user.getPassword());
                userJson.put("role", user.getRole());

                users.put( userJson );
            }

        } catch (SQLException | ClassNotFoundException | JSONException e) {
            e.printStackTrace();
        }

        return users.toString();
    }

    public String getUserInformation(Integer uId) {
        User user;
        Solvency solvency;
        JSONObject userJson=null;

        String select = "SELECT " +
                Const.USER_TABLE + "." + Const.USER_NAME + ", " +
                Const.USER_TABLE + "." + Const.USER_SURNAME + ", " +
                Const.USER_TABLE + "." + Const.USER_PHONE + ", " +
                Const.USER_TABLE + "." + Const.USER_EMAIL + ", " +
                Const.SOLVENCY_TABLE + "." + Const.SOLVENCY_CASH +
                " FROM " + Const.SOLVENCY_TABLE + ", " + Const.USER_TABLE +
                " WHERE " + Const.SOLVENCY_TABLE + "." + Const.SOLVENCY_USER_ID + "=" + Const.USER_TABLE + "." + Const.USER_ID +
                " AND " + Const.USER_TABLE + "." + Const.USER_ID + "=" + uId;
        PreparedStatement prSt = null;
        try {
            prSt = getDbConnection().prepareStatement(select);

            //      prSt.setInt(1, uId);

            ResultSet rs = prSt.executeQuery();
            if (rs.next()) {
                user = new User();
                solvency = new Solvency();

                user.setName(rs.getString(1));
                user.setSurname(rs.getString(2));
                user.setPhone(rs.getString(3));
                user.setEmail(rs.getString(4));
                solvency.setCash(rs.getInt(5));
                //  user.setTel(rs.getString(4));

                userJson = new JSONObject();
                userJson.put("name", user.getName());
                userJson.put("surname", user.getSurname());
                userJson.put("tel", user.getPhone());
                userJson.put("email", user.getEmail());
                userJson.put("cash", solvency.getCash());
                //System.out.println(userJson);

                String name = rs.getString(1);
                String surname = rs.getString(2);
                String phone = rs.getString(3);
                String email = rs.getString(4);
                String solv = rs.getString(5);
                String ololo = surname+" "+name+" "+email+" "+phone+" "+solv;
                AdminCommand.send(name);
                AdminCommand.send(surname);
                AdminCommand.send(phone);
                AdminCommand.send(email);
                AdminCommand.send(solv);
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return userJson.toString();
    }

    public void deleteUser(Integer userId) throws SQLException, ClassNotFoundException {
        String deletion = "DELETE FROM " + Const.USER_TABLE+ " WHERE "+ Const.USER_ID +" = ?";
        PreparedStatement prSt=getDbConnection().prepareStatement(deletion);
        prSt.setInt(1,userId);
        prSt.executeUpdate();
    }

    public String checkMail(String email) throws SQLException {
        JSONObject userJson = new JSONObject();
        User user = new User();
        String result =null;
        try {
            String select = "SELECT * FROM "+ Const.USER_TABLE + " WHERE "+ Const.USER_EMAIL +
                    "= ?";

            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, email);
            ResultSet rs = prSt.executeQuery();

            if (rs.next() == false ) {
                result = "nobody";
            } else
            { do {
                user.setEmail(rs.getString(5));
                user.setPassword(rs.getString(6));

                userJson.put("email", user.getEmail());
                userJson.put("password", user.getPassword());
            } while (rs.next());
                result = userJson.toString();
            }
        } catch (ClassNotFoundException | JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getProducts() {
        Products products;

        JSONObject productJson;
        JSONArray product = new JSONArray(  );
        try {

            String select = "SELECT * FROM "+Const.PRODUCTS_TABLE;
            PreparedStatement prep1 = getDbConnection().prepareStatement( select );
            ResultSet rs = prep1.executeQuery();
            while (rs.next()){

                products = new Products();
                products.setIdproduct(rs.getInt(1));
                products.setProductname(rs.getString(2));
                products.setMaterial(rs.getString(3));
                products.setCost_price(rs.getInt(4));

                productJson = new JSONObject();
                productJson.put("idproduct", products.getIdproduct());
                productJson.put("productname", products.getProductname());
                productJson.put("material", products.getMaterial());
                productJson.put("cost_price", products.getCost_price());

                product.put( productJson );
            }
        } catch (SQLException | ClassNotFoundException | JSONException e) {
            e.printStackTrace();
        }
        return product.toString();
    }

    public void deleteProduct(Integer productId) throws SQLException, ClassNotFoundException {
        String deletion = "DELETE FROM " + Const.PRODUCTS_TABLE + " WHERE " + Const.PRODUCT_ID + " = ?";
        PreparedStatement prSt=getDbConnection().prepareStatement(deletion);
        prSt.setInt(1,productId);
        prSt.executeUpdate();
    }

    public void deleteCatalog(Integer catalogId) throws SQLException, ClassNotFoundException {
        String deletion = "DELETE FROM " + Const.CATALOG_TABLE + " WHERE " + Const.CATALOG_ID + " = ?";
        PreparedStatement prSt=getDbConnection().prepareStatement(deletion);
        prSt.setInt(1,catalogId);
        prSt.executeUpdate();
    }

    public void addProduct(String productname, String material, String cost_price) {
        String insert = "INSERT INTO " + Const.PRODUCTS_TABLE + "(" + Const.PRODUCT_NAME + "," +
                Const.PRODUCT_MATERIAL + "," + Const.PRODUCT_COST_PRICE + ")" + "VALUES(?,?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            // prSt.setInt(1, idproduct);
            prSt.setString(1, productname);
            prSt.setString(2, material);
            prSt.setString(3, cost_price);

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addToOpros(String option1, String option2, String option3) {
        String insert = "INSERT INTO " + Const.OPROS_TABLE + "(" + Const.OPROS_OPTION1 + "," +
                Const.OPROS_OPTION2 + "," + Const.OPROS_OPTION3 + ")" + "VALUES(?,?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            // prSt.setInt(1, idproduct);
            prSt.setString(1, option1);
            prSt.setString(2, option2);
            prSt.setString(3, option3);

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getNewProduct(){
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.PRODUCTS_TABLE + " ORDER BY " + Const.PRODUCT_ID + " DESC LIMIT 1";

        try {
            PreparedStatement prSt  =  getDbConnection().prepareStatement(select);

            resSet = prSt.executeQuery();

            resSet.next();

        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        }

        return resSet;
    }

    public String getProduct() {
        Products prod;
        JSONObject productJson;
        JSONArray prods = new JSONArray();
        try {

            String select = "SELECT * FROM "+Const.PRODUCTS_TABLE;
            PreparedStatement prep = getDbConnection().prepareStatement(select);
            ResultSet rs = prep.executeQuery();
            while (rs.next()){

                prod = new Products();
                prod.setIdproduct(rs.getInt(1));
                prod.setProductname(rs.getString(2));
                prod.setMaterial(rs.getString(3));
                prod.setCost_price(rs.getInt(4));

                productJson = new JSONObject();
                productJson.put("idproduct", prod.getIdproduct());
                productJson.put("productname", prod.getProductname());
                productJson.put("material", prod.getMaterial());
                productJson.put("cost_price", prod.getCost_price());

                prods.put( productJson );
            }

        } catch (SQLException | ClassNotFoundException | JSONException e) {
            e.printStackTrace();
        }

        return prods.toString();
    }

    public String getOpros() {
        Opros opros;
        JSONObject oprosJson=null;
        Integer YES1=0, NO1=0, YES2=0, NO2=0, YES3=0, NO3=0;
        try {
            String select = "SELECT * FROM "+Const.OPROS_TABLE;
            PreparedStatement prep = getDbConnection().prepareStatement(select);
            ResultSet rs = prep.executeQuery();
            while (rs.next()){
                if (rs.getString(2).equals("yes")){
                    YES1=YES1+1;
                }
                else if (rs.getString(2).equals("no")){
                    NO1=NO1+1;
                }
                if (rs.getString(3).equals("yes")){
                    YES2=YES2+1;
                }
                else if (rs.getString(3).equals("no")){
                    NO2=NO2+1;
                }
                if (rs.getString(4).equals("yes")){
                    YES3=YES3+1;
                }
                else if (rs.getString(4).equals("no")){
                    NO3=NO3+1;
                }
            }
            oprosJson = new JSONObject();
            oprosJson.put("yes1", YES1);
            oprosJson.put("no1", NO1);
            oprosJson.put("yes2", YES2);
            oprosJson.put("no2", NO2);
            oprosJson.put("yes3", YES3);
            oprosJson.put("no3", NO3);
        } catch (SQLException | ClassNotFoundException | JSONException e) {
            e.printStackTrace();
        }
        return oprosJson.toString();
    }

    public void addNewProductToCatalog(String sale_value, String idproduct){
        String insert = "INSERT INTO " + Const.CATALOG_TABLE + " (" + Const.CATALOG_SALE_VALUE + ", " + Const.CATALOG_PRODUCT_ID + ") " +  "VALUES(?, ?)";

        try {
            //System.out.println(catalog);
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, sale_value);
            prSt.setString(2, idproduct);
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        }
    }

    public void addNewCatalogToGarant(Integer idcatalog, Date date){
        String insert = "INSERT INTO " + Const.GARANT_TABLE + "(" + Const.GARANT_SALE_DATE + "," + Const.GARANT_ID_CATALOG + ")" +  "VALUES(?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setInt(2, idcatalog);
            prSt.setDate(1, date);
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        }
    }

    public void addNewOrder(Integer idcatalog, Date date, Integer iduser, String paid, String bron){
        String insert = "INSERT `" + Const.ORDER_TABLE + "`( " + Const.ORDER_PAID + ", " + Const.ORDER_BRON + ", " +
                Const.ORDER_USERS_ID + ", " + Const.ORDER_GARANT_ID + ") " + "VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);

            prSt.setString(1, paid);
            prSt.setString(2, bron);
            prSt.setInt(3, iduser);
            prSt.setInt(4, idcatalog);


            prSt.executeUpdate();

        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        }
    }

    /*public void addNewOrder(Order order){
        String insert = "INSERT " + Const.ORDER_TABLE + "(" + Const.ORDER_USERS_ID + "," + Const.ORDER_GARANT_ID + "," +
                Const.ORDER_PAID + "," + Const.ORDER_BRON + ") " + "VALUES(?,?,?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);

            prSt.setInt(1, order.getUsers_id());
            prSt.setInt(2, order.getGarant_idgarant());
            prSt.setString(3, order.getPaid());
            prSt.setString(4, order.getBron());


            prSt.executeUpdate();

        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        }
    }*/

    public ResultSet getNewCatalog(){
        ResultSet resSet = null;
        String select = "SELECT " + Const.CATALOG_TABLE + "." + Const.CATALOG_ID + ", " +
                Const.PRODUCTS_TABLE + "." + Const.PRODUCT_NAME + ", " +
                Const.PRODUCTS_TABLE + "." + Const.PRODUCT_MATERIAL + ", " +
                Const.PRODUCTS_TABLE + "." + Const.PRODUCT_COST_PRICE + ", " +
                Const.CATALOG_TABLE + "." + Const.CATALOG_SALE_VALUE +
                " FROM " + Const.CATALOG_TABLE + ", " + Const.PRODUCTS_TABLE +
                " WHERE " + Const.CATALOG_TABLE + "." + Const.CATALOG_PRODUCT_ID + "=" +
                Const.PRODUCTS_TABLE + "." + Const.PRODUCT_ID +
                " ORDER BY "+ Const.CATALOG_TABLE + "." + Const.CATALOG_ID + " DESC LIMIT 1";
        try {
            PreparedStatement prSt  =  getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
            resSet.next();
        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        }
        return resSet;
    }

    public String getCatalog(){
        Catalog catalog;
        Products prod;
        JSONObject catalogJson;
        JSONArray catalogs = new JSONArray();

        String select = "SELECT " + Const.CATALOG_TABLE + "." + Const.CATALOG_ID + ", " +
                Const.PRODUCTS_TABLE + "." + Const.PRODUCT_NAME + ", " +
                Const.PRODUCTS_TABLE + "." + Const.PRODUCT_MATERIAL + ", " +
                Const.PRODUCTS_TABLE + "." + Const.PRODUCT_COST_PRICE + ", " +
                Const.CATALOG_TABLE + "." + Const.CATALOG_SALE_VALUE +
                " FROM " + Const.CATALOG_TABLE + ", " + Const.PRODUCTS_TABLE +
                " WHERE " + Const.CATALOG_TABLE + "." + Const.CATALOG_PRODUCT_ID + "=" +
                Const.PRODUCTS_TABLE + "." + Const.PRODUCT_ID;

        PreparedStatement prSt = null;
        try {
            prSt = getDbConnection().prepareStatement(select);

            ResultSet rs = prSt.executeQuery();

            while (rs.next()){
                catalog = new Catalog();
                prod = new Products();
                catalog.setIdcatalog(rs.getInt(1));
                prod.setProductname(rs.getString(2));
                prod.setMaterial(rs.getString(3));
                prod.setCost_price(rs.getInt(4));
                catalog.setSale_value(rs.getInt(5));

                catalogJson = new JSONObject();
                catalogJson.put("idcatalog", catalog.getIdcatalog());
                catalogJson.put("productname", prod.getProductname());
                catalogJson.put("material", prod.getMaterial());
                catalogJson.put("cost_price", prod.getCost_price());
                catalogJson.put("sale_value", catalog.getSale_value());

                catalogs.put( catalogJson );
            }
        } catch (SQLException | ClassNotFoundException | JSONException e) {
            e.printStackTrace();
        }
        return catalogs.toString();
    }

    public String getStatistic() {
        Statistics statistic;
        JSONObject statisticJson;
        JSONArray firm = new JSONArray(  );
        try {

            String select = "SELECT * FROM "+Const.STATISTIC_TABLE;
            PreparedStatement prep2 = getDbConnection().prepareStatement( select );
            ResultSet rs1 = prep2.executeQuery();
            while (rs1.next()){

                statistic = new Statistics();
                statistic.setYear(rs1.getInt(1));
                statistic.setRevenue(rs1.getInt(2));
                statistic.setExpenses(rs1.getInt(3));
                statistic.setProfit(rs1.getInt(4));

                statisticJson = new JSONObject();
                statisticJson.put("year", statistic.getYear());
                statisticJson.put("revenue", statistic.getRevenue());
                statisticJson.put("expenses", statistic.getExpenses());
                statisticJson.put("profit", statistic.getProfit());

                firm.put( statisticJson );
            }

        } catch (SQLException | ClassNotFoundException | JSONException e) {
            e.printStackTrace();
        }

        return firm.toString();
    }

    public void statisticInput(String year, String revenue, String expenses, String profit){
        String insert = "INSERT INTO " + Const.STATISTIC_TABLE + "(" + Const.STATISTIC_YEAR + ","
                + Const.STATISTIC_REVENUE + "," + Const.STATISTIC_EXPENSES + "," +
                Const.STATISTIC_PROFIT +  ")" +   "VALUES(?,?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, year);
            prSt.setString(2, revenue);
            prSt.setString(3, expenses);
            prSt.setString(4, profit);

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public String getHistory(String id){
        Integer IdUser = Integer.parseInt(id);
        Catalog catalog;
        Garant garant;
        Products prod;
        JSONObject catalogJson;
        JSONArray catalogs = new JSONArray();

        String sel = "SELECT products.productname, products.material, catalogs.sale_value, garant.sale_date \n" +
                "FROM users\n" +
                "INNER JOIN orders ON users.id = orders.users_id \n" +
                "INNER JOIN garant ON garant.idgarant = orders.garant_idgarant \n" +
                "INNER JOIN catalogs ON catalogs.idcatalog = garant.catalog_idcatalog \n" +
                "INNER JOIN products ON products.idproduct = catalogs.products_idproduct  \n" +
                "WHERE users.id =  " +IdUser;

        PreparedStatement prSt = null;
        try {
            prSt = getDbConnection().prepareStatement(sel);

            ResultSet rs = prSt.executeQuery();

            while (rs.next()){
                catalog = new Catalog();
                prod = new Products();
                garant = new Garant();
                prod.setProductname(rs.getString(1));
                prod.setMaterial(rs.getString(2));
                catalog.setSale_value(rs.getInt(3));
                garant.setSale_date(rs.getDate(4));

                catalogJson = new JSONObject();
                catalogJson.put("productname", prod.getProductname());
                catalogJson.put("material", prod.getMaterial());
                catalogJson.put("sale_value", catalog.getSale_value());
                catalogJson.put("sale_date", garant.getSale_date());

                catalogs.put( catalogJson );
            }
        } catch (SQLException | ClassNotFoundException | JSONException e) {
            e.printStackTrace();
        }
        if (!catalogs.isEmpty())
            return catalogs.toString();
        else return "null";
    }



    public ResultSet getCompanyInfo(){
        ResultSet resSet = null;

        String select = "SELECT * FROM "+Const.INFO_TABLE;

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);

            resSet = prSt.executeQuery();

            resSet.next();

        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        }
        return resSet;
    }

    public void updateCompanyInfo(String paragraph) throws SQLException, ClassNotFoundException {
        String update = "UPDATE " + Const.INFO_TABLE + " SET " + Const.INFO_CONDITIONS +
                "= ? " + "WHERE " + Const.INFO_ID + " = ?";

        PreparedStatement prSt = getDbConnection().prepareStatement(update);

        prSt.setInt(2, 1);
        prSt.setString(1, paragraph);

        prSt.executeUpdate();
        prSt.close();
    }

    public ResultSet getUser(String userPhone){
        ResultSet resSet = null;
        String select = "SELECT * FROM "+ Const.USER_TABLE + " WHERE "+ Const.USER_PHONE + "= ?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, userPhone);

            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        }
        return resSet;
    }

    public ResultSet getCompanyInformation(){
        ResultSet resSet = null;

        String select = "SELECT * FROM "+Const.INFO_TABLE;

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);

            resSet = prSt.executeQuery();

            resSet.next();

        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        }
        return resSet;
    }

    public void openCashAccount(int userId, int cash){
        String insert = "INSERT " + Const.SOLVENCY_TABLE + "(" + Const.SOLVENCY_CASH + "," + Const.SOLVENCY_USER_ID + ") " +
                "VALUES(?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);

            prSt.setInt(2, userId);
            prSt.setInt(1, cash);

            prSt.executeUpdate();

        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        }
    }

    public ResultSet getUserCash(int userId){
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.SOLVENCY_TABLE + " WHERE " + Const.SOLVENCY_USER_ID + "= ?";

        try {
            PreparedStatement prSt  =  getDbConnection().prepareStatement(select);

            prSt.setInt(1, userId);

            resSet = prSt.executeQuery();

            resSet.next();

        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        }
        return resSet;
    }

    public ResultSet getUserCashByOstatok (int ostatok){
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.SOLVENCY_TABLE + " WHERE " + Const.SOLVENCY_CASH + "= ?";

        try {
            PreparedStatement prSt  =  getDbConnection().prepareStatement(select);

            prSt.setInt(1, ostatok);

            resSet = prSt.executeQuery();

            resSet.next();

        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        }
        return resSet;
    }

    public void updateBalance(Solvency solvency) throws SQLException, ClassNotFoundException {
        String update = "UPDATE " + Const.SOLVENCY_TABLE + " SET " + Const.SOLVENCY_CASH +
                "=? " + "WHERE " + Const.SOLVENCY_USER_ID + " = ?";

        PreparedStatement prSt = getDbConnection().prepareStatement(update);

        prSt.setInt(1, solvency.getCash());/////////////////////////////////
        prSt.setInt(2, solvency.getUser_id());

        prSt.executeUpdate();
        prSt.close();
    }

}
