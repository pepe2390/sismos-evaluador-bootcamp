/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analisadorsismos1;
/**
 * @author Luca
 */
import java.time.LocalDate;

/**
 * Clase Hija que extiende Sismo.
 * Representa un sismo de alta intensidad (ej. >= 7.0).
 */
public class Terremoto extends Sismo {

    public Terremoto(double magnitud, LocalDate fecha, String nombreProvinciaAfectada, 
                     String nombreRegionAfectada, int provinciaId) {
        // Llama al constructor del padre con el ID
        super(magnitud, fecha, nombreProvinciaAfectada, nombreRegionAfectada, provinciaId);
    }

    @Override
    public String getNivelPeligro() {
        return "ALTO - Acción Inmediata Requerida";
    }
}