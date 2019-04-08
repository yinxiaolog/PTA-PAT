import util.Read;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DigitalLibrary {
    private int N;
    private int M;
    private Map<String, Book> booksMap;
    private Map<String, String> titlesMap;
    private Map<String, String> authorsMap;
    private Map<String, String> keysMap;
    private Map<String, String> publishersMap;
    private Map<String, String> publisherYearsMap;

    public DigitalLibrary(int N, int M, Map<String, Book> booksMap) {
        this.N = N;
        this.M = M;
        this.booksMap = booksMap;
        setBookAttribute();
    }

    private void setBookAttribute() {
        titlesMap = new HashMap<>();
        authorsMap = new HashMap<>();
        keysMap = new HashMap<>();
        publishersMap = new HashMap<>();
        publisherYearsMap = new HashMap<>();

        for (Map.Entry<String, Book> entry : booksMap.entrySet()) {
            String id = entry.getKey();
            Book book = entry.getValue();
            titlesMap.put(id, book.getTitle());
            authorsMap.put(id, book.getAuthor());
            keysMap.put(id, book.getKey());
            publishersMap.put(id, book.getPublisher());
            publisherYearsMap.put(id, book.getPublihserYear());
        }
    }

    public List<String> queryTitle(String title) {
        List<String> ans = new ArrayList<>();
        for (Map.Entry<String, String> entry : titlesMap.entrySet()) {
            if (entry.getValue().equals(title)) {
                ans.add(entry.getKey());
            }
        }
        Collections.sort(ans);
        return ans;
    }

    public List<String> queryAuthor(String author) {
        List<String> ans = new ArrayList<>();
        for (Map.Entry<String, String> entry : authorsMap.entrySet()) {
            if (entry.getValue().equals(author)) {
                ans.add(entry.getKey());
            }
        }
        Collections.sort(ans);
        return ans;
    }

    public List<String> queryKey(String key) {
        List<String> ans = new ArrayList<>();
        for (Map.Entry<String, String> entry : keysMap.entrySet()) {
            if (entry.getValue().contains(key)) {
                ans.add(entry.getKey());
            }
        }
        Collections.sort(ans);
        return ans;
    }

    public List<String> queryPublisher(String publisher) {
        List<String> ans = new ArrayList<>();
        for (Map.Entry<String, String> entry : publishersMap.entrySet()) {
            if (entry.getValue().equals(publisher)) {
                ans.add(entry.getKey());
            }
        }
        Collections.sort(ans);
        return ans;
    }

    public List<String> queryPublisherYear(String publisherYear) {
        List<String> ans = new ArrayList<>();
        for (Map.Entry<String, String> entry : publisherYearsMap.entrySet()) {
            if (entry.getValue().equals(publisherYear)) {
                ans.add(entry.getKey());
            }
        }
        Collections.sort(ans);
        return ans;
    }

    public List<String> query(int i, String s) {
        switch (i) {
            case 1:
                return queryTitle(s);
            case 2:
                return queryAuthor(s);
            case 3:
                return queryKey(s);
            case 4:
                return queryPublisher(s);
            case 5:
                return queryPublisherYear(s);
            default:
                return null;
        }
    }

    public static void main(String[] args) throws IOException {
        Map<String, Book> bookMap = new HashMap<>();
        Read.init();
        int bookNum = Read.nextInt();
        for (int i = 0; i < bookNum; i++) {
            String id = Read.next();
            Book book = new Book();
            book.setId(id);
            book.setTitle(Read.nextLine());
            book.setAuthor(Read.nextLine());
            book.setKey(Read.nextLine());
            book.setPublisher(Read.nextLine());
            book.setPublihserYear(Read.next());
            bookMap.put(id, book);
        }

        //System.out.println(bookMap);
        int queryNum = Read.nextInt();
        List<String> queryList = new ArrayList<>();
        for (int i = 0; i < queryNum; i++) {
            String s = Read.nextLine();
            queryList.add(s);
        }

        //System.out.println(queryList);
        DigitalLibrary digitalLibrary = new DigitalLibrary(bookNum, queryNum, bookMap);
        for (String s : queryList) {
            int query = Integer.parseInt(s.split(":")[0].trim());
            String keyWord = s.split(":")[1].trim();
            List<String> list = digitalLibrary.query(query, keyWord);
            System.out.println(s);
            if (list.size() == 0 || list == null) {
                System.out.println("Not Found");
            } else {
                for (String ans : list) {
                    System.out.println(ans);
                }
            }
        }
    }
}

class Book {
    private String id;
    private String title;
    private String author;
    private String key;
    private String publisher;
    private String publihserYear;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublihserYear() {
        return publihserYear;
    }

    public void setPublihserYear(String publihserYear) {
        this.publihserYear = publihserYear;
    }

    @Override
    public String toString() {
        return "[" + "id: " + id + " title: " + title + " author: " + author + " key: "
                + key + " publisher: " + publisher + " pulisherYear: " + publihserYear;
    }
}
