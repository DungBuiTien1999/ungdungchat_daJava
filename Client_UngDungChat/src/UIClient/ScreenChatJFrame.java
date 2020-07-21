/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIClient;

import TCPClient.Message;
import com.google.gson.Gson;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DUNG BUI
 */
public class ScreenChatJFrame extends javax.swing.JFrame {
    Socket ss;
    String sendperson;
    String receiveperson;
    HashMap<String, ScreenChatJFrame> chatwindows;
    HashMap<String, javax.swing.JTextArea> showChatOnScreenHashMap;
    OutputStream os = null;
    BufferedWriter bw = null;

    /**
     * Creates new form ScreenChatJFrame
     * @param s
     * @param SendPerson
     * @param ReceivePerson
     * @param ChatWindows
     * @param showChatOnScreenHashMap
     */
    public ScreenChatJFrame(Socket s, String SendPerson, String ReceivePerson, HashMap<String,ScreenChatJFrame> ChatWindows, HashMap<String, javax.swing.JTextArea> showChatOnScreenHashMap) {
        initComponents();
        this.ss = s;
        this.sendperson = SendPerson;
        this.receiveperson = ReceivePerson;
        this.chatwindows = ChatWindows;
        this.showChatOnScreenHashMap = showChatOnScreenHashMap;
        load();
    }
    
    private void load(){
        this.jLabelUserNameMess.setText(this.receiveperson);
        showChatOnScreenHashMap.put(this.receiveperson, jTextAreaConversation);
        try {
            os = ss.getOutputStream();
            bw = new BufferedWriter(new OutputStreamWriter(os));
            
        } catch (IOException ex) {
            Logger.getLogger(ScreenChatJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanelScreenMess = new javax.swing.JPanel();
        jLabelUserNameMess = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButtonSendFile = new javax.swing.JButton();
        jTextFieldTypeMess = new javax.swing.JTextField();
        jButtonSend = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaConversation = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelScreenMess.setBackground(new java.awt.Color(0, 255, 255));

        jLabelUserNameMess.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabelUserNameMess.setText("Lan");

        jPanel2.setBackground(new java.awt.Color(255, 153, 255));

        jButtonSendFile.setBackground(new java.awt.Color(102, 255, 102));
        jButtonSendFile.setText("Send file");

        jTextFieldTypeMess.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jButtonSend.setBackground(new java.awt.Color(255, 204, 51));
        jButtonSend.setText("Send");
        jButtonSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonSendFile, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldTypeMess, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSend)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldTypeMess, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(jButtonSendFile, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonSend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTextAreaConversation.setEditable(false);
        jTextAreaConversation.setBackground(new java.awt.Color(51, 204, 255));
        jTextAreaConversation.setColumns(20);
        jTextAreaConversation.setRows(5);
        jScrollPane2.setViewportView(jTextAreaConversation);

        javax.swing.GroupLayout jPanelScreenMessLayout = new javax.swing.GroupLayout(jPanelScreenMess);
        jPanelScreenMess.setLayout(jPanelScreenMessLayout);
        jPanelScreenMessLayout.setHorizontalGroup(
            jPanelScreenMessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelScreenMessLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelScreenMessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelScreenMessLayout.createSequentialGroup()
                        .addComponent(jLabelUserNameMess, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelScreenMessLayout.setVerticalGroup(
            jPanelScreenMessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelScreenMessLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelUserNameMess, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 486, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanelScreenMess, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 476, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanelScreenMess, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSendActionPerformed
        // TODO add your handling code here:
        if(!this.jTextFieldTypeMess.getText().equalsIgnoreCase("")){
            String messString = this.jTextFieldTypeMess.getText().trim();
            this.jTextAreaConversation.setText(this.jTextAreaConversation.getText().trim() + "\n" + messString);

            Message message = new Message(2, sendperson, receiveperson, messString);

            Gson gson = new Gson();

            String sendMessString = gson.toJson(message, Message.class);

            try {
                bw.write(sendMessString);
                bw.newLine();
                bw.flush();
            } catch (IOException ex) {
                Logger.getLogger(ScreenChatJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.jTextFieldTypeMess.setText("");
        }
    }//GEN-LAST:event_jButtonSendActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(ScreenChatJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ScreenChatJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ScreenChatJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ScreenChatJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new ScreenChatJFrame().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSend;
    private javax.swing.JButton jButtonSendFile;
    private javax.swing.JLabel jLabelUserNameMess;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelScreenMess;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextAreaConversation;
    private javax.swing.JTextField jTextFieldTypeMess;
    // End of variables declaration//GEN-END:variables
}
