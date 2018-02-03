package app.com.example.wagner.meupredi.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import app.com.example.wagner.meupredi.Model.ModelClass.Paciente;

/**
 * Created by tico_ on 31/01/2018.
 */

public class ModelPeso extends SQLiteOpenHelper {

    // BANCO DE PESOS (LINKADO AO BANCO DE PACIENTES POR ID)
    private static final String TABLE_PESOS = "pesos";

    private static final String TABLE_PACIENTES = "pacientes";
    private static final String KEY_ID = "idAccount";


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Banco";


    // COLUNAS DO BANCO DE PESOS
    private static final String KEY_ID_PESO = "idPeso";
    private static final String KEY_PESO = "peso";
    private static final String KEY_DATA = "dataPeso";
    private static final String KEY_PAC = "pac";

    public ModelPeso(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String  CREATE_PESOS_TABLE = "CREATE TABLE IF NOT EXISTS "
                + TABLE_PESOS
                + "("
                + KEY_ID_PESO + " INTEGER PRIMARY KEY,"
                + KEY_PESO + " REAL,"
                + KEY_DATA + " DATETIME,"
                + KEY_PAC + " INTEGER,"
                + " FOREIGN KEY("+KEY_PAC+") REFERENCES "+TABLE_PACIENTES+"("+KEY_ID+"));";
        db.execSQL(CREATE_PESOS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + TABLE_PESOS);

        onCreate(db);
    }

    public void  ModelAtualizarPeso(Paciente paciente){

        //pega data atual
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = sdf.format(date);
        Log.d("Data : ", dateString);

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values;

        //guarda dados do paciente (peso e data atual)
        values = new ContentValues();
        values.put(KEY_PESO, paciente.get_peso());
        values.put(KEY_DATA, dateString);
        values.put(KEY_PAC, paciente.get_id());

        //insere dados no banco de pesos
        long retorno;
        retorno = db.insert(TABLE_PESOS, null, values);
        db.close();

        if(retorno == -1){
            Log.d("Erro ao atualizar peso!", "DatabaseHandler");
        } else {
            Log.d("Peso atualizado!", "DatabaseHandler");
        }
    }

    public double ModelGetPeso(Paciente paciente){
        int id = paciente.get_id();

        double peso = 0;

        String selectQuery = "SELECT * FROM " + TABLE_PESOS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        //procura o peso pelo id do paciente
        if(cursor.moveToFirst()){
            do{
                if(cursor.getString(3).equals(String.valueOf(id))){
                    peso = Double.parseDouble(cursor.getString(1));
                    Log.d("Peso achado : ", String.valueOf(peso));
                }
            } while(cursor.moveToNext());
        }

        //retorna peso atual (ou 0 se nao encontrou/ainda nao cadastrou)
        return peso;

    }

    public ArrayList<Float> ModelGetAllPesos(Paciente paciente){
        int idPaciente = paciente.get_id();
        ArrayList<Float> pesos = new ArrayList<>();
        Log.d("DB, ", "GetAllPesos");

        String selectQuery  = "SELECT * FROM " + TABLE_PESOS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                if(Integer.parseInt(cursor.getString(3))==idPaciente){
                    pesos.add(Float.valueOf(cursor.getString(1)));
                }
            } while(cursor.moveToNext());
        }

        return pesos;
    }
}
