/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spotifei.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import spotifei.dao.HistoricoDAO;
import spotifei.model.Usuario;

/**
 *
 * @author adone
 */
public class HistoricoService {
    
    private HistoricoDAO historicoDAO;
    
    public HistoricoService(Connection connection) {
        this.historicoDAO = new HistoricoDAO(connection);
    }
    
    public void adicionarNovaPesquisa(Usuario usuario, String textoPesquisa) throws SQLException {
        historicoDAO.inserirPesquisa(usuario, textoPesquisa);
    }
    
    public List<String> buscarHistoricoUsuario(Usuario usuario) throws SQLException {
        return historicoDAO.consultarHistoricoUsuario(usuario);
    }
}
