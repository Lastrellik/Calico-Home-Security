#include "Arduino.h"
#include <ComponentTester.cpp>
#include "LED.h"
#include "Photoresistor.h"
#include "Buzzer.h"

LED greenLED(2);
LED redLED(3);
LED alarmLED(4);

Photoresistor photoR(A0);

Buzzer buzzer(6);

//Test each of the components
void testBoardComponents(){
  ComponentTester tester(greenLED.getPin(), greenLED.getComponentType());
  tester.testPin();
  tester.setPinAndMode(redLED.getPin(), redLED.getComponentType());
  tester.testPin();
  tester.setPinAndMode(alarmLED.getPin(), alarmLED.getComponentType());
  tester.testPin();
  tester.setPinAndMode(photoR.getPin(), photoR.getComponentType());
  tester.testPin();
  tester.setPinAndMode(buzzer.getPin(), buzzer.getComponentType());
  tester.testPin();
}

void setup() {
  Serial.begin(9600);  //Begin serial communcation
  testBoardComponents();
}

void loop(){
  Serial.print("AnalogRead: ");
  Serial.println(photoR.takeReading());
  delay(1000);
}
