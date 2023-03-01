import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class AsiakasIO {
    public static void main(String[] args) {


    }

    public static void asiakasWriteTest() {
        ArrayList<Asiakas> al = new ArrayList<>();
        al.add(new Asiakas("12345", "Antti Asiakas", 10));
        al.add(new Asiakas("54321", "Anna Asiakas", 55.50));
        al.add(new Asiakas("99999", "Keijo Keksitty", 1000));
        kirjoitaAsiakkaat(al, "asiakkaat.csv");
    }

    public static void asiakasReadTest() {
        ArrayList<Asiakas> al = lueAsiakkaat("asiakkaat.csv");
        for (Asiakas as : al) {
            System.out.println(as);
        }
    }


    /**
     * Kirjoittaa asiakaslistan annetun nimiseen tiedostoon.
     *
     * @param asiakasLista  lista kirjoitettavista asiakkaista.
     * @param tiedostonNimi kirjoitettavan tiedoston nimi
     */
    public static void kirjoitaAsiakkaat(ArrayList<Asiakas> asiakasLista,
                                         String tiedostonNimi) {
        String data = "";
        for (Asiakas asiakas : asiakasLista) {
            data += asiakas.getData(VerkkokauppaIO.EROTIN);
            data += "\n";
        }
        // Poistetaan viimeinen "turha" rivinvaihto
        if (data.length() > 0) {
            data = data.substring(0, data.length() - 1);
        }
        VerkkokauppaIO.kirjoitaTiedosto(tiedostonNimi, data);
    }
    /**
     * Palauttaa uuden asiakasolion annetun datarivin perusteella.
     * Rivillä tulee olla asiakasnumero, nimi ja tehdyt ostot tässä
     * järjestyksessä erotettuna merkillä
     * <code>VerkkokauppaIO.EROTIN</code>
     *
     * @param data datarivi, josta tiedot parsitaan
     * @return uuden Asiakas-olion dataan perustuen
     */
    public static Asiakas parsiAsiakas(String data) {
        String[] tiedot = data.split(VerkkokauppaIO.EROTIN);
        // Tässä vaiheessa tulee tietää tietojen järjestys
        String asNro = tiedot[0];
        String nimi = tiedot[1];
        double ostot = Double.parseDouble(tiedot[2]);

        return new Asiakas(asNro, nimi, ostot);
    }



    /**
     * Metodi lukee asiakkaat annetun nimisestä tiedostosta ja
     * palauttaa sisällön mukaisen listan Asiakas-olioita.
     *
     * @param tiedostonNimi luettavan tiedoston nimi
     * @return tiedostosta luetut asiakasoliot listana
     */
    public static ArrayList<Asiakas> lueAsiakkaat(String tiedostonNimi) {
        ArrayList<Asiakas> asiakkaat = new ArrayList<>();
        ArrayList<String> data = VerkkokauppaIO.lueTiedosto(tiedostonNimi);
        for (String adata : data) {
            Asiakas as = parsiAsiakas(adata);
            asiakkaat.add(as);
        }
        return asiakkaat;
    }

}

