package Service;

import Domain.Agent;
import Domain.Produs;
import Repository.AgentRepository;
import Repository.ProdusRepository;

import java.time.LocalDate;
import java.util.List;

public class Service {
    private AgentRepository repoa;
    private ProdusRepository repop;

    public Service(AgentRepository repoa, ProdusRepository repop) {
        this.repoa = repoa;
        this.repop = repop;
    }

    public Agent login(String username, String password) {
        return repoa.findUserByUnPs(username, password);
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
    public void addAgent(String username, String password, LocalDate birthdate) {
        Agent agent = new Agent(username, password, birthdate);
        repoa.save(agent);
    }
}
