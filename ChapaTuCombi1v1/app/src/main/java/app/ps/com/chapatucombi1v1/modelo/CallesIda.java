package app.ps.com.chapatucombi1v1.modelo;

/**
 * Created by Renzo on 20/12/2017.
 */

public class CallesIda {
    private int id;
    private String nombre;
    private int rutaId;

    public CallesIda() {
    }

    public CallesIda(int id, String nombre, int rutaId) {
        this.id = id;
        this.nombre = nombre;
        this.rutaId = rutaId;
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

    public int getRutaId() {
        return rutaId;
    }

    public void setRutaId(int rutaId) {
        this.rutaId = rutaId;
    }
}
