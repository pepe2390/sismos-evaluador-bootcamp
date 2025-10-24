/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package sismosbutant;

/**
 * Interfaz que define una constante para el porcentaje de aumento.
 * Todos los campos en una interfaz son public static final.
 */
abstract interface Aumentable {

    
    final float AUMENTO = 0.2f;
    abstract void calcular_herridos();
    abstract void calcular_fallecidos();
}
