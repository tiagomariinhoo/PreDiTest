package app.com.example.wagner.meupredi.TelasMenu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;

import app.com.example.wagner.meupredi.BDMenuLogin.MenuPrincipal;
import app.com.example.wagner.meupredi.BDMenuLogin.Paciente;
import app.com.example.wagner.meupredi.R;

/**
 * Created by LeandroDias1 on 18/04/2017.
 */

public class Perfil extends Fragment {

    MenuPrincipal menu;
    TextView imc;
    BarChart barChart;
    ImageView imagemCentral;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments

        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        Paciente paciente = new Paciente();
        paciente = ((MenuPrincipal)getActivity()).pegarPacienteMenu();

        imc = (TextView) view.findViewById(R.id.text_imc_valor_perfil);
        imc.setText(String.valueOf(paciente.get_imc()));

        imagemCentral = (ImageView) view.findViewById(R.id.image_central);

        paciente.set_sexo("M");
        paciente.set_imc(22);

        if(paciente.get_sexo() == "M"){
            if(paciente.get_imc() >= 20.7 && paciente.get_imc() <= 26.4){ // PESO NORMAL
                imagemCentral.setImageResource(R.mipmap.happy_face);
            }
            else{
                imagemCentral.setImageResource(R.mipmap.sad_face);
            }
        }
        else if(paciente.get_sexo() == "F"){
            if(paciente.get_imc() >= 19.1 && paciente.get_imc() <= 25.8){ // PESO NORMAL

            }
        }

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
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Perfil");
    }

}
