/*
  dataPacket.h - A class designed around Serial Communication
*/

#include "DataPacket.h"
#include "Arduino.h"
#include "../lib/Cryptosuite/Sha/sha1.h"

#define SIZE_OF_HASH_IN_PACKET 8
#define SIZE_OF_DATAPACKET_IN_BYTES 64

DataPacket::DataPacket(String messageString, String packetTypeString){
  packetTypeString.toUpperCase();
  this->messageString = messageString;
  this->packetTypeString = packetTypeString;
  appendPacketHeader();
  appendMessage();
}

DataPacket::DataPacket(byte* packetContents){
  parsePacketArray(packetContents);
  parseSha1FromRawPacket(packetContents);
  parsePacketTypeFromRawPacket(packetContents);
  parseMessageFromRawPacket(packetContents);
}

byte* DataPacket::getPacketAsArray(){
  return packetAsArray;
}

byte* DataPacket::getPacketHash(){
  return packetHash;
}

int DataPacket::getSizeInBytes(){
  return SIZE_OF_DATAPACKET_IN_BYTES;
}

String DataPacket::getPacketType(){
  return packetTypeString;
}

String DataPacket::getMessage(){
  return messageString;
}

void DataPacket::appendPacketHeader(){
  appendHash();
  appendPacketTypeByte();
}

void DataPacket::appendHash(){
  Sha1.init();
  Sha1.print(messageString);
  for(int i = 0; i < SIZE_OF_HASH_IN_PACKET; i++){
    packetHash[i] = Sha1.result()[i];
  }
  append(packetHash, SIZE_OF_HASH_IN_PACKET);
}

void DataPacket::appendPacketTypeByte(){
  byte packetTypeByte = 0;
  if(packetTypeString == "LOG"){
    packetTypeByte = 0;
  } else if (packetTypeString == "COMMAND"){
    packetTypeByte = 1;
  } else if (packetTypeString == "INFO"){
    packetTypeByte = 2;
  } else if (packetTypeString == "REQUEST"){
    packetTypeByte = 3;
  }
  append(packetTypeByte);
}

void DataPacket::appendMessage(){
  byte messageByte[messageString.length() + 1];
  messageString.getBytes(messageByte, messageString.length() + 1);
  append(messageByte, messageString.length());
}

void DataPacket::append(byte rawData[], int length){
  for(uint8_t i = 0; i < length; i++){
    append(rawData[i]);
  }
}

void DataPacket::append(byte singleRawByte){
  packetAsArray[arrayCounter++] = singleRawByte;
}

void DataPacket::parsePacketArray(byte* packetContents){
  for(int i = 0; i < SIZE_OF_DATAPACKET_IN_BYTES; i++){
    packetAsArray[i] = packetContents[i];
  }
}

void DataPacket::parseSha1FromRawPacket(byte* packetContents){
  for(int i = 0; i < SIZE_OF_HASH_IN_PACKET; i++){
    packetHash[i] = packetContents[i + 1];
  }
}

void DataPacket::parsePacketTypeFromRawPacket(byte* packetContents){
  byte packetTypeBytePositionInArray = SIZE_OF_HASH_IN_PACKET + 1;
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
    case 3:
      packetTypeString = "REQUEST";
      break;
  }
}

void DataPacket::parseMessageFromRawPacket(byte* packetContents){
  for(int i = SIZE_OF_HASH_IN_PACKET + 1; i < SIZE_OF_DATAPACKET_IN_BYTES; i++){
    messageString += packetContents[i];
  }
}
