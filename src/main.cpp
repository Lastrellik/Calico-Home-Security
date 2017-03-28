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

#include "module_WIFI\Wifi.h"
Wifi* wifi;
/**
  Setups the intial classes to be used like Alarm and Wifi
*/
void setup() {
  Serial.begin(Properties::BAUD_RATE);
  alarm = new Alarm();
  if(not Properties::MODULE_PI) alarm->testBoardComponents();
  commandListener = new CommandListener(alarm);
  if(not Properties::MODULE_PI) alarm->calibrate();

  if (Properties::MODULE_WIFI) {
    wifi = new Wifi(Properties::WIFI_RX_PIN, Properties::WIFI_TX_PIN);
    wifi->initialize();
  }
}
/**
  Looping command for active listening or what is happening on the Arduino.
    Listens to see if the alarm has been trigged, calibrated, or armed.
*/
void loop(){
  commandListener->executeCommandIfAvailable();
  if(not alarm->isArmed()){
    if(alarm->isButtonPressed()){
      alarm->arm();
    }
  } else {
    if(alarm->isTripped() && not alarm->isTriggered()){
      alarm->trigger();
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

  if(alarm->isTriggered() && alarm->isButtonPressed()){
    alarm->disarm();
  }

  if(alarm->isArmed() && alarm->isButtonPressed()){
    alarm->disarm();
  }
}
