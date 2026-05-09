package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public class Data {
    private static final Faker faker = new Faker(new Locale("en"));
    private static final DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM");
    private static final DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yy");

    @Value
    public static class CardData {
        String number;
        String month;
        String year;
        String holder;
        String cvv;
    }

    // Генерация валидной карты (одобрена)
    public static CardData getApprovedCard() {
        return new CardData(
                generateApprovedCardNumber(),
                generateValidMonth(),
                generateValidYear(),
                generateValidHolder(),
                generateValidCvv()
        );
    }

    // Генерация отклонённой карты
    public static CardData getDeclinedCard() {
        return new CardData(
                "4444 4444 4444 4442",
                generateValidMonth(),
                generateValidYear(),
                generateValidHolder(),
                generateValidCvv()
        );
    }

    // --- Номер карты ---
    public static CardData getInvalidCardNumberIfEmpty() {
        return new CardData("", generateValidMonth(), generateValidYear(), generateValidHolder(), generateValidCvv());
    }

    public static CardData getInvalidCardNumberIfLess16Sym() {
        return new CardData(faker.numerify("#### #### #### #"), generateValidMonth(), generateValidYear(), generateValidHolder(), generateValidCvv());
    }

    public static CardData getInvalidCardNumberIfOutOfBase() {
        return new CardData("5469 4444 4444 4441", generateValidMonth(), generateValidYear(), generateValidHolder(), generateValidCvv());
    }

    // --- Месяц ---
    public static CardData getInvalidNumberOfMonthIfEmpty() {
        return new CardData(generateValidCardNumber(), "", generateValidYear(), generateValidHolder(), generateValidCvv());
    }

    public static CardData getInvalidNumberOfMonthIfOneSym() {
        return new CardData(generateValidCardNumber(), String.valueOf(ThreadLocalRandom.current().nextInt(1, 10)), generateValidYear(), generateValidHolder(), generateValidCvv());
    }

    public static CardData getInvalidNumberOfMonthIfMore12() {
        return new CardData(generateValidCardNumber(), String.valueOf(ThreadLocalRandom.current().nextInt(13, 99)), generateValidYear(), generateValidHolder(), generateValidCvv());
    }

    public static CardData getInvalidNumberOfMonthIfZero() {
        return new CardData(generateValidCardNumber(), "00", generateValidYear(), generateValidHolder(), generateValidCvv());
    }

    // --- Год ---
    public static CardData getInvalidYearIfEmpty() {
        return new CardData(generateValidCardNumber(), generateValidMonth(), "", generateValidHolder(), generateValidCvv());
    }

    public static CardData getInvalidYearIfOneSym() {
        return new CardData(generateValidCardNumber(), generateValidMonth(), String.valueOf(ThreadLocalRandom.current().nextInt(0, 10)), generateValidHolder(), generateValidCvv());
    }

    public static CardData getInvalidYearIfBeforeCurrentYear() {
        int pastYear = LocalDate.now().minusYears(ThreadLocalRandom.current().nextInt(1, 10)).getYear() % 100;
        return new CardData(generateValidCardNumber(), generateValidMonth(), String.format("%02d", pastYear), generateValidHolder(), generateValidCvv());
    }

    public static CardData getInvalidYearIfZero() {
        return new CardData(generateValidCardNumber(), generateValidMonth(), "00", generateValidHolder(), generateValidCvv());
    }

    public static CardData getInvalidYearIfInTheFarFuture() {
        int farFutureYear = LocalDate.now().plusYears(ThreadLocalRandom.current().nextInt(10, 30)).getYear() % 100;
        return new CardData(generateValidCardNumber(), generateValidMonth(), String.format("%02d", farFutureYear), generateValidHolder(), generateValidCvv());
    }

    // --- Владелец ---
    public static CardData getInvalidCardholderNameIfEmpty() {
        return new CardData(generateValidCardNumber(), generateValidMonth(), generateValidYear(), "", generateValidCvv());
    }

    public static CardData getInvalidCardholderNameIfOneWord() {
        return new CardData(generateValidCardNumber(), generateValidMonth(), generateValidYear(), faker.name().firstName(), generateValidCvv());
    }

    public static CardData getInvalidCardholderNameIfThreeWords() {
        return new CardData(generateValidCardNumber(), generateValidMonth(), generateValidYear(), faker.name().firstName() + " " + faker.name().lastName() + " " + faker.name().firstName(), generateValidCvv());
    }

    public static CardData getInvalidCardholderNameIfRusSym() {
        Faker rusFaker = new Faker(new Locale("ru"));
        return new CardData(generateValidCardNumber(), generateValidMonth(), generateValidYear(), rusFaker.name().fullName(), generateValidCvv());
    }

    public static CardData getInvalidCardholderNameIfNumeric() {
        return new CardData(generateValidCardNumber(), generateValidMonth(), generateValidYear(), faker.numerify("#### ####"), generateValidCvv());
    }

    public static CardData getInvalidCardholderNameIfWildcard() {
        return new CardData(generateValidCardNumber(), generateValidMonth(), generateValidYear(), "#%№@!&*", generateValidCvv());
    }

    // --- CVV ---
    public static CardData getInvalidCvvIfEmpty() {
        return new CardData(generateValidCardNumber(), generateValidMonth(), generateValidYear(), generateValidHolder(), "");
    }

    public static CardData getInvalidCvvIfOneSym() {
        return new CardData(generateValidCardNumber(), generateValidMonth(), generateValidYear(), generateValidHolder(), String.valueOf(ThreadLocalRandom.current().nextInt(0, 10)));
    }

    public static CardData getInvalidCvvIfTwoSym() {
        return new CardData(generateValidCardNumber(), generateValidMonth(), generateValidYear(), generateValidHolder(), String.format("%02d", ThreadLocalRandom.current().nextInt(0, 100)));
    }

    public static CardData getInvalidCvvIfThreeZero() {
        return new CardData(generateValidCardNumber(), generateValidMonth(), generateValidYear(), generateValidHolder(), "000");
    }

    public static CardData getInvalidCardDataIfEmptyAllFields() {
        return new CardData("", "", "", "", "");
    }

    // --- Вспомогательные генераторы ---
    private static String generateValidCardNumber() {
        // Генерация случайного номера карты (16 цифр) с префиксом 4444 для совместимости с approved/declined логикой
        return "4444 " + faker.numerify("#### #### ####");
    }

    private static String generateApprovedCardNumber() {
        return "4444 4444 4444 4441";
    }

    private static String generateValidMonth() {
        return LocalDate.now().plusMonths(ThreadLocalRandom.current().nextInt(1, 12)).format(monthFormatter);
    }

    private static String generateValidYear() {
        return LocalDate.now().plusYears(ThreadLocalRandom.current().nextInt(1, 5)).format(yearFormatter);
    }

    private static String generateValidHolder() {
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    private static String generateValidCvv() {
        return faker.numerify("###");
    }
}