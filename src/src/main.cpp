/**
 * Calico Home Security System
 *
 *
 */
#include "Arduino.h"

#ifndef LED_BUILTIN
#define LED_BUILTIN 13
#endif

void setup()

{
  // initialize LED digital pin as an output.
  pinMode(LED_BUILTIN, OUTPUT);
}
void loop()
{

  for (int i = 0; i < 75; i=i+1)
  {
    digitalWrite(LED_BUILTIN, HIGH);
      delay(i);
    digitalWrite(LED_BUILTIN, LOW);
      delay(i);
  }
  for (int j = 75; j > 0; j=j-1)
  {
    digitalWrite(LED_BUILTIN, HIGH);
      delay(j);
    digitalWrite(LED_BUILTIN, LOW);
      delay(j);
  }

}
