package yksikkotestaus;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DayOfYearTest {

	@Test
	public void testFirstAndLastDayOfYear() {
		// 1.1.2019
		int result = DayOfYear.dayOfYear(1, 1, 2019);	
		assertEquals(1, result);
		
		// 31.12.2019
		result = DayOfYear.dayOfYear(12, 31, 2019);
		assertEquals(365, result);
	}
	
	@Test
	public void testLastDayOfLeapYear() {
		// 31.12.2020
		int result = DayOfYear.dayOfYear(12, 31, 2020);
		
		assertEquals(366, result);
	}

	@Test
	public void testDateMarch() {
		// 1.3.2019
		int result = DayOfYear.dayOfYear(3, 1, 2019);
		
		assertEquals(60, result);
	}

	@Test
	public void testDateMarchWithLeapYear() {
		// 1.3.2020
		int result = DayOfYear.dayOfYear(3, 1, 2020);
		
		assertEquals(61, result);
	}
	
	@Test
	public void testDateFebruary() {
		// 28.2.2019
		int result = DayOfYear.dayOfYear(2, 28, 2019);
		
		assertEquals(59, result);
	}
	
	@Test
	public void testInvalidMonth() {
		String expectedMessage = "Month has to be between 1 - 12.";
		
		// Testataan yli sallitun rajan annettu kuukausi
		Exception overLimit = assertThrows(IllegalArgumentException.class, () -> {
			DayOfYear.dayOfYear(13, 1, 2019);
		});
		  
	    assertTrue(overLimit.getMessage().contains(expectedMessage));
		
	    // Testataan alle sallitun rajan annettu kuukausi
		Exception underLimit = assertThrows(IllegalArgumentException.class, () -> {
			DayOfYear.dayOfYear(0, 1, 2019);
		});
		
		assertTrue(underLimit.getMessage().contains(expectedMessage));
	}
	
	@Test
	public void testInvalidDay() {
		String expectedMessage = "Day has to be between 1 - 31.";
		
		// Testataan yli sallitun rajan annettu päivä
		Exception overLimit = assertThrows(IllegalArgumentException.class, () -> {
			DayOfYear.dayOfYear(12, 32, 2019);
		});
		
		assertTrue(overLimit.getMessage().contains(expectedMessage));
		
		// Testataan alle sallitun rajan annettu päivä
		Exception underLimit = assertThrows(IllegalArgumentException.class, () -> {
			DayOfYear.dayOfYear(12, 0, 2019);
		});
		
		assertTrue(underLimit.getMessage().contains(expectedMessage));
	}
	
	@Test
	public void testInvalidYear() {
		Exception e = assertThrows(IllegalArgumentException.class, () -> {
			DayOfYear.dayOfYear(1, 1, 0);
		});
		
		String expectedMessage = "Year has to be more than 0.";
		String actualMessage = e.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
		
	}
	
	
}
