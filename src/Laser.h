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
  void engage();
  void disengage();
  void toggle();
private:
  boolean _isOn;
};

#endif
