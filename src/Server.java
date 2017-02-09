/**
 * Created by AaronR on 1/31/17.
 * for ?
 */
public final class Server {
    private int id;
    private double tips = 0 ;

    public Server(int id){
        this.id = id;

    }
    public int getId(){
        return this.id;
    }
    public void addTip(double newTip){
        this.tips += newTip;
    }

    public String getTips(){
        return "$" + String.format("%1.2f", this.tips);
    }

    @Override
    public String toString() {
        return "Server #" + this.id + " (" + getTips()+" in tips)";
    }
}

