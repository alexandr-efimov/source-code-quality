package software.sigma.library.mocks;


import software.sigma.library.data.DataServices;
import software.sigma.library.models.Book;
import software.sigma.library.models.BookTitle;
import software.sigma.library.models.Patron;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MockDataServices implements DataServices {
    public Book added;
    private Map<String, List<Book>> map = new HashMap<String, List<Book>>();
    private static long lastId = 0;
    Map<String, Patron> patronMap = new HashMap<String, Patron>();
    public String wasLastCalledWithThisIsbn;
    Map<String, BookTitle> titleMap = new HashMap<String, BookTitle>();

    public Book addBook(BookTitle string) {
        Book c = new Book(string, "" + (++lastId));
        String isbn = string.getIsbn();
        List<Book> copies2 = map.get(isbn);
        if (copies2 == null) {
            copies2 = new LinkedList<Book>();
            map.put(isbn, copies2);
        }
        copies2.add(c);
        added = c;
        return c;
    }

    public Book findCopy1(String string) {
        List<Book> copies2 = map.get(string);
        if (copies2 != null)
            return copies2.get(0);
        else
            return null;
    }

    public int countBooks() {
        return map.size();
    }

    public List<Book> findMany(String isbn) {
        List<Book> copies2 = map.get(isbn);
        if (copies2 == null)
            return new ArrayList<Book>();
        return copies2;
    }

    public boolean canFindCopy(String string) {
        return map.containsKey(string);
    }

    public Book findAvailableCopy(String isbn) {
        List<Book> copies2 = findMany(isbn);
        for (int i = 0; i < copies2.size(); i++) {
            Book c = (Book) copies2.get(i);
            if (!c.isBorrowed())
                return c;
        }
        return null;
    }

    public Book findCopy2(String copyId) {
        Collection<List<Book>> copies2 = map.values();
        for (Iterator<List<Book>> i = copies2.iterator(); i.hasNext(); ) {
            List<Book> copies3 = i.next();
            for (int j = 0; j < copies3.size(); j++) {
                Book c = (Book) copies3.get(j);
                if (c.getId().equals(copyId))
                    return c;
            }
        }
        return null;
    }

    public int countActivePatrons() {
        return patronMap.size();
    }

    public void addPatron(Patron p) {
        patronMap.put(p.getId(), p);
    }

    public Patron findPatron(String string) {
        return patronMap.get(string);
    }

    public void setBookToReturn(BookTitle t) {
        titleMap.put(t.getIsbn(), t);
    }

    public BookTitle findTitleByIsbn(String string) {
        wasLastCalledWithThisIsbn = string;
        return titleMap.get(string);
    }
}
