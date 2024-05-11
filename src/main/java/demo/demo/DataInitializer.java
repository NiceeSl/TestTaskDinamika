package demo.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final ClientRepository clientRepository;
    private final BookLoanRepository bookLoanRepository;

    @Autowired
    public DataInitializer(BookRepository bookRepository, ClientRepository clientRepository, BookLoanRepository bookLoanRepository) {
        this.bookRepository = bookRepository;
        this.clientRepository = clientRepository;
        this.bookLoanRepository = bookLoanRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Book book1 = new Book("Война и мир", "Лев Толстой", "9785811601098");
        Book book2 = new Book("Преступление и наказание", "Фёдор Достоевский", "9785519604766");
        Book book3 = new Book("Гарри Поттер и философский камень", "Джоан Роулинг", "9785353002998");

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

        Client client1 = new Client("Иванов Иван Иванович", LocalDate.of(1985, 5, 10));
        Client client2 = new Client("Петров Петр Петрович", LocalDate.of(1990, 8, 20));
        Client client3 = new Client("Сидоров Сидор Сидорович", LocalDate.of(1980, 3, 15));

        clientRepository.save(client1);
        clientRepository.save(client2);
        clientRepository.save(client3);

        BookLoan bookLoan1 = new BookLoan(book1, client1, LocalDateTime.now());
        BookLoan bookLoan2 = new BookLoan(book2, client2, LocalDateTime.now());

        bookLoanRepository.save(bookLoan1);
        bookLoanRepository.save(bookLoan2);
    }
}
