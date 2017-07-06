package grid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




public abstract class Ship {
	
	protected String name;
	protected String registration;
	protected int x;
	protected int y;
	protected boolean orientation;

	
	public Ship(String name, String registration, int x, int y, boolean orientation){
		this.name = name;
		this.registration = registration;
		this.x = x;
		this.y = y;
		this.orientation = orientation;
	}
	

	public abstract int getNbBox();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegistration() {
		return registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isOrientation() {
		return orientation;
	}

	public void setOrientation(boolean orientation) {
		this.orientation = orientation;
	}

	public abstract String svg();

	public abstract String serialisation();
	
	public abstract void initShipPosition(Writer w);

	
}
