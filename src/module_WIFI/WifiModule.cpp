/*
  WifiModule.cpp -  A class for connecting an ESP8266 to the system

  Inspired by the code presented at http://yaab-arduino.blogspot.com/p/wifiesp.html
*/
#include "Arduino.h"
#include "WifiModule.h"
#include "WiFiEsp.h"
#include <SoftwareSerial.h>
#include "Properties.h"

WifiModule::WifiModule() : esp8266(SoftwareSerial(Properties::WIFI_RX_PIN, Properties::WIFI_TX_PIN)) {
}

WifiModule::WifiModule(int baudRate) : esp8266(SoftwareSerial(Properties::WIFI_RX_PIN, Properties::WIFI_TX_PIN)) {
  setBaudRate(baudRate);
}

void WifiModule::setBaudRate(int baudRate) {
  _baudRate = baudRate;
}

void WifiModule::initStatus() {
  _status = WL_IDLE_STATUS;
}

/**
  Returns whether the module is initialized or not.

  @return True if initialized and connected. False if not initialized and connected.
*/
boolean WifiModule::isInitialized() {
  if (Properties::DEBUGGING_ACTIVE) {
    Serial.println("Wifi Initialized Status:");
    Serial.println(_initialized);
  }
  return _initialized;
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

/* TODO: formalize this. I suspect that I either need to do some sort of 'keep-alive' once every 1000 cycles or something
    or it may eventually disconnect from wifi. If that happens then we want to reinitialize. For now I will leave
    the call to this method commented out as a reminder to come back and work on it further.
*/
void WifiModule::checkStatusAndReinitIfNecessary() {
  if (_status != WL_CONNECTED) {
    if (Properties::DEBUGGING_ACTIVE) Serial.println("Wifi not connected. Reinitializing...");
    initialize();
  }
  return;
}

void WifiModule::initialize() {
  if (Properties::DEBUGGING_ACTIVE) Serial.println("Initiallizing ESP9266...");

  esp8266.begin(Properties::BAUD_RATE);

  initStatus();
  WiFi.init(&esp8266);

  if (WiFi.status() == WL_NO_SHIELD) {
    Serial.println("ESP8266 not present or miscofigured");
    _initialized = false;
    return; // Wifi Module failure. Return so we don't try any further work.
  }

  int connectAttemptCount = 0;

  while (_status != WL_CONNECTED) {
    if (Properties::DEBUGGING_ACTIVE) {
      Serial.print("Connect to SSID: ");
      Serial.println(Properties::WIFI_SSID);
    }

    _status = WiFi.begin(Properties::WIFI_SSID.c_str(), Properties::WIFI_PASSWORD.c_str());

    if(++connectAttemptCount >= Properties::WIFI_CONNECT_RETRY_COUNT) {
      _initialized = false;
      return; // We failed to connect in the number of times configured so bail early.
    }
  }

  printWifiStatus();
  _initialized = true;
}

void WifiModule::sendNotification() {
  if (Properties::DEBUGGING_ACTIVE) Serial.println("Sending notification...");

  // TODO: Determine whether this is needed and how best to handle it.
  // checkStatusAndReinitIfNecessary();

  WiFiEspClient client;

  if (client.connect(Properties::IFTTT_MAKER_URI.c_str(), 80) <= 0) {
    if (Properties::DEBUGGING_ACTIVE) {
      Serial.print("Failed to connect to: ");
      Serial.println(Properties::IFTTT_MAKER_URI);
    }
    return;
  }

  if (Properties::DEBUGGING_ACTIVE) Serial.println("Connected.");

  if (Properties::DEBUGGING_ACTIVE) Serial.println("Posting to IFTTT!");

  if (Properties::DEBUGGING_ACTIVE) Serial.println((String)"POST /trigger/" + Properties::IFTTT_MAKER_EVENT + "/with/key/" + Properties::IFTTT_MAKER_KEY + " HTTP/1.1\r\n" + "Host: " + Properties::IFTTT_MAKER_URI + "\r\n" + "Content-Type: application/x-www-form-urlencoded\r\n\r\n");
  client.print((String)"POST /trigger/" + Properties::IFTTT_MAKER_EVENT + "/with/key/" + Properties::IFTTT_MAKER_KEY + " HTTP/1.1\r\n" + "Host: " + Properties::IFTTT_MAKER_URI + "\r\n" + "Content-Type: application/x-www-form-urlencoded\r\n\r\n");

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

// TODO: This currently has issues with actually sending the parameters. IFTTT Returns a partial bad response.
void WifiModule::sendNotificationWithData(String param1, String param2, String param3) {
  if (Properties::DEBUGGING_ACTIVE) Serial.println("Sending notification...");

  // TODO: Determine whether this is needed and how best to handle it.
  // checkStatusAndReinitIfNecessary();

  WiFiEspClient client;

  if (client.connect(Properties::IFTTT_MAKER_URI.c_str(), 80) <= 0) {
    if (Properties::DEBUGGING_ACTIVE) {
      Serial.print("Failed to connect to: ");
      Serial.println(Properties::IFTTT_MAKER_URI);
    }
    return;
  }

  if (Properties::DEBUGGING_ACTIVE) Serial.println("Connected.");

  String data="{\"value1\":\""+param1+"\",\"value2\":\""+param2+"\",\"value3\":\""+param3+"\"}";

  if (Properties::DEBUGGING_ACTIVE) Serial.println("Posting to IFTTT!");
  // Serial.println(data.length());
  // Serial.println(data);

  if (Properties::DEBUGGING_ACTIVE) Serial.println((String)"POST /trigger/" + Properties::IFTTT_MAKER_EVENT + "/with/key/" + Properties::IFTTT_MAKER_KEY + " HTTP/1.1\r\n" + "Host: " + Properties::IFTTT_MAKER_URI + "\r\n" + "Content-Type: application/x-www-form-urlencoded\r\n\r\n");
  client.print((String)"POST /trigger/" + Properties::IFTTT_MAKER_EVENT + "/with/key/" + Properties::IFTTT_MAKER_KEY + " HTTP/1.1\r\n" + "Host: " + Properties::IFTTT_MAKER_URI + "\r\n" + "Content-Type: application/x-www-form-urlencoded\r\n\r\n");
  client.print("Content-Length: ");
  client.print(data.length());
  client.print("\r\n\r\n");
  client.print(data);

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
