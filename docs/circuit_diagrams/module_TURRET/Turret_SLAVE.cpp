#include "Arduino.h"
#include <Wire.h>

int BUILTIN_LED = 13;
int commandCode = 0;

void receiveEvent(int bytes) {
  commandCode = Wire.read();    // read one character from the I2C
}

void beginTurretSequence() {
  // TODO: Turret Code begins here
}

void blinkLED_Fast() {
  digitalWrite(BUILTIN_LED, HIGH);
  delay(200);
  digitalWrite(BUILTIN_LED, LOW);
  delay(200);
}

void blinkLED_Slow() {
  digitalWrite(BUILTIN_LED, HIGH);
  delay(400);
  digitalWrite(BUILTIN_LED, LOW);
  delay(400);
}

void setup() {
  pinMode (BUILTIN_LED, OUTPUT);

  Wire.begin(9);  // Start the I2C Bus as Slave on address 9
  Wire.onReceive(receiveEvent); // Attach a function to trigger when something is received.
}

void loop() {
  //If value received is 0 blink LED for 200 ms
  if (commandCode == '0') {
    blinkLED_Fast();
  }
  //If value received is 3 blink LED for 400 ms
  if (commandCode == '3') {
    blinkLED_Slow();
  }

  if (commandCode == '9') { // TODO: Need to determine what code we are going to send
    beginTurretSequence();
  }
}
