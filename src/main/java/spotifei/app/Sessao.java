/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spotifei.app;

import spotifei.model.Usuario;

/**
 * Classe utilitária para gerenciar a sessão do usuário logado no sistema.
 * 
 * <p>Armazena o usuário atualmente autenticado de forma estática,
 * permitindo acesso global enquanto a aplicação estiver em execução.</p>
 */
public class Sessao {
    /** 
     * Referência ao usuário atualmente logado.
     */
    private static Usuario usuarioLogado;
    
    public static void setUsuarioLogado(Usuario usuario) {
        usuarioLogado = usuario;
    }
    
    public static Usuario getUsuarioLogado() {
        return usuarioLogado;
    }
}
