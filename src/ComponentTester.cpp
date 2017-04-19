/**
  Calico Home Security System, ComponentTester.cpp
  Purpose: Used to test the Component subclasses

  @author Christopher Nash, Jason Bruderer, David Tille, Tyler Jacobs
  
*/
#include "Arduino.h"
#include "ComponentTester.h"
#include "Buzzer.h"
#include "Button.h"
/**
  Base constructor that logs that ComponentTester is being used
*/
ComponentTester::ComponentTester() {
  if(Properties::MODULE_PI) Serial.write("13300"); // 13300 = Log, Debug, ComponentTester default constructor object successfully created
}
/**
  Constructor that builds a Component object for testing
*/
ComponentTester::ComponentTester(Component* component){
  if(Properties::MODULE_PI) Serial.write("13301"); // 13301 = Log, Debug, ComponentTester object successfully created
  _component = component;
}
/**
  Determines the componentType and tests it accordingly based on the class
*/
void ComponentTester::testPin(){
  String componentType = _component->getComponentType();
  int pin = _component->getPin();
  if(componentType.equalsIgnoreCase("LED") ||
      componentType.equalsIgnoreCase("LASER")){
      digitalWrite(pin, HIGH);
      delay(_millisOn);
      digitalWrite(pin, LOW);
      delay(_millisOff);
  }
  if(componentType.equalsIgnoreCase("BUZZER")){
    tone(pin, 1000, 100); //(Buzzer)_component.tone(1000) wouldn't work. Polymorphism?
  }
  if(componentType.equalsIgnoreCase("PHOTORESISTOR")){
    for(int i = 1; i <= 10; i++){
        if(Properties::DEBUGGING_ACTIVE) {
        //  Logger::Log(String("Test #") + i + String(": ") + String(analogRead(pin)));
        }
      }
  }
  if(componentType.equalsIgnoreCase("BUTTON")){
    Button button(_component->getPin()); //May be wasted memory
    for(int i = 0; i < 150; i++){
      if(button.isPressed()) {
        //Logger::Log("The button was pressed");
        break;
      }
      delay(200);
    }
  }
}
/**
  Setter for the component
*/
void ComponentTester::setComponent(Component* component){
  _component = component;
}
/**
  A function that uses setComponent and testPin at the same time
*/
void ComponentTester::testComponent(Component* component){
  setComponent(component);
  testPin();
}
