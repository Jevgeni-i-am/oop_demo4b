import java.io.Serializable;

public class Ostotapahtuma implements Serializable {
    private Asiakas asiakas;
    private Myyja myyja;
    private Tuote tuote;
    private int maara;
    private double hinta;

    public Ostotapahtuma(Asiakas asiakas, Myyja myyja,
                         Tuote tuote, int maara) {
        this.asiakas = asiakas;
        this.myyja = myyja;
        this.tuote = tuote;
        this.maara = maara;
        hinta = maara * tuote.getHinta();

        // Vähennetään hinnasta vielä alennus
        hinta = hinta - hinta * (asiakas.getAlennusprosentti() / 100);
    }



    public Asiakas getAsiakas() {
        return asiakas;
    }

    public Myyja getMyyja() {
        return myyja;
    }

    public Tuote getTuote() {
        return tuote;
    }

    public int getMaara() {
        return maara;
    }

    public double getHinta() {
        return hinta;
    }

    public String getData(String erotinmerkki) {
        String data = asiakas + erotinmerkki;
        data +=  myyja+ erotinmerkki;
        data += tuote+ erotinmerkki;
        data += maara+ erotinmerkki;
        data += hinta;

        return data;
    }

    @Override
    public String toString() {
        return "Asiakas: "+asiakas.getNimi() + " (" + asiakas.getAsiakasNumero() + ")," +asiakas.getOstojaTehty()+"\n"+
                "Myyja" +myyja.getNimi()+" ("+myyja.getTunniste()+" ) "+myyja.getProvisiot()+"\n"+
                "Tuote" +tuote.getNimi()+ " ( Saldo: "+tuote.getSaldo()+" Hinta: " + tuote.getHinta() + " €.)\n"+hinta;

    }


}
