/**
 * 
 */
package com.cognizant.automation;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.jnlp.BasicService;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.cognizant.automation.bo.Record;
import com.cognizant.automation.utils.ExcelFileReader;

/**
 * @author 238209
 *
 */
public class Main {

	public static BasicService basicService = null;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Creating the Frame
        JFrame frame = new JFrame("Chat Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("FILE");
        JMenu m2 = new JMenu("Help");
        mb.add(m1);
        mb.add(m2);
        JMenuItem m11 = new JMenuItem("Open");
        JMenuItem m22 = new JMenuItem("Save as");
        m1.add(m11);
        m1.add(m22);

        String[] modules = {"Pre-Edit", "TFA/TBA", "COB Recovery", "TMG"};
        
        JLabel moduleLabel = new JLabel("Select Eligibility Module to Run");
        
        //Creating the panel at bottom and adding components
        JPanel modulePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        JComboBox moduleList = new JComboBox(modules);
        moduleList.setSelectedIndex(2);
        //petList.addActionListener(this);
        
        modulePanel.add(moduleLabel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        
        modulePanel.add(moduleList, gbc);
    
        /*try {
        	basicService = (BasicService)
        			ServiceManager.lookup("javax.jnlp.BasicService");
        } catch (UnavailableServiceException e) {
        	System.err.println("Lookup failed: " + e);
        }*/
        
        /*JLabel label = new JLabel("Enter Text");
        JTextField tf = new JTextField(10); // accepts upto 10 characters
        JButton send = new JButton("Send");
        JButton reset = new JButton("Reset");
        panel.add(label); // Components Added using Flow Layout
        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(send);
        panel.add(reset);*/
        
        modulePanel.add(moduleList, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        
        JLabel label = new JLabel("User Id");
        modulePanel.add(label, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;
        JTextField tf = new JTextField(10);
        modulePanel.add(tf, gbc);

        String filePath = "D:/238209/Technical/jnlp/SampleTc.xlsx";
        List<Record> recordList = 
        		ExcelFileReader.readFileWithHeader(filePath);
        List<String> testCaseList = new ArrayList<String>();
        for (Record record : recordList) {
			testCaseList.add(record.getStringValue("Test Case Id"));
		}
        
        JComboBox tcList = new JComboBox(testCaseList.toArray(new String[0]));
        //tcList.setSelectedIndex(0);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 4;
        modulePanel.add(tcList, gbc);
        
        //Adding Components to the frame.
        frame.getContentPane().add(modulePanel);
        //frame.getContentPane().add(BorderLayout.NORTH, mb);
        //frame.getContentPane().add(BorderLayout.CENTER, ta);
        frame.setVisible(true);

	}

}
