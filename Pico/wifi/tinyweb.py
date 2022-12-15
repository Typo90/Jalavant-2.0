import socket
import network
import tinyweb
import time
from machine import Pin, PWM, ADC
import logging
from utime import sleep
​
​
# Define SSID and password for the access point
ssid = "Jalavant"
password = "Jalavant"
​
# Define an access point, name it and then make it active
ap = network.WLAN(network.AP_IF)
ap.config(essid=ssid, password=password)
ap.active(True)
​
data = []
for i in range(288):
    data.append(i)
​
while True:

        

    
        #turn off LEDs
        red_pwm.duty_u16 ( 0 ) #duty 0% (low)
        green_pwm.duty_u16 ( 0 ) #duty 0% (low)
        blue_pwm.duty_u16 ( 0 ) #duty 0% (low)
        IR_pwm.duty_u16 ( 0 ) #duty 0% (low)
    
        #send array to phone
        print("send array to phone")
        # Wait until it is active
        while ap.active == False:
            pass
​
        print("Access point active")
        # Print out IP information
        print(ap.ifconfig())
​
​
        # Start up a tiny web server
        app = tinyweb.webserver()
​
        # Serve a simple Hello World! response when / is called
        # and turn the LED on/off using toggle()
        @app.route('/')
        async def index(request, response):
            # Start HTTP response with content-type text/html
            await response.start_html()
            # Send actual HTML page
            await response.send('<html><body><h1>Spectrometer Values</h1></body></html>\n')
            for i in range(288):
            
              await response.send(str(data[i]).encode())
              await response.send(',')
              await response.send('\n')
        
​
​
​
        # Run the web server as the sole process
        app.run(host="0.0.0.0", port=80)
​