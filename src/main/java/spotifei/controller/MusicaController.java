/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spotifei.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import spotifei.dao.Conexao;
import spotifei.model.Musica;
import spotifei.model.Usuario;
import spotifei.service.MusicaService;
import spotifei.view.BuscaMusicasPanel;
import spotifei.view.MusicasReagidasPanel;

/**
 *
 * @author adone
 */
public class MusicaController {
    
    private BuscaMusicasPanel buscaMusicasView;
    private MusicasReagidasPanel musicasReagidasView;
    
    public void listarMusicasPesquisa() {
        
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
            JOptionPane.showMessageDialog(null, "Erro na busca: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public String reagirMusica(Usuario usuario, Musica musica, char reacao) {
        
        try (Connection connection = Conexao.criarConexaoBD()) {
            
            MusicaService musicaService = new MusicaService(connection);
            return musicaService.alterarReacaoMusica(usuario, musica, reacao);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public void listarMusicasReagidas(Usuario usuario, char tipoReacao) {
        try (Connection connection = Conexao.criarConexaoBD()) {
            
            MusicaService musicaService = new MusicaService(connection);
            List<Musica> listaMusicasReacoes = musicaService.buscarMusicasUsuarioReacao(usuario, tipoReacao);
            
            musicasReagidasView.listarReacaoItem(listaMusicasReacoes);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setBuscaMusicasView(BuscaMusicasPanel buscaMusicasView) {
        this.buscaMusicasView = buscaMusicasView;
    }

    public void setMusicasReagidasView(MusicasReagidasPanel musicasReagidasView) {
        this.musicasReagidasView = musicasReagidasView;
    }
}
