/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spotifei.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import spotifei.dao.MusicaDAO;
import spotifei.model.Musica;

/**
 *
 * @author adone
 */
public class MusicaService {
 
    private MusicaDAO musicaDAO;

    public MusicaService(Connection connection) {
        this.musicaDAO = new MusicaDAO(connection);
    }
    
    public List<Musica> buscarMusicas(String textoPesquisa) throws SQLException {
        return musicaDAO.consultarPorPesquisa(textoPesquisa);
    }
}
