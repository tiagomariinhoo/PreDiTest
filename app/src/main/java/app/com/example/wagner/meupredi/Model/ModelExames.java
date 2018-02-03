package app.com.example.wagner.meupredi.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import app.com.example.wagner.meupredi.Model.ModelClass.ExameClass;
import app.com.example.wagner.meupredi.Model.ModelClass.LipidogramaClass;
import app.com.example.wagner.meupredi.Model.ModelClass.HemogramaClass;
import app.com.example.wagner.meupredi.Model.ModelClass.Paciente;

/**
 * Created by tico_ on 31/01/2018.
 */

public class ModelExames extends SQLiteOpenHelper {



    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Banco";

    // TABLE EXAMES
    private static final String TABLE_EXAMES = "exames";

    private static final String TABLE_PACIENTES = "pacientes";
    private static final String KEY_ID = "idAccount";

    //COLUNAS DOS EXAMES
    private static final String KEY_ID_EXAME = "idExame";
    private static final String KEY_GLICOSEJEJUM = "glicosejejum";
    private static final String KEY_GLICOSE75G = "glicose75g";
    private static final String KEY_COLESTEROL = "colesterol";
    private static final String KEY_DATA_EXAME = "dataExame";
    private static final String KEY_PAC2 = "pac2";

    //TABLE LIPIDOGRAMA
    private static final String TABLE_LIPIDOGRAMA = "lipidograma";

    //COLUNAS DO LIPIDOGRAMA
    private static final String KEY_ID_LIPIDOGRAMA = "idLipidograma";
    private static final String KEY_HDL = "lipHdl";
    private static final String KEY_LDL = "lipLdl";
    private static final String KEY_COLESTEROLTOTAL = "lipColesterolTotal";
    private static final String KEY_TRIGLICERIDES = "lipTriglicerides";
    private static final String KEY_DATA_LIPIDOGRAMA = "dataLipidograma";
    private static final String KEY_LOCAL_LIPIDOGRAMA = "localLipidograma";
    private static final String KEY_PAC4 = "pac4";

    //TABLE HEMOGRAMA
    private static final String TABLE_HEMOGRAMA = "hemograma";

    //COLUNAS HEMOGRAMA
    private static final String KEY_ID_HEMOGRAMA = "idHemograma";
    private static final String KEY_HEMOGLOBINA = "hemHemoglobina";
    private static final String KEY_HEMATOCRITO = "hemHematocrito";
    private static final String KEY_VGM = "hemVgm"; // Vol Glob Medio
    private static final String KEY_CHCM = "hemChcm"; // Hem Glob Media
    private static final String KEY_CHGM = "hemChgm"; // C.H Glob Media
    private static final String KEY_RWD = "hemRwd";
    private static final String KEY_DATA_HEMOGRAMA = "dataHemograma";
    private static final String KEY_PAC5 = "pac5";



    public ModelExames(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EXAMES_TABLE = "CREATE TABLE IF NOT EXISTS "
                + TABLE_EXAMES
                + "("
                + KEY_ID_EXAME + " INTEGER PRIMARY KEY,"
                + KEY_GLICOSE75G + " REAL,"
                + KEY_GLICOSEJEJUM + " REAL,"
                + KEY_COLESTEROL + " REAL,"
                + KEY_DATA_EXAME + " DATETIME,"
                + KEY_PAC2 + " INTEGER,"
                + " FOREIGN KEY("+KEY_PAC2+") REFERENCES "+TABLE_PACIENTES+"("+KEY_ID+"));";
        db.execSQL(CREATE_EXAMES_TABLE);

        String CREATE_LIPIDOGRAMA_TABLE = "CREATE TABLE IF NOT EXISTS "
                + TABLE_LIPIDOGRAMA
                + "("
                + KEY_ID_LIPIDOGRAMA + " INTEGER PRIMARY KEY,"
                + KEY_HDL + " INTEGER,"
                + KEY_LDL + " INTEGER,"
                + KEY_COLESTEROLTOTAL + " INTEGER,"
                + KEY_TRIGLICERIDES + " INTEGER,"
                + KEY_DATA_LIPIDOGRAMA + " DATETIME,"
                + KEY_LOCAL_LIPIDOGRAMA + " TEXT,"
                + KEY_PAC4 + " INTEGER,"
                + " FOREIGN KEY ("+KEY_PAC4+") REFERENCES "+TABLE_PACIENTES+"("+KEY_ID+"));";
        db.execSQL(CREATE_LIPIDOGRAMA_TABLE);

        String CREATE_HEMOGRAMA_TABLE = "CREATE TABLE IF NOT EXISTS "
                + TABLE_HEMOGRAMA
                + "("
                + KEY_ID_HEMOGRAMA + " INTEGER PRIMARY KEY,"
                + KEY_HEMOGLOBINA + " REAL,"
                + KEY_HEMATOCRITO + " REAL,"
                + KEY_VGM + " REAL,"
                + KEY_CHCM + " REAL,"
                + KEY_CHGM + " REAL,"
                + KEY_RWD + " REAL,"
                + KEY_DATA_HEMOGRAMA + " DATETIME,"
                + KEY_PAC5 + " INTEGER,"
                + " FOREIGN KEY ("+KEY_PAC5+") REFERENCES "+TABLE_PACIENTES+"("+KEY_ID+"));";
        db.execSQL(CREATE_HEMOGRAMA_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXIST " + TABLE_EXAMES);
        db.execSQL("DROP TABLE IF EXIST " + TABLE_LIPIDOGRAMA);
        db.execSQL("DROP TABLE IF EXIST " + TABLE_HEMOGRAMA);

        onCreate(db);
    }

    public String ModelAddExame (ExameClass exame){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        Log.d("Adicionando : ", "Metodo addExame");
        Log.d("Glicose 75g : ", String.valueOf(exame.getGlicose75g()));
        Log.d("Glicose jejum : ", String.valueOf(exame.getGlicoseJejum()));
        Log.d("Colesterol : ", String.valueOf(exame.getColesterol()));

        values.put(KEY_GLICOSE75G, exame.getGlicose75g());
        values.put(KEY_GLICOSEJEJUM, exame.getGlicoseJejum());
        values.put(KEY_COLESTEROL, exame.getColesterol());
        values.put(KEY_DATA_EXAME, String.valueOf(exame.getDataExame()));
        values.put(KEY_PAC2, exame.getIdPac());

        long retorno;
        retorno = db.insert(TABLE_EXAMES, null, values);
        db.close();

        if(retorno == -1){
            return "Erro ao inserir o registro dos exames!";
        } else {
            return "Registro dos exames inserido com sucesso!";
        }
    }

    public String ModelAddLipidograma (LipidogramaClass lipidograma){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_HDL, lipidograma.getHDL());
        values.put(KEY_LDL, lipidograma.getLDL());
        values.put(KEY_COLESTEROLTOTAL, lipidograma.getColesterolTotal());
        values.put(KEY_TRIGLICERIDES, lipidograma.getTriglicerides());
        values.put(KEY_DATA_LIPIDOGRAMA, lipidograma.getDataLipidograma());
        values.put(KEY_LOCAL_LIPIDOGRAMA, lipidograma.getLocalLipidograma());
        values.put(KEY_PAC4, lipidograma.getIdPacienteLipidograma());

        long retorno;
        retorno = db.insert(TABLE_LIPIDOGRAMA, null, values);
        db.close();

        if(retorno == -1){
            return "Erro ao registrar o lipidograma!";
        } else {
            return "Lipidograma registrado com sucesso!";
        }
    }

    public String ModelAddHemograma (HemogramaClass hemograma){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_HEMOGLOBINA, hemograma.getHemoglobina());
        values.put(KEY_HEMATOCRITO, hemograma.getHematocrito());
        values.put(KEY_VGM, hemograma.getVgm());
        values.put(KEY_CHCM, hemograma.getChcm());
        values.put(KEY_CHGM, hemograma.getChgm());
        values.put(KEY_RWD, hemograma.getRwd());
        values.put(KEY_DATA_HEMOGRAMA, hemograma.getDataHemograma());
        values.put(KEY_PAC5, hemograma.getIdPacienteHemograma());

        long retorno;
        retorno = db.insert(TABLE_HEMOGRAMA, null, values);
        db.close();

        if(retorno == -1){
            return "Erro ao inserir o hemograma";
        } else {
            return "Registro do hemograma feito com sucesso!";
        }
    }

    public List<ExameClass> ModelGetAllExames() throws ParseException {
        List<ExameClass> exameList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_EXAMES;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                ExameClass exame = new ExameClass();
                exame.setId(Integer.parseInt(cursor.getString(0)));
                exame.setGlicose75g(Double.parseDouble(cursor.getString(1)));
                exame.setGlicoseJejum(Double.parseDouble(cursor.getString(2)));
                exame.setColesterol(Double.parseDouble(cursor.getString(3)));
                exame.setDataExame(cursor.getString(4));
                exame.setIdPac(Integer.parseInt(cursor.getString(5)));
                exameList.add(exame);
            }while(cursor.moveToNext());
        }

        return exameList;
    }

    public void ModelAtualizarTaxas(Paciente paciente){
        //pega data atual
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = sdf.format(date);
        Log.d("Data : ", dateString);

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values;

        //guarda dados do paciente (peso e data atual)
        values = new ContentValues();
        values.put(KEY_GLICOSE75G, paciente.get_glicose75g());
        values.put(KEY_GLICOSEJEJUM, paciente.get_glicosejejum());
        values.put(KEY_COLESTEROL, paciente.get_colesterol());
        values.put(KEY_DATA_EXAME, dateString);
        values.put(KEY_PAC2, paciente.get_id());

        //insere dados no banco de pesos
        long retorno;
        retorno = db.insert(TABLE_EXAMES, null, values);
        db.close();

        if(retorno == -1){
            Log.d("Erro ao atualizar taxas!", "DatabaseHandler");
        } else {
            Log.d("Taxas atualizadas!", "DatabaseHandler");
        }
    }

    public Paciente ModelGetUltimasTaxas(Paciente paciente){
        String selectQuery = "SELECT * FROM " + TABLE_EXAMES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        //procura o peso pelo id do paciente
        if(cursor.moveToFirst()){
            do{
                if(cursor.getString(5).equals(String.valueOf(paciente.get_id()))) {
                    if(Double.parseDouble(cursor.getString(1)) != 0.0) {
                        paciente.set_glicose75g(Double.parseDouble(cursor.getString(1)));
                        Log.d("Glicose75 : ", String.valueOf(paciente.get_glicose75g()));
                    }

                    if(Double.parseDouble(cursor.getString(2)) != 0.0) {
                        paciente.set_glicosejejum(Double.parseDouble(cursor.getString(2)));
                        Log.d("Glicose Jejum : ", String.valueOf(paciente.get_glicosejejum()));
                    }

                    if(Double.parseDouble(cursor.getString(3)) != 0.0) {
                        paciente.set_colesterol(Double.parseDouble(cursor.getString(3)));
                        Log.d("Colesterol : ", String.valueOf(paciente.get_colesterol()));
                    }
                }
            } while(cursor.moveToNext());
        }

        Log.d("G75 : ", String.valueOf(paciente.get_glicose75g()));
        Log.d("GJejum : ", String.valueOf(paciente.get_glicosejejum()));
        Log.d("Co : ", String.valueOf(paciente.get_colesterol()));

        //retorna paciente com ultimas taxas cadastradas pelo usuario
        return paciente;
    }

}
