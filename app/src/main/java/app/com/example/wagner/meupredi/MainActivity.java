package app.com.example.wagner.meupredi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(this);

        db.deleteAllPacientes();

        // INSERIR PACIENTES
        Log.d("Insert: ", "Inserting...");
        db.addPaciente(new Paciente(0, "Amanda", "1234", "amanda@gmail.com", 13, 11, 9,1));

        Log.d("Reading: ", "Reading all contacts..");


        Intent intent1 = new Intent(this, TelaLogin.class);
        startActivity(intent1);




    }
}
