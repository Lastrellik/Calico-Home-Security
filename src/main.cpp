/**
  Calico Home Security System, ComponentTester.cpp
  Purpose: Main class that manages looping of other classes and manages
    sequentially checks of certain conditions.

  @author Christopher Nash, Jason Bruderer, David Tille, Tyler Jacobs
  @version To be Determined
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
#include "module_Pi/Arduino_Uno/CommandListener.h"

Alarm* alarm;
CommandListener* commandListener;
long timeAlarmHasBeenTrippedInMillis = 0;
const long TIME_ALARM_CAN_BE_TRIPPED_IN_MILLIS = 30000;
#include "module_WIFI\WifiModule.h"
WifiModule* wifiModule;

/**
  Sets up the intial classes to be used like Alarm and Wifi
*/
void setup() {
  Serial.begin(Properties::BAUD_RATE);
  alarm = new Alarm();
  if(not Properties::MODULE_PI) alarm->testBoardComponents();
  commandListener = new CommandListener(alarm);
  if(not Properties::MODULE_PI) alarm->calibrate();

  if (Properties::MODULE_WIFI) {
    alarm->alertWaitingAction();
    wifiModule = new WifiModule();
    wifiModule->initialize();
    alarm->alertSuccessfulAction();
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
      if (Properties::MODULE_WIFI) {
        wifiModule->sendNotification(); // TODO: This is blocking. See https://github.com/Lastrellik/Calico-Home-Security/issues/62
      }
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
    timeAlarmHasBeenTrippedInMillis = 0;
  }

  if(alarm->isArmed() && alarm->isButtonPressed()){
    alarm->disarm();
    timeAlarmHasBeenTrippedInMillis = 0;
  }

  if(alarm->isTripped()){
    long startTime = millis();
    alarm->alertWaitingAction();
    timeAlarmHasBeenTrippedInMillis += millis() - startTime;//Seconds it takes for the
    if(timeAlarmHasBeenTrippedInMillis >= TIME_ALARM_CAN_BE_TRIPPED_IN_MILLIS){
      alarm->trigger();
    }
  }
}
