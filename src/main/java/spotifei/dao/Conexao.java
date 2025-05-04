/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spotifei.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author adone
 */
public class Conexao {
        
    private static final String USUARIO = "postgres";
    private static final String SENHA = "admin";
    private static final String URL_BD = "jdbc:postgresql://localhost:5432/spotifei";
    
    public static Connection criarConexaoBD() throws SQLException {
        Connection conexao = DriverManager.getConnection(URL_BD, USUARIO, SENHA);
        return conexao;
    } 
}
