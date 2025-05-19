/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spotifei.model;

/**
 * Representa uma playlist do sistema com todas suas informações.
 */
public class Playlist {
    private int id;
    private String nome;
    private int qntMusicas;

    /**
    * Construtor que inicializa a playlist sem ID.
    *
    * @param nome Nome da playlist.
    * @param qntMusicas Quantidade de músicas na playlist.
    */
    public Playlist(String nome, int qntMusicas) {
        this.nome = nome;
        this.qntMusicas = qntMusicas;
    }
    
    /**
    * Construtor que inicializa a playlist completa.
    *
    * @param id Identificador único da playlist.
    * @param nome Nome da playlist.
    * @param qntMusicas Quantidade de músicas na playlist.
    */
    public Playlist(int id, String nome, int qntMusicas) {
        this.id = id;
        this.nome = nome;
        this.qntMusicas = qntMusicas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQntMusicas() {
        return qntMusicas;
    }

    public void setQntMusicas(int qntMusicas) {
        this.qntMusicas = qntMusicas;
    }

    @Override
    public String toString() {
        return nome;
    }    
}
