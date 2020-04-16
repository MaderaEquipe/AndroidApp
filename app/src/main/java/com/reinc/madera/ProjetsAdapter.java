package com.reinc.madera;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ProjetsAdapter extends ArrayAdapter<Projet> {
        private List<Projet> data; // Le tableau représentant les données à insérer dans la liste
        private int listItemResLayout; // Le Layout de la vue personnalisée, pour chaque ligne de la liste
        private Context context; // Le Context de la vue

        public ProjetsAdapter(Context context, int resource, List<Projet> data) {
            super(context, resource, data);
            this.data = data;
            this.listItemResLayout = resource;
            this.context = context;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = convertView;
            ViewHolderProjet holder;

            if (null == view) {
                LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = layoutInflater.inflate(listItemResLayout, parent, false);

                final TextView tvProjetLabelPlan = (TextView) view.findViewById(R.id.projetLabelPlan);
                final TextView tvProjetDatePlan = (TextView) view.findViewById(R.id.projetDatePlan);

                // On stocke les Widgets dans le Holder pour optimiser les appels au "view.findViewById"
                holder = new ViewHolderProjet();
                holder.labelPlan = tvProjetLabelPlan;
                holder.datePlan = tvProjetDatePlan;
                view.setTag(holder);

            } else {
                holder = (ViewHolderProjet) view.getTag();

            }

            final Projet currentProjet = data.get(position);

            if (currentProjet != null) {
                holder.labelPlan.setText(currentProjet.getLabelPlan());
                holder.datePlan.setText( currentProjet.getDatePlan());
            }

            return view;
        }

        @Override
        public int getCount() {
            if (data != null) return data.size();
            return 0;
        }

    }
    class ViewHolderProjet {
        TextView labelPlan;
        TextView datePlan;
    }

