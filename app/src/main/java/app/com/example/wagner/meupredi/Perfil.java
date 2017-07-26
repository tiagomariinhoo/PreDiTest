package app.com.example.wagner.meupredi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import app.com.example.wagner.meupredi.BDMenuLogin.DatabaseHandler;
import app.com.example.wagner.meupredi.BDMenuLogin.Paciente;

/**
 * Created by LeandroDias1 on 18/04/2017.
 */

public class Perfil extends Fragment {

    MenuPrincipal menu;
    TextView nome, imc, pesoValor;
    TextView taxas, peso, desempenho, dados;
    ImageView prancheta, grafico, engrenagem;
    Paciente paciente;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        DatabaseHandler db = new DatabaseHandler(getActivity().getApplicationContext());
        paciente = ((MenuPrincipal)getActivity()).pegarPacienteMenu();
        Log.d("Paciente nome : " , paciente.get_nome());
        Log.d("Paciente peso : ", String.valueOf(paciente.get_peso()));

        //pega o peso atualizado no banco para exibir na tela
        paciente.set_peso(db.getPeso(paciente.get_id()));

        nome = (TextView) view.findViewById(R.id.text_nome_perfil);
        nome.setText(String.valueOf(paciente.get_nome()));

        imc = (TextView) view.findViewById(R.id.text_valor_imc);
        imc.setText("IMC - " + String.valueOf(paciente.get_imc()));

        prancheta = (ImageView) view.findViewById(R.id.image_prancheta_perfil);
        pesoValor = (TextView) view.findViewById(R.id.text_valor_peso);
        pesoValor.setText(String.valueOf(paciente.get_peso()));
        grafico = (ImageView) view.findViewById(R.id.image_graficos_perfil);
        engrenagem = (ImageView) view.findViewById(R.id.image_dados_perfil);

        taxas = (TextView) view.findViewById(R.id.text_taxas_perfil);
        peso = (TextView) view.findViewById(R.id.text_peso_perfil);
        desempenho = (TextView) view.findViewById(R.id.text_desempenho_perfil);
        dados = (TextView) view.findViewById(R.id.text_dados_perfil);



        //paciente = ((MenuPrincipal)getActivity()).pegarPacienteMenu();

        //imc = (TextView) view.findViewById(R.id.text_imc_valor_perfil);
        //imc.setText(String.valueOf(paciente.get_imc()));

        //imagemCentral = (ImageView) view.findViewById(R.id.image_central);

       /* barChart = (BarChart) view.findViewById(R.id.bargraph);

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(44f, 0));
        barEntries.add(new BarEntry(88f, 1));
        barEntries.add(new BarEntry(66f, 2));
        barEntries.add(new BarEntry(12f, 3));
        barEntries.add(new BarEntry(19f, 4));
        barEntries.add(new BarEntry(91f, 5));

        ArrayList<IBarDataSet> barDataSet = new ArrayList<>();
        //BarDataSet barDataSet = new BarDataSet(barEntries, "Dates");
        barDataSet.add((IBarDataSet) barEntries);

        ArrayList<String> theDates = new ArrayList<>();
        theDates.add("April");
        theDates.add("May");
        theDates.add("June");
        theDates.add("July");
        theDates.add("August");
        theDates.add("September");

        BarData theData = new BarData(theDates, barDataSet);
        barChart.setData(theData);

        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);*/

       //db.verificarData(paciente);

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Perfil");

        paciente = ((MenuPrincipal)getActivity()).pegarPacienteMenu();

        taxas.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), Taxas.class);
                intent.putExtra("Paciente", paciente);
                startActivity(intent);
            }
        });

        peso.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), Peso.class);
                intent.putExtra("Paciente", paciente);
                startActivity(intent);
            }
        });

        desempenho.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), NovoExercicio.class);
                intent.putExtra("Paciente", paciente);
                startActivity(intent);
            }
        });

        dados.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), Dados.class);
                intent.putExtra("Paciente", paciente);
                startActivity(intent);
            }
        });

        prancheta.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), Taxas.class);
                intent.putExtra("Paciente", paciente);
                startActivity(intent);
            }

        });

        pesoValor.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), Peso.class);
                intent.putExtra("Paciente", paciente);
                startActivity(intent);
            }

        });

        grafico.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), NovoExercicio.class);
                intent.putExtra("Paciente", paciente);
                startActivity(intent);
            }

        });

        engrenagem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), Dados.class);
                intent.putExtra("Paciente", paciente);
                startActivity(intent);
            }

        });
    }

    @Override
    public void onResume() {

        //esse metodo e usado para recarregar as informacoes 'dinamicas' da tela (nome, imc, peso)
        super.onResume();

        Log.d("Recarregando tela", "onResume");

        paciente = ((MenuPrincipal)getActivity()).pegarPacienteMenu();

        nome.setText(String.valueOf(paciente.get_nome()));
        imc.setText("IMC - " + String.valueOf(paciente.get_imc()));
        pesoValor.setText(String.valueOf(paciente.get_peso()));
    }

}
