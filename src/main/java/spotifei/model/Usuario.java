/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spotifei.model;

import java.util.List;

/**
 *
 * @author unifacalen
 */
public class Usuario extends Pessoa implements Autenticavel{
    
    private int id;
    private String login;
    private String senha;
    private List<Playlist> playlists;

    public Usuario(String login, String senha, String nome) {
        this.login = login;
        this.senha = senha;
        super.setNome(nome);
    }
    
    public Usuario(int id, String login, String senha, String nome) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        super.setNome(nome);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }    
    
    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String getSenha() {
        return senha;
    }

    @Override
    public void setSenha(String senha) {
        this.senha = senha;
    }
}
