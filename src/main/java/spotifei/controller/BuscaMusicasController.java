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
import spotifei.dao.MusicaDAO;
import spotifei.model.Musica;
import spotifei.view.BuscaMusicasPanel;

/**
 *
 * @author adone
 */
public class BuscaMusicasController {
    
    private BuscaMusicasPanel buscaMusicasView;

    public BuscaMusicasController(BuscaMusicasPanel buscaMusicasView) {
        this.buscaMusicasView = buscaMusicasView;
    }
    
    public void buscarMusicas() {
        
        String textoPesquisa = buscaMusicasView.getTxtBuscarMusicas().getText();
        
        try (Connection connection = Conexao.criarConexaoBD()) {
            
            MusicaDAO musicaDao = new MusicaDAO(connection);
            List<Musica> resultadoPesquisa = musicaDao.consultarMusicas(textoPesquisa);
            
            buscaMusicasView.atualizarListaMusicas(resultadoPesquisa);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(buscaMusicasView, "Erro na busca: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
}
