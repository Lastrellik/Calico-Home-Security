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
#include "ComponentTester.h"

Alarm::Alarm(){
  Logger::Log("Alarm has been created");
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
  _laser->off();
}

void Alarm::testBoardComponents(){
  ComponentTester tester(_greenLED);
  tester.testPin();
  tester.testComponent(_redLED);
  tester.testComponent(_alarmLED);
  tester.testComponent(_photoR);
  tester.testComponent(_buzzer);
  tester.testComponent(_laser);
  //tester.testComponent(_armButton);

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
  Logger::Log("Base Photoresistor Reading Determined");
  _laser->on();
}

void Alarm::alertFailedAction(){
  Logger::Log("Alarm Failed Action");
  _buzzer->soundNegativeTone();
  _redLED->flash(1000);
}

void Alarm::alertSuccessfulAction(){
  Logger::Log("Alarm successful action");
  _buzzer->soundAffirmativeTone();
  _greenLED->flash(1000);
}

void Alarm::arm(){
  _laser->on();
  if (!this->isReadyToArm()){
   Logger::Log("Alarm failed to arm");
   this->alertFailedAction();
   _laser->off();
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
  Logger::Log("Alarm has been triggered!");
  _isTriggered = true;
  _isSilenced = false;
}

void Alarm::soundOneAlarmCycle(){
  if (not this->_isSilenced) _buzzer->soundAlarmHighTone();
  _redLED->flash(300);
  if (not this->_isSilenced) _buzzer->soundAlarmLowTone();
  _alarmLED->flash(300);
}

void Alarm::resetCalibration(){
  disarm();
  _isCalibrated = false;
}

bool Alarm::isTriggered(){
  return _isTriggered;
}

void Alarm::disarm(){
  this->alertSuccessfulAction();
  _isTriggered = false;
  _isArmed = false;
  _alarmLED->off();
  _isSilenced = false;
  _laser->off();
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
