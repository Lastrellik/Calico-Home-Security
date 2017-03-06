/*
  SerialControl.h - A class designed around serial control of the Alarm System
*/
#ifndef SerialComm_h
#define SerialComm_h

#include "Arduino.h"
#include "../lib/QueueArray.h"
#include "../lib/Cryptosuite/Sha/sha256.h"

class SerialComm{
    public:
       SerialComm();
       void sendDataStream(String dataStream);

    private:
      void sendSyncRequestPacket();
      void sendAcknowledgementPacket();
      void loadStringInOutputStream(String dataStream);
      uint8_t* generateMessageHash(String message);
      void printHash(uint8_t* hash);
      bool gotCorrectHashFromDevice(uint8_t* messageHash);
      QueueArray<char> serialInputStream;
      QueueArray<char> serialOutputStream;
      bool establishedSocketWithRemoteHost = false;
};

#endif
