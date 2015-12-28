package binaryTree;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BinaryTreeFrame extends JFrame {
	/**
	 * The program was coded in a way that every time, data is inserted or
	 * removed, the code re-requests the proper traversed list from the Binary
	 * Tree class even through I can manipulate the listModel data easier
	 * because I want to show how the data really is rather than just doing what
	 * is meant to happen. (When the data is just reorganized, the list is not
	 * re-accessed from the Binary Tree)
	 */
	private static final long serialVersionUID = 1L;
	private JList<Integer> list;
	private Container container;
	private JPanel eastPanel;
	private JPanel centerPanel;
	private DefaultListModel<Integer> listModel;
	private BinaryTree<Integer> tree;
	private JLabel root;
	private JButton addValue;
	private JButton removeValue;
	private JButton reorganize;
	private JButton addValueR;
	private JButton removeValueR;
	private JButton reorganizeR;
	private JLabel iterative;
	private JLabel recursive;
	private JLabel foundResult;
	private JLabel find;
	private JLabel northLabel;
	private JLabel image;
	private JTextField findText;
	private ImageIcon treePic;
	private Image img;
	private Image newImg;

	public BinaryTreeFrame() {
		setTitle("Binary Tree");
		setSize(600, 550);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.container = getContentPane();
		this.eastPanel = new JPanel();
		this.centerPanel = new JPanel();
		this.listModel = new DefaultListModel<Integer>();
		this.list = new JList<Integer>(listModel);
		this.tree = new BinaryTree<Integer>();
		this.addValue = new JButton("Add Value");
		this.removeValue = new JButton("Remove Value");
		this.reorganize = new JButton("Reorganize");
		this.addValueR = new JButton("Add Value");
		this.removeValueR = new JButton("Remove Value");
		this.reorganizeR = new JButton("Reorganize");
		this.root = new JLabel("Root = ");
		this.iterative = new JLabel("Iterative Approach");
		this.recursive = new JLabel("Recursive Approach");
		this.foundResult = new JLabel();
		this.image = new JLabel();
		this.northLabel = new JLabel(
				"Note: Every time the user makes a change, new info is accessed from the tree to represent valid data.");
		this.find = new JLabel("Find: ");
		this.findText = new JTextField();
		this.treePic = new ImageIcon(this.getClass().getResource(
				"BinaryTreeImage2.png"));
		this.img = treePic.getImage();
		this.newImg = img.getScaledInstance(400, 200,
				java.awt.Image.SCALE_SMOOTH);
		this.treePic = new ImageIcon(this.newImg);
		setValues();
		addValues();
	}

	private void setValues() {
		
		this.container.setLayout(new BorderLayout());
		this.eastPanel.setLayout(new GridLayout(11, 1));
		this.list.setLayoutOrientation(JList.VERTICAL_WRAP);
		this.list.setVisibleRowCount(-1);
		this.iterative.setFont(new Font("SansSerif", Font.BOLD, 20));
		this.recursive.setFont(new Font("SansSerif", Font.BOLD, 20));
		this.find.setFont(new Font("SansSerif", Font.BOLD, 20));
		this.findText.setFont(new Font("SansSerif", Font.PLAIN, 20));
		this.centerPanel.setLayout(new BorderLayout());
		this.root.setFont(new Font("SansSerif", Font.BOLD, 20));
		this.image.setIcon(treePic);
	}

	private void addValues() {
		this.eastPanel.add(this.iterative);
		this.eastPanel.add(this.addValue);
		this.eastPanel.add(this.removeValue);
		this.eastPanel.add(this.reorganize);
		this.eastPanel.add(this.recursive);
		this.eastPanel.add(this.addValueR);
		this.eastPanel.add(this.removeValueR);
		this.eastPanel.add(this.reorganizeR);
		this.eastPanel.add(this.find);
		this.eastPanel.add(this.findText);
		this.eastPanel.add(this.foundResult);
		this.centerPanel.add(this.image, BorderLayout.NORTH);
		this.centerPanel.add(this.list, BorderLayout.CENTER);
		this.container.add(this.eastPanel, BorderLayout.EAST);
		this.container.add(this.centerPanel, BorderLayout.CENTER);
		this.container.add(this.root, BorderLayout.SOUTH);
		this.container.add(this.northLabel, BorderLayout.NORTH);
		this.addValue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String value;
				value = JOptionPane.showInputDialog("Enter a value to add.");
				int addValue = Integer.parseInt(value);
				tree.insert(addValue);
				// the next few lines is an example to what I refer to as
				// re-access the sorted
				// list. I could've just manipulated the list Model
				ArrayList<Integer> array = new ArrayList<Integer>();
				array = tree.traverse(array);
				listModel.removeAllElements();
				for (int i = 0; i < array.size(); i++) {
					listModel.addElement(array.get(i));
				}
				root.setText("Root = " + tree.getRoot());
			}
		});
		this.removeValue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (tree.isEmpty()) {
					playSound();
					JOptionPane.showMessageDialog(null,
							"Sorry, your list is empty.");
				} else {
					String value;
					value = JOptionPane
							.showInputDialog("Enter a value to remove.");
					int removeValue = Integer.parseInt(value);
					// removes values with recursive method
					boolean found = tree.removeVal(removeValue);
					if (!found) {
						playSound();
						JOptionPane.showMessageDialog(null,
								"Sorry. The value wasn't found.");
					}
					if (tree.isEmpty()) {
						listModel.removeAllElements();
						root.setText("Root = ");
					} else {
						ArrayList<Integer> array = new ArrayList<Integer>();
						array = tree.traverse(array);
						listModel.removeAllElements();
						for (int i = 0; i < array.size(); i++) {
							listModel.addElement(array.get(i));
						}
						root.setText("Root = " + tree.getRoot());
					}
				}
			}
		});
		this.reorganize.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (tree.isEmpty()) {
					root.setText("Root = ");
				} else {
					tree.reorganize();
					root.setText("Root = " + tree.getRoot());
				}
			}
		});
		this.addValueR.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String value;
				value = JOptionPane.showInputDialog("Enter a value to add.");
				int addValue = Integer.parseInt(value);
				tree.insertRecur(addValue);
				ArrayList<Integer> array = new ArrayList<Integer>();
				array = tree.traverseInOrder(array);
				listModel.removeAllElements();
				for (int i = 0; i < array.size(); i++) {
					listModel.addElement(array.get(i));
				}
				root.setText("Root = " + tree.getRoot());
			}
		});
		this.removeValueR.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (tree.isEmpty()) {
					playSound();
					JOptionPane.showMessageDialog(null,
							"Sorry, your list is empty.");
				} else {
					String value;
					value = JOptionPane
							.showInputDialog("Enter a value to remove.");
					int removeValue = Integer.parseInt(value);
					boolean found = tree.removeVal(removeValue);
					if (!found) {
						playSound();
						JOptionPane.showMessageDialog(null,
								"Sorry. The value wasn't found.");
					}
					if (tree.isEmpty()) {
						listModel.removeAllElements();
						root.setText("Root = ");
					} else {
						ArrayList<Integer> array = new ArrayList<Integer>();
						array = tree.traverseInOrder(array);
						listModel.removeAllElements();
						for (int i = 0; i < array.size(); i++) {
							listModel.addElement(array.get(i));
						}
						root.setText("Root = " + tree.getRoot());
					}
				}
			}
		});
		this.reorganizeR.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (tree.isEmpty()) {
					root.setText("Root = ");
				} else {
					tree.reorganizeR();
					root.setText("Root = " + tree.getRoot());
				}
			}
		});
		this.findText.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean found = tree.get(Integer.parseInt(findText.getText()));
				if (found) {
					foundResult.setText("Found");
				} else {
					foundResult.setText("Not Found");
				}
			}
		});
	}

	public void playSound() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Applet.newAudioClip(getClass().getResource("beep.wav")).play();
			}
		}).start();
	}
}
