///////////////////////////////////////////////////////////////////////////////
//
// CONTRIBUTORS - Be careful What you commit in this file!
//
// Follow these rules when committing this file:
// 1. All module's should be disabled when this file is committed.
// 2. Generic "Placeholders" should be included for potentially sensative values (such as WIFI_PASWORD)
//
///////////////////////////////////////////////////////////////////////////////

#include "Arduino.h"
#include "Properties.h"

// ********** GENERAL ********** //
boolean Properties::DEBUGGING_ACTIVE = false;
int Properties::BAUD_RATE = 9600;

// ********** MODULES ********** //
boolean Properties::MODULE_WIFI = false;
boolean Properties::MODULE_PI = true;
boolean Properties::MODULE_IR = false;


// ********** WIFI MODULE PROPERTIES ********** //
int Properties::WIFI_RX_PIN = 10;
int Properties::WIFI_TX_PIN = 11;
String Properties::WIFI_SSID = "YOUR_SSID_HERE";
String Properties::WIFI_PASSWORD = "YOUR_WIRELESS_PASSWORD";
String Properties::IFTTT_MAKER_KEY = "YOUR_IFTTT_MAKER_KEY";
String Properties::IFTTT_MAKER_EVENT = "alarm_tripped";
