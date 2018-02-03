package app.com.example.wagner.meupredi.Controller;

import android.content.Context;

import java.util.ArrayList;

import app.com.example.wagner.meupredi.Model.ModelClass.Paciente;
import app.com.example.wagner.meupredi.Model.ModelPeso;

/**
 * Created by tico_ on 31/01/2018.
 */

public class ControllerPeso {
    ModelPeso modelPeso;

    public ControllerPeso(Context context) {
        modelPeso = new ModelPeso(context);
    }

    public void atualizarPeso(Paciente paciente){
        modelPeso.ModelAtualizarPeso(paciente);
    }

    public double getPeso(Paciente paciente){
        return modelPeso.ModelGetPeso(paciente);
    }

    public ArrayList<Float> getAllPesos(Paciente paciente){
        return modelPeso.ModelGetAllPesos(paciente);
    }
}
