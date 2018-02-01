package app.com.example.wagner.meupredi.View.Application;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import app.com.example.wagner.meupredi.R;

/**
 * Created by LeandroDias1 on 18/04/2017.
 */

public class Consultas extends Fragment {

    private DateFormat formatacaoData = DateFormat.getDateInstance();
    private Calendar dataTime = Calendar.getInstance();
    private TextView textoExibicao, cardNomeNovaConsulta, cardDataNovaConsulta, cardHorarioNovaConsulta;
    private Button btnMarcarData, btnMarcarHorario, agendarNovaConsulta;
    private EditText nomeNovaConsulta;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_consultas, container, false);

        textoExibicao = (TextView) view.findViewById(R.id.textView_data_tela_consulta);
        btnMarcarData = (Button) view.findViewById(R.id.btn_data_consulta_marcada);
        btnMarcarHorario = (Button) view.findViewById(R.id.btn_horario_consulta_marcada);
        nomeNovaConsulta = (EditText) view.findViewById(R.id.editText_nome_nova_consulta);
        cardNomeNovaConsulta = (TextView) view.findViewById(R.id.textView_nome_card_nova_consulta);
        cardDataNovaConsulta = (TextView) view.findViewById(R.id.textView_data_card_nova_consulta);
        cardHorarioNovaConsulta = (TextView) view.findViewById(R.id.textView_horario_card_nova_consulta);
        agendarNovaConsulta = (Button) view.findViewById(R.id.btn_agendar_nova_consulta);

        agendarNovaConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nomeNovaConsulta != null){
                    cardNomeNovaConsulta.setText( nomeNovaConsulta.getText().toString() );
                }
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

        updateTextLabel(); // FUNCAO DE CAPTURAR DATA ATUAL

        return view;
    }

    private void updateData(){
        new DatePickerDialog(getActivity(), d, dataTime.get(Calendar.YEAR), dataTime.get(Calendar.MONTH), dataTime.get(Calendar.DAY_OF_MONTH)).show();


    }

    private void updateTime(){
        new TimePickerDialog(getActivity(), t, dataTime.get(Calendar.HOUR_OF_DAY), dataTime.get(Calendar.MINUTE), true ).show();

    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener(){

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            cardDataNovaConsulta.setText(formatacaoData.format(dataTime.getTime()));
        }
    };

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener(){

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        }
    };

    private void updateTextLabel() {
        textoExibicao.setText(formatacaoData.format(dataTime.getTime()));

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

    }
}