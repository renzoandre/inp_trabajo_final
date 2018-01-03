package app.ps.com.chapatucombi1v1.modelo;

/**
 * Created by Renzo on 9/12/2017.
 */

public class Unidad {
    private int id;
    private String nombre;
    private boolean activo;
    private int rutaId;
    private String ide;

    public Unidad() {
    }

    public Unidad(int id, String nombre, boolean activo, int rutaId, String ide) {
        this.id = id;
        this.nombre = nombre;
        this.activo = activo;
        this.rutaId = rutaId;
        this.ide = ide;
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

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getRutaId() {
        return rutaId;
    }

    public void setRutaId(int rutaId) {
        this.rutaId = rutaId;
    }

    public String getIde() {
        return ide;
    }

    public void setIde(String ide) {
        this.ide = ide;
    }
}
