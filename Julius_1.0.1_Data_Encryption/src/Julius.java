public class Julius
{
	public void GUI()
	{
		new SplashScreen();
		new MyFrame();
		
	}
	
	
	public void SelectFiles()
	{
		System.out.print("Function run");
	}
	
	public static void main(String args[])
	{
		
		Julius obj = new Julius();
		obj.GUI();
	}

}
