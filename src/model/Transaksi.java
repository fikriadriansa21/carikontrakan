/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author fikriadriansa21
 */
public class Transaksi {
    private String idTransaksi;
    private String tglTransaksi;
    private String noKontrakan;
    private String idPengontrak;        

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
    
}
