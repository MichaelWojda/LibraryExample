package Service;


import Model.Book;
import Model.Person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LibraryService {

    private Map<Integer, Book> bookList = new HashMap<>();


    public void addNewBook(Book book) {
        int id = bookList.size();
        if (!bookList.containsKey(id)) {
            book.setId(id);
            book.setLent(false);
            bookList.put(id, book);

        } else {
            id = generateValidId();
            book.setId(id);
            bookList.put(id, book);
        }
    }

    public Book getBookById(int id) {
        Book book = bookList.get(id);
        if (book != null) {
            System.out.println(book.toString());
            return book;
        } else {
            System.err.println("There is no book with id: " + id);
            return null;
        }

    }

    public void deleteById(int id) {
        if (!bookList.containsKey(id)) {
            System.err.println("There is no book with id: " + id);
        } else if (bookList.get(id).isLent() == true) {
            System.err.println("You can't delete this book as it has been lent");
        } else {
            Book book = bookList.remove(id);
            System.out.println("Book: " + book.getTitle() + " has been successfully deleted");

        }

    }

    public void showAllBooks() {
        if (bookList.isEmpty()) {
            System.out.println("There is no books in the library at this time.");
        } else {
            bookList.forEach((k, v) -> {
                System.out.println(
                        "Id: "
                                + k
                                + " Title: "
                                + v.getTitle()
                                + " Author: "
                                + v.getAuthor()
                                + " Year: "
                                + v.getYear()
                                + " Is Lent?: "
                                + v.isLent()
                                + " Current readers(null if not borrowed): "
                                + v.getReader()

                );
            });
            long availableCount = bookList.entrySet().stream().filter(e -> !e.getValue().isLent()).count();
            long lentCount = bookList.entrySet().stream().filter(e -> e.getValue().isLent()).count();
            System.out.println("Currently there is available books: " + availableCount + " and lent books: " + lentCount);
        }
    }

    private int generateValidId() {
        int id = bookList.size();
        while (bookList.containsKey(id)) {
            id++;
        }
        return id;
    }

    public void searchBook(String parameter, String value) {
        if(parameter.equals("title") || parameter.equals("author") || parameter.equals("year")) {
            switch (parameter) {
                case "title":
                    List<Map.Entry<Integer, Book>> listTitle = bookList.entrySet().stream().filter(e -> e.getValue().getTitle().equals(value)).collect(Collectors.toList());
                    listTitle.forEach(e -> System.out.println(e.getValue().toString()));
                    break;
                case "author":
                    List<Map.Entry<Integer, Book>> listAuthor = bookList.entrySet().stream().filter(e -> e.getValue().getAuthor().equals(value)).collect(Collectors.toList());
                    listAuthor.forEach(e -> System.out.println(e.getValue().toString()));
                    break;
                case "year":
                    List<Map.Entry<Integer, Book>> listYear = bookList.entrySet().stream().filter(e -> e.getValue().getYear().equals(value)).collect(Collectors.toList());
                    listYear.forEach(e -> System.out.println(e.getValue().toString()));
            }
        }
        else{
            System.err.println("Wrong parameter error");
        }

    }

    public void searchByTwoParameters(String parameter1, String parameter2, String value1, String value2) {
        if (parameter1.equals("title") && parameter2.equals("author")) {
            List<Map.Entry<Integer, Book>> list = bookList.entrySet().stream().filter(e -> e.getValue().getTitle().equals(value1))
                    .filter(e -> e.getValue().getAuthor().equals(value2))
                    .collect(Collectors.toList());
            list.forEach(e -> System.out.println(e.getValue().toString()));
        } else if (parameter1.equals("title") && parameter2.equals("year")) {
            List<Map.Entry<Integer, Book>> list = bookList.entrySet().stream().filter(e -> e.getValue().getTitle().equals(value1))
                    .filter(e -> e.getValue().getYear().equals(value2))
                    .collect(Collectors.toList());
            list.forEach(e -> System.out.println(e.getValue().toString()));
        } else if (parameter1.equals("author") && parameter2.equals("year")) {
            List<Map.Entry<Integer, Book>> list = bookList.entrySet().stream().filter(e -> e.getValue().getAuthor().equals(value1))
                    .filter(e -> e.getValue().getYear().equals(value2))
                    .collect(Collectors.toList());
            list.forEach(e -> System.out.println(e.getValue().toString()));
        } else {
            System.err.println("Wrong parameter error");
        }
//I should implement mirror image of the params possibilities to cover them all.
    }

    public void borrowBook(int bookId, Person reader) {
        Book book = bookList.get(bookId);
        if (book == null) {
            System.err.println("There is no book with id: " + bookId);
        } else if (book.isLent()) {
            System.err.println("You cannot borrow book that has been lent");
        } else {
            book.setReader(reader);
            book.setLent(true);
            bookList.put(bookId, book);
            System.out.println("Book: "
                    + book.getTitle()
                    + " has been lent to reader: "
                    + reader.getName()
                    + " "
                    + reader.getSurname()
            );
        }

    }


    public int librarySize(){
        return bookList.size();
    }


}
