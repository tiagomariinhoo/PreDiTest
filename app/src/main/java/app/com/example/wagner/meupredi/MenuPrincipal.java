package app.com.example.wagner.meupredi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MenuPrincipal extends AppCompatActivity {

    public static final String PREFS_NAME = "Preferences";
    private DrawerLayout menuPrincipalTela;
    private ActionBarDrawerToggle mToggle;
    private Paciente paciente;
    private TextView nomeUsuario;
    private TextView idadeUsuario;
    private TextView alturaUsuario;
    private TextView pesoUsuario;
    private TextView circUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        menuPrincipalTela = (DrawerLayout) findViewById(R.id.menuPrincipaLayout);
        mToggle = new ActionBarDrawerToggle(this, menuPrincipalTela, R.string.open, R.string.close);

        menuPrincipalTela.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nomeUsuario = (TextView) findViewById(R.id.text_nome_usuario_menu_principal);
        idadeUsuario = (TextView) findViewById(R.id.text_idade_usuario_menu_principal);
        alturaUsuario = (TextView) findViewById(R.id.text_altura_usuario_menu_principal);
        pesoUsuario = (TextView) findViewById(R.id.text_peso_usuario_menu_principal);
        circUsuario = (TextView) findViewById(R.id.text_circ_usuario_menu_principal);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();

        paciente = (Paciente) getIntent().getExtras().get("Paciente");

        nomeUsuario.setText(paciente.get_nome());
        /*idadeUsuario.setText(paciente.get_idade());
        alturaUsuario.setText((int) paciente.get_altura());
        pesoUsuario.setText((int) paciente.get_peso());
        circUsuario.setText((int) paciente.get_circunferencia());*/

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
