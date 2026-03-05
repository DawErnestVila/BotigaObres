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
public class MainVista {

    public MainVista() {
        MaquinaController mc = new MaquinaController();
        ComandaController cc = new ComandaController(mc);

        int opcio;
        do {
            opcio = Validate.llegirInt("""
                                        Selecciona una de les opcions seguents:
                                        \t 1. Gestionar Maquines
                                        \t 2. Gestionar Comandes
                                        \t 0. Sortir
                                       """);
            switch (opcio) {
                case 1:
                    MaquinaVista mv = new MaquinaVista(mc);
                    break;
                case 2:
                    ComandaVista cv = new ComandaVista(cc, mc);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Error, opcio no correcte");
            }
        } while (opcio != 0);
    }

}
