package Domain;

import java.util.Objects;

public class Produs extends Entity<Long>{
    private double pret;
    private String nume;
    private String descriere;
    private int cantitate;

    public Produs(double pret, String nume, String descriere, int cantitate) {
        this.pret = pret;
        this.nume = nume;
        this.descriere = descriere;
        this.cantitate = cantitate;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Produs produs = (Produs) o;
        return Double.compare(pret, produs.pret) == 0 && cantitate == produs.cantitate && Objects.equals(nume, produs.nume) && Objects.equals(descriere, produs.descriere);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pret, nume, descriere, cantitate);
    }
}
