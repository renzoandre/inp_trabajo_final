package app.ps.com.chapatucombi1v1.modelo;

import android.media.Image;

/**
 * Created by Renzo on 9/12/2017.
 */

public class Empresa {
    private int id;
    private String nombre;
    private String chapa;
    private Image logo;

    public Empresa() {
    }

    public Empresa(int id, String nombre, String chapa, Image logo) {
        this.id = id;
        this.nombre = nombre;
        this.chapa = chapa;
        this.logo = logo;
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

    public String getChapa() {
        return chapa;
    }

    public void setChapa(String chapa) {
        this.chapa = chapa;
    }

    public Image getLogo() {
        return logo;
    }

    public void setLogo(Image logo) {
        this.logo = logo;
    }
}
