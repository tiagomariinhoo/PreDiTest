package app.com.example.wagner.meupredi.BDMenuLogin;

import android.content.Context;
import android.content.Intent;
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

import app.com.example.wagner.meupredi.R;

/**
 * Created by Pichau on 08/04/2017.
 */

public class PosLogin extends AppCompatActivity {

    public static final String PREFS_NAME = "Preferences";
    private TextView nomeUsuario;
    private EditText idade;
    private EditText altura;
    private EditText peso;
    private EditText circunferecia;
    private Button pular;
    private Button concluir;
    private Paciente paciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poslogin);

        nomeUsuario = (TextView) findViewById(R.id.text_nome_usuario_poslogin);
        idade = (EditText) findViewById(R.id.edit_idade_poslogin);
        idade.setRawInputType(Configuration.KEYBOARD_QWERTY);
        altura = (EditText) findViewById(R.id.edit_altura_poslogin);
        altura.setRawInputType(Configuration.KEYBOARD_QWERTY);
        peso = (EditText) findViewById(R.id.edit_peso_poslogin);
        peso.setRawInputType(Configuration.KEYBOARD_QWERTY);
        circunferecia = (EditText) findViewById(R.id.edit_circunferencia_poslogin);
        circunferecia.setRawInputType(Configuration.KEYBOARD_QWERTY);
        pular = (Button) findViewById(R.id.btn_pular_poslogin);
        concluir = (Button) findViewById(R.id.btn_concluir_poslogin);

        paciente = (Paciente) getIntent().getExtras().get("Paciente");

        nomeUsuario.setText(paciente.get_nome());

        //se o usuario ja fez o cadastro dos dados, pula esta tela
        if(paciente.get_idade() > 0) {

            //DEBUG: imprime dados do usuario pegos do banco
            Log.d("Se ja existe: ", "poslogin");
            Log.d("Nome : ", paciente.get_nome());
            Log.d("Senha : ", paciente.get_senha());
            Log.d("Email: ", paciente.get_email());
            Log.d("Idade : ", String.valueOf(paciente.get_idade()));
            Log.d("Circunferencia : ", String.valueOf(paciente.get_circunferencia()));
            Log.d("Peso : ", String.valueOf(paciente.get_peso()));
            Log.d("Peso anterior: ", String.valueOf(paciente.get_pesoAnterior()));
            Log.d("Altura : ", String.valueOf(paciente.get_altura()));
            Log.d("IMC : ", String.valueOf(paciente.get_imc()));
            Log.d("HBA1C : ", String.valueOf(paciente.get_hba1c()));
            Log.d("GlicoseJejum : ", String.valueOf(paciente.get_glicosejejum()));
            Log.d("Glicose75g : ", String.valueOf(paciente.get_glicose75g()));

            Intent intent = new Intent(PosLogin.this, MenuPrincipal.class);
            intent.putExtra("Paciente", paciente);
            startActivity(intent);
        }

        findViewById(R.id.tela_pos_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getCurrentFocus()!=null && getCurrentFocus() instanceof EditText){
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(idade.getWindowToken(), 0);
                    imm.hideSoftInputFromWindow(peso.getWindowToken(), 0);
                    imm.hideSoftInputFromWindow(circunferecia.getWindowToken(), 0);
                }
            }
        });

        concluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boolean flag = false;

                String idadeCadastro = idade.getText().toString();
                String alturaCadastro = altura.getText().toString();
                String pesoCadastro = peso.getText().toString();
                String circunferenciaCadastro = circunferecia.getText().toString();

                //se o usuario nao preencheu algum dado, coloca como -1
                //TODO: criar tela para preencher os dados ausentes
                if(idadeCadastro.length()==0){
                    paciente.set_idade(-1);
                    flag = true;
                } else {
                    paciente.set_idade(Integer.parseInt(idadeCadastro));
                } if (alturaCadastro.length()==0){
                    paciente.set_altura(-1);
                    flag = true;
                } else {
                    paciente.set_altura(Double.parseDouble(alturaCadastro));
                } if (pesoCadastro.length()==0){
                    paciente.set_peso(-1);
                    flag = true;
                } else {
                    paciente.set_peso(Double.parseDouble(pesoCadastro));
                } if (circunferenciaCadastro.length()==0){
                    paciente.set_circunferencia(-1);
                    flag = true;
                } else {
                    paciente.set_circunferencia(Double.parseDouble(circunferenciaCadastro));
                }

                //calculo de IMC
                if(paciente.get_peso() != -1 && paciente.get_altura() != -1) {

                    double imc = (paciente.get_peso()/(((paciente.get_altura()/100)*((paciente.get_altura())/100))));
                    String imcFormatado = String.format(Locale.ENGLISH, "%.2f", imc);
                    imc = Double.parseDouble(imcFormatado);
                    paciente.set_imc(imc);
                } else {
                    paciente.set_imc(0);
                }

                //se o usuario nao preencheu algum dos dados, avisa que ele pode preencher depois
                if(flag) {
                    Toast.makeText(getApplicationContext(),"Você pode completar" +
                                                           " seus dados na tela de" +
                                                           " configurações quando quiser.",Toast.LENGTH_LONG).show();
                }

                DatabaseHandler db = new DatabaseHandler(getApplicationContext());

                //pega peso cadastrado pelo paciente na tela e insere em sua respectiva tabela no banco
                if(pesoCadastro.length() != 0){
                    db.atualizarPeso(paciente);
                }

                //atualiza dados do usuario no banco
                if(db.atualizarPaciente(paciente)){
                    Toast.makeText(getApplicationContext(),"Sucesso ao editar!",Toast.LENGTH_LONG).show();
                    Log.d("Sucesso"," Sucesso");
                } else {
                    Toast.makeText(getApplicationContext(),"Erro ao editar!",Toast.LENGTH_LONG).show();
                    Log.d("Erro"," Erro");
                }

                //DEBUG: imprime os dados do paciente para verificar se estao corretos
                Log.d("Sincronizado: ", "poslogin");
                Log.d("Nome : ", paciente.get_nome());
                Log.d("Senha : ", paciente.get_senha());
                Log.d("Email: ", paciente.get_email());
                Log.d("Idade : ", String.valueOf(paciente.get_idade()));
                Log.d("Circunferencia : ", String.valueOf(paciente.get_circunferencia()));
                Log.d("Peso : ", String.valueOf(paciente.get_peso()));
                Log.d("Peso anterior: ", String.valueOf(paciente.get_pesoAnterior()));
                Log.d("Altura : ", String.valueOf(paciente.get_altura()));
                Log.d("IMC : ", String.valueOf(paciente.get_imc()));
                Log.d("HBA1C : ", String.valueOf(paciente.get_hba1c()));
                Log.d("GlicoseJejum : ", String.valueOf(paciente.get_glicosejejum()));
                Log.d("Glicose75g : ", String.valueOf(paciente.get_glicose75g()));

                Intent intent = new Intent(PosLogin.this, MenuPrincipal.class);
                intent.putExtra("Paciente", paciente);
                startActivity(intent);
            }

        });

        pular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pularCadastro = new Intent(PosLogin.this, MenuPrincipal.class);
                startActivity(pularCadastro);
            }
        });

    }
}