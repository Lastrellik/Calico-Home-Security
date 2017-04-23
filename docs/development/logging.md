Logging & Command Messages
==========================
This document describes the Logging & Command Message structure and usage.

Message Scheme
--------------
The scheme we decided on is similar to that of HTML codes. HTML codes follow a standard form with 3 digits. For example, `404` is a `Not Found` error. The first digit `4` represents a 'class' of error. In this case, a `Client Error`. The last two digits represents the actual 'message'.

Following a similar pattern we decided that the key things we needed to specify were a `Type`, a `Log Level`, and then the `Details` of the message. We will represent this with a 5-Digit number.

| Type | Log Level | Details |
| ---- | --------- | ------- |
| The `Type` of message such as a `Log` or a `Command` | Only applicable for `Log` commands. `Error`, ` Debug`, etc. | The nitty-gritty details on which message you are intending to send. |

The receiving client will be responsible for parsing the sent message and acting on it accordingly. Some ideas for accomplishing this might be to turn it into a String and looking at each character. Or an array list of Chars. Etc.

The Arduino will also receive commands using this same scheme. It will receive the message and respond. The Arduino's functionality will be limited in the actions it will perform. For example, if a client were to send the Arduino a Log message, the Arduino would just ignore the message as it would only be listening for a `Command` type of message.


Examples
--------
The following are a few examples of some messages which might be sent and what they translate to:
* `13514` = This message would represent a `1 - Log` Type command being logged at a `Debug` Log Level and the representing the `514` Detailed message (whatever `514` is defined as below).
* `30025` = This message would represent a `3 - Data` Type command. Since this is not a `Log` Type of command, the `Log Level` is irrelevant and thus a `0 - N/A` is given. The Details for this command are `025`. The intention is for you, the receiver, to execute this command. Perhaps it is meant to represent a command such as `Arm` or `Disarm`.


Developer Code Comment Convention
---------------------------------
Whenever a message is sent, it should have the code's translation included on the same line where the code is being sent. This will enable an easier time for other developers coming in at a future date and being able to more easily parse what is going on with the message being sent.

Example:
```c++
Serial.write(13514);  // 13514 = Log, Debug, Component Configured on Pin 14
```
This should hold true for both the Java and the C++ sides of the code.

Where possible, especially in Java, a value should be defined as a member variable with a descriptive name OR be 'built' using enum (or similar) values, all with descriptive names, so that the code can easily be translated by another developer.


Message Translation Chart
-------------------------
| Type [0-9]  | Log Level  [0-9] | Details  [000-999]  |
| ----------- | ---------------- | ------------------- |
| 0 - N/A     | 0 - N/A          | 000 - N/A            |
| 1 - Log     | 1 - Error        | 001 - Execute Command: Arm |
| 2 - Data    | 2 - Warn         | 002 - Execute Command: Disarm |
| 3 - Command | 3 - Debug        | 003 - Execute Command: Silence |
| 4 - Error   | 4 - Info         | 004 - Execute Command: Calibrate |
| 5 - Request | 5 - Trace        | 005 - Execute Command: Trigger |
|             |                  | 006 - Execute Command: Reset Calibration |
|             |                  | 007 - Execute Command: Test Components |
|             |                  | 008 - Execute Command: Trip|
|             |                  | 009 - Execute Command: Toggle Laser |
|             |                  | 100 - Alarm object successfully created |
|             |                  | 101 - Alarm base photoresistor reading determined
|             |                  | 102 - Alarm failed action
|             |                  | 103 - Alarm successful action
|             |                  | 104 - Alarm failed to arm
|             |                  | 105 - Alarm successfully armed
|             |                  | 106 - Alarm successful calibration
|             |                  | 107 - Alarm has begun arming
|             |                  | 108 - Alarm has been triggered
|             |                  | 109 - Alarm calibration is being reset
|             |                  | 110 - Alarm is being disarmed
|             |                  | 111 - Alarm is being is being silenced
|             |                  | 112 - Alarm has been tripped
|             |                  | 150 - Button object successfully created
|             |                  | 151 - Button has been pressed
|             |                  | 200 - Buzzer object has been created
|             |                  | 201 - Buzzer has started to sound
|             |                  | 202 - Buzzer has stopped sounding
|             |                  | 250 - Component object successfully created
|             |                  | 300 - ComponentTester default constructor called
|             |                  | 301 - ComponentTester object successfully created
|             |                  | 350 - Laser object successfully created
|             |                  | 351 - Laser has been turned on
|             |                  | 352 - Laser has been turned off
|             |                  | 353 - Laser has been toggled
|             |                  | 400 - LED object successfully created
|             |                  | 401 - LED has been turned on
|             |                  | 402 - LED has been turned off
|             |                  | 404 - LED toggling
|             |                  | 450 - Photoresistor object has been successfully created

**Details Block Definitions:**
* [001 - 009] - Reserved for Commands intended for the Alarm (Arduino) to execute.
* [100 - 149] - Reserved for Alarm Class messages to be logged.
* [150 - 199] - Reserved for Button messages to be logged.
* [200 - 249] - Reserved for Buzzer messages to be logged.
* [250 - 299] - Reserved for Component messages to be logged.
* [300 - 349] - Reserved for ComponentTester messages to be logged.
* [350 - 399] - Reserved for Laser messages to be logged.
* [400 - 449] - Reserved for LED messages to be logged.
* [450 - 499] - Reserved for Photoresistor messages to be logged.
* [700 - 999] - Currently reserved for TURRET Commands.

**Notes:**
* In general, you should never send a message of Type `0`. That really just wouldn't make any sense...
* `Log Level` is only relevant when a `Log` Type message is sent. In all other cases a `0 - N/A` should be passed.
