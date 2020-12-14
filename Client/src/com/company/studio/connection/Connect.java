package com.company.studio.connection;

import com.company.studio.behavior.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public final class Connect{
    private static DataOutputStream oos;
    private static DataInputStream ois;
    private static Connect instance;

    public Connect() {
        try {
            Socket socket = new Socket("localhost", 3346);
            //System.out.println("Client connected to socket");
            Thread.sleep(2000);
            oos = new DataOutputStream(socket.getOutputStream());
            ois = new DataInputStream(socket.getInputStream());
            //System.out.println("Client oos & ois initialized");
        } catch (Exception e) {
            System.out.println("Сервер офлайн");
        }
    }

    public static void send(String message) {
        try {
            oos.writeUTF(message);
            oos.flush();
            System.out.println("write command: " + message);
        } catch (Exception e) {
            System.out.println("Потеряно соединение");
        }
    }

    public static void send(Object obj) {
        try {
            oos.writeUTF(obj.toString());
            oos.flush();
            System.out.println("write message: " + obj.toString());
        } catch (Exception e) {
            System.out.println("Потеряно соединение");
        }
    }


    public static String get(){
        try {
            System.out.println("Принято сообщение: ");
            return ois.readUTF();
        } catch (IOException e){
            System.out.println("Потеряно соединение");
        }
        return null;
    }

    public String isWork(){
        try {
            return "Server start/Подключились к серверу";
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
        return null;
    }

}
