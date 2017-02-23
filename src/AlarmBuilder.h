/*
  AlarmBuilder.h - A class dedicated to the correct construction of the Alarm
  The engineering paradigm used here is the "fluent" pattern. Read about it here:
  https://dzone.com/articles/factories-builders-and-fluent- and here:
  http://stackoverflow.com/questions/40264/how-many-constructor-arguments-is-too-many
  and here:
  http://stackoverflow.com/questions/222214/managing-constructors-with-many-parameters-in-java
*/
#ifndef AlarmBuilder_h
#define AlarmBuilder_h

#include "Component.h"
#include "LED.h"
#include "Buzzer.h"
#include "Laser.h"
#include "Photoresistor.h"
#include "Button.h"
#include "Alarm.h"

class AlarmBuilder {
  private:
    LED _alarmLED; //LED for alarm status
    LED _statusLED; //LED for calibration and status
    Buzzer _buzzer;
    Laser _laser;
    Photoresistor _photoresistor;
    Button _button;

  public:
    AlarmBuilder alarmLED(LED alarmLED);
    AlarmBuilder statusLED(LED statusLED);
    AlarmBuilder buzzer(Buzzer buzzer);
    AlarmBuilder laser(Laser laser);
    AlarmBuilder photoresistor(Photoresistor photoresistor);
    AlarmBuilder button(Button button);
    Alarm buildAlarm();
    LED getAlarmLED();
    LED getStatusLED();
    Buzzer getBuzzer();
    Laser getLaser();
    Photoresistor getPhotoresistor();
    Button getButton();
    AlarmBuilder(void);
};

#endif
