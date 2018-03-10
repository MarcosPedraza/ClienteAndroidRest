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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mareinc.marcospedraza.clienteandroidrest.modelos.Usuario;
import com.mareinc.marcospedraza.clienteandroidrest.utils.WebService;

import org.json.JSONException;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InsertUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InsertUserFragment extends Fragment {


    private static final String TAG = "InsertUserFragment";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //witget
    Button btn_probar;

    EditText et_nombre;
    Spinner spinner_estado;
    EditText et_monto;

    //vars

    Usuario mUser;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public InsertUserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InsertUserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InsertUserFragment newInstance(String param1, String param2) {
        InsertUserFragment fragment = new InsertUserFragment();
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
        View view = inflater.inflate(R.layout.fragment_insert_user, container, false);

        btn_probar = view.findViewById(R.id.btn_insertFrag_probar);

        et_nombre = view.findViewById(R.id.et_nombre_insert_user);
        spinner_estado = view.findViewById(R.id.spinner_estado);
        et_monto = view.findViewById(R.id.et_monto_insert_user);

        btn_probar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usuario user = new Usuario(8,"Enrique Pedraza",1800.0,"activo");

                try {
                    Log.d(TAG, "onClick: JSON: "+ WebService.generateJson(user));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                getDataFromUI();


            }
        });

        return  view;
    }

    public void getDataFromUI()
    {
        String nombre = et_nombre.getText().toString().trim();
        String monto = et_monto.getText().toString().trim();
        String estado = spinner_estado.getSelectedItem().toString().trim();
        if(nombre.equals(""))
        {
            et_nombre.setError("debe llenar el campo");
            return;
        }
        if(monto.equals(""))
        {
            et_monto.setError("debe llenar el campo");
            return;
        }

        mUser = new Usuario(0,nombre,Double.valueOf(monto),estado);

        CallServer callServer = new CallServer();
        callServer.execute();
    }

    private class CallServer extends AsyncTask<String, Void, Void>
    {

        String result;
        @Override
        protected Void doInBackground(String... strings) {
            try {
                result = WebService.insertarUsuario(mUser,"insertar");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            et_nombre.setText("");
            et_monto.setText("");

            Toast.makeText(getContext(),result,Toast.LENGTH_SHORT).show();
            Log.d(TAG, "onPostExecute: " + result);

        }
    }

}
