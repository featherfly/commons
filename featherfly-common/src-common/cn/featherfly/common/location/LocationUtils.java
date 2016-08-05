package cn.featherfly.common.location;

/**
 * The type Location utils.
 */
public class LocationUtils {
  
    /* 地球半径 */
    private static final double EARTH_RADIUS = 6371000;  

    private LocationUtils() {
    }

    /**
     * Hav double.
     *
     * @param theta the theta
     * @return the double
     */
    public static double hav(double theta) {
        double s = Math.sin(theta / 2);  
        return s * s;  
    }

    /**
     * Gets distance.
     *
     * @param point0 the point 0
     * @param point1 the point 1
     * @return the distance
     */
    public static double getDistance(LocationPoint point0, LocationPoint point1) {
        double lat0 = Math.toRadians(point0.getLatitude());
        double lat1 = Math.toRadians(point1.getLatitude());
        double lng0 = Math.toRadians(point0.getLongitude());
        double lng1 = Math.toRadians(point1.getLongitude());

        double dlng = Math.abs(lng0 - lng1);
        double dlat = Math.abs(lat0 - lat1);
        double h = hav(dlat) + Math.cos(lat0) * Math.cos(lat1) * hav(dlng);  
        double distance = 2 * EARTH_RADIUS * Math.asin(Math.sqrt(h));  
  
        return distance;  
    }

    /**
     * Gets rectangle 4 point.
     *
     * @param point the point
     * @param distance the distance
     * @return the rectangle 4 point
     */
    public static RectanglePoint getRectanglePoint(LocationPoint point,
                                                   double distance) {
        // double dlng = 2 * Math.asin(Math.sin(distance / (2 * EARTH_RADIUS)) / Math.cos(lat));
        double lat = point.getLatitude();
        double lng = point.getLongitude();
        double dlng = 2 * Math.asin(Math.sin(distance/(2*EARTH_RADIUS))/Math.cos(Math.toRadians(lat)));
        dlng = Math.toDegrees(dlng);

        double dlat = distance / EARTH_RADIUS;
        dlat = Math.toDegrees(dlat); // # 弧度转换成角度

        RectanglePoint rectanglePoint = new RectanglePoint();
        rectanglePoint.setLeftTop(new LocationPoint(lat + dlat, lng - dlng));
        rectanglePoint.setLeftBottom(new LocationPoint(lat - dlat, lng - dlng));
        rectanglePoint.setRightTop(new LocationPoint(lat + dlat, lng + dlng));
        rectanglePoint.setRightBottom(new LocationPoint(lat - dlat, lng + dlng));

        return rectanglePoint ;
    }  


    /*public static void main(String[] args) {
        double lat = 30.677644;
        double lng = 104.058454;
        System.out.println(30.677644 + " , " + 104.058454);
        double distance = 1000d;

        RectanglePoint rectanglePoint = LocationUtils.getRectanglePoint(new LocationPoint(lat, lng), distance);
        String sql = "SELECT * FROM place WHERE lat > "
                + rectanglePoint.getRightBottom().getLatitude() + " AND lat < "
                + rectanglePoint.getLeftTop().getLatitude() + " AND lng > "
                + rectanglePoint.getLeftTop().getLongitude()+ " AND lng < "
                + rectanglePoint.getRightBottom().getLongitude();

        System.out.println(sql);

        System.out.println(rectanglePoint.getLeftTop().getLatitude() + " , " + rectanglePoint.getLeftTop().getLongitude());
        System.out.println(rectanglePoint.getLeftBottom().getLatitude() + " , " + rectanglePoint.getLeftBottom().getLongitude());
        System.out.println(rectanglePoint.getRightTop().getLatitude() + " , " + rectanglePoint.getRightTop().getLongitude());
        System.out.println(rectanglePoint.getRightBottom().getLatitude() + " , " + rectanglePoint.getRightBottom().getLongitude());

        double lat1 = 30.495503391970406;  
        double lng1 = 120.49261708577215;  
        double d = LocationUtils.getDistance(new LocationPoint(lat, lng), new LocationPoint(lat1, lng1));
        System.out.println(d);  
    }*/
}