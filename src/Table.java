/**
 * Created by Aaron Renfroe on 1/24/17.
 * EGR 326
 * Assignment 3 Restaurant
 *4 4 8 2 2 6 4 2
 */
public final class Table implements Comparable{
    private final int size;
    private final int ID;


    public Table(int ID, int size ){
        this.size = size;
        this.ID = ID;


    }

    public int getSize(){
        return this.size;
    }

    public int getID() {
        return this.ID;
    }

    public boolean isSameSize(Table t){
        return this.size == t.getSize();
    }
    public int compareTo(Object o){
        if (o.getClass() == Table.class){
            Table t = (Table) o;
            return t.getSize()-this.size;
        } else if (o == null){
            throw new NullPointerException("Cannot compare null to Table");
        }else{
            throw new IllegalArgumentException("Object passed must be of type Table");
        }
    }

    @Override
    public int hashCode(){
        return ((this.size * 31) + (this.getID()*17));
    }

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass() == Table.class) {
            return (obj.hashCode() == this.hashCode());
        }else return false;
    }

    @Override
    public String toString(){
        return "Table Id:" + this.getID()+", Size: " + this.getSize();
    }
}
