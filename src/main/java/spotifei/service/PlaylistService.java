/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spotifei.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import spotifei.dao.MusicaDAO;
import spotifei.model.Musica;
import spotifei.model.Playlist;

/**
 *
 * @author adone
 */
public class PlaylistService {
    private MusicaDAO musicaDAO;

    public PlaylistService(Connection connection) {
        this.musicaDAO = new MusicaDAO(connection);
    }

    public List<Musica> getMusicas(Playlist playlist) throws SQLException {
        return musicaDAO.consultarPorPlaylist(playlist.getId());
    }
}
