package com.example.shared.Model;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class NotasDAO {
    private SQLiteDatabase db;

    public NotasDAO(Context c){
        db = c.openOrCreateDatabase("banco.db", Context.MODE_PRIVATE, null);

        // Cria a tabela com as colunas: id, titulo e conteudo
        db.execSQL("CREATE TABLE IF NOT EXISTS notas (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "titulo TEXT, " +
                "conteudo TEXT);");
    }

    public Nota insereNota(Nota n){
        ContentValues contentValues = new ContentValues();
        contentValues.put("titulo", n.getTitulo());
        contentValues.put("conteudo", n.getTxt());

        long id = db.insert("notas", null, contentValues);
        n.setIdNota((int) id);
        return n;
    }

    public boolean updateNota(Nota n){
        ContentValues contentValues = new ContentValues();
        contentValues.put("titulo", n.getTitulo());
        contentValues.put("conteudo", n.getTxt());

        int linhasAfetadas = db.update("notas", contentValues, "id = ?", new String[]{String.valueOf(n.getIdNota())});
        return linhasAfetadas > 0;
    }

    public boolean deletaNota(int id){
        int linhasAfetadas = db.delete("notas", "id = ?", new String[]{String.valueOf(id)});
        return linhasAfetadas > 0;
    }

    public Nota getNota(int id){
        Nota nota = null;
        Cursor cursor = db.rawQuery("SELECT * FROM notas WHERE id = ?", new String[]{String.valueOf(id)});

        if (cursor != null && cursor.moveToFirst()) {
            nota = new Nota(
                    cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    cursor.getString(cursor.getColumnIndexOrThrow("titulo")),
                    cursor.getString(cursor.getColumnIndexOrThrow("conteudo"))
            );
            cursor.close();
        }
        return nota;
    }

    public ArrayList<Nota> getListaNotas(){
        Cursor cursor = db.rawQuery("SELECT * FROM notas", null);
        ArrayList<Nota> lista = new ArrayList<>();

        if (cursor != null && cursor.moveToFirst()) {
            while (!cursor.isAfterLast()){
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                String titulo = cursor.getString(cursor.getColumnIndex("titulo"));
                String conteudo = cursor.getString(cursor.getColumnIndex("conteudo"));

                lista.add(new Nota(id, titulo, conteudo));

                Log.d("Select notas", "id: " + id + " titulo: " + titulo + " conteudo: " + conteudo);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return lista;
    }
}