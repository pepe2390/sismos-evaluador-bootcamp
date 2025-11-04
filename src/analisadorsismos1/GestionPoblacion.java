/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analisadorsismos1;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementación de IGestionPoblacion.
 * Carga el CSV de Provincias en un Map (Índice -> Provincia).
 */
public class GestionPoblacion implements IGestionPoblacion {

    private Map<Integer, Provincia> mapaProvincias;

    public GestionPoblacion() {
        this.mapaProvincias = new HashMap<>();
    }

    /**
     * Lee el CSV de 12 columnas (poblaciones_con_indice.csv)
     * y crea los objetos Provincia y Recursos.
     */
    @Override
    public void importarCSV(String rutaArchivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            reader.readLine(); // Omitir cabecera
            String linea;
            
            while ((linea = reader.readLine()) != null) {
                // Separador de comas que ignora comas dentro de comillas
                String[] campos = linea.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                if (campos.length < 12) continue; // Omitir líneas mal formadas

                try {
                    // Limpieza de datos (quitar comillas y espacios)
                    String indiceStr = campos[0].trim().replace("\"", "");
                    String numRegion = campos[1].trim().replace("\"", "");
                    String nomRegion = campos[2].trim().replace("\"", "");
                    String nomProv = campos[3].trim().replace("\"", "");
                    String poblacionStr = campos[4].trim().replace("\"", "");
                    String superficieKm2Str = campos[5].trim().replace("\"", "");
                    String tipoSuperficie = campos[6].trim().replace("\"", "");
                    String luzStr = campos[7].trim().replace("\"", "");
                    String aguaStr = campos[8].trim().replace("\"", "");
                    String comidaStr = campos[9].trim().replace("\"", "");
                    String medicamentosStr = campos[10].trim().replace("\"", "");
                    String comunicacionesStr = campos[11].trim().replace("\"", "");
                    
                    // Parseo de números
                    int indice = Integer.parseInt(indiceStr);
                    long poblacion = Long.parseLong(poblacionStr);
                    int superficieKm2 = Integer.parseInt(superficieKm2Str);
                    int luz = Integer.parseInt(luzStr);
                    int agua = Integer.parseInt(aguaStr);
                    int comida = Integer.parseInt(comidaStr);
                    int medicamentos = Integer.parseInt(medicamentosStr);
                    int comunicaciones = Integer.parseInt(comunicacionesStr);

                    Recursos recursos = new Recursos(luz, agua, comida, medicamentos, comunicaciones);

                    // Crear objeto Provincia
                    Provincia provincia = new Provincia(indice, nomProv, poblacion, superficieKm2, tipoSuperficie,
                                                        numRegion, nomRegion, recursos);
                    
                    // Almacena usando el ÍNDICE como clave
                    this.mapaProvincias.put(indice, provincia); 

                } catch (NumberFormatException e) {
                    System.err.println("Error al parsear línea de CSV: " + linea + " | Error: " + e.getMessage());
                }
            }
            System.out.println("[GestorPoblacion] Importación CSV completada. " + mapaProvincias.size() + " registros.");

        } catch (IOException e) {
            System.err.println("Error fatal al leer el archivo CSV: " + e.getMessage());
        }
    }

    @Override
    public Provincia buscarPorIndice(int indice) {
        // Búsqueda O(1) muy rápida
        return this.mapaProvincias.get(indice);
    }

    @Override
    public Provincia buscarPorNombre(String nombreProvincia) {
        // Búsqueda O(n) más lenta, de respaldo
        for (Provincia p : this.mapaProvincias.values()) {
            if (p.getNombreProvincia().trim().equalsIgnoreCase(nombreProvincia.trim())) {
                return p;
            }
        }
        return null; // No se encontró
    }
    
    @Override
    public Collection<Provincia> getTodasLasProvincias() {
        return this.mapaProvincias.values();
    }

    @Override
    public boolean modificarPoblacion(int indice, long nuevaPoblacion) {
        Provincia provincia = buscarPorIndice(indice);
        if (provincia != null) {
            provincia.setPoblacion(nuevaPoblacion);
            return true;
        }
        return false;
    }
}