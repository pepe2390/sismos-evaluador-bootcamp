/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analisadorsismos1;


/**
 * Clase POJO que encapsula los niveles de 
 * recursos de una provincia (1 = disponible, 0 = no disponible).
 */
public class Recursos {
    private int luz;
    private int agua;
    private int comida;
    private int medicamentos;
    private int comunicaciones;

    public Recursos(int luz, int agua, int comida, int medicamentos, int comunicaciones) {
        this.luz = luz;
        this.agua = agua;
        this.comida = comida;
        this.medicamentos = medicamentos;
        this.comunicaciones = comunicaciones;
    }

    // --- Getters ---
    public int getLuz() { return luz; }
    public int getAgua() { return agua; }
    public int getComida() { return comida; }
    public int getMedicamentos() { return medicamentos; }
    public int getComunicaciones() { return comunicaciones; }

    /**
     * Devuelve un String con los recursos que FALTAN (valor 0).
     * @return String con los recursos críticos.
     */
    public String getRecursosFaltantes() {
        String faltantes = "";
        if (luz == 0) faltantes += "Luz, ";
        if (agua == 0) faltantes += "Agua, ";
        if (comida == 0) faltantes += "Comida, ";
        if (medicamentos == 0) faltantes += "Medicamentos, ";
        if (comunicaciones == 0) faltantes += "Comunicaciones, ";

        return faltantes.isEmpty() ? "Ninguno" : faltantes.substring(0, faltantes.length() - 2);
    }
}