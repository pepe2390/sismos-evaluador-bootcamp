/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analisadorsismos1;

/**
 * @author Luca
 */
/**
 * Clase Padre que representa una Región.
 * Contiene la información común a todas las provincias de esta región.
 */
public class Region {
    
    protected String numeroRegion;
    protected String nombreRegion;

    public Region(String numeroRegion, String nombreRegion) {
        this.numeroRegion = numeroRegion;
        this.nombreRegion = nombreRegion;
    }

    // --- Getters ---
    public String getNumeroRegion() { return numeroRegion; }
    public String getNombreRegion() { return nombreRegion; }

    @Override
    public String toString() {
        return "Region [Nombre=" + nombreRegion + ", Numero=" + numeroRegion + "]";
    }
}