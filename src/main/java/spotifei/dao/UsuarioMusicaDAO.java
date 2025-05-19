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
 * Classe responsável por gerenciar as reações (curtida/descurtida) de usuários em músicas.
 */
public class UsuarioMusicaDAO {
    
    private Connection connection;    
    private static final char SEM_REACAO = '-';

    /**
     * Construtor que recebe a conexão com o banco.
     */
    public UsuarioMusicaDAO(Connection connection) {
        this.connection = connection;
    }
    
    /**
     * Insere uma reação (curtida ou descurtida) do usuário em uma música.
     * 
     * @param usuario Usuário que está reagindo.
     * @param musica Música alvo da reação.
     * @param reacao Tipo da reação (ex: 'C' para curtir, 'D' para descurtir).
     * @throws SQLException Caso ocorra erro ao inserir a reação.
     */
    private void inserirReacaoMusica(Usuario usuario, Musica musica, char reacao) throws SQLException {
        
        String sql = "INSERT INTO usuario_musica (id_usuario, id_musica, tipo_reacao) VALUES (?, ?, ?)";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, usuario.getId());
            statement.setInt(2, musica.getId());
            statement.setString(3, String.valueOf(reacao));
            
            statement.executeUpdate();            
        }
    }
    
    /**
     * Deleta a reação do usuário para uma música no banco.
     *
     * @param usuario Usuário que quer remover a reação.
     * @param musica Música alvo da remoção.
     * @throws SQLException Caso ocorra erro ao deletar a reação.
     */
    private void deletarReacaoMusica(Usuario usuario, Musica musica) throws SQLException {
        
        String sql = "DELETE FROM usuario_musica WHERE id_usuario = ? AND id_musica = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, usuario.getId());
            statement.setInt(2, musica.getId());
            
            statement.executeUpdate();            
        }
    }
    
    /**
     * Atualiza a reação do usuário para uma música no banco.
     *
     * @param usuario Usuário que está modificando a reação.
     * @param musica Música alvo da alteração.
     * @param reacao Novo tipo de reação.
     * @throws SQLException Caso ocorra erro ao atualizar a reação.
     */
    private void editarReacaoMusica(Usuario usuario, Musica musica, char reacao) throws SQLException {
        
        String sql = "UPDATE usuario_musica SET tipo_reacao = ? WHERE id_usuario = ? AND id_musica = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, String.valueOf(reacao));
            statement.setInt(2, usuario.getId());
            statement.setInt(3, musica.getId());
            
            statement.executeUpdate();            
        }
    }
    
    /**
     * Consulta a reação atual do usuário para uma música.
     *
     * @param usuario Usuário consultado.
     * @param musica Música consultada.
     * @return O caractere representando a reação atual ou '-' se nenhuma reação existir.
     * @throws SQLException Caso ocorra erro na consulta.
     */
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
    
    /**
     * Verifica e alterna a reação do usuário para uma música.
     * Se a reação atual for igual à informada, remove a reação.
     * Se for diferente, atualiza para a nova reação.
     * Se não houver reação, insere a nova.
     *
     * @param usuario Usuário que está reagindo.
     * @param musica Música alvo da reação.
     * @param reacao Tipo da reação ('C' para curtir, 'D' para descurtir).
     * @return Mensagem indicando a ação executada.
     * @throws SQLException Caso ocorra erro ao manipular a reação.
     */
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
    
    /**
     * Consulta as músicas associadas a um usuário por tipo de reação.
     *
     * @param usuario Usuário alvo da consulta.
     * @param tipoReacao Tipo da reação que se quer filtrar ('C', 'D', etc).
     * @return Lista de músicas com a reação especificada.
     * @throws SQLException Caso ocorra erro na consulta.
     */
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
