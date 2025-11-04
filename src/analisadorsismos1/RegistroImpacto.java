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
 * Clase POJO que almacena el resultado de un único evento de impacto 
 * en la provincia (tanto desastres como causas naturales).
 */
public class RegistroImpacto {
    private LocalDate fecha;
    private String causa;
    private long fallecidos;
    private long heridos;

    /**
     * Constructor del registro de impacto.
     * @param fecha La fecha del evento.
     * @param causa La causa (ej. "Terremoto", "Causa Natural").
     * @param fallecidos El número de fallecidos.
     * @param heridos El número de heridos.
     */
    public RegistroImpacto(LocalDate fecha, String causa, long fallecidos, long heridos) {
        this.fecha = fecha;
        this.causa = causa;
        this.fallecidos = fallecidos;
        this.heridos = heridos;
    }

    // --- Getters ---
    public LocalDate getFecha() { return fecha; }
    public String getCausa() { return causa; }
    public long getFallecidos() { return fallecidos; }
    public long getHeridos() { return heridos; }

    @Override
    public String toString() {
        return "Registro [Fecha=" + fecha + ", Causa=" + causa + 
               ", Fallecidos=" + fallecidos + ", Heridos=" + heridos + "]";
    }
}