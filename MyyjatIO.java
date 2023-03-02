import java.util.ArrayList;

public class MyyjatIO {

    public static void main(String[] args) {
        System.out.println("Testataan myyjien tallentaminen tiedostoon.... ");
        myyjaWriteTest();
        System.out.println("Luetaan tallennetut myyjät:");
        myyjaReadTest();
    }

    public static void myyjaWriteTest() {
        ArrayList<Myyja> al = new ArrayList<>();
        al.add(new Myyja("KT", "Kova Tykki", 10));
        al.add(new Myyja("MM", "Mikko Myyjä", 2.5));
        al.add(new Myyja("Amat", "Newbie Amätöörinen", 0.5));
        kirjoitaMyyjat(al, "myyjat.csv");
    }



    public static void myyjaReadTest() {
        ArrayList<Myyja> al = lueMyyjat("myyjat.csv");
        for (Myyja m : al) {
            System.out.println(m);
        }
    }

    /**
     * Kirjoittaa myyjalistan annetun nimiseen tiedostoon.
     *
     * @param myyjaLista  lista kirjoitettavista myyjistä.
     * @param tiedostonNimi kirjoitettavan tiedoston nimi
     */
    public static void kirjoitaMyyjat(ArrayList<Myyja> myyjaLista,
                                         String tiedostonNimi) {
        String data = "";
        for (Myyja myyja : myyjaLista) {
            data += myyja.getData(VerkkokauppaIO.EROTIN);
            data += "\n";
        }
        // Poistetaan viimeinen "turha" rivinvaihto
        if (data.length() > 0) {
            data = data.substring(0, data.length() - 1);
        }
        VerkkokauppaIO.kirjoitaTiedosto(tiedostonNimi, data);
    }



    /**
     * Palauttaa uuden myyjaolion annetun datarivin perusteella.
     * Rivillä tulee olla tunniste, nimi ja proviision prosentti tässä
     * järjestyksessä erotettuna merkillä
     * <code>VerkkokauppaIO.EROTIN</code>
     *
     * @param data datarivi, josta tiedot parsitaan
     * @return uuden Myyja-olion dataan perustuen
     */
    public static Myyja parsiMyyja(String data) {
        String[] tiedot = data.split(VerkkokauppaIO.EROTIN);
        // Tässä vaiheessa tulee tietää tietojen järjestys
        String tun = tiedot[0];
        String nimi = tiedot[1];
        double prov = Double.parseDouble(tiedot[2]);

        return new Myyja(tun, nimi, prov);
    }


    /**
     * Metodi lukee myyjät annetun nimisestä tiedostosta ja
     * palauttaa sisällön mukaisen listan Myyjä-olioita.
     *
     * @param tiedostonNimi luettavan tiedoston nimi
     * @return tiedostosta luetut myyjäoliot listana
     */
    public static ArrayList<Myyja> lueMyyjat(String tiedostonNimi) {
        ArrayList<Myyja> myyjat = new ArrayList<>();
        ArrayList<String> data = VerkkokauppaIO.lueTiedosto(tiedostonNimi);
        for (String adata : data) {
            Myyja mm = parsiMyyja(adata);
            myyjat.add(mm);
        }
        return myyjat;
    }


}
