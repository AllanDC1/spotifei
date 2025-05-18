/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spotifei.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import spotifei.dao.MusicaDAO;
import spotifei.dao.UsuarioMusicaDAO;
import spotifei.model.Musica;
import spotifei.model.Usuario;

/**
 *
 * @author adone
 */
public class MusicaService {
 
    private MusicaDAO musicaDAO;
    private UsuarioMusicaDAO usuarioMusicaDAO;

    public MusicaService(Connection connection) {
        this.musicaDAO = new MusicaDAO(connection);
        this.usuarioMusicaDAO = new UsuarioMusicaDAO(connection);
    }
    
    public List<Musica> buscarMusicas(String textoPesquisa) throws SQLException {
        return musicaDAO.consultarPorPesquisa(textoPesquisa);
    }
    
    public String alterarReacaoMusica(Usuario usuario, Musica musica, char reacao) throws SQLException {
       return usuarioMusicaDAO.verificarReacaoMusica(usuario, musica, reacao);
    }
    
    public List<Musica> buscarMusicasUsuarioReacao(Usuario usuario, char tipoReacao) throws SQLException {
        return usuarioMusicaDAO.consultarMusicasUsuarioReacao(usuario, tipoReacao);
    }
}
