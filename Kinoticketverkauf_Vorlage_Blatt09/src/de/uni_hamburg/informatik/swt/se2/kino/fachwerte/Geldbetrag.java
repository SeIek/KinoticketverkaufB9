package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

/**
 * Ein Geldbetrag, angegeben in Eurocent.
 * 
 * @author Lina Heinzke, SE2-Gruppe "No Pascha"
 * @version 05.07.2016
 * 
 */
public class Geldbetrag
{
    private final int _eurocent;

    private Geldbetrag(int eurocent)
    {
        _eurocent = eurocent;
    }

    /**
     * Wählt einen Geldbetrag aus.
     * 
     * @param eurocent Der Betrag in Eurocent
     * 
     * @ensure getEuroCent() == eurocent 
     */
    public static Geldbetrag getGeldbetrag(int eurocent)
    {
        return new Geldbetrag(eurocent);
    }

    /**
     * Wählt einen Geldbetrag aus.
     * 
     * @param euroString Der Betrag in Euro, ggf. als Kommazahl mit exakt zwei Nachkommastellen.
     * 
     * @require istGeldbetrag(euroString)
     */
    public static Geldbetrag getGeldbetrag(String euroString)
    {
        assert istGeldbetrag(euroString) : "Vorbedingung verletzt: istGeldbetrag(euroString)";

        int betrag = parseBetrag(euroString);

        return Geldbetrag.getGeldbetrag(betrag);
    }

    /**
     * Prüft, ob der String ein gültiges Format hat. 
     * Akzeptiert werden nur Zahlen ohne Komma oder Zahlen mit einem Komma und exakt zwei Nachkommastellen.
     * Dabei kann ein Vorzeichen (+/-) vorangestellt werden.
     * 
     * @param euroString Der zu prüfende String
     */
    public static boolean istGeldbetrag(String euroString)
    {
        return euroString.matches("[+-]?\\d+,\\d{2}")
                || euroString.matches("[+-]?\\d+");
    }

    /**
     * Gibt den Betrag eines Strings in Eurocent zurück.
     * 
     * @require istGeldbetrag(euroString)
     */
    private static int parseBetrag(String euroString)
    {
        assert istGeldbetrag(euroString) : "Vorbedingung verletzt: istGeldbetrag(euroString)";

        int betrag;

        if (euroString.contains(","))
        {
            String[] euroCent = euroString.split(",");
            int euro = Integer.parseInt(euroCent[0]);
            int cent = Integer.parseInt(euroCent[1]);
            betrag = euro * 100 + cent;
        }
        else
        {
            betrag = Integer.parseInt(euroString) * 100;
        }

        return betrag;
    }

    /**
     * Addiert zwei Geldbeträge.
     * 
     * @require koennenAddiertWerden(betrag1.getEurocent(), betrag2.getEurocent())
     */
    public static Geldbetrag addiere(Geldbetrag betrag1, Geldbetrag betrag2)
    {
        assert koennenAddiertWerden(betrag1.getEurocent(),
                betrag2.getEurocent()) : "Vorbedingung verletzt: koennenAddiertWerden(betrag1.getEurocent(), betrag2.getEurocent())";
        
        int ergebnis = betrag1.getEurocent() + betrag2.getEurocent();
        
        return Geldbetrag.getGeldbetrag(ergebnis);
    }

    private static boolean koennenAddiertWerden(int zahl1, int zahl2)
    {
        return true;
    }

    /**
     * Subtrahiert zwei Geldbeträge (betrag1 - betrag2)
     * 
     * @require koennenSubtrahiertWerden(betrag1.getEurocent(), betrag2.getEurocent())
     */
    public static Geldbetrag subtrahiere(Geldbetrag betrag1, Geldbetrag betrag2)
    {
        assert koennenSubtrahiertWerden(betrag1.getEurocent(),
                betrag2.getEurocent()) : "Vorbedingung verletzt: koennenSubtrahiertWerden(betrag1.getEurocent(), betrag2.getEurocent())";

        int ergebnis = betrag1.getEurocent() - betrag2.getEurocent();
        
        return Geldbetrag.getGeldbetrag(ergebnis);
    }

    private static boolean koennenSubtrahiertWerden(int zahl1, int zahl2)
    {
        return true;
    }

    /**
     * Multipliziert einen Geldbetrag mit einem Faktor.
     * 
     * @require koennenMultipliziertWerden(betrag1.getEurocent(), faktor)
     */
    public static Geldbetrag multipliziere(Geldbetrag betrag, int faktor)
    {
        assert koennenMultipliziertWerden(betrag.getEurocent(), faktor) : "Vorbedingung verletzt: koennenMultipliziertWerden(betrag1.getEurocent(), faktor)";

        int ergebnis = faktor * betrag.getEurocent();
        
        return Geldbetrag.getGeldbetrag(ergebnis);
    }

    private static boolean koennenMultipliziertWerden(int zahl1, int zahl2)
    {
        return true;
    }

    public int getEuroAnteil()
    {
        return 0;
    }

    public int getCentAnteil()
    {
        return 0;
    }

    public int getEurocent()
    {
        return 0;
    }

    public boolean istPositiv()
    {
        return false;
    }

    public String toString()
    {
        return null;
    }

    @Override
    public boolean equals(Object obj)
    {
        return false;
    }

    @Override
    public int hashCode()
    {
        return 0;
    }

}
