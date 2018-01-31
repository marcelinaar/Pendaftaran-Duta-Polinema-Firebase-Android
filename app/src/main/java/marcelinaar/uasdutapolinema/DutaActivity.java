package marcelinaar.uasdutapolinema;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import marcelinaar.uasdutapolinema.Model.Duta;

public class DutaActivity extends AppCompatActivity {

    private TextView nmS, nimS, klsS, ipkS, nohpS, ketS, jkS;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duta);

        databaseReference = FirebaseDatabase.getInstance().getReference("dutas");

        nmS = (TextView)findViewById(R.id.sNama);
        nimS = (TextView)findViewById(R.id.sNim);
        klsS = (TextView)findViewById(R.id.sKelas);
        ipkS = (TextView)findViewById(R.id.sIpk);
        nohpS = (TextView)findViewById(R.id.sNo);
        ketS = (TextView)findViewById(R.id.sKet);
        jkS = (TextView)findViewById(R.id.sJK);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
            if(dataSnapshot.exists()) {
//                String nama = (String) dataSnapshot.child("nam").getValue();
//                String nim = (String) dataSnapshot.child("nim").getValue();
//                String kelas = (String) dataSnapshot.child("kelas").getValue();
//                String ipk = (String) dataSnapshot.child("ipk").getValue();
//                String nohp = (String) dataSnapshot.child("nohp").getValue();
//                String ket = (String) dataSnapshot.child("ket").getValue();
//                String jk = (String) dataSnapshot.child("jk").getValue();
                for (DataSnapshot dutDS : dataSnapshot.getChildren()) {
                    Duta duta = dutDS.getValue(Duta.class);

                    nmS.setText(duta.getNama());
                    nimS.setText(duta.getNim());
                    klsS.setText(duta.getKelas());
                    ipkS.setText(duta.getIpk());
                    nohpS.setText(duta.getNohp());
                    ketS.setText(duta.getKet());
                    jkS.setText(duta.getJkS());
                }
            }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
