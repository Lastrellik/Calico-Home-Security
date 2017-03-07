#include "Arduino.h"
#include "Properties.h"

// ********** GENERAL ********** //
int Properties::BAUD_RATE = 9600;

// ********** MODULES ********** //
boolean Properties::MODULE_WIFI = true;
boolean Properties::MODULE_PI = false;
boolean Properties::MODULE_IR = false;


// ********** WIFI MODULE PROPERTIES ********** //
int Properties::WIFI_RX_PIN = 10;
int Properties::WIFI_TX_PIN = 11;
String Properties::WIFI_SSID = "YOUR_SSID_HERE";
String Properties::WIFI_PASSWORD = "YOUR_WIRELESS_PASSWORD";
