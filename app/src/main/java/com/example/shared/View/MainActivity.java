package com.example.shared.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.shared.Control.NotaController;
import com.example.shared.Model.Nota;
import com.example.shared.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    NotaController notaController;
    Nota nota;

    ListView listView;
    EditText editText;
    Button button;

    ArrayAdapter<Nota> adapter;
    ArrayList<Nota> listaDeNotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        button = findViewById(R.id.buttonAdd);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        notaController = new NotaController(this);

        // 🛠️ REMOVIDO: O método listarNotas() saiu daqui e foi para o onResume()

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetalheNotaActivity.class);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Nota notaClicada = listaDeNotas.get(position);

                Intent intent = new Intent(MainActivity.this, DetalheNotaActivity.class);
                intent.putExtra("NOTA_ID", notaClicada.getIdNota());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        listarNotas();
    }

    public void listarNotas(){
        listaDeNotas = notaController.getListaNotas();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaDeNotas);
        listView.setAdapter(adapter);
    }
}