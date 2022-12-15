import socket
import network
import time
from machine import Pin, PWM, ADC
from utime import sleep
​
#from matplotlib import pyplot as plt
print(machine.freq())
#machine.freq(133000000)
#print(machine.freq())

​
#Spectrometer init
spec_ST = Pin(10, Pin.OUT)
spec_CLK = Pin(11, Pin.OUT)
# spec_vid_1 = machine.ADC(26)
spec_vid_1 = machine.ADC(27)
spec_vid_3 = machine.ADC(28)
data = []
​
#initialize them to high
spec_CLK.high()
spec_ST.low() #high
print("INIT ST CLK")
print(spec_CLK.value())
print(spec_ST.value())
​

​
#Define read spec function
def readSpectrometer () :
    global reads
    global readOnce
    readOnce = True
    reads=reads+1
    delayTime = .000001
    
    spec_CLK.low()
    time.sleep(delayTime)
    spec_CLK.high()
    time.sleep(delayTime)
    spec_CLK.low()
    spec_ST.high()
    time.sleep(delayTime)
    
    intTime = 10
    re = 1
    
    #print ("ABOUT TO START FIRST FOR LOOP")
    
    for j in range(re):
        for i in range(15000):
           spec_CLK.high()
           time.sleep(delayTime)
           spec_CLK.low()
           time.sleep(delayTime)
        
    spec_ST.low()
    
    #print("SECOND FOR LOOP")
        
    for i in range(85):
      spec_CLK.high()
      time.sleep(delayTime)
      spec_CLK.low()
      time.sleep(delayTime)
    
    spec_CLK.high()
    time.sleep(delayTime)
    spec_CLK.low()
    time.sleep(delayTime)
    #print("ENTERING THIRD FOR LOOP")
    for i in range(288):
        data.append(spec_vid_1.read_u16())
        spec_CLK.high()
        time.sleep(delayTime)
        spec_CLK.low()
        time.sleep(delayTime)
    
​
        
    
    spec_ST.high()
    #print("ENTERING LAST FOR LOOP")
    for i in range(7):
        spec_CLK.high()
        time.sleep(delayTime)
        spec_CLK.low()
        time.sleep(delayTime)
    
    spec_CLK.high()
    time.sleep(delayTime)
    
 
while True:
    #timing init
​
    readSpectrometer()
​
    print(data)
    break
        
        # x axis values
        #x = list(range(1,288))
        # corresponding y axis values
        #y = data
  
        # plotting the points 
        #plt.plot(x, y)
  
        # naming the x axis
        #plt.xlabel('x - axis')
        # naming the y axis
        #plt.ylabel('y - axis')
  
        # giving a title to my graph
        #plt.title('My first graph!')
  
        # function to show the plot
        #plt.show()
        