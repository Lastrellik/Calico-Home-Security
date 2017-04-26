/**
  Calico Home Security System, ComponentTester.cpp
  Purpose: Main class that manages looping of other classes and manages
    sequentially checks of certain conditions.

  @author Christopher Nash, Jason Bruderer, David Tille, Tyler Jacobs
*/
#include "Arduino.h"

#include "LED.h"
#include "Alarm.h"
#include "Photoresistor.h"
#include "Buzzer.h"
#include "Button.h"
#include "ComponentTester.h"
#include "Laser.h"
#include "Properties.h"
#include "module_PI/Arduino_Uno/CommandListener.h"

Alarm* alarm;
CommandListener* commandListener;

long timeAlarmHasBeenTrippedInMillis = 0;

#include "module_WIFI/WifiModule.h"
WifiModule* wifiModule;

#include "module_TURRET/TurretMASTER.h"
TurretMASTER* turretMASTER;


/**
  Sets up the intial classes to be used like Alarm and Wifi
*/
void setup() {
  Serial.begin(Properties::BAUD_RATE);
  alarm = new Alarm();

  if(Properties::LASER_AIMING_MODE){
    alarm->toggleLaser();
    while(not alarm->isButtonPressed()) {
      // Loop here until the button is pressed
    }
    alarm->toggleLaser();
  }

  if(not Properties::MODULE_PI) alarm->testBoardComponents();
  commandListener = new CommandListener(alarm);
  if(not Properties::MODULE_PI) alarm->calibrate();

  if (Properties::MODULE_WIFI) {
    alarm->alertWaitingAction(); // Let the user know that we're preparing to connect
    wifiModule = new WifiModule();
    wifiModule->initialize();
    if(wifiModule->isInitialized()) {
      alarm->alertSuccessfulAction(); // Let the user know that everything is good
    } else {
      alarm->alertFailedAction();
      alarm->alertFailedAction(); // Let the user know that something didn't go right
      alarm->alertFailedAction();
    }
  }

  if (Properties::MODULE_TURRET) {
    turretMASTER = new TurretMASTER();
  }
}

/**
  Looping command for active listening or what is happening on the Arduino.
    Listens to see if the alarm has been trigged, calibrated, or armed.
*/
void loop(){
  commandListener->executeCommandIfAvailable();
  if(not alarm->isArmed()){
    if(alarm->isButtonPressed() && alarm->isCalibrated()){
      alarm->arm();
    }
  } else {
    if(alarm->isLaserBeamBroken() && not alarm->isTriggered() && not alarm->isTripped()){
      alarm->trip();
    }
  }

  if(not alarm->isCalibrated()){
    if(alarm->isButtonPressed()){
      alarm->calibrate();
    }
  }

  if(alarm->isTriggered()){
    alarm->soundOneAlarmCycle();
  }

  if((alarm->isTriggered() || alarm->isTripped()) && alarm->isButtonPressed()){
    alarm->disarm();
    if (Properties::MODULE_TURRET) {
      turretMASTER->resetStatus();
    }
  }

  if(not alarm->isTripped() && not alarm->isTriggered()){
    timeAlarmHasBeenTrippedInMillis = 0;
  }

  if(alarm->isArmed() && alarm->isButtonPressed()){
    alarm->disarm();
    if (Properties::MODULE_TURRET) {
      turretMASTER->resetStatus();
    }
  }

  if(alarm->isTripped()){
    long startTime = millis();
    alarm->alertWaitingAction();
    timeAlarmHasBeenTrippedInMillis += millis() - startTime;//Seconds it takes for the
    if(timeAlarmHasBeenTrippedInMillis >= Properties::ALARM_TRIPPED_TO_TRIGGERED_MILLIS){
      alarm->trigger();

      if (Properties::MODULE_WIFI && wifiModule->isInitialized()) {
        wifiModule->sendNotification(); // TODO: This is blocking. See https://github.com/Lastrellik/Calico-Home-Security/issues/62
      }

      if (Properties::MODULE_TURRET) {
        turretMASTER->sendTransmission(Properties::TURRET_SLAVE_NUMBER, Properties::TURRET_COMMAND);
      }
    }
  }
}
