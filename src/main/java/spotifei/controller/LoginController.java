/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spotifei.controller;

import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import spotifei.dao.UsuarioDAO;
import spotifei.model.Usuario;
import spotifei.dao.Conexao;
import spotifei.view.LoginFrame;

/**
 *
 * @author unifacalen
 */
public class LoginController {
    
    private LoginFrame loginView;

    public LoginController(LoginFrame loginView) {
        this.loginView = loginView;
    }
    
    public void logarUsuario() {
        String login = loginView.getTxtUsuarioLogin().getText();
        String senha = loginView.getTxtSenhaLogin().getText();
        
        if (login.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(loginView, "Preencha todos os campos.", "Erro", JOptionPane.WARNING_MESSAGE);
            return; // alterar caso queira trocar de janela
        }
        
        try (Connection connection = Conexao.criarConexaoBD()) {
            
            UsuarioDAO usuarioDao = new UsuarioDAO(connection);                           
            Usuario usuarioLogado = usuarioDao.consultarLogin(login, senha);
            
            if (usuarioLogado != null) {
                JOptionPane.showMessageDialog(loginView, "Usuário Logado!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(loginView, "Usuário ou Senha incorretos.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(loginView, "Erro no Login: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
