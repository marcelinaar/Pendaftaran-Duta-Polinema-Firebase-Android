package marcelinaar.uasdutapolinema;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DaftarActivity extends AppCompatActivity implements View.OnClickListener{

    private Button dB;
    private EditText dE, dP ;
    private TextView dT;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(this, DutaHome.class));
        }

        progressDialog = new ProgressDialog(this);

        dB = (Button)findViewById(R.id.dButton);
        dE = (EditText)findViewById(R.id.dEmail);
        dP = (EditText)findViewById(R.id.dPassword);
        dT = (TextView)findViewById(R.id.dLinkLogin);

        dB.setOnClickListener(this);
        dT.setOnClickListener(this);
    }

    private void daftarDuta(){

        String email = dE.getText().toString().trim();
        String password = dP.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Harap isi Email!",Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Harap isi Password!",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Proses daftar user...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(), DutaHome.class));
                        }else {
                            Toast.makeText(DaftarActivity.this, "Daftar email gagal! Coba lagi!", Toast
                                    .LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }

    @Override
    public void onClick(View view) {
        if(view == dB){
            daftarDuta();
        }

        if (view == dT){
            startActivity(new Intent(this, MasukActivity.class));
        }
    }
}
