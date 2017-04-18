#include "Arduino.h"
#include <Wire.h>
#include <Servo.h>

int ic2SlaveNumber = 9; // Configure this value to be the number you will communicate with via the Master

int commandCode = 0;

///// PAN Servo
Servo servopan;
int panPin = 3;
int position_P = 0;

///// TILT Servo
Servo servotilt;
int tiltPin = 4;
int position_T = 0;

///// GUN
int gun = 7;

void receiveEvent(int bytes) {
  commandCode = Wire.read();    // read one int from the I2C
}

void beginTurretSequence() {
  for (position_P = 0; position_P <= 120; position_P += 1);
  for (position_T = 60; position_T <= 100; position_T += 1) {
    servopan.write(position_P);
    servotilt.write(position_T);
    delay(10);
  }

  digitalWrite(gun,HIGH);
  delay (500);
  digitalWrite(gun,LOW);

  for (position_T = 120; position_T >= 100; position_T -= 1);
  for (position_P = 120; position_P >= 0; position_P -= 1) {
    servopan.write(position_P);
    servotilt.write(position_T);
    delay(10);
  }

  digitalWrite(gun,HIGH);
  delay (500);
  digitalWrite(gun,LOW);

  commandCode = 0;
}

void blinkLED_Fast() {
  for (int i = 0; i < 5; i++) {
    digitalWrite(LED_BUILTIN, HIGH);
    delay(200);
    digitalWrite(LED_BUILTIN, LOW);
    delay(200);
  }

}

void blinkLED_Slow() {
  for (int i=0; i < 5; i++) {
    digitalWrite(LED_BUILTIN, HIGH);
    delay(800);
    digitalWrite(LED_BUILTIN, LOW);
    delay(800);
  }
}

void setup() {
  Serial.begin(9600);
  pinMode (LED_BUILTIN, OUTPUT);
  servopan.attach(panPin);
  servotilt.attach(tiltPin);
  pinMode(gun, OUTPUT);

  Wire.begin(ic2SlaveNumber);     // Start the I2C Bus as Slave
  Wire.onReceive(receiveEvent);   // Attach a function to trigger when something is received.
}

void loop() {
  if (commandCode == 0) {
    blinkLED_Fast();
  }

  if (commandCode == 3) {
    blinkLED_Slow();
  }

  if (commandCode == 9) {
    beginTurretSequence();
  }
}
