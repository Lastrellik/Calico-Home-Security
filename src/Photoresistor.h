/**
  Calico Home Security System, Photoresistor.h
  Purpose: Reads in the Photoresistor output

  @author Christopher Nash, Jason Bruderer, David Tille, Tyler Jacobs
  
*/
#ifndef Photoresistor_h
#define Photoresistor_h

#include "Arduino.h"
#include "Component.h"
#include "Properties.h"

class Photoresistor : public Component {
public:
  Photoresistor(int pin);
  Photoresistor();
  /**
    @param takeReading is used for the value the photoresistor is producing
  */
  int takeReading();
};

#endif
