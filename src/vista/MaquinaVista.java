/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controllers.MaquinaController;
import model.Dumper;
import model.Excavadora;
import model.Generador;
import model.Grua;
import model.Maquina;
import utils.Validate;
import utils.Enums.*;

/**
 *
 * @author ernes
 */
public class MaquinaVista {

    private MaquinaController mc;

    public MaquinaVista(MaquinaController mc) {
        this.mc = mc;
        int opcio;
        do {
            mc.rewriteArray();
            mc.updateInfoDate();
            mc.rewriteFile();
            opcio = Validate.llegirInt("""
                                       \nSelecciona una de les opcions seguents:
                                       \t 1. Afegir una maquina
                                       \t 2. Eliminar una maquina
                                       \t 3. Editar una maquina
                                       \t 4. Mostrar maquines
                                       \t 5. Mostrar maquines sense llogar
                                       \t 0. Tirar enrere""");
            switch (opcio) {
                case 1:
                    mc.rewriteArray();
                    SeleccioAfegirMaquina();
                    mc.rewriteFile();
                    break;
                case 2:
                    mc.rewriteArray();
                    eliminarMaquina();
                    mc.rewriteFile();
                    break;
                case 3:
                    mc.rewriteArray();
                    editarMaquina();
                    mc.rewriteFile();
                    break;
                case 4:
                    mc.rewriteArray();
                    mostrarMaquines();
                    mc.rewriteFile();
                    break;
                case 5:
                    mc.rewriteArray();
                    mostrarMaquinesNoLlogades();
                    mc.rewriteFile();
                case 0:
                    break;
                default:
                    System.out.println("ERROR, OPCIO NO CORRECTE");
            }
        } while (opcio != 0);
    }

    private void SeleccioAfegirMaquina() {
        int opcio;
        do {
            opcio = Validate.llegirInt("""
                                       \nQuina maquina vols afegir:
                                       \t 1. Grua
                                       \t 2. Dumper
                                       \t 3. Excavadora
                                       \t 4. Generador
                                       \t 0. Cancelar""");
            switch (opcio) {
                case 1:
                    afegirGrua();
                    break;
                case 2:
                    afegirDumper();
                    break;
                case 3:
                    afegirExcavadora();
                    break;
                case 4:
                    afegirGenerador();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("ERROR, OPCIO NO CORRECTE");
            }
        } while (opcio != 0);
    }

    private void eliminarMaquina() {
        int opcio;
        mc.mostrarMaquines();
        opcio = Validate.llegirInt("\nSelecciona la maquina a eliminar (-1 per cancel.lar)");
        mc.eliminarMaquina(opcio);
    }

    private void editarMaquina() {
        int opcio;
        mc.mostrarMaquines();
        opcio = Validate.llegirInt("\nQuina maquina vols editar? (-1 per cancel.lar)");
        mc.editarMaquina(opcio);
    }

    private void afegirGrua() {
        demanarInfoMaquina("grua");
    }

    private void afegirDumper() {
        demanarInfoMaquina("dumper");
    }

    private void afegirExcavadora() {
        demanarInfoMaquina("excavadora");
    }

    private void afegirGenerador() {
        demanarInfoMaquina("generador");
    }

    private void demanarInfoMaquina(String funcioPropia) {
        String model, combustible = "";
        int potencia, diesReserva, funcioMaquinaInt, id, idCombustible;
        double preuXdia, pes, funcioMaqinaDouble;
        boolean seguir = false;
        id = mc.getNewId();
        model = Validate.llegirStringLletres("Escriu el nom del model");
        do {
            idCombustible = 0;
            System.out.println("");
            for (Combustible c : Combustible.values()) {
                System.out.println(idCombustible + ". " + c.getCombustible());
                idCombustible++;
            }
            System.out.println("");
            idCombustible = Validate.llegirIntSuperior0("Quin combustible utilitza?");
            switch (idCombustible) {
                case 0:
                    combustible = "Gasolina";
                    seguir = true;
                    break;
                case 1:
                    combustible = "Gasoil";
                    seguir = true;
                    break;
                case 2:
                    combustible = "Electricitat";
                    seguir = true;
                    break;
                default:
                    System.out.println("ERROR, LA OPCIO NO EXISTEIX");
                    seguir = false;
            }
            //combustible = Validate.llegirStringLletres("Quin combustible utilitza?");
        } while (!seguir);
        potencia = Validate.llegirIntSuperior0("Escriu la potencia que te");
        diesReserva = 0;
        preuXdia = Validate.llegirDouble("Entra el preu/dia de la maquina");
        pes = Validate.llegirDouble("Entra el pes de la maquina");
        switch (funcioPropia) {
            case "grua":
                funcioMaquinaInt = Validate.llegirIntSuperior0("Esctiu l'alçada maxima de la grua");
                Maquina grua = new Grua(funcioMaquinaInt, id, potencia, model, preuXdia, pes, combustible, false, null, null, diesReserva);
                mc.afegirMaquina(grua);
                break;
            case "dumper":
                funcioMaqinaDouble = Validate.llegirDouble("Escriu el pes maxim de carrega del dumper");
                Maquina dumper = new Dumper(funcioMaqinaDouble, id, potencia, model, preuXdia, pes, combustible, false, null, null, diesReserva);
                mc.afegirMaquina(dumper);
                break;
            case "excavadora":
                funcioMaqinaDouble = Validate.llegirDouble("Escriu la capacitat de la pala en m2");
                Maquina excavadora = new Excavadora(funcioMaqinaDouble, id, potencia, model, preuXdia, pes, combustible, false, null, null, diesReserva);
                mc.afegirMaquina(excavadora);
                break;
            case "generador":
                funcioMaquinaInt = Validate.llegirIntSuperior0("Escriu els Kw/h que fa el generador");
                Maquina generador = new Generador(funcioMaquinaInt, id, potencia, model, preuXdia, pes, combustible, false, null, null, diesReserva);
                mc.afegirMaquina(generador);
                break;
            default:
                System.out.println("ERROR, MAQUINA NO TROBADA");
        }
    }

    private void mostrarMaquines() {
        if (mc.getLlargMaquines() < 1) {
            System.out.println("\n NO HI HA MAQUINES, SI EN VOLS VEURE AFEGEIX-NE UNA");
        } else {
            mc.mostrarMaquines();
        }
    }

    private void mostrarMaquinesNoLlogades() {
        if (mc.getLlargMaquinesLlogar() < 1) {
            System.out.println("\n TOTES LES MAQUINES ESTAN LLOGADES\n");
        } else {
            mc.mostrarMaquinesLlogar();
        }
    }
}
