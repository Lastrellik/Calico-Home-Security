/*
CommandListener.h - A class for listening and executing commands from an external source
*/

#include "Arduino.h"
#include "Alarm.h"
#include "CommandListener.h"

#define SIZE_OF_DATAPACKET_IN_BYTES 5

CommandListener::CommandListener(Alarm* alarm){
  this->alarm = alarm;
}

void CommandListener::executeCommandIfAvailable(){
  if(Serial.available() >= SIZE_OF_DATAPACKET_IN_BYTES){
    for(int i = 0; i < SIZE_OF_DATAPACKET_IN_BYTES; i++){
      commandBytes[i] = Serial.read();
    }
    executeCommand(commandBytes);
  }
}

int CommandListener::commandByteToInt(byte* commandBytes){
    int commandByteToInt = 0;
    int multiplier = 10000;
    for(int i = 0; i < SIZE_OF_DATAPACKET_IN_BYTES; i++){
      commandByteToInt += multiplier * commandBytes[i];
      multiplier /= 10;
    }
    return commandByteToInt;
}

void CommandListener::executeCommand(byte* commandBytes){
  int commandInt = commandByteToInt(commandBytes);
  switch (commandInt) {
    case 32001:
      alarm->arm();
      break;
    case 32002:
      alarm->disarm();
      break;
    case 32003:
      alarm->silence();
      break;
    case 32004:
      alarm->calibrate();
      break;
    case 32005:
      alarm->trigger();
      break;
    case 32006:
      alarm->resetCalibration();
      break;
    case 32007:
      alarm->testBoardComponents();
      break;
    case 32008:
      alarm->trip();
      break;
    case 32009:
      alarm->toggleLaser();
      break;
  }
}
