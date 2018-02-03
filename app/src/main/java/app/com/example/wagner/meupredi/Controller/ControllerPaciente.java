package app.com.example.wagner.meupredi.Controller;

import android.content.Context;

import java.util.List;

import app.com.example.wagner.meupredi.Model.ModelClass.Paciente;
import app.com.example.wagner.meupredi.Model.ModelPaciente;

/**
 * Created by tico_ on 31/01/2018.
 */

public class ControllerPaciente {
    private ModelPaciente modelPaciente;

    public ControllerPaciente(Context context) {
        modelPaciente = new ModelPaciente(context);
    }

    public String addPaciente(Paciente paciente){
        return modelPaciente.ModelAddPaciente(paciente);
    }

    public List<Paciente> getAllUsers(){
      return  modelPaciente.ModelGetAllUsers();
    }

    public Paciente getPaciente(String email){
        return modelPaciente.ModelGetPaciente(email);
    }

    public void deleteAllPacientes(){
        modelPaciente.ModelDeleteAllPacientes();
    }

    public Paciente verificarLogin(String email, String senha){
        return modelPaciente.ModelVerificarLogin(email, senha);
    }

    public Paciente verificarEmail(String email){
        return modelPaciente.ModelVerificarEmail(email);
    }

    public boolean atualizarPaciente(Paciente paciente){
        return modelPaciente.ModelAtualizarPaciente(paciente);
    }

}
