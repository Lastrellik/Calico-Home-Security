#include "Arduino.h"
#include <Wire.h>
#include <Servo.h>

int BUILTIN_LED = 13;
int commandCode = 0;

Servo servopan;
int panPin = 3;

Servo servotilt;
int tiltPin = 4;

int gun = 7;
int post = 0;
int posp = 0;

void receiveEvent(int bytes) {
  commandCode = Wire.read();    // read one character from the I2C
}

void beginTurretSequence() {
  for (posp = 0; posp <= 120; posp += 1);
  for (post = 60; post <= 100; post += 1) {
    servopan.write(posp);
    servotilt.write(post);
    delay(10);
  }

  digitalWrite(gun,HIGH);
  delay (500);
  digitalWrite(gun,LOW);

  for (post = 120; post >= 100; post -= 1);
  for (posp = 120; posp >= 0; posp -= 1) {
    servopan.write(posp);
    servotilt.write(post);
    delay(10);
  }

  digitalWrite(gun,HIGH);
  delay (500);
  digitalWrite(gun,LOW);
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
  servopan.attach(panPin);
  servotilt.attach(tiltPin);
  pinMode(gun, OUTPUT);

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

  if (commandCode == '9') {
    beginTurretSequence();
  }
}
