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
import spotifei.model.Musica;
import spotifei.service.MusicaService;
import spotifei.view.BuscaMusicasPanel;

/**
 *
 * @author adone
 */
public class MusicaController {
    
    private BuscaMusicasPanel buscaMusicasView;
    
    public void listarMusicas() {
        
        String textoPesquisa = buscaMusicasView.getTxtBuscarMusicas().getText();
        // Se estiver com o placeholder, substitui a pesquisa para vazio
        if (textoPesquisa.equals("Pesquisar...")) {
            textoPesquisa = "";
        }
        
        try (Connection connection = Conexao.criarConexaoBD()) {
            
            MusicaService musicaService = new MusicaService(connection);
            List<Musica> resultadoPesquisa = musicaService.buscarMusicas(textoPesquisa);
            
            buscaMusicasView.atualizarListaMusicas(resultadoPesquisa);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(buscaMusicasView, "Erro na busca: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setView(BuscaMusicasPanel buscaMusicasView) {
        this.buscaMusicasView = buscaMusicasView;
    }
    
}
