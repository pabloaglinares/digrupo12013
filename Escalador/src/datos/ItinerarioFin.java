package datos;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 */
public class ItinerarioFin implements Serializable {

    private String nombre;
    private Timestamp fecha;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public ItinerarioFin(String nombre, Timestamp fecha) {
        this.nombre = nombre;
        this.fecha = fecha;
    }

}
