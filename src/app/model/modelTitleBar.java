package app.model;

import app.database.DbConnect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class modelTitleBar {

    private String idUser,nama_lengkap,username;

    public void setValue2(String username){
        try {
            Connection connection = DbConnect.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM admin WHERE username ='"+username+ "'");

            while(res.next()){
                this.username=res.getString(1);
                this.idUser=res.getString(2);
                this.nama_lengkap=res.getString(3);
                }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public String getUsername() {
        return username;
    }



    public void setValue(String username) {
        try {
            Connection connection = DbConnect.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM admin WHERE username ='"+username+ "'");

            while(res.next()){
                this.idUser=res.getString(1);
                this.username=res.getString(2);
                this.nama_lengkap=res.getString(3);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
