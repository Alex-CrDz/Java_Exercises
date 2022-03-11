package com.globant.topic_6;

import com.globant.topic_6.Exceptions.InsufficientFundsException;
import com.globant.topic_6.Exceptions.InvalidTargetFundsException;
import com.globant.topic_6.Persistance.Model.Bank;
import com.globant.topic_6.Persistance.Model.BankAccount;
import com.globant.topic_6.Persistance.Model.TransactionTransfer;
import com.globant.topic_6.Service.Interfaces.BankAccountService;
import com.globant.topic_6.Service.Interfaces.TransactionTransferService;
import com.globant.topic_6.Service.TransferService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TransferServiceTest {

    @Mock
    private TransactionTransferService transactionService;
    @Mock
    private BankAccountService accountService;
    @InjectMocks
    private TransferService transferService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void originAccountWithNoFunds() {
        BankAccount originAccount = BankAccount.builder()
                .idAccount("13625524788")
                .typeAccount(BankAccount.SAVINGS)
                .ownerIdNumber("6649552142")
                .ownerFirstName("Geraldine")
                .ownerLastName("Crabb")
                .fundsAccount(10000.0)
                .bank(Bank.builder().idBank(5).nameBank("Globant Bank").build())
                .build();
        BankAccount targetAccount = BankAccount.builder()
                .idAccount("17385739284")
                .typeAccount(BankAccount.SAVINGS)
                .ownerIdNumber("8888082920")
                .ownerFirstName("Torre")
                .ownerLastName("Roberti")
                .fundsAccount(500000.0)
                .bank(Bank.builder().idBank(5).nameBank("Globant Bank").build())
                .build();

        when(accountService.findAccountById("13625524788")).thenReturn(originAccount);
        when(accountService.findAccountById("17385739284")).thenReturn(targetAccount);
        when(accountService.updateAccount(any(BankAccount.class))).then(AdditionalAnswers.returnsFirstArg());
        when(transactionService.newTransaction(any(TransactionTransfer.class))).then(AdditionalAnswers.returnsFirstArg());
        when(transactionService.updateTransaction(any(TransactionTransfer.class))).then(AdditionalAnswers.returnsFirstArg());

        InsufficientFundsException e =
                assertThrows(InsufficientFundsException.class,
                        () -> transferService.transfer("13625524788", "17385739284", 50000.00));

        assertEquals(InsufficientFundsException.MESSAGE_DEFAULT, e.getMessage());
    }

    @Test
    void validTransferToAnotherBank() throws Exception {
        BankAccount originAccount = BankAccount.builder()
                .idAccount("13625524788")
                .typeAccount(BankAccount.SAVINGS)
                .ownerIdNumber("6649552142")
                .ownerFirstName("Geraldine")
                .ownerLastName("Crabb")
                .fundsAccount(100000.0)
                .bank(Bank.builder().idBank(5).nameBank("Globant Bank").build())
                .build();
        BankAccount targetAccount = BankAccount.builder()
                .idAccount("17385739284")
                .typeAccount(BankAccount.SAVINGS)
                .ownerIdNumber("8888082920")
                .ownerFirstName("Torre")
                .ownerLastName("Roberti")
                .fundsAccount(500000.0)
                .bank(Bank.builder().idBank(1).nameBank("Bancolombia").build())
                .build();

        when(accountService.findAccountById("13625524788")).thenReturn(originAccount);
        when(accountService.findAccountById("17385739284")).thenReturn(targetAccount);
        when(accountService.updateAccount(any(BankAccount.class))).then(AdditionalAnswers.returnsFirstArg());
        when(transactionService.newTransaction(any(TransactionTransfer.class))).then(AdditionalAnswers.returnsFirstArg());
        when(transactionService.updateTransaction(any(TransactionTransfer.class))).then(AdditionalAnswers.returnsFirstArg());

        TransactionTransfer result = transferService.transfer("13625524788", "17385739284", 50000.0);
        double expectedTransactionAmount = 50000.0;
        double expectedOriginFunds = 46500.0;
        double expectedTargetFunds = 550000.0;
        String expectedTransactionStatus = "FINISHED";

        assertAll("result",
                () -> assertEquals(expectedTransactionAmount, result.getAmount()),
                () -> assertEquals(expectedOriginFunds, result.getOriginAccount().getFundsAccount()),
                () -> assertEquals(expectedTargetFunds, result.getDestinationAccount().getFundsAccount()),
                () -> assertEquals(expectedTransactionStatus, result.getStatus()));
    }

    @Test
    void invalidTransferToCheckingAccount() throws Exception {
        BankAccount originAccount = BankAccount.builder()
                .idAccount("13625524788")
                .typeAccount(BankAccount.SAVINGS)
                .ownerIdNumber("6649552142")
                .ownerFirstName("Geraldine")
                .ownerLastName("Crabb")
                .fundsAccount(10000000.0)
                .bank(Bank.builder().idBank(5).nameBank("Globant Bank").build())
                .build();
        BankAccount targetAccount = BankAccount.builder()
                .idAccount("17385739284")
                .typeAccount(BankAccount.CHECKING)
                .ownerIdNumber("8888082920")
                .ownerFirstName("Torre")
                .ownerLastName("Roberti")
                .fundsAccount(5000000.0)
                .bank(Bank.builder().idBank(5).nameBank("Globant Bank").build())
                .build();

        when(accountService.findAccountById("13625524788")).thenReturn(originAccount);
        when(accountService.findAccountById("17385739284")).thenReturn(targetAccount);
        when(accountService.updateAccount(any(BankAccount.class))).then(AdditionalAnswers.returnsFirstArg());
        when(transactionService.newTransaction(any(TransactionTransfer.class))).then(AdditionalAnswers.returnsFirstArg());
        when(transactionService.updateTransaction(any(TransactionTransfer.class))).then(AdditionalAnswers.returnsFirstArg());

        InvalidTargetFundsException e =
                assertThrows(InvalidTargetFundsException.class,
                        () -> transferService.transfer("13625524788", "17385739284", 1000000.0));

        assertEquals(InvalidTargetFundsException.MESSAGE, e.getMessage());
    }

    @Test
    void validTransferToCheckingAccount() throws Exception {
        BankAccount originAccount = BankAccount.builder()
                .idAccount("13625524788")
                .typeAccount(BankAccount.SAVINGS)
                .ownerIdNumber("6649552142")
                .ownerFirstName("Geraldine")
                .ownerLastName("Crabb")
                .fundsAccount(10000000.0)
                .bank(Bank.builder().idBank(5).nameBank("Globant Bank").build())
                .build();
        BankAccount targetAccount = BankAccount.builder()
                .idAccount("17385739284")
                .typeAccount(BankAccount.CHECKING)
                .ownerIdNumber("8888082920")
                .ownerFirstName("Torre")
                .ownerLastName("Roberti")
                .fundsAccount(2000000.0)
                .bank(Bank.builder().idBank(5).nameBank("Globant Bank").build())
                .build();

        when(accountService.findAccountById("13625524788")).thenReturn(originAccount);
        when(accountService.findAccountById("17385739284")).thenReturn(targetAccount);
        when(accountService.updateAccount(any(BankAccount.class))).then(AdditionalAnswers.returnsFirstArg());
        when(transactionService.newTransaction(any(TransactionTransfer.class))).then(AdditionalAnswers.returnsFirstArg());
        when(transactionService.updateTransaction(any(TransactionTransfer.class))).then(AdditionalAnswers.returnsFirstArg());

        TransactionTransfer result = transferService.transfer("13625524788", "17385739284", 1000000.0);
        double expectedTransactionAmount = 1000000.0;
        double expectedOriginFunds = 9000000.0;
        double expectedTargetFunds = 3000000.0;
        String expectedTransactionStatus = "FINISHED";

        assertAll("result",
                () -> assertEquals(expectedTransactionAmount, result.getAmount()),
                () -> assertEquals(expectedOriginFunds, result.getOriginAccount().getFundsAccount()),
                () -> assertEquals(expectedTargetFunds, result.getDestinationAccount().getFundsAccount()),
                () -> assertEquals(expectedTransactionStatus, result.getStatus()));
    }

    @Test
    void transferMountGreaterThanOneAndHalfMillion() throws Exception {
        BankAccount originAccount = BankAccount.builder()
                .idAccount("13625524788")
                .typeAccount(BankAccount.SAVINGS)
                .ownerIdNumber("6649552142")
                .ownerFirstName("Geraldine")
                .ownerLastName("Crabb")
                .fundsAccount(10000000.0)
                .bank(Bank.builder().idBank(5).nameBank("Globant Bank").build())
                .build();
        BankAccount targetAccount = BankAccount.builder()
                .idAccount("17385739284")
                .typeAccount(BankAccount.SAVINGS)
                .ownerIdNumber("8888082920")
                .ownerFirstName("Torre")
                .ownerLastName("Roberti")
                .fundsAccount(2000000.0)
                .bank(Bank.builder().idBank(5).nameBank("Globant Bank").build())
                .build();

        when(accountService.findAccountById("13625524788")).thenReturn(originAccount);
        when(accountService.findAccountById("17385739284")).thenReturn(targetAccount);
        when(accountService.updateAccount(any(BankAccount.class))).then(AdditionalAnswers.returnsFirstArg());
        when(transactionService.newTransaction(any(TransactionTransfer.class))).then(AdditionalAnswers.returnsFirstArg());
        when(transactionService.updateTransaction(any(TransactionTransfer.class))).then(AdditionalAnswers.returnsFirstArg());

        TransactionTransfer result = transferService.transfer("13625524788", "17385739284", 2000000.0);
        double expectedTransactionAmount = 1940000.0;
        double expectedOriginFunds = 8000000.0;
        double expectedTargetFunds = 3940000.0;
        String expectedTransactionStatus = "FINISHED";

        assertAll("result",
                () -> assertEquals(expectedTransactionAmount, result.getAmount()),
                () -> assertEquals(expectedOriginFunds, result.getOriginAccount().getFundsAccount()),
                () -> assertEquals(expectedTargetFunds, result.getDestinationAccount().getFundsAccount()),
                () -> assertEquals(expectedTransactionStatus, result.getStatus()));
    }
}
