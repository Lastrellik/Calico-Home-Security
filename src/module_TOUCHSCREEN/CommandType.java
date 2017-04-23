package module_TOUCHSCREEN;

public enum CommandType {
	ARM, DISARM, SILENCE, CALIBRATE, DECALIBRATE, 
	TOGGLELASER, TRIP, TRIGGER, TESTCOMPONENTS;
	
	public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}
