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
public class Fasilitas {
    private String idFasilitas;    
    private String noKontrakan;
    private String namaFasilitas;
    private int jumlah;
    
    private ConnectionDB db;
    private Statement stmt;
    private String query;
    private ResultSet rs;

    /**
     * @return the idFasilitas
     */
    public String getIdFasilitas() {
        return idFasilitas;
    }

    /**
     * @param idFasilitas the idFasilitas to set
     */
    public void setIdFasilitas(String idFasilitas) {
        this.idFasilitas = idFasilitas;
    }

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
     * @return the namaFasilitas
     */
    public String getNamaFasilitas() {
        return namaFasilitas;
    }

    /**
     * @param namaFasilitas the namaFasilitas to set
     */
    public void setNamaFasilitas(String namaFasilitas) {
        this.namaFasilitas = namaFasilitas;
    }

    /**
     * @return the jumlah
     */
    public int getJumlah() {
        return jumlah;
    }

    /**
     * @param jumlah the jumlah to set
     */
    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
    
    public String[][] tampilData(){
        rs = null;
        String[][] data = null;
        db = new ConnectionDB();
        db.dbConnect();
        int jumlahBaris = 0;
        
        try {
            stmt = db.conection.createStatement();
            query = "SELECT COUNT(id_fasilitas) as jumlah FROM fasilitas;";
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                jumlahBaris = rs.getInt("jumlah");
                        
            }
            query = "SELECT * FROM fasilitas;";
            rs = stmt.executeQuery(query);
            data = new String[jumlahBaris][4];
            int r = 0;
            
            while (rs.next()) {                
                data[r][0] = rs.getString("id_fasilitas");
                data[r][1] = rs.getString("no_kontrakan");
                data[r][2] = rs.getString("nama_fasilitas");
                data[r][3] = rs.getString("jumlah");
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
            String idFasilitas, String noKontrakan, String namaFasilitas, int jumlah
    ){
        db = new ConnectionDB();
        db.dbConnect();
        try {
            stmt = db.conection.createStatement();
            query = "INSERT INTO fasilitas(id_fasilitas, no_kontrakan, nama_fasilitas, jumlah)"
                    + "VALUES('"+idFasilitas+"', '"+noKontrakan+"', '"+namaFasilitas+"', '"+jumlah+"')";
            stmt.executeUpdate(query);
            stmt.close();
            db.conection.close();
            
            JOptionPane.showMessageDialog(null, "Data berhasil dimasukkan");            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void ubahData (String noKontrakan, String namaFasilitas, int jumlah, String idFasilitas){
        db = new ConnectionDB();
        db.dbConnect();
        try {
            query = "UPDATE fasilitas SET "
                    + "no_kontrakan = ?, "
                    + "nama_fasilitas = ?, "
                    + "jumlah = ? WHERE id_fasilitas = '"+idFasilitas+"'; ";
            PreparedStatement update = db.conection.prepareStatement(query);
            update.setString(1, noKontrakan);
            update.setString(2, namaFasilitas);
            update.setInt(3, jumlah);
            
            update.executeUpdate();
            update.close();
            db.conection.close();
            JOptionPane.showMessageDialog(null, "Data fasilitas dengan id "+idFasilitas+" berhasil dirubah!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void hapusData(String idFasilitas){
        db = new ConnectionDB();
        db.dbConnect();
        try {
            query = "DELETE FROM fasilitas WHERE id_fasilitas = ?; ";
            PreparedStatement delete = db.conection.prepareStatement(query);
            delete.setString(1, idFasilitas);
            delete.executeUpdate();
            delete.close();
            db.conection.close();
            
            JOptionPane.showMessageDialog(null, "Data kontrakan dengan no "+idFasilitas+" berhasil dihapus!");
        } catch (Exception e) {
        }
    }
    
    public String[][] cariNamaFasilitas(String namaFasilitas){
        rs = null;
        String[][] data = null;
        db = new ConnectionDB();
        db.dbConnect();
        int jumlahBaris = 0;
        
        try {
            stmt = db.conection.createStatement();
            query = "SELECT COUNT(id_fasilitas) as jumlah FROM fasilitas;";
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                jumlahBaris = rs.getInt("jumlah");
                        
            }
            query = "SELECT * FROM fasilitas WHERE nama_fasilitas = '"+namaFasilitas+"' ;";
            rs = stmt.executeQuery(query);
            data = new String[jumlahBaris][4];
            int r = 0;
            
            while (rs.next()) {                
                data[r][0] = rs.getString("id_fasilitas");
                data[r][1] = rs.getString("no_kontrakan");
                data[r][2] = rs.getString("nama_fasilitas");
                data[r][3] = rs.getString("jumlah");
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
