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
public class Dumper extends Maquina implements Serializable {

    private double carregaMax; //Càrrega màxima en kg

    public Dumper(double carregaMax, int id, int potencia, String model, double preuXdia, double pes, String combustible, boolean llogat, LocalDate dataInici, LocalDate dataRetorn, int diesReserva) {
        super(id, potencia, model, preuXdia, pes, combustible, llogat, dataInici, dataRetorn, diesReserva);
        this.carregaMax = carregaMax;
    }

    public Dumper() {
    }

    public double getCarregaMax() {
        return carregaMax;
    }

    public void setCarregaMax(double carregaMax) {
        this.carregaMax = carregaMax;
    }

    public void printDumper() {
        super.printMaquina();
        //return super.printMaquina() + carregaMax;
    }

}
