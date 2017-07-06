/*ARNEAU Megan - BONOTTE Benjamin - GUISSET Abdoul*/


package fr.enseirb.battleship.App;
import fr.enseirb.battleship.play.*;

import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import fr.enseirb.battleship.*;
import fr.enseirb.battleship.play.Players;
import fr.enseirb.battleship.ships.AircraftCarrier;
import fr.enseirb.battleship.ships.Battleship;
import fr.enseirb.battleship.ships.Destroyer;
import fr.enseirb.battleship.ships.PatrolBoat;
import fr.enseirb.battleship.ships.Submarine;

public class Main  {

	public static void main(String[] args) throws ShipOutOfBoundsException, ShipOverlapException {
		
		boolean win = false;
		String value;
		
		Grid r = new Grid();
		Ia Artif = new Ia(r);
		Human Megan = new Human(r);
		
		ReadGridFile xml = new ReadGridFile();
		SaveFile file = new SaveFile();
		
		Scanner sc = new Scanner(System.in);
		
		int x1 = 0;
		int y1 = 0;
		int xMemory = 0;
		int yMemory = 0;
		
		int height = r.getHeight();
		int width = r.getWidth();
		
		Random generator = new Random();
		
		// Decide who begin the game.
		int firstPlayer = generator.nextInt(2);
		if (firstPlayer==0){
			System.out.println("IA begins the game.\n");
			x1 = generator.nextInt(width);
			y1 = generator.nextInt(height);
			Artif.fire(x1, y1);
			
			xMemory = x1;
			yMemory = y1;
		}
		else{
			System.out.println("You begin the game.\n");
		}
		
		try {
			r.svgMaker(new FileWriter("debug.svg"), r);

			while(win==false){
	
				System.out.println("Entrez une commande : fire - debug - view - exit.\n");
				String str = sc.nextLine();	
				
				if (str.equals("fire")){
					System.out.println("Saisissez x.\n");
					int x = sc.nextInt();
					System.out.println("Saisissez y.\n");
					int y = sc.nextInt();
					Megan.fire(x, y);
					sc.nextLine();
					
					String fireType = r.getFiring();
					if (fireType.equals("random")){
						x1 = generator.nextInt(width);
						y1 = generator.nextInt(height);
						Artif.fire(x1, y1);
					}
					
					if (fireType.equals("pack")){
						x1 = generator.nextInt(width/2);
						y1 = generator.nextInt(height/2);
						Artif.fire(x1, y1);
					}
					
					if (fireType.equals("far")){
						// random = min + random(max-min)
						if(xMemory < width/2 && yMemory < height/2){
							x1 = width/2 + generator.nextInt(width-width/2);
							y1 = generator.nextInt(height/2);
							Artif.fire(x1, y1);
						}
						else if(xMemory > width/2 && yMemory < height/2){
							x1 = width/2 + generator.nextInt(width-width/2);
							y1 = height/2 + generator.nextInt(height-height/2);
							Artif.fire(x1, y1);
						}
						else if(xMemory < width/2 && yMemory > height/2)
						{								
							x1 = generator.nextInt(width/2);
							y1 = generator.nextInt(height/2);
							Artif.fire(x1, y1);
						}
						else{
							x1 = generator.nextInt(width/2);
							y1 = height/2+ generator.nextInt(height-height/2);
							Artif.fire(x1, y1);
						}
						
						xMemory = x1;
						yMemory  = y1;
					}
					
					if (fireType.equals("detection")){

						if(xMemory < width/2 && yMemory < height/2){
							x1 = generator.nextInt(width/2);
							y1 = generator.nextInt(height/2);

							value = Artif.fire(x1, y1);
						}
						else if(xMemory > width/2 && yMemory < height/2){
							x1 = width/2 + generator.nextInt(width-width/2);
							y1 = generator.nextInt(height/2);
							
							value = Artif.fire(x1, y1);
						}
						else if(xMemory < width/2 && yMemory > height/2)
						{								
							x1 = generator.nextInt(width/2);
							y1 = height/2+ generator.nextInt(height-height/2);
							
							value = Artif.fire(x1, y1);
						}
						else{
							x1 = width/2 + generator.nextInt(width-width/2);
							y1 = height/2 + generator.nextInt(height-height/2);

							value = Artif.fire(x1, y1);
						}
						
						// if we touch a boat, we go on shooting in the same direction
						if (value.equals("Touch")){
							xMemory = x1;
							yMemory = y1;
						}
						// else, we shoot at random
						else{
							xMemory = generator.nextInt(width);;
							yMemory = generator.nextInt(height);;
						}
					}
				}
				
				else if (str.equals("debug")){
					file.debug(Artif, r);
				}

				else if (str.equals("view")){
					file.view(new FileWriter("view.svg"), r);
				}
				
				else if (str.equals("exit")){
					System.out.println("Vous avez arrêté le jeu.\n");
					System.exit(0);
				}

				else{
					String message = "Vous n'avez pas rentré de commande valide.\n";
					throw new CommandException(message);
				}
				
				win = Artif.win(r, 0);
				if (win==true){
					System.exit(0);
				}

				win = Megan.win(r, 10);
				}
		} 
		catch (Exception e) {
		    e.printStackTrace();
		} 
		
	}
}

