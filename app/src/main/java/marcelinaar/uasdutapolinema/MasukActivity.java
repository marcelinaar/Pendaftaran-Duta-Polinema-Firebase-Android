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

public class MasukActivity extends AppCompatActivity implements View.OnClickListener{

    private Button lB;
    private EditText lE, lP;
    private TextView lT;

    private FirebaseAuth firebaseAuth;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masuk);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(), DutaHome.class));
        }

        lB = (Button)findViewById(R.id.lButton);
        lE = (EditText)findViewById(R.id.lEmail);
        lP = (EditText)findViewById(R.id.lPassword);
        lT = (TextView)findViewById(R.id.lLinkDaftar);

        progressDialog = new ProgressDialog(this);

        lB.setOnClickListener(this);
        lT.setOnClickListener(this);
    }

    private void dutaLogin(){

        String email = lE.getText().toString().trim();
        String password = lP.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Harap isi Email!",Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Harap isi Password!",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Proses login user...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            finish();
                            startActivity(new Intent(getApplicationContext(), DutaHome.class));
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        if(view == lB){
            dutaLogin();
        }
        if(view == lT){
            finish();;
            startActivity(new Intent(this, DaftarActivity.class));
        }
    }
}
