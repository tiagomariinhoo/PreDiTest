package app.com.example.wagner.meupredi.View.Application;

import android.app.ActivityGroup;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;

import app.com.example.wagner.meupredi.R;

import static app.com.example.wagner.meupredi.R.layout.activity_perfil;

/**
 * Created by LeandroDias1 on 18/04/2017.
 */


public class Perfil extends ActivityGroup {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(activity_perfil);

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

        Button notify = (Button) findViewById(R.id.notify_btm);
        notify.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                notificationCall();
            }
        });

    }

    public void notificationCall(){
        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setSmallIcon(R.mipmap.ic_coracao)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_coracao))
                .setContentTitle("Notification from PreDi!")
                .setContentText("Hello!! Teste teste teste teste teste");

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notificationBuilder.build());
    }

}

