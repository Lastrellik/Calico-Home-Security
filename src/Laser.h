/*
  Laser.h - Simple class for laser module
*/
#ifndef Laser_h
#define Laser_h

#include "Arduino.h"
#include "Component.h"

class Laser : public Component {
public:
  Laser(int pin);
  Laser();
  void on();
  void off();
  void toggle();
private:
  boolean _isOn;
};

#endif
