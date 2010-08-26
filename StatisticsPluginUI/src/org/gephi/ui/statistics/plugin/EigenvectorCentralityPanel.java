/*
Copyright 2008-2010 Gephi
Authors : Patick J. McSweeney <pjmcswee@syr.edu>
Website : http://www.gephi.org

This file is part of Gephi.

Gephi is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as
published by the Free Software Foundation, either version 3 of the
License, or (at your option) any later version.

Gephi is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with Gephi.  If not, see <http://www.gnu.org/licenses/>.
*/
package org.gephi.ui.statistics.plugin;

/**
 *
 * @author pjmcswee
 */
public class EigenvectorCentralityPanel extends javax.swing.JPanel {

    /** Creates new form EigenvectorCentralityPanel */
    public EigenvectorCentralityPanel() {
        initComponents();
    }

    public boolean isDirected(){
        return this.jRadioButton1.isSelected();
    }

    public void setDirected(boolean pDirected){
        this.jRadioButton1.setSelected(pDirected);
        this.jRadioButton1.setSelected(!pDirected);
    }

    public void setNumRuns(int mRuns){
        jTextField1.setText(mRuns+"");
    }

    public int getNumRuns(){
        try{
            int runs = Integer.parseInt(jTextField1.getText());
            return runs;
        }catch(Exception e){e.printStackTrace();}
        return 0;
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        header = new org.jdesktop.swingx.JXHeader();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();

        header.setDescription(org.openide.util.NbBundle.getMessage(EigenvectorCentralityPanel.class, "EigenvectorCentralityPanel.header.description")); // NOI18N
        header.setTitle(org.openide.util.NbBundle.getMessage(EigenvectorCentralityPanel.class, "EigenvectorCentralityPanel.header.title")); // NOI18N

        jTextField1.setMinimumSize(new java.awt.Dimension(30, 27));

        jLabel1.setText(org.openide.util.NbBundle.getMessage(EigenvectorCentralityPanel.class, "EigenvectorCentralityPanel.labeliterations.text")); // NOI18N

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText(org.openide.util.NbBundle.getMessage(EigenvectorCentralityPanel.class, "EigenvectorCentralityPanel.directedButton.text")); // NOI18N

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText(org.openide.util.NbBundle.getMessage(EigenvectorCentralityPanel.class, "EigenvectorCentralityPanel.undirectedButton.text")); // NOI18N
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(8, 8, 8)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(332, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButton1)
                .addContainerGap(555, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButton2)
                .addContainerGap(541, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton2)
                .addContainerGap(81, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private org.jdesktop.swingx.JXHeader header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

}
