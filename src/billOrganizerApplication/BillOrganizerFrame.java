package billOrganizerApplication;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class BillOrganizerFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private Container container;
	private BillOrganizer organizer;
	private JButton addBill;
	private JButton addBillStatement;
	private JButton payBill;
	private JLabel findTotalBills;
	private JButton viewByDate;
	private JButton viewByBillType;
	private JButton viewByAmount;
	private JButton saveOrganizer;
	private JButton restore;
	private JButton payBillID;
	private JPanel northPanel;
	private JPanel eastPanel;
	private JPanel centerPanel;
	private JLabel totalDue;
	private BillOrganizerFrame myself;
	private JList<?> list;
	private ArrayList<JLabel> array;

	public BillOrganizerFrame() {
		setTitle("Bill Organizer");
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.myself = this;
		this.container = getContentPane();
		this.organizer = new BillOrganizer();
		this.addBill = new JButton("Insert a new Bill");
		this.addBillStatement = new JButton("Insert a Bill Statement");
		this.payBill = new JButton("Pay Next Bill");
		this.findTotalBills = new JLabel("Total Amount Due");
		this.viewByAmount = new JButton("View bills by Amount");
		this.viewByBillType = new JButton("View bills by Type");
		this.viewByDate = new JButton("View bills by Date");
		this.saveOrganizer = new JButton("Save");
		this.restore = new JButton("Restore my saved file");
		this.payBillID = new JButton("Pay a Specific Bill");
		this.northPanel = new JPanel();
		this.eastPanel = new JPanel();
		this.centerPanel = new JPanel();
		this.totalDue = new JLabel();
		this.array = new ArrayList<JLabel>();
		this.list = new JList<Object>();
		setComponents();
		addComponents();
	}

	public void setComponents() {
		this.container.setLayout(new BorderLayout());
		this.northPanel.setLayout(new FlowLayout());
		this.eastPanel.setLayout(new GridLayout(8, 1));
		this.centerPanel.setLayout(new BoxLayout(centerPanel,
				BoxLayout.PAGE_AXIS));
		this.list.setLayoutOrientation(JList.VERTICAL_WRAP);
		this.list.setVisibleRowCount(-1);

	}

	public void addComponents() {
		this.northPanel.add(this.viewByAmount);
		this.northPanel.add(this.viewByBillType);
		this.northPanel.add(this.viewByDate);
		this.eastPanel.add(this.addBill);
		this.eastPanel.add(this.addBillStatement);
		this.eastPanel.add(this.payBillID);
		this.eastPanel.add(this.payBill);
		this.eastPanel.add(this.saveOrganizer);
		this.eastPanel.add(this.restore);
		this.eastPanel.add(this.findTotalBills);
		this.eastPanel.add(this.totalDue);
		this.container.add(this.northPanel, BorderLayout.NORTH);
		this.container.add(this.eastPanel, BorderLayout.EAST);
		this.container.add(this.centerPanel, BorderLayout.CENTER);
		setCenter();
		this.saveOrganizer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String filename = JOptionPane
						.showInputDialog("What would you like to save your file as?(.ser)");
				// "BillLList.ser"
				try {
					organizer.closeOrganizer(filename);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		this.restore.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				int dialogResult = JOptionPane
						.showConfirmDialog(
								null,
								"Restoring a file will delete your current bill entries. Would you like to proceed?",
								"Warning", JOptionPane.YES_NO_OPTION);
				if (dialogResult == JOptionPane.YES_OPTION) {

					String filename = JOptionPane
							.showInputDialog("Enter the file name. (.ser)");
					try {
						organizer = new BillOrganizer(filename);
						array = organizer.toLabelList();
						setCenter();
					} catch (ClassNotFoundException | IOException e) {
						JOptionPane.showMessageDialog(null,
								"File does not exist");
					} catch (DuplicateDataException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		this.addBill.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				BillFrame frame = new BillFrame(myself);
				frame.setVisible(true);
			}
		});
		this.addBillStatement.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String filename = JOptionPane
						.showInputDialog("Please enter the file name of the bill statement. Please note: the file must be in the correct format.(.txt)");
				File file = new File(filename);
				Scanner input;
				try {
					input = new Scanner(file);
					organizer.insertStatements(input);
					array = organizer.toLabelList();
					setCenter();
				} catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(null,
							"Sorry. The File you requested does not exist");
				} catch (DuplicateDataException e) {
					JOptionPane.showMessageDialog(null,
							"Error! Duplicate data!");
				}
			}
		});
		this.viewByAmount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LLIterator<Bill> iter = organizer.iteratorByAmount();
				ArrayList<JLabel> allLabels = new ArrayList<JLabel>();
				while (iter.hasNext()) {
					Bill bill = iter.next();
					JLabel[] thisLabel = bill.toBillLabel();
					for (int i = 0; i < thisLabel.length; i++) {
						allLabels.add(thisLabel[i]);
					}
					allLabels.add(new JLabel(" "));
				}
				array = allLabels;
				setCenter();
			}
		});
		this.viewByBillType.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LLIterator<Bill> iter = organizer.iteratorByType();
				ArrayList<JLabel> allLabels = new ArrayList<JLabel>();
				while (iter.hasNext()) {
					Bill bill = iter.next();
					JLabel[] thisLabel = bill.toBillLabel();
					for (int i = 0; i < thisLabel.length; i++) {
						allLabels.add(thisLabel[i]);
					}
					allLabels.add(new JLabel(" "));
				}
				array = allLabels;
				setCenter();
			}
		});
		this.viewByDate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LLIterator<Bill> iter = organizer.iteratorByDate();

				ArrayList<JLabel> allLabels = new ArrayList<JLabel>();
				while (iter.hasNext()) {
					Bill bill = iter.next();
					JLabel[] thisLabel = bill.toBillLabel();
					for (int i = 0; i < thisLabel.length; i++) {
						allLabels.add(thisLabel[i]);
					}
					allLabels.add(new JLabel(" "));
				}
				array = allLabels;
				setCenter();
			}
		});
		this.payBillID.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String idString = JOptionPane
						.showInputDialog("Enter the bill ID of the bill you would like to pay?");
				int ID = Integer.parseInt(idString);
				Bill bill = null;
				try {
					bill = organizer.payBillID(ID);
					JOptionPane.showMessageDialog(
							null,
							"Thank you for your payment of $"
									+ bill.getAmountDue() + ".");
					array = organizer.toLabelList();
					setCenter();
				} catch (NotFoundException e) {
					JOptionPane.showMessageDialog(null,
							"Sorry, no bill was found with that ID.");
				}

			}
		});
		this.payBill.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				BillCriteriaEnum criteria;
				String[] choices = { "Pay the next bill due",
						"Pay the lowest Bill", "Pay the next Bill by Criterea" };
				String input = (String) JOptionPane
						.showInputDialog(null,
								"Which bill would you like to pay?", "Pay...",
								JOptionPane.QUESTION_MESSAGE, null, choices,
								choices[0]); // Array of choices

				if (input == choices[0]) {
					criteria = BillCriteriaEnum.BILLDUEDATE;
				} else if (input == choices[1]) {
					criteria = BillCriteriaEnum.BILLAMOUNT;
				} else {
					criteria = BillCriteriaEnum.BILLTYPE;
				}
				Bill bill = null;
				try {

					bill = organizer.payNextBill(criteria);
					JOptionPane.showMessageDialog(
							null,
							"Thank you for your payment of $"
									+ bill.getAmountDue() + ".");
					array = organizer.toLabelList();
					setCenter();
				} catch (NotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	public void ReDisplayList(Bill bill) throws DuplicateDataException {
		this.organizer.insert(bill);
		this.array = this.organizer.toLabelList();
		System.out.println(organizer.totalBills());
		setCenter();
	}

	public void setCenter() {
		centerPanel.removeAll();
		DefaultListModel<String> model = new DefaultListModel<String>();
		for (int i = 0; i < this.array.size(); i++) {
			model.addElement(this.array.get(i).getText());
		}
		centerPanel.add(new JScrollPane(new JList<String>(model)));
		DecimalFormat formatter = new DecimalFormat("###,###,###.00");
		totalDue.setText("$"
				+ String.valueOf(formatter.format(organizer.totalBills())));
		revalidate();
		repaint();

	}
}
