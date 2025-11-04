/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analisadorsismos1;


/**
 * Clase de servicio que genera el reporte de texto para el usuario.
 * MODIFICADA: Utiliza buscarPorIndice() en lugar de buscarPorNombre().
 */
public class Recomendador {

    private IGestionPoblacion gestorPoblacion;

    public Recomendador(IGestionPoblacion gestorPoblacion) {
        this.gestorPoblacion = gestorPoblacion;
    }

    /**
     * Analiza un sismo y genera una recomendación de acción.
     * @param sismo El evento a analizar.
     * @return Un String con la recomendación detallada.
     */
    public String generarRecomendacion(Sismo sismo) {
        
        Provincia provinciaAfectada = gestorPoblacion.buscarPorIndice(sismo.getProvinciaId());

        if (provinciaAfectada == null) {
            // Mensaje de error
            return "ALERTA: Sismo en provincia con ID " + sismo.getProvinciaId() + 
                   " (" + sismo.getNombreProvinciaAfectada() + ") no encontrada en BBDD CSV.";
        }

        // 1. Calcular impacto 
        ReporteDestruccion reporte = Destruccion.calcularImpacto(sismo, provinciaAfectada);
        String recursosCriticos = provinciaAfectada.getRecursos().getRecursosFaltantes();

        // 2. Construir el reporte de texto 
        StringBuilder sb = new StringBuilder();
        
        if (sismo instanceof Terremoto) {
            sb.append("\n============================================\n");
            sb.append("¡¡ALERTA MÁXIMA: TERREMOTO!!\n");
            sb.append("============================================\n");
        } else {
            sb.append("\n--------------------------------------------\n");
            sb.append("INFORME DE SISMO (TEMBLOR)\n");
            sb.append("--------------------------------------------\n");
        }
        
        sb.append("Evento:     Magnitud (Richter) ").append(sismo.getMagnitud())
          .append(" el ").append(sismo.getFecha()).append("\n");
        sb.append("Intensidad Est. (Mercalli): ")
          .append(reporte.getMercalliEstimadaComoRomano())
          .append(" (").append(reporte.getIntensidadMercalliEstimada()).append("/12)\n");
        sb.append("Epicentro:  Provincia de ").append(provinciaAfectada.getNombreProvincia()) // (Usamos el nombre para el reporte)
          .append(", ").append(provinciaAfectada.getNombreRegion()).append("\n");
        
        sb.append("\n--- EVALUACIÓN DE IMPACTO ---\n");
        sb.append("Nivel de Destrucción: ").append(reporte.getNivelDestruccion()).append("\n");
        sb.append("Población Total (Pre-Impacto): ").append(provinciaAfectada.getPoblacion() + reporte.getFallecidosEstimados()).append("\n"); 
        sb.append("Fallecidos (Est.):    ").append(reporte.getFallecidosEstimados()).append("\n");
        sb.append("Heridos (Est.):       ").append(reporte.getHeridosEstimados()).append("\n");
        
        sb.append("\n--- ANÁLISIS DE RECURSOS ---\n");
        sb.append("Recursos críticos PRE-SISMO (Faltantes): ").append(recursosCriticos).append("\n");
        
        sb.append("\n--- RECOMENDACIÓN ---\n");
        if (sismo instanceof Terremoto || reporte.getHeridosEstimados() > 0) {
            sb.append("ACCIÓN: ").append(sismo.getNivelPeligro()).append("\n");
            if (!recursosCriticos.equals("Ninguno")) {
                sb.append("¡PRIORIDAD MÁXIMA! La provincia ya reportaba carencias de: ")
                  .append(recursosCriticos).append(". Enviar suministros y equipos médicos de inmediato.\n");
            } else {
                sb.append("Provincia con recursos base. Preparar envío de ayuda humanitaria y equipos médicos/rescate.\n");
            }
        } else {
            sb.append("ACCIÓN: ").append(sismo.getNivelPeligro()).append(". Sin acción inmediata. Evaluar reportes locales.\n");
        }

        return sb.toString();
    }
}