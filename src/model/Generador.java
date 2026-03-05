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
public class Generador extends Maquina implements Serializable {

    private int kwH;

    public Generador(int kwH, int id, int potencia, String model, double preuXdia, double pes, String combustible, boolean llogat, LocalDate dataInici, LocalDate dataRetorn, int diesReserva) {
        super(id, potencia, model, preuXdia, pes, combustible, llogat, dataInici, dataRetorn, diesReserva);
        this.kwH = kwH;
    }

    public Generador() {
    }

    public int getKwH() {
        return kwH;
    }

    public void setKwH(int kwH) {
        this.kwH = kwH;
    }

    public void printGenerador() {
        super.printMaquina();
        //return super.printMaquina() + kwH;
    }

}
