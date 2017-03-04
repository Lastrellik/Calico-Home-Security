#include "Arduino.h"

#include "LED.h"
#include "Alarm.h"
#include "Photoresistor.h"
#include "Buzzer.h"
#include "Button.h"
#include "ComponentTester.h"
#include "Laser.h"

Alarm* alarm;

int baudRate = 9600;

// TODO: Add conditional if around this section
#include "module_WIFI\Wifi.h"
Wifi* wifi;
int rxPin = 10;
int txPin = 11;
String ssid = "YOUR_SSID_HERE";
String password = "YOUR_WIRELESS_PASSWORD";

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
  Serial.begin(baudRate);  //Begin serial communication
  testBoardComponents();
  alarm = new Alarm();
  alarm->calibrate();

  wifi = new Wifi(rxPin, txPin);
  wifi->initialize();
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
