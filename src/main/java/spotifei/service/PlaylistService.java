/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spotifei.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import spotifei.dao.MusicaDAO;
import spotifei.dao.PlaylistDAO;
import spotifei.model.Musica;
import spotifei.model.Playlist;

/**
 *
 * @author adone
 */
public class PlaylistService {
    private MusicaDAO musicaDAO;
    private PlaylistDAO playlistDAO;

    public PlaylistService(Connection connection) {
        this.musicaDAO = new MusicaDAO(connection);
        this.playlistDAO = new PlaylistDAO(connection);
    }

    public List<Musica> getMusicas(Playlist playlist) throws SQLException {
        return musicaDAO.consultarPorPlaylist(playlist.getId());
    }
    
    public void cadastrarPlaylist(Playlist playlist) throws SQLException {
        playlistDAO.inserirPlaylist(playlist);
    }
    
    public void alterarPlaylist(int idPlaylistAlterada, String novoNome) throws SQLException {
        playlistDAO.editarPlaylist(idPlaylistAlterada, novoNome);
    }
    
    public void removerPlaylist(Playlist playlist) throws SQLException {
        playlistDAO.deletarPlaylist(playlist);
    }
}
