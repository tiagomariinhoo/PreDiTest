package app.com.example.wagner.meupredi.Controller;

import android.content.Context;

import java.text.ParseException;
import java.util.List;

import app.com.example.wagner.meupredi.Model.ModelClass.ExameClass;
import app.com.example.wagner.meupredi.Model.ModelClass.HemogramaClass;
import app.com.example.wagner.meupredi.Model.ModelClass.LipidogramaClass;
import app.com.example.wagner.meupredi.Model.ModelClass.Paciente;
import app.com.example.wagner.meupredi.Model.ModelExames;

/**
 * Created by tico_ on 31/01/2018.
 */

public class ControllerExames {
    ModelExames modelExames;

    public ControllerExames(Context context) {
        modelExames = new ModelExames(context);
    }

    public String addExames(ExameClass exame){
        return modelExames.ModelAddExame(exame);
    }

    public String addLipidograma(LipidogramaClass lipidograma){
        return modelExames.ModelAddLipidograma(lipidograma);
    }

    public String addHemograma(HemogramaClass hemograma){
        return modelExames.ModelAddHemograma(hemograma);
    }

    public List<ExameClass> getAllExames() throws ParseException {
        return modelExames.ModelGetAllExames();
    }

    public void atualizarTaxas(Paciente paciente){
        modelExames.ModelAtualizarTaxas(paciente);
    }

    public Paciente getUltimasTaxas(Paciente paciente){
        return modelExames.ModelGetUltimasTaxas(paciente);
    }

}
