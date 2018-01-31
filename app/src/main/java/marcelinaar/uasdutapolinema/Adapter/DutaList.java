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

import marcelinaar.uasdutapolinema.Model.Duta;
import marcelinaar.uasdutapolinema.R;

/**
 * Created by User on 28/01/2018.
 */

public class DutaList extends ArrayAdapter<Duta>{

    private Activity ac;
    private List<Duta> dutaList;

    public DutaList(Activity ac, List<Duta> dutaList){
        super(ac, R.layout.list_duta, dutaList);
        this.ac = ac;
        this.dutaList = dutaList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = ac.getLayoutInflater();
        View listAcDuta = inflater.inflate(R.layout.list_duta, null, true);

        TextView nimS = (TextView) listAcDuta.findViewById(R.id.sNim);
        TextView namaS = (TextView) listAcDuta.findViewById(R.id.sNama);
        TextView kelasS = (TextView) listAcDuta.findViewById(R.id.sKelas);
        TextView ipkS = (TextView) listAcDuta.findViewById(R.id.sIpk);
        TextView nohpS = (TextView) listAcDuta.findViewById(R.id.sNo);
        TextView ketS = (TextView) listAcDuta.findViewById(R.id.sKet);
        TextView jkS = (TextView) listAcDuta.findViewById(R.id.sJK);

        Duta duta = dutaList.get(position);

        nimS.setText(duta.getNim());
        namaS.setText(duta.getNama());
        kelasS.setText(duta.getKelas());
        ipkS.setText(duta.getIpk());
        nohpS.setText(duta.getNohp());
        ketS.setText(duta.getKet());
        jkS.setText(duta.getJkS());

        return  listAcDuta;
    }
}
