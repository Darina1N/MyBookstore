package sk.kosickaakademia.kolesarova.bookstore.entity;

import sk.kosickaakademia.kolesarova.bookstore.enumerator.Genre;

public class Book {
   private int id;
   private String name;
   private String author;
   private int quantity;
   private double price;
   private Genre genre;

   public Book(int id, String name, String author, int quantity, double price, int genre) {
      this.id = id;
      this.name = name;
      this.author = author;
      this.quantity = quantity;
      this.price = price;
      this.genre = genre==0?Genre.Educational : genre==1?Genre.Poetry : genre==2?Genre.Novels : genre==3?Genre.Detective_stories
                 : genre==4?Genre.Fantasy : Genre.Fairy_tales;
   }

   public int getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   public String getAuthor() {
      return author;
   }

   public int getQuantity() {
      return quantity;
   }

   public double getPrice() {
      return price;
   }

   public Genre getGenre() {
      return genre;
   }

   @Override
   public String toString(){
      return "Book{id="+id+", name='"+name+'\''+", author='"+author+'\''+"quantity="+quantity+
                   ", price="+price+", genre="+genre+"}";
   }
}
