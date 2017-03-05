/*
  SerialControl.h - A class designed around serial control of the Alarm System
*/
#include "Arduino.h"
#include "SerialComm.h"
#include "../lib/Cryptosuite-master/Sha/sha256.h"

SerialComm::SerialComm(){
  serialOutputStream.setPrinter(Serial);
}

void SerialComm::sendDataStream(String dataStream){
  sendSyncRequestPacket();
  loadStringInOutputStream(dataStream);
  while(!serialOutputStream.isEmpty()){
    Serial.print(serialOutputStream.dequeue());
  }
  Serial.println();
}

void SerialComm::loadStringInOutputStream(String dataStream){
Sha256.init();
  for(char c : dataStream){
    serialOutputStream.enqueue(c);
  }
}

void SerialComm::sendSyncRequestPacket(){
}

void SerialAcknowledgementPacket(){

}
