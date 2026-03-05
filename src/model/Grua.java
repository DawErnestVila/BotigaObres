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
public class Grua extends Maquina implements Serializable {

    private int alcadaMax;

    public Grua(int alcadaMax, int id, int potencia, String model, double preuXdia, double pes, String combustible, boolean llogat, LocalDate dataInici, LocalDate dataRetorn, int diesReserva) {
        super(id, potencia, model, preuXdia, pes, combustible, llogat, dataInici, dataRetorn, diesReserva);
        this.alcadaMax = alcadaMax;
    }

    public Grua() {
    }

    public int getAlcadaMax() {
        return alcadaMax;
    }

    public void setAlcadaMax(int alcadaMax) {
        this.alcadaMax = alcadaMax;
    }

    public void printGrua() {
        super.printMaquina();
        //System.out.print(alcadaMax);
    }

}
