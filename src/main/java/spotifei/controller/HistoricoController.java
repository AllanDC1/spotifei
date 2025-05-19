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
 * Controlador responsável por gerenciar o histórico de pesquisas realizadas por um usuário.
 *
 * <p>Permite adicionar novas pesquisas ao histórico e recuperar pesquisas anteriores para exibição na interface gráfica.</p>
 */
public class HistoricoController {
    
    private BuscaMusicasPanel view;
    
     /**
     * Adiciona uma nova pesquisa ao histórico do usuário.
     *
     * <p>Ignora entradas vazias ou o texto padrão "Pesquisar...".</p>
     *
     * @param usuario O usuário que realizou a pesquisa.
     * @param textoPesquisa O texto pesquisado.
     */
    public void adicionarPesquisa(Usuario usuario, String textoPesquisa) {
        
        if (textoPesquisa.equals("Pesquisar...") || textoPesquisa.isEmpty()) {
            return; // Não salva no histórico
        }
        
        try (Connection connection = Conexao.criarConexaoBD()) {
            
            HistoricoService historicoService = new HistoricoService(connection);
            historicoService.adicionarNovaPesquisa(usuario, textoPesquisa);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Recupera e exibe o histórico de pesquisas do usuário na interface.
     *
     * @param usuario O usuário cujo histórico será exibido.
     */
    public void buscarExibirHistorico(Usuario usuario) {
        
        try (Connection connection = Conexao.criarConexaoBD()) {
            
            HistoricoService historicoService = new HistoricoService(connection);
            List<String> historico = historicoService.buscarHistoricoUsuario(usuario);
            
            view.exibirHistorico(historico);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setView(BuscaMusicasPanel buscaMusicasview) {
        this.view = buscaMusicasview;
    }
}
