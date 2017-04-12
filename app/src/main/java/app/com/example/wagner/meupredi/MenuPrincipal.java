package app.com.example.wagner.meupredi;

import android.content.SharedPreferences;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import static app.com.example.wagner.meupredi.TelaLogin.PREFS_NAME;

public class MenuPrincipal extends AppCompatActivity {

    private DrawerLayout menuPrincipalTela;
    private ActionBarDrawerToggle mToggle;
    private String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        menuPrincipalTela = (DrawerLayout) findViewById(R.id.menuPrincipaLayout);
        mToggle = new ActionBarDrawerToggle(this, menuPrincipalTela, R.string.open, R.string.close);

        menuPrincipalTela.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();

        usuario = settings.getString("PrefUsuario", "");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
