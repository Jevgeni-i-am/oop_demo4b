import java.io.*;
import java.util.ArrayList;

public class TuotteetIO {


        public static void main(String[] args) {
            tuoteReadTest();
            //tuoteWriteTest();

        }


    public static void tuoteWriteTest() {
        ArrayList<Tuote> al = new ArrayList<>();
        al.add(new Tuote("Omena", 15, 10));
        al.add(new Tuote("Päärynä", 25, 55.50));
      //  al.add(new Tuote("Muistitikku", 53, 23.25));
      //  al.add(new Tuote("Auto", 1, 15000));
        kirjoitaTuotteet(al, "tuotteet.csv");
    }

    public static void tuoteReadTest() {
        ArrayList<Tuote> al = lueTuotteet("tuotteet.csv");
        for (Tuote tu : al) {
            System.out.println(tu);
        }
    }




    /**
     * Kirjoittaa tuotelistan annetun nimiseen tiedostoon.
     *
     * @param tuotelista    lista tuotteista
     * @param tiedostonNimi kirjoitettavan tiedoston nimi
     */
    public static void kirjoitaTuotteet(ArrayList<Tuote> tuotelista, String tiedostonNimi) {
        String data = "";
        for (Tuote tuote : tuotelista) {
            data +=tuote.getData(VerkkokauppaIO.EROTIN);
            data += "\n";
        }
        // Poistetaan viimeinen "turha" rivinvaihto
        if (data.length() > 0) {
            data = data.substring(0, data.length() - 1);
        }
        VerkkokauppaIO.kirjoitaTiedosto(tiedostonNimi, data);
    }




    /**
     * Palauttaa uuden tuoteolion annetun datarivin perusteella.
     * Rivillä tulee olla tuotenimi, saldo ja hinta tässä
     * järjestyksessä erotettuna merkillä
     * <code>VerkkokauppaIO.EROTIN</code>
     *
     * @param data datarivi, josta tiedot parsitaan
     * @return uuden Asiakas-olion dataan perustuen
     */
    public static Tuote parsiTuote(String data) {
        String[] tiedot = data.split(VerkkokauppaIO.EROTIN);

        // Tässä vaiheessa tulee tietää tietojen järjestys
        String nimi = tiedot[0];
        int saldo = Integer.parseInt(tiedot[1]);
        double hinta = Double.parseDouble(tiedot[2]);

        return new Tuote(nimi, saldo, hinta);
    }

    /**
     * Metodi lukee tuotteet annetun nimisestä tiedostosta ja
     * palauttaa sisällön mukaisen listan Tuote-olioita.
     *
     * @param tiedostonNimi luettavan tiedoston nimi
     * @return tiedostosta luetut tuoteoliot listana
     */
    public static ArrayList<Tuote> lueTuotteet(String tiedostonNimi) {
        ArrayList<Tuote> tuotteet = new ArrayList<>();
        ArrayList<String> data = VerkkokauppaIO.lueTiedosto(tiedostonNimi);
        for (String adata : data) {
            Tuote tu = parsiTuote(adata);
            tuotteet.add(tu);
        }
        return tuotteet;
    }



}
