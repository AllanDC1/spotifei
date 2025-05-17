/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spotifei.controller;

import java.awt.CardLayout;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import spotifei.dao.Conexao;
import spotifei.exception.ErroSQL;
import spotifei.model.Musica;
import spotifei.model.Playlist;
import spotifei.model.Usuario;
import spotifei.service.PlaylistService;
import spotifei.service.UsuarioService;
import spotifei.view.BuscaMusicasPanel;
import spotifei.view.GerenciarPlaylistsPanel;
import spotifei.view.ExibirPlaylistPanel;
import spotifei.view.SpotifeiFrame;

/**
 *
 * @author adone
 */
public class SpotifeiFrameController {
    
    private SpotifeiFrame spotifeiView;
    private JPanel pnlMain;
    private CardLayout cl;

    public SpotifeiFrameController(SpotifeiFrame spotifeiView) {
        this.spotifeiView = spotifeiView;
        
        this.pnlMain = spotifeiView.getPnlMainSpotifei();
        this.cl = (CardLayout)(pnlMain.getLayout());
    }
    
    public void navegarMusicas() {
        pnlMain.add(new BuscaMusicasPanel(), "buscarMusicas");
        cl.show(pnlMain, "buscarMusicas");
    }
    
    public void navegarPlaylists() {
        pnlMain.add(new GerenciarPlaylistsPanel(this), "gerenciarPlaylists");
        cl.show(pnlMain, "gerenciarPlaylists");
    }
    
    public void exibirPlaylist(Playlist playlist) {
        pnlMain.add(new ExibirPlaylistPanel(this, playlist), "exibirPlaylist");
        cl.show(pnlMain, "exibirPlaylist");
    }
    
    public List<Playlist> buscarPlaylistsUsuario(Usuario usuario) {
        
        List<Playlist> listaPlaylists = new ArrayList<>();
        
        try (Connection connection = Conexao.criarConexaoBD()) {
            UsuarioService usuarioService = new UsuarioService(connection);
            listaPlaylists = usuarioService.getPlaylists(usuario);          
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(spotifeiView, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return listaPlaylists;
    }
    
    public List<Musica> buscarMusicasPlaylist(Playlist playlist) {
        
        List<Musica> listaMusicas = new ArrayList<>();
        
        try (Connection connection = Conexao.criarConexaoBD()) {
            PlaylistService playlistService = new PlaylistService(connection);
            listaMusicas = playlistService.getMusicas(playlist);          
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(spotifeiView, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return listaMusicas;
    }
    
    public boolean salvarPlaylist(Playlist playlist) {
        try (Connection connection = Conexao.criarConexaoBD()) {
            PlaylistService playlistService = new PlaylistService(connection);
            playlistService.cadastrarPlaylist(playlist);
            
            return true;
        } catch (SQLException e) {
            if (e.getSQLState().equals(ErroSQL.ERRO_UNIQUE_POSTGRESQL)) {
                JOptionPane.showMessageDialog(spotifeiView, "Playlist com esse nome já existe.", "Aviso", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(spotifeiView, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
            return false;
        }
    }
}
