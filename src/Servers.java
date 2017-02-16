import java.util.*;

/**
 * Created by Aaron Renfroe on 1/24/17.
 * EGR 326
 * Assignment 3 Restaurant
 * Class Servers is a manager of all the servers and their actions
 */
public final class Servers {
    private RoundRobin serversRR;
    private int dailyServerCount;
    private Iterator<Server> serverQ;
    private HashMap<Server, List<Table>> assignments;

    /**
     * Constructor, Initializes collection of servers, daily server count, RoundRobin Iterator, and tableAssignments
     */
    public Servers() {
        serversRR = new RoundRobin<Servers>();
        dailyServerCount = 0;
        serverQ = serversRR.iterator();
        assignments = new HashMap<>();
    }

    /**
     * Adds new Server to Round Robin
     * @return New total number of servers
     */
    protected int addServer() {
        RoundRobin newRR = new RoundRobin();
        newRR.add(new Server(++dailyServerCount));
        for (int i = 0; i < serversRR.size(); i++) {
            newRR.add(serverQ.next());
        }
        serversRR = newRR;
        serverQ = serversRR.iterator();
        return this.serversRR.size();
    }

    /**
     * Check to see if there are servers on duty
     * @return True if there is at least one server on duty
     */
    protected boolean hasServers() {
        return serverQ.hasNext();
    }

    /**
     * The number of servers on duty
     * @return The number of servers on duty
     */
    protected int numberOfServers() {
        return serversRR.size();
    }

    /**
     *  Gets next server in Round Robin fashion Used for Testing
     * @return Next server
     */
    protected Server getNext(){
      return serverQ.next();
    }

    /**
     * Takes table and adds table to the Next server's Assignments
     * @param t Table to be assigned to Next server
     */
    protected void assignToServer(Table t){
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

    /**
     * Called to cash out the next server
     * @return Returns Number of remaining servers on duty after cash out
     * @throws NullPointerException Thrown is there are 0 servers on duty
     * @throws UnsupportedOperationException Thrown if there is only one server left and it is asked to cash out
     */
    protected String cashOut() throws NullPointerException, UnsupportedOperationException{

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
                serverQ = serversRR.iterator();
                return "Server #" + leavingServer.getId() + " cashes out with " + leavingServer.getTips();
            }

        }else if(serversRR.size() == 0){
            throw new NullPointerException("There are no servers to remove.");
            // no parties so we can remove server
        }else{
            assert assignments.size() == 0;
            Server leavingServer = serverQ.next();
            serversRR.remove(leavingServer);
            serverQ = serversRR.iterator();
            return "Server #" + leavingServer.getId() + " cashes out with " + leavingServer.getTips();
        }

    }

    /**
     * This finds the server who was assigned to that table, removes the assignment, adds tip to server
     * @param t Table that tip was from
     * @param tip Tip amount to be added to server
     */
    protected void receiveTipClearTable(Table t, double tip){
        assignments.forEach((k,v)->{
            if (v.contains(t)){
                v.remove(t);
                k.addTip(tip);
                // return is necessary because once server is found we do not need to continue looking
                return;
            }
        });
    }

    /**
     * Gets server assigned to this table passes as a parameter
     * @param t Search Parameter
     * @return returns server who is assigned to Table t
     */
    protected Server getServerForTable(Table t){
        final Server[] returnedServer = new Server[1];
        assignments.forEach((k,v)->{
            if (v.contains(t)){
                returnedServer[0] = k;
                return;
            }
        });
        if (returnedServer[0] == null){
            throw new NullPointerException("Table doesn't have a server");
        }else {
            return returnedServer[0];
        }

    }

    /**
     *
     * @return List of Collection of Server toStrings
     */
    protected List<String> serversToStrings(){
        List<String> sStrings = new ArrayList();
        for (int i = 0; i < numberOfServers(); i++) {
            sStrings.add(serverQ.next().toString());
        }
        return sStrings;
    }

}
