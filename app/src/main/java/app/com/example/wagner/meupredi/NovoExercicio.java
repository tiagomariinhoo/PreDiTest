package app.com.example.wagner.meupredi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by wagne on 09/07/2017.
 */

public class NovoExercicio extends AppCompatActivity {

    private ListView listView;
    private Button adicionar;
    private ArrayList<String> listaExercicios;
    private ArrayAdapter<String> adaptador;
    private CheckBox checkBoxAcademia, checkBoxCorrida, checkBoxCiclismo, checkBoxFutebol, checkBoxNatacao, checkBoxTenis, checkBoxOutra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_exercicio);

        checkBoxAcademia = (CheckBox) findViewById(R.id.checkBox_academia);
        checkBoxCiclismo = (CheckBox) findViewById(R.id.checkBox_ciclismo);
        checkBoxCorrida = (CheckBox) findViewById(R.id.checkBox_corrida);
        checkBoxFutebol = (CheckBox) findViewById(R.id.checkBox_futebol);
        checkBoxTenis = (CheckBox) findViewById(R.id.checkBox_tenis);
        checkBoxNatacao = (CheckBox) findViewById(R.id.checkBox_natacao);
        checkBoxOutra = (CheckBox) findViewById(R.id.checkBox_outra);

        Log.d("TEMA 4", " TEMA 4");
        listaExercicios = getIntent().getStringArrayListExtra("listaExercicios2");
        if(listaExercicios == null){
            listaExercicios = new ArrayList<>();
        }

        adicionar = (Button) findViewById(R.id.btn_salvar_novo_exe);
        adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBoxAcademia.isChecked()){
                    adicionarExercicio("Academia");
                }
                else if(checkBoxCiclismo.isChecked()){
                    adicionarExercicio("Ciclismo");
                }
                else if(checkBoxCorrida.isChecked()){
                    adicionarExercicio("Outra");;
                }
                else if(checkBoxFutebol.isChecked()){
                    adicionarExercicio("Futebol");
                }
                else if(checkBoxTenis.isChecked()){
                    adicionarExercicio("Tenis");
                }
                else if(checkBoxNatacao.isChecked()){
                    adicionarExercicio("Natação");
                }
                else if(checkBoxCiclismo.isChecked()){
                    adicionarExercicio("Outra");
                }

                Intent intent = new Intent(NovoExercicio.this, Ginasio.class);
                intent.putStringArrayListExtra("listaExercicios", listaExercicios);
                startActivity(intent);

            }
        });

    }

    public void adicionarExercicio(String novoExercicio){

        listaExercicios.add(novoExercicio);

        /*setContentView(R.layout.activity_ginasio);
        listView = (ListView) findViewById(R.id.lista_exercicios);

        listaExercicios = new ArrayList<>();
        adaptador = new ArrayAdapter<String>(this, R.layout.lista_item_exercicios, R.id.text_item_lista_exe, listaExercicios);


        listView.setAdapter(adaptador);

        listaExercicios.add(novoExercicio);*/
    }

}
