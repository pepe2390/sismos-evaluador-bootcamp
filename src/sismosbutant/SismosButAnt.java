/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sismosbutant;

import java.time.LocalDate;
import java.time.LocalTime;
/**
 *
 * @author EQUIPO 467
 */
public class SismosButAnt {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LocalDate inicio=LocalDate.of(1, 1, 1);
        LocalTime fin=LocalTime.of(1, 1, 1);
        Sismo s = new Sismo(0f, inicio, fin, 0f, 0f);
        Region r = new Region(null,0,0,0,0);
        s.setMagnitud();
        s.setFecha();
        s.mostrarTodo();
        
    }
}