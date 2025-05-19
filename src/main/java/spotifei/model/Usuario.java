/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spotifei.model;

/**
 * Representa um usuário do sistema.
 * Estenden a classe {@code Pessoa} e implementa {@code Autenticavel}
 */
public class Usuario extends Pessoa implements Autenticavel{
    
    private int id;
    private String login;
    private String senha;

    /**
     * Construtor para um usuário apenas com login, senha e nome.
     * 
     * @param login o login do usuário
     * @param senha a senha do usuário
     * @param nome  o nome do usuário
     */
    public Usuario(String login, String senha, String nome) {
        this.login = login;
        this.senha = senha;
        super.setNome(nome);
    }
    
    /**
     * Construtor completo para um usuário.
     * 
     * @param id    o identificador do usuário
     * @param login o login do usuário
     * @param senha a senha do usuário
     * @param nome  o nome do usuário
     */
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
