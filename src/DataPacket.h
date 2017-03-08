/*
  dataPacket.h - A class designed around Serial Communication
*/
#ifndef DataPacket_h
#define DataPacket_h

#include "Arduino.h"
#include "../lib/Cryptosuite/Sha/sha256.h"

class DataPacket{
    public:
      DataPacket(String message, String packetType);
      DataPacket(byte* packetContents);
      byte* getPacketAsArray();
      byte* getPacketSha256Hash();
      int getSizeInBytes();
      DataPacket getDataPacket();
    private:
      void appendPacketHeader();
      void appendHash();
      void appendPacketTypeByte();
      void appendMessage();
      void append(byte rawData[]);
      void append(byte singleRawByte);
      void parseSha256FromRawPacket(byte* packetContents);
      void parsePacketTypeFromRawPacket(byte* packetContents);
      void parseMessageFromRawPacket(byte* packetContents);
      int arrayCounter = 1;
      byte sha256MessageHash[32];
      byte packetAsArray[64] { '~' };
      String messageString;
      String packetTypeString;
};

#endif
