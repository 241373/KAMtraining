#!/usr/bin/env python3

import socket
import time
import datetime

MCAST_GRP = '239.255.255.240'
MCAST_PORT = 5151
MULTICAST_TTL = 3

sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM, socket.IPPROTO_UDP)
sock.setsockopt(socket.IPPROTO_IP, socket.IP_MULTICAST_TTL, MULTICAST_TTL)

sock.sendto(b"robot", (MCAST_GRP, MCAST_PORT))

try:

    count = 0

    while True:
        print("sending...")
        payload = f"<1;{count};<0;0;10;80;10>>".encode('utf-8')
        sock.sendto(payload, (MCAST_GRP, MCAST_PORT))
        
        if count == 1:
            t = datetime.datetime.now()
            triage_payload = f"<1;52.284843,20.889328;{t};0;{payload}>".encode('utf-8')
            sock.sendto(triage_payload, (MCAST_GRP, MCAST_PORT))
            print("sending start triage...")
        
        if count == 6:
            t = datetime.datetime.now()
            triage_payload = f"<1;52.284843,20.889328;{t};2;{payload}>".encode('utf-8')
            sock.sendto(triage_payload, (MCAST_GRP, MCAST_PORT))
            print("sending stop triage...")
        
        
        
        count = count + 1
        time.sleep(5)

except KeyboardInterrupt:
    print("Koniec...")
    pass