package marcelinaar.uasdutapolinema;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import marcelinaar.uasdutapolinema.Adapter.PrestasiList;
import marcelinaar.uasdutapolinema.Model.Prestasi;

public class PrestasiActivity extends AppCompatActivity {

    private EditText eNamaJuara;
    private Button bSimJuara;
    private Spinner spinnerJ;

    DatabaseReference databasePrestasi;

    ListView listViewJuara;

    List<Prestasi> prestasiList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prestasi);

        databasePrestasi = FirebaseDatabase.getInstance().getReference("prestasi");

        eNamaJuara = (EditText)findViewById(R.id.eNPres);
        bSimJuara = (Button)findViewById(R.id.bSimP);
        spinnerJ = (Spinner)findViewById(R.id.spinnerJuara);

        listViewJuara = (ListView)findViewById(R.id.listVPrestasi);

        prestasiList = new ArrayList<>();

        bSimJuara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPrestasi();
            }
        });

        listViewJuara.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Prestasi prestasi = prestasiList.get(i);

                showUpdateDialog(prestasi.getIdP(), prestasi.getNamaPres());
                return;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        databasePrestasi.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                prestasiList.clear();

                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    Prestasi prestasi = ds.getValue(Prestasi.class);
                    prestasiList.add(prestasi);
                }

                PrestasiList adapter = new PrestasiList(PrestasiActivity.this, prestasiList);
                listViewJuara.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void showUpdateDialog(final String idP, final String namaPres){
        AlertDialog.Builder dialogB = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogV = inflater.inflate(R.layout.update_prestasi, null);
        dialogB.setView(dialogV);

        final EditText eUpNama = (EditText)dialogV.findViewById(R.id.uNPres);
        final Button bUp = (Button)dialogV.findViewById(R.id.bUpdP);
        final Spinner spinnerUp = (Spinner)dialogV.findViewById(R.id.spinnerUJ);
        final Button bDel = (Button)dialogV.findViewById(R.id.bDelP);

        dialogB.setTitle("Update "+namaPres);

        final AlertDialog alertDialog = dialogB.create();
        alertDialog.show();

        bUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namaPres = eUpNama.getText().toString().trim();
                String juaraPres = spinnerUp.getSelectedItem().toString();

                if(TextUtils.isEmpty(namaPres)){
                    eUpNama.setError("Isi nama kejuaraan!");
                    return;
                }

                updatePrestasi(idP, namaPres, juaraPres);
                alertDialog.dismiss();
            }
        });

        bDel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                deletePrestasi(idP);
            }
        });
    }

    private void deletePrestasi(String idP) {
        DatabaseReference dPrestasi = FirebaseDatabase.getInstance().getReference("prestasi").child(idP);
        dPrestasi.removeValue();
        Toast.makeText(this, "Prestasi berhasil dihapus", Toast.LENGTH_LONG).show();
    }

    private boolean updatePrestasi(String idP, String namaPres, String juaraPres){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("prestasi").child(idP);

        Prestasi prestasi = new Prestasi(idP, namaPres, juaraPres);

        databaseReference.setValue(prestasi);

        Toast.makeText(this, "Nama Kejuaraan sukses update!", Toast.LENGTH_LONG).show();
        return true;
    }

    private void addPrestasi(){
        String namaPres = eNamaJuara.getText().toString().trim();
        String juaraPres = spinnerJ.getSelectedItem().toString();

        if(!TextUtils.isEmpty(namaPres)){
            String idP = databasePrestasi.push().getKey();
            Prestasi prestasi = new Prestasi(idP, namaPres, juaraPres);
            databasePrestasi.child(idP).setValue(prestasi);
            Toast.makeText(this, "Tambah prestasi...", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Harus isi nama kejuaraan!", Toast.LENGTH_LONG).show();
        }

    }
}
