import java.util.*;

/**
 * Created by AaronR on 2/8/17.
 * for ?
 */
public final class Servers {
    private RoundRobin serversRR;
    private int dailyServerCount = 0;
    private Iterator<Server> serverQ;
    private HashMap<Server, List<Table>> assignments;


    public Servers() {
        serversRR = new RoundRobin();
        dailyServerCount = 0;
        serverQ = serversRR.iterator();
        assignments = new HashMap<>();
    }

    public int addServer() {

        this.serversRR.add(this.serverQ, new Server(++this.dailyServerCount));
        return this.serversRR.size();
    }

    private void remove() throws Exception {

        serversRR.remove(serverQ);
        this.serverQ = this.serversRR.iterator();
    }

    public boolean hasServers() {
        return serverQ.hasNext();
    }

    public int numberOfServers() {
        return serversRR.size();
    }

    public Server getNext(){
      return serverQ.next();
    }

    public void assignToServer(Table t){
        Server nextServer = serverQ.next();
        if (assignments.containsKey(nextServer)){
            List serversTables = assignments.get(nextServer);
            serversTables.add(t);
            assignments.replace(nextServer,serversTables);
        }else{
            List<Table> serversTables = new ArrayList();
            serversTables.add(t);
            assignments.put(nextServer,serversTables);
        }
    }

    public String cashOut() throws NullPointerException, UnsupportedOperationException{


        if (assignments.size() > 0)  {

            if (serversRR.size() == 1){
                throw new UnsupportedOperationException("Cannot remove last server while parties are still seated");
            }else {
                Server leavingServer = (Server) serverQ.next();

                if (assignments.containsKey(leavingServer)) {

                    List<Table> tList = assignments.get(leavingServer);

                    tList.forEach(table -> assignToServer(table));
                }
                serversRR.remove(leavingServer);
                return "Server #" + leavingServer.getId() + " cashes out with " + leavingServer.getTips();
            }

        }else if(serversRR.size() == 0){
            throw new NullPointerException("There are no servers to remove.");
            // no parties so we can remove server
        }else{
            assert assignments.size() == 0;
            Server leavingServer = serverQ.next();
            serversRR.remove(leavingServer);
            return "Server #" + leavingServer.getId() + " cashes out with " + leavingServer.getTips();
        }

    }
    private void unassignTable(Table t){
        assignments.forEach((k,v)->{
            if (v.contains(t)){
                v.remove(t);
                return;
            }
        });
    }
    public Server getServerForTable(Table t){
        final Server[] returnedServer = new Server[1];
        assignments.forEach((k,v)->{
            if (v.contains(t)){
                returnedServer[0] = k;
                return;
            }
        });
        return returnedServer[0];

    }

}
