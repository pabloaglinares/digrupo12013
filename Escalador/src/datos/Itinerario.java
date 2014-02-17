package datos;

public class Itinerario {
    private int id; // p_itinerario
    private String nombre, localizacion, via, dificultad, URLimagen;
    //File fotoItinerario; //?

    // Constructor sin el id
    // Falta el atributo de la Foto
    public Itinerario(String nombre, String localizacion, String via, String dificultad, String URLimagen) {
        this.nombre = nombre;
        this.localizacion = localizacion;
        this.via = via;
        this.dificultad = dificultad;
    }

    // Constructor con el id
    // Falta el atributo de la Foto
    public Itinerario(int id, String nombre, String localizacion, String via, String dificultad, String URLimagen) {
        this.id = id;
        this.nombre = nombre;
        this.localizacion = localizacion;
        this.via = via;
        this.dificultad = dificultad;
    }

    public String getURLimagen() {
        return URLimagen;
    }

    public void setURLimagen(String URLimagen) {
        this.URLimagen = URLimagen;
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

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }
}
