/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spotifei.controller;

import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import spotifei.app.Sessao;
import spotifei.model.Usuario;
import spotifei.dao.Conexao;
import spotifei.service.UsuarioService;
import spotifei.view.LoginFrame;
import spotifei.view.SpotifeiFrame;

/**
 * Controlador responsável por realizar o processo de login do usuário.
 *
 * <p>Interage com a interface gráfica {@code LoginFrame} para obter as credenciais
 * informadas, validar os campos e autenticar o usuário no sistema.</p>
 */
public class LoginController {
    
    private LoginFrame loginView;

    /**
     * Construtor do controlador de login.
     *
     * @param loginView A interface gráfica que contém os campos de login e senha.
     */
    public LoginController(LoginFrame loginView) {
        this.loginView = loginView;
    }
    
    /**
     * Realiza o processo de login com base nas credenciais fornecidas pelo usuário.
     *
     * <p>Valida os campos, autentica o usuário no banco de dados e, se bem-sucedido,
     * inicializa a sessão e abre a interface principal do sistema.</p>
     */
    public void logarUsuario() {
        String login = loginView.getTxtUsuarioLogin().getText();
        String senha = new String(loginView.getPswSenhaLogin().getPassword());
        
        if (login.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(loginView, "Preencha todos os campos.", "Erro", JOptionPane.WARNING_MESSAGE);
            return; // Interrompe caso tenha erro
        }
        
        try (Connection connection = Conexao.criarConexaoBD()) {
            
            UsuarioService usuarioService = new UsuarioService(connection);                       
            Usuario usuarioLogado = usuarioService.logarUsuario(login, senha);
            
            if (usuarioLogado != null) {
                Sessao.setUsuarioLogado(usuarioLogado);
                JOptionPane.showMessageDialog(loginView, "Usuário Logado!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                
                SpotifeiFrame homeFrame = new SpotifeiFrame();
                homeFrame.setVisible(true);
                
                loginView.dispose();
            } else {
                JOptionPane.showMessageDialog(loginView, "Usuário ou Senha incorretos.", "Aviso", JOptionPane.WARNING_MESSAGE);
                loginView.getPswSenhaLogin().setText("");
            }
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(loginView, "Erro no Login: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
