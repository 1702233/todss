package persistance;

import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MenuPostgresDaoImpl extends PostgresBaseDao implements MenuDao {

    public Menu getMenu(String role) {
        ArrayList<Page> pages = new ArrayList<Page>();

        Page dashboard = new Page("Dashboard", "dashboard.html");
        pages.add(dashboard);

        Page arrangementen = new Page("Arrangementen", null);
        arrangementen.addSubPage(new Page("Alle arrangementen", "allarrangements.html"));
        arrangementen.addSubPage(new Page("Nieuw arrangement", "newarrangement.html"));
        pages.add(arrangementen);

        Page minigames = new Page("Minigames", null);
        minigames.addSubPage(new Page("Nieuwe minigame", "newminigame.html"));
        pages.add(minigames);

        Page sessies = new Page("Sessies", null);
        sessies.addSubPage(new Page("Alle sessies", "allsessions.html"));
        sessies.addSubPage(new Page("Nieuwe sessie", "newsession.html"));
        pages.add(sessies);

        Page kaartensets = new Page("Kaartensets", null);
        kaartensets.addSubPage(new Page("Alle kaartensets", "allcardsets.html"));
        kaartensets.addSubPage(new Page("Nieuwe kaartenset", "newcardset.html"));
        pages.add(kaartensets);

        if (role.equals("admin")) {
            Page addAccount = new Page("Account maken", "newaccount.html");
            pages.add(addAccount);
        }

        return new Menu(pages);
    }

}
