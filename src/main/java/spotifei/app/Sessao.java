/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spotifei.app;

import spotifei.model.Usuario;

/**
 *
 * @author adone
 */
public class Sessao {
    private static Usuario usuarioLogado;
    
    public static void setUsuarioLogado(Usuario usuario) {
        usuarioLogado = usuario;
    }
    
    public static Usuario getUsuarioLogado() {
        return usuarioLogado;
    }
}
