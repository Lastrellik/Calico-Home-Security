/*
  Alarm.h - A class for creating, arming, disarming, and calibrating the alarm
*/

#ifndef Alarm_h
#define Alarm_h

#include "Arduino.h"
#include "Alarmbuilder.h"
#include "Component.h"
#include "LED.h"
#include "Buzzer.h"
#include "Laser.h"
#include "Photoresistor.h"
#include "Button.h"

class Alarm{
  private:
    Component _alarmLED; //LED for alarm status
    Component _statusLED; //LED for calibration and status
    Component _buzzer;
    Component _laser;
    Component _photoresistor;
    Component _button;

  public:
    void arm();
    void disarm();
    void calibrate();
    void silence();
    Alarm(AlarmBuilder alarmBuilder);
    Alarm(LED alarmLED, LED statusLED, Buzzer buzzer, Laser laser, Photoresistor photoresistor, Button button);
  };

#endif
