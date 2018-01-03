package app.ps.com.chapatucombi1v1.controlador;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import app.ps.com.chapatucombi1v1.R;
import app.ps.com.chapatucombi1v1.modelo.ConsultaCallesIda;
import app.ps.com.chapatucombi1v1.modelo.ConsultaCallesVuelta;
import app.ps.com.chapatucombi1v1.modelo.ConsultaDetalleEmpresa;
import app.ps.com.chapatucombi1v1.modelo.CallesIda;
import app.ps.com.chapatucombi1v1.modelo.CallesVuelta;
import app.ps.com.chapatucombi1v1.modelo.Ruta;

/**
 * Created by Renzo on 9/12/2017.
 */

public class ActivityEmpresa extends AppCompatActivity {

    ArrayList<Ruta> rutas = new ArrayList<>();
    ArrayList<CallesIda> callesIda = new ArrayList<>();
    ArrayList<CallesVuelta> callesVuelta = new ArrayList<>();
    Integer rutaIdSeleccionada = 0;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresa);

        //Recibe id?nombre de empresa, se separan ambos datos para mostrar el nombre de la empresa
        //y tener su respectivo id para la busqueda de las rutas correspondientes
        String empresa = (String) this.getIntent().getSerializableExtra("empresa");
        int empresaId = Integer.parseInt(empresa.substring(0, empresa.indexOf("?")));
        String empresaNombre = empresa.substring(empresa.indexOf("?") + 1);
        TextView txtNombre = (TextView) findViewById(R.id.txt_empresa_nombre);
        txtNombre.setText( empresaNombre);

        //Con el id de empresa anteriormente separado de hacer la busqueda de las rutas y se agrega
        //a la lista rutas
        //LLAMA webservice
        ConsultaDetalleEmpresa de = new ConsultaDetalleEmpresa();
        de.execute(empresaId);
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex){
        }
        rutas = de.retornarValor();

        //Se busca el layyout que contendra un grupo de botones cuya cantidad depente de la cantidad
        //de rutas que se haya encontrado por el id de la empresa
        LinearLayout layout = (LinearLayout) findViewById(R.id.layout_botones_rutas);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        //Se construye los botones de acuerdo a la cantidad de rutas y se obtiene los datos de cada
        //ruta r

        for (final Ruta r : rutas) {
            final Button btn = new Button(this);
            btn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            btn.setText("RUTA " + r.getNombre());
            btn.setId(r.getId());
            btn.setOnClickListener(new View.OnClickListener() {//Se programa la accion del boton al hacer clic
                public void onClick(View v) {
                    rutaIdSeleccionada = r.getId();
                    TextView txtRuta = (TextView) findViewById(R.id.txt_ruta_nombre);
                    txtRuta.setText(btn.getText());
                    //Se hace visible el layout que contendra el listado de calles de ida y vuelta
                    LinearLayout layoutB = (LinearLayout) findViewById(R.id.layout_rutas_ida_vuelta);
                    LinearLayout layoutBM = (LinearLayout) findViewById(R.id.layout_botones_mapas);
                    layoutBM.setVisibility(View.VISIBLE);
                    layoutB.setVisibility(View.VISIBLE);

                    ConsultaCallesIda ci = new ConsultaCallesIda();
                    ci.execute(r.getId());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex){
                    }
                    callesIda = ci.retornarValor();
                    //Se construye el ListView de calles ida
                    construirListaIda();

                    ConsultaCallesVuelta cv = new ConsultaCallesVuelta();
                    cv.execute(r.getId());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex){
                    }
                    callesVuelta = cv.retornarValor();
                    //Se construye el ListView de calles vueta
                    construirListaVuelta();
                }
            });
            layout.addView(btn);
        }
    }

    public void construirListaIda() {
        ArrayList<String> listaIda = new ArrayList<String>();
        ListView listview = (ListView) findViewById(R.id.simple_list_ida);
        for (CallesIda ci : callesIda) {
            listaIda.add(ci.getNombre());
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaIda);
        listview.setAdapter(adapter);
    }

    public void construirListaVuelta() {
        ArrayList<String> listaVuelta = new ArrayList<String>();
        ListView listview = (ListView) findViewById(R.id.simple_list_vuelta);
        for (CallesVuelta ci : callesVuelta) {
            listaVuelta.add(ci.getNombre());
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaVuelta);
        listview.setAdapter(adapter);
    }

    public void verUnidadesMapa(View view) {
        Intent intent = new Intent(ActivityEmpresa.this, MapsActivity.class);
        intent.putExtra("rutaId", rutaIdSeleccionada);
        startActivity(intent);
    }

}
