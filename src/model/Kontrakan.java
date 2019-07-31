/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Izun
 */
public class Kontrakan {
    private String noKontrakan;
    private String status;
    private double luasKontrakan;
    private double harga;    

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
    
}
