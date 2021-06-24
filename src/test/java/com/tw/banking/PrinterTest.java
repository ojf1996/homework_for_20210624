package com.tw.banking;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;

import java.util.Arrays;
import java.util.List;

import static com.tw.banking.Printer.STATEMENT_HEADER;
import static org.mockito.Mockito.*;

class PrinterTest {

    @Test
    public void should_console_print_header_together_with_transaction_and_running_balance_in_descending_order_of_time_when_print_given_multiple_transaction() {
        //given
        Console consoleMock = mock(Console.class);
        Printer printer = new Printer(consoleMock);
        List<Transaction> transactions = Arrays.asList(new Transaction("01/04/2021", 10),
                new Transaction("01/02/2021", 2), new Transaction("01/05/2021", 5));
        InOrder inOrder = inOrder(consoleMock);
        //when
        printer.print(transactions);
        //then
        inOrder.verify(consoleMock, times(1)).printLine(STATEMENT_HEADER);
        inOrder.verify(consoleMock, times(1)).printLine("01/05/2021 | 5 | 17");
        inOrder.verify(consoleMock, times(1)).printLine("01/04/2021 | 10 | 12");
        inOrder.verify(consoleMock, times(1)).printLine("01/02/2021 | 2 | 2");
    }

    @Test
    public void should_overflow_when_print_given_total_running_amount_is_larger_than_2147483647() {
        //given
        Console consoleMock = mock(Console.class);
        Printer printer = new Printer(consoleMock);
        List<Transaction> transactions = Arrays.asList(new Transaction("01/04/2021", 1),
                new Transaction("01/02/2021", Integer.MAX_VALUE));
        InOrder inOrder = inOrder(consoleMock);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        //when
        printer.print(transactions);
        //then
        inOrder.verify(consoleMock, times(1)).printLine(STATEMENT_HEADER);
        inOrder.verify(consoleMock, times(1)).printLine("01/04/2021 | 1 | -2147483648");
        inOrder.verify(consoleMock, times(1)).printLine("01/02/2021 | 2147483647 | 2147483647");
    }
}