/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * LicenseFrame.java
 *
 * Created on Dec 18, 2009, 3:22:51 PM
 */

package codepaw;

import org.jdesktop.application.Action;

/**
 *
 * @author stef
 */
public class LicenseFrame extends javax.swing.JFrame {

    /** Creates new form LicenseFrame */
    public LicenseFrame() {
        initComponents();
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        TextPane = new javax.swing.JTextPane();
        label1 = new javax.swing.JLabel();
        Label2 = new javax.swing.JLabel();
        image = new javax.swing.JLabel();
        CloseBTN = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        TextPane.setEditable(false);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(codepaw.CodePawApp.class).getContext().getResourceMap(LicenseFrame.class);
        TextPane.setText(resourceMap.getString("TextPane.text")); // NOI18N
        TextPane.setCaretPosition(26363);
        TextPane.setName("TextPane"); // NOI18N
        jScrollPane1.setViewportView(TextPane);

        label1.setFont(resourceMap.getFont("label1.font")); // NOI18N
        label1.setText(resourceMap.getString("label1.text")); // NOI18N
        label1.setName("label1"); // NOI18N

        Label2.setFont(resourceMap.getFont("Label2.font")); // NOI18N
        Label2.setText(resourceMap.getString("Label2.text")); // NOI18N
        Label2.setName("Label2"); // NOI18N

        image.setIcon(resourceMap.getIcon("image.icon")); // NOI18N
        image.setText(resourceMap.getString("image.text")); // NOI18N
        image.setName("image"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(codepaw.CodePawApp.class).getContext().getActionMap(LicenseFrame.class, this);
        CloseBTN.setAction(actionMap.get("close")); // NOI18N
        CloseBTN.setText(resourceMap.getString("CloseBTN.text")); // NOI18N
        CloseBTN.setName("CloseBTN"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label1)
                    .addComponent(Label2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(image, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(CloseBTN)
                        .addGap(30, 30, 30))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(image)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                        .addComponent(CloseBTN))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(label1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Label2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowActivated

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LicenseFrame().setVisible(true);
            }
        });
    }

    @Action
    public void close() {
        dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CloseBTN;
    private javax.swing.JLabel Label2;
    private javax.swing.JTextPane TextPane;
    private javax.swing.JLabel image;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label1;
    // End of variables declaration//GEN-END:variables

}
