/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

public class Enums {

    public enum Combustible {
        GASOLINA, GASOIL, ELECTRICITAT;

        public String getCombustible() {
            return this.name();
        }
    }

}
