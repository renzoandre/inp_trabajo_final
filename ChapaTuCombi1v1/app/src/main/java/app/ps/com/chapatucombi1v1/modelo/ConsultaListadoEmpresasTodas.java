package app.ps.com.chapatucombi1v1.modelo;

import android.os.AsyncTask;
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

import app.ps.com.chapatucombi1v1.modelo.Empresa;

/**
 * Created by Renzo on 29/11/2017.
 */
public class ConsultaListadoEmpresasTodas extends AsyncTask<Void, Void, Boolean> {

    Empresa empresa = new Empresa();
    ArrayList<Empresa> lstEmpresas = new ArrayList<>();

    @Override
    protected Boolean doInBackground(Void... params) {
        Log.i("chassspa:" , "");
        final String NAMESPACE = "http://ws/";
        final String URL = "http://192.168.43.119:9090/ChapaTuCombiWS/ServicioWeb?WSDL";
        final String METHOD_NAME = "consultarEmpresasTodas";
        final String SOAP_ACTION = NAMESPACE + METHOD_NAME;

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);

        HttpTransportSE ht = new HttpTransportSE(URL);

        try {
            ht.call(SOAP_ACTION, envelope);
            SoapObject response = (SoapObject)envelope.bodyIn;

            for (int i = 0; i < response.getPropertyCount(); i++) {
                SoapObject ic = (SoapObject)response.getProperty(i);
                Log.i("chapa:" , ic.getProperty(0).toString());
                Log.i("id:" , ic.getProperty(1).toString());
                Log.i("nombre:" , ic.getProperty(2).toString());
                Empresa e = new Empresa();
                e.setId(Integer.parseInt(ic.getProperty(1).toString()));
                e.setNombre(ic.getProperty(2).toString());
                e.setChapa(ic.getProperty(0).toString());
                lstEmpresas.add(e);
            }

            return true;
        } catch (Exception e) {
            Log.i("Error: ", e.getMessage());
            e.printStackTrace();
            return false;
        }


    }

    public ArrayList<Empresa> retornarValor() {
        return lstEmpresas;
    }

/*
    @Override
    protected void onPostExecute(final Boolean success) {
        if (success == false) {
            Toast.makeText(getActivity().getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context.getApplicationContext(), "El resultado es: " + resultado, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCancelled() {
        Toast.makeText(context.getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
    }
*/

}