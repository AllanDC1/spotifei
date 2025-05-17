/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spotifei.model;

/**
 *
 * @author unifacalen
 */
public class Artista extends Pessoa {
    private int id;

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
