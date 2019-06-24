package model;

import java.util.ArrayList;

public class Page {
    private String title;
    private String url;
    private ArrayList<Page> subPages;
//    private ArrayList<Role> rolesWithAcces = new ArrayList<Role>();


    public Page(String title, String url) {
        this.title = title;
        this.url = url;
        this.subPages = new ArrayList<Page>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ArrayList<Page> getSubPages() {
        return subPages;
    }

    public void setSubPages(ArrayList<Page> subPages) {
        this.subPages = subPages;
    }

    public void addSubPage(Page page) {
        this.subPages.add(page);
    }
}
