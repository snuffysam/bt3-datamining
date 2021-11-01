package moddingApp;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUIElements implements ActionListener {

	JFrame frame;
	JPanel topMenu;
	JButton remapButton;
	JButton viewButton;
	JButton importButton;
	JPanel mainSection;
	JComboBox<String> cssList;
	JLabel mappingLabel;
	JComboBox<String> allFightersList;
	JPanel bottomMenu;
	JButton resetButton;
	JButton exportButton;
	String[] allFighterNames;

	public GUIElements() {
		frame = new JFrame("DBZ BT3 Modding App");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);

		topMenu = new JPanel();
		remapButton = new JButton("Remap Fighters");
		viewButton = new JButton("View/Modify Fighter Data");
		importButton = new JButton("Import New Fighter");
		topMenu.add(BorderLayout.WEST, remapButton);
		topMenu.add(BorderLayout.CENTER, viewButton);
		topMenu.add(BorderLayout.EAST, importButton);

		mainSection = new JPanel(new BorderLayout());
		cssList = new JComboBox<String>(CharacterFileRef.GetSlotNames());
		cssList.addActionListener(this);
		mappingLabel = new JLabel(
				"= = = Choose which slot on the select screen (above)... = = =\n= = = ...maps to which fighter data (below)! = = =");
		allFighterNames = CheckAllFiles.GetAllFighterNames();
		allFightersList = new JComboBox<String>(allFighterNames);
		int index = 0;
		for (int i = 0; i < allFighterNames.length; i++) {
			if (allFighterNames[i].equals(CheckAllFiles.fighterMappings.get(CharacterFileRef.allFighterNames[0]))) {
				index = i;
				break;
			}
		}
		allFightersList.setSelectedIndex(index);
		mainSection.add(BorderLayout.NORTH, cssList);
		mainSection.add(BorderLayout.CENTER, mappingLabel);
		mainSection.add(BorderLayout.SOUTH, allFightersList);

		bottomMenu = new JPanel();
		resetButton = new JButton("Reset All Mappings");
		exportButton = new JButton("Export Re-mapped Roster");
		bottomMenu.add(BorderLayout.WEST, resetButton);
		bottomMenu.add(BorderLayout.EAST, exportButton);

		frame.getContentPane().add(BorderLayout.NORTH, topMenu);
		frame.getContentPane().add(BorderLayout.CENTER, mainSection);
		frame.getContentPane().add(BorderLayout.SOUTH, bottomMenu);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(cssList)) {
			int ind = cssList.getSelectedIndex();
			int index = 0;
			for (int i = 0; i < allFighterNames.length; i++) {
				if (allFighterNames[i]
						.equals(CheckAllFiles.fighterMappings.get(CharacterFileRef.allFighterNames[ind]))) {
					index = i;
					break;
				}
			}
			allFightersList.setSelectedIndex(index);
		}

	}

}
