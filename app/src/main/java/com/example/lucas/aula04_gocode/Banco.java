package com.example.lucas.aula04_gocode;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucas on 08/10/2015.
 */
public class Banco extends SQLiteOpenHelper {

    private static final int VERSAO_BANCO = 1;
    private static final String NOME_BANCO = "agenda_gocode";

    public Banco(Context context){
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS pessoa ( _id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT NOT NULL, telefone TEXT, idade INTEGER, serie INTEGER, cidade TEXT, atividades TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void inserirPessoa(Pessoa p){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues valores = new ContentValues();

        valores.put("nome",p.getNome());
        valores.put("telefone",p.getTelefone());
        valores.put("idade",p.getIdade());
        valores.put("serie",p.getSerie());
        valores.put("cidade",p.getCidade());
        valores.put("atividades",p.getAtividades());

        db.insert("pessoa",null ,valores);

        db.close();

    }

    public void editarPessoa(Pessoa p){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues valores = new ContentValues();

        valores.put("nome",p.getNome());
        valores.put("telefone",p.getTelefone());
        valores.put("idade",p.getIdade());
        valores.put("serie",p.getSerie());
        valores.put("cidade",p.getCidade());
        valores.put("atividades",p.getAtividades());

        db.update("pessoa", valores, " _id =" +p.getId(), null);

        db.close();

    }

    public List<String> listarNomesPessoas(){

        List<String> lista = new ArrayList<String>();
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT nome FROM pessoa;";
         Cursor cursor = db.rawQuery(sql,null);

        if(cursor.getCount() > 0){
            cursor.moveToFirst();

            do {
                String nome = cursor.getString(0);
                lista.add(nome);
            }while(cursor.moveToNext());

        }

        db.close();
        return lista;
    }

    public Pessoa buscarPessoaPeloNome(String nome){
            Pessoa p = new Pessoa();

        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM pessoa WHERE nome LIKE '" + nome + "';";

        Cursor cursor  = db.rawQuery(sql, null);

        cursor.moveToFirst();

        p.setId( cursor.getInt(0));
        p.setNome(cursor.getString(1));
        p.setTelefone(cursor.getString(2));
        p.setIdade(cursor.getInt(3));
        p.setSerie(cursor.getInt(4));
        p.setCidade(cursor.getString(5));
        p.setAtividades(cursor.getString(6));
        return p;
    }

    



}
