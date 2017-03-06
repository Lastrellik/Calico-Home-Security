/*
  SerialControl.h - A class designed around serial control of the Alarm System
*/
#include "Arduino.h"
#include "SerialComm.h"
#include "../lib/Cryptosuite/Sha/sha256.h"

SerialComm::SerialComm(){
  serialOutputStream.setPrinter(Serial);
  serialInputStream.setPrinter(Serial);
}

void SerialComm::sendDataStream(String dataStream){
  int communicationAttempts = 0;
  uint8_t* messageHash = generateMessageHash(dataStream);
  do{
  loadStringInOutputStream(dataStream);
  while(!serialOutputStream.isEmpty()){
    Serial.print(serialOutputStream.dequeue());
  }
  Serial.println();
  } while (gotCorrectHashFromDevice(messageHash) && communicationAttempts <= 3);
}

bool SerialComm::gotCorrectHashFromDevice(uint8_t* messageHash){
  int iterator = 0;
  while(Serial.available() > 0){
    if(messageHash[iterator++] != Serial.read()) return false;
  }
  return true;
}

uint8_t* SerialComm::generateMessageHash(String message){
  Sha256.init();
  Sha256.print(message);
  printHash(Sha256.result());
  return Sha256.result();
}

void SerialComm::loadStringInOutputStream(String dataStream){
  for(char c : dataStream){
    serialOutputStream.enqueue(c);
  }
}

void SerialComm::sendSyncRequestPacket(){
}

void SerialAcknowledgementPacket(){
}
//for debugging. Should delete before committing
void SerialComm::printHash(uint8_t* hash) {
  int i;
  for (i=0; i<32; i++) {
    Serial.print("0123456789abcdef"[hash[i]>>4]);
    Serial.print("0123456789abcdef"[hash[i]&0xf]);
  }
  Serial.println();
}
