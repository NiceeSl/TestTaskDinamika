package demo.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LibraryRestController {
    private final ClientRepository clientRepository;
    private final BookRepository bookRepository;
    private final BookLoanRepository bookLoanRepository;

    public LibraryRestController(ClientRepository clientRepository, BookRepository bookRepository, BookLoanRepository bookLoanRepository) {
        this.clientRepository = clientRepository;
        this.bookRepository = bookRepository;
        this.bookLoanRepository = bookLoanRepository;
    }

    @GetMapping("/readers")
    public List<ReaderDTO> getAllReaders() {
        return bookLoanRepository.findAll().stream()
                .map(bookLoan -> {
                    ReaderDTO readerDTO = new ReaderDTO();
                    readerDTO.setFullName(bookLoan.getClient().getFullName());
                    readerDTO.setDateOfBirth(bookLoan.getClient().getDateOfBirth().atStartOfDay());
                    readerDTO.setBookTitle(bookLoan.getBook().getTitle());
                    readerDTO.setBookAuthor(bookLoan.getBook().getAuthor());
                    readerDTO.setBookIsbn(bookLoan.getBook().getIsbn());
                    readerDTO.setBorrowDate(bookLoan.getLoanDate());
                    return readerDTO;
                })
                .collect(Collectors.toList());
    }

    @PostMapping("/borrow/{clientId}/{bookId}")
    public ResponseEntity<String> borrowBook(@PathVariable Long clientId, @PathVariable Long bookId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Client not found with id " + clientId));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with id " + bookId));

        BookLoan bookLoan = new BookLoan(book, client, LocalDateTime.now());
        bookLoanRepository.save(bookLoan);

        return ResponseEntity.status(HttpStatus.CREATED).body("Book borrowed successfully");
    }
}
