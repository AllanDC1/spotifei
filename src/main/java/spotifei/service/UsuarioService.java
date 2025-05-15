/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spotifei.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import spotifei.dao.PlaylistDAO;
import spotifei.model.Playlist;
import spotifei.model.Usuario;

/**
 *
 * @author adone
 */
public class UsuarioService {
    private PlaylistDAO playlistDAO;

    public UsuarioService(Connection connection) {
        this.playlistDAO = new PlaylistDAO(connection);
    }

    public List<Playlist> getPlaylists(Usuario usuario) throws SQLException {
        return playlistDAO.consultarPorUsuario(usuario.getId());
    }
}
