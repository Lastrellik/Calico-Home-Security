#include "Arduino.h"


#include "LED.h"
#include "Alarm.h"
#include "Photoresistor.h"
#include "Buzzer.h"
#include "Button.h"
#include "ComponentTester.h"
#include "Laser.h"
#include "SerialComm.h"

Alarm* alarm;
SerialComm* serialComm;
int iterations = 0;

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
  Serial.begin(9600);
  //testBoardComponents();
  alarm = new Alarm();
  serialComm = new SerialComm();
  alarm->calibrate();
}

void loop(){
  if(not alarm->isArmed()){
    if(alarm->isButtonPressed()){
      SerialComm::sendLogMessage("Alarm Armed");
      alarm->arm();
    }
  } else {
    if(alarm->isTripped()){
      SerialComm::sendLogMessage("Alarm Tripped");
      alarm->trigger();
    }
  }

  if(not alarm->isCalibrated()){
    if(alarm->isButtonPressed()){
      alarm->calibrate();
    }
  }

}
