package utils;

import java.util.HashMap;
import java.util.List;

public class Convertor {
    public static HashMap<String,String> stringToMap(String str) {
        String[] exprs = str.split(",,");
        HashMap<String,String> data = new HashMap<>();

        for (String expr : exprs){
            int colon = expr.indexOf(':');
            String key = expr.substring(0,colon);
            String value = expr.substring(colon + 1);
            data.put(key,value);
        }
        return data;
    }
    public static HashMap<String, String> stringListToMap(List<String> stringList){
        if(stringList.isEmpty()) return new HashMap<>();
        HashMap<String, String> data = new HashMap<>();
        for (String expr : stringList) {
            int colon = expr.indexOf(":");
            String key = expr.substring(0,colon);
            String value = expr.substring(colon + 2);
            data.put(key,value);
        }
        return data;
    }
}