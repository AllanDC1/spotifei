/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spotifei.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import spotifei.model.Playlist;

/**
 *
 * @author adone
 */
public class PlaylistDAO {
    private Connection connection;

    public PlaylistDAO(Connection connection) {
        this.connection = connection;
    }
    
    public List<Playlist> consultarPorUsuario(int idUsuario) throws SQLException {
        
        List<Playlist> resultadoConsulta = new ArrayList<>();
        
        String sql = "SELECT p.id_playlist, p.nome_playlist, COUNT(pm.id_musica) AS qnt_musicas "
                + "FROM playlists p "
                + "JOIN playlist_musica pm ON p.id_playlist = pm.id_playlist "
                + "WHERE id_usuario = ? "
                + "GROUP BY p.id_playlist, p.nome_playlist";
        
        try (PreparedStatement statement = connection.prepareCall(sql)) {
            statement.setInt(1, idUsuario);
            
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()) {
                Playlist playlist = new Playlist(
                    rs.getInt("id_playlist"),
                    rs.getString("nome_playlist"),
                    rs.getInt("qnt_musicas")
                );
                
                resultadoConsulta.add(playlist);
            }
        }
        
        return resultadoConsulta;
    }
}
