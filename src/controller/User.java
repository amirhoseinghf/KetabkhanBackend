package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    String email,userName,name,familyName,profileImageUrl;
    double credit = 0;
    String password;
    List<String> booksIds = new ArrayList<>();
    List<String> readingIds = new ArrayList<>();

    public User(String email, String userName, String password, String name, String familyName, String profileImageUrl, double credit){
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.familyName = familyName;
        this.profileImageUrl = profileImageUrl;
        this.credit = credit;
    }
}
