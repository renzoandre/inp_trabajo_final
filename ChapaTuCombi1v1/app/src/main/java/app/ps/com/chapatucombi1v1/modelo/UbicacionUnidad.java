package app.ps.com.chapatucombi1v1.modelo;

/**
 * Created by Renzo on 9/12/2017.
 */

public class UbicacionUnidad {
    private int id;
    private double latitud;
    private double longitud;
    private int unidadId;

    public UbicacionUnidad() {
    }

    public UbicacionUnidad(int id, double latitud, double longitud, int unidadId) {
        this.id = id;
        this.latitud = latitud;
        this.longitud = longitud;
        this.unidadId = unidadId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public int getUnidadId() {
        return unidadId;
    }

    public void setUnidadId(int unidadId) {
        this.unidadId = unidadId;
    }
}
