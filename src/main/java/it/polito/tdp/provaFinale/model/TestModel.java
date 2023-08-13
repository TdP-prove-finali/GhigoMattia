package it.polito.tdp.provaFinale.model;

import java.util.*;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

public class TestModel {

	public static void main(String[] args) {
		Model m = new Model();
		List<Albergo> alberghi = new ArrayList<>(m.getAllAlberghi());
		System.out.println(alberghi.size());
		m.creaListaAlberghi(100000.0, 0, 1000000.0, false, false, false);
		Albergo a = m.getAlbergo(965);
		m.setAlbergo(a);
		System.out.println(a);
		m.creaGrafo();
//		m.creaItinerario(300.0, null, null, null);
//		List<Luogo> itinerario = m.getItinerarioMigliore();
//		for(int i=0;i<itinerario.size();i++) {
//			if(i==0) {
////				System.out.println(LatLngTool.distance(m.getAlbergo().getCoordinate(), itinerario.get(i).getCoordinate(), LengthUnit.KILOMETER)*60/4);
//				System.out.println(itinerario.get(i));
//
//			}
//			else {
//				System.out.println(LatLngTool.distance(itinerario.get(i-1).getCoordinate(), itinerario.get(i).getCoordinate(), LengthUnit.KILOMETER)*60/4);
//				System.out.println(itinerario.get(i));
//			}
//		}
//		System.out.println(LatLngTool.distance(itinerario.get(itinerario.size()-1).getCoordinate(), m.getAlbergo().getCoordinate(), LengthUnit.KILOMETER)*60/4);
//		System.out.println(m.getAlbergo()+"\n");
//		System.out.println(m.getDurata());
	}

}
