/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sismosbutant;

/**
 *
 * @author EQUIPO 467
 */
public class Recurso {
        protected boolean Luz;
    protected boolean Agua;
    protected boolean Comida;
    protected boolean Medicamentos;
    protected boolean Comunicacion;
    
   

    public Recurso(boolean Luz, boolean Agua, boolean Comida, boolean Medicamentos, boolean Comunicacion) {
        this.Luz = Luz;
        this.Agua = Agua;
        this.Comida = Comida;
        this.Medicamentos = Medicamentos;
        this.Comunicacion = Comunicacion;
    }

    public boolean isLuz() {
        return Luz;
    }

    public boolean isAgua() {
        return Agua;
    }

    public boolean isComida() {
        return Comida;
    }

    public boolean isMedicamentos() {
        return Medicamentos;
    }

    public boolean isComunicacion() {
        return Comunicacion;
    }

    public void setLuz(boolean Luz) {
        this.Luz = Luz;
    }

    public void setAgua(boolean Agua) {
        this.Agua = Agua;
    }

    public void setComida(boolean Comida) {
        this.Comida = Comida;
    }

    public void setMedicamentos(boolean Medicamentos) {
        this.Medicamentos = Medicamentos;
    }

    public void setComunicacion(boolean Comunicacion) {
        this.Comunicacion = Comunicacion;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Recurso other = (Recurso) obj;
        if (this.Luz != other.Luz) {
            return false;
        }
        if (this.Agua != other.Agua) {
            return false;
        }
        if (this.Comida != other.Comida) {
            return false;
        }
        if (this.Medicamentos != other.Medicamentos) {
            return false;
        }
        return this.Comunicacion == other.Comunicacion;
    }

    @Override
    public String toString() {
        return "Recursos{" + "Luz=" + Luz + ", Agua=" + Agua + ", Comida=" + Comida + ", Medicamentos=" + Medicamentos + ", Comunicacion=" + Comunicacion + '}';
}}
    