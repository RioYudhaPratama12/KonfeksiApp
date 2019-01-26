package app.model;

import app.database.DbConnect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class modelLogin {

    private String idUser, nama_lengkap;

    public ResultSet res;

    public void setLogin(String username, String password){
        try {
            int row=0;
            Connection connection = DbConnect.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery("SELECT id_User, nama_lengkap FROM admin where username='"+username+"' and password='"+password+"'");


            while(res.next()){
                row=res.getRow();
                this.idUser=res.getString("idUser");
                this.nama_lengkap=res.getString("nama_lengkap");

            }

        } catch (Exception e) {
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



}
