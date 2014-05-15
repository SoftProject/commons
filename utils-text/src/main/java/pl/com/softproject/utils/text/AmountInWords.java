/*
 * Copyright 2012-01-23 the original author or authors.
 */
package pl.com.softproject.utils.text;

/**
 *
 * @author Adrian Lapierre <adrian@softproject.com.pl>
 */
public class AmountInWords {

    // Tblica opisów wartości jednostek.
    static String[] units = {
        "zero", "jeden", "dwa", "trzy", "cztery", "pięć", "sześć",
        "siedem", "osiem", "dziewięć", "dziesięć", "jedenaście",
        "dwanaście", "trzynaście", "czternaście", "piętnaście",
        "szesnaście", "siedemnaście", "osiemnaście", "dziewiętnaście"
    };
    //Tablica opisów dziesiątek
    static String[] tens = {
        "dwadzieścia", "trzydzieści", "czterdzieści", "pięćdziesiąt",
        "sześćdziesiąt", "siedemdziesiąt", "osiemdziesiąt",
        "dziewięćdziesiąt"
    };
    //Tablica obisów setek
    static String[] hundreds = {
        "", "sto", "dwieście", "trzysta", "czterysta", "pięćset",
        "sześćset", "siedemset", "osiemset", "dziewięćset"
    };
    //Dwuwymiarowa tablica tysięcy,milionów,miliarów ...
    static String[][] otherUnits = {
        {"tysiąc", "tysiące", "tysięcy"},
        {"milion", "miliony", "milionów"},
        {"miliard", "miliardy", "miliardów"}
    };

    //Konwersja małych liczb ....
    private static String smallValueToWords(int n) {
        if (n == 0) {
            return null;
        }

        StringBuilder valueInWords = new StringBuilder();

        // Konwertuj setki.

        int temp = n / 100;
        if (temp > 0) {
            valueInWords.append(hundreds[temp]);
            n -= temp * 100;
        }

        // Konwertuj dziesiątki i jedności.

        if (n > 0) {
            if (valueInWords.length() > 0) {
                valueInWords.append(" ");
            }

            if (n < 20) {
                //  Liczby poniżej 20 przekonwertuj na podstawie
                //  tablicy jedności.

                valueInWords.append(units[n]);
            } else {
                //  Większe liczby przekonwertuj łącząc nazwy
                //  krotności dziesiątek z nazwami jedności.
                valueInWords.append(tens[(n / 10) - 2]);
                int lastDigit = n % 10;

                if (lastDigit > 0) {
                    valueInWords.append(" ");
                    valueInWords.append(units[lastDigit]);
                }
            }
        }
        return valueInWords.toString();
    }

    //Obliczenia dla dużych liczb ... i odmiana prawidłowa ich wartości..
    private static int GetBigUnitIndex(long n) {
        int lastDigit = (int) n % 10;

        if ((n >= 10 && (n <= 20 || lastDigit == 0))
                || (lastDigit > 4)) {
            return 2;
        }
        return (lastDigit == 1) ? 0 : 1;
    }

    private static long processNumericValue(StringBuilder valueInWords, long n, int level) {
        int smallValue = 0;
        //long divisor = (long)Math.pow(10000, (long)level + 1);
        long divisor = (long) Math.pow(1000, (long) level + 1);

        if (divisor <= n) {
            //  Jeśli liczbę da się podzielić przez najbliższą
            //  potęgę 1000, kontynuuj rekurencję.

            n = processNumericValue(valueInWords, n, level + 1);
            smallValue = (int) (n / divisor);

            if (valueInWords.length() > 0) {
                valueInWords.append(" ");
            }

            if (smallValue > 1) {
                valueInWords.append(smallValueToWords(smallValue));
                valueInWords.append(" ");
            }
            valueInWords.append(otherUnits[level][GetBigUnitIndex(smallValue)]);
        }

        return n - smallValue * divisor;
    }

    private static String toWords(long value) {
        if (value == 0) {
            // Zero.
            return units[0];
        }
        StringBuilder valueInWords = new StringBuilder();
        long smallValue = processNumericValue(valueInWords, value, 0);
        if (smallValue > 0) {
            if (valueInWords.length() > 0) {
                valueInWords.append(" ");
            }
            valueInWords.append(smallValueToWords((int) smallValue));
        }
        return valueInWords.toString();
    }

    static long liczba_zlotych(double kwota) {
        Double result = Math.floor(kwota);
        return result.longValue();
    }

    static long liczba_groszy(double kwota) {
        Double groszy = (kwota * 100 - liczba_zlotych(kwota) * 100);
        return groszy.longValue();
    }

    public static String toWords(double kwota) {

        if (kwota < 0) {
            kwota = kwota * -1;
        }
        String strKwotaSl;
        strKwotaSl = toWords(liczba_zlotych(kwota)) + " zł " + toWords(liczba_groszy(kwota)) + " gr";
        return strKwotaSl;
    }
    
    public static String toWords(double kwota, String currency) {

        if (kwota < 0) {
            kwota = kwota * -1;
        }
        String strKwotaSl;
        strKwotaSl = toWords(liczba_zlotych(kwota)) + " " + currency + " " + liczba_groszy(kwota) + "/100";
        return strKwotaSl;
    }
}
