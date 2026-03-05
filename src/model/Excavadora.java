/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author ernes
 */
public class Excavadora extends Maquina implements Serializable {

    private double capacitatPala; //Capacitat de la pala en m2

    public Excavadora(double capacitatPala, int id, int potencia, String model, double preuXdia, double pes, String combustible, boolean llogat, LocalDate dataInici, LocalDate dataRetorn, int diesReserva) {
        super(id, potencia, model, preuXdia, pes, combustible, llogat, dataInici, dataRetorn, diesReserva);
        this.capacitatPala = capacitatPala;
    }

    public Excavadora() {
    }

    public double getCapacitatPala() {
        return capacitatPala;
    }

    public void setCapacitatPala(double capacitatPala) {
        this.capacitatPala = capacitatPala;
    }

    public void printExcavadora() {
        super.printMaquina();
        //return super.printMaquina() + capacitatPala;
    }

}
