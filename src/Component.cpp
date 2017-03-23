/*
  Component.h Component superclass for component classes
*/
#include "Arduino.h"
#include "Component.h"
#include "Logger.h"

Component::Component(int pin, String componentType){
  Logger::Log("Component " + componentType + " is set at pin " + pin);
  _pin = pin;
  _componentType = componentType;
  delay(100);
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
