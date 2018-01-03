package app.ps.com.chapatucombi1v1.modelo;

import android.os.AsyncTask;
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

import app.ps.com.chapatucombi1v1.modelo.CallesIda;

/**
 * Created by Renzo on 30/12/2017.
 */

public class ConsultaCallesIda extends AsyncTask<Integer, Void, Boolean> {

    public ArrayList<CallesIda> lstCallesIda = new ArrayList<>();

    @Override
    protected Boolean doInBackground(Integer... params) {
        Log.i("chassspa:" , "");
        final String NAMESPACE = "http://ws/";
        final String URL = "http://192.168.43.119:9090/ChapaTuCombiWS/ServicioWeb?WSDL";
        final String METHOD_NAME = "consultarCallesIdaPorRutaId";
        final String SOAP_ACTION = NAMESPACE + METHOD_NAME;

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        request.addProperty("rutaId", params[0]);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);

        HttpTransportSE ht = new HttpTransportSE(URL);

        try {
            ht.call(SOAP_ACTION, envelope);
            SoapObject response = (SoapObject)envelope.bodyIn;

            for (int i = 0; i < response.getPropertyCount(); i++) {
                SoapObject ic = (SoapObject)response.getProperty(i);
                Log.i("id::::" , ic.getProperty(0).toString());
                Log.i("nombre:::" , ic.getProperty(1).toString());
                Log.i("rutaId::::" , ic.getProperty(2).toString());
                CallesIda r = new CallesIda();
                r.setId(Integer.parseInt(ic.getProperty(0).toString()));
                r.setNombre(ic.getProperty(1).toString());
                r.setRutaId(Integer.parseInt(ic.getProperty(2).toString()));
                lstCallesIda.add(r);
            }
            return true;
        } catch (Exception e) {
            Log.i("Error: ", e.getMessage());
            e.printStackTrace();
            return false;
        }


    }

    public ArrayList<CallesIda> retornarValor() {
        return lstCallesIda;
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
