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
#include "Logger.h"

Alarm::Alarm(){
}


void Alarm::calibrate(){
  determineBasePhotoresistorReading();
  if(_photoR->takeReading() - _threshold < _baseReading){
    alertFailedAction();
    _isCalibrated = false;
  } else {
    alertSuccessfulAction();
    Logger::Log("Alarm Successful Calibration");
    _isCalibrated = true;
  }
}

void Alarm::determineBasePhotoresistorReading(){
  _laser->off();
  int avgReading = 0;
  int numOfReadings = 15;
  for(int i = 0; i < numOfReadings; i++){
    avgReading += _photoR->takeReading();
    //_greenLED->flash();
  }
  _baseReading = avgReading / numOfReadings;
  _laser->on();
  //Logger::Log("Base Photoresistor Reading: ");
}

void Alarm::alertFailedAction(){
  _buzzer->soundNegativeTone();
  _redLED->flash(1000);
  Logger::Log("Alarm Failed Action");
}

void Alarm::alertSuccessfulAction(){
  _buzzer->soundAffirmativeTone();
  _greenLED->flash(1000);
  Logger::Log("Alarm successful action");
}

void Alarm::arm(){
  if (!this->isReadyToArm()){
   this->alertFailedAction();
   Logger::Log("Alarm failed to arm");
 } else {
   this->alertSuccessfulAction();
    Logger::Log("Alarm sucessfully armed");
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
  while(not _armButton->isPressed()){
    this->soundOneAlarmCycle();
  }
  this->alertSuccessfulAction();
  this->disarm();
}

void Alarm::soundOneAlarmCycle(){
  if (not this->_isSilenced) _buzzer->soundAlarmHighTone();
  _redLED->flash(300);
  if (not this->_isSilenced) _buzzer->soundAlarmLowTone();
  _alarmLED->flash(300);
}

bool Alarm::isTriggered(){
  return _isTriggered;
}

void Alarm::disarm(){
  _isTriggered = false;
  _isArmed = false;
  Logger::Log("Alarm has been disarmed");
}
void Alarm::silence(){
  _isSilenced = true;
  Logger::Log("Alarm has been silenced");
}

bool Alarm::isCalibrated(){
  return _isCalibrated;
}

bool Alarm::isButtonPressed(){
  return _armButton->isPressed();
}
