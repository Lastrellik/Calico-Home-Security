/*
  Properties.h - A class for Wifi Properties
*/

#ifndef Properties_h
#define Properties_h

#include "Arduino.h"


class Properties {

  public:
    // ********** GENERAL ********** //
    static int BAUD_RATE;


    // ********** MODULES ********** //
    static boolean MODULE_WIFI;
    static boolean MODULE_PI;
    static boolean MODULE_IR;


    // ********** WIFI MODULE PROPERTIES ********** //
    static int WIFI_RX_PIN;
    static int WIFI_TX_PIN;
    static String WIFI_SSID;
    static String WIFI_PASSWORD;

  private:

};

#endif
