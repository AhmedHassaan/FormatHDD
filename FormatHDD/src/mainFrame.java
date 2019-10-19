import java.awt.EventQueue;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import javafx.scene.control.ComboBox;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class mainFrame {

	private JFrame frame;
	List<folderData> folderList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainFrame window = new mainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 704, 366);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnInfo = new JButton("Info");
		btnInfo.setBounds(53, 236, 110, 47);
		
		JButton btnFormat = new JButton("Format");
		btnFormat.setBounds(284, 236, 110, 47);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnExit.setBounds(520, 236, 110, 47);
		
		JComboBox comboBox1 = new JComboBox();
		comboBox1.setBounds(53, 51, 183, 20);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(btnInfo);
		frame.getContentPane().add(btnFormat);
		frame.getContentPane().add(btnExit);
		frame.getContentPane().add(comboBox1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(53, 74, 183, 116);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblTotalSpace = new JLabel("Total Space : ");
		lblTotalSpace.setBounds(10, 11, 163, 25);
		panel.add(lblTotalSpace);
		
		JLabel lblUsedSpace = new JLabel("Used Space : ");
		lblUsedSpace.setBounds(10, 43, 163, 25);
		panel.add(lblUsedSpace);
		
		JLabel lblFreeSpace = new JLabel("Free Space : ");
		lblFreeSpace.setBounds(10, 79, 163, 25);
		panel.add(lblFreeSpace);
		
		folderList = new ArrayList<>();
		// get main Folder name and data
		File[] roots = File.listRoots();
		String name;
		long used,free,total;
		comboBox1.removeAllItems();
		System.out.println("Found " + roots.length + " roots ");
		for (int i = 0; i < roots.length; i++) {
			name = roots[i].toString();
			comboBox1.addItem(name);
			System.out.print(name);
			free = roots[i].getFreeSpace() / 1000000000;
			total = roots[i].getTotalSpace() / 1000000000;
			used = total - free;
			folderData data = new folderData();
			data.setFree(free);data.setName(name);data.setTotal(total);data.setUsed(used);
			folderList.add(data);
			System.out.print(": Total Space= " + total + " GB");
			System.out.print(", Used Space= " + used + " GB");
			System.out.print(", free Space= " + free + " GB");
			System.out.println();
		}
		System.out.println(comboBox1.getItemCount());
		lblFreeSpace.setText("Free Space: " + String.valueOf(folderList.get(0).getFree()));
		lblUsedSpace.setText("Used Space: " + String.valueOf(folderList.get(0).getUsed()));
		lblTotalSpace.setText("Total Space: " + String.valueOf(folderList.get(0).getTotal()));
		
		comboBox1.addActionListener(new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	int selectedIndex = comboBox1.getSelectedIndex();
		    	lblFreeSpace.setText("Free Space: " + String.valueOf(folderList.get(selectedIndex).getFree()));
				lblUsedSpace.setText("Used Space: " + String.valueOf(folderList.get(selectedIndex).getUsed()));
				lblTotalSpace.setText("Total Space: " + String.valueOf(folderList.get(selectedIndex).getTotal()));
		    }
		});
		
		btnFormat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formatFolder(comboBox1.getSelectedIndex());
			}
		});
	}
	private void formatFolder(int index) {
		String path = String.valueOf(folderList.get(index).getName());
		

		System.out.println("-------------------------");
		System.out.println(path);
		int dialogButton = JOptionPane.YES_NO_OPTION;
		int dialogResult = JOptionPane.showConfirmDialog(null, "Would You Like to Format this?", "Warning",
				dialogButton);
		if(dialogResult == JOptionPane.YES_OPTION) {
			//path = "E:\\Kanata no Astra";
			File file = new File(path);
			delete(file, false);
			// show done message
			JOptionPane.showConfirmDialog(null, "Format Completed", 
					"Finish", 
					JOptionPane.DEFAULT_OPTION);
		}
	}
	private void delete(File file,boolean deleteThis) {
		File[] temp = file.listFiles();
		System.out.println(file.getPath());
		if(temp!=null) {
			for(int i=0;i<temp.length;i++) {
				if(temp[i].listFiles() == null) {
					temp[i].delete();
					System.out.println(temp[i].getPath());
				}
				else {
					delete(temp[i],true);
				}
			}
		}
		if(deleteThis) {
			file.delete();
		}
	}
}
