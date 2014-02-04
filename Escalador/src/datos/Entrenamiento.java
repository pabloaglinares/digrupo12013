package datos;

import java.sql.Date;

public class Entrenamiento {
    
    private int id; // p_entrenamiento
    private String tipo,  descripcion;
    private Date fechaInicio, fechaFin;

    public Entrenamiento(int id, String tipo, String descripcion, Date fechaInicio, Date fechaFin) {
        this.id = id;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Entrenamiento(String tipo, String descripcion, Date fechaInicio, Date fechaFin) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    @Override
    public String toString() {
        return "Entrenamiento{" + "id=" + id + ", tipo=" + tipo + ", descripcion=" + descripcion + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + '}';
    }
  
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

   
}
