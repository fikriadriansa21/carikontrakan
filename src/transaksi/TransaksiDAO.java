/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transaksi;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import model.ConnectionDB;

/**
 *
 * @author fikriadriansa21
 */
public class TransaksiDAO {
    private ConnectionDB db;
    private Statement stmt;
    private String query;
    private ResultSet rs;
    
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
            query = "SELECT t.id_transaksi, t.tanggal_transaksi, t.no_kontrakan, t.id_pengontrak "
                    + " FROM transaksi t "
                    + " ; ";
            rs = stmt.executeQuery(query);
            data = new String[jumlahBaris][4];
            int r = 0;
            
            while (rs.next()) {                
                data[r][0] = rs.getString("id_transaksi");
                data[r][1] = rs.getString("tanggal_transaksi");
                data[r][2] = rs.getString("no_kontrakan");
                data[r][3] = rs.getString("id_pengontrak");
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
            
            JOptionPane.showMessageDialog(null, "Data transaksi berhasil dilakukan!");            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void ubahData (String tanggal, String noKontrakan, String idPengontrak, String idTransaksi){
        db = new ConnectionDB();
        db.dbConnect();
        try {
            query = "UPDATE transaksi SET "
                    + "tanggal_transaksi = ?, "
                    + "no_kontrakan = ?, "
                    + "id_pengontrak = ? WHERE id_transaksi = '"+idTransaksi+"'; ";
            PreparedStatement update = db.conection.prepareStatement(query);
            update.setString(1, tanggal);
            update.setString(2, noKontrakan);
            update.setString(3, idPengontrak);
            
            update.executeUpdate();
            update.close();
            db.conection.close();
            JOptionPane.showMessageDialog(null, "Data transaksi dengan id "+idTransaksi+" berhasil dirubah!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void hapusData(String idTransaksi){
        db = new ConnectionDB();
        db.dbConnect();
        try {
            query = "DELETE FROM transaksi WHERE id_transaksi = ?; ";
            PreparedStatement delete = db.conection.prepareStatement(query);
            delete.setString(1, idTransaksi);
            delete.executeUpdate();
            delete.close();
            db.conection.close();
            
            JOptionPane.showMessageDialog(null, "Data transaksi dengan no "+idTransaksi+" berhasil dihapus!");
        } catch (Exception e) {
        }
    }
    
    public String[][] cariTanggal(String tanggalTransaksi){
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
            query = "SELECT * FROM transaksi WHERE tanggal_transaksi = '"+tanggalTransaksi+"' ;";
            rs = stmt.executeQuery(query);
            data = new String[jumlahBaris][4];
            int r = 0;
            
            while (rs.next()) {                
                data[r][0] = rs.getString("id_transaksi");
                data[r][1] = rs.getString("tanggal_transaksi");
                data[r][2] = rs.getString("no_kontrakan");
                data[r][3] = rs.getString("id_pengontrak");
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
