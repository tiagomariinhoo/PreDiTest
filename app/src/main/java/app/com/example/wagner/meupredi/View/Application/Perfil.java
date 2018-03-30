package app.com.example.wagner.meupredi.View.Application;

import android.app.ActivityGroup;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import app.com.example.wagner.meupredi.Controller.ControllerAgenda;
import app.com.example.wagner.meupredi.Model.ModelClass.Paciente;
import app.com.example.wagner.meupredi.R;
import app.com.example.wagner.meupredi.View.Account.MenuPrincipal;

import static app.com.example.wagner.meupredi.R.layout.activity_perfil;

/**
 * Created by LeandroDias1 on 18/04/2017.
 */

@Deprecated
public class Perfil extends ActivityGroup {

    private ImageView coracao, configuracoes;
    private Button notificacoes;
    private ControllerAgenda controllerAgenda;
    private MenuPrincipal menuPrincipal;
    private Paciente paciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(activity_perfil);

        coracao = (ImageView) findViewById(R.id.image_perfil_coracao);
        configuracoes = (ImageView) findViewById(R.id.image_perfil_dados);
        notificacoes = (Button) findViewById(R.id.notify_perfil_btm);

        paciente = (Paciente) getIntent().getExtras().get("Paciente");


        controllerAgenda = new ControllerAgenda(getApplicationContext());
        controllerAgenda.getAllEventos(paciente);

        Date notifyDate = controllerAgenda.eventNotify(paciente);
        if(notifyDate != null){
            getNotify(notifyDate);
        }


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

    public void getNotify(Date notifyDate){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setSmallIcon(R.mipmap.ic_coracao)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_coracao))
                .setContentTitle("Notification from PreDi!")
                .setContentText("Você tem uma consulta em " + dateFormat.format(notifyDate) + ", dê uma verificada!");

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notificationBuilder.build());
    }
}

