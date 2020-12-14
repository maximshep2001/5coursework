package com.company.studio.collections;

import com.company.studio.behavior.UserInformation;
import com.company.studio.connection.Connect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class CollectionUsers {

    private ObservableList<UserInformation> users = FXCollections.observableArrayList();

    private static CollectionUsers instance;

    public static synchronized CollectionUsers getInstance(){
        if(instance == null){
            instance = new CollectionUsers();
        }
        return instance;
    }

    public ObservableList<UserInformation> getUsers() {
        return users;
    }

    public void fillData(){
        try {
            users.removeAll(users);
            String array = Connect.get();
            System.out.println(array);
            JSONArray newArray = null;
            if (array != null) {
                newArray = new JSONArray(array);
                int count = newArray.length();
                for(int i = 0; i<count; i++) {
                    JSONObject object = newArray.getJSONObject( i );
                    Integer id = object.getInt("id");
                    String name = object.getString( "name" );
                    String surname = object.getString( "surname" );
                    String phone = object.getString( "phone" );
                    String email = object.getString( "email" );
                    String password=object.getString("password");
                    String role=object.getString("role");
                    UserInformation user = new UserInformation(id, name, surname, phone, email, password, role);
                    users.add(user);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void delete(UserInformation user){
        users.remove(user);
    }
}
