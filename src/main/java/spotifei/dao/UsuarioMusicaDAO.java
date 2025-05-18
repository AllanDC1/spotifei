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
import spotifei.model.Usuario;

/**
 *
 * @author adone
 */
public class UsuarioMusicaDAO {
    
    private Connection connection;    
    private static final char SEM_REACAO = '-';

    public UsuarioMusicaDAO(Connection connection) {
        this.connection = connection;
    }
    
    private void inserirReacaoMusica(Usuario usuario, Musica musica, char reacao) throws SQLException {
        
        String sql = "INSERT INTO usuario_musica (id_usuario, id_musica, tipo_reacao) VALUES (?, ?, ?)";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, usuario.getId());
            statement.setInt(2, musica.getId());
            statement.setString(3, String.valueOf(reacao));
            
            statement.executeUpdate();            
        }
    }
    
    private void deletarReacaoMusica(Usuario usuario, Musica musica) throws SQLException {
        
        String sql = "DELETE FROM usuario_musica WHERE id_usuario = ? AND id_musica = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, usuario.getId());
            statement.setInt(2, musica.getId());
            
            statement.executeUpdate();            
        }
    }
    
    private void editarReacaoMusica(Usuario usuario, Musica musica, char reacao) throws SQLException {
        
        String sql = "UPDATE usuario_musica SET tipo_reacao = ? WHERE id_usuario = ? AND id_musica = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, String.valueOf(reacao));
            statement.setInt(2, usuario.getId());
            statement.setInt(3, musica.getId());
            
            statement.executeUpdate();            
        }
    }
    
    private char consultarReacaoMusica(Usuario usuario, Musica musica) throws SQLException {
        
        String sql = "SELECT tipo_reacao FROM usuario_musica WHERE id_usuario = ? AND id_musica = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, usuario.getId());
            statement.setInt(2, musica.getId());
            
            ResultSet rs = statement.executeQuery();
            
            if (rs.next()) {
                return rs.getString("tipo_reacao").charAt(0);
            }
        }
        
        return SEM_REACAO;
    }
    
    public String verificarReacaoMusica(Usuario usuario, Musica musica, char reacao) throws SQLException {
        
        char reacaoAtual = consultarReacaoMusica(usuario, musica);
        
        if (reacaoAtual == reacao) {
            deletarReacaoMusica(usuario, musica);
            return reacao == 'C' ? "Música removida de curtidas." : "Música removida de descurtidas.";
        } else if (reacaoAtual == SEM_REACAO) {
            inserirReacaoMusica(usuario, musica, reacao);
            return reacao == 'C' ? "Música adicionada à curtidas." : "Música adicionada à descurtidas.";
        } else {
            editarReacaoMusica(usuario, musica, reacao);
            return reacao == 'C' ? "Música removida de descurtidas, e adicionada à curtidas." : "Música removida de curtidas, e adicionada à descurtidas.";
        }
    }
    
    public List<Musica> consultarMusicasUsuarioReacao(Usuario usuario, char tipoReacao) throws SQLException {
        
        List<Musica> resultadoConsulta = new ArrayList<>();
        
        String sql = "SELECT m.id_musica, m.titulo_musica, m.duracao, "
                    + "a.id_artista, a.nome_artista "
                    + "FROM musicas m "
                    + "JOIN usuario_musica um ON m.id_musica = um.id_musica "
                    + "JOIN artistas a ON m.id_artista = a.id_artista "
                    + "WHERE um.id_usuario = ? AND um.tipo_reacao = ?";
        
         try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, usuario.getId());
            statement.setString(2, String.valueOf(tipoReacao));
            
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
