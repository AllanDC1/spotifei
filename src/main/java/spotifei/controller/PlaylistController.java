/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spotifei.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import spotifei.dao.Conexao;
import spotifei.exception.ErroSQL;
import spotifei.model.Musica;
import spotifei.model.Playlist;
import spotifei.model.Usuario;
import spotifei.service.PlaylistService;
import spotifei.service.UsuarioService;

/**
 *
 * @author adone
 */
public class PlaylistController {
    
    public List<Musica> buscarMusicasPlaylist(Playlist playlist) {
        
        List<Musica> listaMusicas = new ArrayList<>();
        
        try (Connection connection = Conexao.criarConexaoBD()) {
            PlaylistService playlistService = new PlaylistService(connection);
            listaMusicas = playlistService.getMusicas(playlist);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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
                JOptionPane.showMessageDialog(null, "Uma Playlist com esse nome já existe.", "Aviso", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
            return false;
        }
    }

    public boolean renomearPlaylist(int idPlaylistAlterada, String novoNome) {
        
        try (Connection connection = Conexao.criarConexaoBD()) {
            PlaylistService playlistService = new PlaylistService(connection);
            playlistService.alterarPlaylist(idPlaylistAlterada, novoNome);
            
            return true;
        } catch (SQLException e) {
            if (e.getSQLState().equals(ErroSQL.ERRO_UNIQUE_POSTGRESQL)) {
                JOptionPane.showMessageDialog(null, "Uma Playlist com esse nome já existe.", "Aviso", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
            return false;
        }
    }

    public void excluirPlaylist(Playlist playlist) {
        
        try (Connection connection = Conexao.criarConexaoBD()) {
            PlaylistService playlistService = new PlaylistService(connection);
            playlistService.removerPlaylist(playlist);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<Playlist> buscarPlaylistsUsuario(Usuario usuario) {
        
        List<Playlist> listaPlaylists = new ArrayList<>();
        
        try (Connection connection = Conexao.criarConexaoBD()) {
            UsuarioService usuarioService = new UsuarioService(connection);
            listaPlaylists = usuarioService.getPlaylists(usuario);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return listaPlaylists;
    }
    
    public boolean adicionarMusica(Playlist playlist, Musica musica) {
        
        try (Connection connection = Conexao.criarConexaoBD()) {
            PlaylistService playlistService = new PlaylistService(connection);
            playlistService.adicionarMusicaPlaylist(playlist, musica);
            return true;
        } catch (SQLException e) {
            if (e.getSQLState().equals(ErroSQL.ERRO_UNIQUE_POSTGRESQL)) {
                JOptionPane.showMessageDialog(null, "A música já está nessa playlist.", "Aviso", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
            return false;
        }
    }
    
    public void removerMusica(Playlist playlist, Musica musica) {
        
        try (Connection connection = Conexao.criarConexaoBD()) {
            PlaylistService playlistService = new PlaylistService(connection);
            playlistService.removerMusicaPlaylist(playlist, musica);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
