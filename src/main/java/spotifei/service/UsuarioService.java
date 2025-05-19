/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spotifei.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import spotifei.dao.PlaylistDAO;
import spotifei.dao.UsuarioDAO;
import spotifei.model.Playlist;
import spotifei.model.Usuario;

/**
 * Serviço responsável pelas operações relacionadas a usuários e suas playlists.
 * Atua como camada intermediária entre os controladores e os DAOs {@code UsuarioDAO} e {@code PlaylistDAO}.
 */
public class UsuarioService {
    
    private PlaylistDAO playlistDAO;
    private UsuarioDAO usuarioDAO;

    /**
     * Construtor que inicializa os DAOs com a conexão fornecida.
     *
     * @param connection Conexão ativa com o banco de dados.
     */
    public UsuarioService(Connection connection) {
        this.playlistDAO = new PlaylistDAO(connection);
        this.usuarioDAO = new UsuarioDAO(connection);
    }

    /**
     * Retorna a lista de playlists associadas a um usuário.
     *
     * @param usuario Usuário cujas playlists serão consultadas.
     * @return Lista de playlists do usuário.
     * @throws SQLException Caso ocorra um erro durante a consulta ao banco de dados.
     */
    public List<Playlist> getPlaylists(Usuario usuario) throws SQLException {
        return playlistDAO.consultarPorUsuario(usuario.getId());
    }
    
    /**
     * Cadastra um novo usuário no banco de dados.
     *
     * @param novoUsuario Objeto {@code Usuario} a ser inserido.
     * @throws SQLException Caso ocorra um erro durante a inserção.
     */
    public void cadastrarUsuario(Usuario novoUsuario) throws SQLException {
        usuarioDAO.inserirUsuario(novoUsuario);
    }
    
    /**
     * Realiza o login de um usuário com base no login e senha informados.
     *
     * @param login Nome de login do usuário.
     * @param senha Senha correspondente ao login.
     * @return Objeto {@code Usuario} se as credenciais forem válidas; caso contrário, {@code null}.
     * @throws SQLException Caso ocorra um erro durante a consulta.
     */
    public Usuario logarUsuario(String login, String senha) throws SQLException {
        return usuarioDAO.consultarLogin(login, senha);
    }
}
