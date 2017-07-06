package fr.enseirb.battleship;
import fr.enseirb.battleship.play.*;
import fr.enseirb.battleship.*;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import fr.enseirb.battleship.play.Players;
import fr.enseirb.battleship.ships.AircraftCarrier;
import fr.enseirb.battleship.ships.Battleship;
import fr.enseirb.battleship.ships.Destroyer;
import fr.enseirb.battleship.ships.PatrolBoat;
import fr.enseirb.battleship.ships.Submarine;

public class TestView{

	public static void main(String[] args) throws ShipOutOfBoundsException, ShipOverlapException {
		
		Grid r = new Grid();
		Ia M = new Ia(r);
		Human H = new Human(r);
		
		ReadGridFile xml = new ReadGridFile();
		SaveFile file = new SaveFile();
		
		
		try {
			r.svgMaker(new FileWriter("debug.svg"), r);
			
			M.fire(3, 2);
			M.fire(3, 2);
			M.fire(0, 2);
			M.fire(0, 3);
			H.fire(2, 0);
			H.fire(2, 0);
			H.fire(6, 7);
			file.view(new FileWriter("view.svg"), r);
		} catch (IOException e) {
		    e.printStackTrace();
		} catch (InvalidGridException e) {
			System.out.println("Not good size ");
		}
		
	}
}

