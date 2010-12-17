/*
 * Copyright 2008-2010 Gephi
 * Authors : Cezary Bartosiak
 * Website : http://www.gephi.org
 *
 * This file is part of Gephi.
 *
 * Gephi is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Gephi is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Gephi.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.gephi.ui.statistics.plugin;

/**
 *
 *
 * @author Cezary Bartosiak
 */
public class GlobalConnectionEfficiencyPanel extends javax.swing.JPanel {

    /** Creates new form GlobalConnectionEfficiencyPanel */
    public GlobalConnectionEfficiencyPanel() {
        initComponents();
    }

	public boolean isDirected() {
		return directedRadioButton.isSelected();
	}

	public void setDirected(boolean directed) {
		directedButtonGroup.setSelected(directed ? directedRadioButton.getModel() : undirectedRadioButton.getModel(), true);
	}

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        directedButtonGroup = new javax.swing.ButtonGroup();
        header = new org.jdesktop.swingx.JXHeader();
        undirectedRadioButton = new javax.swing.JRadioButton();
        directedRadioButton = new javax.swing.JRadioButton();

        setPreferredSize(new java.awt.Dimension(419, 240));

        header.setDescription(org.openide.util.NbBundle.getMessage(GlobalConnectionEfficiencyPanel.class, "GlobalConnectionEfficiencyPanel.header.description")); // NOI18N
        header.setTitle(org.openide.util.NbBundle.getMessage(GlobalConnectionEfficiencyPanel.class, "GlobalConnectionEfficiencyPanel.header.title")); // NOI18N

        directedButtonGroup.add(undirectedRadioButton);
        undirectedRadioButton.setText(org.openide.util.NbBundle.getMessage(GlobalConnectionEfficiencyPanel.class, "GlobalConnectionEfficiencyPanel.undirectedRadioButton.text")); // NOI18N

        directedButtonGroup.add(directedRadioButton);
        directedRadioButton.setText(org.openide.util.NbBundle.getMessage(GlobalConnectionEfficiencyPanel.class, "GlobalConnectionEfficiencyPanel.directedRadioButton.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(undirectedRadioButton)
                    .addComponent(directedRadioButton))
                .addContainerGap(334, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(directedRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(undirectedRadioButton)
                .addContainerGap(106, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup directedButtonGroup;
    protected javax.swing.JRadioButton directedRadioButton;
    private org.jdesktop.swingx.JXHeader header;
    protected javax.swing.JRadioButton undirectedRadioButton;
    // End of variables declaration//GEN-END:variables

}