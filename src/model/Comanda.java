/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import controllers.LiniaController;
import controllers.MaquinaController;
import java.io.Serializable;

public class Comanda implements Serializable {

    protected int idComanda;
    protected double total;
    protected LiniaController lc;

    public Comanda(int idComanda, double total, MaquinaController mc) {
        this.idComanda = idComanda;
        this.total = total;
        lc = new LiniaController(mc);
    }

    public Comanda(int idComanda, MaquinaController mc) {
        this.idComanda = idComanda;
        lc = new LiniaController(mc);
    }

    public Comanda() {
    }

    public int getIdComanda() {
        return idComanda;
    }

    public void setIdComanda(int idComanda) {
        this.idComanda = idComanda;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void afegirLiniaComanda(LiniaComanda liniaIn) {
        lc.afegirLinies(liniaIn);
    }

    public void borrarArray() {
        lc.borrarArray();
    }

    public double sumarSubtotals() {
        return lc.sumarSubtotals();
    }

    public int getLineId() {
        return lc.getNewId();
    }

    public void printComanda() {
        System.out.println("|\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/|");
        System.out.println("|                                                              |");
        System.out.printf("|    Identificado Comanda: %-3s         Total Comanda: %-7.2f  |\n", idComanda, total);
        System.out.println("|                                                              |");
        System.out.println("|--------------------------------------------------------------|");
        System.out.println("|                                                              |");
        lc.printLinies();
        System.out.println("|/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\|");
    }

    @Override
    public String toString() {
        lc.printLinies();
        return "id:" + idComanda + "   total:" + total;
    }

}
