/*
  Buzzer.h - Simple Library for making sounds on a Piezo Buzzer
*/
#ifndef Buzzer_h
#define Buzzer_h

#include "Arduino.h"
#include "Component.h"
#include "Properties.h"

class Buzzer : public Component {
public:
  Buzzer(int pin);
  Buzzer();
  void soundTone(int frequency);
  void soundTone(int frequency, int millisOn);
  void soundAffirmativeTone();
  void soundNegativeTone();
  void soundAlarmHighTone();
  void soundAlarmLowTone();
  void stopTone();
};

#endif
