package app.com.example.wagner.meupredi;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MenuPrincipal extends AppCompatActivity {

    private DrawerLayout menuPrincipalTela;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        menuPrincipalTela = (DrawerLayout) findViewById(R.id.menuPrincipaLayout);
        mToggle = new ActionBarDrawerToggle(this, menuPrincipalTela, R.string.open, R.string.close);

        menuPrincipalTela.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
