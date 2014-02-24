/**
 * 
 */
package kitchen;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.List;


import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

/**
 * @author Piotr Siczek
 *
 */
public class KitchenCellRenderer implements ListCellRenderer 
{ 
	 private JPanel displayPanel;
	 private JLabel[] labels;
	 private Color fgs; // Stores the foreground colours for each column.
	 private Color bgs; // Stores the background colours for each column.
	 private Color sfgs; // Stores the foreground colours for each column when selected.
	 private Color sbgs; // Stores the background colours for each column when selected.
	 private int size;
	 private JPanel mainPanel;
	 private int columns;
	 private List<Integer> selected;
	 private Color c, c2;


	 
	 public KitchenCellRenderer(int pairs) 
	 {
		 mainPanel = new JPanel();
		 mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));		  
		 mainPanel.setBorder(BorderFactory.createEmptyBorder(0,0,2,0));
		 displayPanel = new JPanel();
		 size=0;
		 this.columns = pairs*2;
		 labels = new JLabel[columns];
		 c = Color.green;
		 c2 = new Color(92, 231, 115);
	     
			 for (int i=0; i < columns; i++)
			 {
				 labels[i] = new JLabel();
			 }
			 
			 selected = new LinkedList<Integer>();
			 
			 //selected.get(1);
			 //selected.add(0, false);
			 //selected.add(1, false);
			 //selected.add(2, false);
	 }

	 public int length()
	 {
		return size;
	 }
	 
	 public int getCols()
	 {
		 return columns;
	 }
	 
	 public void setBackground(int index)
	 {		 
		 selected.add(index);
	 }
	 
	 public void removeSpecial(int index)
	 {
		 for (int i=0; i < selected.size(); i++)
		 {
			 if (selected.get(i) == index)
			 {
				 selected.remove(i);
				 return;
			 }
		 }
	 }
	 
	 public boolean checkSpecial(int index)
	 {
		 
		 for (int i=0; i < selected.size(); i++)
		 {
			 if (selected.get(i) == index) return true;
		 }
		 		 
		 return false;
	 }
	 	 
	 public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) 
	 {

		 String celldata = (String)value;
		 
		 displayPanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		 displayPanel.setOpaque(true);
		 System.out.println("indeks^^^^^^^^^^^^^" + index);
		 
			for (int i=0; i< columns; i++)
			{
				labels[i].setText("");
			}
	  
			String[] parts = celldata.split(";");
	  
			  for (int i=0; i < parts.length; i++)
			  {
				  labels[i].setText(parts[i]);		 
		 		  displayPanel.add(labels[i]);
			  }
		 
			  displayPanel.setLayout(new GridLayout(1,columns));
		 
  
			  if(isSelected)
			  {
				  if (checkSpecial(index) == true)
				  {
					  displayPanel.setBackground(c2);
					  displayPanel.setForeground(c2);
				  }
					  else 
					  {
						  displayPanel.setBackground(list.getSelectionBackground());
						  displayPanel.setForeground(list.getSelectionForeground()); 
					  }   
			  }
			  else 
			  {  	  
				  if (checkSpecial(index) == true)
				  {
					  displayPanel.setBackground(c);
					  displayPanel.setForeground(c);
				  }
					  else
					  {
						  displayPanel.setBackground(list.getBackground());
						  displayPanel.setForeground(list.getForeground());
					  }
			  }
	     

	  	 mainPanel.add(displayPanel);	
	     //mainPanel.setEnabled(list.isEnabled());
	     //mainPanel.setFont(list.getFont());
	     
	     return mainPanel;
	 }
	   }
