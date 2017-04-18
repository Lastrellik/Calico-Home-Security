/*
  I2C_Master.cpp - A class for connecting with another Arduino Uno or other I2C Device as the MASTER.
*/
#include "Arduino.h"
#include <Wire.h>
#include "TurretMASTER.h"

TurretMASTER::TurretMASTER() {
  Wire.begin();
  currentStatus = Status::WAITING; // Reset status to Waiting
}

void TurretMASTER::sendTransmission(int deviceNumber, int command) {
  if(!currentStatus == Status::SENT) { // Only send the message once until it is reset when the alamr is disarmed
    // TODO: Add some logging statements here about the turret being triggered.
    setStatus(Status::SENT);
    Wire.beginTransmission(deviceNumber);
    Wire.write(command);
    Wire.endTransmission();
  }
}

void TurretMASTER::resetStatus() {
  currentStatus = Status::WAITING; // Reset status to Waiting
  // TODO: Send a command here to stop the turret
}

void TurretMASTER::setStatus(Status status) {
  currentStatus = status;
}
