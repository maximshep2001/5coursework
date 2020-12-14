package com.company.studio.connection;

import com.company.studio.command.AdminCommand;
import com.company.studio.command.BasicCommand;
import com.company.studio.command.CheckCommand;
import com.company.studio.command.UserCommand;
import com.company.studio.database.DataBaseHandler;
import com.company.studio.database.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Objects;

public class MonoThreadClientHandler implements Runnable {
    private static DataInputStream ois;
    private static DataOutputStream oos;
    private final Socket clientDialog;
    //DataOutputStream oos;
    //DataInputStream ois;

    public MonoThreadClientHandler(Socket client) throws IOException {
        clientDialog = client;
        oos = new DataOutputStream(clientDialog.getOutputStream());
        ois = new DataInputStream(clientDialog.getInputStream());
        System.out.println("New client connected");
    }

    public static void send(String message) throws IOException {
        oos.writeUTF(message);
        System.out.println("Отправил: " + message);
        oos.flush();
    }

    public static String get(){
        try {
            return ois.readUTF();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public void run() {
        try{
            while (true) {
                String role = null;
                String command = get();
                System.out.println("Я принял: " + command);
                if(command == null || command.equals("exit")){
                    break;
                }
                switch (command){
                    case "authorization":{
                        role = BasicCommand.authorization();
                        break;
                    }
                    case "registration":{
                        BasicCommand.registration();
                        role = "null";
                        break;
                    }
                    case "forgot password":{
                        BasicCommand.forgotpassword();
                        role = "null";
                        break;
                    }
                    case "phoneExist":{
                        CheckCommand.phoneExist();
                        role = "null";
                        break;
                    }
                    case "getCompanyInfo":{
                        UserCommand.getCompanyInformation();
                        role = "null";
                        break;
                    }
                }
                switch (role){
                    case "ADMIN":{
                        openMenuAdmin();
                        break;
                    }
                    case "USER":{
                        openMenuUser();
                        break;
                    }
                    case "null": {
                        break;
                    }
                    default:break;
                }
            }
            oos.close();
            ois.close();
            clientDialog.close();

            System.out.println("Closing connections & channels - DONE.");
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void CompanyManage() throws SQLException, ClassNotFoundException {
        String menu = "work with products";
        //System.out.println(menu);
        try {
            AdminCommand.sendProductsData();
            AdminCommand.sendCatalogData();
            AdminCommand.sendCompanyInfo();
            AdminCommand.sendStatData();
                while (!menu.equals( "back" )) {
                    menu = get();
                    System.out.println(menu);
                switch (Objects.requireNonNull(menu)) {
                    case "deleteProduct":{
                        AdminCommand.deleteProduct();
                        break;
                    }
                    case "addProduct":{
                        AdminCommand.addProduct();
                        break;
                    }
                    case "getNewProduct":{
                        AdminCommand.getNewProduct();
                        break;
                    }
                    case "updateCompanyInfo":{
                        AdminCommand.updateCompanyInfo();
                        break;
                    }
                    case "addProductToCatalog": {
                        AdminCommand.addProductToCatalog();
                        break;
                    }
                    case "getNewCatalog": {
                        AdminCommand.getNewCatalog();
                        break;
                    }
                    case "getOpros":{
                        AdminCommand.getOpros();
                        break;
                    }
                    case "getStatistics":{
                        AdminCommand.getStatistics();
                        break;
                    }
                    case "deleteCatalog":{
                        AdminCommand.deleteCatalog();
                        break;
                    }
                    case "getSingleSolution":{
                        AdminCommand.getSingleSolution(1);
                        break;
                    }
                    case "getSingleSolutionTree":{
                        AdminCommand.getSingleSolution(2);
                        break;
                    }
                    case "back": {
                        menu = "back";
                        break;
                    }
                    case "exit": {
                        menu = "exit";
                    }
                    default:break;
                }
            }
        }
        catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }


    private void UserManage() throws SQLException, ClassNotFoundException {
        String menu = "work with users";
        System.out.println(menu);
        try {
            AdminCommand.sendUsersData();
            while (!menu.equals( "back" )) {
                menu = get();
                System.out.println(menu);
                switch (Objects.requireNonNull(menu)) {
                    case "deleteUser": {
                        AdminCommand.deleteUser();
                        break;
                    }
                    case "exit": {
                        menu = "exit";
                        break;
                    }
                    case "back": {
                        menu = "back";
                        break;
                    }
                    default:break;
                    }
                }
            } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void openMenuUser() throws IOException, SQLException, ClassNotFoundException {
        String menu = "work";
        String id = get();
        AdminCommand.sendUserInformation(id);
        AdminCommand.sendCatalogData();
        AdminCommand.sendHistoryData(id);

        while (!menu.equals( "back" )) {
            menu = get();
            System.out.println(menu);

            switch (Objects.requireNonNull(menu)){
                case "opros":{
                    UserCommand.addToOpros();
                    break;
                }
                case "addCash":{
                    UserCommand.getUserId();
                    UserCommand.updateUserCash();
                    break;
                }
                case "buyGoods":{
                    UserCommand.buyGoods(id);

                    break;
                }

                case "back": {
                    menu = "back";
                    break;
                }
                default:break;
            }
        }
    }


    private void openMenuAdmin() throws SQLException, ClassNotFoundException {
        String menu = "work";
                while (!menu.equals("exit") && !menu.equals("back1")) {
                    menu = get();
                    System.out.println(menu);
                    if (menu != null) {
                        switch (menu) {
                            case "user manage": {
                                UserManage();
                                break;
                            }
                            case "company manage": {
                                CompanyManage();
                                break;
                            }
                            case "back1": {
                                menu = "back1";
                                break;
                            }
                            default:
                                break;
                        }
                    }
                }
        }
    }


