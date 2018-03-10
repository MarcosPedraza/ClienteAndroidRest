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
import android.widget.Toast;

import com.mareinc.marcospedraza.clienteandroidrest.modelos.Usuario;
import com.mareinc.marcospedraza.clienteandroidrest.utils.WebService;

import org.json.JSONException;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserDeleteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserDeleteFragment extends Fragment {

    private static final String TAG = "UserDeleteFragment";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //widgets

    Button btn_delete;
    EditText et_id;

    //vars
    Usuario mUser;


    public UserDeleteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserDeleteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserDeleteFragment newInstance(String param1, String param2) {
        UserDeleteFragment fragment = new UserDeleteFragment();
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
        View view = inflater.inflate(R.layout.fragment_user_delete, container, false);
        et_id = view.findViewById(R.id.et_delete_user_frag);
        btn_delete = view.findViewById(R.id.btn_delete_user_frag);


        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDataFromUI();
            }
        });



        return view;
    }


    public void getDataFromUI()
    {
        String id = et_id.getText().toString().trim();

        if(id.equals(""))
        {
            et_id.setError("debe ingresar el usuario a eliminar");
            return;
        }


        mUser = new Usuario(Integer.valueOf(id),"",0.0,"desconocido");

        CallServer callServer = new CallServer();
        callServer.execute();
    }



    private class CallServer extends AsyncTask<String, Void, Void>
    {

        String result;
        @Override
        protected Void doInBackground(String... strings) {
            try {
                result = WebService.eliminarUsuario(mUser,"borrar");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Log.d(TAG, "onPostExecute: " + result);
            Toast.makeText(getContext(),result,Toast.LENGTH_SHORT).show();

        }
    }

}
