package model;

import java.util.ArrayList;

public class Menu {
    private ArrayList<Page> allPages = new ArrayList<Page>();

    public Menu(ArrayList<Page> allPages) {
        this.allPages = allPages;
    }

    public ArrayList<Page> getAllPages() {
        return allPages;
    }

    public void setAllPages(ArrayList<Page> allPages) {
        this.allPages = allPages;
    }

    public void addPage(Page page) {
        this.allPages.add(page);
    }
}
