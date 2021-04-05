package sk.kosickaakademia.kolesarova.bookstore.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import sk.kosickaakademia.kolesarova.bookstore.entity.Book;

import java.util.List;

public class Util {
    public String getJSON(List<Book> list){
        if(list.isEmpty())
            return "{}";
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray=new JSONArray();
        for(Book book: list){
            JSONObject bookJson= new JSONObject();
            bookJson.put("id",book.getId());
            bookJson.put("name", book.getName());
            bookJson.put("author",book.getAuthor());
            bookJson.put("quantity",book.getQuantity());
            bookJson.put("price",book.getPrice());
            bookJson.put("genre",book.getGenre().toString());
            jsonArray.add(bookJson);
        }
        jsonObject.put("books",jsonArray);
        return jsonObject.toJSONString();
    }
}
