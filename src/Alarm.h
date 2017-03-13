/*
  Alarm.h - A class for creating, arming, disarming, and calibrating the alarm
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
#include "module_TOUCHSCREEN/SerialComm.h"
#include "Properties.h"

class Alarm{

  public:
    Alarm();
    void calibrate();
    void arm();
    void disarm();
    void silence();
    void setGreenLED(LED& greenLED);
    void setRedLED(LED& redLED);
    void setAlarmLED(LED& alarmLED);
    void setBuzzer(Buzzer& buzzer);
    void setLaser(Laser& laser);
    void setPhotoresistor(Photoresistor& photoresistor);
    void setButton(Button& button);
    void trigger();
    bool isButtonPressed();
    bool isCalibrated();
    bool isArmed();
    bool isTripped();
    bool isTriggered();
    bool isReadyToArm();
    void soundOneAlarmCycle();

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
    int _baseReading = 0;
    int _threshold = 100;
    void determineBasePhotoresistorReading();
    void alertFailedAction();
    void alertSuccessfulAction();
  };

#endif
