package grid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Grid extends Cell{
	/*Grid dimension & ship position*/
	private int grid[][]; 
	private int shipPosition[][];
	
	/*Grid size*/
	private int x;
	private int y;
	
	/*Ship position*/
	private int xShip;
	private int yShip; 
	
	/*Ship number*/
	private int nbShip;
	private Set<Ship> ships;
	
	private int i;
	
	public Grid(int x, int y, int nbShip){
		super();
		this.grid = new int[x][y];
		this.shipPosition = new int[nbShip][2];
		this.ships = new HashSet<Ship>();
	}

	public Grid(){
		super();
		grid = new int[9][9];
		/*Default : three boats*/
		this.shipPosition = new int[3][2];
	}
	
	/*Init ship position in the grid*/
	private void initShipPosition(int nbShip, Cell cell){
		
		while(cell.getToken()==true){
			xShip = (int)(Math.random() * (x + 1));
			yShip = (int)(Math.random() * (y + 1));
		}
		
		shipPosition[nbShip][1] = xShip;
		shipPosition[nbShip][2] = yShip;
	}
	
	
	/*Access*/
	public int getXShipPosition(int nbShip){
		return shipPosition[nbShip][1];
	}

	public int getYShipPosition(int nbShip){
		return shipPosition[nbShip][2];
	}
	
	 
	/*Access to all ships*/
	Set<Ship> getShips() {
		return ships;
	}
	
	
	
}

