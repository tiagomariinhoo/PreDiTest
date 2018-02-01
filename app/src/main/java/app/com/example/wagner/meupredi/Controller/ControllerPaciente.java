package app.com.example.wagner.meupredi.Controller;

import app.com.example.wagner.meupredi.Model.ModelClass.Paciente;
import app.com.example.wagner.meupredi.Model.ModelPaciente;

/**
 * Created by tico_ on 31/01/2018.
 */

public class ControllerPaciente {
    private ModelPaciente modelPaciente;

    public ControllerPaciente() {
    }

    public void ControllerAddPaciente(Paciente paciente){
        modelPaciente.ModelAddPaciente(paciente);
    }
}
