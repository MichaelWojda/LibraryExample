import Model.Book;
import Model.Person;
import Service.LibraryService;

public class Main {
    public static void main(String[] args) {

        System.out.println("Main Class will display functionality of this simple App");
        LibraryService libraryService = new LibraryService();
        waitFor5sec();
        System.out.println("\n");


        System.out.println("Customer object is created with data id=1, name=Jan surname=Kowalski");
        Person reader1 = new Person(1,"Jan","Kowalski");
        waitFor5sec();
        System.out.println("\n");

        System.out.println("Six different books are being added into the Library");
        Book book1 = new Book("Władca Pierścieni","J.R.R Tolkien","2009");
        Book book4 = new Book("Władca Pierścieni","J.R.R Tolkien","2013");
        Book book5 = new Book("Władca Pierścieni","J.R.R Tolkien","2013");
        Book book6 = new Book("Silmarilion","J.R.R Tolkien","2013");
        Book book2 = new Book("Wiedzmin","Andrzej Sapkowski","2020");
        Book book3 = new Book("Bogaty Ojciec, biedny Ojciec","Robert Kiyosaki","2008");

        libraryService.addNewBook(book1);
        libraryService.addNewBook(book2);
        libraryService.addNewBook(book3);
        libraryService.addNewBook(book4);
        libraryService.addNewBook(book5);
        libraryService.addNewBook(book6);
        libraryService.showAllBooks();
        waitFor5sec();
        System.out.println("\n");

        System.out.println("Now we will display one book by id of 0");
        libraryService.getBookById(0);
        waitFor5sec();
        System.out.println("\n");

        System.out.println("We will delete this book using deleteById function");
        libraryService.deleteById(0);
        waitFor5sec();
        System.out.println("\n");

        System.out.println("Let's see the list of books now");
        libraryService.showAllBooks();
        waitFor5sec();
        System.out.println("\n");

        System.out.println("We will now look up for Book Silmarilion");
        libraryService.searchBook("title","Silmarilion");
        waitFor5sec();
        System.out.println("\n");

        System.out.println("...and for books with title Władca Pierścieni and author J.R.R Tolkien ");
        libraryService.searchByTwoParameters("title","author","Władca Pierścieni","J.R.R Tolkien");
        waitFor5sec();
        System.out.println("\n");

        System.out.println("We will borrow Silmarilion and one of the Władca Pierścieni");
        libraryService.borrowBook(5,reader1);
        libraryService.borrowBook(3,reader1);
        waitFor5sec();
        System.out.println("\n");

        System.out.println("The books are now borrowed");
        libraryService.showAllBooks();





    }

    private static void waitFor5sec() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
