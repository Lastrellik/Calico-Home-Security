/*
  dataPacket.h - A class designed around Serial Communication
*/
#ifndef DataPacket_h
#define DataPacket_h

#include "Arduino.h"
#include "../lib/Cryptosuite/Sha/sha1.h"

#define SIZE_OF_SHA1_IN_BYTES 20
#define SIZE_OF_DATAPACKET_IN_BYTES 64

class DataPacket{
    public:
      DataPacket(String message, String packetType);
      DataPacket(byte* packetContents);
      byte* getPacketAsArray();
      byte* getPacketSha1Hash();
      int getSizeInBytes();
      DataPacket getDataPacket();
    private:
      void appendPacketHeader();
      void appendHash();
      void appendPacketTypeByte();
      void appendMessage();
      void append(byte rawData[]);
      void append(byte singleRawByte);
      void parseSha1FromRawPacket(byte* packetContents);
      void parsePacketTypeFromRawPacket(byte* packetContents);
      void parseMessageFromRawPacket(byte* packetContents);
      int arrayCounter = 1;
      byte sha1MessageHash[SIZE_OF_SHA1_IN_BYTES];
      byte packetAsArray[SIZE_OF_DATAPACKET_IN_BYTES] { '~' };
      String messageString;
      String packetTypeString;
};

#endif
