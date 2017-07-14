package app.com.example.wagner.meupredi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import app.com.example.wagner.meupredi.BDMenuLogin.DatabaseHandler;
import app.com.example.wagner.meupredi.BDMenuLogin.Paciente;

/**
 * Created by wagne on 09/07/2017.
 */

public class NovoExercicio extends AppCompatActivity {

    private Button adicionar;
    private ArrayList<String> listaExercicios;
    private CheckBox checkBoxAcademia, checkBoxCorrida, checkBoxCiclismo, checkBoxFutebol, checkBoxNatacao, checkBoxTenis, checkBoxOutra;
    private EditText metaDiaria;
    private Paciente paciente;
    String metaDia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_exercicio);

        DatabaseHandler db = new DatabaseHandler(getApplicationContext());

        checkBoxAcademia = (CheckBox) findViewById(R.id.checkBox_academia);
        checkBoxCiclismo = (CheckBox) findViewById(R.id.checkBox_ciclismo);
        checkBoxCorrida = (CheckBox) findViewById(R.id.checkBox_corrida);
        checkBoxFutebol = (CheckBox) findViewById(R.id.checkBox_futebol);
        checkBoxTenis = (CheckBox) findViewById(R.id.checkBox_tenis);
        checkBoxNatacao = (CheckBox) findViewById(R.id.checkBox_natacao);
        checkBoxOutra = (CheckBox) findViewById(R.id.checkBox_outra);
        metaDiaria = (EditText) findViewById(R.id.edit_novo_exe_meta_diaria);

        Log.d("TEMA 4", " TEMA 4");
        listaExercicios = getIntent().getStringArrayListExtra("listaExercicios2");
        if(listaExercicios == null){
            listaExercicios = new ArrayList<>();
        }

        paciente = (Paciente) getIntent().getExtras().get("Paciente");

        adicionar = (Button) findViewById(R.id.btn_salvar_novo_exe);

        adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("Adicionar", "entrei");
                metaDia = metaDiaria.getText().toString();

                if(metaDia.length()>0) {
                    if (checkBoxAcademia.isChecked()) {
                        adicionarExercicio("Academia");
                    } else if (checkBoxCiclismo.isChecked()) {
                        adicionarExercicio("Ciclismo");
                    } else if (checkBoxCorrida.isChecked()) {
                        adicionarExercicio("Outra");
                    } else if (checkBoxFutebol.isChecked()) {
                        adicionarExercicio("Futebol");
                    } else if (checkBoxTenis.isChecked()) {
                        adicionarExercicio("Tenis");
                    } else if (checkBoxNatacao.isChecked()) {
                        adicionarExercicio("Natação");
                    } else if (checkBoxCiclismo.isChecked()) {
                        adicionarExercicio("Outra");
                    }

                    Intent intent = new Intent(NovoExercicio.this, Ginasio.class);
                    intent.putStringArrayListExtra("listaExercicios", listaExercicios);
                    intent.putExtra("Paciente", paciente);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(),"Por favor, digite um valor válido na meta do dia!",Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    public void adicionarExercicio(String novoExercicio){

        DatabaseHandler db = new DatabaseHandler(getApplicationContext());

        listaExercicios.add(novoExercicio);
        String msg = db.addExercicio(Integer.parseInt(metaDia), novoExercicio, paciente.get_id());
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();

        /*setContentView(R.layout.activity_ginasio);
        listView = (ListView) findViewById(R.id.lista_exercicios);

        listaExercicios = new ArrayList<>();
        adaptador = new ArrayAdapter<String>(this, R.layout.lista_item_exercicios, R.id.text_item_lista_exe, listaExercicios);


        listView.setAdapter(adaptador);

        listaExercicios.add(novoExercicio);*/
    }

}
