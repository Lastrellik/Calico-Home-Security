/*
  SerialControl.h - A class designed around serial control of the Alarm System
*/
#ifndef SerialComm_h
#define SerialComm_h

#include "Arduino.h"
#include "DataPacket.h"

#define SIZE_OF_SHA1_IN_BYTES 20
#define SIZE_OF_DATAPACKET_IN_BYTES 64

class SerialComm{
    public:
      static void sendLogMessage(String logMessage);
      static void sendCommandMessage(String commandMessage);
      static void sendInfoMessage(String infoMessage);
      static bool hasDataPacket();
      static DataPacket getDataPacket();
    private:
      static void sendDataPacket(DataPacket packet);
      static bool recievedValidHashResponse(byte* validSha1Hash);
      static bool recievedSha1HashBeforeTimeout();
};

#endif
