
package cn.featherfly.common.http.po;

import cn.featherfly.common.location.LocationPoint;

/**
 * ReverseGeocoding.
 *
 * @author zhongj
 */
public class ReverseGeocoding {

    private Integer status;

    private Result result;

    /**
     * get status value
     *
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * set status value
     *
     * @param status status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * get result value
     *
     * @return result
     */
    public Result getResult() {
        return result;
    }

    /**
     * set result value
     *
     * @param result result
     */
    public void setResult(Result result) {
        this.result = result;
    }

    public static class Result {

        private LocationPoint location;
        private String formatted_address;
        private String business;
        private Integer cityCode;
        private String sematic_description;

        //      pois: [ ],
        //    roads: [ ],
        //    poiRegions: [ ],

        /**
         * get location value
         *
         * @return location
         */
        public LocationPoint getLocation() {
            return location;
        }

        /**
         * set location value
         *
         * @param location location
         */
        public void setLocation(LocationPoint location) {
            this.location = location;
        }

        /**
         * get formatted_address value
         *
         * @return formatted_address
         */
        public String getFormatted_address() {
            return formatted_address;
        }

        /**
         * set formatted_address value
         *
         * @param formatted_address formatted_address
         */
        public void setFormatted_address(String formatted_address) {
            this.formatted_address = formatted_address;
        }

        /**
         * get business value
         *
         * @return business
         */
        public String getBusiness() {
            return business;
        }

        /**
         * set business value
         *
         * @param business business
         */
        public void setBusiness(String business) {
            this.business = business;
        }

        /**
         * get cityCode value
         *
         * @return cityCode
         */
        public Integer getCityCode() {
            return cityCode;
        }

        /**
         * set cityCode value
         *
         * @param cityCode cityCode
         */
        public void setCityCode(Integer cityCode) {
            this.cityCode = cityCode;
        }

        /**
         * get sematic_description value
         *
         * @return sematic_description
         */
        public String getSematic_description() {
            return sematic_description;
        }

        /**
         * set sematic_description value
         *
         * @param sematic_description sematic_description
         */
        public void setSematic_description(String sematic_description) {
            this.sematic_description = sematic_description;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return "Result [location=" + location + ", formatted_address=" + formatted_address + ", business="
                    + business + ", cityCode=" + cityCode + ", sematic_description=" + sematic_description + "]";
        }

    }

    public static class AddressComponent {
        private String country;
        private Integer countryCode;
        private String country_code_iso;
        private String country_code_iso2;
        private String province;
        private String city;
        private Integer city_level;
        private String district;
        private String town;
        private String town_code;
        private String adcode;
        private String street;
        private String street_number;
        private String direction;
        private String distance;

        /**
         * get country value
         *
         * @return country
         */
        public String getCountry() {
            return country;
        }

        /**
         * set country value
         *
         * @param country country
         */
        public void setCountry(String country) {
            this.country = country;
        }

        /**
         * get countryCode value
         *
         * @return countryCode
         */
        public Integer getCountryCode() {
            return countryCode;
        }

        /**
         * set countryCode value
         *
         * @param countryCode countryCode
         */
        public void setCountryCode(Integer countryCode) {
            this.countryCode = countryCode;
        }

        /**
         * get country_code_iso value
         *
         * @return country_code_iso
         */
        public String getCountry_code_iso() {
            return country_code_iso;
        }

        /**
         * set country_code_iso value
         *
         * @param country_code_iso country_code_iso
         */
        public void setCountry_code_iso(String country_code_iso) {
            this.country_code_iso = country_code_iso;
        }

        /**
         * get country_code_iso2 value
         *
         * @return country_code_iso2
         */
        public String getCountry_code_iso2() {
            return country_code_iso2;
        }

        /**
         * set country_code_iso2 value
         *
         * @param country_code_iso2 country_code_iso2
         */
        public void setCountry_code_iso2(String country_code_iso2) {
            this.country_code_iso2 = country_code_iso2;
        }

        /**
         * get province value
         *
         * @return province
         */
        public String getProvince() {
            return province;
        }

        /**
         * set province value
         *
         * @param province province
         */
        public void setProvince(String province) {
            this.province = province;
        }

        /**
         * get city value
         *
         * @return city
         */
        public String getCity() {
            return city;
        }

        /**
         * set city value
         *
         * @param city city
         */
        public void setCity(String city) {
            this.city = city;
        }

        /**
         * get city_level value
         *
         * @return city_level
         */
        public Integer getCity_level() {
            return city_level;
        }

        /**
         * set city_level value
         *
         * @param city_level city_level
         */
        public void setCity_level(Integer city_level) {
            this.city_level = city_level;
        }

        /**
         * get district value
         *
         * @return district
         */
        public String getDistrict() {
            return district;
        }

        /**
         * set district value
         *
         * @param district district
         */
        public void setDistrict(String district) {
            this.district = district;
        }

        /**
         * get town value
         *
         * @return town
         */
        public String getTown() {
            return town;
        }

        /**
         * set town value
         *
         * @param town town
         */
        public void setTown(String town) {
            this.town = town;
        }

        /**
         * get town_code value
         *
         * @return town_code
         */
        public String getTown_code() {
            return town_code;
        }

        /**
         * set town_code value
         *
         * @param town_code town_code
         */
        public void setTown_code(String town_code) {
            this.town_code = town_code;
        }

        /**
         * get adcode value
         *
         * @return adcode
         */
        public String getAdcode() {
            return adcode;
        }

        /**
         * set adcode value
         *
         * @param adcode adcode
         */
        public void setAdcode(String adcode) {
            this.adcode = adcode;
        }

        /**
         * get street value
         *
         * @return street
         */
        public String getStreet() {
            return street;
        }

        /**
         * set street value
         *
         * @param street street
         */
        public void setStreet(String street) {
            this.street = street;
        }

        /**
         * get street_number value
         *
         * @return street_number
         */
        public String getStreet_number() {
            return street_number;
        }

        /**
         * set street_number value
         *
         * @param street_number street_number
         */
        public void setStreet_number(String street_number) {
            this.street_number = street_number;
        }

        /**
         * get direction value
         *
         * @return direction
         */
        public String getDirection() {
            return direction;
        }

        /**
         * set direction value
         *
         * @param direction direction
         */
        public void setDirection(String direction) {
            this.direction = direction;
        }

        /**
         * get distance value
         *
         * @return distance
         */
        public String getDistance() {
            return distance;
        }

        /**
         * set distance value
         *
         * @param distance distance
         */
        public void setDistance(String distance) {
            this.distance = distance;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return "AddressComponent [country=" + country + ", countryCode=" + countryCode + ", country_code_iso="
                    + country_code_iso + ", country_code_iso2=" + country_code_iso2 + ", province=" + province
                    + ", city=" + city + ", city_level=" + city_level + ", district=" + district + ", town=" + town
                    + ", town_code=" + town_code + ", adcode=" + adcode + ", street=" + street + ", street_number="
                    + street_number + ", direction=" + direction + ", distance=" + distance + "]";
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "ReverseGeocoding [status=" + status + ", result=" + result + ", getStatus()=" + getStatus()
                + ", getResult()=" + getResult() + "]";
    }

}
