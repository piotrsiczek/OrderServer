/**
 * 
 */
package kitchen;



/**
 * @author Piotrek
 *
 */
public class Kitchen {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Model model = new Model();

		Controler controler = new Controler(model);
	
		
		View orderView = new View(model, controler);		
		controler.setView(orderView);
		
		
		/*
		for (int i=0; i<1000; i++)
		{
			controler.test(i);
			try {
				Thread.sleep(2500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		


		
		*/
		
		//System.out.println("not comming...");
		
	}

}
