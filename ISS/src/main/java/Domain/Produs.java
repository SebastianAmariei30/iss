package Domain;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table( name = "products" )
public class Produs extends Domain.Entity<Long>{
    private double pret;
    private String nume;
    private String descriere;
    private int cantitate;

    public Produs(){
        id=0L;
        pret=0;
        nume=descriere="";
        cantitate=0;
    }
    public Produs(double pret, String nume, String descriere, int cantitate) {
        this.pret = pret;
        this.nume = nume;
        this.descriere = descriere;
        this.cantitate = cantitate;
    }

    @Id
    @GeneratedValue(generator="increment")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "pret")
    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    @Column(name = "nume")
    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    @Column(name = "descriere")
    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    @Column(name = "cantitate")
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
