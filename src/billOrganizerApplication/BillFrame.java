package billOrganizerApplication;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.GregorianCalendar;
import java.util.HashMap;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BillFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BillOrganizerFrame parent;
	private Container container;
	private JPanel datePanel;
	private JPanel centerPanel;
	private JPanel southPanel;
	private HashMap<Integer, BillTypeEnum> typeMap;
	private JComboBox<String> comboDay;
	private JComboBox<String> comboMonth;
	private JComboBox<String> comboYear;
	private JCheckBox[] checkBox = new JCheckBox[7];
	private ButtonGroup topGroup;
	private JLabel date;
	private JLabel amount;
	private JLabel instruct;
	private JLabel vendor;
	private JTextField vendorName;
	private JTextField amountDue;
	private JButton submit;
	private JButton cancel;
	private int indexSelected;

	public BillFrame(BillOrganizerFrame parent) {
		setTitle("Add a Bill");
		setSize(450, 400);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.parent = parent;
		this.container = getContentPane();
		this.vendor = new JLabel("Vendor Name: ");
		this.vendorName = new JTextField();
		this.amount = new JLabel("Amount Due: ");
		this.amountDue = new JTextField();
		this.date = new JLabel("Date Due: ");
		this.southPanel = new JPanel();
		this.centerPanel = new JPanel();
		this.datePanel = new JPanel();
		this.topGroup = new ButtonGroup();
		String[] days = new String[32];
		days[0] = "Day";
		for (int i = 1; i < days.length; i++) {
			days[i] = String.valueOf(i);
		}
		String[] months = { "Month", "January", "February", "March", "April",
				"May", "June", "July", "August", "September", "October",
				"November", "December" };

		String[] years = new String[11];
		years[0] = "Years";
		// 2015 is no longer an option for new bills
		for (int i = 1; i < years.length; i++) {
			years[i] = String.valueOf(2015 + i);
		}
		this.comboDay = new JComboBox<String>(days);
		this.comboMonth = new JComboBox<String>(months);
		this.comboYear = new JComboBox<String>(years);
		this.instruct = new JLabel("Please check bill Type:");
		this.typeMap = new HashMap<Integer, BillTypeEnum>();
		int index = 0;
		for (BillTypeEnum type : BillTypeEnum.values()) {
			this.checkBox[index] = new JCheckBox(type.toString(), false);
			typeMap.put(index, type);
			index++;
		}
		this.cancel = new JButton("Cancel");
		this.submit = new JButton("Submit");
		this.indexSelected = -1;
		setComponents();
		addComponents();
	}

	private void setComponents() {
		this.datePanel.setLayout(new FlowLayout());
		this.centerPanel.setLayout(new GridLayout(7, 2));
		this.southPanel.setLayout(new FlowLayout());
		this.container.setLayout(new BorderLayout());

	}

	private void addComponents() {
		this.datePanel.add(this.comboMonth);
		this.datePanel.add(this.comboDay);
		this.datePanel.add(this.comboYear);
		this.centerPanel.add(this.vendor);
		this.centerPanel.add(this.vendorName);
		this.centerPanel.add(this.amount);
		this.centerPanel.add(this.amountDue);
		this.centerPanel.add(this.date);
		this.centerPanel.add(this.datePanel);
		this.centerPanel.add(this.instruct);
		this.southPanel.add(this.submit);
		this.southPanel.add(this.cancel);
		this.container.add(this.southPanel, BorderLayout.SOUTH);
		this.container.add(this.centerPanel, BorderLayout.CENTER);
		for (int i = 0; i < this.checkBox.length; i++) {
			this.topGroup.add(this.checkBox[i]);
			final int index = i;
			this.centerPanel.add(this.checkBox[i]);
			this.checkBox[i].addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					indexSelected = index;
				}

			});
		}
		this.cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				dispose();
			}
		});
		this.submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int day = comboDay.getSelectedIndex();
				int month = comboMonth.getSelectedIndex();
				int year = comboYear.getSelectedIndex();
				if (vendorName.getText().length() == 0
						|| amountDue.getText().length() == 0
						|| indexSelected == -1 || day == 0 || month == 0
						|| year == 0) {
					JOptionPane.showMessageDialog(null,
							"You have invalid fields!");
				} else {
					String name = vendorName.getText();
					try {
						Double amountMoney = Double.parseDouble(amountDue
								.getText());
						GregorianCalendar cal = new GregorianCalendar(Integer
								.parseInt(comboYear.getSelectedItem()
										.toString()), month - 1, day);
						BillTypeEnum billType = typeMap.get(indexSelected);
						Bill bill = new Bill(name, amountMoney, cal, billType);
						try {
							parent.ReDisplayList(bill);
						} catch (DuplicateDataException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						setVisible(false);
						dispose();
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null,
								"You have invalid fields!");
					}

				}

			}
		});
	}
}
