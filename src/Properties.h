/*
  Properties.h - A class for Properties

  This class is intended to be included in any class which needs access to various Properties.
  For example, if your class needs to know whether the PI module is activte or not then
  you should include Properties.h in your class and call Properties::MODULE_PI to see
  whether it is configured to be true or not.
*/

#ifndef Properties_h
#define Properties_h

#include "Arduino.h"


class Properties {

  public:
    // ********** GENERAL ********** //
    static boolean DEBUGGING_ACTIVE;
    static int BAUD_RATE;


    // ********** MODULES ********** //
    static boolean MODULE_BASE;
    static boolean MODULE_MINI;
    static boolean MODULE_WIFI;
    static boolean MODULE_PI;
    static boolean MODULE_IR;
    static boolean MODULE_TOUCHSCREEN;
    static boolean MODULE_TURRET;


    // ********** WIFI MODULE PROPERTIES ********** //
    static int WIFI_RX_PIN;
    static int WIFI_TX_PIN;
    static String WIFI_SSID;
    static String WIFI_PASSWORD;
    static String IFTTT_MAKER_KEY;
    static String IFTTT_MAKER_EVENT;
    static String IFTTT_MAKER_URI;

  private:

};

#endif
