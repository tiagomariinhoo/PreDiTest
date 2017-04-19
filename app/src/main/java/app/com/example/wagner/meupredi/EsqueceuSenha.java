package app.com.example.wagner.meupredi;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by LeandroDias1 on 10/04/2017.
 */

public class EsqueceuSenha extends AppCompatActivity {

    private Button enviar, cancelar;
    private EditText email;
    private ConstraintLayout tela;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueceu_senha);

        enviar = (Button) findViewById(R.id.btn_enviar_esqueceu);
        cancelar = (Button) findViewById(R.id.btn_cancelar_esqueceu);
        email = (EditText) findViewById(R.id.edit_email_esqueceu);
        tela = (ConstraintLayout) findViewById(R.id.tela_esqueceu_senha);

        findViewById(R.id.tela_esqueceu_senha).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getCurrentFocus()!=null && getCurrentFocus() instanceof EditText){
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(email.getWindowToken(), 0);
                }
            }
        });

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                Paciente paciente = new Paciente();

                paciente = db.verificarEmail(email.getText().toString().trim());

                //verificando existencia do email no banco de dados
                if(paciente.get_id() != -1){
                    //setando dados da mensagem
                    String sender = email.getText().toString().trim();
                    String subject = "MeuPreDi: recuperar senha";
                    String message = "Sr(a) " + paciente.get_nome().toString()
                                             + ", a opção de reenvio de senha foi solicitada com seu email. "
                                             + "Se você não fez essa solicitação, desconsidere esta mensagem.\n\n"
                                             + "Senha: " + paciente.get_senha().toString()
                                             + "\n\nEquipe MeuPreDi";

                    //Creating SendMail object
                    SendMail sm = new SendMail(getApplicationContext(), sender, subject, message);

                    //Executing sendmail to send email
                    sm.execute();

                    //mostrar toast
                    Toast.makeText(getApplicationContext(), "Email de recuperação de senha enviado!", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(EsqueceuSenha.this, TelaLogin.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Email não cadatrado ou inválido!", Toast.LENGTH_LONG).show();
                }


            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EsqueceuSenha.this, TelaLogin.class);
                startActivity(intent);
            }
        });

    }

}
