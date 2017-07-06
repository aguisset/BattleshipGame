/*ARNEAU Megan - BONOTTE Benjamin - GUISSET Abdoul*/


package fr.enseirb.battleship.play;

import fr.enseirb.battleship.Grid;
import fr.enseirb.battleship.InvalidGridException;
import fr.enseirb.battleship.ReadShipsFile;
import fr.enseirb.battleship.ShipOverlapException;
import fr.enseirb.battleship.Ships;
import fr.enseirb.battleship.play.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SaveFile {
	protected int xMax;
	protected int yMax;
		
	
	// The last line of the file is "</svg>". If we want to write in again, we need to delete this last line.
	public String delete(int nbLine){
		String line;
		String newLine = "";
		int nbLinesRead = 0;
		String file = "debug.svg";
		
		try {
			InputStream ips = new FileInputStream(file); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			
			// While end of file
			while ((line=br.readLine())!=null){
				nbLinesRead ++;
				if (nbLinesRead != nbLine){
					newLine += line + "\n";
				}
			}
		}
		catch (Exception e){
			System.out.println(e.toString());
		}	
		
		return newLine;
	}
	
	public void debug(Players h, Grid g){
		String newLine = "";
		String file = "debug.svg";
		String save = "save.svg";
		String line;
		int nbLines = 0;
		
		xMax = g.getWidth();
		yMax = g.getHeight();
		
		
		try{
			// Read text file
			InputStream ips = new FileInputStream(file); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			
			// While end of file
			while ((line=br.readLine())!=null){
				nbLines ++;
			}
			
			newLine = delete(nbLines);
			
			// Close reading file
			br.close(); 
			
			// creation of saving file
			FileWriter fw = new FileWriter (save);
			BufferedWriter bw = new BufferedWriter (fw);
			PrintWriter outFile = new PrintWriter (bw); 
			
			String value[][] = h.getGridShot();
			
			for (int i=0; i<2*xMax; i++){
				for (int j=0; j<yMax; j++){
					if (value[i][j].equals("nothing")){
						
					}
					else{
						// show on the grid where we already shoot
						String lineValue = String.format("<text id='cellType' x = '%d' y = '%d'>%s</text>",i*100, j*100, value[i][j]);
						newLine += lineValue + "\n";
					}
				}
			}
			
			newLine += "</svg>";
			
			outFile.println(newLine);

			// Close out file
			outFile.close();
			
			System.out.println("Saving file " + save + " created.");
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}	
	}


	public void view (Writer w, Grid grid) throws InvalidGridException {
	
		xMax = grid.getWidth();
		yMax = grid.getHeight();
		
		grid.setGridElements();
		
		Set<Ships> elements = new HashSet<Ships>();
		
		ReadShipsFile xml = new ReadShipsFile();
		
		elements = grid.getElements();
			
    	// Total grid
    	try{
    		w.append("<?xml version='1.0' encoding='utf-8'?>\n");
    		w.append(String.format("<svg xmlns='http://www.w3.org/2000/svg' version='1.1' width='%d' height='%d' style='fill:#0000ff fill-opacity:0.75;stroke:#000000; stroke-width:5px' id='rect1353'>\n", 2000, 1000));
            
    		int nbCellWidth = grid.getWidth();
            int nbCellHeight = grid.getHeight();
    		
    		// Player grid
            grid.gridMaker(w, 200*nbCellWidth, 100*nbCellHeight, 2*nbCellWidth, 2*nbCellHeight);
            
            try {

            	Iterator<Ships> it = elements.iterator();
	            	
	            while (it.hasNext()) {

					Ships element = it.next() ; 
					
					// Random x y
					int x1 = element.getX();
					int y1 = element.getY();
					x1 = x1-nbCellWidth*100;
					
					// give orientation
					String orientation = element.isOrientation();
					
					// add svg element 
					String appen = element.svg(x1, y1, orientation);
					
					// add the boat name
					String id = element.getId();
					String writeName = element.name(x1, y1, id);
					
					// add to the file
					w.append(String.format(appen));
					w.append(String.format(writeName));

		         }
            }
            catch (Exception e) {
            	e.printStackTrace();
            }
            
			String value[][] = grid.getGridShot();
			
			for (int i=0; i<2*xMax; i++){
				for (int j=0; j<yMax; j++){
					
					if (value[i][j].equals("nothing")){
						
					}
					else{
						// show on the grid where we already shoot
						String lineValue = String.format("<text id='cellType' x = '%d' y = '%d'>%s</text>",i*100, j*100, value[i][j]);
						w.append(lineValue);
					}
				}
			}
			
            w.append("</svg>");
            w.close();
			System.out.println("Saving file view.svg created.");
    	}
    	catch (IOException e) {
            e.printStackTrace();
    	}
	
	}
}