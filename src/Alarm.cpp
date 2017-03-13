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
}


void Alarm::calibrate(){
  determineBasePhotoresistorReading();
  if(_photoR->takeReading() - _threshold < _baseReading){
    alertFailedAction();
    _isCalibrated = false;
  } else {
    alertSuccessfulAction();
    _isCalibrated = true;
  }
}

void Alarm::determineBasePhotoresistorReading(){
  _laser->off();
  int avgReading = 0;
  int numOfReadings = 15;
  for(int i = 0; i < numOfReadings; i++){
    avgReading += _photoR->takeReading();
    _greenLED->flash();
  }
  _baseReading = avgReading / numOfReadings;
  _laser->on();
}

void Alarm::alertFailedAction(){
  _buzzer->soundNegativeTone();
  _redLED->flash(3000);
}

void Alarm::alertSuccessfulAction(){
  _buzzer->soundAffirmativeTone();
  _greenLED->flash(3000);
}

void Alarm::arm(){
  if (!this->isReadyToArm()){
   this->alertFailedAction();
 } else {
   this->alertSuccessfulAction();
    if(Properties::MODULE_PI) SerialComm::sendLogMessage("Alarm Armed");
   _alarmLED->on();
   _isArmed = true;
 }
}

bool Alarm::isReadyToArm(){
  return(!this->isArmed()
        && this->isCalibrated()
        && !this->isTripped()
        && !this->isTriggered());
}

bool Alarm::isArmed(){
  return _isArmed;
}

bool Alarm::isTripped(){
  return (_photoR->takeReading() - 100 < _baseReading);
}

void Alarm::trigger(){
  _isTriggered = true;
  if(Properties::MODULE_PI) SerialComm::sendLogMessage("Alarm Triggered");
  while(not _armButton->isPressed()){
    this->soundOneAlarmCycle();
  }
  this->alertSuccessfulAction();
  this->disarm();
}

void Alarm::soundOneAlarmCycle(){
  _buzzer->soundAlarmHighTone();
  _redLED->flash(300);
  _buzzer->soundAlarmLowTone();
  _alarmLED->flash(300);
}

bool Alarm::isTriggered(){
  return _isTriggered;
}

void Alarm::disarm(){
  _isTriggered = false;
  _isArmed = false;
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
