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

import com.github.mikephil.charting.charts.BarChart;

import app.com.example.wagner.meupredi.BDMenuLogin.DatabaseHandler;
import app.com.example.wagner.meupredi.BDMenuLogin.Paciente;

/**
 * Created by LeandroDias1 on 18/04/2017.
 */

public class Perfil extends Fragment {

    MenuPrincipal menu;
    TextView imc, peso;
    BarChart barChart;
    ImageView imagemCentral;
    Paciente paciente;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        DatabaseHandler db = new DatabaseHandler(getActivity().getApplicationContext());
        paciente = ((MenuPrincipal)getActivity()).pegarPacienteMenu();

        //pega o peso atualizado no banco para exibir na tela
        paciente.set_peso(db.getPeso(paciente.get_id()));

        imc = (TextView) view.findViewById(R.id.text_valor_imc);
        imc.setText(String.valueOf(paciente.get_imc()));

        peso = (TextView) view.findViewById(R.id.text_valor_peso);
        peso.setText(String.valueOf(paciente.get_peso()));

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

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Perfil");

        paciente = ((MenuPrincipal)getActivity()).pegarPacienteMenu();

        peso.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), Peso.class);
                intent.putExtra("Paciente", paciente);
                startActivity(intent);
            }

        });
    }

    @Override
    public void onResume() {

        //esse metodo e usado para recarregar as informacoes 'dinamicas' da tela (imc, peso, imagem...)
        super.onResume();

        Log.d("Recarregando tela", "onResume");

        paciente = ((MenuPrincipal)getActivity()).pegarPacienteMenu();

        imc.setText(String.valueOf(paciente.get_imc()));
        peso.setText(String.valueOf(paciente.get_peso()));

    }

}
