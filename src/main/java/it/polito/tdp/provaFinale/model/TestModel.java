package it.polito.tdp.provaFinale.model;

import java.util.*;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

public class TestModel {

	public static void main(String[] args) {
		
		Model m = new Model();
		
//		List<Albergo> alberghi = new ArrayList<>(m.getAllAlberghi());
//		System.out.println(alberghi.size());
		
		m.creaListaAlberghi(100000.0, 0, 1000000.0, false, false, false);
		Albergo a = m.getAlbergo(523);
		m.setAlbergo(a);
//		System.out.println(a);
		
		
//		m.creaListaParchi();
//		List<Altro> parchi = m.getParchi();
//		for(Altro p : parchi) {
//			System.out.println(p);
//		}
		
//		m.setToret();
//		Toretto t = m.getToret();
//		System.out.print(t+"\n");
		
//		m.creaListaLocali();
//		List<Altro> locali = m.getLocali();
//		for(Altro l : locali) {
//			System.out.println(l);
//		}
		
//		m.creaGrafo();
		
		long tic = System.currentTimeMillis();
		m.creaItinerario(120.0, 1, 1, 2);
		long toc = System.currentTimeMillis();
		List<Luogo> itinerario = m.getItinerarioMigliore();
		for(int i=0;i<itinerario.size();i++) {
			if(i==0) {
				System.out.println("\n"+itinerario.get(i));

			}
			else {
				System.out.println("Spostamento: "+LatLngTool.distance(itinerario.get(i-1).getCoordinate(), itinerario.get(i).getCoordinate(), LengthUnit.KILOMETER)*60/4);
				System.out.println(itinerario.get(i));
			}
		}
		System.out.println("\nPosti visitabili: "+(itinerario.size()-2));
		System.out.println("\nDurata complessiva: "+m.getDurata());
		System.out.println("\n"+((toc-tic)/1000)+" secondi");
	}

}
