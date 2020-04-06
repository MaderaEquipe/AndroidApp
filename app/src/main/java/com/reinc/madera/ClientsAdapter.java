package com.reinc.madera;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ClientsAdapter extends ArrayAdapter<Client> {
    private List<Client> data; // Le tableau représentant les données à insérer dans la liste
    private int listItemResLayout; // Le Layout de la vue personnalisée, pour chaque ligne de la liste
    private Context context; // Le Context de la vue

    public ClientsAdapter(Context context, int resource, List<Client> data) {
        super(context, resource, data);
        this.data = data;
        this.listItemResLayout = resource;
        this.context = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        ViewHolder holder;

        if (null == view) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(listItemResLayout, parent, false);

            final TextView tvUserName = (TextView) view.findViewById(R.id.userName);
            final TextView tvUserSurname = (TextView) view.findViewById(R.id.UserSurname);

            // On stocke les Widgets dans le Holder pour optimiser les appels au "view.findViewById"
            holder = new ViewHolder();
            holder.name = tvUserName;
            holder.surname = tvUserSurname;
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();

        }

        final Client currentClient = data.get(position);

        if (currentClient != null) {
            holder.name.setText(currentClient.getUserName());
            holder.surname.setText( currentClient.getUserSurname());
        }

        return view;
    }

    @Override
    public int getCount() {
        if (data != null) return data.size();
        return 0;
    }

}
class ViewHolder {
    TextView name;
    TextView surname;
}