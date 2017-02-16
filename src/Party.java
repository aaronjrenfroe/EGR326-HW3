

/**
 * @author Aaron Renfroe
 * Created by Aaron Renfroe on 1/24/17.
 * EGR 326
 * Assignment 3 Restaurant
 *
 */
public final class Party {

    private final String name;
    private final int size;
    // Constructor takes in a String name and Int size as parameters
    public Party(String name, int size){

            this.name = name.trim();
            this.size = size;
    }
    // Returns parties name
    public String getName(){

        return this.name;
    }
    // Return parties size
    public int getSize(){
        return this.size;
    }

    // Returns "Name, Party of #"
    @Override
    public String toString(){

        return (this.name + ", Party of " + this.getSize());

    }

    @Override
    public int hashCode() {
        int hc = 151;
        hc += 17 * this.name.hashCode();

        return hc;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == Party.class){
            Party p = (Party) obj;
            if (p.getName().equals(this.getName())){
                return true;
            }
        }
        return false;
    }

    private boolean checkName(String n){
        if (n != null && n.trim().length() > 0){
            return true;
        }else return false;
    }
    private boolean checkSize(int s){
        if (s >=1){
            return true;
        }
        else return false;
    }

}
