/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spotifei.controller;

import java.awt.CardLayout;
import javax.swing.JPanel;
import spotifei.model.Playlist;
import spotifei.view.BuscaMusicasPanel;
import spotifei.view.GerenciarPlaylistsPanel;
import spotifei.view.ExibirPlaylistPanel;
import spotifei.view.MusicasReagidasPanel;
import spotifei.view.SpotifeiFrame;

/**
 * Controlador responsável por gerenciar a navegação entre os painéis principais da aplicação.
 *
 * <p>Utiliza um {@code CardLayout} para alternar entre diferentes views dentro do painel principal
 * da interface {@code SpotifeiFrame}.</p>
 */
public class MainFrameController {
    
    private JPanel pnlMain;
    private CardLayout cl;

    /**
     * Construtor do controlador principal.
     *
     * @param spotifeiView A view principal da aplicação que contém o painel onde as telas são exibidas.
     */
    public MainFrameController(SpotifeiFrame spotifeiView) {        
        this.pnlMain = spotifeiView.getPnlMainSpotifei();
        this.cl = (CardLayout)(pnlMain.getLayout());
    }
    
    /**
     * Navega para o painel de busca de músicas.
     */
    public void navegarMusicas() {
        pnlMain.add(new BuscaMusicasPanel(new MusicaController(), new HistoricoController()), "buscarMusicas");
        cl.show(pnlMain, "buscarMusicas");
        pnlMain.revalidate();
        pnlMain.repaint();
    }
    
    /**
     * Navega para o painel de gerenciamento de playlists.
     */
    public void navegarPlaylists() {
        pnlMain.add(new GerenciarPlaylistsPanel(this, new PlaylistController()), "gerenciarPlaylists");
        cl.show(pnlMain, "gerenciarPlaylists");
        pnlMain.revalidate();
        pnlMain.repaint();
    }
    
    /**
     * Exibe o painel de visualização de uma playlist específica.
     *
     * @param playlist A playlist que será exibida.
     */
    public void exibirPlaylist(Playlist playlist) {
        pnlMain.add(new ExibirPlaylistPanel(this, new PlaylistController(), playlist), "exibirPlaylist");
        cl.show(pnlMain, "exibirPlaylist");
    }
    
    /**
     * Navega para o painel de músicas curtidas pelo usuário.
     */
    public void navegarCurtidas() {
        pnlMain.add(new MusicasReagidasPanel(new MusicaController(), "Curtidas"), "exibirCurtidas");
        cl.show(pnlMain, "exibirCurtidas");
        pnlMain.revalidate();
        pnlMain.repaint();
    }
    
    /**
     * Navega para o painel de músicas descurtidas pelo usuário.
     */
    public void navegarDescurtidas() {
        pnlMain.add(new MusicasReagidasPanel(new MusicaController(), "Descurtidas"), "exibirDescurtidas");
        cl.show(pnlMain, "exibirDescurtidas");
        pnlMain.revalidate();
        pnlMain.repaint();
    }
}
