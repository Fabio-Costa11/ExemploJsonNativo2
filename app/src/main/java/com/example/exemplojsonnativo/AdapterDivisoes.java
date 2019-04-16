package com.example.exemplojsonnativo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterDivisoes extends BaseAdapter {

    private Context context;
    private ArrayList<DivisoesModel> divisoesModelArrayList;

    public AdapterDivisoes(Context context, ArrayList<DivisoesModel> divisoesModelArrayList) {
        this.context = context;
        this.divisoesModelArrayList = divisoesModelArrayList;
    }

    @Override
    public int getCount() {
        return divisoesModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return divisoesModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_divisoes, null, true);

            holder.id_divisao = convertView.findViewById(R.id.textView_id_divisao);
            holder.descricao_divisao = convertView.findViewById(R.id.textView_descricao_divisao);

            holder.id_divisao.setText(divisoesModelArrayList.get(position).getId_divisao());
            holder.descricao_divisao.setText(divisoesModelArrayList.get(position).getDescricao());

        }
        return convertView;
    }

    private class ViewHolder {

        protected TextView id_divisao, descricao_divisao;
    }
}
