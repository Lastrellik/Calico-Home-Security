The RASPBERRY PI Module.

The Raspberry PI module is intended to allow for expanded implementation of the
security system by making the following possible:

-Installing a 7 inch touchscreen
-Enhanced and stored log files
-Remote administration of the Home Security system
-Email and Picture alerts of intruders
-SSH administration
-Web server management
-User database with unique keycodes

A critical element behind the workings of the Raspberry PI module is reliable and
accurate communication between the Raspberry PI and the Arduino Uno. In order to
accomplish this we have developed the SerialComm class and the DataPacket class
to assist with the management and structure of the communication.

#The DataPacket class
The DataPacket class is designed around organizing serial communication into 64-byte
*DataPackets*. The DataPackets, inspired by TCP-IP packets, have a *Header* section
and a *Message* section. The *Header* section consists of a single byte representing
the start of the packet (~), an 8 byte SHA1 hash of the Message itself (not the whole
packet, only the Message portion), and a single byte identifying the Packet Type
(Log, Command, info, Data Request, etc).

The *Message* portion of the packet is the actual message of the packet itself. This
could be Logging information or a Command, but it is basically the actual data itself
instead of the metadata inside of the header.

#The SerialComm class
SerialComm is a static class designed to create and communicate the DataPackets to
and from the Raspberry PI. SerialComm generates the SHA-1 hash of the message and
stores it in the header of the DataPacket using the Cryptosuite library in the lib
folder.

The 4 methods used in the SerialComm class are sendLogMessage, sendCommandMessage, sendInfoMessage and sendInstructionRequest. Each method is segregated in such a way that the user doesn't have to insert any kind of input to determine the metadata of the packet, but rather just uses the method most appropriate to the message being communicated. For example, Log messages will use the sendLogMessage method, etc.

#The communication
To ensure accurate communication between the PI and the Uno, The SHA-1 hash of the message itself is stored in the header of the DataPacket. This allows for two things:
-The device receiving the packet can verify the contents of the DataPacket
-The device receiving the packet will also send back the 8-byte hash over Serial so the other device gets successful communication confirmation

Right now the responsibility of sending the Hash back over Serial is handled by the SerialInputListener class on the Raspberry PI. This class is a threaded class designed to constantly be running in the background during Raspberry PI operation. It listens for Serial traffic and stores it in a Queue to allow for further processing. It also organizes the Serial traffic into DataPackets to allow for processing locally on the Raspberry PI.
