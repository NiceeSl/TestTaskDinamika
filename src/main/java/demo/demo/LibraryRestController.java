package demo.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
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
}
