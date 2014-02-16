package datos;

import java.sql.Timestamp;

public class Configuracion {
   private int id;
   private String nombre, apellido;
   private Timestamp fechaIni,fechaFin;

    public Configuracion(int id, String nombre, String apellido, Timestamp fechaIni, Timestamp fechaFin) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaIni = fechaIni;
        this.fechaFin = fechaFin;
    }

    public Configuracion(String nombre, String apellido, Timestamp fechaIni, Timestamp fechaFin) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaIni = fechaIni;
        this.fechaFin = fechaFin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Timestamp getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(Timestamp fechaIni) {
        this.fechaIni = fechaIni;
    }

    public Timestamp getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Timestamp fechaFin) {
        this.fechaFin = fechaFin;
    }
   
}
