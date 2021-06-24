package com.tw.banking;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TransactionRepositoryTest {

    @Test
    public void should_add_transaction_with_date_01012021_amount_10_to_transactions_when_addDeposit_given_today_is_01012021_and_amount_is_10() {
        //given
        Clock clockMock = mock(Clock.class);
        TransactionRepository transactionRepository = new TransactionRepository(clockMock);
        when(clockMock.todayAsString()).thenReturn("01/01/2021");
        //when
        transactionRepository.addDeposit(10);
        //then
        assertEquals(1, transactionRepository.transactions.size());
        assertEquals("01/01/2021", transactionRepository.transactions.get(0).date());
        assertEquals(10, transactionRepository.transactions.get(0).amount());
    }

    @Test
    public void should_add_transaction_with_date_01012021_amount_is_minus_10_to_transactions_when_addDeposit_given_today_is_01012021_and_amount_is_10() {
        //given
        Clock clockMock = mock(Clock.class);
        TransactionRepository transactionRepository = new TransactionRepository(clockMock);
        when(clockMock.todayAsString()).thenReturn("01/01/2021");
        //when
        transactionRepository.addWithdraw(10);
        //then
        assertEquals(1, transactionRepository.transactions.size());
        assertEquals("01/01/2021", transactionRepository.transactions.get(0).date());
        assertEquals(-10, transactionRepository.transactions.get(0).amount());
    }
}