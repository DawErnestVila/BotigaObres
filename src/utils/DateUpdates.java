/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author ernes
 */
public class DateUpdates {

    public static boolean checkReturnDate(LocalDate dataRetorn) {
        LocalDate avui = LocalDate.now();
        return avui.equals(dataRetorn);
    }

    public static int calculateDaysLeft(LocalDate dataRetorn) {
        int diesActualitzats = 0;
        LocalDate avui = LocalDate.now();
        diesActualitzats = (int) ChronoUnit.DAYS.between(avui, dataRetorn);
        return diesActualitzats;
    }

    public static boolean furtherDate(LocalDate dataRetorn) {
        LocalDate avui = LocalDate.now();

        if (dataRetorn.isAfter(avui)) {
            return true;
        } else {
            System.out.println("La data ha de ser com a minim la de dema");
            return false;
        }
    }
}
