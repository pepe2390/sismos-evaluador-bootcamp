/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sismosbutant;

/**
 *
 * @author EQUIPO 467
 */
public class Provincia {
        protected int Poblacion;
    protected boolean Acceso_mar;
    protected String Provincia;
    protected int ID;
    protected float superficie;
    protected int PIB;

    public Provincia(int Poblacion, boolean Acceso_mar, String Provincia, int ID, float superficie, int PIB) {
        this.Poblacion = Poblacion;
        this.Acceso_mar = Acceso_mar;
        this.Provincia = Provincia;
        this.ID = ID;
        this.superficie = superficie;
        this.PIB = PIB;
    }

    public int getPoblacion() {
        return Poblacion;
    }

    public boolean isAcceso_mar() {
        return Acceso_mar;
    }

    public String getProvincia() {
        return Provincia;
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

    public void setPoblacion(int Poblacion) {
        this.Poblacion = Poblacion;
    }

    public void setAcceso_mar(boolean Acceso_mar) {
        this.Acceso_mar = Acceso_mar;
    }

    public void setProvincia(String Provincia) {
        this.Provincia = Provincia;
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
    private static final System.Logger LOG = System.getLogger(Provincia.class.getName());

    @Override
    public String toString() {
        return "Provincia{" + "Poblacion=" + Poblacion + ", Acceso_mar=" + Acceso_mar + ", Provincia=" + Provincia + ", ID=" + ID + ", superficie=" + superficie + ", PIB=" + PIB + '}';
    }

    public static System.Logger getLOG() {
        return LOG;
    }
}
