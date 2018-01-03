package app.ps.com.chapatucombi1v1.controlador;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

import app.ps.com.chapatucombi1v1.R;
import app.ps.com.chapatucombi1v1.modelo.ConsultaEmpresasPorCalles;
import app.ps.com.chapatucombi1v1.modelo.ConsultaListadoEmpresasTodas;
import app.ps.com.chapatucombi1v1.modelo.Empresa;

public class MainActivity extends AppCompatActivity {

    ArrayList<Empresa> empresas = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    EmpresaAdapter empresaAdapter;
    ListView simpleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        simpleList = (ListView) findViewById(R.id.simpleListEmpresas);
        ConsultaListadoEmpresasTodas l = new ConsultaListadoEmpresasTodas();
        l.execute();
        empresas = l.retornarValor();

        empresaAdapter = new EmpresaAdapter(this, R.layout.row_empresa, empresas);
        simpleList.setAdapter(empresaAdapter);

        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(MainActivity.this, ActivityEmpresa.class);
                intent.putExtra("empresa", empresas.get(position).getId() + "?" + empresas.get(position).getNombre());
                startActivity(intent);
            }
        });

        //Toast.makeText(getApplicationContext(), "Error" , Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.menuSearch);
        SearchView searchView =(SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ConsultaEmpresasPorCalles l = new ConsultaEmpresasPorCalles();
                l.execute(newText);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex){
                    Log.e("Mensaje", "NO se conecto");
                }
                empresas = l.retornarValor();
                empresaAdapter = new EmpresaAdapter(getApplicationContext(), R.layout.row_empresa, empresas);
                simpleList.setAdapter(empresaAdapter);

                return false;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                ConsultaListadoEmpresasTodas l = new ConsultaListadoEmpresasTodas();
                l.execute();
                empresas = l.retornarValor();

                empresaAdapter = new EmpresaAdapter(getApplicationContext(), R.layout.row_empresa, empresas);
                simpleList.setAdapter(empresaAdapter);

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);

    }

}
