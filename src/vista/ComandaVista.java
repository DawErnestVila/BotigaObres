/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controllers.ComandaController;
import controllers.MaquinaController;
import utils.Validate;

/**
 *
 * @author ernes
 */
public class ComandaVista {

    private ComandaController cc;
    private MaquinaController mc;

    public ComandaVista(ComandaController cc, MaquinaController mc) {
        this.cc = cc;
        this.mc = mc;
        int opcio;
        do {

            opcio = Validate.llegirInt("""
                                       \nSelecciona una de les opcions seguents
                                       \t 1. Fer una comanda
                                       \t 2. Eliminar una comanda
                                       \t 3. Mostrar totes les comandes
                                       \t 0. Tirar enrere
                                       """);
            switch (opcio) {
                case 1:
                    mc.rewriteArray();
                    cc.writeArrayComandes();
                    ferComanda();
                    cc.writeFileComandes();
                    mc.rewriteFile();
                    break;
                case 2:
                    cc.writeArrayComandes();
                    eliminarComanda();
                    cc.writeFileComandes();
                    break;
                case 3:
                    cc.writeArrayComandes();
                    mostrarTotesComandes();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("\nERROR, OPCIO INCORRECTE\n");
            }
        } while (opcio != 0);
    }

    private void mostrarTotesComandes() {
        for (int i = 0; i < cc.getLengthcomandes(); i++) {
            cc.mostrarComanda(i);
        }
    }

    private void eliminarComanda() {
        int opcio = -1, posicio = -1;
        boolean trobat = false;

        do {
            mostrarTotesComandes();
            opcio = Validate.llegirIntSuperior0("\nQuina comanda vols eliminar?");
            posicio = cc.getPosicioById(opcio);
            if (posicio == -2) {
                System.out.println("\nERROR, COMANDA NO TROBADA\n");
                trobat = false;
            } else {
                trobat = true;
            }
        } while (!trobat);
        if (trobat && posicio != -1) {
            cc.eliminarComanda(posicio);
        }
    }

    private void ferComanda() { //Crear una línia amb la maquina que és i despres cridar la funció d'afegir linia al linia controller
        int opcio, posicio = 0, diesALlogar = 0, quantitatLlogat = 0;
        boolean trobat = false;

        if (mc.getLlargMaquinesLlogar() > 0) {
            cc.crearComanda();
            do {
                do {
                    System.out.println("");
                    mc.mostrarMaquinesLlogar();
                    System.out.println("-1. Tirar enrere");
                    opcio = Validate.llegirInt("\nQuina maquina vols llogar?");
                    posicio = mc.getPosicioByIdLloguer(opcio);
                    if (posicio == -2) {
                        System.out.println("\nERROR, MAQUINA NO TROBADA\n");
                        trobat = false;
                    } else {
                        trobat = true;
                    }
                } while (!trobat);
                if (trobat && posicio != -1) {
                    diesALlogar = Validate.llegirIntSuperior0("\nQuants dies vols llogar la maquina?");
                    if (diesALlogar > 0) {
                        cc.afegirAComanda(opcio, posicio, diesALlogar);
                        quantitatLlogat++;
                    }
                } else if (posicio == -1) {
                    break;
                }
            } while (opcio != -1 && mc.getLlargMaquinesLlogar() > 0);
            if (quantitatLlogat > 0 && diesALlogar > 0) {
                cc.calcularTotalComanda();
            }
            if (quantitatLlogat == 0) {
                cc.cancelComanda();
            }
        } else {
            System.out.println("NO HI HA MAQUINES PER LLOGAR ACTUALMENT");
        }
    }
}
