package com.suj.lang.sockets;

/**
 * Created by sujayjayaram on 05/02/2016.
 * Socket programming boils down to two systems communicating with one another.
 * Network communication comes in two flavors: Transport Control Protocol (TCP)
 * and User Datagram Protocol (UDP). TCP and UDP are used for different purposes
 * and both have unique constraints:

 see http://www.javaworld.com/article/2853780/java-app-dev/socket-programming-for-scalable-systems.html

 The Transmission Control Protocol(TCP) and the Internet Protocol (IP) are two
 of the network standards that define the Internet.  IP defines how computers
 can get data to each other over a routed, interconnected set of networks.
 TCP defines how applications can create reliable channels of communication
 across such a network. Basically, IP defines addressing and routing, while
 TCP defines how to have a conversation across the link without garbling or
 losing data.  TCP/IP grew out of research by the U.S. Dept. of Defense and
 is based on a loose rather than a strict approach to layering.

 Many other key Internet protocols, such as the Hypertext Transfer Protocol (HTTP),
 the basic protocol of the Web, and the Simple Mail Transfer Protocol (SMTP),
 the core email transfer protocol, are built on top of TCP. The User Datagram
 Protocol(UDP), a companion to TCP, sacrifices the guarantees of reliability
 that TCP makes in return for faster communications.

 TCP is relatively simple and reliable protocol that enables a client to make a
 connection to a server and the two systems to communicate.
 In TCP, each entity knows that its communication payloads have been received.
 TCP guarantees the recipient will receive the packets in order by numbering them.
 The recipient sends messages back to the sender saying it received the messages.
 If the sender doesn’t get a correct response, it will resend the packets to
 ensure the recipient received them. Packets are also checked for errors. TCP is
 all about this reliability — packets sent with TCP are tracked so no data is lost
 or corrupted in transit. This is why file downloads don’t become corrupted even
 if there are network hiccups. Of course, if the recipient is completely offline,
 your computer will give up and you’ll see an error message saying it can’t
 communicate with the remote host.
 TCP is UNICAST

 UDP is a connectionless protocol and is good for scenarios where you do not
 necessarily need every packet to arrive at its destination, such as media streaming.

 UDP does not provide the reliability nor ordering guarantees that TCP does.

 Broadcast is sent to everybody that wants to receive it, but he MUST be on the
 same physical network, because by default routers/gateways do not forward BROADCAST messages.

 */
public class ReadMe {
}
