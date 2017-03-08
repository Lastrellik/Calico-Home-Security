/*
  dataPacket.h - A class designed around Serial Communication
*/
#include "Arduino.h"
#include "DataPacket.h"
#include "../lib/Cryptosuite/Sha/sha256.h"

DataPacket::DataPacket(String messageString, String packetTypeString){
  packetTypeString.toUpperCase();
  messageString.toUpperCase();
  this->messageString = messageString;
  this->packetTypeString = packetTypeString;
  appendPacketHeader();
  appendMessage();
}

byte* DataPacket::getPacketAsArray(){
  return packetAsArray;
}

void DataPacket::appendPacketHeader(){
  appendHash();
  appendPacketTypeByte();
}

void DataPacket::appendHash(){
  Sha256.init();
  Sha256.print(messageString);
  for(int i = 0; i < 32; i++){
    sha256MessageHash[i] = Sha256.result()[i];
  }
  append(sha256MessageHash);
}

void DataPacket::appendPacketTypeByte(){
  byte packetTypeByte = 0;
  if(packetTypeString == "LOG"){
    packetTypeByte = 0;
  } else if (packetTypeString == "COMMAND"){
    packetTypeByte = 1;
  } else if (packetTypeString == "INFO"){
    packetTypeByte = 2;
  }
  append(packetTypeByte);
}

void DataPacket::appendMessage(){
  byte messageByte[messageString.length()];
  messageString.getBytes(messageByte, messageString.length());
  append(messageByte);
}

void DataPacket::append(byte rawData[]){
  for(uint8_t i = 0; i < sizeof(rawData)/sizeof(byte); i++){
    append(rawData[i]);
  }
}

void DataPacket::append(byte singleRawByte){
  packetAsArray[arrayCounter++] = singleRawByte;
}
