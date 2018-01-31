package marcelinaar.uasdutapolinema;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import marcelinaar.uasdutapolinema.Model.Duta;

public class DutaAdd extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth firebaseAuth;

    private DatabaseReference databaseRefence;

    private EditText tNm, tNim, tKls, tIp, tNP, tKet;
    private Button tBtn;
    private Spinner spinnerJK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duta_add);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, MasukActivity.class));
        }

        databaseRefence = FirebaseDatabase.getInstance().getReference("dutas");

        tNm = (EditText)findViewById(R.id.tNama);
        tNim = (EditText)findViewById(R.id.tNim);
        tKls = (EditText)findViewById(R.id.tKelas);
        tIp = (EditText)findViewById(R.id.tIpk);
        tNP = (EditText)findViewById(R.id.tNohp);
        tKet = (EditText)findViewById(R.id.tKet);
        tBtn = (Button)findViewById(R.id.tButton);
        spinnerJK = (Spinner)findViewById(R.id.spinnerK);

        tBtn.setOnClickListener(this);
    }

    private void addDuta(){
        String nama = tNm.getText().toString().trim();
        String nim = tNim.getText().toString().trim();
        String kelas = tKls.getText().toString().trim();
        String ipk = tIp.getText().toString().trim();
        String nohp = tNP.getText().toString().trim();
        String ket = tKet.getText().toString().trim();
        String jk = spinnerJK.getSelectedItem().toString();

        if(!TextUtils.isEmpty(nim)) {

            String idduta = databaseRefence.push().getKey();

            Duta duta = new Duta(idduta, nim, nama, kelas, ipk, nohp, ket, jk);

            databaseRefence.child(idduta).setValue(duta);

            Toast.makeText(this, "Simpan data daftar..",
                    Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Lengkapi NIM!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View view) {
        if(view == tBtn){
            addDuta();
        }
    }
}
