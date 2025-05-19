/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spotifei.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import spotifei.dao.Conexao;
import spotifei.model.Musica;
import spotifei.model.Usuario;
import spotifei.service.MusicaService;
import spotifei.view.BuscaMusicasPanel;
import spotifei.view.MusicasReagidasPanel;

/**
 * Controlador responsável pelas ações relacionadas à manipulação de músicas.
 *
 * <p>Permite buscar músicas, reagir a músicas (curtir/descurtir) e listar músicas com reações.
 * Atua como intermediário entre a interface gráfica e a camada de serviços.</p>
 */
public class MusicaController {
    
    private BuscaMusicasPanel buscaMusicasView;
    private MusicasReagidasPanel musicasReagidasView;
    
    /**
     * Realiza a busca de músicas com base no texto informado.
     *
     * @param textoPesquisa Texto digitado pelo usuário para filtrar as músicas.
     */
    public void listarMusicasPesquisa(String textoPesquisa) {
        
        if (textoPesquisa.equals("Pesquisar...")) {
            textoPesquisa = "";
        }
        
        try (Connection connection = Conexao.criarConexaoBD()) {
            
            MusicaService musicaService = new MusicaService(connection);
            List<Musica> resultadoPesquisa = musicaService.buscarMusicas(textoPesquisa);
            
            buscaMusicasView.atualizarListaMusicas(resultadoPesquisa);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro na busca: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Registra a reação de um usuário a uma música.
     *
     * @param usuario Usuário que está reagindo à música.
     * @param musica Música a ser reagida.
     * @param reacao Tipo de reação ('C' para curtir, 'D' para descurtir).
     * @return Mensagem de retorno da operação ou {@code null} em caso de erro.
     */
    public String reagirMusica(Usuario usuario, Musica musica, char reacao) {
        
        try (Connection connection = Conexao.criarConexaoBD()) {
            
            MusicaService musicaService = new MusicaService(connection);
            return musicaService.alterarReacaoMusica(usuario, musica, reacao);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    /**
     * Lista todas as músicas com determinada reação feitas por um usuário.
     *
     * @param usuario Usuário logado.
     * @param tipoReacao Tipo da reação a ser filtrada ('C' para curtidas, 'D' para descurtidas).
     */
    public void listarMusicasReagidas(Usuario usuario, char tipoReacao) {
        try (Connection connection = Conexao.criarConexaoBD()) {
            
            MusicaService musicaService = new MusicaService(connection);
            List<Musica> listaMusicasReacoes = musicaService.buscarMusicasUsuarioReacao(usuario, tipoReacao);
            
            musicasReagidasView.listarReacaoItem(listaMusicasReacoes);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setBuscaMusicasView(BuscaMusicasPanel buscaMusicasView) {
        this.buscaMusicasView = buscaMusicasView;
    }

    public void setMusicasReagidasView(MusicasReagidasPanel musicasReagidasView) {
        this.musicasReagidasView = musicasReagidasView;
    }
}
