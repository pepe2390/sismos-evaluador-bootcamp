/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analisadorsismos1;

/**
 * @author Luca
 */
/**
 * Clase que calcula el impacto de un sismo.
 * Es la responsable de la lógica de negocio que transforma
 * magnitud y terreno en fallecidos y heridos.
 */
public class Destruccion {

    /**
     * Calcula el impacto de un sismo, generando estimaciones
     * de fallecidos y heridos.
     * @param sismo El evento sísmico.
     * @param provincia La provincia afectada.
     * @return Un objeto ReporteDestruccion con todos los resultados.
     */
    public static ReporteDestruccion calcularImpacto(Sismo sismo, Provincia provincia) {
        
        double magnitud = sismo.getMagnitud();
        long poblacionTotal = provincia.getPoblacion();
        
        // 1. Calcular Mercalli (para definir el nivel de destrucción)
        int mercalli = calcularIntensidadMercalli(magnitud);
        
        // 2. Factores de impacto (lógica de simulación)
        double factorFallecidos = 0.0;
        double factorHeridos = 0.0;

        if (magnitud < 5.0) {       // Leve (IV-V MMI)
            factorFallecidos = 0.000001; // 0.0001%
            factorHeridos = 0.00001;   // 0.001%
        } else if (magnitud < 7.0) { // Fuerte (VI-VIII MMI)
            factorFallecidos = 0.005;  // 0.5%
            factorHeridos = 0.05;   // 5%
        } else if (magnitud < 8.0) { // Mayor (IX-X MMI)
            factorFallecidos = 0.20;  // 20%
            factorHeridos = 0.30;   // 30%
        } else {                     // Catastrófico (XI-XII MMI)
            factorFallecidos = 0.45;  // 45%
            factorHeridos = 0.25;   // 25%
        }

        // 3. Modificador por tipo de superficie
        String tipoSuperficie = provincia.getTipoSuperficie().toLowerCase();
        double modificador = 1.0;
        
        if (tipoSuperficie.equals("sedimentario") || tipoSuperficie.equals("mixto/transición")) {
            modificador = 1.2; // 20% más impacto
        } else if (tipoSuperficie.equals("montaña/roca")) {
            modificador = 0.8; // 20% menos impacto
        }
        
        factorFallecidos *= modificador;
        factorHeridos *= modificador;

        // 4. Calcular resultados finales
        long fallecidosEstimados = (long) (poblacionTotal * factorFallecidos);
        long heridosEstimados = (long) (poblacionTotal * factorHeridos);
        
        // Validar que no superen la población total
        if (fallecidosEstimados + heridosEstimados > poblacionTotal) {
             fallecidosEstimados = (long) (poblacionTotal * factorFallecidos / (factorFallecidos + factorHeridos));
             heridosEstimados = (long) (poblacionTotal * factorHeridos / (factorFallecidos + factorHeridos));
        }

        String nivelDestruccion;
        if (mercalli >= 10) nivelDestruccion = "Catastrofico";
        else if (mercalli >= 8) nivelDestruccion = "Severo";
        else if (mercalli >= 6) nivelDestruccion = "Moderado";
        else nivelDestruccion = "Leve";

        // 5. Devolver el reporte
        return new ReporteDestruccion(nivelDestruccion, mercalli, fallecidosEstimados, heridosEstimados);
    }
    
    /**
     * Estima la intensidad Mercalli (MMI) a partir de la Magnitud (Richter).
     */
    public static int calcularIntensidadMercalli(double magnitud) {
        if (magnitud < 2.0) return 1; if (magnitud < 3.0) return 2;
        if (magnitud < 4.0) return 3; if (magnitud < 4.5) return 4;
        if (magnitud < 5.0) return 5; if (magnitud < 5.5) return 6;
        if (magnitud < 6.5) return 7; if (magnitud < 7.0) return 8;
        if (magnitud < 7.5) return 9; if (magnitud < 8.0) return 10;
        if (magnitud < 8.5) return 11;
        return 12; 
    }
}