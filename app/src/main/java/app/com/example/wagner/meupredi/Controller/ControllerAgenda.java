package app.com.example.wagner.meupredi.Controller;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

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

    public void printAllEventos(Paciente paciente){
        ArrayList<AgendaClass> printEventos = db.modelGetAllAgendas(paciente);
        for(int i=0;i<printEventos.size();i++){
            for(int j=0;j<printEventos.size() - 1;j++){
                if(printEventos.get(j).getDate().after(printEventos.get(j+1).getDate())){
                    Collections.swap(printEventos, j, j+1 );
                }
            }
        }
        Log.d("Eventos do ", paciente.get_nome());
        for(int i=0;i<printEventos.size();i++){
            Log.d("Local : ", printEventos.get(i).getLocal());
            Log.d("Data : ", printEventos.get(i).getDate().toString());
        }

    }

}
