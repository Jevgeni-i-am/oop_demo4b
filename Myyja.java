import java.io.Serializable;

public class Myyja implements Serializable {
    private String tunniste;
    private String nimi;
    private double provisiot;

    public Myyja(String tunniste, String nimi, double provisiot) {
        this.tunniste = tunniste;
        this.nimi = nimi;
        this.provisiot = provisiot;
    }

    public String getTunniste() {
        return tunniste;
    }

    public void setTunniste(String tunniste) {
        this.tunniste = tunniste;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public double getProvisiot() {
        return provisiot;
    }

    public void setProvisiot(double provisiot) {
        if (provisiot >= 0) {
            this.provisiot = provisiot;
        }
    }

    public void lisaaProvisio(double provisio) {
        if (provisio > 0) {
            this.provisiot += provisio;
        }
    }

    public String getData(String erotinmerkki) {
        String data = tunniste + erotinmerkki;
        data += nimi + erotinmerkki;
        data += provisiot;

        return data;
    }

    @Override
    public String toString() {
        return nimi + " (" + tunniste + "), Myyjän provisio: " + provisiot;
    }

}


