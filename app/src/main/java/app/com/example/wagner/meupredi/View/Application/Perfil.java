package app.com.example.wagner.meupredi.View.Application;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TextView;

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

    }
}

