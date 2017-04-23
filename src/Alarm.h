/**
  Calico Home Security System, Alarm.h
  Purpose: Alarm creaties an alarm ojbect, arms it, disarms it, and calibrates the paramaters
  for the alarm object

  @author Christopher Nash, Jason Bruderer, David Tille, Tyler Jacobs

*/

#ifndef Alarm_h
#define Alarm_h

#include "Arduino.h"
#include "Component.h"
#include "LED.h"
#include "Buzzer.h"
#include "Laser.h"
#include "Photoresistor.h"
#include "Button.h"
#include "Properties.h"

class Alarm{

  public:
    Alarm();
    void calibrate();
    void testBoardComponents();
    void arm();
    void disarm();
    void silence();
    void trigger();
    void trip();
    bool isButtonPressed();
    bool isCalibrated();
    bool isArmed();
    bool isLaserBeamBroken();
    bool isTripped();
    bool isTriggered();
    bool isReadyToArm();
    void soundOneAlarmCycle();
    void resetCalibration();
    void alertFailedAction();
    void alertSuccessfulAction();
    void alertWaitingAction();
    void toggleLaser();
    /**
      @param _greenLED, _redLED, _alarmLED used for LEDs on the breadboard
        creates LED objects to manage them
      @param _photoR used for value of photorsistor
        creates Photoresistor object to manage it
      @param _buzzer used to set Buzzer value
        creates Buzzer object to manage it
      @param _armButton used to determine state of Button on or off
        creates a Button object to manage it
      @param _laser used to manage the laser status on or off
        creats a Lser object to manage it
      @param bools are used to determine the state of the status of what they're
        named for
    */
  private:
    LED* _greenLED = new LED(2);
    LED* _redLED = new LED(3);
    LED* _alarmLED = new LED(4);
    Photoresistor* _photoR = new Photoresistor(A0);
    Buzzer* _buzzer = new Buzzer(6);
    Button* _armButton = new Button(7);
    Laser* _laser = new Laser(8);
    bool _isArmed = false;
    bool _isCalibrated = false;
    bool _isTriggered = false;
    bool _isSilenced = false;
    bool _isTripped = false;
    int _baseReading = 0;
    int _threshold = 100;
    void determineBasePhotoresistorReading();
  };

#endif
