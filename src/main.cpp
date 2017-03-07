#include "Arduino.h"

#include "LED.h"
#include "Alarm.h"
#include "Photoresistor.h"
#include "Buzzer.h"
#include "Button.h"
#include "ComponentTester.h"
#include "Laser.h"
#include "Properties.h"

Alarm* alarm;

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
  Serial.begin(Properties::BAUD_RATE);  //Begin serial communication
  testBoardComponents();
  alarm = new Alarm();
  alarm->calibrate();

  if (Properties::MODULE_WIFI) {
    wifi = new Wifi(Properties::WIFI_RX_PIN, Properties::WIFI_TX_PIN);
    wifi->initialize();
  }
}

void loop(){
  if(not alarm->isArmed()){
    if(alarm->isButtonPressed()){
      alarm->arm();
    }
  } else {
    if(alarm->isTripped()){
      alarm->trigger();
    }
  }

  if(not alarm->isCalibrated()){
    if(alarm->isButtonPressed()){
      alarm->calibrate();
    }
  }
  wifi->checkTwoWayCommunication();
}
