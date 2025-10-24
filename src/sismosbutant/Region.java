/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sismosbutant;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author EQUIPO 467
 */
public class Region {
        private String nombre;
    protected int ID;
    protected float superficie;
    protected int PIB;
    protected int densidad;

    public Region(String nombre, int ID, float superficie, int PIB, int densidad) {
        this.nombre = nombre;
        this.ID = ID;
        this.superficie = superficie;
        this.PIB = PIB;
        this.densidad = densidad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getID() {
        return ID;
    }

    public float getSuperficie() {
        return superficie;
    }

    public int getPIB() {
        return PIB;
    }

    public int getDensidad() {
        return densidad;
    }

    public void setNombre() {
    FileReader archivo;
    BufferedReader lector;
    
    int contador=0;
    
    try{
    archivo = new FileReader("C:\\Users\\lucae\\OneDrive\\Escritorio\\trabajo sismos\\output\\datos.txt");   //Cambiar dirección al archivo correcto wasa
    if(archivo.ready()){
    lector = new BufferedReader(archivo);
    String cadena;
    while ((cadena = lector.readLine()) !=null) {
        contador++;
        if (contador==1){
            continue;
        }else{
            if (contador==2){
            String[] corte;
                corte = cadena.split(",");    //Separa la linea en distintos elementos, utilizando como separador las comas ","
            String nombreRegion = corte[1].trim();
            this.nombre=nombreRegion;
        }
        }
    }
    }
    else {
    System.out.println("El archivo no está listo para ser leído...");
}
} catch (IOException e) {
    System  .out.println("Error: "+ e.getMessage());
}

    }



    public void setID(int ID) {
        this.ID = ID;
    }

    public void setSuperficie(float superficie) {
        this.superficie = superficie;
    }

    public void setPIB(int PIB) {
        this.PIB = PIB;
    }

    public void setDensidad(int densidad) {
        this.densidad = densidad;
    }

    @Override
    public String toString() {
        return "Regiones{" + "nombre=" + nombre + ", ID=" + ID + ", superficie=" + superficie + ", PIB=" + PIB + ", densidad=" + densidad + '}';
    }
}