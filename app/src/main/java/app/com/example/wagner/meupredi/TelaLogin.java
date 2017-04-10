package app.com.example.wagner.meupredi;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TelaLogin extends AppCompatActivity {

    EditText usuario, senha;
    Button btnLogin;
    TextView textCriar;
    CheckBox mostrarSenha;
    ConstraintLayout tela;
    TextView esqueceuSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);

        usuario = (EditText) findViewById(R.id.edit_usuario_login);
        senha = (EditText) findViewById(R.id.edit_senha_login);
        btnLogin = (Button) findViewById(R.id.btn_login);
        textCriar = (TextView) findViewById(R.id.text_criar_conta);
        mostrarSenha = (CheckBox) findViewById(R.id.checkBox_senha_login);
        tela = (ConstraintLayout) findViewById(R.id.tela_login);
        esqueceuSenha = (TextView) findViewById(R.id.text_esqueceu_senha_login);

        findViewById(R.id.tela_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getCurrentFocus()!=null && getCurrentFocus() instanceof EditText){
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(senha.getWindowToken(), 0);
                    imm.hideSoftInputFromWindow(usuario.getWindowToken(), 0);
                }
            }
        });

        mostrarSenha.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(!isChecked) {
                    senha.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    senha.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        textCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abreCriarConta = new Intent(TelaLogin.this, CriarConta.class);
                startActivity(abreCriarConta);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHandler db = new DatabaseHandler(getApplicationContext());


                List<Paciente> pacientesList = new ArrayList<Paciente>();
                pacientesList = db.getAllPacientes();

                for(int i=0;i<pacientesList.size();i++){
                    Log.d(pacientesList.get(i).get_nome()," -> Nome do paciente");
                    Log.d(pacientesList.get(i).get_email()," -> Email do paciente");
                    Log.d(pacientesList.get(i).get_senha(), " -> Senha do paciente");

                }

                String user,pass;
                user = usuario.getText().toString();
                pass = senha.getText().toString();

                if(db.verificarLogin(user,pass)){
                    Intent it = new Intent(TelaLogin.this, PosLogin.class);
                    startActivity(it);

                } else {
                    Toast.makeText(getApplicationContext(), "Insira um usuário válido!", Toast.LENGTH_LONG).show();
                }

            }
        });

        esqueceuSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaLogin.this, EsqueceuSenha.class);
                startActivity(intent);

            }
        });
    }
}