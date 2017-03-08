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
      byte* getPacketAsArray();
    private:
      void appendPacketHeader();
      void appendHash();
      void appendPacketTypeByte();
      void appendMessage();
      void append(byte rawData[]);
      void append(byte singleRawByte);
      int arrayCounter = 1;
      byte sha256MessageHash[32];
      byte packetAsArray[128] { '~' };
      String messageString;
      String packetTypeString;
};

#endif
