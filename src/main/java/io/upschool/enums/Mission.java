package io.upschool.enums;

public enum Mission {

    HOSTES("Hostes"),
    HOST("Host"),
    BIRINCI_PILOT("1.Pilot"),
    IKINCI_PILOT("2.Pilot");

    private String missionName;

    Mission(String missionName) {
        this.missionName = missionName;
    }

    public String toString() {
        return missionName;
    }

}
