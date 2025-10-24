/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sismosbutant;

import java.util.Objects;

/**
 *
 * @author EQUIPO 467
 */
public class Destruccion {
    protected String Mercalli;
    protected int Fallecidos_H;
    protected int Fallecidos_M;
    protected float Indemnizacion; // en la foto decia int pero puede que sea mejor el float por los decimales een caso de que tenga 
    protected int Heridos_H;
    protected int Heridos_M;

    public Destruccion(String Mercalli, int Fallecidos_H, int Fallecidos_M, float Indemnizacion, int Heridos_H, int Heridos_M) {
        this.Mercalli = Mercalli;
        this.Fallecidos_H = Fallecidos_H;
        this.Fallecidos_M = Fallecidos_M;
        this.Indemnizacion = Indemnizacion;
        this.Heridos_H = Heridos_H;
        this.Heridos_M = Heridos_M;
    }

    public String getMercalli() {
        return Mercalli;
    }

    public int getFallecidos_H() {
        return Fallecidos_H;
    }

    public int getFallecidos_M() {
        return Fallecidos_M;
    }

    public float getIndemnizacion() {
        return Indemnizacion;
    }

    public int getHeridos_H() {
        return Heridos_H;
    }

    public int getHeridos_M() {
        return Heridos_M;
    }

    public void setMercalli(String Mercalli) {
        this.Mercalli = Mercalli;
    }

    public void setFallecidos_H(int Fallecidos_H) {
        this.Fallecidos_H = Fallecidos_H;
    }

    public void setFallecidos_M(int Fallecidos_M) {
        this.Fallecidos_M = Fallecidos_M;
    }

    public void setIndemnizacion(float Indemnizacion) {
        this.Indemnizacion = Indemnizacion;
    }

    public void setHeridos_H(int Heridos_H) {
        this.Heridos_H = Heridos_H;
    }

    public void setHeridos_M(int Heridos_M) {
        this.Heridos_M = Heridos_M;
    }

    @Override
    public String toString() {
        return "Destruccion{" + "Mercalli=" + Mercalli + ", Fallecidos_H=" + Fallecidos_H + ", Fallecidos_M=" + Fallecidos_M + ", Indemnizacion=" + Indemnizacion + ", Heridos_H=" + Heridos_H + ", Heridos_M=" + Heridos_M + '}';
    }
    
    
    
}