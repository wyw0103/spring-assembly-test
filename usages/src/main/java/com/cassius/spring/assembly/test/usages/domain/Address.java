package com.cassius.spring.assembly.test.usages.domain;

/**
 * Address
 *
 * @author Cassius Cai
 * @version v 0.1 5/30/15 15:21 Exp $
 */
public class Address {

    /**
     * The Region.
     */
    private String region;
    /**
     * The Province.
     */
    private String province;
    /**
     * The City.
     */
    private String city;
    /**
     * The Street.
     */
    private String street;

    /**
     * Instantiates a new Address.
     */
    private Address() {
    }

    /**
     * Region i province.
     *
     * @param region the region
     * @return the i province
     */
    public static IProvince region(String region) {
        return new Address.Builder(region);
    }

    /**
     * The interface I province.
     */
    public interface IProvince {
        /**
         * Province i city.
         *
         * @param province the province
         * @return the i city
         */
        ICity province(String province);
    }

    /**
     * The interface I city.
     */
    public interface ICity {
        /**
         * City i street.
         *
         * @param city the city
         * @return the i street
         */
        IStreet city(String city);
    }

    /**
     * The interface I street.
     */
    public interface IStreet {
        /**
         * Street i build.
         *
         * @param street the street
         * @return the i build
         */
        IBuild street(String street);
    }

    /**
     * The interface I build.
     */
    public interface IBuild {
        /**
         * Build address.
         *
         * @return the address
         */
        Address build();
    }

    /**
     * The type Builder.
     */
    private static class Builder implements IProvince, ICity, IStreet, IBuild {
        /**
         * The Instance.
         */
        private Address instance = new Address();

        /**
         * Instantiates a new Builder.
         *
         * @param region the region
         */
        private Builder(String region) {
            instance.region = region;
        }

        /**
         * Province i city.
         *
         * @param province the province
         * @return the i city
         */
        @Override
        public ICity province(String province) {
            instance.province = province;
            return this;
        }

        /**
         * City i street.
         *
         * @param city the city
         * @return the i street
         */
        @Override
        public IStreet city(String city) {
            instance.city = city;
            return this;
        }

        /**
         * Street i build.
         *
         * @param street the street
         * @return the i build
         */
        @Override
        public IBuild street(String street) {
            instance.street = street;
            return this;
        }

        /**
         * Build address.
         *
         * @return the address
         */
        public Address build() {
            return instance;
        }
    }
}
