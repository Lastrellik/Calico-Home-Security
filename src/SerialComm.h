/*
  SerialControl.h - A class designed around serial control of the Alarm System
*/
#ifndef SerialComm_h
#define SerialComm_h

#include "Arduino.h"
#include "../lib/QueueArray.h"

class SerialComm{
    public:
       SerialComm();
       void sendSyncRequestPacket();
       void sendSyncAknowledgementPacket();
       void sendAcknowledgementPacket();
       void sendDataStream(byte dataStream[]);
       int getSerialPort();
       void setSerialPort(int serialPort);

    private:
      QueueArray<byte> serialInputStream;
      QueueArray<byte> serialOutputStream;
      int externalDeviceSerialPort;
      void determineSerialPort();
      bool establishedSocketWithRemoteHost = false;
};

#endif
