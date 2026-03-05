/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.io.Serializable;
import model.LiniaComanda;

public class LiniaController implements Serializable {

    private LiniaComanda[] linies;
    private MaquinaController mc;

    public LiniaController(MaquinaController mc) {
        linies = new LiniaComanda[0];
        this.mc = mc;
    }

    /**
     * Funcio que serveix per afegir linies a l'array
     *
     * @param liniaIn linia a afegir
     */
    public void afegirLinies(LiniaComanda liniaIn) {
        // Creem un array auxiliar per afegir la nova línia de comanda
        LiniaComanda[] aux = new LiniaComanda[linies.length + 1];

        // Copiem les dades de l'array original a l'array auxiliar
        for (int i = 0; i < linies.length; i++) {
            aux[i] = linies[i];
        }

        // Afegim la nova línia de comanda a l'última posició de l'array auxiliar
        aux[aux.length - 1] = liniaIn;

        // Assignem l'array auxiliar a la variable de classe linies
        linies = aux;
    }

    /**
     * Funcio que serveix per tenir una nova id
     *
     * @return retorna la nova id
     */
    public int getNewId() {
        int newId;
        if (linies.length == 0) {
            // Si no hi ha cap línia, el nou id serà 0.
            newId = 0;
        } else {
            // Si ja hi ha línies, el nou id serà l'últim id més 1.
            newId = linies[linies.length - 1].getIdLinia() + 1;
        }
        return newId;
    }

    /**
     * Funcio que serveix per borrar l'array
     */
    public void borrarArray() {
        //borra l'array
        linies = new LiniaComanda[0];
    }

    /**
     * Funcio que serveix per sumar els subtotals de les linies
     *
     * @return retorna el total de les sumes
     */
    public double sumarSubtotals() {
        // Inicialitzem la variable que contindrà el total dels subtotals a 0.
        double totalSubtotals = 0;

        // Recorrem totes les línies de comanda i sumem els seus subtotals a la variable total.
        for (LiniaComanda linia : linies) {
            totalSubtotals += linia.getSubtotalLinia();
        }

        // Retornem el total dels subtotals.
        return totalSubtotals;
    }

    /**
     * Funcio per printejar les linies
     */
    public void printLinies() {
        // Imprimeix el títol de les columnes de la taula
        System.out.println("|      ID LINIA       MAQUINA       DIES        SUBTOTAL       |");

        // Imprimeix la línia divisòria de la taula
        System.out.println("|   --------------------------------------------------------   |");

        // Imprimeix les dades de cada línia de comanda
        for (LiniaComanda linia : linies) {
            System.out.printf("|        %3s. %15s     %5s           %-10.2f   |\n", linia.getIdLinia(), mc.getNomById(linia.getIdMaquina()), linia.getDiesLlogat(), linia.getSubtotalLinia());

            // Imprimeix una línia en blanc per separar les línies de comanda
            System.out.println("|                                                              |");
        }
    }
}
