package app.com.example.wagner.meupredi.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import app.com.example.wagner.meupredi.Controller.ControllerExames;
import app.com.example.wagner.meupredi.Controller.ControllerPeso;
import app.com.example.wagner.meupredi.Model.ModelClass.Paciente;

/**
 * Created by tico_ on 31/01/2018.
 */

public class ModelPaciente extends SQLiteOpenHelper {

    ControllerExames controllerExames;
    ControllerPeso controllerPeso;


    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "Banco";

    // BANCO DE PACIENTES
    private static final String TABLE_PACIENTES = "pacientes";
    private static final String TABLE_PESOS = "pesos";

    // COLUNAS DO BANCO DE PACIENTES
    private static final String KEY_ID = "idAccount";
    private static final String KEY_NOME = "nome";
    private static final String KEY_SENHA = "senha";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_SEXO = "sexo";
    private static final String KEY_NASCIMENTO = "nascimento";
    private static final String KEY_IDADE = "idade";
    private static final String KEY_CIRCUNFERENCIA = "circunferencia";
    private static final String KEY_ALTURA = "altura";
    private static final String KEY_IMC = "imc";
    private static final String KEY_DIA = "diaAtual";
    private static final String KEY_DIA_INICIO = "diaInicio";
    private static final String KEY_SEM_MAX = "semMax";


    public ModelPaciente(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String  CREATE_PACIENTES_TABLE = "CREATE TABLE IF NOT EXISTS "
                + TABLE_PACIENTES
                + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NOME + " TEXT,"
                + KEY_SENHA + " TEXT,"
                + KEY_EMAIL + " TEXT,"
                + KEY_SEXO + " TEXT,"
                + KEY_NASCIMENTO + " TEXT,"
                + KEY_IDADE + " INTEGER,"
                + KEY_CIRCUNFERENCIA + " REAL,"
                + KEY_ALTURA + " REAL,"
                + KEY_IMC + " REAL,"
                + KEY_DIA + " INTEGER,"
                + KEY_DIA_INICIO + " INTEGER"
                + ")";
        db.execSQL(CREATE_PACIENTES_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PACIENTES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PESOS);
        onCreate(db);
    }

    public String ModelAddPaciente(Paciente paciente){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        //DEBUG: imprime dados do objeto paciente
        Log.d("Adicionando: ", "método addPaciente");
        Log.d("Nome : ", paciente.get_nome());
        Log.d("Senha : ", paciente.get_senha());
        Log.d("Email: ", paciente.get_email());
        Log.d("Sexo: ", String.valueOf(paciente.get_sexo()));
        Log.d("Nascimento: ", paciente.get_nascimento());
        Log.d("Idade : ", String.valueOf(paciente.get_idade()));
        Log.d("Circunferencia : ", String.valueOf(paciente.get_circunferencia()));
        Log.d("Peso : ", String.valueOf(paciente.get_peso()));
        Log.d("Altura : ", String.valueOf(paciente.get_altura()));
        Log.d("IMC : ", String.valueOf(paciente.get_imc()));
        Log.d("HBA1C : ", String.valueOf(paciente.get_hba1c()));
        Log.d("GlicoseJejum : ", String.valueOf(paciente.get_glicosejejum()));
        Log.d("Glicose75g : ", String.valueOf(paciente.get_glicose75g()));
        Log.d("Colesterol : ", String.valueOf(paciente.get_colesterol()));

        //agrupa dados e insere no banco
        values.put(KEY_NOME, paciente.get_nome());
        values.put(KEY_SENHA, paciente.get_senha());
        values.put(KEY_EMAIL, paciente.get_email());
        values.put(KEY_SEXO, paciente.get_sexo());
        values.put(KEY_NASCIMENTO, String.valueOf(paciente.get_nascimento()));
        values.put(KEY_IDADE, paciente.get_idade());
        values.put(KEY_CIRCUNFERENCIA, paciente.get_circunferencia());
        values.put(KEY_ALTURA, paciente.get_altura());
        values.put(KEY_IMC, paciente.get_imc());
        values.put(KEY_DIA, paciente.getDia());
        values.put(KEY_DIA_INICIO, paciente.getDiaInicio());

        long retorno;
        retorno = db.insert(TABLE_PACIENTES, null, values);
        db.close();

        if(retorno == -1){
            return "Erro ao inserir o registro!";
        } else {
            Log.d("Paciente  : ", "Inserido com sucesso!");
            return "Registro inserido com sucesso!";
        }
    }

    public List<Paciente> ModelGetAllUsers(Context context){
        List<Paciente> pacientesList = new ArrayList<Paciente>();

        controllerExames = new ControllerExames(context);

        controllerPeso = new ControllerPeso(context);

        //pega todos os dados do banco de pacientes
        String selectQuery = "SELECT * FROM " + TABLE_PACIENTES;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //adiciona, um por um, a uma lista
        if(cursor.moveToFirst()){
            do{
                Paciente paciente = new Paciente();
                paciente.set_id(Integer.parseInt(cursor.getString(0)));
                paciente.set_nome(cursor.getString(1));
                paciente.set_senha(cursor.getString(2));
                paciente.set_email(cursor.getString(3));
                paciente.set_sexo(cursor.getString(4));
                paciente.set_nascimento(cursor.getString(5));
                paciente.set_idade(Integer.parseInt(cursor.getString(6)));
                paciente.set_circunferencia(Double.parseDouble(cursor.getString(7)));
                paciente.set_altura(Double.parseDouble(cursor.getString(8)));
                paciente.set_imc(Double.parseDouble(cursor.getString(9)));
                paciente.setDia(Integer.parseInt(cursor.getString(10)));
                paciente.setDiaInicio(Integer.parseInt(cursor.getString(11)));
                Log.d("Paciente, GetAllUsers : ", paciente.get_nome());
                //pega seu ultimo peso registrado
                paciente.set_peso(controllerPeso.getPeso(paciente));
                //paciente.set_peso(0);
                //pega suas ultimas taxas cadastradas
                paciente = controllerExames.getUltimasTaxas(paciente);

                pacientesList.add(paciente);

            }while(cursor.moveToNext());
        }
        return pacientesList;
    }

    public Paciente ModelGetPaciente(String email, Context context){
        List<Paciente> pacientesList = ModelGetAllUsers(context);
        Paciente paciente = new Paciente();

        //procura paciente desejado
        for(int i = 0; i < pacientesList.size(); i++) {
            if(email.equals(pacientesList.get(i).get_email())) {
                paciente = pacientesList.get(i);
                break;
            }
        }

        return paciente;
    }

    public void ModelDeleteAllPacientes(){
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "DELETE FROM " + TABLE_PACIENTES;

        db.execSQL(selectQuery);
        db.close();
    }

    public Paciente ModelVerificarLogin(String email, String senha){
        //pega todos os pacientes
        String selectQuery = "SELECT * FROM " + TABLE_PACIENTES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        Paciente paciente = new Paciente();

        //verifica as credenciais
        if(cursor.moveToFirst()){
            do{
                if(cursor.getString(3).equals(email) && cursor.getString(2).equals(senha)){
                    paciente.set_id(Integer.parseInt(cursor.getString(0)));
                    paciente.set_nome(cursor.getString(1));
                    paciente.set_senha(cursor.getString(2));
                    paciente.set_email(cursor.getString(3));
                    paciente.set_sexo(cursor.getString(4));
                    paciente.set_nascimento(cursor.getString(5));
                    paciente.set_idade(Integer.parseInt(cursor.getString(6)));
                    paciente.set_circunferencia(Double.parseDouble(cursor.getString(7)));
                    paciente.set_altura(Double.parseDouble(cursor.getString(8)));
                    paciente.set_imc(Double.parseDouble(cursor.getString(9)));
                    paciente.setDia(Integer.parseInt(cursor.getString(10)));
                    paciente.setDiaInicio(Integer.parseInt(cursor.getString(11)));

                    //DEBUG
                    Log.d("Infos do banco: ", "verificarLogin");
                    Log.d("Nome : ", paciente.get_nome());
                    Log.d("Senha : ", paciente.get_senha());
                    Log.d("Email: ", paciente.get_email());
                    Log.d("Sexo: ", String.valueOf(paciente.get_sexo()));
                    Log.d("Nascimento: ", paciente.get_nascimento());
                    Log.d("Idade : ", String.valueOf(paciente.get_idade()));
                    Log.d("Circunferencia : ", String.valueOf(paciente.get_circunferencia()));
                    Log.d("Peso : ", String.valueOf(paciente.get_peso()));
                    Log.d("Altura : ", String.valueOf(paciente.get_altura()));
                    Log.d("IMC : ", String.valueOf(paciente.get_imc()));
                    Log.d("HBA1C : ", String.valueOf(paciente.get_hba1c()));
                    Log.d("GlicoseJejum : ", String.valueOf(paciente.get_glicosejejum()));
                    Log.d("Glicose75g : ", String.valueOf(paciente.get_glicose75g()));
                    Log.d("Colesterol : ", String.valueOf(paciente.get_colesterol()));
                    Log.d("Dia atual : " , String.valueOf(paciente.getDia()));
                    Log.d("Dia inicio : " , String.valueOf(paciente.getDiaInicio()));

                    //se encontrou o paciente correto, retorna objeto
                    return paciente;
                }

            }while(cursor.moveToNext());

        }

        paciente.set_id(-1);
        return paciente;
    }

    public Paciente ModelVerificarEmail(String email){
        String selectQuery = "SELECT * FROM " + TABLE_PACIENTES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        Paciente paciente = new Paciente();

        //verifica se existe algum usuario com o mesmo email
        if(cursor.moveToFirst()){
            do{
                if(cursor.getString(3).equals(email)){
                    paciente.set_id(Integer.parseInt(cursor.getString(0)));
                    paciente.set_nome(cursor.getString(1));
                    paciente.set_senha(cursor.getString(2));

                    //se existir, retorna id do mesmo
                    return paciente;
                }

            }while(cursor.moveToNext());
        }

        //se nao existir, retorna objeto com id igual a -1
        paciente.set_id(-1);
        return paciente;
    }

    public boolean ModelAtualizarPaciente(Paciente paciente){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values;
        String where =  this.KEY_ID + "=" + String.valueOf(paciente.get_id());

        values = new ContentValues();

        values.put(KEY_NOME, paciente.get_nome());
        values.put(KEY_SENHA, paciente.get_senha());
        values.put(KEY_EMAIL, paciente.get_email());
        values.put(KEY_SEXO, paciente.get_sexo());
        values.put(KEY_NASCIMENTO, paciente.get_nascimento());
        values.put(KEY_IDADE, paciente.get_idade());
        values.put(KEY_CIRCUNFERENCIA, paciente.get_circunferencia());
        values.put(KEY_ALTURA, paciente.get_altura());
        values.put(KEY_IMC, paciente.get_imc());

        return db.update(this.TABLE_PACIENTES, values, where, null) > 0;
    }

}
