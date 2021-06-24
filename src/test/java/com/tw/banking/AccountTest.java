package com.tw.banking;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AccountTest {

    @Test
    public void should_transactionRepository_addDeposit_10_when_deposit_given_amount_is_10() {
        //given
        TransactionRepository transactionRepositoryMock = mock(TransactionRepository.class);
        Printer printerMock = mock(Printer.class);
        Account account = new Account(transactionRepositoryMock, printerMock);
        //when
        account.deposit(10);
        //then
        verify(transactionRepositoryMock, times(1)).addDeposit(10);
    }

    @Test
    public void should_transactionRepository_addWithdraw_10_when_withdraw_given_amount_is_10() {
        //given
        TransactionRepository transactionRepositoryMock = mock(TransactionRepository.class);
        Printer printerMock = mock(Printer.class);
        Account account = new Account(transactionRepositoryMock, printerMock);
        //when
        account.withdraw(10);
        //then
        verify(transactionRepositoryMock, times(1)).addWithdraw(10);
    }

    @Test
    public void should_printer_print_transactionMock_when_printStatement_given_allTransactions_from_transactionRepository_is_transactionMock() {
        //given
        TransactionRepository transactionRepositoryMock = mock(TransactionRepository.class);
        Printer printerMock = mock(Printer.class);
        Transaction transactionMock = mock(Transaction.class);
        Account account = new Account(transactionRepositoryMock, printerMock);
        when(transactionRepositoryMock.allTransactions()).thenReturn(Collections.singletonList(transactionMock));
        ArgumentCaptor<List<Transaction>> captor = ArgumentCaptor.forClass(List.class);
        //when
        account.printStatement();
        //then
        verify(printerMock, times(1)).print(captor.capture());
        List<Transaction> argumentList = captor.getValue();
        assertEquals(1, argumentList.size());
        assertEquals(transactionMock, argumentList.get(0));
    }
}