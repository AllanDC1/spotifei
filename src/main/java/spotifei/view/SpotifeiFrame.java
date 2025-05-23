/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package spotifei.view;

//import java.awt.CardLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import spotifei.app.Sessao;
import spotifei.controller.MainFrameController;

/**
 * Janela principal da aplicação Spotifei.
 * Responsável por inicializar a interface gráfica e gerenciar a navegação entre as seções da aplicação.
 */
public class SpotifeiFrame extends javax.swing.JFrame {
    
    private MainFrameController controller;
    
    /**
     * Construtor da janela principal.
     * Inicializa os componentes da interface e o controlador responsável pela navegação.
     */
    public SpotifeiFrame() {
        initComponents();
        controller = new MainFrameController(this);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlNavegacaoSpotifei = new javax.swing.JPanel();
        btnPlaylistNavegacao = new javax.swing.JButton();
        btnMusicasNavegacao = new javax.swing.JButton();
        btnCurtidasNavegacao = new javax.swing.JButton();
        btnDescurtidasNavegacao = new javax.swing.JButton();
        btnSairNavegacao = new javax.swing.JButton();
        lblSpotifeiMain = new javax.swing.JLabel();
        pnlMainSpotifei = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Spotifei");
        setMinimumSize(new java.awt.Dimension(1024, 768));

        pnlNavegacaoSpotifei.setBackground(new java.awt.Color(30, 30, 30));

        btnPlaylistNavegacao.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnPlaylistNavegacao.setText("Playlists");
        btnPlaylistNavegacao.setBorderPainted(false);
        btnPlaylistNavegacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlaylistNavegacaoActionPerformed(evt);
            }
        });

        btnMusicasNavegacao.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnMusicasNavegacao.setText("Músicas");
        btnMusicasNavegacao.setBorderPainted(false);
        btnMusicasNavegacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMusicasNavegacaoActionPerformed(evt);
            }
        });

        btnCurtidasNavegacao.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnCurtidasNavegacao.setText("Curtidas");
        btnCurtidasNavegacao.setBorderPainted(false);
        btnCurtidasNavegacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCurtidasNavegacaoActionPerformed(evt);
            }
        });

        btnDescurtidasNavegacao.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnDescurtidasNavegacao.setText("Descurtidas");
        btnDescurtidasNavegacao.setBorderPainted(false);
        btnDescurtidasNavegacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescurtidasNavegacaoActionPerformed(evt);
            }
        });

        btnSairNavegacao.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnSairNavegacao.setText("Sair");
        btnSairNavegacao.setBorderPainted(false);
        btnSairNavegacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairNavegacaoActionPerformed(evt);
            }
        });

        lblSpotifeiMain.setBackground(new java.awt.Color(0, 0, 0));
        lblSpotifeiMain.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblSpotifeiMain.setForeground(new java.awt.Color(0, 153, 0));
        lblSpotifeiMain.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ImageIcon icon = new ImageIcon(getClass().getResource("/images/spotifeiLogo.png"));
        Image img = icon.getImage().getScaledInstance(120, 40, Image.SCALE_SMOOTH);
        lblSpotifeiMain.setIcon(new ImageIcon(img));
        lblSpotifeiMain.setToolTipText("");

        javax.swing.GroupLayout pnlNavegacaoSpotifeiLayout = new javax.swing.GroupLayout(pnlNavegacaoSpotifei);
        pnlNavegacaoSpotifei.setLayout(pnlNavegacaoSpotifeiLayout);
        pnlNavegacaoSpotifeiLayout.setHorizontalGroup(
            pnlNavegacaoSpotifeiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNavegacaoSpotifeiLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlNavegacaoSpotifeiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSpotifeiMain, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlNavegacaoSpotifeiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnPlaylistNavegacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnMusicasNavegacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCurtidasNavegacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDescurtidasNavegacao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                        .addComponent(btnSairNavegacao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        pnlNavegacaoSpotifeiLayout.setVerticalGroup(
            pnlNavegacaoSpotifeiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNavegacaoSpotifeiLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblSpotifeiMain, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(132, 132, 132)
                .addComponent(btnMusicasNavegacao, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btnPlaylistNavegacao, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(btnCurtidasNavegacao, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btnDescurtidasNavegacao, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSairNavegacao, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
        );

        pnlMainSpotifei.setBackground(new java.awt.Color(44, 44, 44));
        pnlMainSpotifei.setMinimumSize(new java.awt.Dimension(850, 768));
        pnlMainSpotifei.setPreferredSize(new java.awt.Dimension(850, 768));
        pnlMainSpotifei.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlNavegacaoSpotifei, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlMainSpotifei, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlNavegacaoSpotifei, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlMainSpotifei, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Ação executada ao clicar no botão de navegação para a seção de músicas.
     *
     * @param evt Evento de clique do botão.
     */
    private void btnMusicasNavegacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMusicasNavegacaoActionPerformed
        controller.navegarMusicas();
    }//GEN-LAST:event_btnMusicasNavegacaoActionPerformed

    /**
     * Ação executada ao clicar no botão de navegação para a seção de playlists.
     *
     * @param evt Evento de clique do botão.
     */
    private void btnPlaylistNavegacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlaylistNavegacaoActionPerformed
        controller.navegarPlaylists();
    }//GEN-LAST:event_btnPlaylistNavegacaoActionPerformed

    /**
     * Ação executada ao clicar no botão de navegação para a seção de músicas curtidas.
     *
     * @param evt Evento de clique do botão.
     */
    private void btnCurtidasNavegacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCurtidasNavegacaoActionPerformed
        controller.navegarCurtidas();
    }//GEN-LAST:event_btnCurtidasNavegacaoActionPerformed

    /**
     * Ação executada ao clicar no botão de navegação para a seção de músicas descurtidas.
     *
     * @param evt Evento de clique do botão.
     */
    private void btnDescurtidasNavegacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescurtidasNavegacaoActionPerformed
        controller.navegarDescurtidas();
    }//GEN-LAST:event_btnDescurtidasNavegacaoActionPerformed

    /**
     * Ação executada ao clicar no botão de navegação para sair.
     *
     * @param evt Evento de clique do botão.
     */    
    private void btnSairNavegacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairNavegacaoActionPerformed
        JOptionPane.showMessageDialog(null, "Obrigado pela presença!", "Saindo...", JOptionPane.INFORMATION_MESSAGE);
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
        Sessao.logout();
        
        this.dispose();
    }//GEN-LAST:event_btnSairNavegacaoActionPerformed

    public JPanel getPnlMainSpotifei() {
        return pnlMainSpotifei;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCurtidasNavegacao;
    private javax.swing.JButton btnDescurtidasNavegacao;
    private javax.swing.JButton btnMusicasNavegacao;
    private javax.swing.JButton btnPlaylistNavegacao;
    private javax.swing.JButton btnSairNavegacao;
    private javax.swing.JLabel lblSpotifeiMain;
    private javax.swing.JPanel pnlMainSpotifei;
    private javax.swing.JPanel pnlNavegacaoSpotifei;
    // End of variables declaration//GEN-END:variables
}
