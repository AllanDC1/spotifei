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
import spotifei.util.IndiceUsuarios;

/**
 *
 * @author adone
 */
public class UsuarioDAO {
    
    private Connection connection;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }
    
    public Usuario consultarLogin(String login, String senha) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE login_usuario = ? and senha_usuario = ?";
        
        try (PreparedStatement statement = connection.prepareCall(sql)) {
            statement.setString(1, login);
            statement.setString(2, senha);
            
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                        rs.getInt(IndiceUsuarios.ID_USUARIO),
                        rs.getString(IndiceUsuarios.LOGIN_USUARIO),
                        rs.getString(IndiceUsuarios.SENHA_USUARIO),
                        rs.getString(IndiceUsuarios.NOME_USUARIO)
                    );
                }
            }
        }
        return null; // caso nao tenha encontrado uma correspondencia
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
