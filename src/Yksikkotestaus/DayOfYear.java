package Yksikkotestaus;

import java.time.*;

/**
 * Smelly Example #1
 * 
 * https://web.mit.edu/6.005/www/fa16/classes/04-code-review/
 * 
 * Collaboratively authored with contributions from: Saman Amarasinghe, Adam
 * Chlipala, Srini Devadas, Michael Ernst, Max Goldman, John Guttag, Daniel
 * Jackson, Rob Miller, Martin Rinard, and Armando Solar-Lezama.
 * 
 * This work is licensed under CC BY-SA 4.0.
 */
public class DayOfYear {

    /**
     * Tämä konstruktori on merkitty yksityiseksi, koska luokka on "utility class",
     * eli se sisältää ainoastaan staattisia metodeja. Tästä luokasta ei ole
     * sallittua luoda olioita, vaan metodeja tulee kutsua luokan kautta.
     * 
     * Katso esim:
     * 
     * @see http://www.javapractices.com/topic/TopicAction.do?Id=40
     * @see https://rules.sonarsource.com/java/tag/design/RSPEC-1118
     */
    private DayOfYear() {  }
    
    public static void main(String[] args) {
		System.out.println("Testi: " + dayOfYear(3, 1, 2019));
		System.out.println("Testi: " + dayOfYear(3, 1, 2020));
		System.out.println("Testi: " + dayOfYear(1, 12, 2020));
	}
    
    /**
     * 
     * @param month
     * @param dayOfMonth
     * @param year
     * @return dayOfYear
     */
    
    public static int dayOfYear(int month, int dayOfMonth, int year) {
    	
    	/*
    	 * Aluksi sanitoidaan annetut arvot
    	 */
    	
    	if (month <= 0 || month > 12) throw new IllegalArgumentException("Month has to be between 1 - 12.");
    	
    	if (dayOfMonth <= 0 || dayOfMonth > 31) throw new IllegalArgumentException("Day has to be between 1 - 31.");
    	
    	if (year <= 0) throw new IllegalArgumentException("Year has to be more than 0.");
    	
    	// Helpoin tietämäni tapa saman tuloksen saavuttamiselle.
    	// Näin itse toteuttaisin kyseisen asian.
    	
//    	LocalDate date = LocalDate.of(year, month, dayOfMonth);
//    	int dayOfYear = date.getDayOfYear();
//    	return dayOfYear; 
    	
    	
    	// Toinen tapa.
    	// Testit menee hyväksytysti läpi.
    	
//    	int dayOfYear = dayOfMonth;
//    	Year givenYear = Year.of(year);
//
//	   	for (int i = 1; i < month; i++) {
//			dayOfYear += Month.of(i).length(givenYear.isLeap());
//	   	}
//	   	
//    	return dayOfYear;
    	
    	// Alkuperäisestä koodista "paranneltu" versio.
    	// Testit menevät läpi.
    	
    	int dayOfYear = dayOfMonth;
    	Year givenYear = Year.of(year);
    	
    	int[] monthDays = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    	
    	if (givenYear.isLeap()) monthDays[1] = 29;
    	
    	for (int i = 1; i < month; i++) {
			dayOfYear += monthDays[i - 1];
		}
    	return dayOfYear;
    	
  
      /*
       * Alkuperäinen koodi bugien korjauksella ja karkausvuoden huomioonottamisella
       * Testit menevät myös tällä läpi.
       */
    
//    	int dayOfYear = dayOfMonth;
//    	
//        if (month == 2) {
//            dayOfYear += 31;
//        } else if (month == 3) {
//            dayOfYear += 31 + 28;
//        } else if (month == 4) {
//            dayOfYear += 31 + 28 + 31;
//        } else if (month == 5) {
//            dayOfYear += 31 + 28 + 31 + 30;
//        } else if (month == 6) {
//            dayOfYear += 31 + 28 + 31 + 30 + 31;
//        } else if (month == 7) {
//            dayOfYear += 31 + 28 + 31 + 30 + 31 + 30;
//        } else if (month == 8) {
//            dayOfYear += 31 + 28 + 31 + 30 + 31 + 30 + 31;
//        } else if (month == 9) {
//            dayOfYear += 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31;
//        } else if (month == 10) {
//            dayOfYear += 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30;
//        } else if (month == 11) {
//            dayOfYear += 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31;
//        } else if (month == 12) {
//            dayOfYear += 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31 + 30;
//        }
//        
//        if (Year.isLeap(year) && month > 2) {
//        	dayOfYear += 1;
//        }    
//        return dayOfYear;
    }
}