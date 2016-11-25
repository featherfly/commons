package cn.featherfly.common.location;

/**
 * <p>
 * 定位点（经度，纬度对应的一个点）
 * </p>
 * 
 * @author 钟冀
 * @since 1.6
 * @version 1.0
 */
public class LocationPoint {

    private double latitude = 0d;

    private double longitude = 0d;

    /**
     * Instantiates a new Location point.
     */
    public LocationPoint() {
    }

    /**
     * Instantiates a new Location point.
     *
     * @param latitude the latitude
     * @param longitude the longitude
     */
    public LocationPoint(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Gets latitude.
     *
     * @return the latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Sets latitude.
     *
     * @param latitude the latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Gets longitude.
     *
     * @return the longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Sets longitude.
     *
     * @param longitude the longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}