package com.example.shared.Control;

import android.content.Context;

import com.example.shared.Model.Nota;
import com.example.shared.Model.NotasDAO;

import java.util.ArrayList;

public class NotaController {
    NotasDAO notasDAO;
    Nota nota;
    public NotaController(Context context) {
        notasDAO = new NotasDAO(context);
    }

    public Nota cadastrarNovaNota(Nota n){
        notasDAO.insereNota(n);
        return n;
    }

    public boolean atualizaNota(Nota n){
        notasDAO.updateNota(n);
        return true;
    }

    public boolean excluirNota(int id){
        notasDAO.deletaNota(id);
        return true;
    }

    public Nota getNota(int id){
        return notasDAO.getNota(id);
    }

    public ArrayList<Nota> getListaNotas(){
        return notasDAO.getListaNotas();
    }


}
