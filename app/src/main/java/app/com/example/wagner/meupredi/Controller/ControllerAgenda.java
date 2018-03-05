package app.com.example.wagner.meupredi.Controller;

import app.com.example.wagner.meupredi.Model.DatabaseHandler;
import app.com.example.wagner.meupredi.Model.ModelClass.AgendaClass;
import app.com.example.wagner.meupredi.Model.ModelClass.Paciente;

/**
 * Created by LeandroDias1 on 05/03/2018.
 */

public class ControllerAgenda {
    DatabaseHandler db;

    public String adicionarEvento(Paciente paciente, AgendaClass evento){
        return db.addAgenda(paciente, evento);
    }

}
