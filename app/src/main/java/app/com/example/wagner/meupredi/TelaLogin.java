package app.com.example.wagner.meupredi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
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

import app.com.example.wagner.meupredi.BDMenuLogin.Paciente;

public class TelaLogin extends AppCompatActivity {

    public static final String PREFS_NAME = "Preferences";
    EditText usuario, senha;
    Button btnLogin;
    TextView textCriar;
    CheckBox mostrarSenha, manterConectado;
    ConstraintLayout tela;
    TextView esqueceuSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);

        usuario = (EditText) findViewById(R.id.edit_usuario_login);
        senha = (EditText) findViewById(R.id.edit_senha_login);
        manterConectado = (CheckBox) findViewById(R.id.checkBox_manter_conectado_login);
        btnLogin = (Button) findViewById(R.id.btn_login);
        textCriar = (TextView) findViewById(R.id.text_criar_conta);
        mostrarSenha = (CheckBox) findViewById(R.id.checkBox_senha_login);
        tela = (ConstraintLayout) findViewById(R.id.tela_login);
        esqueceuSenha = (TextView) findViewById(R.id.text_esqueceu_senha_login);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        usuario.setText(settings.getString("PrefUsuario", ""));
        senha.setText(settings.getString("PrefSenha", ""));

        manterConectado.setChecked(false);

        //(usuario.getText().length() > 0 && senha.getText().length() > 0)
        if(usuario.getText().length() > 0 && senha.getText().length() > 0) {
            btnLogin.post(new Runnable() {
                @Override
                public void run() {
                    btnLogin.performClick();
                }
            });
        }

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
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = settings.edit();

                List<Paciente> pacList = new ArrayList<Paciente> ();
                pacList = db.getAllPacientes();

                for(int i=0;i<pacList.size();i++){
                    Log.d(pacList.get(i).get_nome()," -> Nome do paciente");
                    Log.d(pacList.get(i).get_email()," -> Email do paciente");
                    Log.d(pacList.get(i).get_senha(), " -> Senha do paciente");
                }

                if(manterConectado.isChecked()) {
                    editor.putString("PrefUsuario", usuario.getText().toString());
                    editor.putString("PrefSenha", senha.getText().toString());

                    editor.commit();
                }

                String user,pass;
                user = usuario.getText().toString();
                pass = senha.getText().toString();

                Paciente paciente = new Paciente();
                paciente = db.verificarLogin(user,pass);

                if(paciente.get_id() != -1){
                    Intent it = new Intent(TelaLogin.this, PosLogin.class);
                    it.putExtra("Paciente", paciente);
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