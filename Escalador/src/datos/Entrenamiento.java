package datos;

import java.sql.Date;
import java.sql.Time;

public class Entrenamiento {

    private Date fecha;
    private Time horaComienzo, horaFin;
    private String tipo, descripcion;

    public Entrenamiento(Date fecha, Time horaComienzo, Time horaFin, String tipo, String descripcion) {
        this.fecha = fecha;
        this.horaComienzo = horaComienzo;
        this.horaFin = horaFin;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHoraComienzo() {
        return horaComienzo;
    }

    public void setHoraComienzo(Time horaComienzo) {
        this.horaComienzo = horaComienzo;
    }

    public Time getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Time horaFin) {
        this.horaFin = horaFin;
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

}
