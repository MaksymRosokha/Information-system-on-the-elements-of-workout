/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package opi.rosokha;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JTextField;
import opi.rosokha.authorization.*;
import opi.rosokha.certificate.*;
import opi.rosokha.elements.Element;
import opi.rosokha.navigation.*;
import opi.rosokha.settings.Setting;

/**
 *
 * @author Maksim
 */
public class FrmMain extends javax.swing.JFrame {
    
    private Authorization frmAuthorization;
    private Login frmLogin;
    private Registration frmRegistration;
    private AboutAuthor frmAboutAuthor;
    private AboutProgram frmAboutProgram;
    private Setting frmSettings;
    private Element frmElement;
    private ViewElements frmViewElements;
    private GeneralInformation frmGeneralInformation;
    private Favorites frmFavorites;
    private Studied frmStudied;
    
    
    private static User user = new User();
    
    /**
     * Creates new form MainJFrame
     */
    public FrmMain() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);//для відкриття вікна у повний розмір
        checkUserStatus();
        createSearch();

        ImageIcon background = new ImageIcon(new ImageIcon("Data\\Photo\\Background.png").getImage().getScaledInstance(1180, this.getHeight() - 125, Image.SCALE_AREA_AVERAGING));
        ImageIcon logo = new ImageIcon(new ImageIcon("Data\\Photo\\Logo.png").getImage().getScaledInstance(350, 237, Image.SCALE_AREA_AVERAGING));
        lblLogo.setIcon(logo);
        lblMainBackground.setIcon(background);
        
        Element.setFrmMain(this);
        
        ConnectorWithDatabase.doConnect();
    }

    public void checkUserStatus(){
        if(user.getStatus().equals(User.ADMINISTRATOR)) {
             btnFavorites.setVisible(true);
             btnStudied.setVisible(true);
             btnSignOut.setVisible(true);
             btnAuthorization.setVisible(false);
        }else if(user.getStatus().equals(User.REGISTERED_USER)){
             btnFavorites.setVisible(true);
             btnStudied.setVisible(true);
             btnSignOut.setVisible(true);
             btnAuthorization.setVisible(false);
        }else{
             btnFavorites.setVisible(false);
             btnStudied.setVisible(false);
             btnSignOut.setVisible(false);
             btnAuthorization.setVisible(true);
        }
    }

    private void createSearch(){
        JTextField txtFldSearch = new JTextField("Пошук");
        txtFldSearch.setSize(200, 30);
        txtFldSearch.setFont(new Font(Font.SERIF,  Font.PLAIN, 15));
        txtFldSearch.addMouseListener(new MouseListener(){ 
            @Override
            public void mousePressed(MouseEvent e) {
                if(txtFldSearch.getText().equals("Пошук"))
                    txtFldSearch.setText("");
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if(txtFldSearch.getText().equals(""))
                    txtFldSearch.setText("Пошук");
            }
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
        });
        
        JButton btnFind = new JButton();
        btnFind.setText("Знайти");
        btnFind.setFont(new Font(Font.SERIF,  Font.BOLD, 15));
        btnFind.setIcon(new ImageIcon(new ImageIcon("Data\\Photo\\Search.png").getImage().getScaledInstance(25, 25, Image.SCALE_AREA_AVERAGING)));
        btnFind.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnFind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                search(txtFldSearch.getText());  
            }
        });
        menuSearch.add(txtFldSearch);
        menuSearch.add(btnFind);
    }
    
    private void search(String txtSearch){
        if(frmViewElements == null){
            btnViewElementsActionPerformed(null);
            hideAllInternalFrames();  
        }
        for(Element element: ConnectorWithDatabase.getListOfElements()){
                if(txtSearch.equals(element.getName())){
                    if(frmElement == null){
                        frmElement = new Element(element.getId(), element.getName(),element.getType(),element.getDescription());
                        dsktpMain.add(frmElement);
                        frmElement.setSize(dsktpMain.getWidth(), dsktpMain.getHeight());
                    } else {
                        frmElement.setName(element.getName());
                        frmElement.setType(element.getType());
                        frmElement.setDescription(element.getDescription());
                        frmElement.setUserStatus(user.getStatus());
                    }
                    frmElement.markCheckBoxes();
                    frmElement.setVisible(true);
                    return;
                }
        }
        showMessageDialog(null, "Не вдалося знайти елемент");      
    }
    
    public static User getUser() {
        return user;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlNavigation = new javax.swing.JPanel();
        pnlForIcon = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        btnViewElements = new javax.swing.JButton();
        btnGeneralInformation = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        btnAuthorization = new javax.swing.JButton();
        btnFavorites = new javax.swing.JButton();
        btnStudied = new javax.swing.JButton();
        btnSignOut = new javax.swing.JButton();
        dsktpMain = new javax.swing.JDesktopPane();
        pnlBackground = new javax.swing.JPanel();
        lblMainBackground = new javax.swing.JLabel();
        menuBarMain = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        menuItemExit = new javax.swing.JMenuItem();
        menuCertificate = new javax.swing.JMenu();
        menuItemAboutProgram = new javax.swing.JMenuItem();
        menuItemAboutAuthor = new javax.swing.JMenuItem();
        menuSettings = new javax.swing.JMenu();
        menuItemSettings = new javax.swing.JMenuItem();
        menuSearch = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Інформаційна система по елементам Workout");
        setFocusCycleRoot(false);
        setPreferredSize(new java.awt.Dimension(1920, 990));

        pnlNavigation.setBackground(new java.awt.Color(50, 51, 55));
        pnlNavigation.setDoubleBuffered(false);
        pnlNavigation.setEnabled(false);

        pnlForIcon.setBackground(new java.awt.Color(50, 51, 55));

        lblLogo.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlForIconLayout = new javax.swing.GroupLayout(pnlForIcon);
        pnlForIcon.setLayout(pnlForIconLayout);
        pnlForIconLayout.setHorizontalGroup(
            pnlForIconLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlForIconLayout.setVerticalGroup(
            pnlForIconLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblLogo, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
        );

        btnViewElements.setBackground(new java.awt.Color(50, 51, 55));
        btnViewElements.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnViewElements.setForeground(new java.awt.Color(255, 255, 255));
        btnViewElements.setText("Перегляд елементів");
        btnViewElements.setContentAreaFilled(false);
        btnViewElements.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnViewElements.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnViewElements.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnViewElementsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnViewElementsMouseExited(evt);
            }
        });
        btnViewElements.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewElementsActionPerformed(evt);
            }
        });

        btnGeneralInformation.setBackground(new java.awt.Color(50, 51, 55));
        btnGeneralInformation.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnGeneralInformation.setForeground(new java.awt.Color(255, 255, 255));
        btnGeneralInformation.setText("Загальна інформація");
        btnGeneralInformation.setContentAreaFilled(false);
        btnGeneralInformation.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGeneralInformation.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnGeneralInformation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGeneralInformationMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGeneralInformationMouseExited(evt);
            }
        });
        btnGeneralInformation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGeneralInformationActionPerformed(evt);
            }
        });

        btnExit.setBackground(new java.awt.Color(50, 51, 55));
        btnExit.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnExit.setForeground(new java.awt.Color(255, 255, 255));
        btnExit.setText("Вихід");
        btnExit.setContentAreaFilled(false);
        btnExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnExitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExitMouseExited(evt);
            }
        });
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        btnAuthorization.setBackground(new java.awt.Color(50, 51, 55));
        btnAuthorization.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnAuthorization.setForeground(new java.awt.Color(255, 255, 255));
        btnAuthorization.setText("Авторизація");
        btnAuthorization.setContentAreaFilled(false);
        btnAuthorization.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAuthorization.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAuthorizationMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAuthorizationMouseExited(evt);
            }
        });
        btnAuthorization.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAuthorizationActionPerformed(evt);
            }
        });

        btnFavorites.setBackground(new java.awt.Color(50, 51, 55));
        btnFavorites.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnFavorites.setForeground(new java.awt.Color(255, 255, 255));
        btnFavorites.setText("Вибрані");
        btnFavorites.setContentAreaFilled(false);
        btnFavorites.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFavorites.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnFavoritesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnFavoritesMouseExited(evt);
            }
        });
        btnFavorites.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFavoritesActionPerformed(evt);
            }
        });

        btnStudied.setBackground(new java.awt.Color(50, 51, 55));
        btnStudied.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnStudied.setForeground(new java.awt.Color(255, 255, 255));
        btnStudied.setText("Вивчені");
        btnStudied.setContentAreaFilled(false);
        btnStudied.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnStudied.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnStudiedMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnStudiedMouseExited(evt);
            }
        });
        btnStudied.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStudiedActionPerformed(evt);
            }
        });

        btnSignOut.setBackground(new java.awt.Color(50, 51, 55));
        btnSignOut.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnSignOut.setForeground(new java.awt.Color(255, 255, 255));
        btnSignOut.setText("Вийти з акаунту");
        btnSignOut.setContentAreaFilled(false);
        btnSignOut.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSignOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSignOutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSignOutMouseExited(evt);
            }
        });
        btnSignOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignOutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlNavigationLayout = new javax.swing.GroupLayout(pnlNavigation);
        pnlNavigation.setLayout(pnlNavigationLayout);
        pnlNavigationLayout.setHorizontalGroup(
            pnlNavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlForIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnViewElements, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnGeneralInformation, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
            .addComponent(btnAuthorization, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnStudied, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnFavorites, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnSignOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlNavigationLayout.setVerticalGroup(
            pnlNavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNavigationLayout.createSequentialGroup()
                .addComponent(pnlForIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(btnViewElements, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnGeneralInformation, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnAuthorization, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnFavorites, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnStudied, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnSignOut, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        dsktpMain.setBackground(new java.awt.Color(126, 126, 126));

        pnlBackground.setBackground(new java.awt.Color(126, 126, 126));

        lblMainBackground.setBackground(new java.awt.Color(126, 126, 126));
        lblMainBackground.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout pnlBackgroundLayout = new javax.swing.GroupLayout(pnlBackground);
        pnlBackground.setLayout(pnlBackgroundLayout);
        pnlBackgroundLayout.setHorizontalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 531, Short.MAX_VALUE)
            .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblMainBackground, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE))
        );
        pnlBackgroundLayout.setVerticalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblMainBackground, javax.swing.GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE))
        );

        dsktpMain.setLayer(pnlBackground, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout dsktpMainLayout = new javax.swing.GroupLayout(dsktpMain);
        dsktpMain.setLayout(dsktpMainLayout);
        dsktpMainLayout.setHorizontalGroup(
            dsktpMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        dsktpMainLayout.setVerticalGroup(
            dsktpMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackground, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        menuBarMain.setBackground(new java.awt.Color(26, 27, 31));
        menuBarMain.setBorder(null);
        menuBarMain.setToolTipText("");
        menuBarMain.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        menuBarMain.setEnabled(false);
        menuBarMain.setFocusable(false);
        menuBarMain.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        menuBarMain.setMinimumSize(new java.awt.Dimension(60, 20));
        menuBarMain.setPreferredSize(new java.awt.Dimension(60, 30));

        menuFile.setText("Файл");
        menuFile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuFile.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        menuItemExit.setText("Вихід");
        menuItemExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemExitActionPerformed(evt);
            }
        });
        menuFile.add(menuItemExit);

        menuBarMain.add(menuFile);

        menuCertificate.setText("Довідка");
        menuCertificate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuCertificate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        menuItemAboutProgram.setText("Про програму");
        menuItemAboutProgram.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuItemAboutProgram.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemAboutProgramActionPerformed(evt);
            }
        });
        menuCertificate.add(menuItemAboutProgram);

        menuItemAboutAuthor.setText("Про автора");
        menuItemAboutAuthor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuItemAboutAuthor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemAboutAuthorActionPerformed(evt);
            }
        });
        menuCertificate.add(menuItemAboutAuthor);

        menuBarMain.add(menuCertificate);

        menuSettings.setBackground(new java.awt.Color(26, 27, 31));
        menuSettings.setText("Налаштування");
        menuSettings.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuSettings.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        menuItemSettings.setText("Налаштування");
        menuItemSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSettingsActionPerformed(evt);
            }
        });
        menuSettings.add(menuItemSettings);

        menuBarMain.add(menuSettings);

        menuSearch.setText("Пошук");
        menuSearch.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        menuBarMain.add(menuSearch);

        setJMenuBar(menuBarMain);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlNavigation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dsktpMain))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlNavigation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(dsktpMain)
        );

        setSize(new java.awt.Dimension(902, 890));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnViewElementsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewElementsActionPerformed
        hideAllInternalFrames();
        if(frmViewElements == null){
            frmViewElements = new ViewElements(this);
            dsktpMain.add(frmViewElements);
            frmViewElements.setSize(dsktpMain.getWidth(), dsktpMain.getHeight());
        }
        frmViewElements.checkUserStatus();
        frmViewElements.createElementButtons();
        frmViewElements.setVisible(true);
    }//GEN-LAST:event_btnViewElementsActionPerformed

    private void menuItemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_menuItemExitActionPerformed

    private void menuItemAboutProgramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemAboutProgramActionPerformed
        hideAllInternalFrames();
        
        if(frmAboutProgram == null){
            frmAboutProgram = new AboutProgram();
            dsktpMain.add(frmAboutProgram);
            frmAboutProgram.setSize(dsktpMain.getWidth(), dsktpMain.getHeight());
        }
        frmAboutProgram.setVisible(true);
    }//GEN-LAST:event_menuItemAboutProgramActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnAuthorizationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAuthorizationActionPerformed
        hideAllInternalFrames();

        if(frmAuthorization == null){
            frmAuthorization = new Authorization(this, user);
            dsktpMain.add(frmAuthorization);
            frmAuthorization.setSize(dsktpMain.getWidth(), dsktpMain.getHeight());
        }
        frmAuthorization.setVisible(true);      
    }//GEN-LAST:event_btnAuthorizationActionPerformed

    private void btnGeneralInformationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGeneralInformationActionPerformed
        hideAllInternalFrames();
        
        if(frmGeneralInformation == null){
            frmGeneralInformation = new GeneralInformation();
            dsktpMain.add(frmGeneralInformation);
            frmGeneralInformation.setSize(dsktpMain.getWidth(), dsktpMain.getHeight());
        }
        frmGeneralInformation.setVisible(true);
    }//GEN-LAST:event_btnGeneralInformationActionPerformed

    private void menuItemAboutAuthorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemAboutAuthorActionPerformed
        hideAllInternalFrames();
        
        if(frmAboutAuthor == null){
            frmAboutAuthor = new AboutAuthor();
            dsktpMain.add(frmAboutAuthor);
            frmAboutAuthor.setSize(dsktpMain.getWidth(), dsktpMain.getHeight());
        }
        frmAboutAuthor.setVisible(true);
    }//GEN-LAST:event_menuItemAboutAuthorActionPerformed

    private void menuItemSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSettingsActionPerformed
        hideAllInternalFrames();
        
        if(frmSettings == null){
            frmSettings = new Setting();
            dsktpMain.add(frmSettings);
            frmSettings.setSize(dsktpMain.getWidth(), dsktpMain.getHeight());
        }
        frmSettings.setVisible(true);
    }//GEN-LAST:event_menuItemSettingsActionPerformed

    private void btnFavoritesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFavoritesActionPerformed
        hideAllInternalFrames();
        
        if(frmFavorites == null){
            frmFavorites = new Favorites(this);
            dsktpMain.add(frmFavorites);
            frmFavorites.setSize(dsktpMain.getWidth(), dsktpMain.getHeight());
        }
        frmFavorites.createElementButtons();
        frmFavorites.setVisible(true);
    }//GEN-LAST:event_btnFavoritesActionPerformed

    private void btnStudiedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStudiedActionPerformed
        hideAllInternalFrames();
        
        if(frmStudied == null){
            frmStudied = new Studied(this);
            dsktpMain.add(frmStudied);
            frmStudied.setSize(dsktpMain.getWidth(), dsktpMain.getHeight());
        }
        frmStudied.createElementButtons();
        frmStudied.setVisible(true);
    }//GEN-LAST:event_btnStudiedActionPerformed

    private void btnSignOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignOutActionPerformed
        hideAllInternalFrames();
        user.setStatus(User.NOT_REGISTERED_USER);
        checkUserStatus();
        
    }//GEN-LAST:event_btnSignOutActionPerformed

    private void btnAuthorizationMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAuthorizationMouseEntered
        btnAuthorization.setContentAreaFilled(true);
    }//GEN-LAST:event_btnAuthorizationMouseEntered

    private void btnAuthorizationMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAuthorizationMouseExited
        btnAuthorization.setContentAreaFilled(false);
    }//GEN-LAST:event_btnAuthorizationMouseExited

    private void btnViewElementsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViewElementsMouseEntered
        btnViewElements.setContentAreaFilled(true);
    }//GEN-LAST:event_btnViewElementsMouseEntered

    private void btnViewElementsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViewElementsMouseExited
        btnViewElements.setContentAreaFilled(false);
    }//GEN-LAST:event_btnViewElementsMouseExited

    private void btnGeneralInformationMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGeneralInformationMouseEntered
        btnGeneralInformation.setContentAreaFilled(true);
    }//GEN-LAST:event_btnGeneralInformationMouseEntered

    private void btnGeneralInformationMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGeneralInformationMouseExited
        btnGeneralInformation.setContentAreaFilled(false);
    }//GEN-LAST:event_btnGeneralInformationMouseExited

    private void btnFavoritesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFavoritesMouseEntered
        btnFavorites.setContentAreaFilled(true);
    }//GEN-LAST:event_btnFavoritesMouseEntered

    private void btnFavoritesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFavoritesMouseExited
        btnFavorites.setContentAreaFilled(false);
    }//GEN-LAST:event_btnFavoritesMouseExited

    private void btnStudiedMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStudiedMouseEntered
        btnStudied.setContentAreaFilled(true);
    }//GEN-LAST:event_btnStudiedMouseEntered

    private void btnStudiedMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStudiedMouseExited
        btnStudied.setContentAreaFilled(false);
    }//GEN-LAST:event_btnStudiedMouseExited

    private void btnSignOutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSignOutMouseEntered
        btnSignOut.setContentAreaFilled(true);
    }//GEN-LAST:event_btnSignOutMouseEntered

    private void btnSignOutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSignOutMouseExited
        btnSignOut.setContentAreaFilled(false);
    }//GEN-LAST:event_btnSignOutMouseExited

    private void btnExitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseEntered
        btnExit.setContentAreaFilled(true);
    }//GEN-LAST:event_btnExitMouseEntered

    private void btnExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseExited
        btnExit.setContentAreaFilled(false);
    }//GEN-LAST:event_btnExitMouseExited

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmMain().setVisible(true);
            }
        });
       
    }
    
    
    public void hideAllInternalFrames(){
        for(Component component : dsktpMain.getComponents()){
            component.setVisible(false);
        }
        pnlBackground.setVisible(true);
    }
    
    public JDesktopPane getDesktopPane(){
        return dsktpMain;
    }
    
    public Login getFrmLogin(){
        return frmLogin;
    }
    
    public Registration getFrmRegistration(){
        return frmRegistration;
    }
    
    public JLabel getLblMainBackground(){
        return lblMainBackground;
    }
    
    public Element getFrmElement() {
        return frmElement;
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAuthorization;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnFavorites;
    private javax.swing.JButton btnGeneralInformation;
    private javax.swing.JButton btnSignOut;
    private javax.swing.JButton btnStudied;
    private javax.swing.JButton btnViewElements;
    private javax.swing.JDesktopPane dsktpMain;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblMainBackground;
    private javax.swing.JMenuBar menuBarMain;
    private javax.swing.JMenu menuCertificate;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenuItem menuItemAboutAuthor;
    private javax.swing.JMenuItem menuItemAboutProgram;
    private javax.swing.JMenuItem menuItemExit;
    private javax.swing.JMenuItem menuItemSettings;
    private javax.swing.JMenu menuSearch;
    private javax.swing.JMenu menuSettings;
    private javax.swing.JPanel pnlBackground;
    private javax.swing.JPanel pnlForIcon;
    private javax.swing.JPanel pnlNavigation;
    // End of variables declaration//GEN-END:variables
}
