package controller;

enum Categoryz {
    favorite,
    newRelease,
    topSelling,
}

enum Type {
    ebooks,
    soundBooks
}

enum Genre {
    romance,
    fiction,
    history,
    horror
}


public class Book {
    final String id;
    final String name;
    final String author;
    final Genre genre;
    final int price;
    final Categoryz category;
    final String imageUrl;
    final Type type;
    Boolean isReadingNow = false;


    Book(String id,String name, String author,String imageUrl, Genre genre, int price, Categoryz category, Type type, Boolean isReadingNow){
        this.id = id;
        this.name = name;
        this.author = author;
        this.imageUrl = imageUrl;
        this.genre = genre;
        this.price = price;
        this.category = category;
        this.type = type;
    }
}
