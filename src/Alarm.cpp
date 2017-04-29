/**
  Calico Home Security System, Alarm.cpp
  Purpose: Alarm creaties an alarm ojbect, arms it, disarms it, and calibrates the paramaters
  for the alarm object

  @author Christopher Nash, Jason Bruderer, David Tille, Tyler Jacobs

*/
#include "Arduino.h"
#include "Component.h"
#include "LED.h"
#include "Buzzer.h"
#include "Laser.h"
#include "Photoresistor.h"
#include "Button.h"
#include "Alarm.h"
#include "ComponentTester.h"

/**
  Base contructor that writes that the Alarm Object has been created
*/
Alarm::Alarm(){
if(Properties::MODULE_PI)  Serial.write("13100"); // 13100 = Log, Debug, Alarm object successfully created
}

/**
  Takes in the base calibration of the light level using the
    determineBasePhotoresistorReading function and alerts of failed action
      if alertFailedAction and return isCalibrated as false, else it returns
      isCalibrated as true.
    @param isCalibrated is set to true or false
*/
void Alarm::calibrate(){
  determineBasePhotoresistorReading();
  if(_photoR->takeReading() - _threshold < _baseReading){
    alertFailedAction();
    _isCalibrated = false;
  } else {
    alertSuccessfulAction();
    if(Properties::MODULE_PI)  Serial.write("13106"); // 13106 = Log, Debug, Alarm succesful calibration
    _isCalibrated = true;
  }
  _laser->off();
}
/**
  Creates a ComponentTester object that makes sure that all components are
    running
*/
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

/**
  Determines a base of the photorsistor at normal light levels and then
    determines the value of the photorsistor when the laser is on it.
  @param avgReading a base for photorsistor at normal light, taken over time,
    determine the average light level of the room.
  @param numOfReadings hardcoded number used a for loop to help determines the
    base reading.
*/
void Alarm::determineBasePhotoresistorReading(){
  _laser->off();
  int avgReading = 0;
  int numOfReadings = 15;
  for(int i = 0; i < numOfReadings; i++){
    avgReading += _photoR->takeReading();
    _greenLED->flash();
  }
  _baseReading = avgReading / numOfReadings;
  if(Properties::MODULE_PI) Serial.write("13101"); // 13101 = Log, Debug, Alarm base photorsistor reading determined
  _laser->on();
}

/**
  Makes the buzzer produce the Negative tone
*/
void Alarm::alertFailedAction(){
  if(Properties::MODULE_PI) Serial.write("13102"); // 13102 = Log, Debug, Alarm failed action
  _buzzer->soundNegativeTone();
  _redLED->flash(1000);
}

/**
  Makes the buzzer produce the Affirmative tone
*/
void Alarm::alertSuccessfulAction(){
  if(Properties::MODULE_PI) Serial.write("13103"); // 13103 = Log, Debug, Alarm successful action
  _buzzer->soundAffirmativeTone();
  _greenLED->flash(1000);
}

/**
  Make an alert sound with lights to indicate the alarm is waiting.
*/
void Alarm::alertWaitingAction(){
  _redLED->flash(1000);
  _buzzer->soundTone(600, 300);
  _greenLED->flash(1000);
  _buzzer->soundTone(600, 300);
  _redLED->flash(1000);
}

/**
  Turns the LASER on and if it fails isReadyToArm turns it back off to as it the
    Alarm class needs to be calibrated again and leaves it on and sets isArmed
    to true and the _alarmLED to be on.
  @param _laser turns it on or keeps it off if calibration failed
  @param _alarmLED if alertSuccessfulAction is turned on
  @param _isArmed is alertSuccessfulAction is set to true
*/
void Alarm::arm(){
  if(Properties::MODULE_PI) Serial.write("13107"); // 13107 = Log, Debug, Alarm has begun arming
  _laser->on();
  if (!this->isReadyToArm()){
   Serial.write("13104"); // 13104 = Log, Debug, Alarm failed to arm
   this->alertFailedAction();
   if(!this->isArmed()) _laser->off();
 } else {
   this->alertSuccessfulAction();
    Serial.write("13105"); // 13105 = Log, Debug, Alarm successfully armed
   _alarmLED->on();
   _isArmed = true;
 }
}

/**
  @return returns true if the isArmed is not armed and isCalibrated is
    calibrated, and isTripped and isTriggered are not activated
*/
bool Alarm::isReadyToArm(){
  _laser->on();
  return(!this->isArmed()
      && this->isCalibrated()
      && !this->isTripped()
      && !this->isTriggered()
      && !this->isLaserBeamBroken());
}

/**
  Getter for the param _isArmed
*/
bool Alarm::isArmed(){
  return _isArmed;
}

bool Alarm::isLaserBeamBroken(){
  return (_photoR->takeReading() - 100 < _baseReading);
}

/**
  Getter for the param _isTripped
*/
bool Alarm::isTripped(){
  return _isTripped;
}

/**
  Sets the alarm to be triggered
  @param sets _isTriggered to be true
  @param sets _isSilenced to be false
  @param sets _isTripped to be false //Except these aren't parameters.
*/
void Alarm::trigger(){
  if(Properties::MODULE_PI) Serial.write("13108"); // 13108 = Log, Debug, Alarm has been triggered
  _isTriggered = true;
  _isSilenced = false;
  _isTripped = false;
}

/**
  Sets the alarm to be tripped
*/
void Alarm::trip(){
  if(Properties::MODULE_PI) Serial.write("13112"); // 13112 = Log, Debug, Alarm has been tripped
  _isTripped = true;
  _laser->off();
}

void Alarm::toggleLaser(){
  if(not _isCalibrated) _laser->toggle();
  if(Properties::MODULE_PI) Serial.write("13353"); // 13353 = Log, Debug, Laser has been toggled
}

/**
  If _isSilenced is not set soundAlarmHighTone and soundAlarmLowTone are used
    to set tone high and low, also tells _redLED and _alarmLED to flash
*/
void Alarm::soundOneAlarmCycle(){
  if (not this->_isSilenced) _buzzer->soundAlarmHighTone();
  _redLED->flash(300);
  if (not this->_isSilenced) _buzzer->soundAlarmLowTone();
  _alarmLED->flash(300);
}

/**
  Disarms the alarm and sets the calibration to false to resetCalibration
  @param _isCalibrated is set to false
*/
void Alarm::resetCalibration(){
  if(Properties::MODULE_PI) Serial.write("13109"); // 13109 = Log, Debug, Alarm calibration is being reset
  disarm();
  _isCalibrated = false;
}

/**
  @return _isTriggered getter
*/
bool Alarm::isTriggered(){
  return _isTriggered;
}

/**
  Used to disarm the alarm
  @param _isTriggered is set to false
  @param _isArmed is set to false
  @param _alarmLED is turned off
  @param _isSilenced is set to false
  @param _laser is turned off
*/
void Alarm::disarm(){
  this->alertSuccessfulAction();
  _isTriggered = false;
  _isTripped = false;
  _isArmed = false;
  _alarmLED->off();
  _isSilenced = false;
  _laser->off();
  Serial.write("13110"); // 13110 = Log, Debug, Alarm is being disarmed
}

/**
  Silences the alarm by setting _isSilenced to true
*/
void Alarm::silence(){
  _isSilenced = true;
  Serial.write("13111"); // 13111 = Log, Debug, Alarm is being silenced
}

/**
  @return getter for _isCalibrated
*/
bool Alarm::isCalibrated(){
  return _isCalibrated;
}

/**
  @return getter for _armButton by using the isPressed function from Button
*/
bool Alarm::isButtonPressed(){
  return _armButton->isPressed();
}
