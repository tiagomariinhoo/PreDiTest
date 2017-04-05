package app.com.example.wagner.meupredi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

public class TelaLogin extends AppCompatActivity {

    EditText usuario, senha;
    Button btnLogin;
    TextView textCriar;
    CheckBox mostrarSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);

        usuario = (EditText) findViewById(R.id.edit_usuario_login);
        senha = (EditText) findViewById(R.id.edit_senha_login);
        btnLogin = (Button) findViewById(R.id.btn_login);
        textCriar = (TextView) findViewById(R.id.text_criar_conta);
        mostrarSenha = (CheckBox) findViewById(R.id.checkBox_senha_login);

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

    }
}
