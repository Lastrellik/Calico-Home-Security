/*
  ComponentTester.h - Generic class for testing components
*/
#ifndef ComponentTester_h
#define ComponentTester_h

#include "Arduino.h"
#include "Component.h"

class ComponentTester{
private:
  Component _component;
  int _millisOn = 500;
  int _millisOff = 100;
public:
  ComponentTester(Component component);
  ComponentTester();
  void testPin();
  void setComponent(Component component);
  Component getComponent();
  void setOnTime(int onTime);
  void setOffTime(int offTime);
  int getOnTime();
  int getOffTime();
};

#endif