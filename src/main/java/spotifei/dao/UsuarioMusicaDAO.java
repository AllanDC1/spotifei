/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spotifei.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}
