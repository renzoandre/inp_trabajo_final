package app.ps.com.chapatucombi1v1.modelo;

/**
 * Created by Renzo on 20/12/2017.
 */

public class UbicacionIda {
    private int id;
    private double latitud;
    private double longitud;
    private int rutaId;

    public UbicacionIda(int id, double latitud, double longitud, int rutaId) {
        this.id = id;
        this.latitud = latitud;
        this.longitud = longitud;
        this.rutaId = rutaId;
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

    public int getRutaId() {
        return rutaId;
    }

    public void setRutaId(int rutaId) {
        this.rutaId = rutaId;
    }
}
