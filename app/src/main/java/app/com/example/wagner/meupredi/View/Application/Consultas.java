package app.com.example.wagner.meupredi.View.Application;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

import app.com.example.wagner.meupredi.R;

/**
 * Created by LeandroDias1 on 18/04/2017.
 */

public class Consultas extends Activity {

    private DateFormat formatacaoData = DateFormat.getDateInstance();
    private Calendar dataTime = Calendar.getInstance();
    private TextView cardDataNovaConsulta, cardHorarioNovaConsulta;
    private Button btnMarcarData, btnMarcarHorario;
    private ImageView agendarNovaConsulta;
    private EditText nomeNovaConsulta;
    private AlertDialog.Builder alertaNovaConsulta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);

        nomeNovaConsulta = (EditText) findViewById(R.id.editText_nome_nova_consulta);
        btnMarcarData = (Button) findViewById(R.id.btn_data_consulta_marcada);
        btnMarcarHorario = (Button) findViewById(R.id.btn_horario_consulta_marcada);
        agendarNovaConsulta = (ImageView) findViewById(R.id.btn_agendar_nova_consulta);
        cardDataNovaConsulta = (TextView) findViewById(R.id.textView_data_card_nova_consulta);
        cardHorarioNovaConsulta = (TextView) findViewById(R.id.textView_horario_card_nova_consulta);

        agendarNovaConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = nomeNovaConsulta.getText().toString();

                cardHorarioNovaConsulta.setText(nome);

                alertaNovaConsulta = new AlertDialog.Builder( Consultas.this );

                alertaNovaConsulta.setTitle("Atenção!");

                alertaNovaConsulta.setMessage("Verifique as informações da sua nova consulta e confirme");

                // Caso Não
                alertaNovaConsulta.setNegativeButton("CANCELAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(Consultas.this, "Nova consulta cancelada", Toast.LENGTH_SHORT).show();
                            }
                        });

                // Caso Sim
                alertaNovaConsulta.setPositiveButton("CONFIRMAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(Consultas.this, "Nova consulta agendada!", Toast.LENGTH_SHORT).show();
                                // FAZER FUNÇÃO DE ADICIONAR NOVA CONSULTA EM LISTA DE CONSULTAS MARCADAS
                            }
                        });

                alertaNovaConsulta.create();
            }
        });

        btnMarcarData .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
            }
        });

        btnMarcarHorario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTime();
            }
        });

    }

    private void updateData(){
        new DatePickerDialog(Consultas.this, d, dataTime.get(Calendar.YEAR), dataTime.get(Calendar.MONTH), dataTime.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void updateTime(){
        new TimePickerDialog(Consultas.this, t, dataTime.get(Calendar.HOUR_OF_DAY), dataTime.get(Calendar.MINUTE), true ).show();

    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener(){

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            String diaEscolhido, mesEscolhido, anoEscolhido;
            diaEscolhido = Integer.toString(dayOfMonth);
            mesEscolhido = Integer.toString(month+1);
            anoEscolhido = Integer.toString(year);
            cardDataNovaConsulta.setText(diaEscolhido + "/" + mesEscolhido + "/" + anoEscolhido);
        }
    };

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener(){

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String horaEscolhida = Integer.toString(hourOfDay);
            String minutosEscolhidos = Integer.toString(minute);
            cardHorarioNovaConsulta.setText(horaEscolhida + ":" + minutosEscolhidos);
        }
    };

}