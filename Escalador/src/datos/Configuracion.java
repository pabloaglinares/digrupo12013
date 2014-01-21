package datos;

public class Configuracion {
    private int id; // p_escalador
    private String nombreEscalador, apellidosEscalador, fechaInicio, fechaFin;

    // Constructor sin el id
    public Configuracion(String nombreEscalador, String apellidosEscalador, String fechaInicio, String fechaFin) {
        this.nombreEscalador = nombreEscalador;
        this.apellidosEscalador = apellidosEscalador;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    // Constructor con el id
    public Configuracion(int id, String nombreEscalador, String apellidosEscalador, String fechaInicio, String fechaFin) {
        this.id = id;
        this.nombreEscalador = nombreEscalador;
        this.apellidosEscalador = apellidosEscalador;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreEscalador() {
        return nombreEscalador;
    }

    public void setNombreEscalador(String nombreEscalador) {
        this.nombreEscalador = nombreEscalador;
    }

    public String getApellidosEscalador() {
        return apellidosEscalador;
    }

    public void setApellidosEscalador(String apellidosEscalador) {
        this.apellidosEscalador = apellidosEscalador;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }
}
