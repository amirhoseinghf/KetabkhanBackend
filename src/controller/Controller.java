package controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import utils.Convertor;


public class Controller {
    private String sign_up(String data) throws IOException {
        HashMap<String,String> dataMap = Convertor.stringToMap(data);

        Path src = Paths.get("src/database/users.txt");
            List<String> lines = new ArrayList<>();
            lines.add(String.format("email: %s", dataMap.get("email")));
            lines.add(String.format("username: %s", dataMap.get("username")));
            lines.add(String.format("password: %s", dataMap.get("password")));
            Files.write(src, lines, StandardOpenOption.APPEND);
        return "Done";

    }
    private String sign_in(String data) throws IOException {
        HashMap<String,String> dataMap = Convertor.stringToMap(data);
        Path src = Paths.get("src/database/users.txt");
        List<String> list = Files.readAllLines(src);
        HashMap<String, String> databaseMap = Convertor.stringListToMap(list);
        if(dataMap.get("email").equals(databaseMap.get("email")) && dataMap.get("password").equals(databaseMap.get("password"))) return "done";
        else return "failed";

    }




    public String start(String command, String data) throws IOException {

        switch (command) {
            case "sign_up": return sign_up(data);
            case "sign_in": return sign_in(data);
        }
        return "";
    }
}
