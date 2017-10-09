package app.com.example.wagner.meupredi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import app.com.example.wagner.meupredi.BDMenuLogin.DatabaseHandler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);

        DatabaseHandler db = new DatabaseHandler(this);

        final String PREFS_NAME = "Preferences";
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();

        //DEBUG: resetar banco e sharedpreferences
        boolean debug = false;

        if(debug) {
            db.deleteAllPacientes();
            editor.clear();
            editor.commit();
        }

        //DEBUG: inserir pacientes para testar banco
        boolean inserir = false;

        if(inserir) {
            //TODO: consertar construtor abaixo
            Log.d("Insert: ", "Inserting...");
            //db.addPaciente(new Paciente(0, "Amanda", "1234", "a", 13, 11, 9,1));
            //db.addPaciente(new Paciente(0, "Laura", 13, 11, 9));
            //db.addPaciente(new Paciente(0, "Shelly", 15, 12, 3));
        }

        Intent intent = new Intent(MainActivity.this, TelaLogin.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        startActivity(intent);
    }
}
