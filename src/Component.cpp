/**
  Calico Home Security System, Component.cpp
  Purpose: Handles creation of a Component object.
    Which are used to determine the type of component Ex. "LED" and where it is
    on the breadboard.
    Component is the superclass for LED, Laser, Buzzer, Photoresistor, Button.

  @author Christopher Nash, Jason Bruderer, David Tille, Tyler Jacobs
  
*/
#include "Arduino.h"
#include "Component.h"
/**
  Constructor that takes in an int for the variable pin and a string for the
  variable componentType
  @param pin sets the pin on the breadboard for the type of component being used
  @param componentType names what type of component is being Ex. "LED"
*/
Component::Component(int pin, String componentType){
  if(Properties::MODULE_PI) Serial.write("13250"); // 13250 = Log, Debug, Component object successfully created

  //Need to figure out if we can put the component type created and the pin it's
  //set at with the Serial.write and the table, for now just doing base message
  //Logger::Log("Component " + componentType + " is set at pin " + pin);
  _pin = pin;
  _componentType = componentType;
  delay(100);
}
/**
  Base Constructor that sets the pin = 13 and componentType = LED
*/
Component::Component(){
  _pin = 13;
  _componentType = "LED";
}
/**
  Getter for the pin
  @return Returns the int of the pin sent to the Component Constructor
*/
int Component::getPin(){
  return _pin;
}
/**
  Getter for the ComponentType
  @return Returns the what type of Component is being used
*/
String Component::getComponentType(){
  return _componentType;
}
/**
  Used to set the pin
  @param pin is set
*/
void Component::setComponentPin(int pin){
  _pin = pin;
}
/**
  Used to set the componentType
  @param componentType is set
*/
void Component::setComponentType(String componentType){
  _componentType = componentType;
}
