package com.mareinc.marcospedraza.clienteandroidrest;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mareinc.marcospedraza.clienteandroidrest.adapters.AdapterUsuarios;
import com.mareinc.marcospedraza.clienteandroidrest.modelos.Usuario;
import com.mareinc.marcospedraza.clienteandroidrest.utils.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link paymentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class paymentFragment extends Fragment {
    private static final String TAG = "paymentFragment";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //widgets
    Button btn_cobro;
    ImageButton btn_busqueda;
    EditText et_busqueda,et_cobro;
    TextView tv_id,tv_nombre,tv_estado,tv_saldo;

    //vars
    Usuario mUser;


    public paymentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment paymentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static paymentFragment newInstance(String param1, String param2) {
        paymentFragment fragment = new paymentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_payment, container, false);

        //enlazar layouts
        et_busqueda = view.findViewById(R.id.et_busqueda_payfragment);
        btn_busqueda = view.findViewById(R.id.btn_busqueda_payment_frag);
        tv_id = view.findViewById(R.id.tv_id_payment_frag);
        tv_nombre = view.findViewById(R.id.tv_nombre_payment_frag);
        tv_estado = view.findViewById(R.id.tv_estado_payment_frag);
        tv_saldo = view.findViewById(R.id.tv_saldo_payment_frag);
        et_cobro = view.findViewById(R.id.et_monto_payment_frag);
        btn_cobro = view.findViewById(R.id.btn_cobro_payment_frag);


        btn_busqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = et_busqueda.getText().toString();
                buscarUsuario(id);
            }
        });


        btn_cobro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mUser == null)
                {
                    Toast.makeText(getContext(), "aun no se a cargado a un usuario", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getContext(), "se cobrara al usuario: "+ mUser.getNombre(), Toast.LENGTH_SHORT).show();
                    realizarCobro();
                }
            }
        });

        return view;
    }//acaba onCreateView

    public void realizarCobro()
    {
        String monto = et_cobro.getText().toString();

        if(monto.equals(""))
        {
            et_cobro.setError("debe de poner un monto a cobrar");
            return;
        }

        Double newMonto = mUser.getMonto() - Double.valueOf(monto);


        if(newMonto >= 0)
        {
            mUser.setMonto(newMonto);
            updateCallServer task = new updateCallServer();
            task.execute();
        }
        else
        {
            Toast.makeText(getContext(), "el saldo es insuficiente", Toast.LENGTH_SHORT).show();
        }



    }

    public void buscarUsuario(String idUser)
    {

        if(idUser.equals(""))
        {
            et_busqueda.setError("Ingrese el usuario a Buscar");
            return;
        }

        CallServer task  = new CallServer();
        task.execute(idUser);


    }


    public void getUsers(String json) throws JSONException
    {
        ArrayList<Usuario> mArrayList = new ArrayList<>();


        JSONArray elJson = new JSONArray(json);

        for (int i =0; i < elJson.length();i++)
        {
            Usuario user = new Usuario();
            JSONObject obj = elJson.getJSONObject(i);

            user.setId(obj.getInt("id"));
            user.setNombre(obj.getString("nombre"));
            user.setMonto(obj.getDouble("monto"));
            user.setEstado(obj.getString("estado"));
            Log.d(TAG, "getUsers: " + obj.getInt("id"));

            mArrayList.add(user);

        }

        if(!mArrayList.isEmpty()){

            tv_id.setText("Id: "+ mArrayList.get(0).getIdUser());
            tv_nombre.setText("Nombre: "+mArrayList.get(0).getNombre());
            tv_estado.setText("Estado: "+mArrayList.get(0).getEstado());
            tv_saldo.setText("Saldo: "+ mArrayList.get(0).getMonto().toString());

            mUser = mArrayList.get(0);
        }
        else
        {
            Toast.makeText(getContext(), "No encontrado", Toast.LENGTH_SHORT).show();
            tv_id.setText("Id:");
            tv_nombre.setText("Nombre:");
            tv_estado.setText("Estado:");
            tv_saldo.setText("Saldo:");
            et_busqueda.setText("");
            mUser = null;
        }


    }


    private class CallServer extends AsyncTask<String, Void, Void>
    {

        String result;

        @Override
        protected Void doInBackground(String... strings) {
            String id = strings[0];

            result = WebService.buscarDatos(id,"busqueda");
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //txt_result.setText(result);
            try {
                getUsers(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class updateCallServer extends AsyncTask<String, Void, Void>
    {

        String result;

        @Override
        protected Void doInBackground(String... strings) {

            try {
                result = WebService.actualizarUsuario(mUser,"actualizar");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            buscarUsuario(""+mUser.getIdUser());
        }
    }

}
