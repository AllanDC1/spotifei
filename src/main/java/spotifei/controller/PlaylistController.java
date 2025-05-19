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
 * Controlador responsável pela manipulação de playlists.
 *
 * <p>Fornece métodos para criar, editar, remover playlists e adicionar/remover músicas delas.
 * Atua como intermediário entre a interface gráfica e a camada de serviços.</p>
 */
public class PlaylistController {
    
    /**
     * Busca todas as músicas de uma playlist.
     *
     * @param playlist Playlist cujas músicas serão recuperadas.
     * @return Lista de músicas da playlist, ou lista vazia em caso de erro.
     */
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

    /**
     * Salva uma nova playlist no banco de dados.
     *
     * @param playlist Playlist a ser cadastrada.
     * @return {@code true} se a operação for bem-sucedida, {@code false} caso contrário.
     */
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

    /**
     * Altera o nome de uma playlist existente.
     *
     * @param idPlaylistAlterada ID da playlist a ser renomeada.
     * @param novoNome Novo nome desejado para a playlist.
     * @return {@code true} se renomeada com sucesso, {@code false} se falhar.
     */
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

    /**
     * Exclui uma playlist.
     *
     * @param playlist Playlist a ser removida do sistema.
     */
    public void excluirPlaylist(Playlist playlist) {
        
        try (Connection connection = Conexao.criarConexaoBD()) {
            PlaylistService playlistService = new PlaylistService(connection);
            playlistService.removerPlaylist(playlist);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Retorna todas as playlists de um usuário.
     *
     * @param usuario Usuário cujas playlists serão buscadas.
     * @return Lista de playlists, ou vazia em caso de erro.
     */
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
    
    /**
     * Adiciona uma música a uma playlist.
     *
     * @param playlist Playlist de destino.
     * @param musica Música a ser adicionada.
     * @return {@code true} se adicionada com sucesso, {@code false} caso contrário.
     */
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
    
    /**
     * Remove uma música de uma playlist.
     *
     * @param playlist Playlist de origem.
     * @param musica Música a ser removida.
     */
    public void removerMusica(Playlist playlist, Musica musica) {
        
        try (Connection connection = Conexao.criarConexaoBD()) {
            PlaylistService playlistService = new PlaylistService(connection);
            playlistService.removerMusicaPlaylist(playlist, musica);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
