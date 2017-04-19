/**
  Calico Home Security System, Photoresistor.cpp
  Purpose: Reads in the Photoresistor output

  @author Christopher Nash, Jason Bruderer, David Tille, Tyler Jacobs
  
*/
#include "Arduino.h"
#include "Photoresistor.h"

/**
  Constructor for Photresistor that sets the pin of the Photresistor.
    Also sets the pinMode to montior the pin passed and sets it to INPUT for
    a voltage greater than 3.0V is present at the pin (5V boards)
    a voltage less than 1.0V (Approx) is present at the pin (3.3V boards);
*/
Photoresistor::Photoresistor(int pin) : Component(pin, "PHOTORESISTOR"){
  if(Properties::MODULE_PI) Serial.write("13450"); // 13450 = Log, Debug, Photoresistor object has been successfully created
  pinMode(pin, INPUT);
}
/**
  Base Contructor for Photoresistor
*/
Photoresistor::Photoresistor(){
}
/**
  Reads in value that is be produced by the photoresistor
  @return Returns the value of the photorsistor at request
*/
int Photoresistor::takeReading() {
  return analogRead(Component::getPin());
}
