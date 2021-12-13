package api;

//Assistance class for ShortestPath
public class ForShortestPath {
    int value;
    double sumWeight;
    ForShortestPath(int value,double sumWeight){
        this.value = value;
        this.sumWeight = sumWeight;
    }

    public double getSumWeight() {
        return sumWeight;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "ForShortestPath{" +
                "value=" + value +
                ", sumWeight=" + sumWeight +
                '}';
    }

}
