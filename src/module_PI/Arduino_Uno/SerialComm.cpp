/*
  SerialControl.h - A class designed around serial control of the Alarm System
*/
#include "Arduino.h"
#include "SerialComm.h"
#include "DataPacket.h"
#include "PacketType.h"
#include "Logger.h"

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

void SerialComm::sendErrorMessage(String errorMessage){
  DataPacket errorPacket(errorMessage, PacketType::ERROR);
  sendDataPacket(errorPacket);
}

DataPacket SerialComm::getDataPacketIfAvailable(){
  if(hasDataPacket()){
    byte serialBuffer[SIZE_OF_DATAPACKET_IN_BYTES];
    Serial.readBytes(serialBuffer, SIZE_OF_DATAPACKET_IN_BYTES);
    DataPacket packet(serialBuffer);
    return packet;
  } else {
    return 0;
  }
}

void SerialComm::sendDataPacket(DataPacket packet){
    for (size_t i = 0; i < SIZE_OF_DATAPACKET_IN_BYTES; i++) {
      Serial.print((char)packet.getPacketAsArray()[i]);
    }

    if(!receivedValidHashResponse(packet.getPacketHash())){
      flushBuffer();
      sendDataPacket(packet);
    }
}

void SerialComm::flushBuffer(){
  while(Serial.available() != 0) {
    char t = Serial.read();
  }
  Logger::Log("Error in DataPacket Communication!");
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
  return(Serial.peek() == '~') && Serial.available() == 64;
}
