/*
  AlarmBuilder.h - A class dedicated to the correct construction of the Alarm
  The engineering paradigm used here is the "fluent" pattern. Read about it here:
  https://dzone.com/articles/factories-builders-and-fluent- and here:
  http://stackoverflow.com/questions/40264/how-many-constructor-arguments-is-too-many
  and here:
  http://stackoverflow.com/questions/222214/managing-constructors-with-many-parameters-in-java
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

AlarmBuilder::AlarmBuilder(void) {

}

AlarmBuilder AlarmBuilder::alarmLED(LED alarmLED){
  _alarmLED = alarmLED;
  return *this;
}


AlarmBuilder AlarmBuilder::statusLED(LED statusLED){
  _statusLED = statusLED;
  return *this;
}

AlarmBuilder AlarmBuilder::buzzer(Buzzer buzzer){
  _buzzer = buzzer;
  return *this;
}

AlarmBuilder AlarmBuilder::laser(Laser laser){
  _laser = laser;
  return *this;
}

AlarmBuilder AlarmBuilder::photoresistor(Photoresistor photoresistor){
  _photoresistor = photoresistor;
  return *this;
}

AlarmBuilder AlarmBuilder::button(Button button){
  _button = button;
  return *this;
}

Alarm AlarmBuilder::buildAlarm(void){
  return new Alarm(_alarmLED, _statusLED, _buzzer, _laser, _photoresistor, _button);
}
LED AlarmBuilder::getAlarmLED(void){
  return _alarmLED;
}
LED AlarmBuilder::getStatusLED(void){
  return _statusLED;
}
Buzzer AlarmBuilder::getBuzzer(void){
  return _buzzer;
}
Laser AlarmBuilder::getLaser(void){
  return _laser;
}
Photoresistor AlarmBuilder::getPhotoresistor(void){
  return _photoresistor;
}
Button AlarmBuilder::getButton(void){
  return _button;
}
