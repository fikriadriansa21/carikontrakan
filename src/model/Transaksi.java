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
public class Transaksi {
    private String idTransaksi;
    private String tglTransaksi;
    private String noKontrakan;
    private String idPengontrak;
    
    private ConnectionDB db;
    private Statement stmt;
    private String query;
    private ResultSet rs;

    /**
     * @return the idTransaksi
     */
    public String getIdTransaksi() {
        return idTransaksi;
    }

    /**
     * @param idTransaksi the idTransaksi to set
     */
    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    /**
     * @return the tglTransaksi
     */
    public String getTglTransaksi() {
        return tglTransaksi;
    }

    /**
     * @param tglTransaksi the tglTransaksi to set
     */
    public void setTglTransaksi(String tglTransaksi) {
        this.tglTransaksi = tglTransaksi;
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
     * @return the idPengontrak
     */
    public String getIdPengontrak() {
        return idPengontrak;
    }

    /**
     * @param idPengontrak the idPengontrak to set
     */
    public void setIdPengontrak(String idPengontrak) {
        this.idPengontrak = idPengontrak;
    }

    
    public String[][] tampilData(){
        rs = null;
        String[][] data = null;
        db = new ConnectionDB();
        db.dbConnect();
        int jumlahBaris = 0;
        
        try {
            stmt = db.conection.createStatement();
            query = "SELECT COUNT(id_transaksi) as jumlah FROM transaksi;";
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                jumlahBaris = rs.getInt("jumlah");
                        
            }
            query = "SELECT t.id_transaksi, t.tanggal_transaksi, t.no_kontrakan, p.nama_pengontrak "
                    + " FROM transaksi t INNER JOIN pengontrak p "
                    + " ON t.id_pengontrak = p.id_pengontrak ; ";
            rs = stmt.executeQuery(query);
            data = new String[jumlahBaris][4];
            int r = 0;
            
            while (rs.next()) {                
                data[r][0] = rs.getString("id_transaksi");
                data[r][1] = rs.getString("tanggal_transaksi");
                data[r][2] = rs.getString("no_kontrakan");
                data[r][3] = rs.getString("nama_pengontrak");
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
            String idTransaksi, String tanggal, String noKontrakan, String idPengontrak
    ){
        db = new ConnectionDB();
        db.dbConnect();
        try {
            stmt = db.conection.createStatement();
            query = "INSERT INTO transaksi(id_transaksi, tanggal_transaksi, no_kontrakan, id_pengontrak)"
                    + "VALUES('"+idTransaksi+"', '"+tanggal+"', '"+noKontrakan+"', '"+idPengontrak+"')";
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
