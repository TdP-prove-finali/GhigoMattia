package it.polito.tdp.provaFinale.db;

import java.sql.Connection;
import java.util.List;

import it.polito.tdp.provaFinale.model.Albergo;
import it.polito.tdp.provaFinale.model.Altro;
import it.polito.tdp.provaFinale.model.Chiesa;
import it.polito.tdp.provaFinale.model.Museo;
import it.polito.tdp.provaFinale.model.Teatro;
import it.polito.tdp.provaFinale.model.Toretto;

public class TestDAO {

	public static void main(String[] args) {

		try {
			Connection connection = DBConnect.getConnection();
			connection.close();
			System.out.println("Connection Test PASSED");
			
			provaFinaleDAO dao = new provaFinaleDAO() ;
			
			List<Albergo> alberghi = dao.readAlberghi();
			alberghi.sort(null);
			for(Albergo a : alberghi) {
				System.out.println(a+"\n");
			}
			System.out.println(alberghi.size());
			
			List<Chiesa> chiese = dao.readChiese();
			for(Chiesa c : chiese) {
				System.out.println(c+"\n");
			}
			System.out.println(chiese.size());
			
			List<Altro> altri = dao.readAltriLuoghi();
			for(Altro a : altri) {
				System.out.println(a+"\n");
			}
			System.out.println(altri.size());
			
			List<Museo> musei = dao.readMusei();
			for(Museo m : musei) {
				System.out.println(m+"\n");
			}
			System.out.println(musei.size());
			
			List<Teatro> teatri = dao.readTeatri();
			for(Teatro t : teatri) {
				System.out.println(t+"\n");
			}
			System.out.println(teatri.size());
			
			List<Toretto> toretti = dao.readToretti();
			for(Toretto t : toretti) {
				System.out.println(t);
			}
			System.out.println(toretti.size());

		} catch (Exception e) {
			throw new RuntimeException("Test FAILED", e);
		}
	}

}
