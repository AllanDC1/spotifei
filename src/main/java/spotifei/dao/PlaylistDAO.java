/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spotifei.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import spotifei.app.Sessao;
import spotifei.model.Musica;
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
                + "LEFT JOIN playlist_musica pm ON p.id_playlist = pm.id_playlist "
                + "WHERE p.id_usuario = ? "
                + "GROUP BY p.id_playlist, p.nome_playlist";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
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
    
    public void inserirPlaylist(Playlist playlist) throws SQLException {
        
        String sql = "INSERT INTO playlists(nome_playlist, id_usuario) VALUES (?, ?)";
        
        try(PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, playlist.getNome());
            statement.setInt(2, Sessao.getUsuarioLogado().getId());
            
            int linhasCriadas = statement.executeUpdate();
            
            if (linhasCriadas > 0) {
                ResultSet keysGeradas = statement.getGeneratedKeys();
                if (keysGeradas.next()) {
                    playlist.setId(keysGeradas.getInt(1));
                }
            }
        }
    }
    
    public void editarPlaylist(int idPlaylistAlterada, String novoNome) throws SQLException {
        
        String sql = "UPDATE playlists SET nome_playlist = ? WHERE id_playlist = ?";
        
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, novoNome);
            statement.setInt(2, idPlaylistAlterada);
            
            statement.executeUpdate();
        }
    }
    
    public void deletarPlaylist(Playlist playlist) throws SQLException {
        
        String sql = "DELETE FROM playlists WHERE id_playlist = ?";
        
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, playlist.getId());
            
            statement.executeUpdate();
        }
    }
    
    public void inserirMusicaPlaylist(Playlist playlist, Musica musica) throws SQLException {
        
        String sql = "INSERT INTO playlist_musica(id_playlist, id_musica) VALUES (?, ?)";
        
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, playlist.getId());
            statement.setInt(2, musica.getId());
            
            statement.executeUpdate();
        }
    }
    
    public void deletarMusicaPlaylist(Playlist playlist, Musica musica) throws SQLException {
        
        String sql = "DELETE FROM playlist_musica WHERE id_playlist = ? AND id_musica = ?";
        
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, playlist.getId());
            statement.setInt(2, musica.getId());
            
            statement.executeUpdate();
        }
    }
}
