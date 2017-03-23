/*
PacketType.h - A class for holding centralized Packet Types
*/

#ifndef PacketType_h
#define PacketType_h

#include "Arduino.h"

enum class PacketType{LOG = 0, COMMAND = 1, INFO = 2, REQUEST = 3, ERROR = 4};

#endif
