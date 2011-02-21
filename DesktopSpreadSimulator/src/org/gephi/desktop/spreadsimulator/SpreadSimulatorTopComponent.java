/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gephi.desktop.spreadsimulator;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.gephi.spreadsimulator.api.Simulation;
import org.gephi.spreadsimulator.api.SimulationEvent;
import org.gephi.spreadsimulator.api.SimulationEvent.EventType;
import org.gephi.spreadsimulator.api.SimulationListener;
import org.gephi.spreadsimulator.spi.StopCondition;
import org.gephi.spreadsimulator.spi.StopConditionBuilder;
import org.gephi.spreadsimulator.spi.StopConditionUI;
import org.gephi.ui.components.SimpleHTMLReport;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
//import org.openide.util.ImageUtilities;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.util.Lookup;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//org.gephi.desktop.spreadsimulator//SpreadSimulator//EN",
					 autostore = false)
public final class SpreadSimulatorTopComponent extends TopComponent {
	private static SpreadSimulatorTopComponent instance;
	/** path to the icon used by the component and its open action */
//    static final String ICON_PATH = "SET/PATH/TO/ICON/HERE";
	private static final String PREFERRED_ID = "SpreadSimulatorTopComponent";

	private Map<String, StopCondition>   scMap;
	private Map<String, StopConditionUI> scUIMap;
	private ComboBoxModel    scComboBoxModel;
	private DefaultListModel scListModel;

	private Simulation simulation;

	public SpreadSimulatorTopComponent() {
		StopConditionBuilder[] scBuilders = Lookup.getDefault().lookupAll(StopConditionBuilder.class).toArray(new StopConditionBuilder[0]);
		StopConditionUI[]      scUIs      = Lookup.getDefault().lookupAll(StopConditionUI.class).toArray(new StopConditionUI[0]);
		scMap   = new HashMap<String, StopCondition>();
		scUIMap = new HashMap<String, StopConditionUI>();
		for (StopConditionBuilder scb : scBuilders)
			scMap.put(scb.getName(), scb.getStopCondition());
		for (StopConditionUI scui : scUIs)
			scUIMap.put(scui.getDisplayName(), scui);
		scComboBoxModel = new DefaultComboBoxModel(scMap.keySet().toArray());
		scListModel = new DefaultListModel();

		simulation = Lookup.getDefault().lookup(Simulation.class);
		simulation.addSimulationListener(new SimulationListener() {
			@Override
			public void simulationChanged(SimulationEvent event) {
				if (event.is(EventType.INIT)) {
					scListModel.clear();
					addSCButton.setEnabled(true);
					removeSCButton.setEnabled(false);
					// fireSCSButton.setEnabled(true);
					// fireRSButton.setEnabled(true);
					delayFormattedTextField.setText(simulation.getDelay() + "");
					delayFormattedTextField.setEnabled(true);
					stopButton.setEnabled(false);
					startButton.setEnabled(false);
					previousStepButton.setEnabled(false);
					nextStepButton.setEnabled(false);
					stepLabel.setText(NbBundle.getMessage(SpreadSimulatorTopComponent.class, "SpreadSimulatorTopComponent.stepLabel.text")
						+ " " + simulation.getSimulationData().getCurrentStep());
				}
				else if (event.is(EventType.ADD_STOP_CONDITION)) {
					removeSCButton.setEnabled(true);
					startButton.setEnabled(!simulation.isFinished());
					nextStepButton.setEnabled(!simulation.isFinished());
				}
				else if (event.is(EventType.REMOVE_STOP_CONDITION)) {
					removeSCButton.setEnabled(!scListModel.isEmpty());
					startButton.setEnabled(!scListModel.isEmpty() && !simulation.isFinished());
					nextStepButton.setEnabled(!scListModel.isEmpty() && !simulation.isFinished());
				}
				else if (event.is(EventType.CANCEL)) {
					addSCButton.setEnabled(true);
					removeSCButton.setEnabled(true);
					stopButton.setEnabled(false);
					startButton.setEnabled(!simulation.isFinished());
					nextStepButton.setEnabled(!simulation.isFinished());
				}
				else if (event.is(EventType.START)) {
					stopButton.setEnabled(true);
				}
				else if (event.is(EventType.PREVIOUS_STEP)) {
					addSCButton.setEnabled(true);
					removeSCButton.setEnabled(true);
					startButton.setEnabled(true);
					previousStepButton.setEnabled(simulation.getSimulationData().getCurrentStep() > 0);
					nextStepButton.setEnabled(true);
				}
				else if (event.is(EventType.NEXT_STEP)) {
					addSCButton.setEnabled(simulation.isCancelled());
					removeSCButton.setEnabled(simulation.isCancelled());
					startButton.setEnabled(simulation.isCancelled() && !simulation.isFinished());
					stepLabel.setText(NbBundle.getMessage(SpreadSimulatorTopComponent.class, "SpreadSimulatorTopComponent.stepLabel.text")
							+ " " + simulation.getSimulationData().getCurrentStep());
					// previousStepButton.setEnabled(true);
					nextStepButton.setEnabled(simulation.isCancelled() && !simulation.isFinished());
					if (simulation.isFinished())
						WindowManager.getDefault().invokeWhenUIReady(new Runnable() {
							public void run() {
								String report = simulation.getReport();
								SimpleHTMLReport dialog = new SimpleHTMLReport(WindowManager.getDefault().getMainWindow(), report);
							}
						});
				}
			}
		});

		initComponents();
		setName(NbBundle.getMessage(SpreadSimulatorTopComponent.class, "CTL_SpreadSimulatorTopComponent"));
		// setToolTipText(NbBundle.getMessage(SpreadSimulatorTopComponent.class, "HINT_SpreadSimulatorTopComponent"));
//        setIcon(ImageUtilities.loadImage(ICON_PATH, true));
		putClientProperty(TopComponent.PROP_MAXIMIZATION_DISABLED, Boolean.TRUE);
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        fireSCSButton = new javax.swing.JButton();
        initButton = new javax.swing.JButton();
        fireRSButton = new javax.swing.JButton();
        stepLabel = new javax.swing.JLabel();
        addSCButton = new javax.swing.JButton();
        removeSCButton = new javax.swing.JButton();
        scScrollPane = new javax.swing.JScrollPane();
        scList = new javax.swing.JList();
        nextStepButton = new javax.swing.JButton();
        scComboBox = new javax.swing.JComboBox();
        previousStepButton = new javax.swing.JButton();
        stopButton = new javax.swing.JButton();
        startButton = new javax.swing.JButton();
        delayLabel = new javax.swing.JLabel();
        delayFormattedTextField = new javax.swing.JFormattedTextField();

        setMinimumSize(new java.awt.Dimension(300, 345));
        setLayout(new java.awt.GridBagLayout());

        org.openide.awt.Mnemonics.setLocalizedText(fireSCSButton, org.openide.util.NbBundle.getMessage(SpreadSimulatorTopComponent.class, "SpreadSimulatorTopComponent.fireSCSButton.text")); // NOI18N
        fireSCSButton.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        add(fireSCSButton, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(initButton, org.openide.util.NbBundle.getMessage(SpreadSimulatorTopComponent.class, "SpreadSimulatorTopComponent.initButton.text")); // NOI18N
        initButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                initButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        add(initButton, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(fireRSButton, org.openide.util.NbBundle.getMessage(SpreadSimulatorTopComponent.class, "SpreadSimulatorTopComponent.fireRSButton.text")); // NOI18N
        fireRSButton.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        add(fireRSButton, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(stepLabel, org.openide.util.NbBundle.getMessage(SpreadSimulatorTopComponent.class, "SpreadSimulatorTopComponent.stepLabel.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(stepLabel, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(addSCButton, org.openide.util.NbBundle.getMessage(SpreadSimulatorTopComponent.class, "SpreadSimulatorTopComponent.addSCButton.text")); // NOI18N
        addSCButton.setEnabled(false);
        addSCButton.setMaximumSize(new java.awt.Dimension(71, 23));
        addSCButton.setMinimumSize(new java.awt.Dimension(71, 23));
        addSCButton.setPreferredSize(new java.awt.Dimension(71, 23));
        addSCButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSCButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 2, 0, 0);
        add(addSCButton, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(removeSCButton, org.openide.util.NbBundle.getMessage(SpreadSimulatorTopComponent.class, "SpreadSimulatorTopComponent.removeSCButton.text")); // NOI18N
        removeSCButton.setEnabled(false);
        removeSCButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeSCButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 2, 0, 10);
        add(removeSCButton, gridBagConstraints);

        scScrollPane.setMaximumSize(new java.awt.Dimension(35, 100));
        scScrollPane.setMinimumSize(new java.awt.Dimension(35, 100));
        scScrollPane.setPreferredSize(new java.awt.Dimension(35, 100));

        scList.setModel(scListModel);
        scList.setEnabled(false);
        scScrollPane.setViewportView(scList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        add(scScrollPane, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(nextStepButton, org.openide.util.NbBundle.getMessage(SpreadSimulatorTopComponent.class, "SpreadSimulatorTopComponent.nextStepButton.text")); // NOI18N
        nextStepButton.setEnabled(false);
        nextStepButton.setMaximumSize(new java.awt.Dimension(99, 23));
        nextStepButton.setMinimumSize(new java.awt.Dimension(99, 23));
        nextStepButton.setPreferredSize(new java.awt.Dimension(99, 23));
        nextStepButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextStepButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 0);
        add(nextStepButton, gridBagConstraints);

        scComboBox.setModel(scComboBoxModel);
        scComboBox.setMaximumSize(new java.awt.Dimension(100, 18));
        scComboBox.setMinimumSize(new java.awt.Dimension(100, 18));
        scComboBox.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        add(scComboBox, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(previousStepButton, org.openide.util.NbBundle.getMessage(SpreadSimulatorTopComponent.class, "SpreadSimulatorTopComponent.previousStepButton.text")); // NOI18N
        previousStepButton.setEnabled(false);
        previousStepButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousStepButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 0);
        add(previousStepButton, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(stopButton, org.openide.util.NbBundle.getMessage(SpreadSimulatorTopComponent.class, "SpreadSimulatorTopComponent.stopButton.text")); // NOI18N
        stopButton.setEnabled(false);
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        add(stopButton, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(startButton, org.openide.util.NbBundle.getMessage(SpreadSimulatorTopComponent.class, "SpreadSimulatorTopComponent.startButton.text")); // NOI18N
        startButton.setEnabled(false);
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        add(startButton, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(delayLabel, org.openide.util.NbBundle.getMessage(SpreadSimulatorTopComponent.class, "SpreadSimulatorTopComponent.delayLabel.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        add(delayLabel, gridBagConstraints);

        delayFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###0"))));
        delayFormattedTextField.setText(org.openide.util.NbBundle.getMessage(SpreadSimulatorTopComponent.class, "SpreadSimulatorTopComponent.delayFormattedTextField.text")); // NOI18N
        delayFormattedTextField.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        add(delayFormattedTextField, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

	private void initButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_initButtonActionPerformed
		simulation.init();
	}//GEN-LAST:event_initButtonActionPerformed

	private void addSCButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSCButtonActionPerformed
		String selectedSC = (String)scComboBoxModel.getSelectedItem();
		StopCondition sc = scMap.get(selectedSC);
		StopConditionUI scui = scUIMap.get(selectedSC);
		if (selectedSC != null && !scListModel.contains(selectedSC)) {
			JPanel settingsPanel = scui.getSettingsPanel();
			scui.setup(sc);
			DialogDescriptor dd = new DialogDescriptor(settingsPanel, selectedSC);
			if (DialogDisplayer.getDefault().notify(dd).equals(NotifyDescriptor.OK_OPTION))
				scui.unsetup();
			scListModel.addElement(selectedSC);
			simulation.addStopCondition(sc);
		}
	}//GEN-LAST:event_addSCButtonActionPerformed

	private void removeSCButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeSCButtonActionPerformed
		String selectedSC = (String)scComboBoxModel.getSelectedItem();
		if (selectedSC != null && scListModel.contains(selectedSC)) {
			scListModel.removeElement(selectedSC);
			simulation.removeStopCondition(scMap.get(selectedSC));
		}
	}//GEN-LAST:event_removeSCButtonActionPerformed

	private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButtonActionPerformed
		simulation.cancel();
	}//GEN-LAST:event_stopButtonActionPerformed

	private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
		addSCButton.setEnabled(false);
		removeSCButton.setEnabled(false);
		startButton.setEnabled(false);
		nextStepButton.setEnabled(false);

		String text = delayFormattedTextField.getText();
		long delay = Long.parseLong(text);
		simulation.setDelay(delay);
		simulation.start();
	}//GEN-LAST:event_startButtonActionPerformed

	private void previousStepButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousStepButtonActionPerformed
		addSCButton.setEnabled(false);
		removeSCButton.setEnabled(false);
		startButton.setEnabled(false);
		previousStepButton.setEnabled(false);
		nextStepButton.setEnabled(false);
		
		simulation.previousStep();
	}//GEN-LAST:event_previousStepButtonActionPerformed

	private void nextStepButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextStepButtonActionPerformed
		addSCButton.setEnabled(false);
		removeSCButton.setEnabled(false);
		startButton.setEnabled(false);
		nextStepButton.setEnabled(false);

		String text = delayFormattedTextField.getText();
		long delay = Long.parseLong(text);
		simulation.setDelay(delay);
		simulation.nextStep();
	}//GEN-LAST:event_nextStepButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addSCButton;
    private javax.swing.JFormattedTextField delayFormattedTextField;
    private javax.swing.JLabel delayLabel;
    private javax.swing.JButton fireRSButton;
    private javax.swing.JButton fireSCSButton;
    private javax.swing.JButton initButton;
    private javax.swing.JButton nextStepButton;
    private javax.swing.JButton previousStepButton;
    private javax.swing.JButton removeSCButton;
    private javax.swing.JComboBox scComboBox;
    private javax.swing.JList scList;
    private javax.swing.JScrollPane scScrollPane;
    private javax.swing.JButton startButton;
    private javax.swing.JLabel stepLabel;
    private javax.swing.JButton stopButton;
    // End of variables declaration//GEN-END:variables
	/**
	 * Gets default instance. Do not use directly: reserved for *.settings files only,
	 * i.e. deserialization routines; otherwise you could get a non-deserialized instance.
	 * To obtain the singleton instance, use {@link #findInstance}.
	 */
	public static synchronized SpreadSimulatorTopComponent getDefault() {
		if (instance == null) {
			instance = new SpreadSimulatorTopComponent();
		}
		return instance;
	}

	/**
	 * Obtain the SpreadSimulatorTopComponent instance. Never call {@link #getDefault} directly!
	 */
	public static synchronized SpreadSimulatorTopComponent findInstance() {
		TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
		if (win == null) {
			Logger.getLogger(SpreadSimulatorTopComponent.class.getName()).warning(
					"Cannot find " + PREFERRED_ID + " component. It will not be located properly in the window system.");
			return getDefault();
		}
		if (win instanceof SpreadSimulatorTopComponent) {
			return (SpreadSimulatorTopComponent)win;
		}
		Logger.getLogger(SpreadSimulatorTopComponent.class.getName()).warning(
				"There seem to be multiple components with the '" + PREFERRED_ID
				+ "' ID. That is a potential source of errors and unexpected behavior.");
		return getDefault();
	}

	@Override
	public int getPersistenceType() {
		return TopComponent.PERSISTENCE_ALWAYS;
	}

	@Override
	public void componentOpened() {
		// TODO add custom code on component opening
	}

	@Override
	public void componentClosed() {
		// TODO add custom code on component closing
	}

	void writeProperties(java.util.Properties p) {
		// better to version settings since initial version as advocated at
		// http://wiki.apidesign.org/wiki/PropertyFiles
		p.setProperty("version", "1.0");
		// TODO store your settings
	}

	Object readProperties(java.util.Properties p) {
		if (instance == null) {
			instance = this;
		}
		instance.readPropertiesImpl(p);
		return instance;
	}

	private void readPropertiesImpl(java.util.Properties p) {
		String version = p.getProperty("version");
		// TODO read your settings according to their version
	}

	@Override
	protected String preferredID() {
		return PREFERRED_ID;
	}
}
