/*
CommandListener.h - A class for listening and executing commands from an external source
*/
#ifndef CommandListener_h
#define CommandListener_h

#include "Arduino.h"
#include "module_PI/Arduino_Uno/DataPacket.h"
#include "Alarm.h"

#define SIZE_OF_HASH_IN_PACKET 8
#define SIZE_OF_DATAPACKET_IN_BYTES 64

class CommandListener {
  public:
    CommandListener(Alarm* alarm);
    void executeCommandIfAvailable();
  private:
    void executeCommand();
    DataPacket currentPacket = 0;
    Alarm* alarm;
};

#endif
