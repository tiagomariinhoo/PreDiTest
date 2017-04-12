package app.com.example.wagner.meupredi;


import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CriarConta extends AppCompatActivity {

    private CheckBox boxSenha;
    private EditText nome;
    private EditText email;
    private EditText senha;
    private EditText conSenha;
    private ConstraintLayout tela;
    private Button criarConta;
    private Button cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_conta);

        boxSenha = (CheckBox) findViewById(R.id.checkedConSenha);
        nome = (EditText) findViewById(R.id.edit_email_esqueceu);
        email = (EditText) findViewById(R.id.edit_endereco_email);
        senha = (EditText) findViewById(R.id.edit_senha_cadastro);
        conSenha = (EditText) findViewById(R.id.edit_novamente_senha);

        criarConta = (Button) findViewById(R.id.btn_enviar_esqueceu);
        cancelar = (Button) findViewById(R.id.btn_cancelar);
        tela = (ConstraintLayout) findViewById(R.id.tela_criar_conta);

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

                final String nomeCompleto = nome.getText().toString();
                final String emailCadastro = email.getText().toString();
                final String senhaCadastro = senha.getText().toString();
                final String conSenhaCadastro = conSenha.getText().toString();

                if (senhaCadastro.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Insira uma senha válida!", Toast.LENGTH_LONG).show();
                } else if (senhaCadastro.equals(conSenhaCadastro)) {
                    Toast.makeText(getApplicationContext(), "Usuário cadastrado com sucesso!", Toast.LENGTH_LONG).show();

                    DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                    //Paciente(int id, String nome, String senha, String email, int idade, double circunferencia, double peso)

                    Paciente paciente = new Paciente (0, nomeCompleto, senhaCadastro, emailCadastro, 0, 0 , 0, 0);

                    String msg = db.addPaciente(paciente);
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();

                    Intent voltaLogin = new Intent(CriarConta.this, TelaLogin.class);
                    startActivity(voltaLogin);

                } else {
                    Toast.makeText(getApplicationContext(), "Insira senhas iguais!", Toast.LENGTH_LONG).show();
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
