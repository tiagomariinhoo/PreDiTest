package app.com.example.wagner.meupredi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

import app.com.example.wagner.meupredi.BDMenuLogin.DatabaseHandler;

/**
 * Created by LeandroDias1 on 28/09/2017.
 */

public class TelaExameLipidograma extends AppCompatActivity {

    private EditText dataLipidograma, localLipidograma;
    private EditText hdl, ldl, colesterolTotal, triglicerides;
    private Button atualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exame_lipidograma);

        dataLipidograma = (EditText) findViewById(R.id.edit_data_exame_hemograma);
        localLipidograma = (EditText) findViewById(R.id.edit_local_exame_hemograma);

        hdl = (EditText) findViewById(R.id.edit_hemoglobina_exame_hemograma);
        ldl = (EditText) findViewById(R.id.edit_hematocrito_exame_hemograma);
        colesterolTotal = (EditText) findViewById(R.id.edit_vgm_exame_hemograma);
        triglicerides = (EditText) findViewById(R.id.edit_chcm_exame_hemograma);

        atualizar = (Button) findViewById(R.id.btn_atualizar_exame_lipidograma);

        findViewById(R.id.tela_exame_lipidograma).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getCurrentFocus()!=null && getCurrentFocus() instanceof EditText){
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(dataLipidograma.getWindowToken(), 0);
                    imm.hideSoftInputFromWindow(localLipidograma.getWindowToken(), 0);
                    imm.hideSoftInputFromWindow(hdl.getWindowToken(), 0);
                    imm.hideSoftInputFromWindow(ldl.getWindowToken(), 0);
                    imm.hideSoftInputFromWindow(colesterolTotal.getWindowToken(), 0);
                    imm.hideSoftInputFromWindow(triglicerides.getWindowToken(), 0);
                }
            }
        });

        atualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(dataLipidograma.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Insira uma data!", Toast.LENGTH_SHORT).show();
                } else if(dataLipidograma.length() != 8) {
                    Toast.makeText(getApplicationContext(), "Data em formato incorreto! Digite no formato ddmmaaaa", Toast.LENGTH_SHORT).show();
                } else if(hdl.length() == 0) {

                } else if(ldl.length() == 0) {

                }
            }

        });

        }

    }
