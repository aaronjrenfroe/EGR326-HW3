import apple.laf.JRSUIConstants;
import javafx.scene.control.Tab;

import javax.naming.SizeLimitExceededException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by AaronR on 2/8/17.
 * for ?
 */
public final class Restaurant {
    private String restaurantName;
    private HashMap<Table,Party> tables;
    private LinkedList<Party> waitList;
    private double cashRegister;
    private int tableCount;
    private int maxTableSize;
    //private HashMap theBoard;

    private Servers servers;

    /**
     *
     *The Constructor initializes the waiting list, the tables, the servers, and the cash register
     *
     */
    public Restaurant(){
        restaurantName = "";
        this.tables = new HashMap<>();
        this.waitList = new LinkedList();
        this.cashRegister = 0;
        this.tableCount = 0;
        this.maxTableSize = 0;
        this.servers = new Servers();

    }


    private List<Servers> getServersOnDuty(){return new ArrayList<Servers>();}

    /**
     *
     * @return List of Servers on duty's toStrings, returns empty if server count is 0
     */
    public List<String> serversOnDutyToString(){
        List<String> sStrings = new ArrayList();
        for (Servers servers1 : getServersOnDuty()) {
            sStrings.add(servers1.toString());
        }
        return sStrings;

    }

    /**
     *
     * @return  number of servers on Duty
     */
    public int addServer(){
        return servers.addServer();
    }

    /**
     *
     * @return Dismissed Servers toString output
     * @throws NullPointerException if server count is 0 before call, there are 0 servers to cashOut
     * @throws UnsupportedOperationException if there are parties seated and only one remaining server, the last server cannot cashOut.
     */
    public String dismissServer()throws NullPointerException, UnsupportedOperationException{return servers.cashOut();}

    /**
     *
     * @return returns number of servers on duty
     */
    public int numberOfServers(){return servers.numberOfServers();}

    /**
     *
     * @return String representation of cash in the Register: $0.00
     */
    public String countCash(){return "$" + String.format("%1.2f", this.cashRegister);}

    /**
     *
     * @return
     */
    public List<String> tableStatuses(){
        List<String> tStrings = new ArrayList();

        for (Table tempT: tables.keySet()){
            StringBuilder tString = new StringBuilder();
        // Table 5 (2-top): Jones party of 2 - Server #2

        tString.append("Table " + tempT.getID() + "(" + tempT.getSize() + "-top):");
        Party hasParty = tables.get(tempT);
        if (hasParty!= null) {
            tString.append(hasParty.getName() + " Party of " + (hasParty.getSize()));
            tString.append(" Server #" + servers.getServerForTable(tempT));
            tStrings.add(tString.toString());
        } else {
            tString.append(" empty");
            tStrings.add(tString.toString());
        }

    }

        return tStrings;
    }

    /**
     *
     * @param p Party passed to check if Name exists in the system, if there is an empty table that will fit them,
     *          or if they are too large to be seated in the restaurant
     * @return  Returns true if can be seated.
     * @throws UnsupportedOperationException If the party's name exists in the system.
     * @throws SizeLimitExceededException If Party's size is too big.
     */
    public boolean partyToBeSeated(Party p) throws UnsupportedOperationException, SizeLimitExceededException{

        if (servers.hasServers()){
            Set<Map.Entry<Table,Party>> tempTables = tables.entrySet();
            Table smallestFittingTable = new Table(99999,9999);
            if (waitList.contains(p)){
                throw new UnsupportedOperationException("Duplicate Name");
            }

            for (Map.Entry kv:tempTables) {

                if ((kv.getValue()) != null) {
                    String name = p.getName();

                    if (((Party) kv.getValue()).getName() == name) {
                        throw new UnsupportedOperationException("Duplicate Name");
                    }
                    else if (p.getSize() > maxTableSize){
                        throw new SizeLimitExceededException("Party is to large to be seated");
                    }else if(p.getSize() < 1){
                        throw new IllegalArgumentException();
                    }
                }else{
                    if (kv.getKey().getClass() == Table.class){
                        Table tempTable = (Table) kv.getKey();
                        System.out.println(tempTable.toString());
                        if (tempTable.getSize() < smallestFittingTable.getSize() && tempTable.getSize() >= p.getSize()){
                            smallestFittingTable = tempTable;
                        }
                    }
                }
            }
            if (smallestFittingTable.getID() != 99999){
                servers.assignToServer(smallestFittingTable);
                tables.replace(smallestFittingTable, p);
                return true;
            }else {
                return false;
            }
        }else throw new NullPointerException("No servers Available");
    }

    /**
     *
     * @param party The party to be added to the waiting list
     */
    public void addToWaitingList(Party party){
        waitList.add(party);
    }

    /**
     *
     * @param fileName File to be passed to the scanner upon initialization
     * @throws FileNotFoundException If file is not found
     */
    public void readInfoFile(File fileName) throws FileNotFoundException{
        try{
            Scanner restInfo = new Scanner(fileName);
            String rName = restInfo.nextLine().trim();
            if (rName != "" || rName != null){
                restaurantName = rName;
            }
            while (restInfo.hasNextInt()){
                int tempSize = restInfo.nextInt();
                if (tempSize > 0 && tempSize < 12) {
                    if (maxTableSize < tempSize){
                        maxTableSize = tempSize;
                    }
                    tables.put(new Table(tableCount++, tempSize), null);
                }
            }
            System.out.println("Tables Added:" + tableCount);
        }catch(FileNotFoundException fi){
            throw new FileNotFoundException("fi");
        }
    }

    /**
     *
     * @return a List populated with the output from the toString Method of every party in waiting list. If waiting list is empty returns an empty List
     */
    public List<String> waitingList(){
        List wListStrings = new ArrayList();
        for (Party p:waitList) {
            wListStrings.add(p.toString());
        }

        return wListStrings;
    }
}

/*
if (choice.equals("S")) {
				serversOnDuty();
			} else if (choice.equals("A")) {
					addServer();
			} else if (choice.equals("D")) {
				dismissServer();
			} else if (choice.equals("R")) {
				cashRegister();
			} else if (choice.equals("P")) {
				partyToBeSeated();
			} else if (choice.equals("T")) {
				tableStatus();
			} else if (choice.equals("C")) {
				checkPlease();
			} else if (choice.equals("W")) {
				waitingList();
			} else if (choice.equals("Q")) {
				break;
			} else if (choice.equals("?")) {
				displayOptions();
			} else if (choice.equals("!")) {
				rickRoll();
			}
			System.out.println();
 */
