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
import org.mindrot.jbcrypt.BCrypt;
import spotifei.model.Usuario;

/**
 * Classe responsável por acessar e manipular os dados dos usuários no banco de dados.
 */
public class UsuarioDAO {
    
    private Connection connection;    

    /**
     * Construtor que recebe a conexão com o banco de dados.
     * 
     * @param connection conexão ativa com o banco
     */
    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }
    
    /**
     * Verifica se o login e a senha informados correspondem a um usuário existente.
     * 
     * @param login login informado pelo usuário
     * @param senha senha informada (em texto plano)
     * @return objeto Usuario se o login for válido, ou null caso contrário
     * @throws SQLException em caso de erro na consulta
     */
    public Usuario consultarLogin(String login, String senha) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE login_usuario = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, login);
            
            try (ResultSet rs = statement.executeQuery()) {
                
                if (rs.next() && BCrypt.checkpw(senha, rs.getString("senha_usuario"))) {                    
                    return new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("login_usuario"),
                        rs.getString("senha_usuario"),
                        rs.getString("nome_usuario")
                    );
                }
            }
        }
        return null; // caso nao tenha encontrado uma correspondencia
    }
    
    /**
     * Insere um novo usuário no banco de dados.
     * 
     * @param usuario objeto contendo os dados do novo usuário
     * @throws SQLException em caso de erro na inserção
     */
    public void inserirUsuario(Usuario usuario) throws SQLException {             
        String sql = "INSERT INTO usuarios(login_usuario, senha_usuario, nome_usuario) VALUES (?, ?, ?)";
        
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, usuario.getLogin());
            statement.setString(2, usuario.getSenha());
            statement.setString(3, usuario.getNome());
            
            int linhasCriadas = statement.executeUpdate();
            
            if (linhasCriadas > 0) {
                ResultSet keysGeradas = statement.getGeneratedKeys();
                if (keysGeradas.next()) {
                    usuario.setId(keysGeradas.getInt(1));
                }
            }
        }
    }
}
