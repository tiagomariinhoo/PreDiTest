package app.com.example.wagner.meupredi;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import app.com.example.wagner.meupredi.BDMenuLogin.DatabaseHandler;
import app.com.example.wagner.meupredi.BDMenuLogin.Paciente;

/**
 * Created by LeandroDias1 on 25/07/2017.
 */

public class Taxas  extends AppCompatActivity {

    Paciente paciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taxas);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}