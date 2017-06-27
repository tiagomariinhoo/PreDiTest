package app.com.example.wagner.meupredi;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.com.example.wagner.meupredi.BDMenuLogin.Paciente;

public class CriarConta extends AppCompatActivity {

    private CheckBox boxSenha;
    private EditText nome;
    private EditText email;
    private Spinner sexo;
    private EditText senha;
    private EditText conSenha;
    private ConstraintLayout tela;
    private Button criarConta;
    private TextView cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_conta);

        boxSenha = (CheckBox) findViewById(R.id.checkedConSenha);
        nome = (EditText) findViewById(R.id.edit_nome_completo);
        email = (EditText) findViewById(R.id.edit_endereco_email);
        senha = (EditText) findViewById(R.id.edit_senha_cadastro);
        conSenha = (EditText) findViewById(R.id.edit_novamente_senha);

        criarConta = (Button) findViewById(R.id.btn_criar_conta);
        cancelar = (TextView) findViewById(R.id.btn_cancelar);
        tela = (ConstraintLayout) findViewById(R.id.tela_criar_conta);
        sexo = (Spinner) findViewById(R.id.spinner_sexo_postlogin);

        findViewById(R.id.tela_criar_conta).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getCurrentFocus()!=null && getCurrentFocus() instanceof EditText){
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(nome.getWindowToken(), 0);
                    imm.hideSoftInputFromWindow(email.getWindowToken(), 0);
                    imm.hideSoftInputFromWindow(senha.getWindowToken(), 0);
                    imm.hideSoftInputFromWindow(conSenha.getWindowToken(), 0);
                }
            }
        });

        //lista de opcoes de sexo
        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("Masc.");
        spinnerArray.add("Fem.");

        //configura o spinner do sexo
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sexo.setAdapter(adapter);


        //final String senhaCadastro = senha.getText().toString();
        //final String conSenhaCadastro = conSenha.getText().toString();

        boxSenha.setChecked(false);

        conSenha.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String senhaCadastro = senha.getText().toString();
                String conSenhaCadastro = conSenha.getText().toString();

                if(senhaCadastro.length()==0){
                    Toast.makeText(getApplicationContext(),"Insira uma senha válida!", Toast.LENGTH_LONG ).show();
                    boxSenha.setChecked(false);
                    return false;
                }
                if(senhaCadastro.equals(conSenhaCadastro)){
                    boxSenha.setChecked(true);
                    return true;
                } else {
                    Toast.makeText(getApplicationContext(),"Insira senhas iguais!", Toast.LENGTH_LONG).show();
                    boxSenha.setChecked(false);
                    return false;
                }
            }


        });


        criarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*TODO: verificar email ao cadastrar (enviar email de confirmacao)*/

                final String nomeCompleto = nome.getText().toString();
                final String emailCadastro = email.getText().toString();
                final String senhaCadastro = senha.getText().toString();
                final String conSenhaCadastro = conSenha.getText().toString();

                //verificando se email ja foi cadastrado
                DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                Paciente tempPaciente = new Paciente();

                tempPaciente = db.verificarEmail(emailCadastro);

                if(tempPaciente.get_id() == -1) {
                    if(nomeCompleto.length() == 0) {
                        Toast.makeText(getApplicationContext(), "Insira um nome válido!", Toast.LENGTH_LONG).show();
                    } else if(emailCadastro.length() == 0) {
                        Toast.makeText(getApplicationContext(), "Insira um email válido!", Toast.LENGTH_LONG).show();
                    } else if (senhaCadastro.length() == 0) {
                        Toast.makeText(getApplicationContext(), "Insira uma senha válida!", Toast.LENGTH_LONG).show();
                    } else if (senhaCadastro.equals(conSenhaCadastro)) {
                        Toast.makeText(getApplicationContext(), "Usuário cadastrado com sucesso!", Toast.LENGTH_LONG).show();


                        //Paciente(int id, String nome, String senha, String email, int idade, double circunferencia, double peso)
                        Paciente paciente = new Paciente (0, nomeCompleto, senhaCadastro, emailCadastro, "M", 0, 0 , 0, 0);

                        //verificar opcao de sexo selecionada
                        String selected = sexo.getSelectedItem().toString();
                        if (selected.equals("Masc.")) {
                            paciente.set_sexo("M");
                        } else {
                            paciente.set_sexo("F");
                        }


                        Log.d("Criando: ", "criar conta");
                        Log.d("Nome : ", paciente.get_nome());
                        Log.d("Senha : ", paciente.get_senha());
                        Log.d("Email: ", paciente.get_email());
                        Log.d("Idade : ", String.valueOf(paciente.get_idade()));
                        Log.d("Circunferencia : ", String.valueOf(paciente.get_circunferencia()));
                        Log.d("Peso : ", String.valueOf(paciente.get_peso()));
                        Log.d("Altura : ", String.valueOf(paciente.get_altura()));
                        Log.d("IMC : ", String.valueOf(paciente.get_imc()));
                        Log.d("HBA1C : ", String.valueOf(paciente.get_hba1c()));
                        Log.d("GlicoseJejum : ", String.valueOf(paciente.get_glicosejejum()));
                        Log.d("Glicose75g : ", String.valueOf(paciente.get_glicose75g()));

                        String msg = db.addPaciente(paciente);

                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();

                        Intent voltaLogin = new Intent(CriarConta.this, TelaLogin.class);
                        startActivity(voltaLogin);

                    } else {
                        Toast.makeText(getApplicationContext(), "Insira senhas iguais!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Email já cadastrado!", Toast.LENGTH_LONG).show();
                }


            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent voltaLogin = new Intent(CriarConta.this, TelaLogin.class);
                startActivity(voltaLogin);
            }
        });
    }
}
