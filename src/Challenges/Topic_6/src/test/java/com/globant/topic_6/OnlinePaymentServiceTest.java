package com.globant.topic_6;

import com.globant.topic_6.Exceptions.InsufficientFundsException;
import com.globant.topic_6.Exceptions.InvalidBillIdException;
import com.globant.topic_6.Persistance.Model.Bank;
import com.globant.topic_6.Persistance.Model.BankAccount;
import com.globant.topic_6.Persistance.Model.Bill;
import com.globant.topic_6.Persistance.Model.TransactionBill;
import com.globant.topic_6.Service.Interfaces.BankAccountService;
import com.globant.topic_6.Service.Interfaces.BillService;
import com.globant.topic_6.Service.Interfaces.TransactionBillService;
import com.globant.topic_6.Service.OnlinePaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class OnlinePaymentServiceTest {

    @Mock
    BankAccountService accountService;
    @Mock
    BillService billService;
    @Mock
    TransactionBillService transactionService;
    @InjectMocks
    OnlinePaymentService onlinePaymentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void invalidIdFormatBillId() {
        BankAccount originAccount = BankAccount.builder()
                .idAccount("13625524788")
                .typeAccount(BankAccount.SAVINGS)
                .ownerIdNumber("6649552142")
                .ownerFirstName("Geraldine")
                .ownerLastName("Crabb")
                .fundsAccount(100000.0)
                .bank(Bank.builder().idBank(5).nameBank("Globant Bank").build())
                .build();
        Bill bill = Bill.builder()
                .idBill("0123456")
                .idCompany("ETB")
                .nameCompany("Empresa de Telecomunicaciones de Bogotá")
                .expirationDate(new Date(2022, 04, 25))
                .valueBill(90000.0)
                .build();

        when(accountService.findAccountById("13625524788")).thenReturn(originAccount);
        when(accountService.updateAccount(any(BankAccount.class))).then(AdditionalAnswers.returnsFirstArg());
        when(billService.getBillById("0123456")).thenReturn(bill);
        when(billService.updateBill(any(Bill.class))).then(AdditionalAnswers.returnsFirstArg());
        when(transactionService.newTransaction(any(TransactionBill.class))).then(AdditionalAnswers.returnsFirstArg());
        when(transactionService.updateTransaction(any(TransactionBill.class))).then(AdditionalAnswers.returnsFirstArg());

        InvalidBillIdException e =
                assertThrows(InvalidBillIdException.class, () -> onlinePaymentService.onlinePayment("13625524788", "0123456"));

        assertEquals(InvalidBillIdException.MESSAGE, e.getMessage());
    }

    @Test
    void invalidIdLengthBillId() {
        BankAccount originAccount = BankAccount.builder()
                .idAccount("13625524788")
                .typeAccount(BankAccount.SAVINGS)
                .ownerIdNumber("6649552142")
                .ownerFirstName("Geraldine")
                .ownerLastName("Crabb")
                .fundsAccount(100000.0)
                .bank(Bank.builder().idBank(5).nameBank("Globant Bank").build())
                .build();
        Bill bill = Bill.builder()
                .idBill("00123456")
                .idCompany("ETB")
                .nameCompany("Empresa de Telecomunicaciones de Bogotá")
                .expirationDate(new Date(2022, 04, 25))
                .valueBill(90900.0)
                .build();

        when(accountService.findAccountById("13625524788")).thenReturn(originAccount);
        when(accountService.updateAccount(any(BankAccount.class))).then(AdditionalAnswers.returnsFirstArg());
        when(billService.getBillById("00123456")).thenReturn(bill);
        when(billService.updateBill(any(Bill.class))).then(AdditionalAnswers.returnsFirstArg());
        when(transactionService.newTransaction(any(TransactionBill.class))).then(AdditionalAnswers.returnsFirstArg());
        when(transactionService.updateTransaction(any(TransactionBill.class))).then(AdditionalAnswers.returnsFirstArg());

        InvalidBillIdException e =
                assertThrows(InvalidBillIdException.class, () -> onlinePaymentService.onlinePayment("13625524788", "00123456"));

        assertEquals(InvalidBillIdException.MESSAGE, e.getMessage());
    }

    @Test
    void originAccountWithNoFunds() {
        BankAccount originAccount = BankAccount.builder()
                .idAccount("13625524788")
                .typeAccount(BankAccount.SAVINGS)
                .ownerIdNumber("6649552142")
                .ownerFirstName("Geraldine")
                .ownerLastName("Crabb")
                .fundsAccount(100000.0)
                .bank(Bank.builder().idBank(5).nameBank("Globant Bank").build())
                .build();
        Bill bill = Bill.builder()
                .idBill("0012345")
                .idCompany("ETB")
                .nameCompany("Empresa de Telecomunicaciones de Bogotá")
                .expirationDate(new Date(2022, 04, 25))
                .valueBill(90000.0)
                .build();

        when(accountService.findAccountById("13625524788")).thenReturn(originAccount);
        when(accountService.updateAccount(any(BankAccount.class))).then(AdditionalAnswers.returnsFirstArg());
        when(billService.getBillById("0012345")).thenReturn(bill);
        when(billService.updateBill(any(Bill.class))).then(AdditionalAnswers.returnsFirstArg());
        when(transactionService.newTransaction(any(TransactionBill.class))).then(AdditionalAnswers.returnsFirstArg());
        when(transactionService.updateTransaction(any(TransactionBill.class))).then(AdditionalAnswers.returnsFirstArg());

        InsufficientFundsException e =
                assertThrows(InsufficientFundsException.class, () -> onlinePaymentService.onlinePayment("13625524788", "0012345"));

        assertEquals(InsufficientFundsException.MESSAGE_BILL, e.getMessage());
    }

    @Test
    void validPaymentWithCheckingDiscount() throws Exception {
        BankAccount originAccount = BankAccount.builder()
                .idAccount("13625524788")
                .typeAccount(BankAccount.CHECKING)
                .ownerIdNumber("6649552142")
                .ownerFirstName("Geraldine")
                .ownerLastName("Crabb")
                .fundsAccount(200000.0)
                .bank(Bank.builder().idBank(5).nameBank("Globant Bank").build())
                .build();
        Bill bill = Bill.builder()
                .idBill("0012345")
                .idCompany("ETB")
                .nameCompany("Empresa de Telecomunicaciones de Bogotá")
                .expirationDate(new Date(2022, 04, 25))
                .valueBill(90000.0)
                .build();

        when(accountService.findAccountById("13625524788")).thenReturn(originAccount);
        when(accountService.updateAccount(any(BankAccount.class))).then(AdditionalAnswers.returnsFirstArg());
        when(billService.getBillById("0012345")).thenReturn(bill);
        when(billService.updateBill(any(Bill.class))).then(AdditionalAnswers.returnsFirstArg());
        when(transactionService.newTransaction(any(TransactionBill.class))).then(AdditionalAnswers.returnsFirstArg());
        when(transactionService.updateTransaction(any(TransactionBill.class))).then(AdditionalAnswers.returnsFirstArg());

        TransactionBill result = onlinePaymentService.onlinePayment("13625524788", "0012345");

        double expectedTransactionAmount = 81000.0;
        double expectedAccountFunds = 119000.0;
        boolean expectedBillStatus = true;
        String expectedTransactionStatus = "FINISHED";

        assertAll("result",
                () -> assertEquals(expectedTransactionAmount, result.getAmount()),
                () -> assertEquals(expectedAccountFunds, result.getOriginAccount().getFundsAccount()),
                () -> assertEquals(expectedBillStatus, result.getBillToPay().isPaid()),
                () -> assertEquals(expectedTransactionStatus, result.getStatus()));
    }

    @Test
    void validPaymentFromSavingsAccount() throws Exception {
        BankAccount originAccount = BankAccount.builder()
                .idAccount("13625524788")
                .typeAccount(BankAccount.SAVINGS)
                .ownerIdNumber("6649552142")
                .ownerFirstName("Geraldine")
                .ownerLastName("Crabb")
                .fundsAccount(200000.0)
                .bank(Bank.builder().idBank(5).nameBank("Globant Bank").build())
                .build();
        Bill bill = Bill.builder()
                .idBill("0012345")
                .idCompany("ETB")
                .nameCompany("Empresa de Telecomunicaciones de Bogotá")
                .expirationDate(new Date(2022, 04, 25))
                .valueBill(90000.0)
                .build();

        when(accountService.findAccountById("13625524788")).thenReturn(originAccount);
        when(accountService.updateAccount(any(BankAccount.class))).then(AdditionalAnswers.returnsFirstArg());
        when(billService.getBillById("0012345")).thenReturn(bill);
        when(billService.updateBill(any(Bill.class))).then(AdditionalAnswers.returnsFirstArg());
        when(transactionService.newTransaction(any(TransactionBill.class))).then(AdditionalAnswers.returnsFirstArg());
        when(transactionService.updateTransaction(any(TransactionBill.class))).then(AdditionalAnswers.returnsFirstArg());

        TransactionBill result = onlinePaymentService.onlinePayment("13625524788", "0012345");

        double expectedTransactionAmount = 90000.0;
        double expectedAccountFunds = 110000.0;
        boolean expectedBillStatus = true;
        String expectedTransactionStatus = "FINISHED";

        assertAll("result",
                () -> assertEquals(expectedTransactionAmount, result.getAmount()),
                () -> assertEquals(expectedAccountFunds, result.getOriginAccount().getFundsAccount()),
                () -> assertEquals(expectedBillStatus, result.getBillToPay().isPaid()),
                () -> assertEquals(expectedTransactionStatus, result.getStatus()));
    }
}
