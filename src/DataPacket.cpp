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

DataPacket::DataPacket(byte* packetContents){
  parseSha256FromRawPacket(packetContents);
  parsePacketTypeFromRawPacket(packetContents);

}

byte* DataPacket::getPacketAsArray(){
  return packetAsArray;
}

byte* DataPacket::getPacketSha256Hash(){
  return sha256MessageHash;
}

int DataPacket::getSizeInBytes(){
  return 64;
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

void DataPacket::parseSha256FromRawPacket(byte* packetContents){
  int sha256HashEnd = 32;
  for(int i = 0; i < sha256HashEnd; i++){
    sha256MessageHash[i] = packetContents[i + 1];
  }
}

void DataPacket::parsePacketTypeFromRawPacket(byte* packetContents){
  switch(packetContents[33]){
    case 0:
      packetTypeString = "LOG";
      break;
    case 1:
      packetTypeString = "COMMAND";
      break;
    case 2:
      packetTypeString = "INFO";
      break;
  }
}

void DataPacket::parseMessageFromRawPacket(byte* packetContents){
  for(int i = 33; i <= 63; i++){
    messageString += packetContents[i];
  }
}
