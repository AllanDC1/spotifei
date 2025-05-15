/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spotifei.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author unifacalen
 */
public class Playlist {
    private int id;
    private String nome;
    private List<Musica> musicas;
    private Usuario dono;

    public Playlist(int id, String nome) {
        this.id = id;
        this.nome = nome;
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

    public Usuario getDono() {
        return dono;
    }

    public void setDono(Usuario dono) {
        this.dono = dono;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    // PARA TESTE ALTERAR DEPOIS
    public void setMusicas(Musica musica) {
        this.musicas = new ArrayList<Musica>();
        this.musicas.add(musica);
    }
}
