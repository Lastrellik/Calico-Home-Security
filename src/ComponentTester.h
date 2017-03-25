/*
  ComponentTester.h - Generic class for testing components
*/
#ifndef ComponentTester_h
#define ComponentTester_h

#include "Arduino.h"
#include "Component.h"
#include "Properties.h"

class ComponentTester{
private:
  Component* _component;
  int _millisOn = 500;
  int _millisOff = 100;
public:
  ComponentTester(Component* component);
  ComponentTester();
  void testPin();
  void testComponent(Component* component);
  void setComponent(Component* component);
};

#endif
