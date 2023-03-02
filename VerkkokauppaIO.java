import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Luokkaa käytetään verkkokaupan tietojen tallentamiseen
 * ja lataamiseen tietovarastosta.
 *
 * @author Erkki
 *
 *
 * @ Oppilas : Jevgeni Liski
 */
public class VerkkokauppaIO {

    public static void main(String[] args) {
        test();
   }

    public static void test() {
        // Tähän voi kirjoittaa koodia, jolla testata
        // kirjoitus- ja lukumetodien toimintaa helposti
        System.out.println();
        System.out.println("Testataan asiakkaiden tallentaminen tiedostoon.... ");
        AsiakasIO.asiakasWriteTest();
        System.out.println("Luetaan tallennetut asiakkaat:");
        AsiakasIO.asiakasReadTest();

        System.out.println();
        System.out.println("Testataan tuotteiden tallentaminen tiedostoon.... ");
        TuotteetIO.tuoteWriteTest();
        System.out.println("Luetaan tallennetut tuotteet:");
        TuotteetIO.tuoteReadTest();

        System.out.println();
        System.out.println("Testataan myyjien tallentaminen tiedostoon.... ");
        MyyjatIO.myyjaWriteTest();
        System.out.println("Luetaan tallennetut myyjät:");
        MyyjatIO.myyjaReadTest();

        System.out.println();
        System.out.println("Testataan myyjien tallentaminen tiedostoon.... ");
        OstotapahtumatIO.ostotapahtumaWriteTest();
        System.out.println("Luetaan tallennetut myyjät:");
        OstotapahtumatIO.ostotapahtumaReadTest();

    }


    public static final String EROTIN = ";";

    public static void kirjoitaTiedosto(String tiedostonNimi,
                                        String sisalto) {
        try (PrintWriter tiedosto = new PrintWriter(tiedostonNimi)) {
            tiedosto.write(sisalto);
        } catch (FileNotFoundException e) {
            System.out.println("Tapahtui virhe: " + e);
        }
    }

    /**
     * Lukee annetun nimisen tiedoston sisällön ja palauttaa sen listassa.
     * Jokainen listan alkio vastaa yhtä tiedoston riviä
     *
     * @param tiedostonNimi luettavan tiedoston nimi
     * @return tiedoston sisällön listana
     */
    public static ArrayList<String> lueTiedosto(String tiedostonNimi) {
        ArrayList<String> data = new ArrayList<>();
        try (Scanner lukija = new Scanner(new File(tiedostonNimi))) {
            while (lukija.hasNextLine()) {
                data.add(lukija.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Tapahtui virhe: " + e);
        }
        return data;
    }








}
