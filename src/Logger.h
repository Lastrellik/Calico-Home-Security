/*
Logger.h - A class based around logging events
*/

#ifndef Logger_h
#define Logger_h

#include "Arduino.h"
#include "Properties.h"

class Logger{
public:
  static void Log(String message){
  //  if(Properties::DEBUGGING_ACTIVE) Serial.println(message);
  //if(Properties::MODULE_PI) SerialComm::sendLogMessage(message);
  }
};

#endif