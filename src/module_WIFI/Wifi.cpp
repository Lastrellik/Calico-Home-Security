/*
  Photoresistor.h - Simple Library for reading from a Photoresistor
*/
#include "Arduino.h"
#include "Wifi.h"
#include <SoftwareSerial.h>

Wifi::Wifi(int rxPin, int txPin) : esp8266(SoftwareSerial(rxPin, txPin)) {
  _rxPin = rxPin;
  _txPin = txPin;
}

Wifi::Wifi(int rxPin, int txPin, int baudRate) : esp8266(SoftwareSerial(rxPin, txPin)) {
  _rxPin = rxPin;
  _txPin = txPin;
  setBaudRate(baudRate);
}

Wifi::Wifi(int rxPin, int txPin, String ssid, String password) : esp8266(SoftwareSerial(rxPin, txPin)) {
  _rxPin = rxPin;
  _txPin = txPin;
  setSSID(ssid);
  setPassword(password);
}

void Wifi::setSSID(String ssid) {
  _ssid = ssid;
}

void Wifi::setPassword(String password) {
  _password = password;
}

void Wifi::setBaudRate(int baudRate) {
  _baudRate = baudRate;
}

void Wifi::initialize() {
  beginSerialCommunication();
  checkTwoWayCommunication();
}

void Wifi::beginSerialCommunication() {
  // Check whether Serial.begin() has been called. If it hasn't then start Serial communication.
  if(!Serial) {
    Serial.begin(_baudRate);
  }
  while (!Serial) { /* Stall until serial comunication is ready */ }
  esp8266.begin(_baudRate);
}

void Wifi::checkTwoWayCommunication() {
  delay(5000);
  Serial.println("Sending an AT command...");
  esp8266.println("AT");
  delay(100);
  while (esp8266.available()){
     String inData = esp8266.readStringUntil('\n');
     Serial.println("Got response from ESP8266: " + inData);
  }
}
