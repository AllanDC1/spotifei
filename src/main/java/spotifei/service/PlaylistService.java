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
 * Serviço responsável por gerenciar operações relacionadas a playlists e suas músicas.
 * Atua como intermediário entre os controladores e os DAOs {@code PlaylistDAO} e {@code MusicaDAO}.
 */
public class PlaylistService {
    private MusicaDAO musicaDAO;
    private PlaylistDAO playlistDAO;

    /**
     * Construtor que inicializa os DAOs com a conexão fornecida.
     *
     * @param connection Conexão ativa com o banco de dados.
     */
    public PlaylistService(Connection connection) {
        this.musicaDAO = new MusicaDAO(connection);
        this.playlistDAO = new PlaylistDAO(connection);
    }

    /**
     * Retorna a lista de músicas pertencentes a uma playlist.
     *
     * @param playlist Playlist cujas músicas serão consultadas.
     * @return Lista de músicas da playlist.
     * @throws SQLException Caso ocorra um erro ao acessar o banco de dados.
     */
    public List<Musica> getMusicas(Playlist playlist) throws SQLException {
        return musicaDAO.consultarPorPlaylist(playlist.getId());
    }
    
    /**
     * Cadastra uma nova playlist no banco de dados.
     *
     * @param playlist Objeto {@code Playlist} a ser inserido.
     * @throws SQLException Caso ocorra um erro durante a inserção.
     */
    public void cadastrarPlaylist(Playlist playlist) throws SQLException {
        playlistDAO.inserirPlaylist(playlist);
    }
    
    /**
     * Altera o nome de uma playlist existente.
     *
     * @param idPlaylistAlterada ID da playlist a ser alterada.
     * @param novoNome Novo nome para a playlist.
     * @throws SQLException Caso ocorra um erro durante a atualização.
     */
    public void alterarPlaylist(int idPlaylistAlterada, String novoNome) throws SQLException {
        playlistDAO.editarPlaylist(idPlaylistAlterada, novoNome);
    }
    
    /**
     * Remove uma playlist do banco de dados.
     *
     * @param playlist Playlist a ser removida.
     * @throws SQLException Caso ocorra um erro durante a exclusão.
     */
    public void removerPlaylist(Playlist playlist) throws SQLException {
        playlistDAO.deletarPlaylist(playlist);
    }
    
    /**
     * Adiciona uma música a uma playlist.
     *
     * @param playlist Playlist que receberá a música.
     * @param musica Música a ser adicionada.
     * @throws SQLException Caso ocorra um erro durante a inserção.
     */
    public void adicionarMusicaPlaylist(Playlist playlist, Musica musica) throws SQLException {
        playlistDAO.inserirMusicaPlaylist(playlist, musica);
    }
    
    /**
     * Remove uma música de uma playlist.
     *
     * @param playlist Playlist da qual a música será removida.
     * @param musica Música a ser removida.
     * @throws SQLException Caso ocorra um erro durante a remoção.
     */
    public void removerMusicaPlaylist(Playlist playlist, Musica musica) throws SQLException {
        playlistDAO.deletarMusicaPlaylist(playlist, musica);
    }
}
