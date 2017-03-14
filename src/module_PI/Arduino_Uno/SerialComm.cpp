/*
  SerialControl.h - A class designed around serial control of the Alarm System
*/
#include "Arduino.h"
#include "SerialComm.h"
#include "DataPacket.h"
#include "PacketType.h"

#define SIZE_OF_HASH_IN_PACKET 8
#define SIZE_OF_DATAPACKET_IN_BYTES 64

void SerialComm::sendLogMessage(String logMessage){
  DataPacket logPacket(logMessage, PacketType::LOG);
  sendDataPacket(logPacket);
}

void SerialComm::sendCommandMessage(String commandMessage){
  DataPacket commandPacket(commandMessage, PacketType::COMMAND);
  sendDataPacket(commandPacket);
}

void SerialComm::sendInfoMessage(String infoMessage){
  DataPacket infoPacket(infoMessage, PacketType::INFO);
  sendDataPacket(infoPacket);
}

void SerialComm::sendInstructionRequest(){
  DataPacket requestPacket("Request Command", PacketType::REQUEST);
  sendDataPacket(requestPacket);
}

void SerialComm::sendDataPacket(DataPacket packet){
  int numOfAttempts = 0;
  int numOfAttemptsBeforeTimeout = 3;
  do {
    for (size_t i = 0; i < 64; i++) {
      Serial.print((char)packet.getPacketAsArray()[i]);
    }
  } while(!receivedValidHashResponse(packet.getPacketHash()) &&
      numOfAttempts++ < numOfAttemptsBeforeTimeout);
}

bool SerialComm::receivedValidHashResponse(byte* validSha1Hash){
  if(!receivedSha1HashBeforeTimeout()){
    return false;
  } else {
    for(int i = 0; i < SIZE_OF_HASH_IN_PACKET; i++){
      if(validSha1Hash[i] != Serial.read()){
        return false;
      }
    }
  }
  return true;
}

bool SerialComm::receivedSha1HashBeforeTimeout(){
  int millisWaitedForResponse = 1;
  int millisBeforeTimeout = 100;
  while(Serial.available() != SIZE_OF_HASH_IN_PACKET
            && millisWaitedForResponse < millisBeforeTimeout){
    delay(millisWaitedForResponse++);
  }
  return(millisWaitedForResponse < millisBeforeTimeout);
}

bool SerialComm::hasDataPacket(){
  return(Serial.peek() == '~' && Serial.available() == SIZE_OF_DATAPACKET_IN_BYTES);
}
