package app.com.example.wagner.meupredi.Controller;

import android.content.Context;
import android.util.Log;

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
        return db.modelAddDate(paciente, evento);
    }

    public ArrayList<AgendaClass> printAllEventos(Paciente paciente){
        ArrayList<AgendaClass> consultas = db.modelGetAllAgendas(paciente);
        Log.d("Consultas do ", paciente.get_nome());
        for(int i=0;i<consultas.size();i++){
            for(int j=0;j<consultas.size()-1;j++){
                int com = consultas.get(j).getDate().compareTo(consultas.get(j+1).getDate());
                if(com == -1){
                    Collections.swap(consultas, j,j+1);
                } else if(com == 0){
                    int com2 = consultas.get(j).getTime().compareTo(consultas.get(j+1).getTime());
                    if(com2 == -1){
                        Collections.swap(consultas, j, j+1);
                    }
                }
            }
        }
        for(int i=0;i<consultas.size();i++){
            Log.d("Consulta : ", consultas.get(i).getTitulo());
            Log.d(consultas.get(i).getDate(), consultas.get(i).getTime());
        }

        return consultas;
    }

}
