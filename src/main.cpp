#include "Arduino.h"

#include "LED.h"
#include "Alarm.h"
#include "Photoresistor.h"
#include "Buzzer.h"
#include "Button.h"
#include "ComponentTester.h"
#include "Laser.h"
#include "Properties.h"
#include "module_PI/Arduino_Uno/SerialComm.h"
#include "module_Pi/Arduino_Uno/CommandListener.h"

Alarm* alarm;
SerialComm* serialComm;
CommandListener* commandListener;

#include "module_WIFI\Wifi.h"
Wifi* wifi;

//Test each of the components
void testBoardComponents(){
  LED greenLED(2);
  LED redLED(3);
  LED alarmLED(4);
  Photoresistor photoR(A0);
  Buzzer buzzer(6);
  Button armButton(7);
  Laser laser(8);

  ComponentTester tester(greenLED);
  tester.testPin();
  tester.testComponent(redLED);
  tester.testComponent(alarmLED);
  tester.testComponent(photoR);
  tester.testComponent(buzzer);
  tester.testComponent(laser);
  //tester.testComponent(armButton);
}

void setup() {
  delay(5000);
  Serial.begin(Properties::BAUD_RATE);
  testBoardComponents();
  alarm = new Alarm();
  serialComm = new SerialComm();
  commandListener = new CommandListener(alarm);
  alarm->calibrate();

  if (Properties::MODULE_WIFI) {
    wifi = new Wifi(Properties::WIFI_RX_PIN, Properties::WIFI_TX_PIN);
    wifi->initialize();
  }
}

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
