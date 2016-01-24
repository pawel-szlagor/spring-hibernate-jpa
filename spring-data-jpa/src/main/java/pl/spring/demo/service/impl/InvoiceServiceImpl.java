package pl.spring.demo.service.impl;

import org.springframework.stereotype.Service;
import pl.spring.demo.service.InvoiceService;

/**
 * Created by Paweł on 24.01.2016.
 */
@Service
public class InvoiceServiceImpl implements InvoiceService {
    private static final String[] CURRENCY = {"złote", "złotych"};

    @Override
    public String amountInWords(double amount) {
        return translacja((long) amount) + prefix(amount);
    }

    private String prefix(double amount) {
        long amountRounded = Math.round(amount * 100);
        StringBuilder builder = new StringBuilder();
        if (amountRounded / 100 % 10 == 2 || amountRounded / 100 % 10 == 3 || amountRounded / 100 % 10 == 4) {
            builder.append(CURRENCY[0]).append(", ");
        } else {
            builder.append(CURRENCY[1]).append(", ");
        }
        if (Math.round((amount * 100) % 100) == 0) {
            builder.append("00");
        } else {
            builder.append(Math.round((amount * 100) % 100));
        }
        builder.append("/100");
        return builder.toString();
    }

    private String translacja(long liczba) {
        String[] jedności = {"", "jeden ", "dwa ", "trzy ", "cztery ",
                "pięć ", "sześć ", "siedem ", "osiem ", "dziewięć ",};

        String[] nastki = {"", "jedenaście ", "dwanaście ", "trzynaście ",
                "czternaście ", "piętnaście ", "szesnaście ", "siedemnaście ",
                "osiemnaście ", "dziewiętnaście ",};

        String[] dziesiątki = {"", "dziesięć ", "dwadzieścia ",
                "trzydzieści ", "czterdzieści ", "pięćdziesiąt ",
                "sześćdziesiąt ", "siedemdziesiąt ", "osiemdziesiąt ",
                "dziewięćdziesiąt ",};

        String[] setki = {"", "sto ", "dwieście ", "trzysta ", "czterysta ",
                "pięćset ", "sześćset ", "siedemset ", "osiemset ",
                "dziewięćset ",};

        String[][] grupy = {{"", "", ""},
                {"tysiąc ", "tysiące ", "tysięcy "},
                {"milion ", "miliony ", "milionów "},
                {"miliard ", "miliardy ", "miliardów "},
                {"bilion ", "biliony ", "bilionów "},
                {"biliard ", "biliardy ", "biliardów "},
                {"trylion ", "tryliony ", "trylionów "},};

// INICJACJA ZMIENNYCH
        long j = 0 /* jedności */, n = 0/* nastki */, d = 0/* dziesiątki */, s = 0/* setki */, g = 0/* grupy */, k = 0/* końcówwki */;
        String słownie = "";
        String znak = "";

// OPERACJA DOTYCZąCA ZNAKU

        if (liczba < 0) {
            znak = "minus ";
            liczba = -liczba; // bezwględna wartość ponieważ, jeśli będziemy
// operować na liczbie z minusem tablica będzie
// przyjmowała wartości ujemne i zwróci nam błąd
        }
        if (liczba == 0) {
            znak = "zero ";
        }

// PĘTLA GŁÓWNA
        while (liczba != 0) {
            s = liczba % 1000 / 100;
            d = liczba % 100 / 10;
            j = liczba % 10;

            if (d == 1 & j > 0) // if zajmujący się nastkami
            {
                n = j;
                d = 0;
                j = 0;
            } else {
                n = 0;
            }

// <---- KOŃCÓWKI

            if (j == 1 & s + d + n == 0) {
                k = 0;

                if (s + d == 0 && g > 0) // jeśli nie będzie dziesiątek ani setek, wtedy otrzymamy samą grupę
                { // przykładowo 1000 - wyświetli nam się "tysiąc", jeśli
// zakomentujemy tego if'a to otrzymamy "jeden tysiąc"
                    j = 0;
                    słownie = grupy[(int) g][(int) k] + słownie;
                }
            } else if (j == 2) {
                k = 1;
            } else if (j == 3) {
                k = 1;
            } else if (j == 4) {
                k = 1;
            } else {
                k = 2;
            }

// KONIEC KOŃCÓWEK -->

            if (s + d + n + j > 0) {
                słownie = setki[(int) s] + dziesiątki[(int) d] + nastki[(int) n]
                        + jedności[(int) j] + grupy[(int) g][(int) k] + słownie;
            }

// POZBYWAMY SIĘ TYCH LICZBY KTÓRE JUŻ PRZEROBILIŚMY czyli
// przykładowo z 132132 zostaje nam 132 do obróbki
            liczba = liczba / 1000;
// ORAZ ZWIĘKSZAMY G KTÓRE ODPOWIEDZIALNE JEST ZA NUMER POLA W
// TABLICY WIELOWYMIAROWEJ
            g = g + 1;
        }
// KONIEC PĘTLI GŁÓWNEJ

// DODANIE ZNAKU I ZWRÓCENIE METODY
        słownie = znak + słownie;
        return słownie;

    }
}
