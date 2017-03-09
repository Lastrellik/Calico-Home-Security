/*
  dataPacket.h - A class designed around Serial Communication
*/
#include "Arduino.h"
#include "DataPacket.h"
#include "../lib/Cryptosuite/Sha/sha1.h"

#define SIZE_OF_SHA1_IN_BYTES 20
#define SIZE_OF_DATAPACKET_IN_BYTES 64

DataPacket::DataPacket(String messageString, String packetTypeString){
  packetTypeString.toUpperCase();
  messageString.toUpperCase();
  this->messageString = messageString;
  this->packetTypeString = packetTypeString;
  appendPacketHeader();
  appendMessage();
}

DataPacket::DataPacket(byte* packetContents){
  parseSha1FromRawPacket(packetContents);
  parsePacketTypeFromRawPacket(packetContents);

}

byte* DataPacket::getPacketAsArray(){
  return packetAsArray;
}

byte* DataPacket::getPacketSha1Hash(){
  return sha1MessageHash;
}

int DataPacket::getSizeInBytes(){
  return SIZE_OF_DATAPACKET_IN_BYTES;
}

void DataPacket::appendPacketHeader(){
  appendHash();
  appendPacketTypeByte();
}

void DataPacket::appendHash(){
  Sha1.init();
  Sha1.print(messageString);
  for(int i = 0; i < SIZE_OF_SHA1_IN_BYTES; i++){
    sha1MessageHash[i] = Sha1.result()[i];
  }
  append(sha1MessageHash);
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

void DataPacket::parseSha1FromRawPacket(byte* packetContents){
  for(int i = 0; i < SIZE_OF_SHA1_IN_BYTES; i++){
    sha1MessageHash[i] = packetContents[i + 1];
  }
}

void DataPacket::parsePacketTypeFromRawPacket(byte* packetContents){
  byte packetTypeBytePositionInArray = SIZE_OF_SHA1_IN_BYTES + 1;
  switch(packetContents[packetTypeBytePositionInArray]){
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
  for(int i = SIZE_OF_SHA1_IN_BYTES + 1; i < SIZE_OF_DATAPACKET_IN_BYTES; i++){
    messageString += packetContents[i];
  }
}
