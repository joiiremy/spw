package f2.spw;

public class ItemLife extends Item{
	public ItemLife(int x, int y){
		super(x,y);
		
		setImage("f2/image/life.png");
	}
	public void collectItem(GameEngine g){
		g.shipLifepoint.lp++;
	}
}
