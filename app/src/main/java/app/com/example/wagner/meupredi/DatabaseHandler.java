package app.com.example.wagner.meupredi;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wagne on 31/03/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Banco";

    // BANCO DE PACIENTES
    private static final String TABLE_PACIENTES = "pacientes";

    // COLUNAS DO BANCO DE PACIENTES
    private static final String KEY_ID = "idAccount";
    private static final String KEY_NOME = "nome";
    private static final String KEY_SENHA = "senha";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_SEXO = "sexo";
    private static final String KEY_IDADE = "idade";
    private static final String KEY_CIRCUNFERENCIA = "circunferencia";
    private static final String KEY_ALTURA = "altura";
    private static final String KEY_IMC = "imc";
    private static final String KEY_HBA1C = "hba1c";
    private static final String KEY_GLICOSEJEJUM = "glicosejejum";
    private static final String KEY_GLICOSE75G = "glicose75g";

    // BANCO DE PESOS (LINKADO AO BANCO DE PACIENTES POR ID)
    private static final String TABLE_PESOS = "pesos";

    // COLUNAS DO BANCO DE PESOS
    private static final String KEY_ID_PESO = "idPeso";
    private static final String KEY_PESO = "peso";
    private static final String KEY_DATA = "dataPeso";
    private static final String KEY_PAC = "pac";


    public DatabaseHandler(Context context) {
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
                + KEY_IDADE + " INTEGER,"
                + KEY_CIRCUNFERENCIA + " REAL,"
                + KEY_ALTURA + " REAL,"
                + KEY_IMC + " REAL,"
                + KEY_HBA1C + " REAL,"
                + KEY_GLICOSEJEJUM + " REAL,"
                + KEY_GLICOSE75G + " REAL"
                + ")";
        db.execSQL(CREATE_PACIENTES_TABLE);

        String  CREATE_PESOS_TABLE = "CREATE TABLE IF NOT EXISTS "
                + TABLE_PESOS
                + "("
                + KEY_ID_PESO + " INTEGER PRIMARY KEY,"
                + KEY_PESO + " REAL,"
                + KEY_DATA + " TEXT,"
                + KEY_PAC + " INTEGER,"
                + " FOREIGN KEY ("+KEY_PAC+") REFERENCES "+TABLE_PACIENTES+"("+KEY_ID+"));";
        db.execSQL(CREATE_PESOS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // TODO: fazer método onUpgrade para a tabela de pesos
        db.execSQL("DROP TABLE IF EXIST " + TABLE_PACIENTES);

        onCreate(db);
    }

    String addPaciente(Paciente paciente){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        Log.d("Adicionando: ", "método addPaciente");
        Log.d("Nome : ", paciente.get_nome());
        Log.d("Senha : ", paciente.get_senha());
        Log.d("Email: ", paciente.get_email());
        Log.d("Sexo: ", String.valueOf(paciente.get_sexo()));
        Log.d("Idade : ", String.valueOf(paciente.get_idade()));
        Log.d("Circunferencia : ", String.valueOf(paciente.get_circunferencia()));
        Log.d("Peso : ", String.valueOf(paciente.get_peso()));
        Log.d("Peso anterior: ", String.valueOf(paciente.get_pesoAnterior()));
        Log.d("Altura : ", String.valueOf(paciente.get_altura()));
        Log.d("IMC : ", String.valueOf(paciente.get_imc()));
        Log.d("HBA1C : ", String.valueOf(paciente.get_hba1c()));
        Log.d("GlicoseJejum : ", String.valueOf(paciente.get_glicosejejum()));
        Log.d("Glicose75g : ", String.valueOf(paciente.get_glicose75g()));

        // TODO: 10/05/2017 Lembrar de mudar todos os métodos para controle de peso
        // TODO: 11/05/2017 Setar peso e pesoAnterior na tabela de pesos

        values.put(KEY_NOME, paciente.get_nome());
        values.put(KEY_SENHA, paciente.get_senha());
        values.put(KEY_EMAIL, paciente.get_email());
        values.put(KEY_SEXO, paciente.get_sexo());
        values.put(KEY_IDADE, paciente.get_idade());
        values.put(KEY_CIRCUNFERENCIA, paciente.get_circunferencia());
        values.put(KEY_ALTURA, paciente.get_altura());
        values.put(KEY_IMC, paciente.get_imc());
        values.put(KEY_HBA1C, paciente.get_hba1c());
        values.put(KEY_GLICOSEJEJUM, paciente.get_glicosejejum());
        values.put(KEY_GLICOSE75G, paciente.get_glicose75g());

        long retorno;
        retorno = db.insert(TABLE_PACIENTES, null, values);
        db.close();

        if(retorno == -1){
            return "Erro ao inserir o registro!";
        } else {
            return "Registro inserido com sucesso!";
        }
    }

    public List<Paciente> getAllPacientes(){

        List<Paciente> pacientesList = new ArrayList<Paciente> ();

        String selectQuery = "SELECT * FROM " + TABLE_PACIENTES;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //TODO: tratar NumberFormatException

        if(cursor.moveToFirst()){
            do{
                Paciente paciente = new Paciente();
                paciente.set_id(Integer.parseInt(cursor.getString(0)));
                paciente.set_nome(cursor.getString(1));
                paciente.set_senha(cursor.getString(2));
                paciente.set_email(cursor.getString(3));
                paciente.set_sexo(cursor.getString(4));
                paciente.set_idade(Integer.parseInt(cursor.getString(5)));
                paciente.set_circunferencia(Double.parseDouble(cursor.getString(6)));
                paciente.set_altura(Double.parseDouble(cursor.getString(7)));
                paciente.set_imc(Double.parseDouble(cursor.getString(8)));
                paciente.set_hba1c(Double.parseDouble(cursor.getString(9)));
                paciente.set_glicosejejum(Double.parseDouble(cursor.getString(10)));
                paciente.set_glicose75g(Double.parseDouble(cursor.getString(11)));

                //TODO: pegar pesos da tabela de pesos e setar no objeto paciente

                pacientesList.add(paciente);

            }while(cursor.moveToNext());
        }
        return pacientesList;
    }

    public Paciente verificarLogin(String email, String senha){

        String selectQuery = "SELECT * FROM " + TABLE_PACIENTES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        Paciente paciente = new Paciente();

        if(cursor.moveToFirst()){
            do{
                if(cursor.getString(3).equals(email) && cursor.getString(2).equals(senha)){
                    paciente.set_id(Integer.parseInt(cursor.getString(0)));
                    paciente.set_nome(cursor.getString(1));
                    paciente.set_senha(cursor.getString(2));
                    paciente.set_email(cursor.getString(3));
                    paciente.set_sexo(cursor.getString(4));
                    paciente.set_idade(Integer.parseInt(cursor.getString(5)));
                    paciente.set_circunferencia(Double.parseDouble(cursor.getString(6)));
                    paciente.set_altura(Double.parseDouble(cursor.getString(7)));
                    paciente.set_imc(Double.parseDouble(cursor.getString(8)));
                    paciente.set_hba1c(Double.parseDouble(cursor.getString(9)));
                    paciente.set_glicosejejum(Double.parseDouble(cursor.getString(10)));
                    paciente.set_glicose75g(Double.parseDouble(cursor.getString(11)));

                    //TODO: pegar pesos da tabela de pesos e setar no objeto paciente

                    Log.d("Infos do banco: ", "databasehandler");
                    Log.d("Nome : ", paciente.get_nome());
                    Log.d("Senha : ", paciente.get_senha());
                    Log.d("Email: ", paciente.get_email());
                    Log.d("Sexo: ", paciente.get_sexo());
                    Log.d("Idade : ", String.valueOf(paciente.get_idade()));
                    Log.d("Circunferencia : ", String.valueOf(paciente.get_circunferencia()));
                    Log.d("Peso : ", String.valueOf(paciente.get_peso()));
                    Log.d("Peso anterior :", String.valueOf(paciente.get_pesoAnterior()));
                    Log.d("Altura : ", String.valueOf(paciente.get_altura()));
                    Log.d("IMC : ", String.valueOf(paciente.get_imc()));
                    Log.d("HBA1C : ", String.valueOf(paciente.get_hba1c()));
                    Log.d("GlicoseJejum : ", String.valueOf(paciente.get_glicosejejum()));
                    Log.d("Glicose75g : ", String.valueOf(paciente.get_glicose75g()));

                    return paciente;
                }

            }while(cursor.moveToNext());

        }

        paciente.set_id(-1);
        return paciente;
    }

    public Paciente verificarEmail(String email) {

        String selectQuery = "SELECT * FROM " + TABLE_PACIENTES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        Paciente paciente = new Paciente();

        if(cursor.moveToFirst()){
            do{
                if(cursor.getString(3).equals(email)){
                    paciente.set_id(Integer.parseInt(cursor.getString(0)));
                    paciente.set_nome(cursor.getString(1));
                    paciente.set_senha(cursor.getString(2));

                    return paciente;
                }

            }while(cursor.moveToNext());

        }

        paciente.set_id(-1);
        return paciente;
    }

    public void deleteAllPacientes() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "DELETE FROM " + TABLE_PACIENTES;

        db.execSQL(selectQuery);
        db.close();
    }

    public boolean atualizarPaciente(Paciente paciente){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values;
        String where =  this.KEY_ID + "=" + String.valueOf(paciente.get_id());

        values = new ContentValues();

        values.put(KEY_NOME, paciente.get_nome());
        values.put(KEY_SENHA, paciente.get_senha());
        values.put(KEY_EMAIL, paciente.get_email());
        values.put(KEY_SEXO, paciente.get_sexo());
        values.put(KEY_IDADE, paciente.get_idade());
        values.put(KEY_CIRCUNFERENCIA, paciente.get_circunferencia());
        values.put(KEY_ALTURA, paciente.get_altura());
        values.put(KEY_IMC, paciente.get_imc());
        values.put(KEY_HBA1C, paciente.get_hba1c());
        values.put(KEY_GLICOSEJEJUM, paciente.get_glicosejejum());
        values.put(KEY_GLICOSE75G, paciente.get_glicose75g());

        // TODO: dar put nos pesos do objeto, para atualizar a tabela de pesos

        return db.update(this.TABLE_PACIENTES, values, where, null) > 0;
        // db.close();
    }

}
