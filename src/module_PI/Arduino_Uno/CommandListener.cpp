/*
CommandListener.h - A class for listening and executing commands from an external source
*/

#include "Arduino.h"
#include "Alarm.h"
#include "Logger.h"
#include "CommandListener.h"

#define SIZE_OF_DATAPACKET_IN_BYTES 8

CommandListener::CommandListener(Alarm* alarm){
  Logger::Log("CommandListener Created");
  this->alarm = alarm;
}

void CommandListener::executeCommandIfAvailable(){
  if(Serial.available() > 0){
    executeCommand(Serial.read());
  }
}

void CommandListener::executeCommand(byte commandByte){
  switch (commandByte) {
    case 0:
      alarm->arm();
      break;
    case 1:
      alarm->disarm();
      break;
    case 3:
      alarm->silence();
      break;
    case 4:
      alarm->calibrate();
      break;
  }
}
