/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sismosbutant;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 *
 * @author EQUIPO 467
 */
public class Sismo {
    protected float Magnitud; //Escala Richter
    protected LocalDate Fecha;
    protected LocalTime Time;//
    protected float Profundidad;
    protected float Alcance;

    public Sismo(float Magnitud, LocalDate Fecha, LocalTime Time, float Profundidad, float Alcance) {
        this.Magnitud = Magnitud;
        this.Fecha = Fecha;
        this.Time = Time;
        this.Profundidad = Profundidad;
        this.Alcance = Alcance;
    }

    public float getMagnitud() {
        return Magnitud;
    }

    public LocalDate getFecha() {
        return Fecha;
    }

    public LocalTime getTime() {
        return Time;
    }

    public float getProfundidad() {
        return Profundidad;
    }

    public float getAlcance() {
        return Alcance;
    }

    public void setFecha() {
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
            String fechaSismo = corte[0].trim();
            
            DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");   //Establece el formato de la fecha
            LocalDate fechaReal = LocalDate.parse(fechaSismo, formatoFecha);
            this.Fecha = fechaReal;
            
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

    public void setTime(LocalTime Time) {
        this.Time = Time;
    }

    public void setProfundidad(float Profundidad) {
        this.Profundidad = Profundidad;
    }

    public void setAlcance(float Alcance) {
        this.Alcance = Alcance;
    }

    @Override
    public String toString() {
        return "Sismos{" + "Magnitud=" + Magnitud + ", Fecha=" + Fecha + ", Time=" + Time + ", Profundidad=" + Profundidad + ", Alcance=" + Alcance + '}';
    }
    
    public void setMagnitud() {
    FileReader archivo;
    BufferedReader lector;
    
    int contador=0;
    
    try{
    archivo = new FileReader("C:\\Users\\lucae\\OneDrive\\Escritorio\\trabajo sismos\\output\\datos.txt");
    if(archivo.ready()){
    lector = new BufferedReader(archivo);
    String cadena;
    while ((cadena = lector.readLine()) !=null) {
        contador++;
        if (contador==1){
            continue;
        }else{
            if (contador==2){
            String[] corte;   //Separa la linea en distintos elementos, utilizando como separador las comas ","
                corte = cadena.split(",");
            float magrich = Float.parseFloat(corte[3].trim());   //"magrich" significa Magnitud Richter. 
            this.Magnitud = magrich;
            
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
    
        public void mostrarTodo(){
        System.out.println("Datos del sismo:");
        System.out.println("Magnitud: "+Magnitud);
        System.out.println("Fecha: "+Fecha);
        System.out.println("Hora: "+Time);
        System.out.println("Profundidad: "+Profundidad);
        System.out.println("Alcance: "+Alcance);
    }
    
}
