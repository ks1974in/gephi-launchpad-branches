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
package org.gephi.spreadsimulator;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import net.miginfocom.swing.MigLayout;
import org.gephi.data.attributes.api.AttributeColumn;
import org.gephi.data.attributes.api.AttributeController;
import org.gephi.data.attributes.api.AttributeOrigin;
import org.gephi.data.attributes.api.AttributeType;
import org.gephi.spreadsimulator.api.ModifyStrategyType;
import org.openide.util.Lookup;

/**
 *
 *
 * @author Cezary Bartosiak
 */
public class StateChangeStrategyPanel extends javax.swing.JPanel {
	private ButtonGroup bgroup;
	private AttributesRadioButton[] attrRadioButtons;

    /** Creates new form StateChangeStrategyPanel */
    public StateChangeStrategyPanel() {
        initComponents();
    }

	public AttributeColumn getAttributeColumn() {
		if (attrRadioButtons != null)
			for (AttributesRadioButton r : attrRadioButtons)
				if (r.isSelected())
					return r.getColumn();
		return null;
	}

	public void setAttributeColumn(AttributeColumn attributeColumn) {
		AttributeController attributeController = Lookup.getDefault().lookup(AttributeController.class);

		List<AttributeColumn> availableColumns = new ArrayList<AttributeColumn>();
		bgroup = new ButtonGroup();
		AttributesRadioButton[] target;
		for (AttributeColumn c : attributeController.getModel().getNodeTable().getColumns())
			if ((c.getOrigin().equals(AttributeOrigin.DATA) || c.getOrigin().equals(AttributeOrigin.COMPUTED)) &&
					(c.getType().equals(AttributeType.BIGDECIMAL) ||
					c.getType().equals(AttributeType.BIGINTEGER) ||
					c.getType().equals(AttributeType.BYTE) ||
					c.getType().equals(AttributeType.DOUBLE) ||
					c.getType().equals(AttributeType.FLOAT) ||
					c.getType().equals(AttributeType.INT) ||
					c.getType().equals(AttributeType.LONG) ||
					c.getType().equals(AttributeType.SHORT)))
				availableColumns.add(c);

		attrRadioButtons = new AttributesRadioButton[availableColumns.size()];
		target = attrRadioButtons;

		contentPanel.removeAll();
		contentPanel.setLayout(new MigLayout("", "[pref!]"));
		for (int i = 0; i < availableColumns.size(); i++) {
			AttributeColumn column = availableColumns.get(i);
			AttributesRadioButton r = new AttributesRadioButton(column, column.equals(attributeColumn));
			bgroup.add(r.getRadioButton());
			target[i] = r;
			contentPanel.add(r.getRadioButton(), "wrap");
		}
		contentPanel.revalidate();
		contentPanel.repaint();
	}

	public ModifyStrategyType getMstype() {
		if (randomRadioButton.isSelected())
			return ModifyStrategyType.RANDOM;
		if (randomRandomRadioButton.isSelected())
			return ModifyStrategyType.RANDOM_RANDOM;
		if (attributeHighestRadioButton.isSelected())
			return ModifyStrategyType.ATTRIBUTE_HIGHEST;
		if (attributeLowestRadioButton.isSelected())
			return ModifyStrategyType.ATTRIBUTE_LOWEST;
		return ModifyStrategyType.RANDOM;
	}

	public void setMstype(ModifyStrategyType mstype) {
		switch (mstype) {
			case RANDOM:
				randomRadioButton.setSelected(true);
				break;
			case RANDOM_RANDOM:
				randomRandomRadioButton.setSelected(true);
				break;
			case ATTRIBUTE_HIGHEST:
				attributeHighestRadioButton.setSelected(true);
				break;
			case ATTRIBUTE_LOWEST:
				attributeLowestRadioButton.setSelected(true);
				break;
			default:
				randomRadioButton.setSelected(true);
				break;

		}
	}

	public int getK() {
		return Integer.parseInt(kFormattedTextField.getText());
	}

	public void setK(int k) {
		kFormattedTextField.setText(k + "");
	}

	public String getStateName() {
		return stateTextField.getText();
	}

	public void setStateName(String stateName) {
		stateTextField.setText(stateName);
	}

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        header = new org.jdesktop.swingx.JXHeader();
        randomRadioButton = new javax.swing.JRadioButton();
        kLabel = new javax.swing.JLabel();
        randomRandomRadioButton = new javax.swing.JRadioButton();
        kFormattedTextField = new javax.swing.JFormattedTextField();
        stateLabel = new javax.swing.JLabel();
        stateTextField = new javax.swing.JTextField();
        attributeHighestRadioButton = new javax.swing.JRadioButton();
        attributeLowestRadioButton = new javax.swing.JRadioButton();
        contentScrollPane = new javax.swing.JScrollPane();
        contentPanel = new javax.swing.JPanel();
        columnsLabel = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(458, 463));

        header.setDescription(org.openide.util.NbBundle.getMessage(StateChangeStrategyPanel.class, "StateChangeStrategyPanel.header.description")); // NOI18N
        header.setTitle(org.openide.util.NbBundle.getMessage(StateChangeStrategyPanel.class, "StateChangeStrategyPanel.header.title")); // NOI18N

        buttonGroup.add(randomRadioButton);
        randomRadioButton.setText(org.openide.util.NbBundle.getMessage(StateChangeStrategyPanel.class, "StateChangeStrategyPanel.randomRadioButton.text")); // NOI18N

        kLabel.setText(org.openide.util.NbBundle.getMessage(StateChangeStrategyPanel.class, "StateChangeStrategyPanel.kLabel.text")); // NOI18N

        buttonGroup.add(randomRandomRadioButton);
        randomRandomRadioButton.setText(org.openide.util.NbBundle.getMessage(StateChangeStrategyPanel.class, "StateChangeStrategyPanel.randomRandomRadioButton.text")); // NOI18N
        randomRandomRadioButton.setActionCommand(org.openide.util.NbBundle.getMessage(StateChangeStrategyPanel.class, "StateChangeStrategyPanel.randomRandomRadioButton.actionCommand")); // NOI18N

        kFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###0"))));

        stateLabel.setText(org.openide.util.NbBundle.getMessage(StateChangeStrategyPanel.class, "StateChangeStrategyPanel.stateLabel.text")); // NOI18N

        stateTextField.setText(org.openide.util.NbBundle.getMessage(StateChangeStrategyPanel.class, "StateChangeStrategyPanel.stateTextField.text")); // NOI18N

        buttonGroup.add(attributeHighestRadioButton);
        attributeHighestRadioButton.setText(org.openide.util.NbBundle.getMessage(StateChangeStrategyPanel.class, "StateChangeStrategyPanel.attributeHighestRadioButton.text")); // NOI18N
        attributeHighestRadioButton.setActionCommand(org.openide.util.NbBundle.getMessage(StateChangeStrategyPanel.class, "StateChangeStrategyPanel.attributeHighestRadioButton.actionCommand")); // NOI18N

        buttonGroup.add(attributeLowestRadioButton);
        attributeLowestRadioButton.setText(org.openide.util.NbBundle.getMessage(StateChangeStrategyPanel.class, "StateChangeStrategyPanel.attributeLowestRadioButton.text")); // NOI18N
        attributeLowestRadioButton.setActionCommand(org.openide.util.NbBundle.getMessage(StateChangeStrategyPanel.class, "StateChangeStrategyPanel.attributeLowestRadioButton.actionCommand")); // NOI18N

        contentPanel.setLayout(new java.awt.GridLayout(1, 0));
        contentScrollPane.setViewportView(contentPanel);

        columnsLabel.setText(org.openide.util.NbBundle.getMessage(StateChangeStrategyPanel.class, "StateChangeStrategyPanel.columnsLabel.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(stateLabel)
                    .addComponent(kLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(kFormattedTextField)
                    .addComponent(stateTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE))
                .addContainerGap(284, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(randomRadioButton)
                    .addComponent(randomRandomRadioButton))
                .addContainerGap(345, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(attributeHighestRadioButton)
                .addContainerGap(317, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(attributeLowestRadioButton)
                .addContainerGap(321, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(contentScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                    .addComponent(columnsLabel))
                .addGap(133, 133, 133))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kLabel)
                    .addComponent(kFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stateLabel)
                    .addComponent(stateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(randomRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(randomRandomRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(attributeHighestRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(attributeLowestRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(columnsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contentScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JRadioButton attributeHighestRadioButton;
    protected javax.swing.JRadioButton attributeLowestRadioButton;
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JLabel columnsLabel;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JScrollPane contentScrollPane;
    private org.jdesktop.swingx.JXHeader header;
    private javax.swing.JFormattedTextField kFormattedTextField;
    private javax.swing.JLabel kLabel;
    protected javax.swing.JRadioButton randomRadioButton;
    protected javax.swing.JRadioButton randomRandomRadioButton;
    private javax.swing.JLabel stateLabel;
    private javax.swing.JTextField stateTextField;
    // End of variables declaration//GEN-END:variables

	private static class AttributesRadioButton {
		private JRadioButton radioButton;
		private AttributeColumn column;

		public AttributesRadioButton(AttributeColumn column, boolean selected) {
			radioButton = new JRadioButton(column.getTitle(), selected);
			this.column = column;
		}

		public void setSelected(boolean selected) {
			radioButton.setSelected(selected);
		}

		public boolean isSelected() {
			return radioButton.isSelected();
		}

		public JRadioButton getRadioButton() {
			return radioButton;
		}

		public AttributeColumn getColumn() {
			return column;
		}
	}
}
