package app.com.example.wagner.meupredi;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pichau on 08/04/2017.
 */

public class PosLogin extends AppCompatActivity {

    private TextView nomeUsuario;
    private EditText idade;
    private EditText altura;
    private EditText peso;
    private EditText circunferecia;
    private Button cancelar;
    private Button concluir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poslogin);

        nomeUsuario = (TextView) findViewById(R.id.text_nome_usuario_poslogin);
        idade = (EditText) findViewById(R.id.edit_idade_poslogin);
        altura = (EditText) findViewById(R.id.edit_altura_poslogin);
        peso = (EditText) findViewById(R.id.edit_peso_poslogin);
        circunferecia = (EditText) findViewById(R.id.edit_circunferencia_poslogin);
        cancelar = (Button) findViewById(R.id.btn_cancelar_poslogin);
        concluir = (Button) findViewById(R.id.btn_concluir_poslogin);

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

                final String idadeCadastro = idade.getText().toString();
                final String alturaCadastro = altura.getText().toString();
                final String pesoCadastro = peso.getText().toString();
                final String circunferenciaCadastro = circunferecia.getText().toString();

                DatabaseHandler bancoController = new DatabaseHandler(getApplicationContext());

                if(bancoController.editarBanco()){
                    Toast.makeText(getApplicationContext(),"Sucesso ao editar!",Toast.LENGTH_LONG).show();
                    Log.d("Sucesso"," Sucesso");
                } else {
                    Toast.makeText(getApplicationContext(),"Erro ao editar!",Toast.LENGTH_LONG).show();
                    Log.d("Erro"," Erro");
                }

                List<Paciente> pac = new ArrayList<Paciente>();
                pac = bancoController.getAllPacientes();

                Log.d("Nome : ", pac.get(0).get_nome());
                Log.d("Idade : ", Integer.toString(pac.get(0).get_idade()));
                Log.d("Email : ",  pac.get(0).get_email());
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent voltaLogin = new Intent(PosLogin.this, TelaLogin.class);
                startActivity(voltaLogin);
            }
        });

    }
}