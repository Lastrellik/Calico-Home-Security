/*
  WifiModule.cpp -  A class for connecting an ESP8266 to the system

  Inspired by the code presented at http://yaab-arduino.blogspot.com/p/wifiesp.html
*/
#include "Arduino.h"
#include "WifiModule.h"
#include "WiFiEsp.h"
#include <SoftwareSerial.h>
#include "Properties.h"

SoftwareSerial esp8266(Properties::WIFI_RX_PIN, Properties::WIFI_TX_PIN);

WifiModule::WifiModule() : esp8266(SoftwareSerial(Properties::WIFI_RX_PIN, Properties::WIFI_TX_PIN)) {
}

WifiModule::WifiModule(int baudRate) : esp8266(SoftwareSerial(Properties::WIFI_RX_PIN, Properties::WIFI_TX_PIN)) {
  setBaudRate(baudRate);
}

void WifiModule::setBaudRate(int baudRate) {
  _baudRate = baudRate;
}

void WifiModule::initStatus(){
  _status = WL_IDLE_STATUS;
}

void WifiModule::printWifiStatus() {
  if (Properties::DEBUGGING_ACTIVE) {
    Serial.println("--------------------------------");

    Serial.print("Connected to SSID: ");
    Serial.println(WiFi.SSID());

    IPAddress ip = WiFi.localIP();
    Serial.print("My IP Address: ");
    Serial.println(ip);

    long rssi = WiFi.RSSI();
    Serial.print("Signal strength: ");
    Serial.print(rssi);
    Serial.println(" dBm");

    Serial.println("--------------------------------");
  }
}

void WifiModule::initialize() {
  if (Properties::DEBUGGING_ACTIVE) Serial.println("Initiallizing ESP9266...");

  esp8266.begin(Properties::BAUD_RATE);

  initStatus();
  WiFi.init(&esp8266);

  if (WiFi.status() == WL_NO_SHIELD) {
    Serial.println("ESP8266 not present or miscofigured");
    while (true); // TODO: Handle this different than just stalling forever...
  }

  while (_status != WL_CONNECTED) {
    if (Properties::DEBUGGING_ACTIVE) {
      Serial.print("Connect to SSID: ");
      Serial.println(Properties::WIFI_SSID);
    }

    _status = WiFi.begin(Properties::WIFI_SSID.c_str(), Properties::WIFI_PASSWORD.c_str());
  }

  printWifiStatus();
}

void WifiModule::sendNotification() {
  if (Properties::DEBUGGING_ACTIVE) Serial.println("Sending notification...");

  WiFiEspClient client;

  if (client.connect(Properties::IFTTT_MAKER_URI.c_str(), 80) <= 0) {
    if (Properties::DEBUGGING_ACTIVE) {
      Serial.print("Failed to connect to: ");
      Serial.println(Properties::IFTTT_MAKER_URI);
    }
    return;
  }
  Serial.println("Connected.");

  // String params1, params2,params3;
  // params1 = String("Val1");
  // params2 = String("Val2");
  // params3 = String("Val3");
  //
  // String data="{\"value1\":\""+params1+"\",\"value2\":\""+params1+"\",\"value3\":\""+params1+"\"}";

  Serial.println("Posting to IFTTT!");
  // Serial.println(data.length());
  // Serial.println(data);

  Serial.println((String)"POST /trigger/" + Properties::IFTTT_MAKER_EVENT + "/with/key/" + Properties::IFTTT_MAKER_KEY + " HTTP/1.1\r\n" + "Host: " + Properties::IFTTT_MAKER_URI + "\r\n" + "Content-Type: application/x-www-form-urlencoded\r\n\r\n");
  client.print((String)"POST /trigger/" + Properties::IFTTT_MAKER_EVENT + "/with/key/" + Properties::IFTTT_MAKER_KEY + " HTTP/1.1\r\n" + "Host: " + Properties::IFTTT_MAKER_URI + "\r\n" + "Content-Type: application/x-www-form-urlencoded\r\n\r\n");
  // client.print("Content-Length: ");
  // client.print(data.length());
  // client.print("\r\n\r\n");
  // client.print(data);

  // available() will return the number of characters currently in the receive buffer.
  while (client.available()) {
    Serial.write(client.read()); // read() gets the FIFO char
  }

  // connected() is a boolean return value - 1 if the connection is active, 0 if it's closed.
  if (client.connected()) {
    client.stop(); // stop() closes a TCP connection.
  }

  while (Serial.available()) {
    Serial.read();
  }
}

// void WifiModule::beginSerialCommunication() {
//   if (Properties::DEBUGGING_ACTIVE) {
//     Serial.println("Beginning serial communication with ESP9266...");
//   }
//   // Check whether Serial.begin() has been called. If it hasn't then start Serial communication.
//   if(!Serial) {
//     Serial.begin(_baudRate);
//   }
//   while (!Serial) { /* Stall until serial comunication is ready */ }
//   esp8266.begin(_baudRate);
// }
//
// void WifiModule::checkTwoWayCommunication() {
//   delay(5000);
//   if (Properties::DEBUGGING_ACTIVE) {
//     Serial.println("Sending an AT command...");
//   }
//   esp8266.println("AT");
//   delay(100);
//   while (esp8266.available()){
//      String inData = esp8266.readStringUntil('\n');
//      if (Properties::DEBUGGING_ACTIVE) {
//        Serial.println("Got response from ESP8266: " + inData);
//      }
//   }
// }
//
// void WifiModule::connectToAccessPoint() {
//   if (Properties::DEBUGGING_ACTIVE) {
//     Serial.println("Setting ESP8266 to Station Mode...");
//   }
//   esp8266.println("AT+CWMODE=1");
//   delay(1000);
//   while (esp8266.available()){
//      String inData = esp8266.readStringUntil('\n');
//      if (Properties::DEBUGGING_ACTIVE) {
//        Serial.println("Got response from ESP8266: " + inData);
//      }
//   }
//
//   if (Properties::DEBUGGING_ACTIVE) {
//     Serial.println("Connecting to AP " + Properties::WIFI_SSID);
//   }
//   esp8266.println("AT+CWJAP_CUR=\"" + Properties::WIFI_SSID + "\",\"" + Properties::WIFI_PASSWORD + "\""); // TODO: There is a different command AT+CWJAP= which persistst the access point instead of using this which is only for the current launch. Refactor this to use it instead.
//
//   if (Properties::DEBUGGING_ACTIVE) {
//     Serial.println("Pausing for 30 seconds...");
//   }
//   delay(30000); // Give 30 seconds for the module to connect
//
//   while (esp8266.available()){ // TODO: This mechanism for logging incoming values from the ESP8266 is faulty for multiple lines of response. Replace this.
//      String inData = esp8266.readStringUntil('\n');
//      if (Properties::DEBUGGING_ACTIVE) {
//        Serial.println("Got response from ESP8266: " + inData);
//      }
//   }
// }
