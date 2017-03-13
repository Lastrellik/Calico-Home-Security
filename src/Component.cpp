/*
  Component.h Component superclass for component classes
*/
#include "Arduino.h"
#include "Component.h"

Component::Component(int pin, String componentType){
  _pin = pin;
  _componentType = componentType;
}

Component::Component(){
  _pin = 13;
  _componentType = "LED";
}

int Component::getPin(){
  return _pin;
}

String Component::getComponentType(){
  return _componentType;
}

void Component::setComponentPin(int pin){
  _pin = pin;
}

void Component::setComponentType(String componentType){
  _componentType = componentType;
}
