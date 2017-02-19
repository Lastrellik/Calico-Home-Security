/*
  Component.h - Component superclass for component classes
*/
#ifndef Component_h
#define Component_h

#include "Arduino.h"

class Component{
  protected:
    Component(int pin, String componentType);
  public:
    int getPin();
    String getComponentType();
  private:
    int _pin;
    String _componentType;
};

#endif
