/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spotifei.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import spotifei.model.Usuario;

/**
 *
 * @author adone
 */
public class UsuarioDAO {
    
    private Connection connection;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }       
    
    public ResultSet consultar(Usuario usuario) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE login_usuario = ? and senha_usuario = ?";
        
        try (PreparedStatement statement = connection.prepareCall(sql)) {
            statement.setString(1, usuario.getLogin());
            statement.setString(2, usuario.getSenha());            
            
            return statement.executeQuery();
        }
    }
    
    public void inserir(Usuario usuario) throws SQLException {             
        String sql = "INSERT INTO usuarios(login_usuario, senha_usuario, nome_usuario) VALUES (?, ?, ?)";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, usuario.getLogin());
            statement.setString(2, usuario.getSenha());
            statement.setString(3, usuario.getNome());
            
            statement.execute();
        }
    }   
}
