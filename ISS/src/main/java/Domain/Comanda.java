package Domain;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table( name = "orders" )
public class Comanda extends Domain.Entity<Long>{
    private Long idProdus;
    private Long idClient;
    private int cantitate;

    public Comanda(){
        id=0L;
        idProdus=idClient=0L;
        cantitate=0;
    }
    public Comanda(Long idProdus, Long idClient, int cantitate) {
        this.idProdus = idProdus;
        this.idClient = idClient;
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

    @Column(name = "idp")
    public Long getIdProdus() {
        return idProdus;
    }

    public void setIdProdus(Long idProdus) {
        this.idProdus = idProdus;
    }

    @Column(name = "idc")
    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    @Column(name = "quantity")
    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    @Override
    public String toString() {
        return "Comanda{" +
                "idProdus=" + idProdus +
                ", idClient=" + idClient +
                ", cantitate=" + cantitate +
                '}';
    }
}
