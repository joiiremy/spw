package f2.spw;


public class ItemClearMap extends Item{
	public ItemClearMap(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		super.setImage("f2/image/itemclearmap.png");
	}
//	public void draw(Graphics2D g) {
//		g.drawImage(this.img, x, y, 20, 20, null);	
//	}
	
	public void collectItem(GameEngine g){
		g.clearMap();
	}
}
