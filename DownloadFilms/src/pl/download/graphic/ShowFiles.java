package pl.download.graphic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import pl.download.model.ModelShow;

/**
 *
 * @author Artur Kokott
 */
public class ShowFiles extends javax.swing.JFrame {

    /**
     * Creates new form ShowFiles
     */
    public ShowFiles() {
        super("Dostępne pliki");
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ShowPanel = new javax.swing.JScrollPane();
        ShowField = new javax.swing.JTextArea();
        searchWordField = new javax.swing.JTextField();
        pageField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        labelSearchWord = new javax.swing.JLabel();
        labelPage = new javax.swing.JLabel();
        maxPageField = new javax.swing.JTextField();
        labelPageMax = new javax.swing.JLabel();
        allPageViewButton = new javax.swing.JButton();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        ShowField.setEditable(false);
        ShowField.setColumns(20);
        ShowField.setRows(5);
        ShowField.setAutoscrolls(false);
        ShowField.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ShowPanel.setViewportView(ShowField);

        searchButton.setText("Szukaj");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        labelSearchWord.setText("Szukany plik:");

        labelPage.setText("Ilość stron do wyświetlenia:");

        maxPageField.setEditable(false);
        maxPageField.setEnabled(false);

        labelPageMax.setText("Dostepnych stron wyszukiwanej frazy:");

        allPageViewButton.setText("Pokaż wyniki");
        allPageViewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allPageViewButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelSearchWord)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(searchWordField, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(searchButton)))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(labelPageMax)
                                .addGap(45, 45, 45)
                                .addComponent(labelPage))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(83, 83, 83)
                                .addComponent(maxPageField, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(149, 149, 149)
                                .addComponent(pageField, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(67, 67, 67)
                                .addComponent(allPageViewButton)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(ShowPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1045, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSearchWord)
                    .addComponent(labelPage)
                    .addComponent(labelPageMax))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchWordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pageField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton)
                    .addComponent(maxPageField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(allPageViewButton))
                .addGap(18, 18, 18)
                .addComponent(ShowPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        ShowField.setLineWrap(true);
        ModelShow modelShow = new ModelShow();
        String getSearchWord = searchWordField.getText().replace(" ", "+");
        String getListFiles;
        List<String> setPageMax = new LinkedList<String>();

        if (getSearchWord == null || getSearchWord.equals("")) {
            JOptionPane.showMessageDialog(null, "Pole nie może być puste.", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                //by nie sciagac plikow - zahaszowac linie nizej
                modelShow.downloadFile(getSearchWord, 1);
                //sprawdzenie czy divisionLine nie zwraca blednej zawartosci
                if (modelShow.divisionLine(1).equals("error")) {
                    return;
                } else if (modelShow.divisionLine(1).equals("emptyValue")) {
                    JOptionPane.showMessageDialog(null, "Brak wyników wyszukiwania.", "Info", JOptionPane.INFORMATION_MESSAGE);
                } else {
                setPageMax = Arrays.asList(modelShow.divisionLine(1).split("!@"));
                maxPageField.setText(setPageMax.get(0));

                getListFiles = setPageMax.get(1);
                ShowField.setText(getListFiles);
                ShowField.setCaretPosition(0);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ShowFiles.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ShowFiles.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_searchButtonActionPerformed

    private void allPageViewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allPageViewButtonActionPerformed
        ShowField.setLineWrap(true);
        ModelShow modelShow = new ModelShow();
        String allPagesResults;
        String getSearchWord = searchWordField.getText();
        String pageMax = maxPageField.getText();
        String getPageValue = pageField.getText();
        List<String> result = new LinkedList<String>();

        if (getPageValue == null || getPageValue.equals("")) {
            JOptionPane.showMessageDialog(null, "Pole nie może być puste.", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                allPagesResults = modelShow.showAllResults(getSearchWord, Integer.parseInt(getPageValue), Integer.parseInt(pageMax));
                result = Arrays.asList(allPagesResults.split("!@"));
                ShowField.setText(result.get(1));
                ShowField.setCaretPosition(0);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Podaj ilość stron.", "Info", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                Logger.getLogger(ShowFiles.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_allPageViewButtonActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        String tempDir = System.getProperty("java.io.tmpdir");
        String nameFile;
        File folder = new File(tempDir);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                nameFile = listOfFiles[i].getName();

                if (nameFile.contains("lista_")) {
                    File file = new File(tempDir + "\\" + nameFile);
                    file.delete();
                }
            }
        }
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(ShowFiles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ShowFiles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ShowFiles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ShowFiles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ShowFiles().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea ShowField;
    private javax.swing.JScrollPane ShowPanel;
    private javax.swing.JButton allPageViewButton;
    private javax.swing.JLabel labelPage;
    private javax.swing.JLabel labelPageMax;
    private javax.swing.JLabel labelSearchWord;
    private javax.swing.JTextField maxPageField;
    private javax.swing.JTextField pageField;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchWordField;
    // End of variables declaration//GEN-END:variables
}
