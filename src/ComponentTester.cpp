/*
  ComponentTester.cpp - Generic class for testing components
*/

#include "Arduino.h"
#include "ComponentTester.h"
#include "Buzzer.h"
#include "Button.h"
#include "Logger.h"

ComponentTester::ComponentTester() {
  Serial.write(14015); //14016 = Log, Info, Componet Tester
}

ComponentTester::ComponentTester(Component* component){
  Seria.write(14015); //14016 = Log, Info, Componet Tester
  _component = component;
}

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
        Serial.write(14014); //14014 Log, Info, Base Photoresistor Test
  }

  if(componentType.equalsIgnoreCase("BUTTON")){
    Button button(_component->getPin()); //May be wasted memory
    for(int i = 0; i < 150; i++){
      if(button.isPressed()) {
        Serial.write(24017); //24017 Data, Info, Button Pressed
        break;
      }
      delay(200);
    }
  }
}

void ComponentTester::setComponent(Component* component){
  _component = component;
}

void ComponentTester::testComponent(Component* component){
  setComponent(component);
  testPin();
}
