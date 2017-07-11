package app.com.example.wagner.meupredi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by wagne on 07/07/2017.
 */

public class Ginasio extends AppCompatActivity {

    private ImageView chamadaNovoExercicio;
    private ListView listView;
    private Button adicionar;
    private ArrayAdapter<String> adaptador;
    private ArrayList<String> listaExercicios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ginasio);

        chamadaNovoExercicio = (ImageView) findViewById(R.id.image_nova_atividade);
        listView = (ListView) findViewById(R.id.lista_exercicios);
        String[] items = {"Caminhada"};

        listaExercicios = new ArrayList<>(Arrays.asList(items));
        adaptador = new ArrayAdapter<String>(this, R.layout.lista_item_exercicios, R.id.text_item_lista_exe, listaExercicios);
        listView.setAdapter(adaptador);

        chamadaNovoExercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Ginasio.this, NovoExercicio.class);
                startActivity(intent);
            }
        });
    }
}
