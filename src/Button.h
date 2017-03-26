/**
  Calico Home Security System, Button.h
  Purpose: Determines if the Button has been pressed, turning it on or off

  @author Chris Nash, Jason Bruderer, David Tille, Tyler Jacobs
  @version To be Determined
*/
#ifndef Button_h
#define Button_h

#include "Arduino.h"
#include "Component.h"
#include "Properties.h"

class Button : public Component{
public:
  Button(int pin);
  Button();
  /**
    @param determines the state of the Button and is used to see if it is
      pressed
  */
  boolean isPressed();
};

#endif
