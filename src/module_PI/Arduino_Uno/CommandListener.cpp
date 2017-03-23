/*
CommandListener.h - A class for listening and executing commands from an external source
*/
#include "Arduino.h"
#include "module_PI/Arduino_Uno/SerialComm.h"
#include "CommandListener.h"
#include "Alarm.h"
#include "Logger.h"

#define SIZE_OF_HASH_IN_PACKET 8
#define SIZE_OF_DATAPACKET_IN_BYTES 64

CommandListener::CommandListener(Alarm* alarm){
  Logger::Log("CommandListener Created");
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
  Logger::Log("Received Command " + message);
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
  }
}
