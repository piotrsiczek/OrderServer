/**
 * 
 */
package kitchen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * @author Piotrek
 *
 */
public class Controler implements Runnable {
	
	private Model model;
	private View view;
	//private String name;
	private Thread t;
	
	private void getDataAction()
	{
		
		//wylaczenie odbierania danych
		t = new Thread(this);
		t.start();
		//^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	}
	
	public Controler(Model model)
	{
		this.model = model;
		getDataAction();            	
	}
	
	public void setView(View view)
	{
		this.view = view;
	}
	

	@Override
	public void run() 
	{
		int i = 0, size;
		String data = null;
		

			while (true)
			{
				System.out.println("Nowy watek" + i);
				i++;
				
				
				data = model.getData();
				size = model.refreshOrdersModel(data, view.getPreparingCols());
				
				if (size == 0)
				{					
					view.setPreparedButton(true);
					view.setReceivedButton(true);
				}
				//view.drawOrdersList();
				//model.getDataTest();
			}
		
	}
	
	public void test(int i)
	{
		System.out.println("main" + i);
	}
	
	
	public class ItemSelect implements ListSelectionListener
	{
		
		private JList list;
				
	        @Override
			public void valueChanged(ListSelectionEvent e) 
			{

					 if (!e.getValueIsAdjusting()) 
					 {
						 
						 int state = 0;
						 list = (JList) e.getSource();
 
							//id = Integer.parseInt(list.getName().substring(12));
	
							String value = (String)list.getSelectedValue();
									
							System.out.println("wartos:" + value);
							
							state = 1;//model.refreshCurrentOrder(values[0], Double.parseDouble(values[1]), id);
								            	     
						   if (state == 1)
						   {
								//view.setDeleteButton(true);
								//view.setDeleteAllButton(true);
								//view.setSetOrderButton(true);
								
						   }						   
						   //list.clearSelection();
					 }	 
	        }									
	}
	
	public class PreparedAction implements ActionListener 
	{
	        public void actionPerformed(ActionEvent e)
	        {
	        	int index = view.getPreparingOrderSelection();

	            if (index != -1)
	            {
	            	//wysylanie danych
	            	model.sendData(Integer.toString(index));
	            	view.setPreparingOrderSelection(index);
	            }       

	        }
	}
	
	public class ReceivedAction implements ActionListener 
	{
	        public void actionPerformed(ActionEvent e)
	        {
    
	        	int index = view.getPreparingOrderSelection();

	            if (index != -1)
	            {
	            	String data = "d" + index;
	            	
	            	model.removePreparedItem(index);
					view.removePreparingOrderSelection(index);
	            	model.sendData(data);
	            	//view.setPreparingOrderSelection(index);
	            } 
	            
	        }
	}

}
