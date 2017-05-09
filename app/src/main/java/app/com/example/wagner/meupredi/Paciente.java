package app.com.example.wagner.meupredi;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.Serializable;

/**
 * Created by wagne on 31/03/2017.
 */

public class Paciente extends AppCompatActivity implements Serializable {

    int _id;
    String _nome;
    String _senha;
    String _email;
    int _idade;
    double _circunferencia;
    double _peso;
    double _altura;
    double _pesoAtual;
    double _imc;
    double _hba1c;
    double _glicosejejum;
    double _glicose75g;

    public Paciente(){

    }

    public Paciente(int id, String nome, String senha, String email, int idade, double circunferencia, double peso, double altura){

        this._id = id;
        this._nome = nome;
        this._senha = senha;
        this._email = email;
        this._idade = idade;
        this._circunferencia = circunferencia;
        this._peso = -1;
        this._altura = altura;
        this._pesoAtual = peso;
        this._imc = -1;
        this._hba1c = -1;
        this._glicose75g = -1;
        this._glicosejejum = -1;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_nome() {
        return _nome;
    }

    public void set_nome(String _nome) {
        this._nome = _nome;
    }

    public String get_senha() {
        return _senha;
    }

    public void set_senha(String _senha) {
        this._senha = _senha;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public int get_idade() {
        return _idade;
    }

    public void set_idade(int _idade) {
        this._idade = _idade;
    }

    public double get_circunferencia() {
        return _circunferencia;
    }

    public void set_circunferencia(double _circunferencia) {
        this._circunferencia = _circunferencia;
    }

    public double get_peso() {
        return _peso;
    }

    public void set_peso(double _peso) {
        this._peso = _peso;
    }

    public double get_altura() {
        return _altura;
    }

    public void set_altura(double _altura) {
        this._altura = _altura;
    }

    public double get_pesoAtual() {
        return _pesoAtual;
    }

    public void set_pesoAtual(double _pesoAtual) {
        this._pesoAtual = _pesoAtual;
    }

    public double get_imc() {
        return _imc;
    }

    public void set_imc(double _imc) {
        this._imc = _imc;
    }

    public double get_hba1c() {
        return _hba1c;
    }

    public void set_hba1c(double _hba1c) {
        this._hba1c = _hba1c;
    }

    public double get_glicosejejum() {
        return _glicosejejum;
    }

    public void set_glicosejejum(double _glicosejejum) {
        this._glicosejejum = _glicosejejum;
    }

    public double get_glicose75g() {
        return _glicose75g;
    }

    public void set_glicose75g(double _glicose75g) {
        this._glicose75g = _glicose75g;
    }

    public void calculo_diabetes(Context context){

    Log.d("Começando ", "O CALCULOO");
        if(get_glicosejejum()>=100 && get_glicosejejum()<=125){
            //Log.d("TTG!","");
            Toast.makeText(context,"TTG",Toast.LENGTH_LONG).show();
            if(get_glicose75g()<140){
                //Log.d("GJA","");
                Toast.makeText(context,"GJA!",Toast.LENGTH_LONG).show();
            } else if (get_glicose75g()>=140 && get_glicose75g()<199){
                //Log.d("TDG"," Pré Diabetes");
                //Log.d("MEV", "Por 6 meses");
                Toast.makeText(context,"TDG Pré Diabetes, MEV por 6 meses!",Toast.LENGTH_LONG).show();

                if(get_peso()!=-1 && get_imc()>=25){
                    double pct = (get_pesoAtual()*100)/get_peso();
                    pct = 100-pct;

                    boolean metas;

                    if(pct>5) metas = true;
                    else metas = false;

                    if(!metas){
                        boolean risco=false;

                        if(get_imc()>=25 && get_glicose75g()>=200 && get_glicosejejum()>=200) risco = true;

                        if(!risco){
                            Log.d("Reforçar","MEV por 6 meses");
                            Toast.makeText(context,"Reforçar MEV por 6 meses",Toast.LENGTH_LONG).show();
                            boolean metas2=false;

                            // TODO: 09/05/2017 Verificar esse metas2 pois será a parte do paciente de risco
                            if(!metas2){
                                Log.d("MEV+","Metformina");
                            } else {
                                Log.d("Acompanhamento","A cada 6 meses");
                            }
                        } else {
                            Toast.makeText(context,"Você está correndo risco! Acompanhamento a cada 6 meses com medida do HbA1c",Toast.LENGTH_LONG).show();
                        }
                    } else {
                        //Log.d("Acompanhamento", "A cada 6 meses com rastreamento anual");
                        Toast.makeText(context,"Parabéns por conseguir perder mais do que 5% do seu peso! A cada 6 meses com rastreamento anual!",Toast.LENGTH_LONG).show();
                    }

                }

            } else if (get_glicose75g() >= 200){
                //Log.d("DM2 : ", "Avaliação e manejo do DM2");
                Toast.makeText(context,"Sua glicose está muito alta! Avaliação e manejo do DM2",Toast.LENGTH_LONG).show();
            }
        } else if (get_glicosejejum()>=126 || get_glicosejejum()>=200){
            Toast.makeText(context,"Sua glicose está muito alta! Avaliação de manejo do DM2",Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context,"Sua glicose está normal! Avaliação a cada 3 anos ou conforme o risco.",Toast.LENGTH_LONG).show();
        }

    Log.d("glicose75 g : ", String.valueOf(get_glicose75g()));
    }





}