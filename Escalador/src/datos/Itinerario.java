package datos;

public class Itinerario {

    private int id; // p_itinerario
    private String nombre, localizacion, tipo, dificultad, URLimagen;
    //File fotoItinerario; //?

    // Constructor sin el id
    // Falta el atributo de la Foto
    public Itinerario(String nombre, String localizacion, String tipo, String dificultad, String URLimagen) {
        this.nombre = nombre;
        this.localizacion = localizacion;
        this.tipo = tipo;
        this.dificultad = dificultad;
        this.URLimagen=URLimagen;
    }

    // Constructor con el id
    // Falta el atributo de la Foto
    public Itinerario(int id, String nombre, String localizacion, String tipo, String dificultad, String URLimagen) {
        this.id = id;
        this.nombre = nombre;
        this.localizacion = localizacion;
        this.tipo = tipo;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }
    
}
