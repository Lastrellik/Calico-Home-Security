/*
  SerialControl.h - A class designed around serial control of the Alarm System
*/
#ifndef SerialComm_h
#define SerialComm_h

#include "Arduino.h"
#include "DataPacket.h"

#define SIZE_OF_HASH_IN_PACKET 8
#define SIZE_OF_DATAPACKET_IN_BYTES 64

class SerialComm{
    public:
      static void sendLogMessage(String logMessage);
      static void sendCommandMessage(String commandMessage);
      static void sendInfoMessage(String infoMessage);
      static void sendInstructionRequest();
      static bool hasDataPacket();
      static DataPacket getDataPacketIfAvailable();
    private:
      static void sendDataPacket(DataPacket packet);
      static bool receivedValidHashResponse(byte* validSha1Hash);
      static bool receivedSha1HashBeforeTimeout();
};

#endif
