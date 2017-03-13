/*
CommandListener.h - A class for listening and executing commands from an external source
*/
#include "Arduino.h"
#include "SerialComm.h"
#include "CommandListener.h"
#include "Alarm.h"

#define SIZE_OF_HASH_IN_PACKET 8
#define SIZE_OF_DATAPACKET_IN_BYTES 64

CommandListener::CommandListener(Alarm* alarm){
  alarm = alarm;
}

void CommandListener::executeCommandIfAvailable(){
  if(SerialComm::hasDataPacket()){
    currentPacket = SerialComm::getDataPacketIfAvailable();
    sendHashBack();
  }
}

void CommandListener::sendHashBack(){
  for(int i = 0; i < SIZE_OF_HASH_IN_PACKET; i++){
    Serial.print(currentPacket.getPacketHash()[i]);
  }
}

void CommandListener::executeCommand(){
  String message = currentPacket.getMessage();
  switch (message.toInt()) {
    case 0:
      alarm->arm();
      SerialComm::sendInfoMessage("String infoMessage");
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
