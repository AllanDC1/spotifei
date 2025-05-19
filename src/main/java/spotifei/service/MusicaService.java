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
 * Serviço responsável pelas operações relacionadas a músicas e reações de usuários.
 * Atua como intermediário entre os controladores e os DAOs {@code MusicaDAO} e {@code UsuarioMusicaDAO}.
 */
public class MusicaService {
 
    private MusicaDAO musicaDAO;
    private UsuarioMusicaDAO usuarioMusicaDAO;

    /**
     * Construtor que inicializa os DAOs com a conexão fornecida.
     *
     * @param connection Conexão ativa com o banco de dados.
     */
    public MusicaService(Connection connection) {
        this.musicaDAO = new MusicaDAO(connection);
        this.usuarioMusicaDAO = new UsuarioMusicaDAO(connection);
    }
    
    /**
     * Realiza a busca de músicas com base em um texto de pesquisa.
     *
     * @param textoPesquisa Texto a ser utilizado na consulta.
     * @return Lista de músicas que correspondem ao texto pesquisado.
     * @throws SQLException Caso ocorra um erro durante a consulta ao banco de dados.
     */
    public List<Musica> buscarMusicas(String textoPesquisa) throws SQLException {
        return musicaDAO.consultarPorPesquisa(textoPesquisa);
    }
    
    /**
     * Altera ou registra uma reação (como curtir ou descurtir) de um usuário a uma música.
     *
     * @param usuario Usuário que está reagindo à música.
     * @param musica  Música que está recebendo a reação.
     * @param reacao  Tipo de reação (ex: 'C' para curtida, 'D' para descurtida).
     * @return Mensagem indicando o resultado da operação.
     * @throws SQLException Caso ocorra um erro durante o processo no banco de dados.
     */
    public String alterarReacaoMusica(Usuario usuario, Musica musica, char reacao) throws SQLException {
       return usuarioMusicaDAO.verificarReacaoMusica(usuario, musica, reacao);
    }
    
    /**
     * Retorna uma lista de músicas com base na reação do usuário (ex: curtidas ou descurtidas).
     *
     * @param usuario     Usuário cujas reações serão consultadas.
     * @param tipoReacao  Tipo de reação a ser filtrada ('C' para curtidas, 'D' para descurtidas).
     * @return Lista de músicas que o usuário reagiu com o tipo especificado.
     * @throws SQLException Caso ocorra um erro ao consultar o banco de dados.
     */
    public List<Musica> buscarMusicasUsuarioReacao(Usuario usuario, char tipoReacao) throws SQLException {
        return usuarioMusicaDAO.consultarMusicasUsuarioReacao(usuario, tipoReacao);
    }
}
