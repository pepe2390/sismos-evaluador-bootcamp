/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package analisadorsismos1;
/**
 * @author Luca
 */

import java.time.LocalDate;
import java.util.List;

/**
 * Clase principal que dirige la simulación.
 * Contiene el método main() para ejecutar el programa.
 * MODIFICADA: Utiliza buscarPorIndice() para aplicar el impacto.
 */
public class Simulador {

    private IGestionPoblacion gestorPoblacion;
    private IBuscadorSismos buscadorSismos;
    private Recomendador recomendador;
    private LocalDate fechaActualSimulacion;

    public Simulador(IGestionPoblacion gestorPoblacion, IBuscadorSismos buscadorSismos) {
        this.gestorPoblacion = gestorPoblacion;
        this.buscadorSismos = buscadorSismos;
        // El Recomendador necesita el gestor de población para cruzar datos
        this.recomendador = new Recomendador(this.gestorPoblacion); 
        this.fechaActualSimulacion = LocalDate.now(); // Inicia en la fecha actual
    }

    /**
     * Simula el paso de un año.
     * Itera por todas las provincias y delega el cálculo de
     * mortalidad natural al GestorDemografico.
     */
    public void simularPasoDeAnio() {
        System.out.println("\n===== SIMULANDO EL AÑO " + fechaActualSimulacion.getYear() + " =====");
        this.fechaActualSimulacion = this.fechaActualSimulacion.plusYears(1);

        for (Provincia provincia : gestorPoblacion.getTodasLasProvincias()) {
            GestorDemografico.aplicarMortalidadNatural(provincia, fechaActualSimulacion);
        }
        
        System.out.println("===== FIN SIMULACIÓN AÑO " + (fechaActualSimulacion.getYear() - 1) + " =====\n");
    }

    /**
     * Simula un sismo específico (MODIFICADO).
     * 1. Llama al Recomendador (que ahora usa ID).
     * 2. Busca la provincia por ID para aplicar el impacto.
     * 3. Llama al GestorDemografico para aplicar el impacto.
     */
    public void simularEventoDeSismo(Sismo sismo) {
        System.out.println("\n¡¡¡ EVENTO SÍSMICO DETECTADO !!!");
        
        // 1. Genera el reporte de texto (ahora usa ID internamente)
        String reporteTexto = recomendador.generarRecomendacion(sismo);
        System.out.println(reporteTexto); // Imprime el reporte para el usuario

        // 2. Busca la provincia afectada por ID para aplicarle el daño
        //    --- CAMBIO: Buscamos por ID ---
        Provincia provinciaAfectada = gestorPoblacion.buscarPorIndice(sismo.getProvinciaId());
        
        // 3. Aplica el impacto
        if (provinciaAfectada != null && (sismo instanceof Terremoto || reporteTexto.contains("Heridos (Est.)"))) {
            // Recalcula el impacto (esto es rápido) para pasarlo al gestor demográfico
            ReporteDestruccion datosImpacto = Destruccion.calcularImpacto(sismo, provinciaAfectada);
            // Delega la aplicación del impacto
            GestorDemografico.aplicarMortalidadPorDesastre(provinciaAfectada, sismo, datosImpacto);
        } else {
             System.out.println("  -> [Simulador] Evento de bajo impacto o provincia no encontrada (ID: " + sismo.getProvinciaId() + "). Sin registro demográfico.");
        }
        System.out.println("--- FIN DEL EVENTO ---\n");
    }
    
    /**
     * Método main() para ejecutar la simulación.
     * Aquí es donde debes poner las rutas a tus archivos.
     */
    public static void main(String[] args) {
        
        System.out.println("--- INICIANDO SIMULADOR ---");
        
        // 1. Cargar gestores y datos de archivos
        IGestionPoblacion gestorP = new GestionPoblacion();
        // --- COLOCA AQUÍ LA RUTA A TU CSV DE POBLACIÓN ---
        gestorP.importarCSV("C:\\Users\\lucae\\OneDrive\\Escritorio\\cosas si\\output\\poblaciones_con_indice.csv");

        IBuscadorSismos buscadorS = new BuscadorSismos();
        // --- COLOCA AQUÍ LA RUTA A TU TXT DE SISMOS ---
        buscadorS.cargarSismos("C:\\Users\\lucae\\OneDrive\\Escritorio\\cosas si\\output\\datos.txt"); 

        // 2. Crear el Simulador
        Simulador simulador = new Simulador(gestorP, buscadorS);
        
        // 3. Provincia de prueba (ID 37 = Concepción, según tu C)
        Provincia provinciaPrueba = gestorP.buscarPorIndice(50); 
        if (provinciaPrueba == null) {
            System.err.println("Error: No se encontro la provincia de prueba (Índice 50)");
            // Intenta con otro índice común si ese falla
             provinciaPrueba = gestorP.buscarPorIndice(1); // Probar Arica
             if(provinciaPrueba == null){
                System.err.println("Error: No se pudo encontrar ninguna provincia. Revisa tu CSV.");
                return;
             }
        }
        
        System.out.println("\nPoblación INICIAL de " + provinciaPrueba.getNombreProvincia() + 
                           ": " + provinciaPrueba.getPoblacion());
        
        // 4. Simular paso de un año
        simulador.simularPasoDeAnio();
        
        // 5. Simular un sismo (el primero de la lista cargada)
        if (buscadorS.getTodosLosSismos().isEmpty()) {
            System.err.println("Advertencia: No hay sismos en 'datos.txt' para simular.");
        } else {
            Sismo sismoPrueba = buscadorS.getTodosLosSismos().get(0);
            simulador.simularEventoDeSismo(sismoPrueba);
        }

        // 6. Simular otro año
        simulador.simularPasoDeAnio();
        
        // 7. Mostrar resultados finales
        System.out.println("\n--- RESULTADO FINAL DE SIMULACION ---");
        System.out.println("Poblacion FINAL de " + provinciaPrueba.getNombreProvincia() + 
                           ": " + provinciaPrueba.getPoblacion());
                           
        // 8. Ver la lista interna de impactos
        System.out.println("Historial de Impactos en " + provinciaPrueba.getNombreProvincia() + ":");
        if(provinciaPrueba.getHistorialDeImpactos().isEmpty()) {
            System.out.println("  - Sin registros.");
        } else {
            for(RegistroImpacto reg : provinciaPrueba.getHistorialDeImpactos()) {
                System.out.println("  - " + reg.toString());
            }
        }
    }
}
