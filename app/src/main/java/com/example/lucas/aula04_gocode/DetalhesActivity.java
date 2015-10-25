package com.example.lucas.aula04_gocode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetalhesActivity extends Activity {

    private TextView tvNome, tvTelefone, tvIdade, tvSerie, tvCidade, tvAtividades;
    private Pessoa pessoa;
    private Button btnEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        tvNome = (TextView) findViewById(R.id.tvDetalhesNome);
        tvTelefone = (TextView) findViewById(R.id.tvDetalhesTelefone);
        tvIdade = (TextView) findViewById(R.id.tvDetalhesIdade);
        tvSerie = (TextView) findViewById(R.id.tvDetalhesSerie);
        tvAtividades = (TextView) findViewById(R.id.tvDetalhesAtividades);
        tvCidade = (TextView) findViewById(R.id.tvDetalhesCidade);
        btnEditar = (Button) findViewById(R.id.btnDetalhesEditar);

        pessoa = new Pessoa();
        Bundle b = getIntent().getExtras();
        String a = b.getString("nome");
        pessoa = pessoa.buscarPeloNome(this, a);

        tvNome.setText(pessoa.getNome());
        tvTelefone.setText(pessoa.getTelefone());
        tvIdade.setText(String.valueOf(pessoa.getIdade()));
        tvSerie.setText(String.valueOf(pessoa.getSerie()));
        tvAtividades.setText(pessoa.getAtividades());
        tvCidade.setText(pessoa.getCidade());

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), CadastrarActivity.class);
                i.putExtra("nome", pessoa.getNome());
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detalhes, menu);
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
