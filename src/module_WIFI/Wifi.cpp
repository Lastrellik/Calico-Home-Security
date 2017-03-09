/*
  Photoresistor.h - Simple Library for reading from a Photoresistor
*/
#include "Arduino.h"
#include "Wifi.h"
#include <SoftwareSerial.h>
#include "Properties.h"

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
  if (Properties::DEBUGGING_ACTIVE) {
    Serial.println("Initiallizing ESP9266...");
  }
  beginSerialCommunication();
  checkTwoWayCommunication();
  connectToAccessPoint();
}

void Wifi::beginSerialCommunication() {
  if (Properties::DEBUGGING_ACTIVE) {
    Serial.println("Beginning serial communication with ESP9266...");
  }
  // Check whether Serial.begin() has been called. If it hasn't then start Serial communication.
  if(!Serial) {
    Serial.begin(_baudRate);
  }
  while (!Serial) { /* Stall until serial comunication is ready */ }
  esp8266.begin(_baudRate);
}

void Wifi::checkTwoWayCommunication() {
  delay(5000);
  if (Properties::DEBUGGING_ACTIVE) {
    Serial.println("Sending an AT command...");
  }
  esp8266.println("AT");
  delay(100);
  while (esp8266.available()){
     String inData = esp8266.readStringUntil('\n');
     if (Properties::DEBUGGING_ACTIVE) {
       Serial.println("Got response from ESP8266: " + inData);
     }
  }
}

void Wifi::connectToAccessPoint() {
  if (Properties::DEBUGGING_ACTIVE) {
    Serial.println("Setting ESP8266 to Station Mode...");
  }
  esp8266.println("AT+CWMODE=1");
  delay(1000);
  while (esp8266.available()){
     String inData = esp8266.readStringUntil('\n');
     if (Properties::DEBUGGING_ACTIVE) {
       Serial.println("Got response from ESP8266: " + inData);
     }
  }

  if (Properties::DEBUGGING_ACTIVE) {
    Serial.println("Connecting to AP " + Properties::WIFI_SSID);
  }
  esp8266.println("AT+CWJAP_CUR=\"" + Properties::WIFI_SSID + "\",\"" + Properties::WIFI_PASSWORD + "\""); // TODO: There is a different command AT+CWJAP= which persistst the access point instead of using this which is only for the current launch. Refactor this to use it instead.

  if (Properties::DEBUGGING_ACTIVE) {
    Serial.println("Pausing for 30 seconds...");
  }
  delay(30000); // Give 30 seconds for the module to connect

  while (esp8266.available()){ // TODO: This mechanism for logging incoming values from the ESP8266 is faulty for multiple lines of response. Replace this.
     String inData = esp8266.readStringUntil('\n');
     if (Properties::DEBUGGING_ACTIVE) {
       Serial.println("Got response from ESP8266: " + inData);
     }
  }
}
