package com.mareinc.marcospedraza.clienteandroidrest.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mareinc.marcospedraza.clienteandroidrest.R;
import com.mareinc.marcospedraza.clienteandroidrest.modelos.Usuario;

import java.util.List;

/**
 * Created by Marcos Pedraza on 07/03/2018.
 */

public class AdapterUsuarios extends BaseAdapter {

    Context context;
    List<Usuario> mLista;

    public AdapterUsuarios(Context context, List<Usuario> lista)
    {
        this.context = context;
        this.mLista = lista;
    }

    static class ViewHolder
    {
        private TextView txt_id;
        private TextView txt_nombre;
        private TextView txt_estado;
        private TextView txt_monto;
    }

    @Override
    public int getCount() {
        return mLista.size();
    }

    @Override
    public Object getItem(int i) {
        return mLista.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if(convertView == null)
        {
            LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.row_view,null);

            holder = new ViewHolder();

            holder.txt_id = convertView.findViewById(R.id.list_row_id_txt);
            holder.txt_nombre = convertView.findViewById(R.id.list_row_name_txt);
            holder.txt_estado = convertView.findViewById(R.id.list_row_estado_txt);
            holder.txt_monto = convertView.findViewById(R.id.list_row_monto_txt);


            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.txt_id.setText("id: "+mLista.get(position).getIdUser());
        holder.txt_nombre.setText("nombre: "+mLista.get(position).getNombre());
        holder.txt_estado.setText("estado: "+ mLista.get(position).getEstado());
        holder.txt_monto.setText("monto: "+mLista.get(position).getMonto().toString());


        return convertView;
    }
}
