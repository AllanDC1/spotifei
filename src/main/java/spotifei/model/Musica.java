/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spotifei.model;

/**
 * Representa uma música do sistema com todas suas informações.
 */
public class Musica {
    private int id;
    private String titulo;
    private Artista artista; 
    private String duracao;

    /**
    * Construtor que inicializa a música todos seus atributos.
    *
    * @param id   Identificador único da música.
    * @param titulo Título da música.
    * @param artista Objeto Artista, artista da música.
    * @param duracao Duração da música em texto.
    */
    public Musica(int id, String titulo, Artista artista, String duracao) {
        this.id = id;
        this.titulo = titulo;
        this.artista = artista;
        this.duracao = duracao;
    }   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }
    
    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }    
    
    @Override
    public String toString() {
        return titulo + " - " + artista;
    }
}
