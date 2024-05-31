package Service;

import Domain.Agent;
import Domain.Comanda;
import Domain.Produs;
import Repository.AgentRepository;
import Repository.ComandaRepository;
import Repository.ProdusRepository;
import Uitls.Observable;
import Uitls.Observer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Service implements Observable {
    private AgentRepository repoa;
    private ProdusRepository repop;
    private ComandaRepository repoc;

    public Service(AgentRepository repoa, ProdusRepository repop,ComandaRepository repoc) {
        this.repoa = repoa;
        this.repop = repop;
        this.repoc=repoc;
    }

   public Agent[] getAllAgents() {
        List<Agent> agents  = (List<Agent>) repoa.findAll();
        Agent[] ags=new Agent[agents.size()];
        ags=agents.toArray(ags);
        return ags;
    }
    public Produs[] getAllProducts() {
        List<Produs> agents  = (List<Produs>) repop.findAll();
        Produs[] ags=new Produs[agents.size()];
        ags=agents.toArray(ags);
        return ags;
    }
    public Agent findAgentByUnPs(String u, String p){
        return repoa.findUserByUnPs(u,p);
    }
    public Agent findAgentByUn(String u){
        return repoa.findUserByUn(u);
    }
    public void addAgent(String username, String password, String birthdate) {
        Agent agent = new Agent(username, password, birthdate);
        repoa.save(agent);
    }
    public void deleteAgent(Agent a){
        repoa.delete(a.getId());
    }
    public int updateAgent(Agent a,String userold){
        if(repoa.findUserByUn(a.getUsername())==null|| Objects.equals(a.getUsername(), userold)){
            repoa.update(a.getId(),a);
            return 0;
        }
        else
            return 1;
    }
    public int order(Long id, int cantitate,Long ida){
        Produs produs = repop.findOne(id);
        if(produs.getCantitate()>=cantitate){
            produs.setCantitate(produs.getCantitate()-cantitate);
            repoc.save(new Comanda(id,ida,cantitate));
            repop.update(id,produs);
            notifyObservers();
            return 1;
        }
        return 0;
    }

    private List<Observer> observers=new ArrayList<>();

    @Override
    public void addObserver(Observer e) {
        observers.add(e);
    }

    @Override
    public void removeObserver(Observer e) {
        observers.remove(e);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(Observer::update);
    }
}
