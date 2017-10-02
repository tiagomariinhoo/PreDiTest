package app.com.example.wagner.meupredi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by LeandroDias1 on 28/09/2017.
 */

public class TelaExameHemograma  extends AppCompatActivity {

    private EditText dataLipidograma, localLipidograma;
    private EditText hdl, ldl, colesterolTotal, triglicerides;
    private Button atualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exame_hemograma);


    }

}