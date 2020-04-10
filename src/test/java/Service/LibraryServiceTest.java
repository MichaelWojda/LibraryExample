package Service;

import Model.Book;
import Model.Person;
import Service.LibraryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class LibraryServiceTest {



    @Test
    void addNewBook() {
        LibraryService libraryService = new LibraryService();
        libraryService.addNewBook(new Book("Title1","Author1","1999"));
        libraryService.addNewBook(new Book("Title2","Author2","2000"));
        libraryService.addNewBook(new Book("Title3","Author3","2001"));
        libraryService.addNewBook(new Book("Title4","Author4","2002"));
        libraryService.addNewBook(new Book("Title5","Author5","2003"));
        Assertions.assertTrue(libraryService.librarySize()==5);

    }

    @Test
    void getBookById() {
        LibraryService libraryService = new LibraryService();
        String title="Title1";
        libraryService.addNewBook(new Book(title,"Author1","1999"));//id=0
        libraryService.addNewBook(new Book("Title2","Author2","2000"));
        libraryService.addNewBook(new Book("Title3","Author3","2001"));
        libraryService.addNewBook(new Book("Title4","Author4","2002"));
        libraryService.addNewBook(new Book("Title5","Author5","2003"));
        Book book = libraryService.getBookById(0);
        Assertions.assertTrue(book.getTitle().equals(title));



    }

    @Test
    void deleteById() {
        LibraryService libraryService = new LibraryService();
        libraryService.addNewBook(new Book("Title1","Author1","1999"));//id=0
        libraryService.addNewBook(new Book("Title2","Author2","2000"));
        libraryService.addNewBook(new Book("Title3","Author3","2001"));
        libraryService.addNewBook(new Book("Title4","Author4","2002"));
        libraryService.addNewBook(new Book("Title5","Author5","2003"));
        libraryService.deleteById(0);
        Assertions.assertNull(libraryService.getBookById(0));

    }

    @Test
    void borrowBook() {
        LibraryService libraryService = new LibraryService();
        int personId=1;
        Person person = new Person(personId,"Jan","Kowalski");
        Book book = new Book("Title1","Author1","1999");
        libraryService.addNewBook(book);// id=0
        libraryService.borrowBook(0,person);
        Book lookedUpBook = libraryService.getBookById(0);
        Assertions.assertTrue(lookedUpBook.isLent()==true && lookedUpBook.getReader().getId()==personId);

    }
}