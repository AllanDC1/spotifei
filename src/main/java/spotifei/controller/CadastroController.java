/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spotifei.controller;

import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import org.mindrot.jbcrypt.BCrypt;
import spotifei.app.Sessao;
import spotifei.dao.UsuarioDAO;
import spotifei.model.Usuario;
import spotifei.dao.Conexao;
import spotifei.exception.ErroSQL;
import spotifei.view.CadastroFrame;
import spotifei.view.SpotifeiFrame;

/**
 *
 * @author unifacalen
 */
public class CadastroController {
    
    private CadastroFrame cadastroView;

    public CadastroController(CadastroFrame cadastroView) {
        this.cadastroView = cadastroView;
    }
    
    public void cadastrarUsuario() {
        String login = cadastroView.getTxtUsuarioCadastro().getText();
        String senha = cadastroView.getTxtSenhaCadastro().getText();
        String nome = cadastroView.getTxtNomeCadastro().getText();
        
        if (login.isEmpty() || senha.isEmpty() || nome.isEmpty()) {
            JOptionPane.showMessageDialog(cadastroView, "Preencha todos os campos.", "Erro", JOptionPane.WARNING_MESSAGE);
            return; // alterar caso queira trocar de janela
        }
        
        // Hashing da senha
        String senhaHash = BCrypt.hashpw(senha, BCrypt.gensalt());
        
        Usuario novoUsuario = new Usuario(login, senhaHash, nome);
        
        try (Connection connection = Conexao.criarConexaoBD()) {
            
            UsuarioDAO usuarioDao = new UsuarioDAO(connection);            
            usuarioDao.inserir(novoUsuario);
            
            JOptionPane.showMessageDialog(cadastroView, "Usuário Cadastrado!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            
            Sessao.setUsuarioLogado(novoUsuario);
            
            SpotifeiFrame homeFrame = new SpotifeiFrame();
            homeFrame.setVisible(true);
                
            cadastroView.dispose();
        }
        catch (SQLException e) {
            if (e.getSQLState().equals(ErroSQL.ERRO_UNIQUE_POSTGRESQL)) {
                JOptionPane.showMessageDialog(cadastroView, "Usuário já cadastrado.", "Aviso", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(cadastroView, "Erro no Cadastro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }        
    }
    
}
