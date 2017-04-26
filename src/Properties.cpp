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
boolean Properties::DEBUGGING_ACTIVE = false;                 // Set to `true` if you are troubleshooting an issue and want to see log output in your terminal window
boolean Properties::LASER_AIMING_MODE = false;                // Setting to true will activate the laser during the setup phase of the alarm and leave it on until the button is pressed. This can be helpful when setting up the laser.
int Properties::BAUD_RATE = 9600;                             // Normally shouldn't be changed. The only time you might need to is when working with an ESP8266 module. See the Wifi Module's README for more information.
int Properties::ALARM_TRIPPED_TO_TRIGGERED_MILLIS = 30000;    // The amount of time between the alarm being tripped and it being triggered. Default is 30000 (30 seconds).

// ********** MODULES ********** //
boolean Properties::MODULE_BASE = true;                       // Normally set to `true`. This is only set to `false` if you are using the MINI module. This is the ONLY module which should be checked into the repo as `true`.
boolean Properties::MODULE_MINI = false;                      // Set to `true` if you have the MINI _instead of_ the BASE module as your foundation. If this is set to `true` then BASE should be set to `false`.
boolean Properties::MODULE_WIFI = false;                      // Set to `true` if you have the WIFI module attached to your alarm system.
boolean Properties::MODULE_PI = false;                        // Set to `true` if you have the Raspberry Pi module attached to your alarm system.
boolean Properties::MODULE_IR = false;                        // Set to `true` if you have the IR module attached to your alarm system.
boolean Properties::MODULE_TOUCHSCREEN = false;               // Set to `true` if you have the Touchscreen module attached to your alarm system.
boolean Properties::MODULE_TURRET = false;                    // Set to `true` if you have the Turret module attached to your alarm system.


// ********** WIFI MODULE PROPERTIES ********** //
int Properties::WIFI_RX_PIN = 10;                             // The pin on the Arduino that the RX pin on the ESP8266 is plugged into. Should be 10 if followed the diagram.
int Properties::WIFI_TX_PIN = 11;                             // The pin on the Arduino that the TX pin on the ESP8266 is plugged into. Should be 11 if followed the diagram.
String Properties::WIFI_SSID = "YOUR_SSID_HERE";              // The name (SSID) of your wireless access point.
String Properties::WIFI_PASSWORD = "YOUR_WIRELESS_PASSWORD";  // The password to connect to your wireless network.
String Properties::IFTTT_MAKER_KEY = "YOUR_IFTTT_MAKER_KEY";  // Key obtained from your IFTTT account. See `IFTTT Setup` instructions in the Wifi Module's README for more details.
int Properties::WIFI_CONNECT_RETRY_COUNT = 3;                      // The number of times the Wifi module will attempt to connect before it fails.
String Properties::IFTTT_MAKER_EVENT = "alarm_tripped";       // The IFTTT Maker's Event. See `IFTTT Setup` instructions in the Wifi Module's README for more details.
String Properties::IFTTT_MAKER_URI = "maker.ifttt.com";       // The IFTTT Maker root URI. There is likely no need to change this until IFTTT changes the URI in the future.


// ********** TURRET MODULE PROPERTIES ********** //
int Properties::TURRET_SLAVE_NUMBER = 9;                      // The number configured in the Turrent_SLAVE.cpp file for the Arduino Uno configured ot control the Turret
int Properties::TURRET_COMMAND = 9;                           // The command to send to the Turret
