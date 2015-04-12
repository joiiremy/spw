package f2.spw;


public class ItemClearMap extends Item{
	public ItemClearMap(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		super.setImage("f2/image/itemclearmap.png");
	}	
	public void collectItem(GameEngine g){
		g.clearMap();
	}
}
