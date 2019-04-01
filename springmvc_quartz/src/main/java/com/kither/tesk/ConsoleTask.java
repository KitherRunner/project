package com.kither.tesk;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConsoleTask {

    public void printTime() {
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }
}
