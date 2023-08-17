package it.polito.tdp.provaFinale.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.provaFinale.model.Albergo;
import it.polito.tdp.provaFinale.model.Altro;
import it.polito.tdp.provaFinale.model.Chiesa;
import it.polito.tdp.provaFinale.model.Museo;
import it.polito.tdp.provaFinale.model.Teatro;
import it.polito.tdp.provaFinale.model.Toretto;

public class provaFinaleDAO {
	
	private LatLng coordinateCentro = new LatLng(45.07121307478032, 7.685087280059961); //coordinate centro di Torino, nello specifico si riferiscono al centro di Piazza Castello

	public List<Albergo> readAlberghi() {

		final String sql = "SELECT a.ID, a.Denominazione, a.Indirizzo, a.Mezza_pensione_alta_stagione, a.Stelle, a.Cap, a.Comune, a.Provincia, a.Latitudine, a.Longitudine, a.Bike_friendly, a.Lingue, a.Disabili, a.Animali_domestici "
				+ "FROM alberghi a";
		List<Albergo> alberghi = new ArrayList<Albergo>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				LatLng coord = new LatLng(rs.getDouble("a.Latitudine"), rs.getDouble("a.Longitudine"));
				Albergo a = new Albergo(rs.getInt("a.ID"), rs.getString("a.Denominazione"), rs.getString("a.Indirizzo"), rs.getDouble("a.Mezza_pensione_alta_stagione"), rs.getInt("a.Stelle"), rs.getInt("a.Cap"), rs.getString("a.Comune"), rs.getString("a.Provincia"), coord, rs.getInt("a.Bike_friendly"), rs.getString("a.Lingue"), rs.getInt("a.Disabili"), rs.getInt("a.Animali_domestici"), LatLngTool.distance(coordinateCentro, coord, LengthUnit.KILOMETER));
				alberghi.add(a);
			}

			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore di connessione al Database.");
		}

		return alberghi;
	}

	public List<Chiesa> readChiese() {

		final String sql = "SELECT c.Nome, c.Indirizzo, c.Latitudine, c.Longitudine, c.Durata "
				+ "FROM chiese c";
		List<Chiesa> chiese = new ArrayList<Chiesa>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Chiesa c = new Chiesa(rs.getString("c.Nome"), rs.getString("c.Indirizzo"), new LatLng(rs.getDouble("c.Latitudine"), rs.getDouble("c.Longitudine")), rs.getInt("c.Durata"));
				chiese.add(c);
			}

			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore di connessione al Database.");
		}

		return chiese;
	}
	
	public List<Altro> readLuoghi() {

		final String sql = "SELECT l.Nome, l.Tipo, l.Indirizzo, l.Latitudine, l.Longitudine, l.Durata "
				+ "FROM luoghi l";
		List<Altro> luoghi = new ArrayList<Altro>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Altro l = new Altro(rs.getString("l.Nome"), rs.getString("l.Tipo"), rs.getString("l.Indirizzo"), new LatLng(rs.getDouble("l.Latitudine"), rs.getDouble("l.Longitudine")), rs.getInt("l.Durata"));
				luoghi.add(l);
			}

			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore di connessione al Database.");
		}

		return luoghi;
	}
	
	public List<Museo> readMusei() {

		final String sql = "SELECT m.Nome, m.Indirizzo, m.Latitudine, m.Longitudine, m.Durata "
				+ "FROM musei m";
		List<Museo> musei = new ArrayList<Museo>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Museo m = new Museo(rs.getString("m.Nome"), rs.getString("m.Indirizzo"), new LatLng(rs.getDouble("m.Latitudine"), rs.getDouble("m.Longitudine")), rs.getInt("m.Durata"));
				musei.add(m);
			}

			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore di connessione al Database.");
		}

		return musei;
	}
	
	public List<Teatro> readTeatri() {

		final String sql = "SELECT t.Nome, t.Indirizzo, t.Latitudine, t.Longitudine, t.Durata "
				+ "FROM teatri t";
		List<Teatro> teatri = new ArrayList<Teatro>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Teatro t = new Teatro(rs.getString("t.Nome"), rs.getString("t.Indirizzo"), new LatLng(rs.getDouble("t.Latitudine"), rs.getDouble("t.Longitudine")), rs.getInt("t.Durata"));
				teatri.add(t);
			}

			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore di connessione al Database.");
		}

		return teatri;
	}

	public List<Toretto> readToretti() {

		final String sql = "SELECT t.ID, t.Indirizzo, t.Latitudine, t.Longitudine, t.Durata "
				+ "FROM toretti t";
		List<Toretto> toretti = new ArrayList<Toretto>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Toretto t = new Toretto(rs.getInt("t.ID"), rs.getString("t.Indirizzo"), new LatLng(rs.getDouble("t.Latitudine"), rs.getDouble("t.Longitudine")), rs.getInt("t.Durata"));
				toretti.add(t);
			}

			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore di connessione al Database.");
		}

		return toretti;
	}
}
