package datos;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 */
public class ItinerarioFin implements Serializable {

    private int id;
    private Timestamp fecha;

    public ItinerarioFin(int id, Timestamp fecha) {
        this.id = id;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

}
