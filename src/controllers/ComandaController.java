/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Logger;
import model.Comanda;
import model.LiniaComanda;
import model.Maquina;

/**
 *
 * @author ernest
 */
public class ComandaController {

    private Comanda[] comandes;
    private MaquinaController mc;

    public ComandaController(MaquinaController mc) {
        comandes = new Comanda[0];
        this.mc = mc;
    }

    /**
     * Funcio que serveix per saber el llarg de l'array de comandes
     *
     * @return retorna la llargada de l'array
     */
    public int getLengthcomandes() {
        return comandes.length; // Retorna la longitud de l'array de comandes
    }

    /**
     * Funcio que serveix per mostrar una comanda
     *
     * @param idComanda id de la comanda a mostrar
     */
    public void mostrarComanda(int idComanda) {
        System.out.println("\n");
        comandes[idComanda].printComanda();
        System.out.println("\n");
        //System.out.println(comandes[idComanda].toString());
        //Comanda comanda = new Comanda(1);
    }

    /**
     * Funcio que serveix per tenir una nova id
     *
     * @return retorna la nova id de la comanda
     */
    public int getNewId() {
        int newId;
        if (comandes.length == 0) { // Si l'array de comandes està buit
            newId = 0; // El nou ID serà 0
        } else {
            newId = comandes[comandes.length - 1].getIdComanda() + 1; // El nou ID serà l'últim ID + 1
        }
        return newId; // Retorna el nou ID
    }

    /**
     * Funcio per aconseguir la posico de la comanda per la id
     *
     * @param idComanda id de la comanda que vols seleccionar
     * @return posicio dins de l'array de la comanda
     */
    public int getPosicioById(int idComanda) {
        int posicio = -2; // Inicialment la posició és -2 per si no es troba la comanda
        boolean trobat = false; // Inicialment la comanda no s'ha trobat
        while (!trobat) { // Mentre no s'hagi trobat la comanda
            if (idComanda == -1) { // Si l'ID és -1
                System.out.println("Cancelant..."); // Mostrem un missatge d'error
                posicio = -1; // Assignem -1 a la posició per indicar que s'ha cancel·lat l'operació
                break; // Sortim del bucle
            } else { // Si l'ID no és -1
                for (int i = 0; i < comandes.length; i++) { // Recorrem totes les comandes
                    if (idComanda == comandes[i].getIdComanda()) { // Si l'ID coincideix amb el de la comanda
                        posicio = i; // Assignem la posició on s'ha trobat la comanda
                        trobat = true; // Indiquem que s'ha trobat la comanda
                        break; // Sortim del bucle
                    }
                }
            }
            if (!trobat && posicio == -2) { // Si la comanda no s'ha trobat i la posició no ha estat assignada
                trobat = true; // Indiquem que s'ha acabat la cerca
            }
        }
        return posicio; // Retornem la posició de la comanda o -2 si no s'ha trobat
    }

    /**
     * Funcio per afegir una comadna a l'array (es fa servir per llegir el fitxer)
     *
     * @param comandaNova comanda nova a afegir
     */
    public void afegirComanda(Comanda comandaNova) {
        // Crea un array auxiliar amb una posició més que l'array actual de comandes
        Comanda[] aux = new Comanda[comandes.length + 1];
        // Copia totes les comandes de l'array actual al nou array auxiliar
        for (int i = 0; i < comandes.length; i++) {
            aux[i] = comandes[i];
        }
        // Afegeix la nova comanda al final del nou array auxiliar
        aux[aux.length - 1] = comandaNova;
        // Actualitza l'array de comandes amb el nou array auxiliar que conté la nova comanda afegida
        comandes = aux;
        // Guarda l'array de comandes actualitzat a un fitxer
        appendToFile();
    }

    /**
     * Funcio per eliminar una comanda de l'array
     *
     * @param posicio posicio de la comanda a eliminar
     */
    public void eliminarComanda(int posicio) {
        // Comprovem si la posició és vàlida
        if (posicio >= 0) {
            // Creem un array auxiliar amb una posició menys
            Comanda[] aux = new Comanda[comandes.length - 1];
            // Copiem totes les comandes excepte la que volem eliminar
            for (int i = 0, j = 0; i < comandes.length; i++) {
                if (i != posicio) {
                    aux[j] = comandes[i];
                    j++;
                }
            }
            // Assignem el nou array de comandes a la variable de classe
            comandes = aux;
        } else if (posicio == -2) {
            System.out.println("ERROR, NO EXISTEIX AQUESTA MAQUINA");
        }
    }

    /**
     * Una altre funcio per crear i afegir una comanda
     */
    public void crearComanda() {
        // Crear un array auxiliar amb una posició més que l'actual
        Comanda[] aux = new Comanda[comandes.length + 1];
        // Crear una nova comanda amb un ID nou i la màquina actual
        Comanda comanda = new Comanda(getNewId(), mc);
        // Copiar les comandes anteriors a l'array auxiliar
        for (int i = 0; i < comandes.length; i++) {
            aux[i] = comandes[i];
        }
        // Afegir la nova comanda a l'última posició de l'array auxiliar
        aux[aux.length - 1] = comanda;
        // Actualitzar la llista de comandes amb l'array auxiliar que conté la nova comanda
        comandes = aux;
    }

    /**
     * Funcio per afegir informacio dins la comanda
     *
     * @param idMaquina id de maquina llogada
     * @param posicio posico de la maquina dins l'array
     * @param dies dies que es lloga la maquina
     */
    public void afegirAComanda(int idMaquina, int posicio, int dies) {
        // Actualitzar els dies de la màquina en la posició especificada
        mc.setDies(posicio, dies);

        // Crear una nova línia de comanda
        LiniaComanda liniaComanda = new LiniaComanda(comandes[comandes.length - 1].getLineId(), idMaquina, dies, mc.calcularPreu(posicio, dies), comandes[comandes.length - 1].getIdComanda(), mc);

        // Afegir la línia de comanda a la comanda més recent
        comandes[comandes.length - 1].afegirLiniaComanda(liniaComanda);
    }

    /**
     * Funcio per caluclar l'import total de la comanda
     */
    public void calcularTotalComanda() {
        //Sumem els subtotals de totes les linies i ho asignem al total de la comanda
        comandes[comandes.length - 1].setTotal(comandes[comandes.length - 1].sumarSubtotals());
    }

    /**
     * Funcio que serveix per cancel·lar la ultima comanda
     */
    public void cancelComanda() {
        // Creem un nou array de Comanda amb una posició menys que l'original
        Comanda[] aux = new Comanda[comandes.length - 1];

        // Copiem totes les comandes menys l'última a l'array auxiliar
        for (int i = 0; i < aux.length; i++) {
            aux[i] = comandes[i];
        }

        // Assignem l'array auxiliar a l'array de comandes per substituir la comanda cancel·lada
        comandes = aux;
    }

    /**
     * Fuincio que serveix per escriure un objecte al final del fitxer
     */
    public void appendToFile() {
        // Ruta on es guardarà el fitxer
        String ruta = "src/fitxers/comandes.txt";
        try {
            // Es crea l'objecte per escriure al fitxer
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta, true));

            // S'escriuen totes les comandes al fitxer
            for (Comanda c : comandes) {
                oos.writeObject(c);
                oos.flush();
            }

            // Escriu null per indicar que s'ha acabat d'escriure
            oos.writeObject(null);
            oos.flush();
            oos.close();
        } catch (IOException ex) {
            // En cas d'error es mostra per pantalla
            System.out.println(ex.toString());
        }
    }

    /**
     * Funció que serveix per reescriure el fitxer
     */
    public void writeFileComandes() {
        // Ruta del fitxer on es guardaran les comandes
        String ruta = "src/fitxers/comandes.txt";
        try {
            // Crear un objecte ObjectOutputStream per escriure les comandes a un fitxer
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta));

            // Recórrer totes les comandes i escriure-les al fitxer
            for (Comanda c : comandes) {
                oos.writeObject(c);
                oos.flush();
            }

            // Escriure null per indicar que hem acabat de guardar les comandes
            oos.writeObject(null);
            oos.flush();
            oos.close();
        } catch (IOException ex) {
            // En cas d'error, mostrar-lo per pantalla
            System.out.println(ex.toString());
        }
    }

    /**
     * Escriu la informació del fitxer a l'array
     */
    public void writeArrayComandes() {
        //Definim la ruta del fitxer on guardem les comandes
        String ruta = "src/fitxers/comandes.txt";
        //Inicialitzem una nova comanda
        Comanda comanda = new Comanda();
        try {
            //Creem un array de comandes buit
            comandes = new Comanda[0];
            //Inicialitzem un ObjectInputStream per llegir les comandes guardades al fitxer
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta));
            //Llegim la primera comanda del fitxer
            comanda = (Comanda) ois.readObject();
            //Mentre hi hagi comandes en el fitxer, les afegim a l'array de comandes
            while (comanda != null) {
                afegirComanda(comanda);
                //Llegim la següent comanda del fitxer
                comanda = (Comanda) ois.readObject();
            }
        } catch (IOException | ClassNotFoundException ex) {
            //En cas d'error, mostrem el missatge d'error al logger
            Logger.getLogger(ex.toString());
        }
    }
}
