/**
  Calico Home Security System, Component.h
  Purpose: Handles creation of a Component object.
    Which are used to determine the type of component Ex. "LED" and where it is
    on the breadboard

  @author Christopher Nash, Jason Bruderer, David Tille, Tyler Jacobs
  
*/
#ifndef Component_h
#define Component_h

#include "Arduino.h"
#include "Properties.h"

class Component{
  protected:
    Component(int pin, String componentType);
  public:
    Component();
    int getPin();
    String getComponentType();
    void setComponentPin(int pin);
    void setComponentType(String componentType);
  private:
    /**
      @param pin is used to set pin of component
      @param componentType is used to determine which library is being used
    */
    int _pin;
    String _componentType;
};

#endif
