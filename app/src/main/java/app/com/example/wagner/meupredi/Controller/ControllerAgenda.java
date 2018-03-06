package app.com.example.wagner.meupredi.Controller;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import app.com.example.wagner.meupredi.Model.DatabaseHandler;
import app.com.example.wagner.meupredi.Model.ModelClass.AgendaClass;
import app.com.example.wagner.meupredi.Model.ModelClass.Paciente;

/**
 * Created by LeandroDias1 on 05/03/2018.
 */

public class ControllerAgenda {
    DatabaseHandler db;

    public ControllerAgenda(Context context) {
        db = new DatabaseHandler(context);
    }

    public String adicionarEvento(Paciente paciente, AgendaClass evento){
        Log.d("ADICIONANDO PACIENTE : ", paciente.get_nome());
        Log.d("EVENTO INFO: ", evento.getTitulo() + " " + evento.getDate().toString());
        return db.modelAddAgenda(paciente, evento);
    }

    public ArrayList<AgendaClass> getAllEventos(Paciente paciente){
        return db.modelGetAllAgendas(paciente);
    }

}
