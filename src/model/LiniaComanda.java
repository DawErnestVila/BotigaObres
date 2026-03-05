/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import controllers.MaquinaController;
import java.io.Serializable;

/**
 *
 * @author ernest
 */
public class LiniaComanda /**
         * extends Comanda*
         */
        implements Serializable {

    private int idLinia;
    private int idMaquina;
    private int diesLlogat;
    private double subtotalLinia;

    public LiniaComanda(int idLinia, int idMaquina, int diesLlogat, double subtotalLinia, int idComanda, MaquinaController mc) {
        //super(idComanda, mc);
        this.idLinia = idLinia;
        this.idMaquina = idMaquina;
        this.diesLlogat = diesLlogat;
        this.subtotalLinia = subtotalLinia;
    }

    public LiniaComanda(/**
             * int idComanda, MaquinaController mc*
             */
            ) {
        //super(idComanda, mc);
    }

    public int getIdLinia() {
        return idLinia;
    }

    public void setIdLinia(int idLinia) {
        this.idLinia = idLinia;
    }

    public int getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(int idMaquina) {
        this.idMaquina = idMaquina;
    }

    public int getDiesLlogat() {
        return diesLlogat;
    }

    public void setDiesLlogat(int diesLlogat) {
        this.diesLlogat = diesLlogat;
    }

    public double getSubtotalLinia() {
        return subtotalLinia;
    }

    public void setSubtotalLinia(double subtotalLinia) {
        this.subtotalLinia = subtotalLinia;
    }

    @Override
    public String toString() {
        return "idLinia:" + idLinia + " idMaquina:" + idMaquina + " diesLlogat:" + diesLlogat + " subtotalLinia:" + subtotalLinia;
    }

}
