package com.reinc.madera;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class DevisAdapter extends ArrayAdapter<Devis> {
    private List<Devis> data; // Le tableau représentant les données à insérer dans la liste
    private int listItemResLayout; // Le Layout de la vue personnalisée, pour chaque ligne de la liste
    private Context context; // Le Context de la vue

    public DevisAdapter(Context context, int resource, List<Devis> data) {
        super(context, resource, data);
        this.data = data;
        this.listItemResLayout = resource;
        this.context = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        ViewHolderDevis holder;

        if (null == view) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(listItemResLayout, parent, false);

            final TextView tvDevisNom = (TextView) view.findViewById(R.id.devisNom);
            final TextView tvDevisMontant = (TextView) view.findViewById(R.id.devisMontant);

            // On stocke les Widgets dans le Holder pour optimiser les appels au "view.findViewById"
            holder = new ViewHolderDevis();
            holder.nom = tvDevisNom;
            holder.montant = tvDevisMontant;
            view.setTag(holder);

        } else {
            holder = (ViewHolderDevis) view.getTag();

        }

        final Devis currentDevis = data.get(position);

        if (currentDevis != null) {
            holder.nom.setText(currentDevis.getEtatDevis());
            holder.montant.setText( currentDevis.getCreationDate());
        }

        return view;
    }

    @Override
    public int getCount() {
        if (data != null) return data.size();
        return 0;
    }

}
class ViewHolderDevis {
    TextView nom;
    TextView montant;
}