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
import spotifei.model.Usuario;
import spotifei.dao.Conexao;
import spotifei.exception.ErroSQL;
import spotifei.service.UsuarioService;
import spotifei.view.CadastroFrame;
import spotifei.view.SpotifeiFrame;

/**
 * Controlador responsável pelo fluxo de cadastro de novos usuários no sistema.
 *
 * <p>Interage com a view {@code CadastroFrame} para obter os dados preenchidos pelo usuário,
 * realiza validações, processa o hash da senha e persiste o novo usuário no banco de dados.</p>
 */
public class CadastroController {
    
    private CadastroFrame cadastroView;

    /**
     * Construtor do controlador de cadastro.
     *
     * @param cadastroView A interface gráfica de onde os dados do usuário serão extraídos.
     */
    public CadastroController(CadastroFrame cadastroView) {
        this.cadastroView = cadastroView;
    }
    
    /**
     * Realiza o cadastro de um novo usuário com base nas informações fornecidas na interface gráfica.
     *
     * <p>Valida se os campos estão preenchidos, realiza o hash da senha, salva o novo usuário
     * no banco de dados, exibe mensagens apropriadas e navega para a tela principal após o cadastro bem-sucedido.</p>
     */
    public void cadastrarUsuario() {
        String login = cadastroView.getTxtUsuarioCadastro().getText();
        String senha = cadastroView.getTxtSenhaCadastro().getText();
        String nome = cadastroView.getTxtNomeCadastro().getText();
        
        if (login.isEmpty() || senha.isEmpty() || nome.isEmpty()) {
            JOptionPane.showMessageDialog(cadastroView, "Preencha todos os campos.", "Erro", JOptionPane.WARNING_MESSAGE);
            return; // Interrompe caso tenha erro
        }
        
        // Hashing da senha
        String senhaHash = BCrypt.hashpw(senha, BCrypt.gensalt());
        
        Usuario novoUsuario = new Usuario(login, senhaHash, nome);
        
        try (Connection connection = Conexao.criarConexaoBD()) {
            
            UsuarioService usuarioService = new UsuarioService(connection);           
            usuarioService.cadastrarUsuario(novoUsuario);
            
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
