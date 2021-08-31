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
     * @return the distance (unit meter)
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
     * Gets rectangle range.
     *
     * @param point    the point
     * @param distance the distance (unit meter)
     * @return the rectangle 4 point
     */
    public static RectangleRange getRectanglePoint(LocationPoint point, double distance) {
        // double dlng = 2 * Math.asin(Math.sin(distance / (2 * EARTH_RADIUS)) / Math.cos(lat));
        double lat = point.getLatitude();
        double lng = point.getLongitude();
        double dlng = 2 * Math.asin(Math.sin(distance / (2 * EARTH_RADIUS)) / Math.cos(Math.toRadians(lat)));
        dlng = Math.toDegrees(dlng);

        double dlat = distance / EARTH_RADIUS;
        dlat = Math.toDegrees(dlat); // # 弧度转换成角度

        RectangleRange rectangleRange = new RectangleRange();
        rectangleRange.setLeftTop(new LocationPoint(lat + dlat, lng - dlng));
        rectangleRange.setLeftBottom(new LocationPoint(lat - dlat, lng - dlng));
        rectangleRange.setRightTop(new LocationPoint(lat + dlat, lng + dlng));
        rectangleRange.setRightBottom(new LocationPoint(lat - dlat, lng + dlng));

        return rectangleRange;
    }
}