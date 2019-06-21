package model.services;

import java.util.List;

import model.Cardset;

import persistance.CardsetDao;
import persistance.CardsetPostgresDaoImpl;

public class CardSetService {
    private CardsetDao dao = new CardsetPostgresDaoImpl();

    public List<Cardset> findByTeacher(String teacher) {
        return dao.findByTeacher(teacher);
    }

    public List<Cardset> findAllCardsets() {
        System.out.println("findallcardsetsservice");
        return dao.findAllCardsets();
    }

    public boolean deleteCardsetById(int id) {
        return dao.deleteCardset(id);
    }

}
