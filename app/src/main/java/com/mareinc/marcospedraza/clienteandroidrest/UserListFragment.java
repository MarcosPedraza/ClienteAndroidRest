package com.mareinc.marcospedraza.clienteandroidrest;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.mareinc.marcospedraza.clienteandroidrest.adapters.AdapterUsuarios;
import com.mareinc.marcospedraza.clienteandroidrest.modelos.Usuario;
import com.mareinc.marcospedraza.clienteandroidrest.utils.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserListFragment extends Fragment {

    private static final String TAG = "UserListFragment";
    //widgets
    TextView txt_result;
    ListView lv_usuarios;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public UserListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserListFragment newInstance(String param1, String param2) {
        UserListFragment fragment = new UserListFragment();
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
        View view = inflater.inflate(R.layout.fragment_user_list, container, false);


        lv_usuarios = view.findViewById(R.id.lv_users);
        txt_result = view.findViewById(R.id.txt_result);

        CallServer task = new CallServer();
        task.execute();


        return view;
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

        lv_usuarios.setAdapter(new AdapterUsuarios(getContext(),mArrayList));
    }


    private class CallServer extends AsyncTask<String, Void, Void>
    {

        String result;
        @Override
        protected Void doInBackground(String... strings) {
            result = WebService.consultarUsers("consulta");

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



}
