package app.ps.com.chapatucombi1v1.controlador;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import app.ps.com.chapatucombi1v1.R;
import app.ps.com.chapatucombi1v1.modelo.Empresa;

/**
 * Created by Renzo on 9/12/2017.
 */

public class EmpresaAdapter extends ArrayAdapter<Empresa> {

    ArrayList<Empresa> empresaList = new ArrayList<>();

    public EmpresaAdapter(Context context, int resource, ArrayList<Empresa> empresas) {
        super(context, resource, empresas);
        empresaList = empresas;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_empresa, parent, false);
        }

        Empresa empresa = getItem(position);
        TextView txtNombreEmpresa = (TextView) convertView.findViewById(R.id.txt_empresa_nombre);
        TextView txtNombreChapa = (TextView) convertView.findViewById(R.id.txt_empresa_chapa);


        txtNombreEmpresa.setText(empresa.getNombre());
        txtNombreChapa.setText(empresa.getChapa());

        return convertView;
    }

    public void accionBoton (View view) {
        Log.d("_______", "---");
    }

}
