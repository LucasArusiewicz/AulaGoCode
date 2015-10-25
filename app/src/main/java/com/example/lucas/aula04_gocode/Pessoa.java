package com.example.lucas.aula04_gocode;

import android.content.Context;

import java.util.List;

/**
 * Created by Lucas on 06/10/2015.
 */
public class Pessoa {
    private int id, idade, serie;
    private String nome, Telefone, atividades, cidade;

    public Pessoa() {
    }

    public Pessoa(int id, int idade, int serie, String nome, String telefone, String atividades, String cidade) {
        this.id = id;
        this.idade = idade;
        this.serie = serie;
        this.nome = nome;
        Telefone = telefone;
        this.atividades = atividades;
        this.cidade = cidade;
    }

    public Pessoa buscarPeloNome(Context context, String nome) {

        Banco db = new Banco(context);
        return db.buscarPessoaPeloNome(nome);

    }

    public void inserir(Context context) {

        Banco db = new Banco(context);
        db.inserirPessoa(this);

    }

    public void editar(Context context){
        Banco db = new Banco(context);
        db.editarPessoa(this);
    }

    public List<String> listarNomes(Context context) {

        Banco db = new Banco(context);

        return db.listarNomesPessoas();

    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getAtividades() {
        return atividades;
    }

    public void setAtividades(String atividades) {
        this.atividades = atividades;
    }

    public int getSerie() {
        return serie;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String telefone) {
        Telefone = telefone;
    }
}
