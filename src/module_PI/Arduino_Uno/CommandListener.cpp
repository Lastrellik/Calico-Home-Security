/*
CommandListener.h - A class for listening and executing commands from an external source
*/
#include "Arduino.h"
#include "module_PI/Arduino_Uno/SerialComm.h"
#include "CommandListener.h"
#include "Alarm.h"

#define SIZE_OF_HASH_IN_PACKET 8
#define SIZE_OF_DATAPACKET_IN_BYTES 64

CommandListener::CommandListener(Alarm* alarm){
  this->alarm = alarm;
}

void CommandListener::executeCommandIfAvailable(){
  if(SerialComm::hasDataPacket()){
    currentPacket = SerialComm::getDataPacketIfAvailable();
    executeCommand();
  }
}

void CommandListener::executeCommand(){
  String message = currentPacket.getMessage();
  //for(char c : message) Serial.print(c);
  //Serial.println(message.toInt());
  switch (message.toInt()) {
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
    default:
      SerialComm::sendLogMessage("Default");
  }
}
