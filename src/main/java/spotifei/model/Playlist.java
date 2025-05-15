/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spotifei.model;

/**
 *
 * @author unifacalen
 */
public class Playlist {
    private int id;
    private String nome;
    private int qntMusicas;

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
}
