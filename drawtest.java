import arc.*;

public class drawtest{
	public static void main(String args[]){
		Console con = new Console("Drawing Test",1280,720);
		con.drawRect(480,560,100,150);
		con.drawRect(680,560,100,150);
		con.drawRect(580,10,100,150);
		con.sleep(2000);
		con.clear();
		con.println("Test");
	}
}
