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
import spotifei.util.Conexao;
import spotifei.util.ErroSQL;
import spotifei.view.CadastroFrame;

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
        String nome = cadastroView.getTxtNomeCadastro().getText();
        String senha = cadastroView.getTxtSenhaCadastro().getText();
        
        if (login.isEmpty() || nome.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(cadastroView, "Preencha todos os campos.", "Erro", JOptionPane.WARNING_MESSAGE);
            return; // alterar caso queira trocar de janela
        }
        
        Usuario novoUsuario = new Usuario(login, nome, senha);
        
        try (Connection connection = Conexao.criarConexaoBD()) {
            
            UsuarioDAO usuarioDao = new UsuarioDAO(connection);
            
            usuarioDao.inserir(novoUsuario);
            JOptionPane.showMessageDialog(cadastroView, "Usuário Cadastrado!", "Aviso", JOptionPane.INFORMATION_MESSAGE);            
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
