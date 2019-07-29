/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.*;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import kontrakan.MenuKontrakanInternalFrame;

/**
 *
 * @author Izun
 */
public class Kontrakan {
    private String noKontrakan;
    private String status;
    private double luasKontrakan;
    private double harga;
    private JComboBox jCombo;
    
    private ConnectionDB db;
    private Statement stmt;
    private String query;
    private ResultSet rs;

    /**
     * @return the noKontrakan
     */
    public String getNoKontrakan() {
        return noKontrakan;
    }

    /**
     * @param noKontrakan the noKontrakan to set
     */
    public void setNoKontrakan(String noKontrakan) {
        this.noKontrakan = noKontrakan;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the luasKontrakan
     */
    public double getLuasKontrakan() {
        return luasKontrakan;
    }

    /**
     * @param luasKontrakan the luasKontrakan to set
     */
    public void setLuasKontrakan(double luasKontrakan) {
        this.luasKontrakan = luasKontrakan;
    }

    /**
     * @return the harga
     */
    public double getHarga() {
        return harga;
    }

    /**
     * @param harga the harga to set
     */
    public void setHarga(double harga) {
        this.harga = harga;
    }    
    
    
    public String[][] tampilData(){
        rs = null;
        String[][] data = null;
        db = new ConnectionDB();
        db.dbConnect();
        int jumlahBaris = 0;
        
        try {
            stmt = db.conection.createStatement();
            query = "SELECT COUNT(no_kontrakan) as jumlah FROM kontrakan;";
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                jumlahBaris = rs.getInt("jumlah");
                        
            }
            query = "SELECT * FROM kontrakan;";
            rs = stmt.executeQuery(query);
            data = new String[jumlahBaris][4];
            int r = 0;
            
            while (rs.next()) {                
                data[r][0] = rs.getString("no_kontrakan");
                data[r][1] = rs.getString("harga_kontrakan");
                data[r][2] = rs.getString("luas_kontrakan");
                data[r][3] = rs.getString("status");
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
            String noKontrakan, double hargaKontrakan, double luasKontrakan, String status
    ){
        db = new ConnectionDB();
        db.dbConnect();
        try {
            stmt = db.conection.createStatement();
            query = "INSERT INTO kontrakan(no_kontrakan, harga_kontrakan, luas_kontrakan, status)"
                    + "VALUES('"+noKontrakan+"', '"+hargaKontrakan+"', '"+luasKontrakan+"', '"+status+"')";
            stmt.executeUpdate(query);
            stmt.close();
            db.conection.close();
            
            JOptionPane.showMessageDialog(null, "Data berhasil dimasukkan");            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void ubahData (double hargaKontrakan, double luasKontrakan, String status, String noKontrakan){
        db = new ConnectionDB();
        db.dbConnect();
        try {
            query = "UPDATE kontrakan SET "
                    + "harga_kontrakan = ?, "
                    + "luas_kontrakan = ?, "
                    + "status = ? WHERE no_kontrakan = '"+noKontrakan+"'; ";
            PreparedStatement update = db.conection.prepareStatement(query);
            update.setDouble(1, hargaKontrakan);
            update.setDouble(2, luasKontrakan);
            update.setString(3, status);
            
            update.executeUpdate();
            update.close();
            db.conection.close();
            JOptionPane.showMessageDialog(null, "Data kontrakan dengan no "+noKontrakan+" berhasil dirubah!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void hapusData(String noKontrakan){
        db = new ConnectionDB();
        db.dbConnect();
        try {
            query = "DELETE FROM kontrakan WHERE no_kontrakan = ?; ";
            PreparedStatement delete = db.conection.prepareStatement(query);
            delete.setString(1, noKontrakan);
            delete.executeUpdate();
            delete.close();
            db.conection.close();
            
            JOptionPane.showMessageDialog(null, "Data kontrakan dengan no "+noKontrakan+" berhasil dihapus!");
        } catch (Exception e) {
        }
    }
    
    
    
}
