/* Sweep
 by BARRAGAN <http://barraganstudio.com>
 This example code is in the public domain.

 modified 8 Nov 2013
 by Scott Fitzgerald
 http://www.arduino.cc/en/Tutorial/Sweep
*/

#include <Servo.h>

Servo servopan,servotilt;  // create servo object to control a servo
// twelve servo objects can be created on most boards
int gun = 7;
int post = 0;
int posp = 0;// variable to store the servo position

void setup() {
  servopan.attach(3);
  servotilt.attach(4);
  pinMode(gun, OUTPUT);
}

void loop() {
  for (posp = 0; posp <= 120; posp += 1);
  for (post = 60; post <= 100; post += 1)
 
  { 
    servopan.write(posp);
    servotilt.write(post);
    delay(10);                      
  }
  digitalWrite(gun,HIGH);
  delay (500);
  digitalWrite(gun,LOW);
  for (post = 120; post >= 100; post -= 1);
  for (posp = 120; posp >= 0; posp -= 1) 
  { 
    servopan.write(posp);
    servotilt.write(post);
    delay(10);                       
  }
  digitalWrite(gun,HIGH);
  delay (500);
  digitalWrite(gun,LOW);
}

