/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sismosbutant;

import java.time.LocalDate;
import java.util.Objects;
import java.time.LocalTime;

public class Terremoto extends Sismo {
    
    
    private Destruccion datosDestruccion; 

    
    public Terremoto(float Magnitud, LocalDate Fecha, LocalTime Time, float Profundidad, float Alcance, String Mercalli, int Fallecidos_H, int Fallecidos_M, float Indemnizacion, int Heridos_H, int Heridos_M){
        
        
        super(Magnitud, Fecha, Time, Profundidad, Alcance);
        
        
        this.datosDestruccion = new Destruccion(
            Mercalli, Fallecidos_H, Fallecidos_M, Indemnizacion, Heridos_H, Heridos_M
        );
    }
    
   
    
    public Destruccion getDatosDestruccion() {
        return datosDestruccion;
    }

    public void setDatosDestruccion(Destruccion datosDestruccion) {
        this.datosDestruccion = datosDestruccion;
    }
    
    
    public String getEscalaMercalli() {
        return datosDestruccion.getMercalli();
    }
    
    
    
    @Override
    public String toString() {
        
        return "Terremoto{" + 
               super.toString() + 
               ", " + 
               datosDestruccion.toString() + 
               '}';
    }
    
    
    
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) { 
            return false;
        }
        if (!(obj instanceof Terremoto)) {
            return false;
        }
        final Terremoto other = (Terremoto) obj;
        // Compara el objeto de Destruccion
        return Objects.equals(this.datosDestruccion, other.datosDestruccion);
    }
    
    
    @Override
    public int hashCode() {
        int hash = super.hashCode(); // Incluye el hash del padre
        hash = 71 * hash + Objects.hashCode(this.datosDestruccion);
        return hash;
    }
}
