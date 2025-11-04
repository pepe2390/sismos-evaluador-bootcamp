/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analisadorsismos1;


import java.time.LocalDate;

/**
 * Clase base ABSTRACTA para un evento sísmico
**/
public abstract class Sismo {
    protected double magnitud;
    protected LocalDate fecha;
    protected String nombreProvinciaAfectada;
    protected String nombreRegionAfectada;
    
    protected int provinciaId; // El ID leído desde datos.txt

    public Sismo(double magnitud, LocalDate fecha, String nombreProvinciaAfectada, 
                 String nombreRegionAfectada, int provinciaId) { 
        this.magnitud = magnitud;
        this.fecha = fecha;
        this.nombreProvinciaAfectada = nombreProvinciaAfectada;
        this.nombreRegionAfectada = nombreRegionAfectada;
        this.provinciaId = provinciaId;
    }

    // --- Getters ---
    public double getMagnitud() { return magnitud; }
    public LocalDate getFecha() { return fecha; }
    public String getNombreProvinciaAfectada() { return nombreProvinciaAfectada; }
    
    public int getProvinciaId() { return provinciaId; }

    public abstract String getNivelPeligro();

    @Override
    public String toString() {
        return "[" + fecha + "] " + getNombreProvinciaAfectada() + " (ID: " + provinciaId + ", Mag: " + magnitud + ")";
    }
}