package app.com.example.wagner.meupredi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wagne on 31/03/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABA_NAME = "Banco";

    // LINHAS DO BANCO
    private static final String TABLE_PACIENTES = "pacientes";

    // COLUNAS DO BANCO
    private static final String KEY_ID = "idAccount";
    private static final String KEY_NOME = "nome";
    private static final String KEY_SENHA = "senha";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_IDADE = "idade";
    private static final String KEY_CIRCUNFERENCIA = "circunferencia";
    private static final String KEY_PESO = "peso";
    private static final String KEY_ALTURA = "altura";


    public DatabaseHandler(Context context) {
        super(context, DATABA_NAME, null, DATABASE_VERSION);
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
                + KEY_IDADE + " INTEGER,"
                + KEY_CIRCUNFERENCIA + " REAL,"
                + KEY_PESO + " REAL,"
                + KEY_ALTURA + " REAL"
                + ")";
        db.execSQL(CREATE_PACIENTES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXIST " + TABLE_PACIENTES);

        onCreate(db);
    }

    String addPaciente(Paciente paciente){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NOME, paciente.get_nome());
        values.put(KEY_SENHA, paciente.get_senha());
        values.put(KEY_EMAIL, paciente.get_email());
        values.put(KEY_IDADE, paciente.get_idade());
        values.put(KEY_CIRCUNFERENCIA, paciente.get_circunferencia());
        values.put(KEY_PESO, paciente.get_peso());
        values.put(KEY_ALTURA, paciente.get_altura());

        long retorno;
        retorno = db.insert(TABLE_PACIENTES, null, values);
        db.close();

        if(retorno == -1){
            return "Erro ao inserir o registro!";
        } else {
            return "Registro inserido com sucesso!";
        }
    }

    public Paciente getPacient(int id){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_PACIENTES, new String[]{
                        KEY_ID,
                        KEY_NOME,
                        KEY_SENHA,
                        KEY_EMAIL,
                        KEY_IDADE,
                        KEY_CIRCUNFERENCIA,
                        KEY_PESO,
                        KEY_ALTURA}, KEY_ID + "=?",
                new String[]{ String.valueOf(id)},
                null, null, null, null);

                    if(cursor!= null) {
                        cursor.moveToFirst();
                    }
                    //Integer.parseInt(cursor.getString(0))
                    Paciente paciente = new Paciente(
                            cursor.getInt(cursor.getColumnIndex(KEY_ID)), // ID
                            cursor.getString(1), // NOME
                            cursor.getString(2), // SENHA
                            cursor.getString(3), // EMAIL
                            Integer.parseInt(cursor.getString(4)), // IDADE
                            Double.parseDouble(cursor.getString(5)), // PESO
                            Double.parseDouble(cursor.getString(6)), // CIRCUNFERENCIA
                            Double.parseDouble(cursor.getString(7)) // ALTURA
                    );

                return paciente;
    }

    public SQLiteDatabase abrirBanco(){
        SQLiteDatabase db = this.getWritableDatabase();

        return db;

    }



    public List<Paciente> getAllPacientes(){

        List<Paciente> pacientesList = new ArrayList<Paciente> ();

        String selectQuery = "SELECT * FROM " + TABLE_PACIENTES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                Paciente paciente = new Paciente();
                paciente.set_id(Integer.parseInt(cursor.getString(0)));
                paciente.set_nome(cursor.getString(1));
                paciente.set_senha(cursor.getString(2));
                paciente.set_email(cursor.getString(3));
                paciente.set_idade(Integer.parseInt(cursor.getString(4)));
                paciente.set_circunferencia(Double.parseDouble(cursor.getString(5)));
                paciente.set_peso(Double.parseDouble(cursor.getString(6)));
                paciente.set_altura(Double.parseDouble(cursor.getString(7)));

                pacientesList.add(paciente);

            }while(cursor.moveToNext());
        }
        return pacientesList;
    }

    public boolean verificarLogin(String email, String senha){

        String selectQuery = "SELECT * FROM " + TABLE_PACIENTES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                if(cursor.getString(3).equals(email) && cursor.getString(2).equals(senha)){
                    return true;
                }

            }while(cursor.moveToNext());

        }


        return false;
    }

    public void deletePaciente(Paciente paciente){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PACIENTES, KEY_ID + " = ?",
                new String[]{ String.valueOf(paciente.get_id())});
        db.close();
    }

    public void deleteAllPacientes() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "DELETE FROM " + TABLE_PACIENTES;

        db.execSQL(selectQuery);
        db.close();
    }
}
