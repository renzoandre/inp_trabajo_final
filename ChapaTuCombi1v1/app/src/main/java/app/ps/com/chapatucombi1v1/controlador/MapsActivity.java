package app.ps.com.chapatucombi1v1.controlador;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import app.ps.com.chapatucombi1v1.R;
import app.ps.com.chapatucombi1v1.modelo.ConsultaUbicacionesUnidades;
import app.ps.com.chapatucombi1v1.modelo.UbicacionUnidad;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Integer rutaId = 0;
    ArrayList<UbicacionUnidad> lstUbicacionUnidads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        rutaId = (Integer) this.getIntent().getSerializableExtra("rutaId");

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        ConsultaUbicacionesUnidades cuu = new ConsultaUbicacionesUnidades();
        cuu.execute(rutaId);
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex){
        }
        lstUbicacionUnidads = cuu.retornarValor();

        for (UbicacionUnidad uu : lstUbicacionUnidads) {
            LatLng unidad = new LatLng(uu.getLatitud(), uu.getLongitud());
            mMap.addMarker(new MarkerOptions().position(unidad).title(uu.getUnidadId()+""));
            float zoom = 13;
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(unidad, zoom));
        }

        // Add a marker in Sydney and move the camera

    }
}
