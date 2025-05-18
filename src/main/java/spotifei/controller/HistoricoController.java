/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spotifei.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import spotifei.dao.Conexao;
import spotifei.model.Usuario;
import spotifei.service.HistoricoService;
import spotifei.view.BuscaMusicasPanel;


/**
 *
 * @author adone
 */
public class HistoricoController {
    
    private BuscaMusicasPanel view;
    
    public void adicionarPesquisa(Usuario usuario, String textoPesquisa) {
        
        if (textoPesquisa.equals("Pesquisar...") || textoPesquisa.isEmpty()) {
            return; // NAO SALVA NO HISTORICO
        }
        
        try (Connection connection = Conexao.criarConexaoBD()) {
            
            HistoricoService historicoService = new HistoricoService(connection);
            historicoService.adicionarNovaPesquisa(usuario, textoPesquisa);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void buscarExibirHistorico(Usuario usuario) {
        
        try (Connection connection = Conexao.criarConexaoBD()) {
            
            HistoricoService historicoService = new HistoricoService(connection);
            List<String> historico = historicoService.buscarHistoricoUsuario(usuario);
            
            view.exibirHistorico(historico);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setView(BuscaMusicasPanel BuscaMusicasview) {
        this.view = BuscaMusicasview;
    }
}
