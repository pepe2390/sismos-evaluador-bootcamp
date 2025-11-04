/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analisadorsismos1;
/**
 * @author Luca
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación de IBuscadorSismos.
 * MODIFICADA: Lee 'datos.txt' incluyendo el nuevo IndiceProvincia.
 */
public class BuscadorSismos implements IBuscadorSismos {

    private static final double LIMITE_TERREMOTO = 7.0;
    // Formato AAAA-MM-DD (coincide con el %Y-%m-%d del código C)
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ISO_LOCAL_DATE; 
    
    private List<Sismo> listaDeSismos;

    public BuscadorSismos() {
        this.listaDeSismos = new ArrayList<>();
    }

    // --- MÉTODO CARGAR SISMOS (MODIFICADO) ---
    @Override
    public void cargarSismos(String rutaArchivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            
            // Omitimos la cabecera (Fecha,Region,Provincia,Magnitud,IndiceProvincia)
            reader.readLine(); 
            
            String linea;
            while ((linea = reader.readLine()) != null) {
                
                // El split ahora manejará comillas si las hay
                String[] campos = linea.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                
                // Ahora esperamos 5 columnas
                if (campos.length < 5) continue; 

                try {
                    // Formato esperado: AAAA-MM-DD, "Region...", "Provincia...", 5.8, 38
                    LocalDate fecha = LocalDate.parse(campos[0].trim(), FORMATO_FECHA);
                    String region = campos[1].trim().replace("\"", "");
                    String provincia = campos[2].trim().replace("\"", "");
                    double magnitud = Double.parseDouble(campos[3].trim());
                    
                    // --- NUEVA LÍNEA: Leer el ID de la 5ta columna ---
                    int provinciaId = Integer.parseInt(campos[4].trim());

                    Sismo nuevoSismo;
                    if (magnitud >= LIMITE_TERREMOTO) {
                        // --- ACTUALIZADO: Pasar el ID al constructor ---
                        nuevoSismo = new Terremoto(magnitud, fecha, provincia, region, provinciaId);
                    } else {
                        // --- ACTUALIZADO: Pasar el ID al constructor ---
                        nuevoSismo = new Temblor(magnitud, fecha, provincia, region, provinciaId);
                    }
                    this.listaDeSismos.add(nuevoSismo);

                } catch (Exception e) {
                    System.err.println("Error al parsear línea de sismo: " + linea + " | Error: " + e.getMessage());
                }
            }
            System.out.println("[BuscadorSismos] Importación completada. " + listaDeSismos.size() + " sismos cargados.");

        } catch (IOException e) {
            System.err.println("Error fatal al leer el archivo de sismos: " + e.getMessage());
        }
    }

    @Override
    public List<Sismo> buscarSismosPorProvincia(String nombreProvincia) {
        // Esta función seguirá funcionando si se necesita
        return this.listaDeSismos.stream()
            .filter(sismo -> sismo.getNombreProvinciaAfectada().equalsIgnoreCase(nombreProvincia.trim()))
            .collect(Collectors.toList());
    }
    
    @Override
    public List<Sismo> getTodosLosSismos() {
        return this.listaDeSismos;
    }
}
