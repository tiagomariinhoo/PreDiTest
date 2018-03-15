package app.com.example.wagner.meupredi.View.Application;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;

import app.com.example.wagner.meupredi.Controller.ControllerAgenda;
import app.com.example.wagner.meupredi.Controller.ControllerPaciente;
import app.com.example.wagner.meupredi.Model.ModelClass.Paciente;
import app.com.example.wagner.meupredi.R;

import static app.com.example.wagner.meupredi.R.layout.activity_perfil;

/**
 * Created by LeandroDias1 on 18/04/2017.
 */

@Deprecated
public class Perfil extends ActivityGroup {

    private ImageView coracao, configuracoes;
    private Button notificacoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(activity_perfil);

        coracao = (ImageView) findViewById(R.id.image_perfil_coracao);
        configuracoes = (ImageView) findViewById(R.id.image_perfil_dados);
        notificacoes = (Button) findViewById(R.id.notify_perfil_btm);

       // Paciente paciente = new Paciente();
       /// paciente = (Paciente) paciente.getIntent().getExtras().get("Paciente");

       // ControllerAgenda controllerAgenda = new ControllerAgenda(getApplicationContext());
        // controllerAgenda.printAllEventos(paciente);

        coracao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Perfil.this, PopPerfil.class));
            }
        });

        TabHost abas = (TabHost) findViewById(R.id.tabhost);
        abas.setup(this.getLocalActivityManager());

        TabHost.TabSpec descritor = abas.newTabSpec("aba1");
        descritor.setContent(new Intent(this, TabTaxas.class));
        descritor.setIndicator("TAXAS");
        abas.addTab(descritor);

        descritor = abas.newTabSpec("aba2");
        descritor.setContent(new Intent(this, TabCorpo.class));
        descritor.setIndicator("CORPO");
        abas.addTab(descritor);

        descritor = abas.newTabSpec("aba3");
        descritor.setContent(new Intent(this, TabEvolucao.class));
        descritor.setIndicator("EVOLUÇÃO");
        abas.addTab(descritor);

    }
}

