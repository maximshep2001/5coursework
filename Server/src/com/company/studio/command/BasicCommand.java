package com.company.studio.command;

import com.company.studio.connection.MonoThreadClientHandler;
import com.company.studio.database.DataBaseHandler;
import com.company.studio.database.JavaMailUtil;
import com.company.studio.database.User;
import org.json.JSONException;
import org.json.JSONObject;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class BasicCommand extends MonoThreadClientHandler {

    public BasicCommand(Socket client) throws IOException {
        super(client);
    }

    public static String authorization() throws IOException {

        String phone = get();
        String password = get();
        String role = null;
        Integer id = null;

        DataBaseHandler handler = new DataBaseHandler();
        User user = new User();
        user.setPhone(phone);
        user.setPassword(password);

        ResultSet result = handler.signInUser(user);

        int counter = 0;
        try {
            while (result.next()) {
                counter++;
                role = result.getNString(7);
                id = result.getInt(1);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        if(counter>=1){
            send(role);
            //System.out.println(role);
            if (role.equals("USER"))
            send(id.toString());
            //System.out.println(id);

        }
        else {
            send("incorrectly");
            role="null";
        }
        return role;
    }

    public static void registration() {
        String user;
        user = get();
        if (user != null) {
            System.out.println("я получил: " + user);

            String[] s = user.split(" ");
            String name = s[0];
            String surname = s[1];
            String phone = s[2];
            String email = s[3];
            String password = s[4];
            String role = s[5];
            DataBaseHandler handler = new DataBaseHandler();
            handler.signUpUser(name, surname, phone, email, password, role);

            ResultSet result = handler.getUser(phone);
            String userId = null;
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
                int uid = Integer.parseInt(userId);

                handler.openCashAccount(uid, 0);
            }
        }
    }

    public static void forgotpassword() {
        try {
            String email = get();

            if (!email.equals("")) {
                DataBaseHandler handler = new DataBaseHandler();
                String sign = handler.checkMail(email);
                if (sign.equals("nobody")) {
                    send("nobody");
                } else {
                    JSONObject mailJson = new JSONObject(sign);
                    String password = mailJson.getString("password");
                    send("user");
                    JavaMailUtil.sendMail(email, password);
                }
            }
        } catch(IOException | JSONException | SQLException | MessagingException e){
            e.printStackTrace();
        }
    }
}
