import java.io.*;
import java.util.ArrayList;

public class OstotapahtumatIO {


    public static void main(String[] args) {
        ostotapahtumaWriteTest();
        ostotapahtumaReadTest();
    }

    public static void ostotapahtumaWriteTest() {
        ArrayList<Ostotapahtuma> al = new ArrayList<>();
        al.add(new Ostotapahtuma(
                new Asiakas("111", "Ville", 10.55),
                new Myyja("KT", "Kova Tykki", 10),
                new Tuote("Auto", 2, 15.000),
                15));

        kirjoitaOstotapahtumat(al, "ostotapahtumat");
    }



    public static void ostotapahtumaReadTest() {
        ArrayList<Ostotapahtuma> al = lueOstotapahtumat("ostotapahtumat");
        for (Ostotapahtuma os : al) {
            System.out.println(os);
        }
    }

    /**
     * Kirjoittaa ostotapahtumalistan annetun nimiseen tiedostoon.
     *
     * @param ostotapahtumaLista  lista kirjoitettavista myyjistä.
     * @param tiedostonNimi kirjoitettavan tiedoston nimi
     */
    public static void kirjoitaOstotapahtumat(ArrayList<Ostotapahtuma> ostotapahtumaLista,
                                              String tiedostonNimi) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(
                             new FileOutputStream(tiedostonNimi))) {
            oos.writeObject(ostotapahtumaLista);
        } catch (IOException e) {
            System.out.println("Tapahtui virhe: " + e);
        }


    }

    /**
     * Palauttaa uuden ostotapahtumaOlion annetun datarivin perusteella.
     * Rivillä tulee olla Asiakkaan, Myyjän  ja Tuotetiedot tässä
     * järjestyksessä erotettuna merkillä
     * <code>VerkkokauppaIO.EROTIN</code>
     *
     * @param data datarivi, josta tiedot parsitaan
     * @return uuden Myyja-olion dataan perustuen
     */
    public static Ostotapahtuma parsiOstotapahtuma(String data) {
        String[] tiedot = data.split(VerkkokauppaIO.EROTIN);
        // Tässä vaiheessa tulee tietää tietojen järjestys

        // Asiakas:
        String asNum=tiedot[0];
        String asNimi=tiedot[1];
        double asOstojaTehty = Double.parseDouble(tiedot[2]);

        // Myyjä:
        String myyjaTunniste = tiedot[6];
        String myyjaNimi = tiedot[7];
        double myyjanProvisiot= Double.parseDouble(tiedot[8]);

        // Tuote:
        String tuoteNimi = tiedot[3];
        int tuoteSaldo = Integer.parseInt(tiedot[4]);
        double tuoteHinta = Double.parseDouble(tiedot[5]);

        // int määrä ja double hinta
        int tapahtumanTuoteMaara = Integer.parseInt(tiedot[9]);


        return new Ostotapahtuma(new Asiakas(asNum, asNimi, asOstojaTehty),
                new Myyja(myyjaTunniste,myyjaNimi,myyjanProvisiot),
                new Tuote(tuoteNimi,tuoteSaldo,tuoteHinta),tapahtumanTuoteMaara);
    }


    /**
     * Metodi lukee ostotapahtumat annetun nimisestä tiedostosta ja
     * palauttaa sisällön mukaisen listan Ostotapahtumat-olioita.
     *
     * @param tiedostonNimi luettavan tiedoston nimi
     * @return tiedostosta luetut myyjäoliot listana
     */
    public static ArrayList<Ostotapahtuma> lueOstotapahtumat(String tiedostonNimi) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tiedostonNimi))) {
            System.out.println("asd");
            ArrayList<Ostotapahtuma> tlista = (ArrayList<Ostotapahtuma>) ois.readObject();
            return tlista;
        } catch (IOException e) {
            System.out.println("Tapahtui virhe: " + e);
        } catch (ClassNotFoundException e) {
            // Tämä virhe tulee, jos luettu tieto ei ole yhteensopiva
            // sen luokan kanssa, jonka tyyppiseksi se yritetään muuntaa
            System.out.println("Tapahtui virhe: " + e);
        }
        return null;

    }

}