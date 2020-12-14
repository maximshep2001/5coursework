package com.company.studio.command;

import com.company.studio.connection.MonoThreadClientHandler;
import com.company.studio.database.DataBaseHandler;
import com.company.studio.database.User;

import java.io.IOException;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class CheckCommand extends MonoThreadClientHandler {
    public CheckCommand(Socket client) throws IOException {
        super(client);
    }

    public static void phoneExist() throws IOException {
        String phone = get();

        DataBaseHandler handler = new DataBaseHandler();
        User user = new User();
        user.setPhone(phone);

        ResultSet result = handler.phoneExist(user);

        int counter = 0;
        try {
            while (result.next()) {
                counter++;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        if(counter>=1){
            send("incorrectly");
        }
        else {
            send("correctly");
        }
    }

}
