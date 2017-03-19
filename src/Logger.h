/*
Logger.h - A class based around logging events
*/

#ifndef Logger_h
#define Logger_h

#include "Arduino.h"
#include "Properties.h"
#include "module_PI/Arduino_Uno/SerialComm.h"

class Logger{
public:
  static void Log(String message){
    if(Properties::DEBUGGING_ACTIVE) Serial.println(message);
    if(Properties::MODULE_PI) SerialComm::sendLogMessage(message);
    delay(100);
  }
};

#endif
