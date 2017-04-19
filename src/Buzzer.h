/**
  Calico Home Security System, Buzzer.h
  Purpose: sets the tone of the Buzzer and if it's on and off

  @author Christopher Nash, Jason Bruderer, David Tille, Tyler Jacobs
  
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
