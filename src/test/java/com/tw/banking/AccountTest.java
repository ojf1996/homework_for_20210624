package com.tw.banking;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class AccountTest {

    @Test
    public void should_transactionRepository_addDeposit_10_when_deposit_given_amount_is_10() {
        //given
        TransactionRepository transactionRepositoryMock = mock(TransactionRepository.class);
        Printer printerMock = mock(Printer.class);
        Account account = new Account(transactionRepositoryMock, printerMock);
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
}