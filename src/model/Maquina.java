/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author ernes
 */
public class Maquina implements Serializable {

    protected int id;
    protected int potencia;
    protected String model;
    protected double preuXdia;
    protected double pes;
    protected String combustible;
    protected boolean llogat;
    protected LocalDate dataInici;
    protected LocalDate dataRetorn;
    protected int diesReserva;

    public Maquina(int id, int potencia, String model, double preuXdia, double pes, String combustible, boolean llogat, LocalDate dataInici, LocalDate dataRetorn, int diesReserva) {
        this.id = id;
        this.potencia = potencia;
        this.model = model;
        this.preuXdia = preuXdia;
        this.pes = pes;
        this.combustible = combustible;
        this.llogat = llogat;
        this.dataInici = dataInici;
        this.dataRetorn = dataRetorn;
        this.diesReserva = diesReserva;
    }

    public Maquina() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPotencia() {
        return potencia;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPreuXdia() {
        return preuXdia;
    }

    public void setPreuXdia(double preuXdia) {
        this.preuXdia = preuXdia;
    }

    public double getPes() {
        return pes;
    }

    public void setPes(double pes) {
        this.pes = pes;
    }

    public String getCombustible() {
        return combustible;
    }

    public void setCombustible(String combustible) {
        this.combustible = combustible;
    }

    public boolean isLlogat() {
        return llogat;
    }

    public void setLlogat(boolean llogat) {
        this.llogat = llogat;
    }

    public LocalDate getDataInici() {
        return dataInici;
    }

    public void setDataInici(LocalDate dataInici) {
        this.dataInici = dataInici;
    }

    public LocalDate getDataRetorn() {
        return dataRetorn;
    }

    public void setDataRetorn(LocalDate dataRetorn) {
        this.dataRetorn = dataRetorn;
    }

    public int getDiesReserva() {
        return diesReserva;
    }

    public void setDiesReserva(int diesReserva) {
        this.diesReserva = diesReserva;
    }

    public void printMaquina() {
        System.out.println("--                                                                                                                                            --");
        System.out.printf("-- %2s. %15s        %-13s   %7.2feur            %6s            %5s        %7.2f       %10s        %10s --\n", id, model, combustible, preuXdia, llogat, diesReserva, pes, dataInici, dataRetorn);
        //System.out.println("-- " + id + ".      " + potencia + "        " + model + "       " + preuXdia + "        " + pes + "         " + combustible + "         " + llogat + "          " + diesReserva + "           " + dataInici + "          " + dataRetorn + " --");
    }

    public double calcularPreu() {
        double cost;
        Period p = Period.between(dataInici, dataRetorn);
        diesReserva = p.getDays();
        cost = diesReserva * preuXdia;
        return cost;
    }

    public void llogar() {
        llogat = true;
    }

    public void desllogar() {
        llogat = false;
    }
}
