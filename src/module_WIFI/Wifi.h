/*
  Wifi.h - A class for connecting an ESP8266 to the system
*/

#ifndef Wifi_h
#define Wifi_h

#include "Arduino.h"
#include <SoftwareSerial.h>
// TODO: Is there a good library out there for ESP8266 that I could just include?

class Wifi { // TODO: I'd like to make this extend Component but the Wifi module requires 2 pins instead of 1. This would make a refactor of Component necessary.

  public:
    Wifi(int rxPin, int txPin);
    Wifi(int rxPin, int txPin, int baudRate);
    Wifi(int rxPin, int txPin, String ssid, String password);
    void setSSID(String ssid);
    void setPassword(String password);
    void setBaudRate(int baudRate);
    void initialize();
    void beginSerialCommunication();
    void checkTwoWayCommunication();
    // void connectToAccessPoint();
    // void sendNotification();

  private:
    int _rxPin;
    int _txPin;
    SoftwareSerial esp8266;
    String _ssid;
    String _password;
    int _baudRate = 9600;
    bool _isConnectedToWifi = false;
};

#endif
