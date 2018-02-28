/*	
 * Ivari
 * (Intervallarithmetik
 * f�r MultiVaLCA)
 */

package de.unistuttgart.iwb.ivaritest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import de.unistuttgart.iwb.ivari.*;

/**
 * @author Dr.-Ing. Joachim Schwarte
 * @version 0.03
 */

class IvariTest {
	IvariScalar a = new IvariScalar(2.4, 3.6);
	IvariScalar b = new IvariScalar(4.1, 5.2);
	IvariScalar c = new IvariScalar(-1.1, 1.2);
	IvariScalar d = new IvariScalar(-3.5, -1.5);
	IvariScalar e = new IvariScalar(1., 2.);
	IvariVector v = new IvariVector(2);	
	IvariMatrix m = new IvariMatrix(2);	

	@Test
	void testAdd() {
		assertEquals(a.add(b).getLowerBound(), 6.5, .001);
		assertEquals(a.add(b).getUpperBound(), 8.8, .001);
		assertEquals(a.add(c).getLowerBound(), 1.3, .001);
		assertEquals(a.add(c).getUpperBound(), 4.8, .001);
		assertEquals(a.add(d).getLowerBound(), -1.1, .001);
		assertEquals(a.add(d).getUpperBound(), 2.1, .001);
	}
	
	@Test
	void testSub() {
		assertEquals(a.sub(b).getLowerBound(), -2.8, .001);
		assertEquals(a.sub(b).getUpperBound(), -0.5, .001);
		assertEquals(a.sub(c).getLowerBound(), 1.2, .001);
		assertEquals(a.sub(c).getUpperBound(), 4.7, .001);
		assertEquals(a.sub(d).getLowerBound(), 3.9, .001);
		assertEquals(a.sub(d).getUpperBound(), 7.1, .001);
	}
	
	@Test
	void testMult() {
		assertEquals(a.mult(b).getLowerBound(), 9.84, .001);
		assertEquals(a.mult(b).getUpperBound(), 18.72, .001);
		assertEquals(a.mult(c).getLowerBound(), -3.96, .001);
		assertEquals(a.mult(c).getUpperBound(), 4.32, .001);
		assertEquals(a.mult(d).getLowerBound(), -12.6, .001);
		assertEquals(a.mult(d).getUpperBound(), -3.6, .001);
	}
	
	@Test
	void testDiv() {
		try {
			assertEquals(a.div(b).getLowerBound(), .461538, .000001);
		} catch (Exception e) {
			assertTrue(e.getMessage().equals("?"));
		}
		try {
			assertEquals(a.div(b).getUpperBound(), .878048, .000001);
		} catch (Exception e) {
			assertTrue(e.getMessage().equals("?"));
		}
		try {
			assertEquals(a.div(c).getLowerBound(), -3.272727, .000001);
		} catch (Exception e) {
			assertTrue(e.getMessage().equals("Fehler bei Kehrwertberechnung"));
		}
		try {
			assertEquals(a.div(c).getUpperBound(), 3., .000001);
		} catch (Exception e) {
			assertTrue(e.getMessage().equals("Fehler bei Kehrwertberechnung"));
		}
		try {
			assertEquals(a.div(d).getLowerBound(), -2.4, .000001);
		} catch (Exception e) {
			assertTrue(e.getMessage().equals("?"));
		}
		try {
			assertEquals(a.div(d).getUpperBound(), -.685714, .000001);
		} catch (Exception e) {
			assertTrue(e.getMessage().equals("?"));
		}
	}
	
	@Test
	void testAbs() {
		try {
			assertEquals(a.abs2(), 8.64, .001);
		} catch (Exception e) {
			assertTrue(e.getMessage().equals("?"));
		}
		try {
			assertEquals(c.abs2(), -1.32, .001);
		} catch (Exception e) {
			assertTrue(e.getMessage().equals("Fehler bei der Absolutwertberechnung"));
		}
	}
	
	@Test
	void testVMult() {
		v.setValue(0, a);
		v.setValue(1, b);
		IvariVector w = v.mult(c);
		assertEquals(w.getValue(0).getLowerBound(), -3.96, .001);
		assertEquals(w.getValue(0).getUpperBound(), 4.32, .001);
		assertEquals(w.getValue(1).getLowerBound(), -5.72, .001);
		assertEquals(w.getValue(1).getUpperBound(), 6.24, .001);		
	}
	
	@Test
	void testMultLine() {
		m.setValue(0, 0, a);
		m.setValue(0, 1, b);
		m.setValue(1, 0, c);
		m.setValue(1, 1, d);
		IvariMatrix m1 = m.multLine(0, e);
		assertEquals(m.getValue(0, 0).getLowerBound(), 2.4, 0.001);
		assertEquals(m.getValue(0, 0).getUpperBound(), 3.6, 0.001);
		assertEquals(m.getValue(0, 1).getLowerBound(), 4.1, 0.001);
		assertEquals(m.getValue(0, 1).getUpperBound(), 5.2, 0.001);
		assertEquals(m1.getValue(0, 0).getLowerBound(), 2.4, 0.001);
		assertEquals(m1.getValue(0, 0).getUpperBound(), 7.2, 0.001);
		assertEquals(m1.getValue(0, 1).getLowerBound(), 4.1, 0.001);
		assertEquals(m1.getValue(0, 1).getUpperBound(), 10.4, 0.001);
		assertEquals(m1.getValue(1, 0).getLowerBound(), -1.1, 0.001);
		assertEquals(m1.getValue(1, 0).getUpperBound(), 1.2, 0.001);
		assertEquals(m1.getValue(1, 1).getLowerBound(), -3.5, 0.001);
		assertEquals(m1.getValue(1, 1).getUpperBound(), -1.5, 0.001);
		
	}



}
