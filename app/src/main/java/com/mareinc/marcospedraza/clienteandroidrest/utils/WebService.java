package com.mareinc.marcospedraza.clienteandroidrest.utils;

import com.mareinc.marcospedraza.clienteandroidrest.modelos.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by Marcos Pedraza on 07/03/2018.
 */

public class WebService {

    private static String NAMESPACE = "http://wsDatos/";
    //Webservice URL - WSDL File location
    private static String URL = "http://192.168.100.5:8080/WSDatosBD/WSDDatosSOAP?WSDL";
    //SOAP Action URI again Namespace + Web method name
    private static String SOAP_ACTION = "http://wsDatos/";

    public static String consultarUsers(String methodName)
    {
        String jsonRespo = "";
        SoapObject request = new SoapObject(NAMESPACE, methodName);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);


        try
        {
            httpTransportSE.call(SOAP_ACTION+methodName, envelope);
            SoapPrimitive responce = (SoapPrimitive)envelope.getResponse();

            jsonRespo = responce.toString();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            jsonRespo = e.getMessage();
        }


        return jsonRespo;
    }

    public static String buscarDatos(String id,String methodName){


        String respText;
        SoapObject request = new SoapObject(NAMESPACE, methodName);
        PropertyInfo dataToSend = new PropertyInfo();
        dataToSend.setName("id");
        dataToSend.setValue(id);
        dataToSend.setType(String.class);

        request.addProperty(dataToSend);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);

        try
        {
            httpTransportSE.call(SOAP_ACTION+methodName,envelope);
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            respText = response.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            respText = "algo salio mal";
        }

        return respText;

    }

    public static String insertarUsuario(Usuario user, String methodName) throws JSONException {

        String respText;
        SoapObject request = new SoapObject(NAMESPACE, methodName);
        PropertyInfo dataToSend = new PropertyInfo();
        dataToSend.setName("json");
        dataToSend.setValue(generateJson(user));
        dataToSend.setType(String.class);

        request.addProperty(dataToSend);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);

        try
        {
            httpTransportSE.call(SOAP_ACTION+methodName,envelope);
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            respText = response.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            respText = "algo salio mal";
        }

        return respText;
    }


    public static String actualizarUsuario(Usuario user, String methodName) throws JSONException {

        String respText;
        SoapObject request = new SoapObject(NAMESPACE, methodName);
        PropertyInfo dataToSend = new PropertyInfo();
        dataToSend.setName("json");
        dataToSend.setValue(generateJson(user));
        dataToSend.setType(String.class);

        request.addProperty(dataToSend);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);

        try
        {
            httpTransportSE.call(SOAP_ACTION+methodName,envelope);
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            respText = response.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            respText = "algo salio mal";
        }

        return respText;
    }

    public static String eliminarUsuario(Usuario user, String methodName) throws JSONException {

        String respText;
        SoapObject request = new SoapObject(NAMESPACE, methodName);
        PropertyInfo dataToSend = new PropertyInfo();
        dataToSend.setName("json");
        dataToSend.setValue(generateJson(user));
        dataToSend.setType(String.class);

        request.addProperty(dataToSend);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);

        try
        {
            httpTransportSE.call(SOAP_ACTION+methodName,envelope);
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            respText = response.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            respText = e.getMessage();
        }

        return respText;
    }




    public static String generateJson(Usuario userToJson) throws JSONException {
        JSONObject json = new JSONObject();
        JSONArray arraJson = new JSONArray();
        json.put("id",userToJson.getIdUser());
        json.put("nombre", userToJson.getNombre());
        json.put("monto",Float.valueOf(userToJson.getMonto().toString()));
        json.put("estado",userToJson.getEstado());
        arraJson.put(json);

        return arraJson.toString();
    }

}
