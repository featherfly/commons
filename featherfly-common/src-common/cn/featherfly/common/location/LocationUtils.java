package cn.featherfly.common.location;

/**
 * <p>
 * The type Location utils.
 * </p>
 * 
 * @author 钟冀
 * @since 1.6
 * @version 1.0
 */
public final class LocationUtils {
  
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
        double dlng = 2 * Math.asin(Math.sin(distance / (2 * EARTH_RADIUS)) / Math.cos(Math.toRadians(lat)));
        dlng = Math.toDegrees(dlng);

        double dlat = distance / EARTH_RADIUS;
        // # 弧度转换成角度
        dlat = Math.toDegrees(dlat); 

        RectanglePoint rectanglePoint = new RectanglePoint();
        rectanglePoint.setLeftTop(new LocationPoint(lat + dlat, lng - dlng));
        rectanglePoint.setLeftBottom(new LocationPoint(lat - dlat, lng - dlng));
        rectanglePoint.setRightTop(new LocationPoint(lat + dlat, lng + dlng));
        rectanglePoint.setRightBottom(new LocationPoint(lat - dlat, lng + dlng));

        return rectanglePoint ;
    }
}