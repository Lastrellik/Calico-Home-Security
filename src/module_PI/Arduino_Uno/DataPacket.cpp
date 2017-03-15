/*
  dataPacket.h - A class designed around Serial Communication
*/

#include "DataPacket.h"
#include "Arduino.h"
#include "../lib/Cryptosuite/Sha/sha1.h"
#include "PacketType.h"

#define SIZE_OF_HASH_IN_PACKET 8
#define SIZE_OF_DATAPACKET_IN_BYTES 64

DataPacket::DataPacket(String messageString, PacketType packetType){
  packetTypeString.toUpperCase();
  this->messageString = messageString;
  this->packetType = packetType;
  appendPacketHeader();
  appendMessage();
}

DataPacket::DataPacket(byte* packetContents){
  for(int i = 0; i < SIZE_OF_DATAPACKET_IN_BYTES; i++){
  packetHash[i] = packetContents[i];
  }
  setSha1FromRawPacket(packetContents);
  setPacketTypeFromRawPacket(packetContents);
  setMessageFromRawPacket(packetContents);
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
  append((byte)packetType);
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

void DataPacket::setSha1FromRawPacket(byte* packetContents){
  for(int i = 0; i < SIZE_OF_HASH_IN_PACKET; i++){
    packetHash[i] = packetContents[i + 1];
  }
}

void DataPacket::setPacketTypeFromRawPacket(byte* packetContents){
  byte packetTypeBytePositionInArray = SIZE_OF_HASH_IN_PACKET + 1;
  switch(packetContents[packetTypeBytePositionInArray]){
    case 0:
      packetTypeString = "LOG";
      packetType = PacketType::LOG;
      break;
    case 1:
      packetTypeString = "COMMAND";
      packetType = PacketType::COMMAND;
      break;
    case 2:
      packetTypeString = "INFO";
      packetType = PacketType::INFO;
      break;
    case 3:
      packetTypeString = "REQUEST";
      packetType = PacketType::REQUEST;
      break;
  }
}

void DataPacket::setMessageFromRawPacket(byte* packetContents){
  for(int i = SIZE_OF_HASH_IN_PACKET + 1; i < SIZE_OF_DATAPACKET_IN_BYTES; i++){
    messageString += packetContents[i];
  }
}
