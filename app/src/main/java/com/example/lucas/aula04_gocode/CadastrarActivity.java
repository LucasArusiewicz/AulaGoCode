package com.example.lucas.aula04_gocode;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class CadastrarActivity extends Activity {

    private EditText etNome, etTelefone, etIdade;
    private Button btnCadastrar;
    private RadioButton rb1, rb2, rb3;
    private CheckBox cbCorrida, cbCaminhada;
    private Spinner spCidade;
    private Pessoa p;
    private Boolean editar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        p = new Pessoa();

        editar = false;

        Bundle b = getIntent().getExtras();

        if (b.getString("nome").isEmpty()) {
            editar = false;
        } else {
            editar = true;
            preencherFormulario(b.getString("nome"));
        }

        btnCadastrar = (Button) findViewById(R.id.btnCadastrarCadastrar);
        etNome = (EditText) findViewById(R.id.etCadastrasNome);
        etTelefone = (EditText) findViewById(R.id.etCadastrastTelefone);
        etIdade = (EditText) findViewById(R.id.etCadastrasIdade);
        rb1 = (RadioButton) findViewById(R.id.rbCadastrar1);
        rb2 = (RadioButton) findViewById(R.id.rbCadastrar2);
        rb3 = (RadioButton) findViewById(R.id.rbCadastrar3);
        cbCaminhada = (CheckBox) findViewById(R.id.cbCadastrarCaminhada);
        cbCorrida = (CheckBox) findViewById(R.id.cbCadastrarCorrida);
        spCidade = (Spinner) findViewById(R.id.spCadastrarCidade);
        preencherSpinnerCidade();
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = etNome.getText().toString();
                String telefone = etTelefone.getText().toString();
                int idade = Integer.valueOf(etIdade.getText().toString());
                int serie = 0;
                if (rb1.isChecked()) {
                    serie = 1;
                }
                if (rb2.isChecked()) {
                    serie = 2;
                }
                if (rb3.isChecked()) {
                    serie = 3;
                }

                String atividades = "";

                if (cbCorrida.isChecked()) {
                    atividades += cbCorrida.getText().toString() + "\n";
                }
                if (cbCaminhada.isChecked()) {
                    atividades += cbCaminhada.getText().toString() + "\n";
                }

                String cidade = spCidade.getSelectedItem().toString();

                p.setNome(nome);
                p.setTelefone(telefone);
                p.setIdade(idade);
                p.setSerie(serie);
                p.setAtividades(atividades);
                p.setCidade(cidade);

                if (editar == true) {
                    p.editar(v.getContext());
                } else {
                    p.inserir(v.getContext());
                }

                p.inserir(v.getContext());

                AlertDialog.Builder alerta = new AlertDialog.Builder(v.getContext());
                alerta.setTitle("Pessoa Cadastrada");
                alerta.setMessage("Nome: " + nome + "\nTelefone: " + telefone + "\nIdade: " + idade + "\nAno: " + serie + "\nCidade: " + cidade + "\nAtividades: \n" + atividades);
                alerta.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(getApplicationContext(), ListarActivity.class);
                        startActivity(i);
                    }
                });
                alerta.show();

            }
        });





    }

    private void preencherFormulario (String nome) {

        p = p.buscarPeloNome(this,nome);

        etNome.setText(p.getNome());
        etTelefone.setText(p.getTelefone());
        etIdade.setText(String.valueOf(p.getIdade()));

        if (p.getSerie() == 1) rb1.setChecked(true);
        if (p.getSerie() == 2) rb2.setChecked(true);
        if (p.getSerie() == 3) rb3.setChecked(true);
        if (p.getAtividades().contains(getResources().getString(R.string.corrida))) cbCorrida.setChecked(true);
        if (p.getAtividades().contains(getResources().getString(R.string.caminhada))) cbCaminhada.setChecked(true);

    }



    @Override
    protected void onRestart() {
        super.onRestart();

        finish();
    }

    private void preencherSpinnerCidade() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.estilo_lista_simples, getResources().getStringArray(R.array.arrayNomesCidades));
        spCidade.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cadastrar, menu);
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
