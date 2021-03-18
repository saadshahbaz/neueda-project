package ca.mcgill.ecse321.arms.dto;

import ca.mcgill.ecse321.arms.model.Car;
import ca.mcgill.ecse321.arms.model.Service;
import ca.mcgill.ecse321.arms.model.TimeSlot;

public class AppointmentDto {
    private int appointmentID;
    private String serviceName;
    private String plateNo;
    private String businessName;
    private String startDate;
    private String startTime;
    private String endDate;
    private String endTime;
    private int spaceID;
    private int technicianID;

    /**
     * @param appointmentID
     * @param serviceName
     * @param plateNo
     * @param businessName
     * @param startDate
     * @param startTime
     * @param endDate
     * @param endTime
     * @param spaceID
     * @param technicianID
     * @author Grey Yuan
     */
    public AppointmentDto(int appointmentID, String serviceName, String plateNo,
                          String businessName,String startDate, String startTime,
                          String endDate, String endTime,int spaceID,int technicianID){
        this.appointmentID = appointmentID;
        this.serviceName = serviceName;
        this.plateNo = plateNo;
        this.businessName = businessName;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.spaceID = spaceID;
        this.technicianID = technicianID;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public String getBusinessName() {
        return businessName;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getEndTime() {
        return endTime;
    }

    public int getSpaceID() {
        return spaceID;
    }
}
