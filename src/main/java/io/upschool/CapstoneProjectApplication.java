package io.upschool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class CapstoneProjectApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(CapstoneProjectApplication.class, args);
        }catch (Exception e){
            e.printStackTrace();
        }

        /*
         String jdbcUrl = "jdbc:mysql://localhost:3306/ticket_app";
        String username = "root";
        String password = "root";

        try {
            // Bağlantıyı kur
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Bağlantı başarılı!");

            // Bağlantıyı kapat
            connection.close();
            System.out.println("Bağlantı kapatıldı.");
        } catch (SQLException e) {
            System.err.println("Bağlantı hatası: " + e.getMessage());
            e.printStackTrace();
        }
         */
    }

}
