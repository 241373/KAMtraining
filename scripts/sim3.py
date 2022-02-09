#!/usr/bin/env python3

import socket
import time
import datetime

MCAST_GRP = '255.255.255.255'
MCAST_PORT = 5151
MULTICAST_TTL = 3

sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM, socket.IPPROTO_UDP)
# sock.setsockopt(socket.IPPROTO_IP, socket.IP_BROADCAST_TTL, MULTICAST_TTL)

sock.sendto(b"robot", (MCAST_GRP, MCAST_PORT))

try:

    count = 0

    print("sending...")
    payload = f"101,black,34,1,false,false,0,false,false,0,0,0".encode('utf-8')
    payload2 = f"<2;{count};<0;0;10;80;10>>".encode('utf-8')
    
    sock.sendto(payload, (MCAST_GRP, MCAST_PORT))
    sock.sendto(payload2, (MCAST_GRP, MCAST_PORT))
    



except KeyboardInterrupt:
    print("except!!!!")
    pass
finally:
    print("koniec...")
