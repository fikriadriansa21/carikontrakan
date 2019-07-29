/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fikriadriansa21
 */
public class TableModel {
    public void setTabel(JTable tabel, String[][] data, String[] namaKolom, int jumlahKolom){
        tabel.setModel(new DefaultTableModel(data, namaKolom));
        for (int i = 0; i < jumlahKolom; i++) {
            tabel.getColumnModel().getColumn(i);
        }
    }
}
