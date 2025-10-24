/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sismosbutant;
import java.time.LocalTime;
import java.time.LocalDate;
/**
 * Clase Temblor
 * Hereda de Sismos. Solo contiene los datos del padre.
 */
public class Temblor extends Sismo {

    public Temblor(float Magnitud, LocalDate Fecha, LocalTime Time, float Profundidad, float Alcance) {
        
        super(Magnitud, Fecha, Time, Profundidad, Alcance);
        
    }
    
    
    @Override
    public String toString() {
        
        return "Temblor{" + super.toString() + "}"; 
    }
}
