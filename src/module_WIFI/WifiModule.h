/*
  WifiModule.h - A class for connecting an ESP8266 to the system
*/

#ifndef WifiModule_h
#define WifiModule_h

#include "Arduino.h"
#include <SoftwareSerial.h>
#include "WiFiEsp.h"
#include "Properties.h"

class WifiModule {

  public:
    WifiModule();
    WifiModule(int baudRate);
    void setBaudRate(int baudRate);
    void initStatus();
    boolean isInitialized();
    void checkStatusAndReinitIfNecessary();
    void initialize();
    void sendNotification();
    void sendNotificationWithData(String param1, String param2, String param3);
    void printWifiStatus();

  private:
    SoftwareSerial esp8266;
    int _baudRate = Properties::BAUD_RATE;
    int _status;
    boolean _initialized = false;
};

#endif
