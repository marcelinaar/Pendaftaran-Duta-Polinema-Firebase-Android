package marcelinaar.uasdutapolinema;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DutaHome extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseAuth firebaseAuth;
    TextView navTE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duta_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), DutaAdd.class);
                startActivity(i);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, MasukActivity.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        navTE = (TextView) findViewById(R.id.tViewE);
        navTE.setText("Email "+ user.getEmail());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profil) {
            Intent i = new Intent(this, DutaActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_info) {
            Intent i = new Intent(this, InfoActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_prestasi) {
            Intent i = new Intent(this, PrestasiActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_scan) {
            Intent i = new Intent(this, UploadActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_lo) {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, MasukActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
