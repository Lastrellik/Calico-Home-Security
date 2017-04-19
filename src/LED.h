/**
  Calico Home Security System, LED.h
  Purpose: LED creaties an LED ojbect and determines whether it should be on or
    off.

  @author Christopher Nash, Jason Bruderer, David Tille, Tyler Jacobs
  
*/
#ifndef LED_h
#define LED_h

#include "Arduino.h"
#include "Component.h"
#include "Properties.h"

class LED : public Component {
public:
  LED(int pin);
  LED();
  void on();
  void off();
  void toggle();
  void flash();
  void flash(int millisOn);
  /**
    _isOn is used in other classes as well but when it is used is subjected to
      the class it resides in and isn't shared out to other classes.
    @param _isOn is used to determine the status of the LED.
      If set to true the LED is on
      If set to false the LED is off
  */
private:
  boolean _isOn;
};

#endif
