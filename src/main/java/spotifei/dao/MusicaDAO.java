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
import spotifei.model.Artista;
import spotifei.model.Musica;

/**
 *
 * @author adone
 */
public class MusicaDAO {
    
    private Connection connection;

    public MusicaDAO(Connection connection) {
        this.connection = connection;
    }
    
    public List<Musica> consultarMusicasPesquisa(String textoPesquisa) throws SQLException {
        
        List<Musica> resultadoConsulta = new ArrayList<>();
        
        String sql = "SELECT m.id_musica, m.titulo_musica, m.duracao, "
                    + "a.id_artista, a.nome_artista "
                    + "FROM musicas m "
                    + "JOIN artistas a ON m.id_artista = a.id_artista "
                    + "JOIN generos g ON m.id_genero = g.id_genero "
                    + "WHERE m.titulo_musica ILIKE ? OR a.nome_artista ILIKE ? OR g.nome_genero ILIKE ?";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + textoPesquisa + "%");
            statement.setString(2, "%" + textoPesquisa + "%");
            statement.setString(3, "%" + textoPesquisa + "%");
            
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()) {                
                Musica musica = new Musica(
                    rs.getInt("id_musica"),
                    rs.getString("titulo_musica"),
                    new Artista(rs.getInt("id_artista"), rs.getString("nome_artista")),
                    rs.getString("duracao")
                );
                
                resultadoConsulta.add(musica);
            }
        }
        
        return resultadoConsulta;
    }
    
    public List<Musica> consultarPorPlaylist(int idPlaylist) throws SQLException{
        
        List<Musica> resultadoConsulta = new ArrayList<>();
        
        String sql = "SELECT m.id_musica, m.titulo_musica, a.id_artista, a.nome_artista, m.duracao "
                + "FROM musicas m "
                + "JOIN artistas a ON m.id_artista = a.id_artista "
                + "JOIN playlist_musica pm ON m.id_musica = pm.id_musica "
                + "WHERE pm.id_playlist = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idPlaylist);
            
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()) {
                Musica musica = new Musica(
                    rs.getInt("id_musica"),
                    rs.getString("titulo_musica"),
                    new Artista(rs.getInt("id_artista"), rs.getString("nome_artista")),
                    rs.getString("duracao")
                );
                
                resultadoConsulta.add(musica);
            }
        }
        
        return resultadoConsulta;
    }
}
