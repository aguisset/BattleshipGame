package grid;

public class Cell extends GridElements{
	private Ship ship;
	private boolean shot;
	protected boolean token;
	private int x;
	private int y;
	
	/* Initialization : create an empty cell without ship and not shot*/
/*	public Cell(){
		this.ship = null;
		this.shot = false;
		this.token = false;
	}
*/	
	public Ship getShip(){
		return this.ship;
	}

	public boolean getToken(){
		return token;
	}
	
	/*set a ship on an empty cell*/
	public void setShip(Ship ship){
		this.ship = ship;
	}
	
	public void shot(){
		if (this.ship!=null){
			this.shot = true;	
		}
	}
	
	@Override
	public String svg() {
		return String.format("<cell cx='%d' cy='%d'/>", x, y);
	}

	@Override
	public String serialisation() {
		return String.format("Cell (%d,%d)", x, y);
	}
	
}
