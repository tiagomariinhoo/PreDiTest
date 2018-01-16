package app.com.example.wagner.meupredi;

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
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by LeandroDias1 on 18/04/2017.
 */

public class Consultas extends Fragment {

    CompactCalendarView compactCalendar;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM, yyyy", Locale.getDefault());

    TextView dataMarcada;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_consultas, container, false);

        compactCalendar = (CompactCalendarView) view.findViewById(R.id.compactcalendar_view);
        compactCalendar.setUseThreeLetterAbbreviation(true);

        //Set an event for Teachers' Professional Day 2016 which is 21st of October
        //TODO: criar tela de criar consulta e salvar no banco
        //TODO: metodo para pegar lista de consultas do banco e setar no calendario

        Event ev1 = new Event(Color.RED, 1477040400000L, "Teachers' Professional Day");
        compactCalendar.addEvent(ev1, false);

        //dataMarcada = (TextView)view.findViewById(R.id.text_data_marcada);

        //dataMarcada.setText(Calendar.getInstance().get(Calendar.DAY_OF_WEEK));

        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {

                if (dateClicked.toString().compareTo("Fri Oct 21 00:00:00 GMT-03:00 2016") == 0) {
                    Toast.makeText(getActivity(), "Teachers' Professional Day", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(), "Sem consultas no dia selecionado.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                getActivity().setTitle(dateFormatMonth.format(firstDayOfNewMonth));
            }
        });

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        Date titulo = new Date();
        getActivity().setTitle(dateFormatMonth.format(titulo));

    }
}