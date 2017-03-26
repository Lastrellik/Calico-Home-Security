/**
  Calico Home Security System, Alarm.cpp
  Purpose: Alarm creaties an alarm ojbect, arms it, disarms it, and calibrates the paramaters
  for the alarm object

  @author Chris Nash, Jason Bruderer, David Tille, Tyler Jacobs
  @version To be Determined
*/

/**

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

/**
  Base contructor that writes that the Alarm Object has been created
*/
Alarm::Alarm(){
  Logger::Log("Alarm has been created");
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
    Logger::Log("Alarm Successful Calibration");
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
  Logger::Log("Base Photoresistor Reading Determined");
  _laser->on();
}
/**
  Makes the buzzer produce the Negative tone
*/
void Alarm::alertFailedAction(){
  Logger::Log("Alarm Failed Action");
  _buzzer->soundNegativeTone();
  _redLED->flash(1000);
}
/**
  Makes the buzzer produce the Affirmative tone
*/
void Alarm::alertSuccessfulAction(){
  Logger::Log("Alarm successful action");
  _buzzer->soundAffirmativeTone();
  _greenLED->flash(1000);
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
  _laser->on();
  if (!this->isReadyToArm()){
   Logger::Log("Alarm failed to arm");
   this->alertFailedAction();
   if(!this->isArmed()) _laser->off();
 } else {
   this->alertSuccessfulAction();
    Logger::Log("Alarm sucessfully armed");
   _alarmLED->on();
   _isArmed = true;
 }
}
/**
  @return returns true if the isArmed is not armed and isCalibrated is
    calibrated, and isTripped and isTriggered are not activated
*/
bool Alarm::isReadyToArm(){
  return(!this->isArmed()
        && this->isCalibrated()
        && !this->isTripped()
        && !this->isTriggered());
}
/**
  Getter for the param _isArmed
*/
bool Alarm::isArmed(){
  return _isArmed;
}
/**
  @return returns a true false based on whether or the reading taken is less
    than the _baseReading by 100
*/
bool Alarm::isTripped(){
  return (_photoR->takeReading() - 100 < _baseReading);
}
/**
  Sets the alarm to be triggered
  @param sets _isTriggered to be true
  @param sets _isSilenced to be false
*/
void Alarm::trigger(){
  Logger::Log("Alarm has been triggered!");
  _isTriggered = true;
  _isSilenced = false;
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
  _isArmed = false;
  _alarmLED->off();
  _isSilenced = false;
  _laser->off();
  Logger::Log("Alarm has been disarmed");
}
/**
  Silences the alarm by setting _isSilenced to true
*/
void Alarm::silence(){
  _isSilenced = true;
  Logger::Log("Alarm has been silenced");
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
