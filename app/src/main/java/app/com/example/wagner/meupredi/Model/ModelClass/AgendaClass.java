package app.com.example.wagner.meupredi.Model.ModelClass;

import java.util.Date;

/**
 * Created by LeandroDias1 on 05/03/2018.
 */

public class AgendaClass {
    private String titulo;
    private String local;
    private Date date;

    public AgendaClass(String titulo, String local, Date date) {
        this.titulo = titulo;
        this.local = local;
        this.date = date;
    }

    public AgendaClass(){

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
