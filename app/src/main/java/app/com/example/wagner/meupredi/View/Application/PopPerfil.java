package app.com.example.wagner.meupredi.View.Application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import app.com.example.wagner.meupredi.R;

/**
 * Created by leandro on 06/03/18.
 */

public class PopPerfil extends Activity{

    private ImageView close;
    private TextView consultas, agenda, metas, academia;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pop_perfil);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) ( width*.8), (int) (height*.55));

    }
}
