package app.ps.com.chapatucombi1v1.modelo;

/**
 * Created by Renzo on 9/12/2017.
 */

public class Ruta {
    private int id;
    private String nombre;
    private int empresaId;

    public Ruta() {
    }

    public Ruta(int id, String nombre, int empresaId) {
        this.id = id;
        this.nombre = nombre;
        this.empresaId = empresaId;
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

    public int getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(int empresaId) {
        this.empresaId = empresaId;
    }
}
