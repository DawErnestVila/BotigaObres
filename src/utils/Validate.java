/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Validate {

    private static Scanner lector = new Scanner(System.in);

    /**
     * Funció que serveix per comprovar si l'usuari entra un Integer
     *
     * @param missatge Missatge que es mostrarà per pantalla abans que l'usuari entri el numero
     * @return Retorna l'usuari un cop comprovat que és un Integer
     */
    public static int llegirInt(String missatge) {
        //Mostrem el missatge
        System.out.println(missatge);
        //Fem un bucle fins que l'usuari entri un numero enter
        while (!lector.hasNextInt()) {
            //Si no entra un numero enter tornem a mostrar el missatge i a demanar que entri un nou numero
            System.out.println(missatge);
            lector.nextLine();
        }
        //Retornem el número que ha entrat
        return lector.nextInt();
    }

    public static int llegirIntSuperior0(String missatge) {
        //Mostrem el missatge
        System.out.println(missatge);
        //Fem un bucle fins que l'usuari entri un numero enter superior a 0
        int num;
        do {
            //Si no entra un numero enter superior a 0 tornem a mostrar el missatge i a demanar que entri un nou numero
            while (!lector.hasNextInt()) {
                System.out.println(missatge);
                lector.nextLine();
            }
            num = lector.nextInt();
            if (num < 0) {
                System.out.println("El número ha de ser superior a 0. Torna a introduir-lo: ");
            }
        } while (num < 0);
        //Retornem el número que ha entrat
        return num;
    }

    /**
     * Funció que serveix per comprovar si l'usuari entra un Double
     *
     * @param missatge Missatge que es mostrarà per pantalla abans que l'usuari entri el numero
     * @return Retorna l'usuari un cop comprovat que és un Double
     */
    public static double llegirDouble(String missatge) {
        //Mostrem el missatge
        System.out.println(missatge);
        //Fem un bucle fins que l'usuari entri un numero Double
        while (!lector.hasNextDouble()) {
            //Si no entra un numero Double tornem a mostrar el missatge i a demanar que entri un nou numero
            System.out.println(missatge);
            lector.nextLine();
        }
        //Retornem el número que ha entrat
        return lector.nextDouble();
    }

    public static String llegirStringLletres(String missatge) {
        String COMPROVAR_TEXT = "^[a-zA-Z\s]{3,40}$", text;
        //Mostrem el missatge
        System.out.println(missatge);
        lector.nextLine();
        //Guardem el text a comprovar
        text = lector.nextLine();
        //Fem un bucle fins que l'usuari entri un text que coincideix amb l'expressió regular
        while (!text.matches(COMPROVAR_TEXT)) {
            //Si no el text no coincideix li diem el que pot contenir i li tornem a demanar
            System.out.println("Només pots posar lletres i espais en blanc, entre 3 i 40 caracters");
            text = lector.nextLine();
        }
        //Retornem l'string
        return text;
    }

    public static LocalDate llegirData(String missatge) {
        String dataString;
        LocalDate data;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        do {
            System.out.println(missatge + " en format dd/MM/yyyy");
            dataString = lector.next();
            try {
                data = LocalDate.parse(dataString, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Format incorrecte. Torna a escriure la data");
                data = null;
            }
        } while (data == null);

        return data;
    }

    public static boolean llegirBool(String missatge) {
        String COMPROVAR_TEXT = "^(si|no)$", text;
        boolean boolRetornar;
        System.out.println(missatge);

        text = lector.next();

        while (!text.matches(COMPROVAR_TEXT)) {
            System.out.println("Només pots escriure si o no");
            text = lector.next();
        }

        boolRetornar = "si".equals(text);
        return boolRetornar;
    }
}
