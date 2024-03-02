package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import metier.SingletonConnection;
import metier.entities.Instrument;

public class InstrumentDaoImpl implements IInstrumentDao {
	@Override
	public Instrument save(Instrument i) {
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO INSTRUMENTS (NOM_INSTRUMENT,PRIX) VALUES(?,?)");
			ps.setString(1, i.getNomInstrument());
			ps.setDouble(2, i.getPrix());
			ps.executeUpdate();
			PreparedStatement ps2 = conn.prepareStatement("SELECT MAX(ID_INSTRUMENT) as MAX_ID FROM INSTRUMENTS");
			ResultSet rs = ps2.executeQuery();
			if (rs.next()) {
				i.setIdInstrument(rs.getLong("MAX_ID"));
			}
			ps.close();
			ps2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public List<Instrument> instrumentsParMC(String mc) {
		List<Instrument> instrs = new ArrayList<Instrument>();
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from INSTRUMENTS where NOM_INSTRUMENT LIKE ?");
			ps.setString(1, "%" + mc + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Instrument i = new Instrument();
				i.setIdInstrument(rs.getLong("ID_INSTRUMENT"));
				i.setNomInstrument(rs.getString("NOM_INSTRUMENT"));
				i.setPrix(rs.getDouble("PRIX"));
				instrs.add(i);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return instrs;
	}

	@Override
	public Instrument getInstrument(Long id) {
	 
	 Connection conn=SingletonConnection.getConnection();
	 Instrument i = new Instrument();
	 try {
	PreparedStatement ps= conn.prepareStatement("select * from INSTRUMENTS where ID_INSTRUMENT = ?");
	ps.setLong(1, id);
	ResultSet rs = ps.executeQuery();
	if (rs.next()) {
	i.setIdInstrument(rs.getLong("ID_INSTRUMENT"));
	i.setNomInstrument(rs.getString("NOM_INSTRUMENT"));
	i.setPrix(rs.getDouble("PRIX"));
	}
	} catch (SQLException e) {
	e.printStackTrace();
	}
	return i;
	}

	@Override
	public Instrument updateInstrument(Instrument i) {
	Connection conn=SingletonConnection.getConnection();
	 try {
	PreparedStatement ps= conn.prepareStatement("UPDATE INSTRUMENTS SET NOM_INSTRUMENT=?,PRIX=? WHERE ID_INSTRUMENT=?");
	ps.setString(1, i.getNomInstrument());
	ps.setDouble(2, i.getPrix());
	ps.setLong(3, i.getIdInstrument());
	ps.executeUpdate();
	ps.close();
	} catch (SQLException e) {
	e.printStackTrace();
	}
	return i;
	}

	@Override
	public void deleteInstrument(Long id) {
	Connection conn=SingletonConnection.getConnection();
	 try {
	PreparedStatement ps= conn.prepareStatement("DELETE FROM INSTRUMENTS WHERE ID_INSTRUMENT = ?");
	ps.setLong(1, id);
	ps.executeUpdate();
	ps.close();
	} catch (SQLException e) {
	e.printStackTrace();
	}
	}}
	
