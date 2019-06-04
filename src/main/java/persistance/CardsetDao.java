package persistance;

import java.util.ArrayList;



import model.Cardset;

public interface CardsetDao {

	public ArrayList<Cardset> findAllCardsets();
	public ArrayList<Cardset> findByTeacher(String teacher);
	public Cardset findByID(int ID);
	public boolean saveCardset(Cardset cardset);
	public boolean updateCardset(Cardset cardset);
	public boolean deleteCardset(int ID);
}
