package com.example.shared.View;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.shared.Control.NotaController;
import com.example.shared.Model.Nota;
import com.example.shared.R;

public class DetalheNotaActivity extends AppCompatActivity {
     EditText editTitulo, editConteudo;
     Button btnSalvar, btnDeletar;
     int notaId = -1;

     NotaController notaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detalhe_nota);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout_detalhe), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        editTitulo = findViewById(R.id.editTitulo);
        editConteudo = findViewById(R.id.editConteudo);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnDeletar = findViewById(R.id.btnDeletar);

        notaController = new NotaController(this);

        notaId = getIntent().getIntExtra("NOTA_ID", -1);

        if (notaId != -1) { //Nota ja existe
            Nota nota = notaController.getNota(notaId);
            if (nota != null) {
                editTitulo.setText(nota.getTitulo());
                editConteudo.setText(nota.getTxt());
            }
            btnDeletar.setVisibility(View.VISIBLE);
        } else { //Nota nao existe
            btnDeletar.setVisibility(View.INVISIBLE);
        }

        btnDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (notaController.excluirNota(notaId)){
                    Toast.makeText(DetalheNotaActivity.this, "Nota apagada!!", Toast.LENGTH_SHORT).show();
                };
                finish();
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo = editTitulo.getText().toString();
                String conteudo = editConteudo.getText().toString();

                if (titulo.isEmpty()) {
                    Toast.makeText(DetalheNotaActivity.this, "Por favor, insira um título!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (notaId != -1){
                    if (notaController.atualizaNota(new Nota(notaId, titulo, conteudo))){
                        Toast.makeText(DetalheNotaActivity.this, "Nota Atualizada!!", Toast.LENGTH_SHORT).show();
                    };
                } else {
                    if (notaController.cadastrarNovaNota(new Nota(notaId, titulo, conteudo)) != null){
                        Toast.makeText(DetalheNotaActivity.this, "Nota Adicionada!!", Toast.LENGTH_SHORT).show();
                    };
                }
                finish();
            }
        });
    }
}