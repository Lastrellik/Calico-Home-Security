/*
  Component.h - Component superclass for component classes
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
    int _pin;
    String _componentType;
};

#endif
