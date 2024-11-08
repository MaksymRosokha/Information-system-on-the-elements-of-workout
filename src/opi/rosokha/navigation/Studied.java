/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package opi.rosokha.navigation;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import opi.rosokha.ConnectorWithDatabase;
import opi.rosokha.FrmMain;
import opi.rosokha.elements.Element;

/**
 *
 * @author Maksim
 */
public class Studied extends javax.swing.JInternalFrame {
    private static List<Element> elements;
    private FrmMain frmMain;
    private Element frmElement = null;
    
    int defaultWidthPanel;
    int defaultHeightPanel;
    /**
     * Creates new form Studied
     */
    public Studied(FrmMain frmMain) {
        initComponents();
        this.frmMain = frmMain;
        defaultWidthPanel = pnlBackground.getWidth();
        defaultHeightPanel = pnlBackground.getHeight() + 100;
    }
    
    public void createElementButtons() {
        pnlBackground.removeAll();
        elements = ConnectorWithDatabase.getListOfStudiedElements();
        pnlBackground.setSize(new Dimension(defaultWidthPanel, defaultHeightPanel));
        pnlBackground.setPreferredSize(new Dimension(defaultWidthPanel, defaultHeightPanel));
        int widthBetweenButtons = 10;
        int heightBetweenButtons = 10;
        for(int i = 0; i < elements.size(); i++){
            int id = elements.get(i).getId();
            String name = elements.get(i).getName();
            String type = elements.get(i).getType();
            String description = elements.get(i).getDescription();
            
            ImageIcon logo = new ImageIcon(new ImageIcon("Data\\Photo\\elements\\" + elements.get(i).getName() + ".png").getImage().getScaledInstance(245, 250, Image.SCALE_AREA_AVERAGING));
            
            JButton btnElement = new JButton();
            btnElement.setHorizontalAlignment(JLabel.CENTER);
            btnElement.setVerticalAlignment(JLabel.TOP);
            btnElement.setSize(250, 300);
            btnElement.setBackground(Color.BLACK);
            btnElement.setCursor(new Cursor(Cursor.HAND_CURSOR));
            
            JLabel lblNameElement = new JLabel(name);
            lblNameElement.setForeground(Color.orange);
            lblNameElement.setCursor(new Cursor(Cursor.HAND_CURSOR));
            lblNameElement.setVerticalAlignment(JLabel.BOTTOM);
            lblNameElement.setFont(new Font(Font.SERIF,  Font.BOLD, 18));
            lblNameElement.setBounds(widthBetweenButtons +10, heightBetweenButtons + btnElement.getHeight() - 42, btnElement.getWidth() - 20, 30);
            
            btnElement.setBounds(widthBetweenButtons, heightBetweenButtons, btnElement.getWidth(), btnElement.getHeight());
            widthBetweenButtons += btnElement.getWidth() + 10;
            if(widthBetweenButtons > frmMain.getDesktopPane().getWidth() - btnElement.getWidth()){
                widthBetweenButtons = 10;
                heightBetweenButtons += btnElement.getHeight() + 10;
                pnlBackground.setSize(new Dimension(pnlBackground.getWidth(), pnlBackground.getHeight() + btnElement.getHeight()));
                pnlBackground.setPreferredSize(new Dimension(pnlBackground.getWidth(), pnlBackground.getHeight() + btnElement.getHeight()));
            }
            btnElement.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frmMain.hideAllInternalFrames();
                    if(frmElement == null){
                        frmElement = new Element(id,name,type,description);
                        frmMain.getDesktopPane().add(frmElement);
                        frmElement.setSize(frmMain.getDesktopPane().getWidth(), frmMain.getDesktopPane().getHeight());
                    } else {
                        frmElement.setId(id);
                        frmElement.setName(name);
                        frmElement.setType(type);
                        frmElement.setDescription(description);
                        frmElement.setUserStatus(FrmMain.getUser().getStatus());
                    }
                    frmElement.markCheckBoxes();
                    frmElement.setVisible(true);
                }
            });

            pnlBackground.add(lblNameElement);
            btnElement.setIcon(logo);
            pnlBackground.add(btnElement);
            lblNameElement.setVisible(true);
            btnElement.setVisible(true);
        }
        
    }
    
    public static List<Element> getElements() {
        return elements;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        skrlPane = new javax.swing.JScrollPane();
        pnlBackground = new javax.swing.JPanel();

        setTitle("Вивчені");

        pnlBackground.setBackground(new java.awt.Color(126, 126, 126));
        pnlBackground.setPreferredSize(new java.awt.Dimension(392, 1000));

        javax.swing.GroupLayout pnlBackgroundLayout = new javax.swing.GroupLayout(pnlBackground);
        pnlBackground.setLayout(pnlBackgroundLayout);
        pnlBackgroundLayout.setHorizontalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 392, Short.MAX_VALUE)
        );
        pnlBackgroundLayout.setVerticalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );

        skrlPane.setViewportView(pnlBackground);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(skrlPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(skrlPane, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnlBackground;
    private javax.swing.JScrollPane skrlPane;
    // End of variables declaration//GEN-END:variables
}
