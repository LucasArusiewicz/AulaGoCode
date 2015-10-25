package com.example.lucas.aula04_gocode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class ListarActivity extends Activity {

    private ListView lvPessoas;
    private ArrayAdapter<String> adapter;
    private Pessoa pessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        lvPessoas = (ListView) findViewById(R.id.lvListarPessoas);

        pessoa = new Pessoa();

        carregarListView();
        //String[] nomes = new String[]{"a","b","c","d"};


        lvPessoas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(view.getContext(), DetalhesActivity.class);
                i.putExtra("nome", lvPessoas.getItemAtPosition(position).toString());
                startActivity(i);

            }
        });
    }



    private void carregarListView(){
        adapter = new ArrayAdapter<String>(this, R.layout.estilo_lista_simples);

        List<String> lista = pessoa.listarNomes(this);
        for(int i = 0; i< lista.size(); i++){
            adapter.add(lista.get(i));
        }

        lvPessoas.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_listar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
