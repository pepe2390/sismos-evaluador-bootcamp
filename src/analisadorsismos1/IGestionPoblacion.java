/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package analisadorsismos1;

/**
 * @author Luca
 */
import java.util.Collection;

/**
 * Interfaz para la gestión de datos de Provincias.
 * Define el contrato de las operaciones de carga y búsqueda.
 */
public interface IGestionPoblacion {
    /** Carga los datos desde un archivo CSV. */
    void importarCSV(String rutaArchivo);
    
    /** Busca una Provincia basado en su índice único (ID). */
    Provincia buscarPorIndice(int indice);
    
    /** Busca una Provincia basado en su nombre. */
    Provincia buscarPorNombre(String nombreProvincia);
    
    /** Devuelve una colección de todas las provincias cargadas. */
    Collection<Provincia> getTodasLasProvincias();
    
    /** Modifica la población de una provincia (para censos/ajustes manuales). */
    boolean modificarPoblacion(int indice, long nuevaPoblacion);
}