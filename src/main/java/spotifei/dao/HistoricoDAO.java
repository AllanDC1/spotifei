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
import spotifei.model.Usuario;

/**
 *
 * @author adone
 */
public class HistoricoDAO {
    
    private Connection connection;

    public HistoricoDAO(Connection connection) {
        this.connection = connection;
    }
    
    public void inserirPesquisa(Usuario usuario, String textoPesquisa) throws SQLException {
        
        String sql = "INSERT INTO historico (id_usuario, texto_pesquisa) VALUES (?, ?) "
                    + "ON CONFLICT (id_usuario, texto_pesquisa) "
                    + "DO UPDATE SET data_pesquisa = NOW()";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, usuario.getId());
            statement.setString(2, textoPesquisa);

            statement.executeUpdate();
        }
    }
    
    public List<String> consultarHistoricoUsuario(Usuario usuario) throws SQLException {
        
        List<String> resultadoConsulta = new ArrayList<>();
        
        String sql = "SELECT texto_pesquisa FROM historico WHERE id_usuario = ? ORDER BY data_pesquisa DESC LIMIT 10";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, usuario.getId());

            ResultSet rs = statement.executeQuery();
            
            while(rs.next()) {
                resultadoConsulta.add(rs.getString("texto_pesquisa"));
            }
        }
        return resultadoConsulta;
    }
    
}
