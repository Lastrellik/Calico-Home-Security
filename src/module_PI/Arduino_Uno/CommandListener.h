/*
CommandListener.h - A class for listening and executing commands from an external source
*/

#ifndef CommandListener_h
#define CommandListener_h

#include "Arduino.h"
#include "Alarm.h"

#define SIZE_OF_DATAPACKET_IN_BYTES 8

class CommandListener {
  public:
    CommandListener(Alarm* alarm);
    void executeCommandIfAvailable();
  private:
    int commandByteToInt(byte* commandBytes);
    void executeCommand(byte* commandBytes);
    byte* commandBytes;
    Alarm* alarm;
};

#endif
