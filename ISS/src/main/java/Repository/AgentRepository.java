package Repository;

import Domain.Agent;

public interface AgentRepository extends Repository<Long, Agent>{
    Agent findUserByUnPs(String username,String password);
    Agent findUserByUn(String username);
}
