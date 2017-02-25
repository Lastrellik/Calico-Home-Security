/*
  Alarm.h - A class for creating, arming, disarming, and calibrating the alarm
*/

#include "Arduino.h"
#include "Component.h"
#include "LED.h"
#include "Buzzer.h"
#include "Laser.h"
#include "Photoresistor.h"
#include "Button.h"
#include "Alarm.h"

Alarm::Alarm(){
  _isArmed = false;
  _isCalibrated = false;
  _threshold = 100;
}


void Alarm::calibrate(){
  _laser->disengage();
  delay(500);
  int avgReading = 0;
  int numOfReadings = 15;
  for(int i = 0; i < numOfReadings; i++){
    avgReading += _photoR->takeReading();
    delay(50);
    _greenLED->on();
    delay(50);
    _greenLED->off();
  }
  avgReading /= numOfReadings; //get the average
  _baseReading = avgReading;
  delay(1000);
  _laser->engage();
  delay(500);
  if(_photoR->takeReading() - 100 < _baseReading){
    _buzzer->soundTone(500, 100);
    _buzzer->soundTone(300, 150);
    _redLED->on();
    _alarmLED->on();
    delay(3000);
    _redLED->off();
    _alarmLED->off();
    _isCalibrated = false;
  } else {
    _buzzer->soundTone(500, 100);
    _buzzer->soundTone(700, 150);
    _greenLED->on();
    _isCalibrated = true;
  }
}

void Alarm::arm(){
  if (_isCalibrated){
    _greenLED->off();
    _alarmLED->on();
    _isArmed = true;
  } else {
    _buzzer->soundTone(500, 100);
    _buzzer->soundTone(300, 150);
  }
}

bool Alarm::isArmed(){
  return _isArmed;
}

bool Alarm::isTripped(){
  return (_photoR->takeReading() - 100 < _baseReading);
}

void Alarm::trigger(){
  _isTriggered = true;
  while(not _armButton->isPressed()){
    _redLED->on();
    delay(100);
    _redLED->off();
    delay(100);
    _alarmLED->on();
    delay(100);
    _alarmLED->off();
    delay(100);
  }
  //It's late. I'm just going to do this until next time.
  _greenLED->on();
  _alarmLED->off();
  _redLED->off();
  _isTriggered = false;
  _isArmed = false;
}

bool Alarm::isTriggered(){
  return _isTriggered;
}
void Alarm::disarm(){
  _isTriggered = false;

}
void Alarm::silence(){

}

bool Alarm::isCalibrated(){
  return _isCalibrated;
}

bool Alarm::isButtonPressed(){
  return _armButton->isPressed();
}
void Alarm::setGreenLED(LED& greenLED){
  _greenLED = &greenLED;
}
void Alarm::setRedLED(LED& redLED){
  _redLED = &redLED;
}
void Alarm::setAlarmLED(LED& alarmLED){
  _alarmLED = &alarmLED;
}
void Alarm::setBuzzer(Buzzer& buzzer){
  _buzzer = &buzzer;
}
void Alarm::setLaser(Laser& laser){
  _laser = &laser;
}
void Alarm::setPhotoresistor(Photoresistor& photoresistor){
  _photoR = &photoresistor;
}
void Alarm::setButton(Button& button){
  _armButton = &button;
}
