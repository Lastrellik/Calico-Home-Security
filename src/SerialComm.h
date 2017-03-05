/*
  SerialControl.h - A class designed around serial control of the Alarm System
*/
#ifndef SerialComm_h
#define SerialComm_h

#include "Arduino.h"
#include "../lib/QueueArray.h"
#include "../lib/Cryptosuite-master/Sha/sha256.h"

class SerialComm{
    public:
       SerialComm();
       void sendDataStream(String dataStream);

    private:
      void sendSyncRequestPacket();
      void sendSyncAknowledgementPacket();
      void sendAcknowledgementPacket();
      void loadStringInOutputStream(String dataStream);
      void initializeAES();
      QueueArray<char> serialInputStream;
      QueueArray<char> serialOutputStream;
      bool establishedSocketWithRemoteHost = false;
};

#endif
