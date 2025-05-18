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
 *
 * @author adone
 */
public class MainFrameController {
    
    private JPanel pnlMain;
    private CardLayout cl;

    public MainFrameController(SpotifeiFrame spotifeiView) {        
        this.pnlMain = spotifeiView.getPnlMainSpotifei();
        this.cl = (CardLayout)(pnlMain.getLayout());
    }
    
    public void navegarMusicas() {
        pnlMain.add(new BuscaMusicasPanel(new MusicaController()), "buscarMusicas");
        cl.show(pnlMain, "buscarMusicas");
        pnlMain.revalidate();
        pnlMain.repaint();
    }
    
    public void navegarPlaylists() {
        pnlMain.add(new GerenciarPlaylistsPanel(this, new PlaylistController()), "gerenciarPlaylists");
        cl.show(pnlMain, "gerenciarPlaylists");
        pnlMain.revalidate();
        pnlMain.repaint();
    }
    
    public void exibirPlaylist(Playlist playlist) {
        pnlMain.add(new ExibirPlaylistPanel(this, new PlaylistController(), playlist), "exibirPlaylist");
        cl.show(pnlMain, "exibirPlaylist");
    }
    
    public void navegarCurtidas() {
        pnlMain.add(new MusicasReagidasPanel(new MusicaController(), "Curtidas"), "exibirCurtidas");
        cl.show(pnlMain, "exibirCurtidas");
        pnlMain.revalidate();
        pnlMain.repaint();
    }
    
    public void navegarDescurtidas() {
        pnlMain.add(new MusicasReagidasPanel(new MusicaController(), "Descurtidas"), "exibirDescurtidas");
        cl.show(pnlMain, "exibirDescurtidas");
        pnlMain.revalidate();
        pnlMain.repaint();
    }
}
