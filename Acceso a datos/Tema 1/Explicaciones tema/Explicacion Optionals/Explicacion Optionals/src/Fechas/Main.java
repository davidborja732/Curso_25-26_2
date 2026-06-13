package Fechas;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        // Fechas
        Calendar calendar=Calendar.getInstance();
        calendar.set(2022,Calendar.JANUARY,12);

        LocalDate date=LocalDate.of(2022, Month.JANUARY,12);
        // Horas
        LocalTime hora=LocalTime.of(20,12);
        // Fecha y Hora
        LocalDateTime fechahora=LocalDateTime.of(2022,Month.DECEMBER,12,22,30);
        LocalDateTime fechahora2=LocalDateTime.of(date,hora);//Uso las horas y fechas ya creadas
        // calculo hace un sema
        LocalDateTime semanapasada=fechahora2.minusDays(7);
        System.out.println(semanapasada);

    }
}
