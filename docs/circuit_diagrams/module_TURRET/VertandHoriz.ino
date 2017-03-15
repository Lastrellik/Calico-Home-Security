#include <Servo.h>

Servo vert,hori;  // create servo object to control a servo

int pos = 0;    // variable to store the servo position
int laser = 12;
int buzzer = 11;

void setup()
{
     hori.attach(9);
     vert.attach(10);  // attaches the servo on pin 9 to the servo object
     pinMode(laser,OUTPUT);
     pinMode(buzzer,OUTPUT);
}

void loop()
{
     int timeBetweenBursts = random(200,1000);
     int timeBetweenShots = random(50,200);
     int vertStart = random(1,180);
     int vertEnd = random(1,180);
     int horiStart = random(1,180);
     int horiEnd = random(1,180);
     int numShots = random(5,20);

     int vertChange = (vertEnd - vertStart) / numShots; //how much to move vertical axis by each shot
     int horiChange = (horiEnd - horiStart) / numShots;

     vert.write(vertStart);//let it get to start position first, wait a little
     hori.write(horiStart);
     delay(100);

     for(int shot = 0; shot<numShots; shot++){
          vert.write(vertStart);
          hori.write(horiStart);

          vertStart += vertChange;//increment the vert value for next time
          horiStart += horiChange;

          fire();
          delay(timeBetweenShots); //add a bit of variety to the speed of shots
     }
     delay(timeBetweenBursts);
}

void fire(){
     digitalWrite(laser,HIGH);
     analogWrite(buzzer,100);
     delay(20);//adjust this to change length of turret shot
     digitalWrite(laser,LOW);
     analogWrite(buzzer, 0); 
} 
