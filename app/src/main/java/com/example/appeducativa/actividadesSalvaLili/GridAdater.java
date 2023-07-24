package com.example.appeducativa.actividadesSalvaLili;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.appeducativa.R;

public class GridAdater extends BaseAdapter {

    private int sets = 0;

    public GridAdater(int sets) {
        this.sets = sets;
    }

    @Override
    public int getCount() {
        return sets;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
        if (convertView == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.set_item, parent, false);
        } else {
            view = convertView;
        }
        // Obtener el conjunto seleccionado (1 o 2) según la posición
        final int selectedSet = position + 1;

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crear un Intent para iniciar la actividad QuestionsActivity
                Intent questionIntent = new Intent(parent.getContext(), QuestionsActivity.class);

                // Agregar el conjunto seleccionado como extra en el Intent
                questionIntent.putExtra("selected_set", selectedSet);

                parent.getContext().startActivity(questionIntent);
            }
        });

        ((TextView) view.findViewById(R.id.textview)).setText(String.valueOf(position + 1));

        return view;
    }
}
