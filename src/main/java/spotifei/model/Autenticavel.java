/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spotifei.model;

/**
 *
 * @author unifacalen
 */
public interface Autenticavel {
    int getId();
    void setId(int id);
    
    String getLogin();
    void setLogin(String login);

    String getSenha();
    void setSenha(String senha);
}
