/**
 * 
 */
package kitchen;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.DefaultListModel;

/**
 * @author Piotrek
 *
 */
public class Model {
	
	private DefaultListModel ordersModel;


	
	public Model()
	{
		System.out.println("created");
		ordersModel = new DefaultListModel();
		
		//serverSocket = null;
		//out = null;
		//in = null;
		
		//ordersModel.addElement("<html>Colombian<br>silver tips<br>Colombian</html>;<html>1<br>1<br>1</html>;<html>golden tips<br>French_Roast<br>szarlotka</html>;<html>1<br>1<br>1</html>;<html>Espresso<br>sernik</html>;<html>1<br>1</html>;");
		//ordersModel.addElement("<html>Colombian<br>silver tips<br>Colombian</html>;<html>1<br>1<br>1</html>;<html>golden tips<br>French_Roast<br>szarlotka</html>;<html>1<br>1<br>1</html>;<html>Espresso<br>sernik</html>;<html>1<br>1</html>;");
		//ordersModel.addElement("<html>Colombian<br>silver tips<br>Colombian</html>;<html>1<br>1<br>1</html>;<html>golden tips<br>French_Roast<br>szarlotka</html>;<html>1<br>1<br>1</html>;<html>Espresso<br>sernik</html>;<html>1<br>1</html>;");
		//ordersModel.addElement("Espresso,2;silver tips,1;sernik,1;golden tips,1;szarlotka,1;");
	}
	
	public int refreshOrdersModel(String data, int colsNumber)
	{		
		int j = 0;
		 String result = "";
		 
		
		 String[] cols = new String[colsNumber];
		 String[] ingredients = data.split(";");
   	 
   	 
		 for (int i = 0; i < colsNumber; i++)
		 {
			 cols[i] = "<html>";
		 }
   		 
		
   	 
		for (int i=0; i < ingredients.length; i++)
		{
			String[] caption = ingredients[i].split(",");

			cols[j%colsNumber] += caption[0] + "<br>";
			cols[(j+1)%colsNumber] += caption[1] + "<br>";
			
			j+=2;			
		}

		for (int i = 0; i < colsNumber; i++)
	   	{
			if (cols[i].length() > 6)
	   		 cols[i] = cols[i].substring(0, cols[i].length()-4);
	   		 cols[i] += "</html>";
	   		 
	   		 result += cols[i] + ";";
	   	}

		ordersModel.addElement(result);
		
		return ordersModel.size()-1;
	}
	
	
	public String getData()
	{
        String order = "";
    	ServerSocket serverSocket = null;
    	OutputStream out = null;
    	InputStream in = null;
		
        try {
        	
            serverSocket = new ServerSocket(4444);
                
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4444.");
            System.exit(1);
        }
 
        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
            
            in = clientSocket.getInputStream();
            out =  clientSocket.getOutputStream();
            
            
            int c;
            
            while ((c = in.read()) != -1)
            {       	
            	order += (char)c;
            }
                        
            serverSocket.close();
            clientSocket.close();
                       
            
                       
            
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        
         return order;
	}
	
	public DefaultListModel getOrdersListModel()
	{
		return ordersModel;
	}
	
	public void sendData(String msg)
	{
        System.out.println("wysylam zaznaczenie...");
		
		Socket socket = null;
        OutputStream out = null;
 
        try 
        {
            socket = new Socket("localhost", 4445);
                      
	            out = socket.getOutputStream();
	                        
		            
		            out.write(msg.getBytes());

            socket.close();
            
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("No message received");
            System.exit(1);
        }
	}
	
	
	public void getDataTest()
	{
		for (int i=0; i < 10; i ++)
		{
			System.out.println("Dostalem dane" + i);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
				
	}
	
	public void removePreparedItem(int index)
	{
		ordersModel.remove(index);	
	}

}
