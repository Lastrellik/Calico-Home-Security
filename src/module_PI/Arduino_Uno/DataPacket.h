/*
  dataPacket.h - A class designed around Serial Communication
*/
#ifndef DataPacket_h
#define DataPacket_h

#include "Arduino.h"
#include "../lib/Cryptosuite/Sha/sha1.h"
#include "PacketType.h"

#define SIZE_OF_HASH_IN_PACKET 8
#define SIZE_OF_DATAPACKET_IN_BYTES 64

class DataPacket{
    public:
      DataPacket(String message, PacketType packetType);
      DataPacket(byte* packetContents);
      byte* getPacketAsArray();
      byte* getPacketHash();
      int getSizeInBytes();
      String getPacketType();
      String getMessage();
    private:
      void appendPacketHeader();
      void appendHash();
      void appendPacketTypeByte();
      void appendMessage();
      void append(byte rawData[], int length);
      void append(byte singleRawByte);
      void setSha1FromRawPacket(byte* packetContents);
      void setPacketTypeFromRawPacket(byte* packetContents);
      void setMessageFromRawPacket(byte* packetContents);
      int arrayCounter = 1;
      byte packetHash[SIZE_OF_HASH_IN_PACKET];
      byte packetAsArray[SIZE_OF_DATAPACKET_IN_BYTES] { '~' };
      String messageString;
      String packetTypeString;
      PacketType packetType;
};

#endif
