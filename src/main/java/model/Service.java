package model;


import java.security.PrivateKey;
import  org.apache.logging.log4j.*;

/**
 * Service class represents a simple data model for storing service-related information.
 * It contains two fields: name and mobileNumber, along with their getters, setters, and a toString method.
 */

public class Service {

    // Private fields to store service name and mobile number
    private String name;
    private String mobileNumber;

      static final Logger logger= LogManager.getLogger(Service.class);
    /**
     * Constructor to initialize Service object with name and mobile number.
     * @param name Name of the service or person
     * @param mobileNumber Contact number associated with the service
     */

    public Service(String name, String mobileNumber) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        logger.info("Model Initilized");
    }

    public String getName() {

        logger.info("Name Retrieved");
        return name;
    }

    public void setName(String name) {
        this.name = name;
        logger.info("Name Initilized");
    }

    public String getMobileNumber() {

        logger.info("Mobile Number Retrieved");return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {

        this.mobileNumber = mobileNumber;
        logger.info("Mobile Number Initilized");
    }


    /**
     * Overrides the default toString method to provide a readable representation of the Service object.
     * @return String representation of Service object
     */

    @Override
    public String toString() {
        logger.info("Successfully Return");
        return "Service{" +
                "name='" + name + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                '}';

    }
}
