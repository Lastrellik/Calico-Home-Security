/*
  I2C_Master.h - A class for connecting with another Arduino Uno or other I2C Device as the MASTER.
*/
#ifndef TurretMASTER_h
#define TurretMASTER_h

#include "Arduino.h"
#include <Wire.h>

class TurretMASTER {

  public:
    enum Status { WAITING, SENT };
    TurretMASTER();
    void sendTransmission(int deviceNumber, int command);
    void resetStatus();
    void setStatus(Status status);

  private:
    Status currentStatus;

};

#endif
