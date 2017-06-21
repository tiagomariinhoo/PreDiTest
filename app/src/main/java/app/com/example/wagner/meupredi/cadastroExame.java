package app.com.example.wagner.meupredi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import app.com.example.wagner.meupredi.BDMenuLogin.Paciente;

/**
 * Created by wagne on 15/06/2017.
 */

public class cadastroExame extends AppCompatActivity {

    Paciente paciente;

    EditText totg;
    EditText glicosejejum;
    EditText colesterolTotal;
    EditText colesterolHdl;
    EditText glicose75g;
    EditText acidoUrico;
    EditText ureia;
    EditText creatinina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // DatabaseHandler db = new DatabaseHandler(this.getApplicationContext());
        //paciente = ((MenuPrincipal)).pegarPacienteMenu();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_exame);

        totg = (EditText) findViewById(R.id.editTotg);
        glicose75g = (EditText) findViewById(R.id.editGlicose75g);
        glicosejejum = (EditText) findViewById(R.id.editGlicoseJejum);
        colesterolTotal = (EditText) findViewById(R.id.editColesterolTotal);
        colesterolHdl = (EditText) findViewById(R.id.editColesterolHdl);
        acidoUrico = (EditText) findViewById(R.id.editAcidoUrico);
        ureia = (EditText) findViewById(R.id.editUreia);
        creatinina = (EditText) findViewById(R.id.editCretinina);

        String TextTotg = totg.getText().toString();
        String TextGlicoseJejum = glicosejejum.getText().toString();
        String TextColesterolTotal = colesterolTotal.getText().toString();
        String TextGlic75g = glicose75g.getText().toString();
        String TextColesterolHdl = colesterolHdl.getText().toString();
        String TextAcidoUrico = acidoUrico.getText().toString();
        String TextUreia = ureia.getText().toString();
        String TextCreatinina = creatinina.getText().toString();

       /* paciente.set_glicosejejum(Double.parseDouble(TextGlicoseJejum));
        paciente.set_hba1c(Double.parseDouble(TextHba1c));
        paciente.set_glicose75g(Double.parseDouble(TextGlic75g));
        paciente.set_lipidograma(Double.parseDouble(TextLipidograma));
        paciente.set_hemograma(Double.parseDouble(TextHemograma));
        paciente.set_tireoide(Double.parseDouble(TextTireoide));
        */

    }
}
