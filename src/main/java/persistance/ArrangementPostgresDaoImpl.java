package persistance;

import java.util.ArrayList;

import model.Arrangement;

public class ArrangementPostgresDaoImpl extends PostgresBaseDao implements ArrangementDao{

	@Override
	public ArrayList<Arrangement> findAllArrangements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Arrangement> findByTeacher(int teacherUsername) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Arrangement findById(int ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveArrangement(Arrangement arrangement) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateArrangement(Arrangement arrangement) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteArrangement(int ID) {
		// TODO Auto-generated method stub
		return false;
	}
}
