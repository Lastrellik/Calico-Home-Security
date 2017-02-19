/*
  Component.h Component superclass for component classes
*/
#include "Arduino.h"
#include "Component.h"

Component::Component(int pin, String componentType){
  _pin = pin;
  _componentType = componentType;
}

int Component::getPin(){
  return _pin;
}

String Component::getComponentType(){
  return _componentType;
}
