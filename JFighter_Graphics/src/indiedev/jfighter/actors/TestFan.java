package indiedev.jfighter.actors;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class TestFan {
	
	int x=400,y=200,dx=200,dy;
	float theta=0f;
	int hitcount=0;
	
	Rectangle rect=new Rectangle();
	
	public void draw(Graphics2D g2d,Rectangle rect1)
	{
		g2d.setColor(Color.WHITE);
		setPolarRot(theta++,dx);
		rect.setBounds(new Rectangle(x++,y,dx,dy));
		if(rect.intersects(rect1))
		{
			g2d.setColor(Color.BLUE);
		}
		else
		{
			
		}
		g2d.fillRect(rect.x, rect.y, rect.width, rect.height);
		//System.out.println(rect.x+","+ rect.y+","+ rect.width+","+ rect.height);
		if(theta>360)
		{
			theta=0;
		}
		
		if(x>800)
		{
			x=0;
		}
		

	}
	
	public void setPolarRot(float angle,float radius)
	{
		//convert angle to radian
		float rad=(float)Math.toRadians(angle);
		
		
		//dx=(int) Math.abs((Math.cos(rad)*radius));
		dy=(int) Math.abs((Math.sin(rad)*radius));
	}

}
