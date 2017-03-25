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
  Serial.write(14016); //14016 = Log, Info Alarm Created
}


void Alarm::calibrate(){
  determineBasePhotoresistorReading();
  if(_photoR->takeReading() - _threshold < _baseReading){
    alertFailedAction();
    _isCalibrated = false;
  } else {
    alertSuccessfulAction();
    Serial.write(34004)); //34004 = Command, Info Execute Command calibrate
    _isCalibrated = true;
  }
  _laser->off();
}

void Alarm::testBoardComponents(){
  if (isArmed()) disarm();
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
  Serial.write(24021); //24021 = Data, Info, Base Photoresistor Data
  _laser->on();
}

void Alarm::alertFailedAction(){
  Serial.write(14017); //14017 = Log, Info, Alarm Failed
  _buzzer->soundNegativeTone();
  _redLED->flash(1000);
}

void Alarm::alertSuccessfulAction(){
  Serial.write(14018); //14018 = Log, Info, Alarm Successful
  _buzzer->soundAffirmativeTone();
  _greenLED->flash(1000);
}

void Alarm::arm(){
  _laser->on();
  if (!this->isReadyToArm()){
   Serial.write(14019); //14011 = Log, Info, Alarm Failed Arming
   this->alertFailedAction();
   if(!this->isArmed()) _laser->off();
 } else {
   this->alertSuccessfulAction();
    Serial.write(14018); //14018 = Log, Info, Alarm Successful
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
  Serial.write(34005); //34005 = Command, Info, Excuet Command: Trigger
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
  Serial.write(14006); //14006 = Log, Info, Execute Command: Reset Calibration
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
  Serial.write(34002); //34002 = Command, Info, Execute Command: Disarm
}
void Alarm::silence(){
  _isSilenced = true;
  Serial.write(34003); //34003 = Command, Info, Excuet Command: Silence
}

bool Alarm::isCalibrated(){
  return _isCalibrated;
}

bool Alarm::isButtonPressed(){
  return _armButton->isPressed();
}
