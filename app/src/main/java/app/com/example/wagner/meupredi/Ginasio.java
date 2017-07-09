package app.com.example.wagner.meupredi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by wagne on 07/07/2017.
 */

public class Ginasio extends AppCompatActivity {

    private ImageView chamadaNovoExercicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ginasio);

        chamadaNovoExercicio = (ImageView) findViewById(R.id.image_nova_atividade);

        chamadaNovoExercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Ginasio.this, NovoExercicio.class);
                startActivity(intent);
            }
        });
    }
}
