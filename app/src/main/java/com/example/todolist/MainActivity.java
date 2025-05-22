package com.example.todolist;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ArrayList<String> tarefas = new ArrayList<>();
        ArrayAdapter<String> adapter;

        ListView listView = findViewById(R.id.listViewTarefas);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tarefas);
        listView.setAdapter(adapter);

// Adicionar tarefa com diálogo
        FloatingActionButton fab = findViewById(R.id.fabAdicionar);
        fab.setOnClickListener(v -> {
            EditText input = new EditText(this);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Nova Tarefa");
            builder.setView(input);
            builder.setPositiveButton("Adicionar", (dialog, which) -> {
                tarefas.add(input.getText().toString());
                adapter.notifyDataSetChanged();
            });
            builder.setNegativeButton("Cancelar", null);
            builder.show();
        });

// Apagar com clique longo
        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            String tarefaSelecionada = tarefas.get(position);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("O que deseja fazer?");
            builder.setItems(new CharSequence[]{"Editar", "Apagar"}, (dialog, which) -> {
                if (which == 0) {
                    // Editar

                } else if (which == 1) {
                    // Apagar

                }
            });
            builder.show();
            return true;
        });
    }
}