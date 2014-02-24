package kitchen;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;


public class View {
	
	private Model model;
	private Controler controler;
	private JFrame frame;
	private JPanel mainPanel;
	private JButton preparedButton;
	private JButton receivedButton;
	private JList list;
	private KitchenCellRenderer kitchenCellRenderer;
	
	public View(Model model, Controler controler)
	{
		this.model = model;
		this.controler = controler;
		
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screensize = kit.getScreenSize();
		
		int width = screensize.width;
		int height = screensize.height;
		
		
			createUI();
		
	        
			mainPanel.setOpaque(true); //content panes must be opaque
			
	        frame.setContentPane(mainPanel);
	        
			frame.pack();
			
			frame.setSize(width/2+200, height/2+200);
			//frame.setLocation(width/2 - frame.getSize().width / 2, height/2 - frame.getSize().height / 2);
			
			frame.setVisible(true);
	}
	
	private void createUI()
	{
		 mainPanel = new JPanel();
		 mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
	     mainPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
	     
	     //mainPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
	        //name.setAlignmentX(JComponent.CENTER_ALIGNMENT);
	        //button.setAlignmentX(JComponent.CENTER_ALIGNMENT);
	    
	     JPanel panel = new JPanel();
	     panel.setAlignmentX(Component.CENTER_ALIGNMENT);
		 panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		 panel.add(createOrdersPane());
		 panel.add(Box.createRigidArea(new Dimension(0, 5)));
		 panel.add(createButtonsPane());
		 
		 mainPanel.add(panel);

    }
	
	private JPanel createButtonsPane()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setAlignmentY(Component.TOP_ALIGNMENT);
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		
		preparedButton = new JButton("prepared");
		//preparedButton.setEnabled(false);
		preparedButton.setEnabled(true);
		preparedButton.setAlignmentY(Component.TOP_ALIGNMENT);
		preparedButton.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		Controler.PreparedAction preparedAction = controler.new PreparedAction();
		preparedButton.addActionListener(preparedAction);
		
		receivedButton = new JButton("received");
		//receivedButton.setEnabled(false);
		receivedButton.setEnabled(true);
		receivedButton.setAlignmentY(Component.TOP_ALIGNMENT);
		receivedButton.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		Controler.ReceivedAction receivedAction = controler.new ReceivedAction();
		receivedButton.addActionListener(receivedAction);
		
		panel.add(preparedButton);
		panel.add(Box.createRigidArea(new Dimension(5, 0)));
		panel.add(receivedButton);
		
		return panel;
	}
	
	private JScrollPane createOrdersPane()
	{
		String table[][];
		table = new String[2][6];
		table[0][0] = "adupaas" + '\n' +  "asfdsf";
		table[0][1] = "b";
		table[0][2] = "c";
		table[0][3] = "d";
		table[0][4] = "e";
		table[0][5] = "f";

		
		table[1][0] = "g";
		table[1][1] = "h";
		table[1][2] = "i";
		table[1][3] = "j";
		table[1][4] = "k";
		table[1][5] = "l";
		

		list = new JList();
		list.setModel(model.getOrdersListModel());
		kitchenCellRenderer = new KitchenCellRenderer(3);
		list.setCellRenderer(kitchenCellRenderer);
		list.setBorder(BorderFactory.createLineBorder(Color.black));
		
		//Controler.ItemSelect selectAction = controler.new ItemSelect();
		//list.addListSelectionListener(selectAction);
		
		JScrollPane scrollPane = new JScrollPane(list);  
		scrollPane.setPreferredSize(new Dimension(700, 400));
		scrollPane.setMinimumSize(new Dimension(700, 400));
		scrollPane.setMaximumSize(new Dimension(700, 400));
	    scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);		
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setViewportView(list);
		
		return scrollPane;
	}
	
	
	public void setPreparedButton(boolean b)
	{
		preparedButton.setEnabled(b);
	}
	
	public void setReceivedButton(boolean b)
	{
		receivedButton.setEnabled(b);
	}
	
	public int getPreparingOrderSelection()
	{
		return list.getSelectedIndex();
	}
	
	public void setPreparingOrderSelection(int index)
	{
		kitchenCellRenderer.setBackground(index);
		
		
		list.clearSelection();
	}
	
	public int getPreparingCols()
	{
		return kitchenCellRenderer.getCols();
	}
	
	public void removePreparingOrderSelection(int index)
	{
		kitchenCellRenderer.removeSpecial(index);
	}
}
