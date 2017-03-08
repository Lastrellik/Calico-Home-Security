/*
  SerialControl.h - A class designed around serial control of the Alarm System
*/
#include "Arduino.h"
#include "SerialComm.h"
#include "DataPacket.h"

void SerialComm::sendLogMessage(String logMessage){
  DataPacket logPacket(logMessage, "LOG");
  sendDataPacket(logPacket);
}

void SerialComm::sendCommandMessage(String commandMessage){
  DataPacket commandPacket(commandMessage, "COMMAND");
  sendDataPacket(commandPacket);
}

void SerialComm::sendInfoMessage(String infoMessage){
  DataPacket infoPacket(infoMessage, "INFO");
  sendDataPacket(infoPacket);
}

void SerialComm::sendDataPacket(DataPacket packet){
  int numOfAttempts = 0;
  int numOfAttemptsBeforeTimeout = 3;
  do {
    Serial.flush();
    Serial.write(packet.getPacketAsArray(), packet.getSizeInBytes());
  } while(!recievedValidHashResponse(packet.getPacketSha256Hash()) &&
      numOfAttempts++ < numOfAttemptsBeforeTimeout);
}

bool SerialComm::recievedValidHashResponse(byte* validSha256Hash){
  int sizeOfSha256HashInBytes = 32;
  if(!recievedSha256HashBeforeTimeout()){
    return false;
  } else {
    for(int i = 0; i < sizeOfSha256HashInBytes; i++){
      if(validSha256Hash[i] != Serial.read()){
        return false;
      }
    }
  }
  return true;
}

bool SerialComm::recievedSha256HashBeforeTimeout(){
  int millisWaitedForResponse = 1;
  int millisBeforeTimeout = 1000;
  int sizeOfSha256HashInBytes = 32;
  while(Serial.available() != sizeOfSha256HashInBytes
            && millisWaitedForResponse < millisBeforeTimeout){
    delay(millisWaitedForResponse++);
  }
  return(millisWaitedForResponse < millisBeforeTimeout);
}

bool SerialComm::hasDataPacket(){
  int serialInputBufferSizeInBytes = 64;
  return(Serial.peek() == '~' && Serial.available() == serialInputBufferSizeInBytes);
}

DataPacket SerialComm::getDataPacket(){
  byte rawPacketData[64];
  if(hasDataPacket()){
    Serial.readBytes(rawPacketData, Serial.available());
    return DataPacket(rawPacketData);
  }
}
