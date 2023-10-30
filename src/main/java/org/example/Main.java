package org.example;

import java.util.Scanner;

class DaysDifferenceCalculator {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("\n**********  Datum vaseg rodjenja  **********");
        System.out.print("Unesite dan :");
        int d1Day = Integer.parseInt(scan.nextLine());
        System.out.print("Unesite mesec :");
        int d1Month = Integer.parseInt(scan.nextLine());
        System.out.print("Unesite godinu :");
        int d1Year = Integer.parseInt(scan.nextLine());

        System.out.println("\n**********  Trenutni datum  **********");
        System.out.print("Unesite dan :");
        int d2Day = Integer.parseInt(scan.nextLine());
        System.out.print("Unesite mesec :");
        int d2Month = Integer.parseInt(scan.nextLine());
        System.out.print("Unesite godinu :");
        int d2Year = Integer.parseInt(scan.nextLine());

        int difference = 0;

        difference = getDaysInBetweenDates(d1Year, d1Month, d1Day, d2Year, d2Month, d2Day);

        System.out.println("\nVi ste stari tacno : " + difference + " dana");

    }

    public static int getDaysInBetweenDates(int d1Year, int d1Month, int d1Day, int d2Year, int d2Month, int d2Day) {
        int differenceDays = 0;

        if (d1Year == d2Year) {
            differenceDays = getDaysForThisYear(d1Day, d1Month, d1Year, d2Day, d2Month, isLeapYear(d1Year), false);

        } else {
            int tempYear = d1Year;

            while (tempYear < d2Year) {
                if (tempYear == d1Year) {
                    differenceDays += getDaysForThisYear(d1Day, d1Month, tempYear, 31, 12, isLeapYear(tempYear), false);
                } else {
                    differenceDays += isLeapYear(tempYear) ? 366 : 365;
                }
                tempYear = tempYear + 1;
            }
            differenceDays += getDaysForThisYear(1, 1, d2Year, d2Day, d2Month, isLeapYear(d2Year), true);
        }

        return differenceDays;
    }

    public static int getDaysForThisYear(int startDay, int startMonth, int thisYear, int lastDay, int lastMonth,
                                         boolean isLeapYear, boolean isLastYear) {

        int daysThisYear = 0;

        if (startMonth == lastMonth) {
            daysThisYear = lastDay - startDay;

        } else {

            int tempMonth = startMonth;
            while (tempMonth < lastMonth) {
                daysThisYear += getDaysOfMonth(tempMonth, isLeapYear);
                tempMonth += 1;
            }

            if (isLastYear) {
                daysThisYear += lastDay;
            } else {
                daysThisYear += lastDay - 1;
            }
        }

        return daysThisYear;
    }

    public static int getDaysOfMonth(int month, boolean isLeapYear) {
        if (month == 2) {
            if (isLeapYear)
                return 29;
            return 28;
        } else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            return 31;
        } else {
            return 30;
        }
    }

    public static boolean isLeapYear(int year) {

        if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))) {
            return true;
        }

        return false;
    }
}