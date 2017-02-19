#include "Arduino.h"


#include "LED.h"
#include "Photoresistor.h"
#include "Buzzer.h"
#include "Button.h"
#include "ComponentTester.h"

LED greenLED(2);
LED redLED(3);
LED alarmLED(4);

Photoresistor photoR(A0);

Buzzer buzzer(6);

Button armButton(7);

//Test each of the components
void testBoardComponents(){
  ComponentTester tester(greenLED);
  tester.testPin();
  tester.setComponent(redLED);
  tester.testPin();
  tester.setComponent(alarmLED);
  tester.testPin();
  tester.setComponent(photoR);
  tester.testPin();
  tester.setComponent(buzzer);
  tester.testPin();
  tester.setComponent(armButton);
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
  if(armButton.isPressed()) {
    buzzer.soundTone(1000);
  } else {
    buzzer.stopTone();
  }
}
