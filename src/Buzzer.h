/*
  Buzzer.h - Simple Library for making sounds on a Piezo Buzzer
*/
#ifndef Buzzer_h
#define Buzzer_h

#include "Arduino.h"
#include "Component.h"

class Buzzer: public Component {
public:
  Buzzer(int pin);
  int getPin();
  String getComponentType();
  void soundTone(int frequency);
  void soundTone(int frequency, int millisOn);
  void stopTone();
private:
  int _pin;
  String _componentType;
};

#endif
