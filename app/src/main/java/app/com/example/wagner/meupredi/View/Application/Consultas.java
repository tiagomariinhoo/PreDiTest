package app.com.example.wagner.meupredi.View.Application;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;

import app.com.example.wagner.meupredi.R;

/**
 * Created by LeandroDias1 on 18/04/2017.
 */

public class Consultas extends Fragment {

    private DateFormat formatacaoData = DateFormat.getDateInstance();
    private Calendar dataTime = Calendar.getInstance();
    private TextView textoExibicao, cardDataNovaConsulta, cardHorarioNovaConsulta;
    private Button btnMarcarData, btnMarcarHorario, agendarNovaConsulta;
    private EditText nomeNovaConsulta;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_consultas, container, false);

        textoExibicao = (TextView) view.findViewById(R.id.textView_data_tela_consulta);
        btnMarcarData = (Button) view.findViewById(R.id.btn_data_consulta_marcada);
        btnMarcarHorario = (Button) view.findViewById(R.id.btn_horario_consulta_marcada);
        cardDataNovaConsulta = (TextView) view.findViewById(R.id.textView_data_card_nova_consulta);
        cardHorarioNovaConsulta = (TextView) view.findViewById(R.id.textView_horario_card_nova_consulta);

        agendarNovaConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nomeNovaConsulta != null){
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
                String horaEscolhida = Integer.toString(hourOfDay);
                String minutosEscolhidos = Integer.toString(minute);
                cardHorarioNovaConsulta.setText(horaEscolhida + ":" + minutosEscolhidos);
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