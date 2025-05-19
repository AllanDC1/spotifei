/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spotifei.model;

/**
 * Representa um artista musical, estendendo a classe {@code Pessoa}.
 * Contém um identificador único e o nome herdado da superclasse.
 */
public class Artista extends Pessoa {
    private int id;

    /**
    * Construtor que inicializa o artista com um ID e um nome.
    *
    * @param id   Identificador único do artista.
    * @param nome Nome do artista.
    */
    public Artista(int id, String nome) {
        this.id = id;
        super.setNome(nome);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return super.getNome();
    }
}
