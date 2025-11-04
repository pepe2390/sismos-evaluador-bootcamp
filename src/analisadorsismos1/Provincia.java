/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analisadorsismos1;


import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

/**
 * Clase Hija que representa una Provincia (HEREDA de Region).
 * Almacena su estado dinámico (población) y su historial de impactos.
 */
public class Provincia extends Region {

    private int indice;
    private String nombreProvincia;
    private long poblacion; // Población actual (estado dinámico)
    private int superficieKm2;
    private String tipoSuperficie;
    private Recursos recursos;
    
    /**
     * La lista interna que almacena objetos 'RegistroImpacto'.
     */
    private List<RegistroImpacto> historialDeImpactos;

    /**
     * Constructor de Provincia.
     */
    public Provincia(int indice, String nombreProvincia, long poblacion, int superficieKm2, String tipoSuperficie,
                     String numeroRegion, String nombreRegion, Recursos recursos) {
        
        super(numeroRegion, nombreRegion); // Llama al constructor del Padre
        this.indice = indice;
        this.nombreProvincia = nombreProvincia;
        this.poblacion = poblacion; // Población inicial
        this.superficieKm2 = superficieKm2;
        this.tipoSuperficie = tipoSuperficie;
        this.recursos = recursos;
        this.historialDeImpactos = new ArrayList<>(); // Inicializa la lista interna
    }

    // --- Getters ---
    public int getIndice() { return indice; }
    public String getNombreProvincia() { return nombreProvincia; }
    public Recursos getRecursos() { return recursos; }
    public String getTipoSuperficie() { return tipoSuperficie; }
    public long getPoblacion() { return this.poblacion; } // Devuelve la población actual
    public List<RegistroImpacto> getHistorialDeImpactos() {
        return this.historialDeImpactos;
    }
    
    /**
     * Registra un evento de impacto (el único método que modifica la población).
     * 1. Reduce la población SÓLO por los fallecidos.
     * 2. Guarda el registro completo (fallecidos y heridos) en la lista interna.
     *
     * @param fecha La fecha del evento.
     * @param causa El motivo (ej. "Terremoto", "Natural").
     * @param fallecidos La cantidad de fallecidos.
     * @param heridos La cantidad de heridos.
     */
    public void registrarImpacto(LocalDate fecha, String causa, long fallecidos, long heridos) {
        
        if (fallecidos > this.poblacion) {
            fallecidos = this.poblacion; // No pueden morir más de los que hay
        }
        
        // 1. Reducir la población actual
        this.poblacion -= fallecidos;
        
        // 2. Guardar el evento en la lista interna
        RegistroImpacto registro = new RegistroImpacto(fecha, causa, fallecidos, heridos);
        this.historialDeImpactos.add(registro);
        
        System.out.println("  -> [Prov. " + this.nombreProvincia + "] REGISTRADO: " + 
                           fallecidos + " fallecidos, " + heridos + " heridos. Causa: " + causa + 
                           ". Población restante: " + this.poblacion);
    }
    
    /**
     * Setter para ajustes manuales/censo.
     * @param nuevaPoblacion El valor al que se ajustará la población.
     */
    public void setPoblacion(long nuevaPoblacion) {
        this.poblacion = nuevaPoblacion;
    }

    @Override
    public String toString() {
        return "Provincia [Indice=" + indice + ", Nombre=" + nombreProvincia + 
               ", Poblacion (Actual)=" + poblacion + "]";
    }
}