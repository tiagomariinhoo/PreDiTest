package app.com.example.wagner.meupredi.TelasMenu;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import app.com.example.wagner.meupredi.BDMenuLogin.DatabaseHandler;
import app.com.example.wagner.meupredi.BDMenuLogin.Paciente;
import app.com.example.wagner.meupredi.R;

/**
 * Created by Allan on 13/06/2017.
 */

public class Peso extends AppCompatActivity {

    TextView peso, meta, novoPeso;
    Button atualizarPeso;
    Paciente paciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peso);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        paciente = (Paciente) getIntent().getExtras().get("Paciente");

        peso = (TextView) findViewById(R.id.text_pesoAtual_valor_peso);
        peso.setText(String.valueOf(paciente.get_peso()) + " kg");

        //TODO: criar calculo de meta
        //TODO: criar atributo de meta para guardar o peso que o paciente devera alcancar

        meta = (TextView) findViewById(R.id.text_meta_valor_peso);

        //pega novo peso digitado pelo usuario
        novoPeso = (TextView) findViewById(R.id.text_registrar_valor_peso);
        novoPeso.setRawInputType(Configuration.KEYBOARD_QWERTY);

        findViewById(R.id.tela_peso).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getCurrentFocus()!=null && getCurrentFocus() instanceof EditText){
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(novoPeso.getWindowToken(), 0);
                }
            }
        });

        atualizarPeso = (Button) findViewById(R.id.btn_atualizar_peso);

        atualizarPeso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //pega string do peso e verifica tamanho
                String pesoAtual = novoPeso.getText().toString();

                if(pesoAtual.length() == 0) {
                    Toast.makeText(getApplicationContext(),"Preencha o campo correspondente!",Toast.LENGTH_SHORT).show();
                } else {

                    //formata a string para transformar corretamente para double (substitui virgula por ponto e limita a uma casa decimal)
                    pesoAtual = pesoAtual.replace(',', '.');
                    Double pesoAtualizado = Double.parseDouble(pesoAtual);
                    String pesoFormatado = String.format(Locale.ENGLISH, "%.2f", pesoAtualizado);
                    Double pesoDoPaciente = Double.parseDouble(pesoFormatado);

                    if(pesoDoPaciente > 0) {
                        //atualiza valor na tela
                        peso.setText(String.valueOf(pesoDoPaciente) + " kg");

                        //atualiza peso no objeto
                        paciente.set_peso(pesoDoPaciente);

                        //recalcula imc
                        if(paciente.get_peso() > 0 && paciente.get_altura() > 0) {

                            double imc = (paciente.get_peso()/(((paciente.get_altura()/100)*((paciente.get_altura())/100))));
                            String imcFormatado = String.format(Locale.ENGLISH, "%.2f", imc);
                            imc = Double.parseDouble(imcFormatado);
                            paciente.set_imc(imc);
                        } else {
                            paciente.set_imc(0);
                        }

                        //atualiza o peso e o imc do paciente no banco
                        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                        db.atualizarPeso(paciente);
                        db.atualizarPaciente(paciente);

                        Toast.makeText(getApplicationContext(),"Peso atualizado com sucesso!",Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(),"Peso inválido!",Toast.LENGTH_SHORT).show();
                    }

                    novoPeso.setText("");

                    try {
                        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    } catch(NullPointerException e) {
                        //caso o teclado ja esteja escondido
                    }
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

}
