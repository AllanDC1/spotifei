/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spotifei.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 * Classe responsável por fornecer conexões com o banco de dados PostgreSQL.
 *
 * <p>Esta classe utiliza um arquivo externo de propriedades {@code bd.properties},
 * localizado em {@code src/main/resources}, para carregar dinamicamente as
 * informações de conexão (URL, usuário e senha).</p>
 *
 */
public class Conexao {
        
    private static final Properties propriedades = new Properties();
    
    static {
        try (InputStream input = Conexao.class.getClassLoader().getResourceAsStream("bd.properties")) {
            if (input == null) {
                JOptionPane.showMessageDialog(null, "Arquivo de configuração 'bd.properties' não encontrado: ", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                propriedades.load(input);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar o arquivo de configuração: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }    
    /**
     * Cria e retorna uma nova conexão com o banco de dados PostgreSQL, utilizando
     * as propriedades carregadas do arquivo {@code bd.properties}.
     *
     * @return {@link Connection} uma conexão ativa com o banco de dados
     * @throws SQLException se ocorrer um erro ao tentar se conectar
     */
    public static Connection criarConexaoBD() throws SQLException {
        String usuario = propriedades.getProperty("bd.usuario");
        String senha = propriedades.getProperty("bd.senha");
        String url = propriedades.getProperty("bd.url");
        
        Connection conexao = DriverManager.getConnection(url, usuario, senha);
        return conexao;
    } 
}
