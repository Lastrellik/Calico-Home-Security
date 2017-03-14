/*
  ComponentTester.cpp - Generic class for testing components
*/

#include "Arduino.h"
#include "ComponentTester.h"
#include "Buzzer.h"
#include "Button.h"

ComponentTester::ComponentTester(Component component) {
  Serial.begin(9600);
  _component = component;
}

void ComponentTester::testPin(){
  String componentType = _component.getComponentType();
  int pin = _component.getPin();
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
          Serial.print("test# " + i + ": ");
          Serial.println(analogRead(pin));
        }
        delay(100);
      }
  }
  if(componentType.equalsIgnoreCase("BUTTON")){
    Button button(_component.getPin()); //May be wasted memory
    Serial.println("Press the button to test");
    for(int i = 0; i < 150; i++){
      if(button.isPressed()) {
        if(Properties::DEBUGGING_ACTIVE) Serial.println("The button was pressed");
        break;
      }
      delay(200);
    }
  }
}

void ComponentTester::setComponent(Component component){
  _component = component;
}

void ComponentTester::testComponent(Component component){
  setComponent(component);
  testPin();
}

Component ComponentTester::getComponent(){
  return _component;
}

void ComponentTester::setOnTime(int onTime){
  _millisOn = onTime;
}

void ComponentTester::setOffTime(int offTime){
  _millisOff = offTime;
}

int ComponentTester::getOnTime(){
  return _millisOn;
}

int ComponentTester::getOffTime(){
  return _millisOff;
}
