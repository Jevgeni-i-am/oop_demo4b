


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

        kirjoitaOstotapahtumat(al, "ostotapahtumat.csv");
    }



    public static void ostotapahtumaReadTest() {
        ArrayList<Ostotapahtuma> al = lueOstotapahtumat("ostotapahtumat.csv");
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
        String data = "";
        for (Ostotapahtuma osto : ostotapahtumaLista) {

            data += osto.getData(VerkkokauppaIO.EROTIN);
            data += "\n";
        }
        // Poistetaan viimeinen "turha" rivinvaihto
        if (data.length() > 0) {
            data = data.substring(0, data.length() - 1);
        }
        VerkkokauppaIO.kirjoitaTiedosto(tiedostonNimi, data);

}

    /**
     * Palauttaa uuden ostotapahtumaOlion annetun datarivin perusteella.
     * Rivillä tulee olla Asiakkaan, Myyjän  ja Tuotetiedot tässä
     * järjestyksessä erotettuna merkillä
     * <code>VerkkokauppaIO.EROTIN</code>
     *
     * @param data datarivi, josta tiedot parsitaan
     * @return uuden Ostotapahtuma-olion dataan perustuen
     */
    public static Ostotapahtuma parsiOstotapahtuma(String data) {
        String[] tiedot = data.split(VerkkokauppaIO.EROTIN);
        // Tässä vaiheessa tulee tietää tietojen järjestys

        System.out.println("Data: "+data);
        System.out.println("T0: " +tiedot[0]);
        System.out.println("T1: " +tiedot[1]);
        System.out.println("T2: " +tiedot[2]);
        System.out.println("T3: " +tiedot[3]);
        System.out.println("T4: " +tiedot[4]);
        System.out.println("T5: " +tiedot[5]);
        Asiakas asiakas=new Asiakas(tiedot[0], tiedot[1],Double.parseDouble(tiedot[2]));
        Myyja myyja = new Myyja(tiedot[3],tiedot[4],Double.parseDouble(tiedot[5]));
        Tuote tuote = new Tuote(tiedot[6],Integer.parseInt(tiedot[7]),Double.parseDouble(tiedot[8]));

        // int määrä
        int tapahtumanTuoteMaara = Integer.parseInt(tiedot[9]);


        return new Ostotapahtuma(asiakas,myyja,tuote,tapahtumanTuoteMaara);
    }


    /**
     * Metodi lukee ostotapahtumat annetun nimisestä tiedostosta ja
     * palauttaa sisällön mukaisen listan Ostotapahtumat-olioita.
     *
     * @param tiedostonNimi luettavan tiedoston nimi
     * @return tiedostosta luetut myyjäoliot listana
     */
    public static ArrayList<Ostotapahtuma> lueOstotapahtumat(String tiedostonNimi) {
        ArrayList<Ostotapahtuma> ostot = new ArrayList<>();
        ArrayList<String> data = VerkkokauppaIO.lueTiedosto(tiedostonNimi);
        for (String adata : data) {
            Ostotapahtuma os = parsiOstotapahtuma(adata);
            ostot.add(os);
        }
       return ostot;
    }

}