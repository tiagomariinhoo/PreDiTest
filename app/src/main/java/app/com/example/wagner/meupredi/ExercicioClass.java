package app.com.example.wagner.meupredi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Pichau on 06/07/2017.
 */

public class ExercicioClass {
    int idPaciente;
    Date data;
    int tempo;

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Date getData() {
        return data;
    }

    public void setData(String data2) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date data = (Date) format.parse(data2);
        this.data = data;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }
}
