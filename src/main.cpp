#include <ComponentTester.cpp>

int ledR1 = 3; //left-most red LED
int ledR2 = 4; //Right red LED
int ledG = 2; //green LED
int photoR = A0; //photoresistor input
int buzzer = 6; //speaker

//Test each of the components
void testBoardComponents(){
  ComponentTester tester(ledR1, "LED");
  tester.testPin();
  tester.setPinAndMode(ledR2, "LED");
  tester.testPin();
  tester.setPinAndMode(ledG, "LED");
  tester.testPin();
  tester.setPinAndMode(photoR, "photoresistor");
  tester.testPin();
  tester.setPinAndMode(buzzer, "speaker");
  tester.testPin();
}

void setup() {
  Serial.begin(9600);  //Begin serial communcation
  pinMode(ledR1, OUTPUT);
  pinMode(ledR2, OUTPUT);
  pinMode(ledG, OUTPUT);
  pinMode(photoR, INPUT);
  testBoardComponents();

}

void loop(){
  Serial.print("AnalogRead: ");
  Serial.println(analogRead(photoR));
  delay(1000);
}
