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


Message Translation Chart
-------------------------
| Type [0-9] | Log Level  [0-9] | Details  [000-999] |
| ----------- | ---------------- | ------------------- |
| 0 - N/A     | 0 - N/A          | 000 - N/A            |
| 1 - Log     | 1 - Error        | 001 - Something      |
| 2 - Data    | 2 - Warn         | 002 - Something else |
| 3 - Command | 3 - Debug        | 003 - Even more      |
| 4 - Error   | 4 - Info         | 514 - Component Configured on Pin 14 |
| 5 - Request | 5 - Trace        | 515 - Component Configured on Pin 15 |
| | | 516 - ... |
| | | 703 - Execute Command - Arm |

Notes:
* In general, you should never send a message of Type `0`. That really just wouldn't make any sense...
* `Log Level` is only relevant when a `Log` Type message is sent. In all other cases a `0 - N/A` should be passed.
