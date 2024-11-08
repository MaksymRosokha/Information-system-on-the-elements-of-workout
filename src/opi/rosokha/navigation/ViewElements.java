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
import opi.rosokha.authorization.User;
import opi.rosokha.elements.CreateNewElement;
import opi.rosokha.settings.Setting;

/**
 *
 * @author Maksim
 */
public class ViewElements extends javax.swing.JInternalFrame {

    private static List<Element> elements;
    
    private Element frmElement = null;
    private FrmMain frmMain;
    private CreateNewElement frmCreateNewElement;
    
    int defaultWidthPanel;
    int defaultHeightPanel;
    
    /**
     * Creates new form ViewItems
     */
    public ViewElements(FrmMain frmMain) {
        initComponents();
        this.frmMain = frmMain;
        defaultWidthPanel = pnlForElements.getWidth();
        defaultHeightPanel = pnlForElements.getHeight() + 100;
    }

    public void createElementButtons(){
        pnlForElements.removeAll();
        elements = ConnectorWithDatabase.getListOfElements();
        pnlForElements.setSize(new Dimension(defaultWidthPanel, defaultHeightPanel));
        pnlForElements.setPreferredSize(new Dimension(defaultWidthPanel, defaultHeightPanel));
        int widthBetweenButtons = 10;
        int heightBetweenButtons = 10;
        for(int i = 0; i < elements.size(); i++){
            if(elements.get(i).getType().equals(Setting.getSorting()[0]) ||
               elements.get(i).getType().equals(Setting.getSorting()[1]) ||
               elements.get(i).getType().equals(Setting.getSorting()[2])){
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
                    pnlForElements.setSize(new Dimension(pnlForElements.getWidth(), pnlForElements.getHeight() + btnElement.getHeight()));
                    pnlForElements.setPreferredSize(new Dimension(pnlForElements.getWidth(), pnlForElements.getHeight() + btnElement.getHeight()));
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

                pnlForElements.add(lblNameElement);
                btnElement.setIcon(logo);
                pnlForElements.add(btnElement);
                lblNameElement.setVisible(true);
                btnElement.setVisible(true);
            }
        }
        
    }
    
    public void checkUserStatus(){
        if(FrmMain.getUser().getStatus().equals(User.ADMINISTRATOR)){
            btnCreateNewElement.setVisible(true);
            btnCreateNewElement.setIcon(new ImageIcon(new ImageIcon("Data\\Photo\\Create new element.png").getImage().getScaledInstance(70, 70, Image.SCALE_AREA_AVERAGING)));
        } else {
            btnCreateNewElement.setVisible(false);
        }
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBackground = new javax.swing.JPanel();
        skrlPane = new javax.swing.JScrollPane();
        pnlForElements = new javax.swing.JPanel();
        btnCreateNewElement = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setTitle("Перегляд елементів");
        setAutoscrolls(true);
        setPreferredSize(new java.awt.Dimension(471, 1024));

        pnlBackground.setBackground(new java.awt.Color(126, 126, 126));

        skrlPane.setBackground(new java.awt.Color(126, 126, 126));
        skrlPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        skrlPane.setAutoscrolls(true);

        pnlForElements.setBackground(new java.awt.Color(126, 126, 126));
        pnlForElements.setAutoscrolls(true);
        pnlForElements.setPreferredSize(new java.awt.Dimension(440, 1000));

        javax.swing.GroupLayout pnlForElementsLayout = new javax.swing.GroupLayout(pnlForElements);
        pnlForElements.setLayout(pnlForElementsLayout);
        pnlForElementsLayout.setHorizontalGroup(
            pnlForElementsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 726, Short.MAX_VALUE)
        );
        pnlForElementsLayout.setVerticalGroup(
            pnlForElementsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );

        skrlPane.setViewportView(pnlForElements);

        btnCreateNewElement.setContentAreaFilled(false);
        btnCreateNewElement.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCreateNewElement.setOpaque(false);
        btnCreateNewElement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateNewElementActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBackgroundLayout = new javax.swing.GroupLayout(pnlBackground);
        pnlBackground.setLayout(pnlBackgroundLayout);
        pnlBackgroundLayout.setHorizontalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(skrlPane, javax.swing.GroupLayout.DEFAULT_SIZE, 745, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBackgroundLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCreateNewElement)
                .addGap(10, 10, 10))
        );
        pnlBackgroundLayout.setVerticalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                .addComponent(skrlPane, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addComponent(btnCreateNewElement)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateNewElementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateNewElementActionPerformed
        frmMain.hideAllInternalFrames();
        if(frmCreateNewElement == null){
            frmCreateNewElement = new CreateNewElement();
            frmMain.getDesktopPane().add(frmCreateNewElement);
            frmCreateNewElement.setSize(frmMain.getDesktopPane().getWidth(), frmMain.getDesktopPane().getHeight());
        }
        frmCreateNewElement.setVisible(true);
    }//GEN-LAST:event_btnCreateNewElementActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreateNewElement;
    private javax.swing.JPanel pnlBackground;
    private javax.swing.JPanel pnlForElements;
    private javax.swing.JScrollPane skrlPane;
    // End of variables declaration//GEN-END:variables
}
