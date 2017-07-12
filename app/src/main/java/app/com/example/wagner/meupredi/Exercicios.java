package app.com.example.wagner.meupredi;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import app.com.example.wagner.meupredi.BDMenuLogin.Paciente;

/**
 * Created by LeandroDias1 on 18/04/2017.
 */

public class Exercicios extends Fragment {

    private TextView chamadaDesempenho;
    private ImageView chamadaGinasio;
    private Button start, reset, pause;
    private Chronometer cronometro;
    private long ultimaPausa;
    private Paciente paciente;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_exercicios, container, false);

        start = (Button) view.findViewById(R.id.btn_start_exercicios);
        pause = (Button) view.findViewById(R.id.btn_pause_exercicios);
        reset = (Button) view.findViewById(R.id.btn_reset_exercicios);
        cronometro = (Chronometer) view.findViewById(R.id.crn_cronometro_exercicio);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ultimaPausa != 0) {
                    cronometro.setBase(cronometro.getBase() + SystemClock.elapsedRealtime() - ultimaPausa);
                }
                else{
                    cronometro.setBase(SystemClock.elapsedRealtime());
                }
                cronometro.start();
                start.setEnabled(false);
                pause.setEnabled(true);
            }
        });


        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ultimaPausa = SystemClock.elapsedRealtime();
                cronometro.stop();
                pause.setEnabled(false);
                start.setEnabled(true);
            }
        });


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cronometro.stop();
                cronometro.setBase(SystemClock.elapsedRealtime());
                ultimaPausa = 0;
                pause.setEnabled(false);
                start.setEnabled(true);
            }
        });


        chamadaGinasio = (ImageView) view.findViewById(R.id.image_chamada_ginasio);
        chamadaGinasio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaGinasio = new Intent(getActivity(), Ginasio.class);
                paciente = ((MenuPrincipal)getActivity()).pegarPacienteMenu();
                telaGinasio.putExtra("Paciente", paciente);
                startActivity(telaGinasio);
            }
        });

        chamadaDesempenho = (TextView) view.findViewById(R.id.text_veja_seu_desempenho);
        chamadaDesempenho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent desempenho = new Intent(getActivity(), Desempenho.class);
                startActivity(desempenho);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Exercicios");
    }
}