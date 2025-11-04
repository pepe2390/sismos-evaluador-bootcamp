/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package analisadorsismos1;

import java.util.List;

/**
 * Interfaz para la gestión de eventos sísmicos (desde datos.txt).
 */
public interface IBuscadorSismos {
    /** Carga los datos de sismos desde el "datos.txt" */
    void cargarSismos(String rutaArchivo);
    
    /** Busca todos los sismos que ocurrieron en una provincia. */
    List<Sismo> buscarSismosPorProvincia(String nombreProvincia);
    
    /** Devuelve todos los sismos cargados en memoria. */
    List<Sismo> getTodosLosSismos();
}