package marcelinaar.uasdutapolinema;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InfoActivity extends AppCompatActivity {

    private Button bTM, bFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        bTM = (Button)findViewById(R.id.bTMDutpol);
        bFinal = (Button)findViewById(R.id.bFinalDutpol);

        bTM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(i);
            }
        });

        bFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(i);
            }
        });
    }
}
