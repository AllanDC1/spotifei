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
import spotifei.view.SpotifeiFrame;

/**
 *
 * @author adone
 */
public class SpotifeiFrameController {
    
    private SpotifeiFrame spotifeiView;
    private JPanel pnlMain;
    private CardLayout cl;

    public SpotifeiFrameController(SpotifeiFrame spotifeiView) {
        this.spotifeiView = spotifeiView;
        
        this.pnlMain = spotifeiView.getPnlMainSpotifei();
        this.cl = (CardLayout)(pnlMain.getLayout());
    }
    
    public void navegarMusicas() {
        pnlMain.add(new BuscaMusicasPanel(), "buscarMusicas");
        cl.show(pnlMain, "buscarMusicas");
    }
    
    public void navegarPlaylists() {
        pnlMain.add(new GerenciarPlaylistsPanel(this), "gerenciarPlaylists");
        cl.show(pnlMain, "gerenciarPlaylists");
    }
    
    public void exibirPlaylist(Playlist playlist) {
        pnlMain.add(new ExibirPlaylistPanel(playlist), "exibirPlaylist");
        cl.show(pnlMain, "exibirPlaylist");
    }
    
}
