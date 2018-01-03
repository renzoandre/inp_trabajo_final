package app.ps.com.chapatucombi1v1.modelo;

import android.os.AsyncTask;
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

import app.ps.com.chapatucombi1v1.modelo.Ruta;

/**
 * Created by Renzo on 30/12/2017.
 */

public class ConsultaDetalleEmpresa extends AsyncTask<Integer, Void, Boolean> {

    Ruta ruta = new Ruta();
    public ArrayList<Ruta> lstRutas = new ArrayList<>();

    @Override
    protected Boolean doInBackground(Integer... params) {
        Log.i("chassspa:" , "");
        final String NAMESPACE = "http://ws/";
        final String URL = "http://192.168.43.119:9090/ChapaTuCombiWS/ServicioWeb?WSDL";
        final String METHOD_NAME = "consultarRutaPorEmpresaId";
        final String SOAP_ACTION = NAMESPACE + METHOD_NAME;

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        request.addProperty("idEmpresa", params[0]);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);

        HttpTransportSE ht = new HttpTransportSE(URL);

        try {
            ht.call(SOAP_ACTION, envelope);
            SoapObject response = (SoapObject)envelope.bodyIn;

            for (int i = 0; i < response.getPropertyCount(); i++) {
                SoapObject ic = (SoapObject)response.getProperty(i);
                Log.i("empresaId::::" , ic.getProperty(0).toString());
                Log.i("id:::" , ic.getProperty(1).toString());
                Log.i("nombre::::" , ic.getProperty(2).toString());
                Ruta r = new Ruta();
                r.setEmpresaId(Integer.parseInt(ic.getProperty(0).toString()));
                r.setId(Integer.parseInt(ic.getProperty(1).toString()));
                r.setNombre(ic.getProperty(2).toString());
                lstRutas.add(r);
            }
            return true;
        } catch (Exception e) {
            Log.i("Error: ", e.getMessage());
            e.printStackTrace();
            return false;
        }


    }

    public ArrayList<Ruta> retornarValor() {
        return lstRutas;
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
