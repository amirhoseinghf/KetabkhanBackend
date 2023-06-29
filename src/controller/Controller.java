package controller;

import network.ClientHandler;
import utils.Convertor;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.*;

import org.json.*;




public class Controller {

    private String sign_up(String data) throws IOException {
        HashMap<String,String> dataMap = Convertor.stringToMap(data);

        ClientHandler.user.email = dataMap.get("email");
        ClientHandler.user.userName = dataMap.get("username");
        ClientHandler.user.password = dataMap.get("password");

        FileOutputStream f1 = new FileOutputStream("src/database/users.txt");
        ObjectOutputStream out = new ObjectOutputStream(f1);

        out.writeObject(ClientHandler.user);

//        Path src = Paths.get("src/database/users.txt");
//            List<String> lines = new ArrayList<>();
//            lines.add(String.format("email: %s", dataMap.get("email")));
//            lines.add(String.format("username: %s", dataMap.get("username")));
//            lines.add(String.format("password: %s", dataMap.get("password")));
//            Files.write(src, lines, StandardOpenOption.APPEND);
        return "Done";

    }
    private String sign_in(String data) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("src/database/users.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        User user1 = (User) ois.readObject();
        HashMap<String,String> dataMap = Convertor.stringToMap(data);
        System.out.println("FSF");
//        Path src = Paths.get("src/database/users.txt");
//        List<String> list = Files.readAllLines(src);
//        HashMap<String, String> databaseMap = Convertor.stringListToMap(list);
        if(dataMap.get("email").equals(user1.email) && dataMap.get("password").equals(user1.password)) return "done";
        else return "failed";

    }

    private String add_book(String data) throws IOException, ClassNotFoundException {
        Gson gson = new Gson();
        Map<String, Object> map = gson.fromJson(data, HashMap.class);
        FileInputStream fi = new FileInputStream("src/database/users.txt");
        ObjectInputStream oi = new ObjectInputStream(fi);
        User user2 = (User) oi.readObject();
        user2.booksIds.add((String) map.get("id"));
        System.out.println(map.get("price"));
        user2.credit -= (double) map.get("price");
        FileOutputStream f1 = new FileOutputStream("src/database/users.txt");
        ObjectOutputStream out = new ObjectOutputStream(f1);
        System.out.println(map);
        out.writeObject(user2);
        return "Hooray!";
    }

    private String get_user_info() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("src/database/users.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Gson gson = new Gson();
        User user1 = (User) ois.readObject();
        System.out.println(gson.toJson(user1));
        return gson.toJson(user1);
    }

    private String add_credit(String data) throws IOException, ClassNotFoundException {
        System.out.println(data);
        FileInputStream fis = new FileInputStream("src/database/users.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        User user = (User) ois.readObject();
        user.credit += Integer.parseInt(data);
        FileOutputStream fos = new FileOutputStream("src/database/users.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(user);
        System.out.println(user.credit);
        return "Done";
    }

    private String save_info(String data) throws IOException, ClassNotFoundException {
        Gson gson = new Gson();
        Map<String, Object> map = gson.fromJson(data, HashMap.class);
        System.out.println(map);
        FileInputStream fis = new FileInputStream("src/database/users.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        User user = (User) ois.readObject();
        user.name = (String) map.get("name");
        user.familyName = (String) map.get("familyName");
        FileOutputStream fos = new FileOutputStream("src/database/users.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(user);
        return "";
    }

    private String add_reading(String id) throws IOException, ClassNotFoundException {
        //TODO: Implement this.
        FileInputStream fis = new FileInputStream("src/database/users.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        User user = (User) ois.readObject();
        System.out.println(id);
        user.readingIds.add(id);
        FileOutputStream fos = new FileOutputStream("src/database/users.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(user);
        return "Done";
    }


    public String start(String command, String data) throws IOException, ClassNotFoundException {

        switch (command) {
            case "sign_up": return sign_up(data);
            case "sign_in": return sign_in(data);
            case "add_book": return add_book(data);
            case "get_user_info": return get_user_info();
            case "add_credit": return add_credit(data);
            case "save_info": return save_info(data);
            case "add_reading": return add_reading(data);
        }
        return "";
    }
}
