package grid;
import grid.Ship;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Player {
	private String nickname;
	
	public Player(String nickname){
		this.nickname = nickname;
	}
	
	public Player(){
		this.nickname = "Default";
	}
	
	public int fire(int x, int y){
		
		return 0;
	}
	
	/* TODO : à finir - fonction de sauvegarde 
	public void debug (Writer w) {
		try {
			
			w.append(String.format("Repere %s\n", titre));
			w.append(String.format("%s\n", x.serialisation()));
			w.append(String.format("%s\n", y.serialisation()));
			for (GridElements g: cells)
				w.append(String.format("%s\n", e.serialisation()));
			w.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	} */
	
		
}
