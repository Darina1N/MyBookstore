package sk.kosickaakademia.kolesarova.bookstore.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sk.kosickaakademia.kolesarova.bookstore.database.Database;
import sk.kosickaakademia.kolesarova.bookstore.entity.Book;
import sk.kosickaakademia.kolesarova.bookstore.enumerator.Genre;
import sk.kosickaakademia.kolesarova.bookstore.util.Util;

import java.util.List;

@RestController
public class Controller {

    @PostMapping("/book/add")
    public ResponseEntity<String> insertNewBook(@RequestBody String data){
        try{
            JSONObject jsonObject=(JSONObject) new JSONParser().parse(data);
            int id=Integer.parseInt(String.valueOf(jsonObject.get("id")));
            String name = (String) jsonObject.get("name");
            String author= (String) jsonObject.get("author");
            int quantity= Integer.parseInt(String.valueOf(jsonObject.get("quantity")));
            double price= Double.parseDouble(String.valueOf(jsonObject.get("price")));
            String genre= (String) jsonObject.get("genre");
            System.out.println(name+" "+author+" "+quantity+" "+price+" "+genre);
            if(name==null || name.trim().length()==0 || author==null || author.trim().length()==0 || quantity<1 || price<0.00){
                JSONObject object=new JSONObject();
                object.put("ERROR", "Missing name of book or author or incorect value of quantity or price");
                return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body(object.toJSONString());
            }
            Genre gen;
            if(genre.equalsIgnoreCase("education")){
                gen=Genre.Educational;
            }else if(genre.equalsIgnoreCase("poetry")){
                gen=Genre.Poetry;
            }else if(genre.equalsIgnoreCase("novels")){
                gen=Genre.Novels;
            }else if(genre.equalsIgnoreCase("detective_stories")){
                gen=Genre.Detective_stories;
            }else if(genre.equalsIgnoreCase("fantasy")){
                gen=Genre.Fantasy;
            }else
                gen=Genre.Fairy_tales;
            Book book=new Book(id,name,author,quantity,price,gen.getValue());
            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(null);
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        } catch (NumberFormatException exception) {
            JSONObject object = new JSONObject();
            object.put("ERROR", "Bad request.");
            return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body(object.toJSONString());
        }
        return null;
    }

    @GetMapping("/users")
    public ResponseEntity<String> getAllBooks() {
        List<Book> list = new Database().getAllBooks();
        String json = new Util().getJSON(list);
        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(json);
    }


}
