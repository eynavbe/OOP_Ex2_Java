package api;

public class GeoLocationC implements GeoLocation{
    private double x;
    private double y;
    private double z;
    GeoLocationC(double x, double y,double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public double x() {
        return this.x;
    }

    @Override
    public double y() {
        return this.y;
    }

    @Override
    public double z() {
        return this.z;
    }
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
    @Override
    public double distance(GeoLocation g) {
        if (g == null){
            return 0;
        }
        double theta = g.x() - this.x;
        double dist = Math.sin(deg2rad(g.y())) * Math.sin(deg2rad(this.y)) + Math.cos(deg2rad(g.y())) * Math.cos(deg2rad(this.y)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
            dist = dist * 1.609344;
            return dist;
    }
}
