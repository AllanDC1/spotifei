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
 * Serviço responsável por gerenciar o histórico de pesquisas dos usuários.
 * Atua como camada intermediária entre a interface e o {@code HistoricoDAO}.
 */
public class HistoricoService {
    
    private HistoricoDAO historicoDAO;
    
    /**
     * Construtor que inicializa o serviço com uma instância do DAO, utilizando a conexão fornecida.
     *
     * @param connection Conexão ativa com o banco de dados.
     */
    public HistoricoService(Connection connection) {
        this.historicoDAO = new HistoricoDAO(connection);
    }
    
    /**
     * Adiciona um novo termo de pesquisa ao histórico do usuário.
     *
     * @param usuario        Usuário que realizou a pesquisa.
     * @param textoPesquisa  Texto pesquisado que será registrado no histórico.
     * @throws SQLException  Caso ocorra um erro ao inserir no banco de dados.
     */
    public void adicionarNovaPesquisa(Usuario usuario, String textoPesquisa) throws SQLException {
        historicoDAO.inserirPesquisa(usuario, textoPesquisa);
    }
    
    /**
     * Retorna uma lista de termos pesquisados anteriormente pelo usuário.
     *
     * @param usuario        Usuário cujo histórico será consultado.
     * @return               Lista de termos de pesquisa.
     * @throws SQLException  Caso ocorra um erro ao consultar o banco de dados.
     */
    public List<String> buscarHistoricoUsuario(Usuario usuario) throws SQLException {
        return historicoDAO.consultarHistoricoUsuario(usuario);
    }
}
