package app.com.example.wagner.meupredi.Controller;

import android.content.Context;

import java.text.ParseException;
import java.util.ArrayList;

import app.com.example.wagner.meupredi.Model.ModelClass.ExercicioClass;
import app.com.example.wagner.meupredi.Model.ModelClass.Paciente;
import app.com.example.wagner.meupredi.Model.ModelExercicio;

/**
 * Created by tico_ on 31/01/2018.
 */

public class ControllerExercicios {
    private ModelExercicio modelExercicio;

    public ControllerExercicios(Context context) {
        modelExercicio = new ModelExercicio(context);
    }

    public String addExercicio(int tempo, String exercicio, Paciente paciente){
       return modelExercicio.ModelAddExercicio(tempo, exercicio, paciente);
    }

    public ArrayList<ExercicioClass> getAllExercicios(Paciente paciente) throws ParseException {
        return modelExercicio.ModelGetAllExercicios(paciente);
    }

}
