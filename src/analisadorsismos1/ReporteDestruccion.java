/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analisadorsismos1;


/**
 * Clase POJO que almacena el resultado de un cálculo de Destruccion.
 * Transporta los datos desde la clase Destruccion hasta el Recomendador
 * y el GestorDemografico.
 */
public class ReporteDestruccion {
    private String nivelDestruccion;
    private int intensidadMercalliEstimada;
    private long fallecidosEstimados;
    private long heridosEstimados;

    public ReporteDestruccion(String nivelDestruccion, int intensidadMercalliEstimada, 
                              long fallecidosEstimados, long heridosEstimados) {
        this.nivelDestruccion = nivelDestruccion;
        this.intensidadMercalliEstimada = intensidadMercalliEstimada;
        this.fallecidosEstimados = fallecidosEstimados;
        this.heridosEstimados = heridosEstimados;
    }

    // --- Getters ---
    public String getNivelDestruccion() { return nivelDestruccion; }
    public int getIntensidadMercalliEstimada() { return intensidadMercalliEstimada; }
    public long getFallecidosEstimados() { return fallecidosEstimados; }
    public long getHeridosEstimados() { return heridosEstimados; }
    
    /**
     * Convierte el número Mercalli (ej. 8) a Romano (ej. "VIII").
     */
    public String getMercalliEstimadaComoRomano() {
        switch (this.intensidadMercalliEstimada) {
            case 1: return "I"; case 2: return "II"; case 3: return "III";
            case 4: return "IV"; case 5: return "V"; case 6: return "VI";
            case 7: return "VII"; case 8: return "VIII"; case 9: return "IX";
            case 10: return "X"; case 11: return "XI"; case 12: return "XII";
            default: return "N/A";
        }
    }
}