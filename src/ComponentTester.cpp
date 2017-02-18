#include "Arduino.h"
class ComponentTester {

private:
  int testedPin; //the pin to test
  int millisOn = 500;
  int millisOff = 500;
  String pinMode;

public:
  //for testing output
  ComponentTester(int pin, String mode) {
    Serial.begin(9600);
    testedPin = pin;
    pinMode = mode;
  }

  void testPin(){
    Serial.print("pinMode: ");
    Serial.print(pinMode);
    Serial.print("\tpin number: ");
    Serial.println(testedPin);
    if(pinMode.equalsIgnoreCase("LED")){
      digitalWrite(testedPin, HIGH);
      delay(millisOn);
      digitalWrite(testedPin, LOW);
      delay(millisOff);
    }
    if (pinMode.equalsIgnoreCase("BUZZER")){
      tone(testedPin, 1000, millisOn);
    }
    if (pinMode.equalsIgnoreCase("PHOTORESISTOR")){
      for(int i = 1; i <= 10; i++){
        Serial.print("test#");
        Serial.print(i);
        Serial.print(" ");
        Serial.println(analogRead(testedPin));
        delay(100);
      }
    }
  }

  void setPin(int pin){
    testedPin = pin;
  }

  void setMode(String mode){
    pinMode = mode;
  }

  void setPinAndMode(int pin, String mode){
    testedPin = pin;
    pinMode = mode;
  }

  void setOnTime(int onTime){
    millisOn = onTime;
  }

  void setOffTime(int offTime){
    millisOff = offTime;
  }

};
