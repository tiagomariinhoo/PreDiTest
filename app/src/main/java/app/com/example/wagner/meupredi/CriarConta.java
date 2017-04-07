package app.com.example.wagner.meupredi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CriarConta extends AppCompatActivity {

    private CheckBox boxSenha;
    private EditText nome;
    private EditText email;
    private EditText senha;
    private EditText conSenha;
    private Button cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_conta);

        boxSenha = (CheckBox) findViewById(R.id.checkedConSenha);
        nome = (EditText) findViewById(R.id.edit_nome_completo);
        email = (EditText) findViewById(R.id.edit_endereco_email);
        senha = (EditText) findViewById(R.id.edit_senha_cadastro);
        conSenha = (EditText) findViewById(R.id.edit_novamente_senha);
        cancelar = (Button) findViewById(R.id.btn_cancelar);

        String nomeCompleto = nome.getText().toString();
        String emailCadastro = email.getText().toString();
        //final String senhaCadastro = senha.getText().toString();
        //final String conSenhaCadastro = conSenha.getText().toString();
        boxSenha.setChecked(false);

        conSenha.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String senhaCadastro = senha.getText().toString();
                String conSenhaCadastro = conSenha.getText().toString();
                //if(senhaCadastro.charAt(senhaCadastro.length())==conSenhaCadastro.charAt(conSenhaCadastro.length())){
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

                //return false;
            }


        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancelarRegistro = new Intent(CriarConta.this, TelaLogin.class);
                startActivity(cancelarRegistro);
            }
        });

    }
}
