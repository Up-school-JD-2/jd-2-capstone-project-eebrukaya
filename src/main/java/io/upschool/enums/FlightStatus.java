package io.upschool.enums;

public enum FlightStatus {

    INDI("İndi"),
    KALKTI("Kalktı"),
    GECIKME("Gecikme"),
    KAPI_KAPANDI("Kapı Kapandı"),
    PLANLI("Planlı");

    private String statusName;

    FlightStatus(String statusName) {
        this.statusName = statusName;
    }

    public String toString() {
        return statusName;
    }

}
