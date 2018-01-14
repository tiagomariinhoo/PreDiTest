package app.com.example.wagner.meupredi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import app.com.example.wagner.meupredi.Model.Paciente;
import app.com.example.wagner.meupredi.View.MenuPrincipal;

/**
 * Created by LeandroDias1 on 18/04/2017.
 */

public class Exames extends Fragment{

    private ImageView chamadaNovoExame;
    private TextView chamadaLipidograma, chamadaHemograma;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments

        View view = inflater.inflate(R.layout.fragment_exames, container, false);

        chamadaNovoExame = (ImageView) view.findViewById(R.id.image_nova_atividade);
        chamadaNovoExame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paciente paciente = ((MenuPrincipal)getActivity()).pegarPacienteMenu();

                Intent novoExame = new Intent(getActivity(), cadastroExame.class);
                novoExame.putExtra("Paciente", paciente);
                startActivity(novoExame);
            }
        });

        chamadaLipidograma = (TextView) view.findViewById(R.id.text_lipidograma);
        chamadaLipidograma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paciente paciente = ((MenuPrincipal)getActivity()).pegarPacienteMenu();

                Intent novoExame = new Intent(getActivity(), TelaExameLipidograma.class);
                novoExame.putExtra("Paciente", paciente);
                startActivity(novoExame);
            }
        });

        chamadaHemograma = (TextView) view.findViewById(R.id.text_hemograma);
        chamadaHemograma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paciente paciente = ((MenuPrincipal)getActivity()).pegarPacienteMenu();

                Intent novoExame = new Intent(getActivity(), TelaExameHemograma.class);
                novoExame.putExtra("Paciente", paciente);
                startActivity(novoExame);
            }
        });


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Exames");
    }

}