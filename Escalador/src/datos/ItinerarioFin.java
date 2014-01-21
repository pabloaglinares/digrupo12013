package datos;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 */
public class ItinerarioFin implements Serializable{
    private String itinerario;
    private Date fecha_fin;

    public ItinerarioFin(String itinerario, Date fecha_fin) {
        this.itinerario = itinerario;
        this.fecha_fin = fecha_fin;
    }

    public ItinerarioFin() {
    }

    public String getItinerario() {
        return itinerario;
    }

    public void setItinerario(String itinerario) {
        this.itinerario = itinerario;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    @Override
    public String toString() {
        return "Itinerario{" + "itinerario=" + itinerario + ", fecha_fin=" + fecha_fin + '}';
    }
    
}
