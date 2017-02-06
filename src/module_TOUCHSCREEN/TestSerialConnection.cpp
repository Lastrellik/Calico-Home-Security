
/**
 * Calico Home Security System
 *
 *
 */
#include "Arduino.h"
#ifndef LED_BUILTIN
#define LED_BUILTIN 13
#endif
int incomingByte = 0;   // for incoming serial data
bool isOn = false;

void setup() {
        Serial.begin(9600);     // opens serial port, sets data rate to 9600 bps
        pinMode(LED_BUILTIN, OUTPUT);
        digitalWrite(LED_BUILTIN, LOW);
}

void loop() {

        // send data only when you receive data:
        if (Serial.available() > 0) {
                // read the incoming byte:
                incomingByte = Serial.read();
                isOn = !isOn;
                if(isOn){
                  digitalWrite(LED_BUILTIN, HIGH);
                } else {
                  digitalWrite(LED_BUILTIN, LOW);
                }

                // say what you got:
                Serial.print("I received: ");
                Serial.println(incomingByte, DEC);
        }
}
