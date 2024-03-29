/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author fikriadriansa21
 */
public class Pengontrak {
    private String idPengontrak;
    private String nik;
    private String namaPengontrak;
    private String noHp;
    
    private ConnectionDB db;
    private Statement stmt;
    private String query;
    private ResultSet rs;


    public String getIdPengontrak() {
        return idPengontrak;
    }

    public void setIdPengontrak(String idPengontrak) {
        this.idPengontrak = idPengontrak;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNamaPengontrak() {
        return namaPengontrak;
    }

    public void setNamaPengontrak(String namaPengontrak) {
        this.namaPengontrak = namaPengontrak;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }
    
    public String[][] tampilData(){
        rs = null;
        String[][] data = null;
        db = new ConnectionDB();
        db.dbConnect();
        int jumlahBaris = 0;
        
        try {
            stmt = db.conection.createStatement();
            query = "SELECT COUNT(id_pengontrak) as jumlah FROM pengontrak;";
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                jumlahBaris = rs.getInt("jumlah");
                        
            }
            query = "SELECT * FROM pengontrak;";
            rs = stmt.executeQuery(query);
            data = new String[jumlahBaris][4];
            int r = 0;
            
            while (rs.next()) {                
                data[r][0] = rs.getString("id_pengontrak");
                data[r][1] = rs.getString("nik");
                data[r][2] = rs.getString("nama_pengontrak");
                data[r][3] = rs.getString("no_hp");
                r++;
            }
            stmt.close();
            db.conection.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return data;
    }
    
    
    
    public void tambahData(
            String idPengontrak, String nik, String namaPengontrak, String noHp
    ){
        db = new ConnectionDB();
        db.dbConnect();
        try {
            stmt = db.conection.createStatement();
            query = "INSERT INTO pengontrak(id_pengontrak, nik, nama_pengontrak, no_hp)"
                    + "VALUES('"+idPengontrak+"', '"+nik+"', '"+namaPengontrak+"', '"+noHp+"')";
            stmt.executeUpdate(query);
            stmt.close();
            db.conection.close();
            
            JOptionPane.showMessageDialog(null, "Data berhasil dimasukkan");            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void ubahData (String nik, String namaPengontrak, String noHp, String idPengontrak){
        db = new ConnectionDB();
        db.dbConnect();
        try {
            query = "UPDATE pengontrak SET "
                    + "nik = ?, "
                    + "nama_pengontrak = ?, "
                    + "no_hp = ? WHERE id_pengontrak = '"+idPengontrak+"'; ";
            PreparedStatement update = db.conection.prepareStatement(query);
            update.setString(1, nik);
            update.setString(2, namaPengontrak);
            update.setString(3, noHp);
            
            update.executeUpdate();
            update.close();
            db.conection.close();
            JOptionPane.showMessageDialog(null, "Data pengontrak dengan id "+idPengontrak+" berhasil dirubah!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void hapusData(String idPengontrak){
        db = new ConnectionDB();
        db.dbConnect();
        try {
            query = "DELETE FROM pengontrak WHERE id_pengontrak = ?; ";
            PreparedStatement delete = db.conection.prepareStatement(query);
            delete.setString(1, idPengontrak);
            delete.executeUpdate();
            delete.close();
            db.conection.close();
            
            JOptionPane.showMessageDialog(null, "Data pengontrak dengan id "+idPengontrak+" berhasil dihapus!");
        } catch (Exception e) {
        }
    }
    
    public String[][] cariNamaPengontrak(String namaPengontrak){
        rs = null;
        String[][] data = null;
        db = new ConnectionDB();
        db.dbConnect();
        int jumlahBaris = 0;
        
        try {
            stmt = db.conection.createStatement();
            query = "SELECT COUNT(id_pengontrak) as jumlah FROM pengontrak;";
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                jumlahBaris = rs.getInt("jumlah");
                        
            }
            query = "SELECT * FROM pengontrak WHERE nama_pengontrak = '"+namaPengontrak+"' ;";
            rs = stmt.executeQuery(query);
            data = new String[jumlahBaris][4];
            int r = 0;
            
            while (rs.next()) {                
                data[r][0] = rs.getString("id_pengontrak");
                data[r][1] = rs.getString("nik");
                data[r][2] = rs.getString("nama_pengontrak");
                data[r][3] = rs.getString("no_hp");
                r++;
            }
            stmt.close();
            db.conection.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return data;
    }
    
}
