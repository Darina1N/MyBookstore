package sk.kosickaakademia.kolesarova.bookstore.database;

import sk.kosickaakademia.kolesarova.bookstore.entity.Book;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Database {
    public Connection getConnection(){
        try {
            Properties properties=new Properties();
            InputStream loader=getClass().getClassLoader().getResourceAsStream("database.properties");
            properties.load(loader);
            String url=properties.getProperty("url");
            String username=properties.getProperty("username");
            String password=properties.getProperty("password");
            Connection connection= DriverManager.getConnection(url,username,password);
            System.out.println("Connection is OK");
            return connection;
        }catch (Exception ex){
            //ex.printStackTrace();
            System.out.println("Connection failed");
        }
        return null;
    }

    public void closeConnection(Connection connection){
        if(connection!=null)
        {
            try {
                connection.close();
                System.out.println("Connection closed.");
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    public boolean insertNewBook(Book book){
        String newBook="INSERT INTO book (name, author, quantity, price, genre) VALUES (?, ?, ?, ?, ?)";
        if(getConnection()!=null){
            try{
                PreparedStatement ps=getConnection().prepareStatement(newBook);
                ps.setString(1,book.getName());
                ps.setString(2,book.getAuthor());
                ps.setInt(3,book.getQuantity());
                ps.setDouble(4,book.getPrice());
                ps.setInt(5,book.getGenre().getValue());
                int result=ps.executeUpdate();
                closeConnection(getConnection());
                System.out.println("New book is added");
                return result==1;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    private List<Book> mainSelect(PreparedStatement ps){
        List<Book> list = new ArrayList<>();
        try {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String author = rs.getString("author");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                int genre = rs.getInt("genre");
                Book book=new Book(id,name,author,quantity,price,genre);
                list.add(book);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    public List<Book> getAllBooks(){
        String allBook= "SELECT * FROM book";
        try{
            PreparedStatement ps=getConnection().prepareStatement(allBook);
            return mainSelect(ps);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
