/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spotifei.controller;

import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import spotifei.app.Sessao;
import spotifei.dao.UsuarioDAO;
import spotifei.model.Usuario;
import spotifei.dao.Conexao;
import spotifei.model.Artista;
import spotifei.model.Musica;
import spotifei.model.Playlist;
import spotifei.view.LoginFrame;
import spotifei.view.SpotifeiFrame;

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
                // PARA TESTE APAGAR DEPOIS
                //Musica musica = new Musica("Musica Teste", new Artista(1, "Artista teste"), "00:10");
                //Playlist playlist = new Playlist(1, "Teste playlist");
                //playlist.setMusicas(musica);
                //usuarioLogado.setPlaylists(playlist);
                // !!!
                Sessao.setUsuarioLogado(usuarioLogado);
                JOptionPane.showMessageDialog(loginView, "Usuário Logado!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                
                SpotifeiFrame homeFrame = new SpotifeiFrame();
                homeFrame.setVisible(true);
                
                loginView.dispose();
            } else {
                JOptionPane.showMessageDialog(loginView, "Usuário ou Senha incorretos.", "Aviso", JOptionPane.WARNING_MESSAGE);
                loginView.getTxtSenhaLogin().setText("");
            }
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(loginView, "Erro no Login: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
