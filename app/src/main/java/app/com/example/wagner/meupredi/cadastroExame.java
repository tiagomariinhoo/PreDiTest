package app.com.example.wagner.meupredi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import app.com.example.wagner.meupredi.BDMenuLogin.DatabaseHandler;
import app.com.example.wagner.meupredi.BDMenuLogin.ExameClass;
import app.com.example.wagner.meupredi.BDMenuLogin.Paciente;

/**
 * Created by wagne on 15/06/2017.
 */

public class cadastroExame extends AppCompatActivity {

    EditText totg;
    EditText glicosejejum;
    EditText colesterolTotal;
    EditText colesterolHdl;
    EditText glicose75g;
    EditText acidoUrico;
    EditText ureia;
    EditText creatinina;
    Button salvar;

    ExameClass exame;
    String TextGlicoseJejum;
    String TextGlic75g;

    Paciente paciente;

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
        salvar = (Button) findViewById(R.id.salvar_exame);


        //String TextTotg = totg.getText().toString();
        //String TextColesterolTotal = colesterolTotal.getText().toString();
        //String TextColesterolHdl = colesterolHdl.getText().toString();
        //String TextAcidoUrico = acidoUrico.getText().toString();
        //String TextUreia = ureia.getText().toString();
        //String TextCreatinina = creatinina.getText().toString();

        paciente = (Paciente) getIntent().getExtras().get("Paciente");



        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                TextGlic75g = glicose75g.getText().toString();
                TextGlicoseJejum = glicosejejum.getText().toString();

                if(TextGlicoseJejum.length() != 0 && TextGlic75g.length() != 0){

                    exame = new ExameClass();
                    exame.setGlicose75g(Double.parseDouble(TextGlic75g));
                    exame.setGlicoseJejum(Double.parseDouble(TextGlicoseJejum));
                    exame.setIdPac(paciente.get_id());
                    String msg = db.addExame(exame);

                    if(msg.equals("Registro dos exames inserido com sucesso!")){
                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                        paciente.set_glicose75g(exame.getGlicose75g());
                        paciente.set_glicosejejum(exame.getGlicoseJejum());

                        Intent intent = new Intent(cadastroExame.this, MenuPrincipal.class);
                        intent.putExtra("Paciente", paciente);
                        startActivity(intent);

                    } else {

                        Toast.makeText(getApplicationContext(), "Por favor, digite os dados corretamente!", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Por favor, digite os dados corretamenteaaaaaaaaa!", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

}
