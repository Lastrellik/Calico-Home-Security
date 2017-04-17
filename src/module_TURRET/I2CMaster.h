/*
  I2C_Master.h - A class for connecting with another Arduino Uno or other I2C Device as the MASTER.
*/
#ifndef I2CMaster_h
#define I2CMaster_h

#include "Arduino.h"
#include <Wire.h>

class I2CMaster {

  public:
    enum Status { WAITING, SENT };
    I2CMaster();
    void sendTransmission(int deviceNumber, int command);
    void resetStatus();
    void setStatus(Status status);

  private:
    Status currentStatus;

};

#endif
