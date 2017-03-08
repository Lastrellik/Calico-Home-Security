/*
  SerialControl.h - A class designed around serial control of the Alarm System
*/
#ifndef SerialComm_h
#define SerialComm_h

#include "Arduino.h"
#include "DataPacket.h"

class SerialComm{
    public:
      static void sendLogMessage(String logMessage);
      static void sendCommandMessage(String commandMessage);
      static void sendInfoMessage(String infoMessage);
      static bool hasDataPacket();
      static DataPacket getDataPacket();
    private:
      static void sendDataPacket(DataPacket packet);
      static bool recievedValidHashResponse(byte* validSha256Hash);
      static bool recievedSha256HashBeforeTimeout();
};

#endif
