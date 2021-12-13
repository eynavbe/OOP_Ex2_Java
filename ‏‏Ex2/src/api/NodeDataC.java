package api;

public class NodeDataC implements NodeData{
    final int WHITE = 0;
    final int GRAY = 1;
    final int BLACK = 2;
    private int key;
    private String pos;
    private int tag;
    public NodeDataC(int key, String pos){
        this.key = key;
        this.pos = pos;
        this.tag = WHITE;
    }
    @Override
    public int getKey() {
        return this.key;
    }

    @Override
    public GeoLocation getLocation() throws NumberFormatException {
        String[] posSplit = pos.split(",");
        return new GeoLocationC(Double.parseDouble(posSplit[0]),Double.parseDouble(posSplit[1]),Double.parseDouble(posSplit[2]));
    }

    public String getPos() {
        return pos;
    }

    @Override
    public void setLocation(GeoLocation p) {
        this.pos = ""+p.x()+","+p.y()+","+p.z();
    }

    @Override
    public double getWeight() {
        return 0;
    }

    @Override
    public void setWeight(double w) {

    }

    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public void setInfo(String s) {}

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
        this.tag = t;
    }

    @Override
    public String toString() {
        return "NodeDataC{" +
                ", key=" + key +
                ", pos='" + pos + '\'' +
                ", tag=" + tag +
                '}';
    }
}
