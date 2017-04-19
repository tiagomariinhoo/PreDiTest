package app.com.example.wagner.meupredi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);

        DatabaseHandler db = new DatabaseHandler(this);

        //db.deleteAllPacientes();

        final String PREFS_NAME = "Preferences";
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.commit();

        // INSERIR PACIENTES
        Log.d("Insert: ", "Inserting...");
        //db.addPaciente(new Paciente(0, "Amanda", "1234", "a", 13, 11, 9,1));
        //db.addPaciente(new Paciente(0, "Laura", 13, 11, 9));
        //db.addPaciente(new Paciente(0, "Shelly", 15, 12, 3));

        Log.d("Reading: ", "Reading all contacts..");

        Intent intent = new Intent(MainActivity.this, TelaLogin.class);
        startActivity(intent);
    }
}
