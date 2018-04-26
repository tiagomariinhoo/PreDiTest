package app.com.example.wagner.meupredi.View.Application;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import app.com.example.wagner.meupredi.Controller.ControllerAgenda;
import app.com.example.wagner.meupredi.Model.ModelClass.AgendaClass;
import app.com.example.wagner.meupredi.Model.ModelClass.Paciente;
import app.com.example.wagner.meupredi.R;

/**
 * Created by LeandroDias1 on 18/04/2017.
 */

public class Consultas extends Activity {

    private DateFormat formatacaoData = DateFormat.getDateInstance();
    private Calendar dataTime = Calendar.getInstance();
    private TextView btnMarcarData, btnMarcarHorario;
    private ImageView agendarNovaConsulta;
    private EditText nomeNovaConsulta;
    private AlertDialog.Builder alertaNovaConsulta;
    private String date = "-", time = "-", nome = "-";
    private String diaEscolhido = "", mesEscolhido = "", anoEscolhido = "";
    private Paciente paciente;
    ArrayList<AgendaClass> agendaList = new ArrayList<>();

    private ListView listaDeConsultas;

    private ArrayList<String> arraylist;
    private ArrayAdapter<String> adapter;
    private String[] items = {};

    private ArrayList<String> arraylistDatas;
    private ArrayAdapter<String> adapterD;
    private String[] itemsDatas = {};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        paciente = (Paciente) getIntent().getExtras().get("Paciente");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);

        nomeNovaConsulta = (EditText) findViewById(R.id.editText_nome_nova_consulta);
        btnMarcarData = (TextView) findViewById(R.id.btn_data_consulta_marcada);
        btnMarcarHorario = (TextView) findViewById(R.id.btn_horario_consulta_marcada);
        agendarNovaConsulta = (ImageView) findViewById(R.id.btn_agendar_nova_consulta);
        listaDeConsultas = (ListView) findViewById(R.id.lista_consultas);

        arraylist = new ArrayList<>(Arrays.asList(items));
        arraylist = new ArrayList<>(Arrays.asList(itemsDatas));
        adapter = new ArrayAdapter<String>(this, R.layout.lista_consultas_item, R.id.text_consulta_item, arraylist);
        adapterD = new ArrayAdapter<String>(this, R.layout.lista_consultas_item, R.id.text_consulta_data_item, arraylistDatas);
        listaDeConsultas.setAdapter(adapter);
//        listaDeConsultas.setAdapter(adapterD);

        paciente = (Paciente) getIntent().getExtras().get("Paciente");
        nome = "";
        agendarNovaConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nome = nomeNovaConsulta.getText().toString();
                //date = btnMarcarData.getText().toString();
                //time = btnMarcarHorario.getText().toString();
                /*Log.d("Date :  ", date);
                Log.d("Time : " , time);*/

                alertaNovaConsulta = new AlertDialog.Builder( Consultas.this );

                alertaNovaConsulta.setTitle("Atenção!");

                alertaNovaConsulta.setMessage("Verifique as informações da sua nova consulta e confirme"+
                "\n"+"Nova Consulta em " + nome + ", " + date + ", as "+ time + ".");

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
                                nomeNovaConsulta.setText("");
                                btnMarcarData.setText("");
                                btnMarcarHorario.setText("");
                                arraylist.add(nome);
                                arraylistDatas.add(date);
                                adapter.notifyDataSetChanged();
                                adapterD.notifyDataSetChanged();

                                Log.d("NOME DO PACIENTE : " , paciente.get_nome());
                                try {
                                    //AgendaClass agenda = new AgendaClass(nome, "3293", date, time);

                                    //ControllerAgenda controllerAgenda = new ControllerAgenda(getApplicationContext());
                                    //Log.d(controllerAgenda.adicionarEvento(paciente, agenda), "");
                                    //agendaList = controllerAgenda.getAllEventos(paciente);
                                    //controllerAgenda.getAllEventos(paciente);

                                    //Log.d("Adicionando : ", controllerAgenda.adicionarEvento(paciente, agenda));
                                } catch (Exception e) {
                                    Log.d("N n n n n", " N FOI POSSIVEL ADICIONAR EVENTO");
                                    e.printStackTrace();
                                }
                            }
                });

                alertaNovaConsulta.create().show();
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

   /* private Date convertDate() throws ParseException {
        SimpleDateFormat convertData = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss", Locale.US);
        Log.d("DATE INICIO : ", date);
        Log.d("TIME INICIO: ", time);
        String ans = date + " " + time;
        try{
            //Date testData = convertData.parse(ans);
            //Log.d("Testdata : ", testData.toString());
            //return testData;
        } catch (ParseException e){
            Log.d("Não foi possível transformar", " ");
        }

        String a = "dd/mm/yyyy hh:mm:ss";
        Date test = convertData.parse(a);

        return test;
    }*/

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener(){

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            date = "";
            if(dayOfMonth < 10) diaEscolhido = "0";
            if(month < 10) mesEscolhido = "0";
            diaEscolhido = diaEscolhido + Integer.toString(dayOfMonth);
            mesEscolhido = mesEscolhido + Integer.toString(month+1);
            anoEscolhido = anoEscolhido + Integer.toString(year);
            date = anoEscolhido + "/" + mesEscolhido + "/" + diaEscolhido;
            btnMarcarData.setText(diaEscolhido + "/" + mesEscolhido + "/" + anoEscolhido);
        }
    };

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener(){

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            time = "";
            String horaEscolhida = "";
            String minutosEscolhidos = "";
            if(hourOfDay < 10 && hourOfDay >= 0) horaEscolhida = "0";
            if(minute < 10 && minute >= 0) minutosEscolhidos = "0";
            horaEscolhida = horaEscolhida + Integer.toString(hourOfDay);
            minutosEscolhidos = minutosEscolhidos + Integer.toString(minute);
            time = horaEscolhida + ":" + minutosEscolhidos + ":00";
            btnMarcarHorario.setText(horaEscolhida + ":" + minutosEscolhidos);
        }
    };

}