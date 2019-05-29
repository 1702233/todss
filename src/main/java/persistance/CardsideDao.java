package persistance;

import java.util.ArrayList;


import model.Cardside;

public interface CardsideDao {

	public ArrayList<Cardside> findAllCardsides();
	public Cardside findByID(int ID);
	public boolean saveCardside(Cardside cardside);
	public boolean updateCardside(Cardside cardside);
	public boolean deleteCardside(int ID);
}
