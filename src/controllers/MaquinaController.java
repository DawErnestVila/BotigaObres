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
import java.io.Serializable;
import java.time.LocalDate;
import java.util.logging.Logger;
import model.Dumper;
import model.Excavadora;
import model.Generador;
import model.Grua;
import model.Maquina;
import utils.DateUpdates;
import utils.Validate;

/**
 *
 * @author ernes
 */
public class MaquinaController implements Serializable {

    private Maquina[] maquines;

    public MaquinaController() {
        maquines = new Maquina[0];
    }

    /**
     * Mostra les dades de totes les màquines que es troben en la llista de màquines. Les dades que es mostren són les següents: ID, MODEL, COMBUSTIBLE, PREU X DIA, ESTAT LLOGUER, DIES LLOGAT, PES, DATA INICI, DATA FI. Les dades són mostrades en una taula amb un format específic. Si la màquina no té un tipus vàlid, es mostra un missatge d'error.
     */
    public void mostrarMaquines() {
        // variable per a guardar el nom de la classe de la màquina
        String classe;

        // imprimir les capçaleres de la taula
        System.out.println("");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("-- ID            MODEL       COMBUSTIBLE       PREU X DIA        ESTAT LLOGUER       DIES LLOGAT     PES         DATA INICI           DATA FI --");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");

        // per a cada màquina en la llista de màquines, imprimir les seves dades
        for (Maquina m : maquines) {
            // obtenir el nom de la classe de la màquina
            classe = m.getClass().getSimpleName();

            // imprimir les dades de la màquina segons el seu tipus
            switch (classe) {
                case "Grua":
                    ((Grua) m).printGrua();
                    break;
                case "Dumper":
                    ((Dumper) m).printDumper();
                    break;
                case "Excavadora":
                    ((Excavadora) m).printExcavadora();
                    break;
                case "Generador":
                    ((Generador) m).printGenerador();
                    break;
                default:
                    System.out.println("ERROR, MAQUINA INCORRECTE A L'HORA DE MOSTRAR");
            }
        }

        // imprimir la línia de separació i el final de la taula
        System.out.println("--                                                                                                                                            --");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    /**
     * *
     * Mostra les màquines disponibles per a llogar amb les seves dades corresponents.
     */
    public void mostrarMaquinesLlogar() {
        String classe; // String per emmagatzemar el nom de la classe de la màquina
        System.out.println("");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("-- ID            MODEL       COMBUSTIBLE       PREU X DIA        ESTAT LLOGUER       DIES LLOGAT     PES         DATA INICI           DATA FI --");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
        for (Maquina m : maquines) { // Per cada màquina en la llista de maquines
            if (m.isLlogat() == false) { // Si la màquina no està llogada
                classe = m.getClass().getSimpleName(); // Obtenir el nom de la classe de la màquina
                switch (classe) { // Switch per a imprimir les dades de la màquina segons el seu tipus
                    case "Grua":
                        ((Grua) m).printGrua();
                        break;
                    case "Dumper":
                        ((Dumper) m).printDumper();
                        break;
                    case "Excavadora":
                        ((Excavadora) m).printExcavadora();
                        break;
                    case "Generador":
                        ((Generador) m).printGenerador();
                        break;
                    default: // Si la màquina no és de cap tipus conegut, mostrar un missatge d'error
                        System.out.println("ERROR, MAQUINA INCORRECTE A L'HORA DE MOSTRAR");
                }
            }
        }
        System.out.println("--                                                                                                                                            --");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    /**
     * Aquesta funció serveix per aconseguir una nova ID
     *
     * @return retorna la nova ID
     */
    public int getNewId() {
        int newId;
        if (maquines.length == 0) { // Si no hi ha cap màquina, l'identificador és 0
            newId = 0;
        } else { // Si hi ha màquines, l'identificador és el darrera + 1
            newId = maquines[maquines.length - 1].getId() + 1;
        }
        return newId;
    }

    /**
     * Funció per afegir una maquina nova
     *
     * @param maquinaNova maquina a afegir a l'aray
     */
    public void afegirMaquina(Maquina maquinaNova) {
        // Creem un array auxiliar amb una posició addicional
        Maquina[] aux = new Maquina[maquines.length + 1];
        // Copiem les maquines de l'array original a l'array auxiliar
        for (int i = 0; i < maquines.length; i++) {
            aux[i] = maquines[i];
        }
        // Afegim la nova màquina a la última posició de l'array auxiliar
        aux[aux.length - 1] = maquinaNova;
        // Actualitzem l'array original amb l'array auxiliar que conté la nova màquina
        maquines = aux;
        // Afegim la informació de la nova màquina al fitxer de registre
        appendTofile();
    }

    /**
     * Aquesta funció serveix per eliminmar una maquina
     *
     * @param idMaquina id de la maquina a eilimnar
     */
    public void eliminarMaquina(int idMaquina) {
        int posicio; // Variable per emmagatzemar la posició de la màquina a eliminar.
        posicio = getPosicioById(idMaquina); // Es crida la funció "getPosicioById" per obtenir la posició de la màquina amb l'ID proporcionat.
        if (posicio >= 0) { // Si la posició és vàlida (és a dir, no és -1 ni -2)
            Maquina[] aux = new Maquina[maquines.length - 1]; // Es crea un array auxiliar per emmagatzemar totes les màquines excepte la que s'ha d'eliminar.
            for (int i = 0, j = 0; i < maquines.length; i++) { // S'inicia un bucle per recórrer totes les màquines.
                if (i != posicio) { // Si la posició actual no és la que s'ha d'eliminar
                    aux[j] = maquines[i]; // S'afegeix la màquina a l'array auxiliar.
                    j++; // Es mou l'índex de l'array auxiliar a la següent posició.
                }
            }
            maquines = aux; // S'actualitza l'array original amb l'array auxiliar que no té la màquina eliminada.
        } else if (posicio == -2) { // Si la posició és -2 (és a dir, l'ID de la màquina no existeix a l'array)
            System.out.println("ERROR, NO EXISTEIX AQUESTA MAQUINA"); // Es mostra un missatge d'error.
        }
    }

    /**
     * Funcio per editar una maquina
     *
     * @param idMaquina id de la maquina a editar
     */
    public void editarMaquina(int idMaquina) {
        int posicio, opcio;
        String classe;
        // Obtenir la posició de la màquina amb l'id especificat
        posicio = getPosicioById(idMaquina);
        if (posicio >= 0) {
            // Mostrar les opcions d'edició per a cada tipus de màquina
            System.out.print("""
                           \nQue vols editar?
                           1. Model
                           2. Combustible
                           3. Potencia
                           4. Preu
                           5. Pes
                           6. Estat lloguer
                           7. Data Retorn
                           """);
            // Obtindre la classe de la màquina en la posició especificada
            classe = maquines[posicio].getClass().getSimpleName();
            switch (classe) {
                case "Grua":
                    // Mostrar les opcions d'edició específiques de les grues
                    opcio = Validate.llegirInt("""
                                           8. Alcada Maxima
                                           0. Cancelar
                                           """);
                    // Demanar la nova informació i actualitzar la màquina
                    demanarInfoEditar(posicio, opcio, classe);
                    break;
                case "Dumper":
                    // Mostrar les opcions d'edició específiques dels dumpers
                    opcio = Validate.llegirInt("""
                                           8. Carrega Maxima
                                           0. Cancelar
                                           """);
                    // Demanar la nova informació i actualitzar la màquina
                    demanarInfoEditar(posicio, opcio, classe);
                    break;
                case "Excavadora":
                    // Mostrar les opcions d'edició específiques de les excavadores
                    opcio = Validate.llegirInt("""
                                           8. Capacitat Pala
                                           0. Cancelar
                                           """);
                    // Demanar la nova informació i actualitzar la màquina
                    demanarInfoEditar(posicio, opcio, classe);
                    break;
                case "Generador":
                    // Mostrar les opcions d'edició específiques dels generadors
                    opcio = Validate.llegirInt("""
                                           8. Kw/h Genera
                                           0. Cancelar
                                           """);
                    // Demanar la nova informació i actualitzar la màquina
                    demanarInfoEditar(posicio, opcio, classe);
                    break;
                default:
                    // Si la màquina no pertany a cap dels tipus especificats, mostrar un missatge d'error
                    System.out.println("ERROR, MAQUINA INCORRECTE A L'HORA D'EDITAR");
            }
        } else if (posicio == -2) {
            // Si no s'ha trobat la màquina amb l'id especificat, mostrar un missatge d'error
            System.out.println("ERROR, NO EXISTEIX AQUESTA MAQUINA");
        }
    }

    /**
     * Funció que complementa a la d'editar, es on demana la informacioó
     *
     * @param posicio posició dins l'array de la maquina
     * @param opcio opcio que es vol editar
     * @param classe classe filla de la maquina
     */
    private void demanarInfoEditar(int posicio, int opcio, String classe) {
        switch (opcio) {
            case 1://Editar el model
                maquines[posicio].setModel(Validate.llegirStringLletres("Entra el nom model de la maquina"));
                break;
            case 2://Editar el combustible
                maquines[posicio].setCombustible(Validate.llegirStringLletres("Entra el nou combustible que utilitza la maquina"));
                break;
            case 3://Editar la potencia
                maquines[posicio].setPotencia(Validate.llegirInt("Entra la nova potencia"));
                break;
            case 4://Editar el Preu X Dia
                maquines[posicio].setPreuXdia(Validate.llegirDouble("Entra el nou Preu x Dia"));
                break;
            case 5://Editar el pes
                maquines[posicio].setPes(Validate.llegirDouble("Entra el nou pes de la maquina"));
                break;
            case 6: //Editar el bool llogat
                maquines[posicio].setLlogat(Validate.llegirBool("Esta llogada la maquina? (si/no)"));
                if (maquines[posicio].isLlogat() == false) {
                    maquines[posicio].setDataInici(null);
                    maquines[posicio].setDataRetorn(null);
                    maquines[posicio].setDiesReserva(0);
                }
                break;
            case 7: //Editar Data de retorn
                do {
                    maquines[posicio].setDataRetorn(Validate.llegirData("Entra la nova data"));
                } while (!DateUpdates.furtherDate(maquines[posicio].getDataRetorn()));
                break;
            case 8://Funcionalitat extra de cada màquina
                switch (classe) {
                    case "Grua"://Editar l'alçada màxima
                        ((Grua) maquines[posicio]).setAlcadaMax(Validate.llegirInt("Entra la nova alcada maxima"));
                        break;
                    case "Dumper"://Editar la càrrega màxima
                        ((Dumper) maquines[posicio]).setCarregaMax(Validate.llegirDouble("Entra la nova carrega maxima"));
                        break;
                    case "Excavadora"://Editar la capacitat de la pala
                        ((Excavadora) maquines[posicio]).setCapacitatPala(Validate.llegirDouble("Entra la nova capacitat de la pala"));
                        break;
                    case "Generador"://Editar els Kw/H del generador
                        ((Generador) maquines[posicio]).setKwH(Validate.llegirInt("Entra els nous Kw/h que genera"));
                        break;
                    default:
                        System.out.println("Error a l'editar la part especifica de la maquina");
                }
                break;
            case 0://Tirar enrere
                break;
            default:
                System.out.println("ERROR, NO ES POT EDITAR UNA OPCIO QUE NO EXISTEIX");
        }
    }

    /**
     * Funció que et retorna la maquina per la id
     *
     * @param idMaquina id de la maquina
     * @return l'objete de maquina corresponent al id
     */
    public Maquina getMaquinaById(int idMaquina) {
        // Iterem per totes les màquines de la llista
        for (Maquina maquina : maquines) {
            // Si trobem una màquina amb el mateix ID que el que busquem
            if (maquina.getId() == idMaquina) {
                // Retornem la màquina
                return maquina;
            }
        }
        // Si no s'ha trobat cap màquina amb l'ID buscat, retornem null
        return null;
    }

    /**
     * Funció per aconseguir la posicio de l'array segons la id
     *
     * @param idMaquina id de la maquina que es vol seleccionar
     * @return es retorna la posicio dins l'array
     */
    public int getPosicioById(int idMaquina) {
        int posicio = -2; // Inicialitzem la posició a -2
        boolean trobat = false; // Inicialitzem la variable trobat a false
        while (!trobat) { // Iniciem un bucle while que es repetirà mentre no haguem trobat la màquina o cancel·lat la cerca
            if (idMaquina == -1) { // Si l'id és -1, es cancel·la la cerca i es retorna -1
                System.out.println("Cancelant...");
                posicio = -1;
                break;
            } else { // En cas contrari, busquem la màquina dins de l'array
                for (int i = 0; i < maquines.length; i++) {
                    if (idMaquina == maquines[i].getId()) { // Si l'id de la màquina coincideix amb l'id passat per paràmetre, guardem la posició i sortim del bucle
                        posicio = i;
                        trobat = true;
                        break;
                    }
                }
            }
            if (!trobat && posicio == -2) { // Si no s'ha trobat la màquina i la posició no s'ha modificat, sortim del bucle
                trobat = true;
            }
        }
        return posicio; // Retornem la posició
    }

    /**
     * Funcio que retorna el nom de la maquina segons la id
     *
     * @param idMaquina id de la maquina que es vol el nom
     * @return retorna el nom del model
     */
    public String getNomById(int idMaquina) {
        String nom; // variable on es guardarà el nom del model de la màquina
        int posicio; // variable on es guardarà la posició de la màquina dins l'array de màquines

        posicio = getPosicioById(idMaquina); // obtenir la posició de la màquina amb l'id proporcionat

        nom = maquines[posicio].getModel(); // obtenir el nom del model de la màquina a partir de la seva posició dins l'array

        return nom; // retornar el nom del model de la màquina
    }

    /**
     * Funció per calcular el preu del lloguer
     *
     * @param posicio posició de la màquina dins l'array
     * @param dies dies que es lloga la maquina
     * @return es retorna el preu del lloguer
     */
    public double calcularPreu(int posicio, int dies) {
        double preu; // Variable on es guardarà el preu del lloguer.
        // Es calcula el preu multiplican el preu per dia de la màquina per el nombre de dies llogats.
        preu = maquines[posicio].getPreuXdia() * dies;
        // S'actualitzen les dades de la màquina llogada.
        maquines[posicio].setLlogat(true);
        maquines[posicio].setDataInici(LocalDate.now());
        maquines[posicio].setDataRetorn(maquines[posicio].getDataInici().plusDays(dies));
        // Es retorna el preu total del lloguer.
        return preu;
    }

    /**
     * Funció que serveix per tornar la posició fent servir l'array, pero que esta fet perque funcioni la opcio de llogar
     *
     * @param idMaquina id de la maquina a seleccionar
     * @return posicio dins l'array de la maquina
     */
    public int getPosicioByIdLloguer(int idMaquina) {
        int posicio = -2; // Inicialitzem la posició a -2 per saber si s'ha trobat la màquina o no
        boolean trobat = false; // Bandera per indicar si s'ha trobat la màquina o no
        while (!trobat) {
            if (idMaquina == -1) { // Si s'ha introduït -1, es cancel·la la cerca
                posicio = -1;
                break;
            } else {
                for (int i = 0; i < maquines.length; i++) { // Cerquem la màquina pel seu id
                    if (idMaquina == maquines[i].getId() && maquines[i].isLlogat() == false) { // Si la màquina té l'estat de llogada a false, és disponible
                        posicio = i; // Guardem la posició de la màquina
                        trobat = true; // Indiquem que hem trobat la màquina
                        break; // Sortim del bucle for
                    }
                }
            }
            if (!trobat && posicio == -2) { // Si no hem trobat la màquina i la posició és -2, és perquè no hi ha màquines disponibles
                trobat = true;
            }
        }
        return posicio;
    }

    /**
     * Funció per veure com de llarg és l'array de maquines
     *
     * @return retorna la llargada de l'array
     */
    public int getLlargMaquines() {
        //Retorna la llargada de l'array de maquines
        return maquines.length;
    }

    /**
     * Funció per calcular el nombre de maquines per poder llogar
     *
     * @return retorna el nombre de maquines disponibles per llogar
     */
    public int getLlargMaquinesLlogar() {
        // Inicialitzem la variable que contindrà el nombre de màquines disponibles
        int quantitat = 0;

        // Recorrem totes les màquines de l'array
        for (Maquina m : maquines) {
            // Si la màquina no està llogada incrementem la variable quantitat
            if (m.isLlogat() == false) {
                quantitat++;
            }
        }

        // Retornem la quantitat de màquines disponibles
        return quantitat;
    }

    /**
     * Funció per establir els dies
     *
     * @param posicio posicio de la maquina dins l'array
     * @param diesIn dies que es llogua la maquina
     */
    public void setDies(int posicio, int diesIn) {
        //Cambiem els dies de lloguer de la maquina
        maquines[posicio].setDiesReserva(diesIn);
    }

    /**
     * Funcio que serveix per comprovar les dates de les maquines i actualitzar la informacio depenent de la data
     */
    public void updateInfoDate() {
        boolean mateixDia; // Variable per a emmagatzemar si la data de retorn és el mateix dia
        for (Maquina m : maquines) { // Bucle per a cada màquina de la llista de maquines
            if (m.getDataRetorn() != null) { // Si la data de retorn de la màquina no és nul·la (és a dir, ha estat llogada)
                mateixDia = DateUpdates.checkReturnDate(maquines[m.getId()].getDataRetorn()); // Comprova si la data de retorn és el mateix dia
                if (mateixDia) { // Si és el mateix dia
                    m.setLlogat(false); // Estableix que la màquina ja no està llogada
                    m.setDataInici(null); // Estableix que la data d'inici és nul·la
                    m.setDataRetorn(null); // Estableix que la data de retorn és nul·la
                    m.setDiesReserva(0); // Estableix el nombre de dies de reserva com a zero
                } else { // Si no és el mateix dia
                    m.setDiesReserva(DateUpdates.calculateDaysLeft(m.getDataRetorn())); // Calcula el nombre de dies que falten per a la data de retorn i ho estableix com a dies de reserva
                }
            }
        }
    }

    /**
     * Fuincio que serveix per escriure un objecte al final del fitxer
     */
    public void appendTofile() {
        String ruta = "src/fitxers/maquines.txt"; // Estableix la ruta del fitxer on s'afegirà la informació de les màquines
        try {
            // Crea un objecte ObjectOutputStream per a escriure les màquines al fitxer
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta, true));

            // Escriu cada màquina de la llista de maquines al fitxer
            for (Maquina m : maquines) {
                oos.writeObject(m);
                oos.flush(); // Força la sortida d'objectes per a assegurar que s'escriuen al fitxer
            }

            // Escriu un objecte nul·la per a indicar que s'ha acabat de llegir les màquines
            oos.writeObject(null);
            oos.flush(); // Força la sortida d'objectes per a assegurar que s'escriuen al fitxer
            oos.close(); // Tanca el fluxe d'escriptura
        } catch (IOException ex) { // Captura l'excepció si hi ha algun problema amb la lectura o escriptura del fitxer
            System.out.println(ex.toString()); // Mostra l'error per pantalla
        }
    }

    /**
     * Funció que serveix per reescriure el fitxer
     */
    public void rewriteFile() {
        String ruta = "src/fitxers/maquines.txt"; // Estableix la ruta del fitxer on es reescriurà la informació de les màquines
        try {
            // Crea un objecte ObjectOutputStream per a escriure les màquines al fitxer, es sobreescriurà la informació que hi hagi
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta));

            // Escriu cada màquina de la llista de maquines al fitxer
            for (Maquina m : maquines) {
                oos.writeObject(m);
                oos.flush(); // Força la sortida d'objectes per a assegurar que s'escriuen al fitxer
            }

            // Escriu un objecte nul·la per a indicar que s'ha acabat de llegir les màquines
            oos.writeObject(null);
            oos.flush(); // Força la sortida d'objectes per a assegurar que s'escriuen al fitxer
            oos.close(); // Tanca el fluxe d'escriptura
        } catch (IOException ex) { // Captura l'excepció si hi ha algun problema amb la lectura o escriptura del fitxer
            System.out.println(ex.toString()); // Mostra l'error per pantalla
        }
    }

    /**
     * Escriu la informació del fitxer a l'array
     */
    public void rewriteArray() {
        String ruta = "src/fitxers/maquines.txt"; // Estableix la ruta del fitxer on es troba la informació de les màquines
        Maquina maquina = new Maquina(); // Crea una nova instància de Maquina
        try {
            maquines = new Maquina[0]; // Crea un nou array de màquines buit
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta)); // Crea un objecte ObjectInputStream per a llegir les màquines del fitxer
            maquina = (Maquina) ois.readObject(); // Llegeix la primera màquina del fitxer
            while (maquina != null) { // Repeteix mentre no arribem al final del fitxer
                afegirMaquina(maquina); // Afegeix la màquina llegida a l'array de maquines
                maquina = (Maquina) ois.readObject(); // Llegeix la següent màquina del fitxer
            }
            ois.close(); // Tanca el fluxe de lectura del fitxer
        } catch (IOException | ClassNotFoundException ex) { // Captura l'excepció si hi ha algun problema amb la lectura o la classe Maquina no es pot trobar
            Logger.getLogger(ex.toString()); // Mostra l'error per pantalla
        }
    }
}
