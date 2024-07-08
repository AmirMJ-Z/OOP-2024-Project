package model.Notebook;

import java.util.ArrayList;
import java.util.Comparator;

public class Notebook<T> {
    private ArrayList<Page> pages = new ArrayList<>();
    private int currentPage = 1;
    private int MAXIMUM_CAPACITY;
    private ArrayList<T> elements = new ArrayList<>();
    public Notebook(int MAXIMUM_CAPACITY) {
        this.MAXIMUM_CAPACITY = MAXIMUM_CAPACITY;
        addPage();
    }
    private void addPage() {
        pages.add(new Page<T>(MAXIMUM_CAPACITY));
    }

    public int getNumberOfPages() {
        return pages.size();
    }

    public void addElement(T element) {
        if (pages.getLast().getCapacity() == 0) {
            addPage();
        }

        pages.getLast().addElement(element);
        elements.add(element);
    }

    public Page getPage(int pageNum) {
        if (pageNum <= 0 || pageNum > getNumberOfPages()) {
            return getCurrentPage();
        }
        currentPage = pageNum;
        return pages.get(pageNum-1);
    }
    public String toString(int pageNum) {
        return pages.get(pageNum-1).toString();
    }

    public String toString() {
        return pages.get(currentPage-1).toString();
    }

    public Page getCurrentPage() {
        return pages.get(currentPage-1);
    }

    public int getCurrentPageNumber() {
        return currentPage;
    }

    public void next() {
        if (currentPage+1 <= getNumberOfPages()) {
            currentPage += 1;
        }
    }

    public void previous() {
        if (currentPage-1 >= 0) {
            currentPage -= 1;
        }
    }

    private void reset() {
        currentPage = 1;
    }

    private void rearrange() {
        pages.clear();
        pages.add(new Page(MAXIMUM_CAPACITY));

        for (T i : elements) {
            addElement(i);
        }
    }

    public void sortElements(Comparator<T> comparator) {
        elements.sort(comparator);
        rearrange();
        reset();
    }
}
