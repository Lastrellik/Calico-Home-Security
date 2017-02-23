/*
  Alarm.h - A class for creating, arming, disarming, and calibrating the alarm
*/

#include "Arduino.h"
#include "Alarmbuilder.h"
#include "Component.h"
#include "LED.h"
#include "Buzzer.h"
#include "Laser.h"
#include "Photoresistor.h"
#include "Button.h"
#include "Alarm.h"

Alarm::Alarm(AlarmBuilder alarmBuilder){
  _alarmLED = alarmBuilder::getAlarmLED();
  _statusLED = alarmBuilder::getStatusLED();
  _buzzer = alarmBuilder::getBuzzer();
  _photoresistor = alarmBuilder::getPhotoresistor();
  _button = alarmBuilder::getButton();
}

Alarm::Alarm(LED alarmLED, LED statusLED, Buzzer buzzer, Laser laser, Photoresistor photoresistor, Button button){
  _alarmLED = alarmLED;
  _statusLED = statusLED;
  _buzzer = buzzer;
  _photoresistor = photoresistor;
  _button = button;
}
