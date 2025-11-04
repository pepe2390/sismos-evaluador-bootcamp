/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analisadorsismos1;


import java.time.LocalDate;

/**
 * Clase de Servicio que aplica los cambios demográficos a las Provincias.
 * Separa la lógica de "cuándo" (Simulador) de "cómo" (Provincia).
 */
public class GestorDemografico {

    // Tasa de mortalidad natural anual (ej. 0.8% de la población)
    private static final double TASA_MORTALIDAD_NATURAL = 0.008;

    /**
     * Calcula y aplica las muertes naturales para un año en una provincia.
     * @param provincia La provincia a modificar.
     * @param fechaSimulacion La fecha actual de la simulación.
     */
    public static void aplicarMortalidadNatural(Provincia provincia, LocalDate fechaSimulacion) {
        
        long muertesNaturales = (long) (provincia.getPoblacion() * TASA_MORTALIDAD_NATURAL);
        
        if (muertesNaturales > 0) {
            // Llama al método de provincia para actualizarse (con 0 heridos)
            provincia.registrarImpacto(
                fechaSimulacion, 
                "Causa Natural",
                muertesNaturales, // fallecidos
                0 // heridos
            );
        }
    }

    /**
     * Aplica los fallecidos y heridos de un desastre a una provincia.
     * @param provincia La provincia a modificar.
     * @param sismo El sismo que causó el impacto.
     * @param reporte El reporte ya calculado por la clase Destruccion.
     */
    public static void aplicarMortalidadPorDesastre(Provincia provincia, Sismo sismo, ReporteDestruccion reporte) {
        
        long fallecidosEstimados = reporte.getFallecidosEstimados();
        long heridosEstimados = reporte.getHeridosEstimados();
        
        if (fallecidosEstimados > 0 || heridosEstimados > 0) {
            // Llama al método de provincia con ambos valores
            provincia.registrarImpacto(
                sismo.getFecha(), 
                "Terremoto (Mag " + sismo.getMagnitud() + ")",
                fallecidosEstimados,
                heridosEstimados
            );
        }
    }
}