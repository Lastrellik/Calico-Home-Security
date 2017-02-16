#include "Arduino.h"

int ledG = 9;
int ledY = 8;
int ledR = 7;
int photoR = 0;
long previousMillis = 0;
long intervalA = 1000;
long intervalB = 1500;
int state1 = LOW;
int state2 = LOW;

void setup() {

  pinMode(ledG, OUTPUT);
  pinMode(ledY, OUTPUT);
  pinMode(ledR, OUTPUT);
  pinMode(photoR, INPUT);
  Serial.begin(9600);  //Begin serial communcation
}

void loop(){


  unsigned long currentMillis = millis();
  long check = currentMillis - previousMillis;
  Serial.println(check);

  if(analogRead(photoR) < 150){
      digitalWrite(ledG, HIGH);
      digitalWrite(ledR, LOW);
      digitalWrite(ledY, LOW);



  } else{
      digitalWrite(ledG, LOW);
      if (currentMillis - previousMillis >= intervalA) {
          // save the last time you blinked the LED
          previousMillis = currentMillis;

          // if the LED is off turn it on and vice-versa:
          if (state1 == LOW){
            state1 = HIGH;
            state2 = LOW;
          }
          else{
            state1 = LOW;
            state2 = HIGH;
          }
          // set the LED with the ledState of the variable:
          digitalWrite(ledR, state1);
          digitalWrite(ledY, state2);
    }
  }
}
