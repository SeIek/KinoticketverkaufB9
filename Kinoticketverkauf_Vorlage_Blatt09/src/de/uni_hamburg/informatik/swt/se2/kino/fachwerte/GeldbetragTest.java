package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import static org.junit.Assert.*;

//import org.junit.Rule;
//import org.junit.rules.ExpectedException;

import org.junit.Test;

public class GeldbetragTest
{
    Geldbetrag _betrag100;
    Geldbetrag _betrag250;
    Geldbetrag _betrag500a;
    Geldbetrag _betrag500b;
    Geldbetrag _betrag400;
    Geldbetrag _betrag1000;
    Geldbetrag _betragMinus100;
    Geldbetrag _betragMinus250;
    Geldbetrag _betragMinus400;
    Geldbetrag _betragMinus500;
    Geldbetrag _riesigeZahl;

    public GeldbetragTest()
    {
        _betrag100 = Geldbetrag.getGeldbetrag(100);
        _betrag250 = Geldbetrag.getGeldbetrag(250);
        _betrag400 = Geldbetrag.getGeldbetrag(400);
        _betrag500a = Geldbetrag.getGeldbetrag(500);
        _betrag500b = Geldbetrag.getGeldbetrag(500);
        _betrag1000 = Geldbetrag.getGeldbetrag(1000);
        _betragMinus100 = Geldbetrag.getGeldbetrag(-100);
        _betragMinus250 = Geldbetrag.getGeldbetrag(-250);
        _betragMinus400 = Geldbetrag.getGeldbetrag(-400);
        _betragMinus500 = Geldbetrag.getGeldbetrag(-500);
        _riesigeZahl = Geldbetrag.getGeldbetrag(Integer.MAX_VALUE);
    }

    @Test
    public void testeEqualsundHash()
    {
        assertEquals(_betrag500a.hashCode(), _betrag500b.hashCode());
        assertEquals(_betrag500a.hashCode(), _betrag500a.hashCode());
        assertEquals(_betrag500a, _betrag500b);
        assertFalse(_betrag500a.equals(_betrag100));
    }

    @Test
    public void testeAddieren()
    {
        assertEquals(_betrag1000, Geldbetrag.addiere(_betrag500a, _betrag500b));
        assertEquals(_betrag1000, Geldbetrag.addiere(_betrag500a, _betrag500a));
        assertEquals(_betrag500a, Geldbetrag.addiere(_betrag250, _betrag250));
        assertEquals(_betrag250, Geldbetrag.addiere(_betragMinus250, _betrag500a));
        assertEquals(_betrag400,
                Geldbetrag.addiere(_betrag500a, _betragMinus100));

    }

    @Test
    public void testeSubtrahieren()
    {
        assertEquals(_betrag400,
                Geldbetrag.subtrahiere(_betrag500a, _betrag100));
        assertEquals(_betrag250,
                Geldbetrag.subtrahiere(_betrag500a, _betrag250));
        assertEquals(_betragMinus250,
                Geldbetrag.subtrahiere(_betrag250, _betrag500a));
        assertEquals(_betrag1000,
                Geldbetrag.subtrahiere(_betrag500a, _betragMinus500));
        assertEquals(_betragMinus400,
                Geldbetrag.subtrahiere(_betrag100, _betrag500a));

    }

    @Test
    public void testeMultiplizieren()
    {
        assertEquals(_betrag500a, Geldbetrag.multipliziere(_betrag100, 5));
        assertEquals(_betrag500a, Geldbetrag.multipliziere(_betrag250, 2));
        assertEquals(_betragMinus500, Geldbetrag.multipliziere(_betrag100, -5));
        assertEquals(_betragMinus250, Geldbetrag.multipliziere(_betrag250, -1));
        assertEquals(_betragMinus500,
                Geldbetrag.multipliziere(_betragMinus100, 5));
    }

    @Test
    public void testeKonvertiereToGeldbetrag()
    {
        assertTrue(Geldbetrag.istGeldbetrag("1,00"));
        assertTrue(Geldbetrag.istGeldbetrag("42,42"));
        assertTrue(Geldbetrag.istGeldbetrag("1"));
        
        
        assertFalse(Geldbetrag.istGeldbetrag("1,0"));
        assertFalse(Geldbetrag.istGeldbetrag("1,000"));
        assertFalse(Geldbetrag.istGeldbetrag("a"));
        assertFalse(Geldbetrag.istGeldbetrag("1.00"));

        assertEquals(_betrag100, Geldbetrag.getGeldbetrag("1,00"));
        assertEquals(_betrag100, Geldbetrag.getGeldbetrag("1"));
        assertEquals(_betragMinus100, Geldbetrag.getGeldbetrag("-1,00"));
        assertEquals(_betragMinus100, Geldbetrag.getGeldbetrag("-1"));
    }

    @Test
    public void testeIstPositiv()
    {
        assertTrue(_betrag500a.istPositiv());
        assertFalse(_betragMinus100.istPositiv());
    }

    @Test
    public void testeGetter()
    {
        assertEquals(100, _betrag100.getEurocent());
        assertEquals(1, _betrag100.getEuroAnteil());
        assertEquals(0, _betrag100.getCentAnteil());
        
        assertEquals(-100, _betragMinus100.getEurocent());
        assertEquals(-1, _betragMinus100.getEuroAnteil());
        assertEquals(0, _betragMinus100.getCentAnteil());
        
        assertEquals(250, _betrag250.getEurocent());
        assertEquals(2, _betrag250.getEuroAnteil());
        assertEquals(50, _betrag250.getCentAnteil());
        
        // Lässt sich hier drüber streiten was denn erwartet wird?!
        assertEquals(-250, _betragMinus250.getEurocent());
        assertEquals(-2, _betragMinus250.getEuroAnteil());
        assertEquals(-50, _betragMinus250.getCentAnteil());

        // Hier scheint es dagegen eher klar zu werden
        Geldbetrag m012 = Geldbetrag.getGeldbetrag(-12);
        assertEquals(-12, m012.getEurocent());
        assertEquals(0, m012.getEuroAnteil());
        assertEquals(-12, m012.getCentAnteil());
    }
    
    @Test
    public void testeToString()
    {
        assertEquals("1,00", _betrag100.toString());
        assertEquals("-1,00", _betragMinus100.toString());
        assertEquals("10,00", _betrag1000.toString());
        assertEquals("2,50", _betrag250.toString());
        assertEquals("-2,50", _betragMinus250.toString());
        assertEquals("0,12", Geldbetrag.getGeldbetrag(12).toString());
        assertEquals("-0,12", Geldbetrag.getGeldbetrag(-12).toString());
        assertEquals("1,01", Geldbetrag.getGeldbetrag(101).toString());
        assertEquals("-1,01", Geldbetrag.getGeldbetrag(-101).toString());
        assertEquals("123456,78", Geldbetrag.getGeldbetrag(12345678).toString());
    }
    
    @Test
    public void testeGeldbetragString()
    {
        assertFalse(Geldbetrag.istGeldbetrag(""));
        assertFalse(Geldbetrag.istGeldbetrag("quatsch"));
        assertFalse(Geldbetrag.istGeldbetrag("123 quatsch"));
        assertFalse(Geldbetrag.istGeldbetrag("quatsch 456"));
        assertTrue(Geldbetrag.istGeldbetrag("0"));
        assertTrue(Geldbetrag.istGeldbetrag("0,00"));
        assertTrue(Geldbetrag.istGeldbetrag("1234"));
        assertTrue(Geldbetrag.istGeldbetrag("5678,99"));
        assertTrue(Geldbetrag.istGeldbetrag("-1234"));
        assertTrue(Geldbetrag.istGeldbetrag("-5678,99"));
    }

    @Test
    public void testeGeldbetragStringZuGross()
    {
        // Mehr als MAX_INT kann schon mal gar nicht gehen
        assertFalse(Geldbetrag.istGeldbetrag("2147483648"));
        assertFalse(Geldbetrag.istGeldbetrag("3147483647"));
        assertFalse(Geldbetrag.istGeldbetrag("2147483648,00"));
        assertFalse(Geldbetrag.istGeldbetrag("3147483647,00"));

        // Zahlwert in Euro, gespeichert werden aber Cent
        assertFalse(Geldbetrag.istGeldbetrag("21474837"));
        assertFalse(Geldbetrag.istGeldbetrag("31474836"));
        assertFalse(Geldbetrag.istGeldbetrag("21474837,00"));
        assertFalse(Geldbetrag.istGeldbetrag("31474836,00"));
    }

//  Falls wir auf Exceptions testen wollen
//
//    @Rule
//    public ExpectedException exception = ExpectedException.none();
//    
//    @Test
//    public void testeOverflow()
//    {
//        exception.expect(IllegalArgumentException.class);
//        [Hier einfügen, was zu einer ArithmeticException führt, also Integer overflow]
//
//    }
}
