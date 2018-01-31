package marcelinaar.uasdutapolinema.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import marcelinaar.uasdutapolinema.Model.Prestasi;
import marcelinaar.uasdutapolinema.R;

/**
 * Created by User on 28/01/2018.
 */

public class PrestasiList extends ArrayAdapter<Prestasi> {
    private Activity ac;
    private List<Prestasi> prestasiList;

    public PrestasiList(Activity ac, List<Prestasi> prestasiList){
        super(ac, R.layout.list_prestasi, prestasiList);
        this.ac = ac;
        this.prestasiList = prestasiList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = ac.getLayoutInflater();
        View listAP = inflater.inflate(R.layout.list_prestasi, null, true);

        TextView nmP = (TextView) listAP.findViewById(R.id.textVNamaJ);
        TextView jP = (TextView) listAP.findViewById(R.id.textVJuara);

        Prestasi prestasi = prestasiList.get(position);

        nmP.setText(prestasi.getNamaPres());
        jP.setText(prestasi.getJuaraPres());

        return  listAP;
    }
}
